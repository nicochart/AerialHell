package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.FreezerMenu;

import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FreezerScreen extends AbstractFurnaceScreen<FreezerMenu>
{
	private static final ResourceLocation FREEZER_GUI_TEXTURES = new ResourceLocation(AerialHell.MODID, "textures/gui/container/freezer.png");
	private static final ResourceLocation LIT_PROGRESS_SPRITE = new ResourceLocation(AerialHell.MODID, "container/freezer/freezing_progress");
	private static final ResourceLocation FREEZING_PROGRESS_SPRITE = new ResourceLocation(AerialHell.MODID, "container/freezer/progress");

	public FreezerScreen(FreezerMenu container, Inventory inventory, Component name)
	{
		super(container, new SmeltingRecipeBookComponent(), inventory, name, FREEZER_GUI_TEXTURES, LIT_PROGRESS_SPRITE, FREEZING_PROGRESS_SPRITE);
	}
}
