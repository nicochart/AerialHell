package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class IntangibleTemporaryBlockEntity extends BlockEntity
{
    private BlockState beforeState;
    private int tickCount;

    public IntangibleTemporaryBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(AerialHellBlockEntities.INTANGIBLE_TEMPORARY_BLOCK.get(), pos, blockState);
        this.tickCount = 0;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, IntangibleTemporaryBlockEntity blockEntity)
    {
        if (blockEntity.tickCount < 0) {blockEntity.tickCount = 0;}
        blockEntity.tickCount++;
        if (blockEntity.tickCount >= 180)
        {
            level.setBlock(pos, blockEntity.beforeState == null ? Blocks.AIR.defaultBlockState() : blockEntity.beforeState, 2);
        }
    }

    public void setBeforeState(@Nullable BlockState state) {this.beforeState = state;}
    @Nullable public BlockState getBeforeState() {return beforeState;}

    @Override protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.saveAdditional(tag, registries);
        tag.put("beforeState", NbtUtils.writeBlockState(beforeState == null ? Blocks.AIR.defaultBlockState() : beforeState));
        tag.putInt("tickCount", tickCount);
    }

    @Override protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        System.out.println("INTANTIBLE LOAD ADDITIONAL"); //not done. where is the block entity after disconnect/reconnect ?
        super.loadAdditional(tag, registries);
        this.beforeState = NbtUtils.readBlockState(this.level.holderLookup(Registries.BLOCK), tag.getCompound("beforeState"));
        this.tickCount = tag.getInt("tickCount");
    }
}
