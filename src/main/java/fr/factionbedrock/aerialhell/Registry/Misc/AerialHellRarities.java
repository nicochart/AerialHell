package fr.factionbedrock.aerialhell.Registry.Misc;

import fr.factionbedrock.aerialhell.Mixin.RarityMixin;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;

public class AerialHellRarities
{
	public static final Rarity FROZEN = create(4, "frozen", ChatFormatting.DARK_AQUA);
	public static final Rarity CORRUPTED = create(5, "corrupted", ChatFormatting.DARK_PURPLE);
	public static final Rarity VIBRANT = create(6, "vibrant", ChatFormatting.DARK_GREEN);
	public static final Rarity LEGENDARY = create(7, "legendary", ChatFormatting.GOLD);
	public static final Rarity MYTHICAL = create(8, "mythical", ChatFormatting.RED);

	public static List<Rarity> VALUES = List.of(FROZEN, CORRUPTED, VIBRANT, LEGENDARY, MYTHICAL);

	private static Rarity create(int index, String name, ChatFormatting formatting)
	{
		return RarityMixin.invokeConstructor(name, index, index, "aerial_hell_"+name, formatting);
	}
}
