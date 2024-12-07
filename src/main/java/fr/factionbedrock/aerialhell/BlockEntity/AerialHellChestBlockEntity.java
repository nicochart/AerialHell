package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.math.BlockPos;

public class AerialHellChestBlockEntity extends ChestBlockEntity
{
	private Block chest = Blocks.AIR;

	protected AerialHellChestBlockEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state) {super(blockEntity, pos, state);}

	public AerialHellChestBlockEntity(BlockPos pos, BlockState state) {this(AerialHellBlockEntities.CHEST, pos, state);}

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
		return !chest.getDefaultState().isAir();
	}
}
