package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Container.FreezerMenu;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FreezerScreen extends AerialHellFurnaceScreen<FreezerMenu>
{
	private static final ResourceLocation FREEZER_GUI_TEXTURES = new ResourceLocation(AerialHell.MODID, "textures/gui/container/freezer.png");
	
	public FreezerScreen(FreezerMenu container, PlayerInventory inventory, Component name)
	{
		super(container, inventory, name, FREEZER_GUI_TEXTURES);
	}
}
