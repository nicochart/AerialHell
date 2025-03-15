package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.FreezerMenu;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FreezerScreen extends FurnaceLikeScreen<FreezerMenu>
{
	private static final Identifier FREEZER_GUI_TEXTURES =  AerialHell.id("textures/gui/container/freezer.png");
	private static final Identifier LIT_PROGRESS_SPRITE =  AerialHell.id("container/freezer/freezing_progress");
	private static final Identifier FREEZING_PROGRESS_SPRITE =  AerialHell.id("container/freezer/progress");

	public FreezerScreen(FreezerMenu container, PlayerInventory inventory, Text name)
	{
		super(container, inventory, name, FREEZER_GUI_TEXTURES, LIT_PROGRESS_SPRITE, FREEZING_PROGRESS_SPRITE);
	}
}
