package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.OscillatorMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class OscillatorScreen extends FurnaceLikeScreen<OscillatorMenu>
{
	public static final Identifier OSCILLATOR_GUI_TEXTURES = AerialHell.id("textures/gui/container/oscillator.png");
	public static final Identifier LIT_PROGRESS_SPRITE =  AerialHell.id("container/oscillator/oscillating_progress");
	public static final Identifier OSCILLATING_PROGRESS_SPRITE =  AerialHell.id("container/oscillator/progress");

	public OscillatorScreen(OscillatorMenu container, Inventory inventory, Component name)
	{
		super(container, inventory, name, OSCILLATOR_GUI_TEXTURES, LIT_PROGRESS_SPRITE, OSCILLATING_PROGRESS_SPRITE);
	}
}