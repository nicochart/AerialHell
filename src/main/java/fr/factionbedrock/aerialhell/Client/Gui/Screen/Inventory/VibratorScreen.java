package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Container.VibratorContainer;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VibratorScreen extends AerialHellFurnaceScreen<VibratorContainer>
{
	private static final ResourceLocation VIBRATOR_GUI_TEXTURES = new ResourceLocation(AerialHell.MODID, "textures/gui/container/vibrator.png");
	
	public VibratorScreen(VibratorContainer container, PlayerInventory inventory, ITextComponent name)
	{
		super(container, inventory, name, VIBRATOR_GUI_TEXTURES);
	}
}