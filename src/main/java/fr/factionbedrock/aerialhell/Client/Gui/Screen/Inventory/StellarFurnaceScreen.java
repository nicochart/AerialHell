package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.StellarFurnaceMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class StellarFurnaceScreen extends FurnaceLikeScreen<StellarFurnaceMenu>
{
	private static final Identifier STELLAR_FURNACE_GUI_TEXTURES =  AerialHell.id("textures/gui/container/stellar_furnace.png");
	private static final Identifier LIT_PROGRESS_SPRITE = Identifier.withDefaultNamespace("container/furnace/lit_progress");
	private static final Identifier BURN_PROGRESS_SPRITE = Identifier.withDefaultNamespace("container/furnace/burn_progress");

	public StellarFurnaceScreen(StellarFurnaceMenu container, Inventory inventory, Component name)
	{
		super(container, inventory, name, STELLAR_FURNACE_GUI_TEXTURES, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE);
	}
}