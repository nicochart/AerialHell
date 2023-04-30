package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Container.StellarFurnaceMenu;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StellarFurnaceScreen extends AerialHellFurnaceScreen<StellarFurnaceMenu>
{
	private static final ResourceLocation STELLAR_FURNACE_GUI_TEXTURES = new ResourceLocation(AerialHell.MODID, "textures/gui/container/stellar_furnace.png");
	
	public StellarFurnaceScreen(StellarFurnaceMenu container, PlayerInventory inventory, Component name)
	{
		super(container, inventory, name, STELLAR_FURNACE_GUI_TEXTURES);
	}
}