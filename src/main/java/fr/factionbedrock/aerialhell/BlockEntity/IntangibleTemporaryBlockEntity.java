package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
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

    public static void tick(Level world, BlockPos pos, BlockState state, IntangibleTemporaryBlockEntity blockEntity)
    {
        if (blockEntity.tickCount < 0) {blockEntity.tickCount = 0;}
        blockEntity.tickCount++;
        if (blockEntity.tickCount >= LIFETIME)
        {
            world.setBlock(pos, blockEntity.beforeState == null ? Blocks.AIR.defaultBlockState() : blockEntity.beforeState, 2);
        }
    }

    public void setBeforeState(@Nullable BlockState state) {this.beforeState = state;}
    @Nullable public BlockState getBeforeState() {return beforeState;}
    public void resetTickCount() {this.tickCount = 0;}
    public int getTickCount() {return tickCount;}

    @Override protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.saveAdditional(nbt, registryLookup);
        nbt.put("beforeState", NbtUtils.writeBlockState(beforeState == null ? Blocks.AIR.defaultBlockState() : beforeState));
        nbt.putInt("tickCount", tickCount);
    }

    @Override protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.loadAdditional(nbt, registryLookup);
        HolderLookup<Block> blockGetter = this.getLevel() != null ? this.getLevel().holderLookup(Registries.BLOCK) : BuiltInRegistries.BLOCK.asLookup();
        this.beforeState = NbtUtils.readBlockState(blockGetter, nbt.getCompound("beforeState"));
        this.tickCount = nbt.getInt("tickCount");
    }
}
