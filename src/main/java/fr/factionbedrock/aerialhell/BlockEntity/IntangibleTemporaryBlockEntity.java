package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class IntangibleTemporaryBlockEntity extends BlockEntity
{
    private BlockState beforeState;
    private int tickCount;
    public static int LIFETIME = 180;

    public IntangibleTemporaryBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(AerialHellBlockEntities.INTANGIBLE_TEMPORARY_BLOCK, pos, blockState);
        this.tickCount = 0;
    }

    public static void tick(World world, BlockPos pos, BlockState state, IntangibleTemporaryBlockEntity blockEntity)
    {
        if (blockEntity.tickCount < 0) {blockEntity.tickCount = 0;}
        blockEntity.tickCount++;
        if (blockEntity.tickCount >= LIFETIME)
        {
            world.setBlockState(pos, blockEntity.beforeState == null ? Blocks.AIR.getDefaultState() : blockEntity.beforeState, 2);
        }
    }

    public void setBeforeState(@Nullable BlockState state) {this.beforeState = state;}
    @Nullable public BlockState getBeforeState() {return beforeState;}
    public void resetTickCount() {this.tickCount = 0;}
    public int getTickCount() {return tickCount;}

    @Override protected void writeData(WriteView view)
    {
        super.writeData(view);
        view.put("beforeState", BlockState.CODEC, beforeState == null ? Blocks.AIR.getDefaultState() : beforeState);
        view.putInt("tickCount", tickCount);
    }

    @Override protected void readData(ReadView view)
    {
        super.readData(view);
        this.beforeState = view.read("beforeState", BlockState.CODEC).orElse(Blocks.AIR.getDefaultState());
        this.tickCount = view.getOptionalInt("tickCount").get();
    }
}
