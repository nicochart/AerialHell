package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Container.FreezerContainer;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FreezerScreen extends AerialHellFurnaceScreen<FreezerContainer>
{
	private static final ResourceLocation FREEZER_GUI_TEXTURES = new ResourceLocation(AerialHell.MODID, "textures/gui/container/freezer.png");
	
	public FreezerScreen(FreezerContainer container, PlayerInventory inventory, ITextComponent name)
	{
		super(container, inventory, name, FREEZER_GUI_TEXTURES);
	}
}
