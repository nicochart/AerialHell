package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Container.OscillatorContainer;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OscillatorScreen extends AerialHellFurnaceScreen<OscillatorContainer>
{
	private static final ResourceLocation OSCILLATOR_TOR_GUI_TEXTURES = new ResourceLocation(AerialHell.MODID, "textures/gui/container/oscillator.png");
	
	public OscillatorScreen(OscillatorContainer container, PlayerInventory inventory, ITextComponent name)
	{
		super(container, inventory, name, OSCILLATOR_TOR_GUI_TEXTURES);
	}
}