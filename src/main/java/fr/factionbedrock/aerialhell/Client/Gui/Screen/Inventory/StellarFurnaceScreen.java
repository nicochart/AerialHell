package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.StellarFurnaceMenu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class StellarFurnaceScreen extends FurnaceLikeScreen<StellarFurnaceMenu>
{
	private static final ResourceLocation STELLAR_FURNACE_GUI_TEXTURES = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/container/stellar_furnace.png");
	private static final ResourceLocation LIT_PROGRESS_SPRITE = ResourceLocation.parse("container/furnace/lit_progress");
	private static final ResourceLocation BURN_PROGRESS_SPRITE = ResourceLocation.parse("container/furnace/burn_progress");

	public StellarFurnaceScreen(StellarFurnaceMenu container, Inventory inventory, Component name)
	{
		super(container, inventory, name, STELLAR_FURNACE_GUI_TEXTURES, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE);
	}
}