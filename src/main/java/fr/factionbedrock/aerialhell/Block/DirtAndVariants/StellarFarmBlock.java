package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.util.TriState;

import javax.annotation.Nullable;

public class StellarFarmBlock extends FarmBlock
{
    public StellarFarmBlock(Properties prop) {super(prop);}

    @Override public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? AerialHellBlocks.STELLAR_DIRT.get().defaultBlockState() : super.getStateForPlacement(context);
    }

    @Override public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
    {
        if (!state.canSurvive(level, pos)) {turnToStellarDirt(null, state, level, pos);}
    }

    @Override public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
    {
        int i = state.getValue(MOISTURE);
        if (!isNearWater(level, pos) && !level.isRainingAt(pos.above()))
        {
            if (i > 0) {level.setBlock(pos, state.setValue(MOISTURE, Integer.valueOf(i - 1)), 2);}
            else if (!shouldMaintainStellarFarmland(level, pos)) {turnToStellarDirt((Entity)null, state, level, pos);}
        }
        else if (i < 7) {level.setBlock(pos, state.setValue(MOISTURE, Integer.valueOf(7)), 2);}
    }

    @Override public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance)
    {
        if (!level.isClientSide && net.neoforged.neoforge.common.CommonHooks.onFarmlandTrample((ServerLevel) level, pos, AerialHellBlocks.STELLAR_DIRT.get().defaultBlockState(), fallDistance, entity))
        {
            turnToStellarDirt(entity, state, level, pos);
        }
        entity.causeFallDamage(fallDistance, 1.0F, entity.damageSources().fall()); //warning : 1.20.2 - removed super call and added what Block.fallOn does
    }

    //copy of net.minecraft.world.level.block.FarmBlock.turnToDirt, edited
    public static void turnToStellarDirt(@Nullable Entity entity, BlockState state, Level level, BlockPos pos)
    {
        BlockState blockstate = pushEntitiesUp(state, AerialHellBlocks.STELLAR_DIRT.get().defaultBlockState(), level, pos);
        level.setBlockAndUpdate(pos, blockstate);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, blockstate));
    }

    //copy of net.minecraft.world.level.block.FarmBlock.shouldMaintainFarmland
    private static boolean shouldMaintainStellarFarmland(BlockGetter blockGetter, BlockPos pos)
    {
        BlockState plant = blockGetter.getBlockState(pos.above());
        BlockState state = blockGetter.getBlockState(pos);
        TriState tristate = state.canSustainPlant(blockGetter, pos, Direction.UP, plant.getBlock().defaultBlockState());
        return tristate == TriState.TRUE || tristate == TriState.DEFAULT;
    }

    //copy of net.minecraft.world.level.block.FarmBlock.isNearWater
    private static boolean isNearWater(LevelReader level, BlockPos pos)
    {
        BlockState state = level.getBlockState(pos);
        for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4)))
        {
            if (state.canBeHydrated(level, pos, level.getFluidState(blockpos), blockpos)) {return true;}
        }
        return net.neoforged.neoforge.common.FarmlandWaterManager.hasBlockWaterTicket(level, pos);
    }
}
