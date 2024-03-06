package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.OscillatorMenu;

import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OscillatorScreen extends AbstractFurnaceScreen<OscillatorMenu>
{
	private static final ResourceLocation OSCILLATOR_GUI_TEXTURES = new ResourceLocation(AerialHell.MODID, "textures/gui/container/oscillator.png");

	public OscillatorScreen(OscillatorMenu container, Inventory inventory, Component name)
	{
		super(container, new SmeltingRecipeBookComponent(), inventory, name, OSCILLATOR_GUI_TEXTURES);
	}
}