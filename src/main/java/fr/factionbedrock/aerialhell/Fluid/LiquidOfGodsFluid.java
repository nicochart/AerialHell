package fr.factionbedrock.aerialhell.Fluid;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellFluids;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public abstract class LiquidOfGodsFluid extends FlowableFluid
{
    public LiquidOfGodsFluid() {}

    @Override public Fluid getFlowing() {return AerialHellFluids.LIQUID_OF_THE_GODS_FLOWING;}

    @Override public Fluid getStill() {return AerialHellFluids.LIQUID_OF_THE_GODS_STILL;}

    @Override protected boolean isInfinite(World world) {return false;}

    @Override protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state)
    {
        BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropStacks(state, world, pos, blockEntity);
    }

    @Override protected int getMaxFlowDistance(WorldView world) {return 4;}

    @Override protected int getLevelDecreasePerBlock(WorldView world) {return 3;}

    @Override public Item getBucketItem() {return AerialHellItems.IRON_LIQUID_OF_GODS_BUCKET;}

    @Override protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {return direction == Direction.DOWN;}

    @Override public int getTickRate(WorldView world) {return 40;}

    @Override protected float getBlastResistance() {return 100.0F;}

    @Override protected BlockState toBlockState(FluidState state) {return (BlockState) AerialHellBlocks.LIQUID_OF_THE_GODS.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));}

    public static class Flowing extends LiquidOfGodsFluid
    {
        public Flowing() {}

        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder)
        {
            super.appendProperties(builder);
            builder.add(new Property[]{LEVEL});
        }

        public int getLevel(FluidState state) {return state.get(LEVEL);}

        public boolean isStill(FluidState state) {return false;}
    }

    public static class Still extends LiquidOfGodsFluid
    {
        public Still() {}

        public int getLevel(FluidState state) {return 4;}

        public boolean isStill(FluidState state) {return true;}
    }
}
