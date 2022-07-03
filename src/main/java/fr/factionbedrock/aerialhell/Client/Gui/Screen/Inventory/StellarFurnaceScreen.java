package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Container.StellarFurnaceContainer;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StellarFurnaceScreen extends AerialHellFurnaceScreen<StellarFurnaceContainer>
{
	private static final ResourceLocation STELLAR_FURNACE_GUI_TEXTURES = new ResourceLocation(AerialHell.MODID, "textures/gui/container/stellar_furnace.png");
	
	public StellarFurnaceScreen(StellarFurnaceContainer container, PlayerInventory inventory, ITextComponent name)
	{
		super(container, inventory, name, STELLAR_FURNACE_GUI_TEXTURES);
	}
}