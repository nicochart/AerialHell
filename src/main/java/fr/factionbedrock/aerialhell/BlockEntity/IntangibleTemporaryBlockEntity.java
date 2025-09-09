package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import javax.annotation.Nullable;

public class IntangibleTemporaryBlockEntity extends BlockEntity
{
    private BlockState beforeState;
    private int tickCount;
    public static int LIFETIME = 180;

    public IntangibleTemporaryBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(AerialHellBlockEntities.INTANGIBLE_TEMPORARY_BLOCK.get(), pos, blockState);
        this.tickCount = 0;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, IntangibleTemporaryBlockEntity blockEntity)
    {
        if (blockEntity.tickCount < 0) {blockEntity.tickCount = 0;}
        blockEntity.tickCount++;
        if (blockEntity.tickCount >= LIFETIME)
        {
            level.setBlock(pos, blockEntity.beforeState == null ? Blocks.AIR.defaultBlockState() : blockEntity.beforeState, 2);
        }
    }

    public void setBeforeState(@Nullable BlockState state) {this.beforeState = state;}
    @Nullable public BlockState getBeforeState() {return beforeState;}
    public void resetTickCount() {this.tickCount = 0;}
    public int getTickCount() {return tickCount;}

    @Override protected void saveAdditional(ValueOutput valueOutput)
    {
        super.saveAdditional(valueOutput);
        valueOutput.store("beforeState", BlockState.CODEC, beforeState == null ? Blocks.AIR.defaultBlockState() : beforeState);
        valueOutput.putInt("tickCount", tickCount);
    }

    @Override protected void loadAdditional(ValueInput valueInput)
    {
        super.loadAdditional(valueInput);
        this.beforeState = valueInput.read("beforeState", BlockState.CODEC).orElse(Blocks.AIR.defaultBlockState());
        this.tickCount = valueInput.getInt("tickCount").get();
    }
}
