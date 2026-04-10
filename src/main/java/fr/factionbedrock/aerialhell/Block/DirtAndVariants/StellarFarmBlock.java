package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gamerules.GameRules;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class StellarFarmBlock extends FarmBlock
{
    public StellarFarmBlock(BlockBehaviour.Properties settings) {super(settings);}

    @Override public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? AerialHellBlocks.STELLAR_DIRT.defaultBlockState() : super.getStateForPlacement(context);
    }

    @Override protected void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random)
    {
        if (!state.canSurvive(world, pos)) {turnToStellarDirt(null, state, world, pos);}
    }

    @Override public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand)
    {
        int i = state.getValue(MOISTURE);
        if (!isNearWater(world, pos) && !world.isRainingAt(pos.above()))
        {
            if (i > 0) {world.setBlock(pos, state.setValue(MOISTURE, Integer.valueOf(i - 1)), 2);}
            else if (!shouldMaintainStellarFarmland(world, pos)) {turnToStellarDirt((Entity)null, state, world, pos);}
        }
        else if (i < 7) {world.setBlock(pos, state.setValue(MOISTURE, Integer.valueOf(7)), 2);}
    }

    @Override public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, double fallDistance)
    {
        if (world instanceof ServerLevel serverWorld && world.random.nextFloat() < fallDistance - 0.5F && entity instanceof LivingEntity && (entity instanceof Player || serverWorld.getGameRules().get(GameRules.MOB_GRIEFING)) && entity.getBbWidth() * entity.getBbWidth() * entity.getBbHeight() > 0.512F)
        {
            turnToStellarDirt(entity, state, world, pos);
        }
        entity.causeFallDamage(fallDistance, 1.0F, entity.damageSources().fall()); //warning : 1.20.2 - removed super call and added what Block.fallOn does
    }

    //copy of net.minecraft.block.FarmlandBlock.setToDirt, edited
    public static void turnToStellarDirt(@Nullable Entity entity, BlockState state, Level world, BlockPos pos)
    {
        BlockState blockState = pushEntitiesUp(state, AerialHellBlocks.STELLAR_DIRT.defaultBlockState(), world, pos);
        world.setBlockAndUpdate(pos, blockState);
        world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, blockState));
    }

    //copy of net.minecraft.block.FarmlandBlock.hasCrop
    private static boolean shouldMaintainStellarFarmland(ServerLevel world, BlockPos pos)
    {
        return world.getBlockState(pos.above()).is(BlockTags.MAINTAINS_FARMLAND);
    }

    //copy of net.minecraft.block.FarmlandBlock.isWaterNearby
    private static boolean isNearWater(ServerLevel world, BlockPos pos)
    {
        Iterator var2 = BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4)).iterator();

        BlockPos blockPos;
        do
        {
            if (!var2.hasNext()) {return false;}
            blockPos = (BlockPos)var2.next();
        }
        while(!world.getFluidState(blockPos).is(FluidTags.WATER));

        return true;
    }
}
