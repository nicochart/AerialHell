package fr.factionbedrock.aerialhell.Fluid;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellFluids;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

public abstract class LiquidOfGodsFluid extends FlowingFluid
{
    public LiquidOfGodsFluid() {}

    @Override public Fluid getFlowing() {return AerialHellFluids.LIQUID_OF_THE_GODS_FLOWING;}

    @Override public Fluid getSource() {return AerialHellFluids.LIQUID_OF_THE_GODS_STILL;}

    @Override protected boolean canConvertToSource(ServerLevel world) {return false;}

    @Override protected void beforeDestroyingBlock(LevelAccessor world, BlockPos pos, BlockState state)
    {
        BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropResources(state, world, pos, blockEntity);
    }

    @Override protected int getSlopeFindDistance(LevelReader world) {return 2;}

    @Override protected int getDropOff(LevelReader world) {return 3;}

    @Override public Item getBucket() {return AerialHellItems.IRON_LIQUID_OF_GODS_BUCKET;}

    @Override protected boolean canBeReplacedWith(FluidState state, BlockGetter world, BlockPos pos, Fluid fluid, Direction direction) {return direction == Direction.DOWN;}

    @Override public int getTickDelay(LevelReader world) {return 40;}

    @Override protected float getExplosionResistance() {return 100.0F;}

    @Override protected BlockState createLegacyBlock(FluidState state) {return (BlockState) AerialHellBlocks.LIQUID_OF_THE_GODS.defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));}

    @Override public boolean isSame(Fluid fluid) { return fluid == AerialHellFluids.LIQUID_OF_THE_GODS_FLOWING || fluid == AerialHellFluids.LIQUID_OF_THE_GODS_STILL;}

    public static class Flowing extends LiquidOfGodsFluid
    {
        public Flowing() {}

        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder)
        {
            super.createFluidStateDefinition(builder);
            builder.add(new Property[]{LEVEL});
        }

        public int getAmount(FluidState state) {return state.getValue(LEVEL);}

        public boolean isSource(FluidState state) {return false;}
    }

    public static class Still extends LiquidOfGodsFluid
    {
        public Still() {}

        public int getAmount(FluidState state) {return 8;}

        public boolean isSource(FluidState state) {return true;}
    }
}
