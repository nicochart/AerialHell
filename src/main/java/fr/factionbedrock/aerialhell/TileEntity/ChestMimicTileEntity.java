package fr.factionbedrock.aerialhell.TileEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellTileEntityTypes;
import net.minecraft.tileentity.TileEntity;

public class ChestMimicTileEntity extends TileEntity
{
	public ChestMimicTileEntity()
	{
		super(AerialHellTileEntityTypes.CHEST_MIMIC.get());
	}
}