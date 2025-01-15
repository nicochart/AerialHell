package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.FreezerMenu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class FreezerScreen extends FurnaceLikeScreen<FreezerMenu>
{
	private static final ResourceLocation FREEZER_GUI_TEXTURES = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/container/freezer.png");
	private static final ResourceLocation LIT_PROGRESS_SPRITE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "container/freezer/freezing_progress");
	private static final ResourceLocation FREEZING_PROGRESS_SPRITE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "container/freezer/progress");

	public FreezerScreen(FreezerMenu container, Inventory inventory, Component name)
	{
		super(container, inventory, name, FREEZER_GUI_TEXTURES, LIT_PROGRESS_SPRITE, FREEZING_PROGRESS_SPRITE);
	}
}
