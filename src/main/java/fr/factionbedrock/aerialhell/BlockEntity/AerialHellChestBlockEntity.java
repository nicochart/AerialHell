package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AerialHellChestBlockEntity extends ChestBlockEntity
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

	@Override public boolean canPlaceItem(int slot, ItemStack itemStack)
	{
		return !BlockHelper.isCoreProtected(this.getBlockState()) && super.canPlaceItem(slot, itemStack);
	}

	@Override public boolean canTakeItem(Container into, int slot, ItemStack itemStack)
	{
		return !BlockHelper.isCoreProtected(this.getBlockState()) && super.canTakeItem(into, slot, itemStack);
	}
}
