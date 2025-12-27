package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.rule.GameRules;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class StellarFarmBlock extends FarmlandBlock
{
    public StellarFarmBlock(AbstractBlock.Settings settings) {super(settings);}

    @Override public BlockState getPlacementState(ItemPlacementContext context)
    {
        return !this.getDefaultState().canPlaceAt(context.getWorld(), context.getBlockPos()) ? AerialHellBlocks.STELLAR_DIRT.getDefaultState() : super.getPlacementState(context);
    }

    @Override protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
        if (!state.canPlaceAt(world, pos)) {turnToStellarDirt(null, state, world, pos);}
    }

    @Override public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand)
    {
        int i = state.get(MOISTURE);
        if (!isNearWater(world, pos) && !world.hasRain(pos.up()))
        {
            if (i > 0) {world.setBlockState(pos, state.with(MOISTURE, Integer.valueOf(i - 1)), 2);}
            else if (!shouldMaintainStellarFarmland(world, pos)) {turnToStellarDirt((Entity)null, state, world, pos);}
        }
        else if (i < 7) {world.setBlockState(pos, state.with(MOISTURE, Integer.valueOf(7)), 2);}
    }

    @Override public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, double fallDistance)
    {
        if (world instanceof ServerWorld serverWorld && world.random.nextFloat() < fallDistance - 0.5F && entity instanceof LivingEntity && (entity instanceof PlayerEntity || serverWorld.getGameRules().getValue(GameRules.DO_MOB_GRIEFING)) && entity.getWidth() * entity.getWidth() * entity.getHeight() > 0.512F)
        {
            turnToStellarDirt(entity, state, world, pos);
        }
        entity.handleFallDamage(fallDistance, 1.0F, entity.getDamageSources().fall()); //warning : 1.20.2 - removed super call and added what Block.fallOn does
    }

    //copy of net.minecraft.block.FarmlandBlock.setToDirt, edited
    public static void turnToStellarDirt(@Nullable Entity entity, BlockState state, World world, BlockPos pos)
    {
        BlockState blockState = pushEntitiesUpBeforeBlockChange(state, AerialHellBlocks.STELLAR_DIRT.getDefaultState(), world, pos);
        world.setBlockState(pos, blockState);
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(entity, blockState));
    }

    //copy of net.minecraft.block.FarmlandBlock.hasCrop
    private static boolean shouldMaintainStellarFarmland(ServerWorld world, BlockPos pos)
    {
        return world.getBlockState(pos.up()).isIn(BlockTags.MAINTAINS_FARMLAND);
    }

    //copy of net.minecraft.block.FarmlandBlock.isWaterNearby
    private static boolean isNearWater(ServerWorld world, BlockPos pos)
    {
        Iterator var2 = BlockPos.iterate(pos.add(-4, 0, -4), pos.add(4, 1, 4)).iterator();

        BlockPos blockPos;
        do
        {
            if (!var2.hasNext()) {return false;}
            blockPos = (BlockPos)var2.next();
        }
        while(!world.getFluidState(blockPos).isIn(FluidTags.WATER));

        return true;
    }
}
