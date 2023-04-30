package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
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
}
