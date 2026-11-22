package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Util.VoluciteGlyphParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.*;

//anchored warden boss room stairs : 4 identical staircases around the room center (STAIRS_TEMPLATE nbt, rotated for each side)
//shield transitions & shield phases : every staircase is destroyed (from top to bottom), the warden arms can turn freely
//activation, shield break transitions, vulnerable phases & final transition (the warden does not turn) : the 2 staircases on the warden facing axis (front & back) are built (from bottom to top),
//  the 2 others are destroyed. the arms are on the other axis
//each tick, the world converges toward the wanted stairs, a few blocks at a time (instant before the first awakening : dungeon just generated)
public class BossRoomStairsHandler
{
    public static final Identifier STAIRS_TEMPLATE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "volucite_castle/boss_room/ground_floor/center_stairs");
    //north staircase template relative origin pos
    private static final int NORTH_ORIGIN_X = -2, NORTH_ORIGIN_Y = 14, NORTH_ORIGIN_Z = -11;
    //template positions that are never modified (relative to the template origin)
    private static final Set<BlockPos> POS_BLACKLIST = Set.of(new BlockPos(0, 0, 0), new BlockPos(1, 0, 0), new BlockPos(2, 0, 0), new BlockPos(3, 0, 0), new BlockPos(1, 0, 1), new BlockPos(2, 0, 1));

    private enum StairsTarget {NONE, FACING_AXIS, ALL}
    private record StairsBlock(BlockPos pos, BlockState state) {}

    private final VoluciteWardenEntity warden;
    @Nullable private BoundingBox room; //boss room piece (the one the warden stands in), null outside a volucite castle
    private final Map<Direction.Axis, List<StairsBlock>> stairsBlocks = new EnumMap<>(Direction.Axis.class); //x axis : east & west staircases, z axis : north & south. from bottom to top
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
        StairsTarget target = this.getTarget();
        if (!this.stairsBlocksComputed) {this.computeStairsBlocks(level);}

        int budget = this.warden.alreadyActivatedOnce() ? 4 : Integer.MAX_VALUE; //blocks per tick (infinite before the first awakening - to make stairs build instant)

        if (target == StairsTarget.ALL)
        {
            //TODO build all stairs
        }
        else
        {
            Direction.Axis facingAxis = this.warden.getFacingAxis();
            for (Direction.Axis axis : List.of(Direction.Axis.X, Direction.Axis.Z))
            {
                if (target == StairsTarget.NONE || axis != facingAxis) {budget = this.destroy(level, this.getStairsBlocks(axis), budget);}
            }
            if (target == StairsTarget.FACING_AXIS) {this.build(level, this.getStairsBlocks(facingAxis), budget);}
        }
    }

    //shield transitions & shield phases : no stairs. phases where the warden does not turn : stairs on its facing axis. final phase, dying & dead phases : unchanged
    private StairsTarget getTarget()
    {
        if (this.warden.isInShieldTransition() || this.warden.isInShieldPhase()) {return StairsTarget.NONE;}
        if (this.warden.isInFinalPhase() || this.warden.isInDeadOrDyingPhase()) {return StairsTarget.ALL;}
        return StairsTarget.FACING_AXIS;
    }

    private List<StairsBlock> getStairsBlocks(Direction.Axis axis) {return this.stairsBlocks.getOrDefault(axis, List.of());}

    //destroys the built stairs blocks, from top to bottom. returns the remaining budget
    private int destroy(ServerLevel level, List<StairsBlock> blocks, int budget)
    {
        for (int i = blocks.size() - 1; i >= 0 && budget > 0; i--)
        {
            StairsBlock block = blocks.get(i);
            if (!level.isLoaded(block.pos()) || !level.getBlockState(block.pos()).is(block.state().getBlock())) {continue;} //not built, or replaced by something else
            level.destroyBlock(block.pos(), false, this.warden); //break particles & sound
            budget--;
        }
        return budget;
    }

    //builds the missing stairs blocks, from bottom to top. returns the remaining budget
    private int build(ServerLevel level, List<StairsBlock> blocks, int budget)
    {
        for (StairsBlock block : blocks)
        {
            if (budget <= 0) {break;}
            BlockPos pos = block.pos();
            if (!level.isLoaded(pos)) {continue;}
            BlockState current = level.getBlockState(pos);
            if (current.is(block.state().getBlock()) || !current.canBeReplaced() || this.isOccupied(level, pos)) {continue;} //already built, or something in the way (tried again next tick)
            level.setBlock(pos, block.state(), Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE); //exact template state : no neighbor shape update
            level.playSound(null, pos, block.state().getSoundType().getPlaceSound(), SoundSource.BLOCKS, 0.8F, 0.8F);
            VoluciteGlyphParticles.sendInward(level, Vec3.atCenterOf(pos), 1.0F, 2.5F, 2); //glyphs gathering into the rebuilt block
            budget--;
        }
        return budget;
    }

    private boolean isOccupied(ServerLevel level, BlockPos pos) {return !level.getEntitiesOfClass(LivingEntity.class, new AABB(pos), entity -> !this.warden.is(entity)).isEmpty();} //the warden & its parts are ignored

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
            List<StairsBlock> axisBlocks = this.stairsBlocks.computeIfAbsent(rotation.rotate(Direction.NORTH).getAxis(), axis -> new ArrayList<>());
            for (StructureTemplate.StructureBlockInfo info : template.get().palettes.getFirst().blocks())
            {
                if (info.state().isAir() || POS_BLACKLIST.contains(info.pos())) {continue;}
                axisBlocks.add(new StairsBlock(rotateAroundCenter(northOrigin.offset(info.pos()), rotation, centerX, centerZ), info.state().rotate(rotation)));
            }
        }
        this.stairsBlocks.values().forEach(blocks -> blocks.sort(Comparator.comparingInt(block -> block.pos().getY())));
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
