package fr.factionbedrock.aerialhell.TileEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ChestTileEntity;

public class AerialHellChestTileEntity extends ChestTileEntity
{
	private Block chest = Blocks.AIR;
	
	public AerialHellChestTileEntity()
	{
		super(AerialHellTileEntityTypes.CHEST.get());
	}

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
