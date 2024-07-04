package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.OscillatorMenu;

import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class OscillatorScreen extends AbstractFurnaceScreen<OscillatorMenu>
{
	private static final ResourceLocation OSCILLATOR_GUI_TEXTURES = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/container/oscillator.png");
	private static final ResourceLocation LIT_PROGRESS_SPRITE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "container/oscillator/oscillating_progress");
	private static final ResourceLocation OSCILLATING_PROGRESS_SPRITE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "container/oscillator/progress");

	public OscillatorScreen(OscillatorMenu container, Inventory inventory, Component name)
	{
		super(container, new SmeltingRecipeBookComponent(), inventory, name, OSCILLATOR_GUI_TEXTURES, LIT_PROGRESS_SPRITE, OSCILLATING_PROGRESS_SPRITE);
	}
}