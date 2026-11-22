package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartStatus;
import fr.factionbedrock.aerialhell.Entity.Util.VoluciteGlyphParticles;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.*;

//anchored warden boss room stairs : 4 identical staircases around the room center (STAIRS_TEMPLATE nbt, rotated for each side)
//activation phase : only the staircase in front of the warden
//shield transitions & shield phases : every staircase is destroyed (from top to bottom), the warden arms can turn freely
//shield break transitions & vulnerable phases (the warden does not turn) : the 2 staircases on the warden facing axis (front & back) are built (from bottom to top),
//  the 2 others are destroyed. the arms are on the other axis
//final transition, final phase, dying & dead phases (the warden does not turn anymore) : every staircase is built
//each tick, the world converges toward the wanted stairs, a few blocks at a time (instant before the first awakening : dungeon just generated)
//blocks placed by players at stairs positions are destroyed with their drops (when a staircase is destroyed or built). stairs blocks (core protected) never drop
public class BossRoomStairsHandler
{
    public static final Identifier STAIRS_TEMPLATE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "volucite_castle/boss_room/ground_floor/center_stairs");
    //north staircase template relative origin pos
    private static final int NORTH_ORIGIN_X = -2, NORTH_ORIGIN_Y = 14, NORTH_ORIGIN_Z = -11;
    //template positions that are never modified (relative to the template origin)
    private static final Set<BlockPos> POS_BLACKLIST = Set.of(new BlockPos(0, 0, 0), new BlockPos(1, 0, 0), new BlockPos(2, 0, 0), new BlockPos(3, 0, 0), new BlockPos(1, 0, 1), new BlockPos(2, 0, 1));

    private enum StairsTarget
    {
        NONE, FRONT, FACING_AXIS, ALL;

        //sides of the room center whose staircase is wanted
        private Set<Direction> getSides(Direction front)
        {
            return switch (this)
            {
                case NONE -> EnumSet.noneOf(Direction.class);
                case FRONT -> EnumSet.of(front);
                case FACING_AXIS -> EnumSet.of(front, front.getOpposite());
                case ALL -> EnumSet.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);
            };
        }
    }

    private record StairsBlock(Direction side, BlockPos pos, BlockState state) {}

    private final VoluciteWardenEntity warden;
    @Nullable private BoundingBox room; //boss room piece (the one the warden stands in), null outside a volucite castle
    private final List<StairsBlock> stairsBlocks = new ArrayList<>(); //the 4 staircases, from bottom to top (staircases are built & destroyed at the same time)
    private boolean stairsBlocksComputed;

    public BossRoomStairsHandler(VoluciteWardenEntity warden) {this.warden = warden;}

    public void setRoom(@Nullable BoundingBox room)
    {
        if (Objects.equals(room, this.room)) {return;}
        this.room = room;
        this.stairsBlocks.clear();
        this.stairsBlocksComputed = false;
    }

    public void tick()
    {
        if (this.room == null || !(this.warden.level() instanceof ServerLevel level)) {return;}
        if (!this.stairsBlocksComputed) {this.computeStairsBlocks(level);}

        int budget = this.warden.alreadyActivatedOnce() ? 4 : Integer.MAX_VALUE; //blocks per tick (infinite before the first awakening - to make stairs build instant)
        Set<Direction> wantedSides = this.getTarget().getSides(this.warden.getFacingDirection());
        budget = this.destroy(level, wantedSides, budget);
        this.build(level, wantedSides, budget);
    }

    //activation phase : front staircase. shield transitions & shield phases : no stairs. phases where the warden does not turn : stairs on its facing axis
    //final transition, final phase, dying & dead phases : every staircase (arms raised then detached, the warden does not turn anymore)
    private StairsTarget getTarget()
    {
        if (this.warden.isInActivationPhase()) {return StairsTarget.FRONT;}
        if (this.warden.isInShieldTransition() || this.warden.isInShieldPhase()) {return StairsTarget.NONE;}
        if (this.warden.isInShieldBreakTransition() || this.warden.isInVulnerablePhase()) {return StairsTarget.FACING_AXIS;}
        return StairsTarget.ALL;
    }

    //destroys the blocks at the positions of the unwanted staircases, from top to bottom. returns the remaining budget
    //stairs blocks are destroyed without drops, other blocks (placed by players) drop as usual
    private int destroy(ServerLevel level, Set<Direction> wantedSides, int budget)
    {
        for (int i = this.stairsBlocks.size() - 1; i >= 0 && budget > 0; i--)
        {
            StairsBlock block = this.stairsBlocks.get(i);
            if (wantedSides.contains(block.side()) || !level.isLoaded(block.pos())) {continue;}
            BlockState current = level.getBlockState(block.pos());
            if (!canDestroy(level, block.pos(), current)) {continue;} //nothing to destroy
            level.destroyBlock(block.pos(), !isStairsBlock(current, block), this.warden); //break particles & sound
            budget--;
        }
        return budget;
    }

    //builds the missing blocks of the wanted staircases, from bottom to top. returns the remaining budget
    //a block in the way (placed by a player) is destroyed first, with its drops
    private int build(ServerLevel level, Set<Direction> wantedSides, int budget)
    {
        for (StairsBlock block : this.stairsBlocks)
        {
            if (budget <= 0) {break;}
            BlockPos pos = block.pos();
            if (!wantedSides.contains(block.side()) || !level.isLoaded(pos)) {continue;}
            BlockState current = level.getBlockState(pos);
            if (isStairsBlock(current, block) || this.isOccupied(level, pos)) {continue;} //already built, or an entity in the way (tried again next tick)
            if (!current.canBeReplaced())
            {
                if (!canDestroy(level, pos, current)) {continue;} //unbreakable block in the way
                level.destroyBlock(pos, true, this.warden);
            }
            level.setBlock(pos, block.state(), Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE); //exact template state : no neighbor shape update
            level.playSound(null, pos, block.state().getSoundType().getPlaceSound(), SoundSource.BLOCKS, 0.8F, 0.8F);
            VoluciteGlyphParticles.sendInward(level, Vec3.atCenterOf(pos), 1.0F, 2.5F, 2); //glyphs gathering into the rebuilt block
            budget--;
        }
        return budget;
    }

    //stairs block placed by the warden : the template state, or any core protected block
    private static boolean isStairsBlock(BlockState current, StairsBlock block) {return current == block.state() || BlockHelper.isCoreProtected(current);}

    //air, fluids & unbreakable blocks are left as they are
    private static boolean canDestroy(ServerLevel level, BlockPos pos, BlockState state) {return !state.isAir() && !(state.getBlock() instanceof LiquidBlock) && state.getDestroySpeed(level, pos) >= 0.0F;}

    private boolean isOccupied(ServerLevel level, BlockPos pos) {return !level.getEntitiesOfClass(LivingEntity.class, new AABB(pos), entity -> !this.isWardenBody(entity)).isEmpty();}

    //the warden & its attached body parts are ignored : their hitboxes overlap the innermost steps (the chest for example), and they never move
    //arm segments are not ignored : during the final transition, the side staircases are built while the arms are raised (blocks placed once the arms are out of the way)
    //detached parts are not ignored either (final phase)
    private boolean isWardenBody(Entity entity)
    {
        if (entity == this.warden) {return true;}
        return entity instanceof VoluciteWardenPartEntity part && !(part instanceof VoluciteWardenArmSegmentEntity) && part.getPartStatus() == PartStatus.ATTACHED && this.warden.is(part);
    }

    //stairs blocks of the 4 staircases : template blocks (except blacklisted positions) of the north staircase, rotated around the room center for the other sides
    private void computeStairsBlocks(ServerLevel level)
    {
        this.stairsBlocksComputed = true;
        Optional<StructureTemplate> template = level.getStructureTemplateManager().get(STAIRS_TEMPLATE);
        if (this.room == null || template.isEmpty() || template.get().palettes.isEmpty()) {return;}

        double centerX = (this.room.minX() + this.room.maxX() + 1) / 2.0D, centerZ = (this.room.minZ() + this.room.maxZ() + 1) / 2.0D; //same as the warden anchor
        BlockPos northOrigin = BlockPos.containing(centerX + NORTH_ORIGIN_X, this.room.minY() + NORTH_ORIGIN_Y, centerZ + NORTH_ORIGIN_Z);
        for (Rotation rotation : List.of(Rotation.NONE, Rotation.CLOCKWISE_90, Rotation.CLOCKWISE_180, Rotation.COUNTERCLOCKWISE_90)) //north, east, south & west staircases
        {
            Direction side = rotation.rotate(Direction.NORTH);
            for (StructureTemplate.StructureBlockInfo info : template.get().palettes.getFirst().blocks())
            {
                if (info.state().isAir() || POS_BLACKLIST.contains(info.pos())) {continue;}
                this.stairsBlocks.add(new StairsBlock(side, rotateAroundCenter(northOrigin.offset(info.pos()), rotation, centerX, centerZ), info.state().rotate(rotation)));
            }
        }
        this.stairsBlocks.sort(Comparator.comparingInt(block -> block.pos().getY()));
    }

    //block position rotated around the room center, quarter turns clockwise (north -> east -> south -> west, like Rotation)
    private static BlockPos rotateAroundCenter(BlockPos pos, Rotation rotation, double centerX, double centerZ)
    {
        double dx = pos.getX() + 0.5D - centerX, dz = pos.getZ() + 0.5D - centerZ; //block center, relative to the room center
        double rotatedX = switch (rotation) {case CLOCKWISE_90 -> -dz; case CLOCKWISE_180 -> -dx; case COUNTERCLOCKWISE_90 -> dz; default -> dx;};
        double rotatedZ = switch (rotation) {case CLOCKWISE_90 -> dx; case CLOCKWISE_180 -> -dz; case COUNTERCLOCKWISE_90 -> -dx; default -> dz;};
        return BlockPos.containing(centerX + rotatedX, pos.getY(), centerZ + rotatedZ);
    }
}
