package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.StellarFurnaceMenu;
import net.minecraft.client.gui.screen.ingame.AbstractFurnaceScreen;
import net.minecraft.client.gui.screen.recipebook.FurnaceRecipeBookScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class StellarFurnaceScreen extends AbstractFurnaceScreen<StellarFurnaceMenu>
{
	private static final Identifier STELLAR_FURNACE_GUI_TEXTURES =  AerialHell.id("textures/gui/container/stellar_furnace.png");
	private static final Identifier LIT_PROGRESS_SPRITE = Identifier.ofVanilla("container/furnace/lit_progress");
	private static final Identifier BURN_PROGRESS_SPRITE = Identifier.ofVanilla("container/furnace/burn_progress");

	public StellarFurnaceScreen(StellarFurnaceMenu container, PlayerInventory inventory, Text name)
	{
		super(container, new FurnaceRecipeBookScreen(), inventory, name, STELLAR_FURNACE_GUI_TEXTURES, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE);
	}
}