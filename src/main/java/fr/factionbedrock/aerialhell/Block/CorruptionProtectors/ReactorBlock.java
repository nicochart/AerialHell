package fr.factionbedrock.aerialhell.Block.CorruptionProtectors;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.BlockEntity.ReactorBlockEntity;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.joml.Vector3d;

import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ReactorBlock extends BiomeShifterBlock
{
    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");
    public static final MapCodec<ReactorBlock> CODEC = createCodec(ReactorBlock::new);

    private ReactorBlock(AbstractBlock.Settings settings) {this(settings, BiomeShifter.MAX_PROTECTION_DISTANCE, BiomeShifter.ShiftType.UNCORRUPT, null);}
    public ReactorBlock(AbstractBlock.Settings settings, int fieldSize, BiomeShifter.ShiftType shiftType, @Nullable Supplier<Block> shiftedOrBrokenVariant)
    {
        super(settings, fieldSize, shiftType, shiftedOrBrokenVariant);
        this.setDefaultState(this.stateManager.getDefaultState().with(ACTIVE, Boolean.FALSE));
    }

    @Override protected MapCodec<? extends ReactorBlock> getCodec() {return CODEC;}
    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(ACTIVE);}

    @Nullable @Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {return new ReactorBlockEntity(pos, state, this.fieldSize, this.shiftType, this.getShiftedOrBrokenVariant());}

    @Override public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hitResult)
    {
        if (world.isClient) {return ActionResult.SUCCESS;}
        else
        {
            this.openScreen(world, pos, player);
            return ActionResult.CONSUME;
        }
    }

    protected void openScreen(World world, BlockPos pos, PlayerEntity player)
    {
        BlockEntity blockentity = world.getBlockEntity(pos);
        if (blockentity instanceof ReactorBlockEntity reactorBlockEntity)
        {
            player.openHandledScreen(reactorBlockEntity);
        }
    }

    @Override protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved)
    {
        ItemScatterer.onStateReplaced(state, newState, world, pos);
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    //sent from server because client side do not have access to activeTimer update (always 0)
    public static void tickParticleAndSoundAnimation(ServerWorld world, BlockState state, BlockPos pos, Random rand, BiomeShifter.ShiftType shiftType)
    {
        if (state.get(ACTIVE) && world.getBlockEntity(pos) instanceof ReactorBlockEntity reactorBlockEntity)
        {
            float percentage = ((float) reactorBlockEntity.getActiveTimer()) / ReactorBlockEntity.MAX_ACTIVE_TIMER;
            int particleNumber = (int) (percentage * 4);
            double pixel = 0.0625D;
            Vector3d basePos = new Vector3d(pos.getX() + 0.5, pos.getY() + 3.5 * pixel,pos.getZ() + 0.5);
            double offsetx, offsetz, offsety = rand.nextFloat() * percentage * 8 * pixel;
            double baseHorizontalParticleOffset = 0.09 * percentage, verticalParticleOffset = 0.1 * percentage;
            double basePosOffset = 0.52;
            double speed = 0.1D + 0.1D * percentage;
            //face 1
            offsetx = basePosOffset; offsetz = 0.0;
            sendReactorParticles(world, new Vector3d(basePos).add(offsetx, offsety, offsetz), particleNumber, 0.0, verticalParticleOffset, baseHorizontalParticleOffset, speed, shiftType);
            //face 2
            offsetx = -basePosOffset; offsetz = 0.0;
            sendReactorParticles(world, new Vector3d(basePos).add(offsetx, offsety, offsetz), particleNumber, 0.0, verticalParticleOffset, baseHorizontalParticleOffset, speed, shiftType);
            //face 3
            offsetx = 0.0; offsetz = basePosOffset;
            sendReactorParticles(world, new Vector3d(basePos).add(offsetx, offsety, offsetz), particleNumber, baseHorizontalParticleOffset, verticalParticleOffset, 0.0, speed, shiftType);
            //face 4
            offsetx = 0.0; offsetz = -basePosOffset;
            sendReactorParticles(world, new Vector3d(basePos).add(offsetx, offsety, offsetz), particleNumber, baseHorizontalParticleOffset, verticalParticleOffset, 0.0, speed, shiftType);

            //TODO add a active sound
            //if (rand.nextDouble() < 0.1)
            //{
            //    level.playLocalSound(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, this.getActiveSound(), SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            //}
        }
    }

    public static void sendReactorParticles(ServerWorld world, Vector3d pos, int number, double xOffset, double yOffset, double zOffset, double speed, BiomeShifter.ShiftType type)
    {
        ParticleEffect particle = type == BiomeShifter.ShiftType.CORRUPT ? AerialHellParticleTypes.SHADOW_LIGHT : AerialHellParticleTypes.OSCILLATOR;
        world.spawnParticles(particle, pos.x, pos.y, pos.z, number, xOffset, yOffset, zOffset, speed);
    }

    @Nullable @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClient ? null : validateTicker(type, AerialHellBlockEntities.REACTOR, ReactorBlockEntity::tick);
    }
}
