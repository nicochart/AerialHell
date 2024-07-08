package fr.factionbedrock.aerialhell.Registry.Misc;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.ChatFormatting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;

public class AerialHellRarities
{
	//TODO : use Extensible Enums https://docs.neoforged.net/docs/advanced/extensibleenums/
	public static final Rarity FROZEN = Rarity.UNCOMMON;//create("aerial_hell_frozen", ChatFormatting.DARK_AQUA);
	public static final Rarity CORRUPTED = Rarity.UNCOMMON;//create("aerial_hell_corrupted", ChatFormatting.DARK_PURPLE);
	public static final Rarity VIBRANT = Rarity.UNCOMMON;//create("aerial_hell_vibrant", ChatFormatting.DARK_GREEN);
	public static final Rarity LEGENDARY = Rarity.RARE;//create("aerial_hell_legendary", ChatFormatting.GOLD);
	public static final Rarity MYTHICAL = Rarity.EPIC;//create("aerial_hell_mythical", ChatFormatting.RED);

	/*public static Rarity create(String name, ChatFormatting chatFormatting)
	{
		return Rarity.create(name, ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, name), chatFormatting);
	}*/
}
