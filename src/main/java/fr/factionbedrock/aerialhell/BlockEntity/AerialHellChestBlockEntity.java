package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Block.DungeonCores.CoreProtectedBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AerialHellChestBlockEntity extends ChestBlockEntity implements WorldlyContainer
{
	private Block chest = Blocks.AIR;

	protected AerialHellChestBlockEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state) {super(blockEntity, pos, state);}

	public AerialHellChestBlockEntity(BlockPos pos, BlockState state) {this(AerialHellBlockEntities.CHEST.get(), pos, state);}

	public void setChest(Block chest)
	{
		this.chest = chest;
	}
		
	public Block getChest()
	{
		return chest;
	}
		
	public boolean hasChest()
	{
		return !chest.defaultBlockState().isAir();
	}

	private boolean isCoreProtected()
	{
		return this.getBlockState().getOptionalValue(CoreProtectedBlock.CORE_PROTECTED).orElse(false);
	}

	@Override public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
	{
		if (cap == ForgeCapabilities.ITEM_HANDLER && this.isCoreProtected())
		{
			return LazyOptional.empty(); //No inventory accessible (including for modded hoppers)
		}
		return super.getCapability(cap, side);
	}

	@Override public int[] getSlotsForFace(Direction side)
	{
		if (this.isCoreProtected())
		{
			return new int[0]; //No inventory accessible
		}
		int[] slots = new int[this.getContainerSize()];
		for (int i = 0; i < slots.length; i++)
		{
			slots[i] = i;
		}
		return slots;
	}

	@Override public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {return this.canPlaceItem(slot, stack);}

	@Override public boolean canPlaceItem(int slot, ItemStack itemStack)
	{
		return !this.isCoreProtected() && super.canPlaceItem(slot, itemStack);
	}

	@Override public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction)
	{
		return !this.isCoreProtected();
	}

	@Override public boolean canTakeItem(Container into, int slot, ItemStack itemStack)
	{
		return !this.isCoreProtected() && super.canTakeItem(into, slot, itemStack);
	}
}
