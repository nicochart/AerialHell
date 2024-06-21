package fr.factionbedrock.aerialhell.Registry.Misc;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.ChatFormatting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;

public class AerialHellRarities
{
	public static final Rarity FROZEN = create("aerial_hell_frozen", ChatFormatting.DARK_AQUA);
	public static final Rarity CORRUPTED = create("aerial_hell_corrupted", ChatFormatting.DARK_PURPLE);
	public static final Rarity VIBRANT = create("aerial_hell_vibrant", ChatFormatting.DARK_GREEN);
	public static final Rarity LEGENDARY = create("aerial_hell_legendary", ChatFormatting.GOLD);
	public static final Rarity MYTHICAL = create("aerial_hell_mythical", ChatFormatting.RED);

	public static Rarity create(String name, ChatFormatting chatFormatting)
	{
		return Rarity.create(name, new ResourceLocation(AerialHell.MODID, name), chatFormatting);
	}
}
