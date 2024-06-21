package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.StellarFurnaceMenu;

import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class StellarFurnaceScreen extends AbstractFurnaceScreen<StellarFurnaceMenu>
{
	private static final ResourceLocation STELLAR_FURNACE_GUI_TEXTURES = new ResourceLocation(AerialHell.MODID, "textures/gui/container/stellar_furnace.png");
	private static final ResourceLocation LIT_PROGRESS_SPRITE = new ResourceLocation("container/furnace/lit_progress");
	private static final ResourceLocation BURN_PROGRESS_SPRITE = new ResourceLocation("container/furnace/burn_progress");

	public StellarFurnaceScreen(StellarFurnaceMenu container, Inventory inventory, Component name)
	{
		super(container, new SmeltingRecipeBookComponent(), inventory, name, STELLAR_FURNACE_GUI_TEXTURES, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE);
	}
}