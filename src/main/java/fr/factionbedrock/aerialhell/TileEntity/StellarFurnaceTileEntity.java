package fr.factionbedrock.aerialhell.TileEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Container.StellarFurnaceContainer;
import fr.factionbedrock.aerialhell.Registry.AerialHellTileEntityTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class StellarFurnaceTileEntity extends AbstractFurnaceTileEntity
{
	public StellarFurnaceTileEntity()
	{
		super(AerialHellTileEntityTypes.STELLAR_FURNACE.get(), IRecipeType.SMELTING);
	}

	@Override
	protected ITextComponent getDefaultName()
	{
		return new TranslationTextComponent("container." + AerialHell.MODID + ".stellar_furnace");
	}
	
	@Override
	protected Container createMenu(int id, PlayerInventory player)
	{
		return new StellarFurnaceContainer(id, player, this, this.furnaceData);
	}
}
