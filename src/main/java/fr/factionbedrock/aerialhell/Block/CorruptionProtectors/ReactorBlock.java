package fr.factionbedrock.aerialhell.Block.CorruptionProtectors;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.BlockEntity.ReactorBlockEntity;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.joml.Vector3d;

import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ReactorBlock extends BiomeShifterBlock
{
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    public static final MapCodec<ReactorBlock> CODEC = simpleCodec(ReactorBlock::new);

    private ReactorBlock(Properties prop) {this(prop, BiomeShifter.MAX_PROTECTION_DISTANCE, BiomeShifter.ShiftType.UNCORRUPT, null);}
    public ReactorBlock(Properties prop, int fieldSize, BiomeShifter.ShiftType shiftType, @Nullable Supplier<Block> shiftedOrBrokenVariant)
    {
        super(prop, fieldSize, shiftType, shiftedOrBrokenVariant);
        this.registerDefaultState(this.stateDefinition.any().setValue(ACTIVE, Boolean.FALSE));
    }

    @Override protected MapCodec<? extends ReactorBlock> codec() {return CODEC;}
    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(ACTIVE);}

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new ReactorBlockEntity(pos, state, this.fieldSize, this.shiftType, this.getShiftedOrBrokenVariant());}

    @Override protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult)
    {
        if (level.isClientSide) {return InteractionResult.SUCCESS;}
        else
        {
            this.openContainer(level, pos, player);
            return InteractionResult.CONSUME;
        }
    }

    protected void openContainer(Level level, BlockPos pos, Player player)
    {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof ReactorBlockEntity)
        {
            player.openMenu((MenuProvider)blockentity);
        }
    }

    @Override protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean isMoving)
    {
        Containers.updateNeighboursAfterDestroy(state, level, pos);
        super.affectNeighborsAfterRemoval(state, level, pos, isMoving);
    }

    //sent from server because client side do not have access to activeTimer update (always 0)
    public static void tickParticleAndSoundAnimation(ServerLevel level, BlockState state, BlockPos pos, RandomSource rand, BiomeShifter.ShiftType shiftType)
    {
        if (state.getValue(ACTIVE) && level.getBlockEntity(pos) instanceof ReactorBlockEntity reactorBlockEntity)
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
            sendReactorParticles(level, new Vector3d(basePos).add(offsetx, offsety, offsetz), particleNumber, 0.0, verticalParticleOffset, baseHorizontalParticleOffset, speed, shiftType);
            //face 2
            offsetx = -basePosOffset; offsetz = 0.0;
            sendReactorParticles(level, new Vector3d(basePos).add(offsetx, offsety, offsetz), particleNumber, 0.0, verticalParticleOffset, baseHorizontalParticleOffset, speed, shiftType);
            //face 3
            offsetx = 0.0; offsetz = basePosOffset;
            sendReactorParticles(level, new Vector3d(basePos).add(offsetx, offsety, offsetz), particleNumber, baseHorizontalParticleOffset, verticalParticleOffset, 0.0, speed, shiftType);
            //face 4
            offsetx = 0.0; offsetz = -basePosOffset;
            sendReactorParticles(level, new Vector3d(basePos).add(offsetx, offsety, offsetz), particleNumber, baseHorizontalParticleOffset, verticalParticleOffset, 0.0, speed, shiftType);

            //TODO add a active sound
            //if (rand.nextDouble() < 0.1)
            //{
            //    level.playLocalSound(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, this.getActiveSound(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
            //}
        }
    }

    public static void sendReactorParticles(ServerLevel level, Vector3d pos, int number, double xOffset, double yOffset, double zOffset, double speed, BiomeShifter.ShiftType type)
    {
        ParticleOptions particle = type == BiomeShifter.ShiftType.CORRUPT ? AerialHellParticleTypes.SHADOW_LIGHT.get() : AerialHellParticleTypes.OSCILLATOR.get();
        level.sendParticles(particle, pos.x, pos.y, pos.z, number, xOffset, yOffset, zOffset, speed);
    }

    @Nullable @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return level.isClientSide ? null : createTickerHelper(type, AerialHellBlockEntities.REACTOR.get(), ReactorBlockEntity::tick);
    }
}
