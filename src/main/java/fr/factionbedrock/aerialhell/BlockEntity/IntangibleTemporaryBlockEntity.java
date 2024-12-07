package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class IntangibleTemporaryBlockEntity extends BlockEntity
{
    private BlockState beforeState;
    private int tickCount;
    public static int LIFETIME = 180;

    public IntangibleTemporaryBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(AerialHellBlockEntities.INTANGIBLE_TEMPORARY_BLOCK, pos, blockState);
        this.age = 0;
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
    public void resetTickCount() {this.age = 0;}
    public int getTickCount() {return tickCount;}

    @Override protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.saveAdditional(tag, registries);
        tag.put("beforeState", NbtUtils.writeBlockState(beforeState == null ? Blocks.AIR.getDefaultState() : beforeState));
        tag.putInt("tickCount", tickCount);
    }

    @Override protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.loadAdditional(tag, registries);
        HolderGetter<Block> blockGetter = this.getLevel() != null ? this.getLevel().holderLookup(Registries.BLOCK) : BuiltInRegistries.BLOCK.asLookup();
        this.beforeState = NbtUtils.readBlockState(blockGetter, tag.getCompound("beforeState"));
        this.age = tag.getInt("tickCount");
    }
}
