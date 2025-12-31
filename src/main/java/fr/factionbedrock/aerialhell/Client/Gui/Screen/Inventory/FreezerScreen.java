package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.FreezerMenu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;

public class FreezerScreen extends FurnaceLikeScreen<FreezerMenu>
{
	public static final Identifier FREEZER_GUI_TEXTURES = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/container/freezer.png");
	public static final Identifier LIT_PROGRESS_SPRITE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "container/freezer/freezing_progress");
	public static final Identifier FREEZING_PROGRESS_SPRITE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "container/freezer/progress");

	public FreezerScreen(FreezerMenu container, Inventory inventory, Component name)
	{
		super(container, inventory, name, FREEZER_GUI_TEXTURES, LIT_PROGRESS_SPRITE, FREEZING_PROGRESS_SPRITE);
	}
}
