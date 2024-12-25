package fr.factionbedrock.aerialhell.Registry.Misc;

import fr.factionbedrock.aerialhell.Mixin.RarityMixin;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;

import java.util.List;

public class AerialHellRarities
{
	public static final Rarity FROZEN = create(4, "frozen", Formatting.DARK_AQUA);
	public static final Rarity CORRUPTED = create(5, "corrupted", Formatting.DARK_PURPLE);
	public static final Rarity VIBRANT = create(6, "vibrant", Formatting.DARK_GREEN);
	public static final Rarity LEGENDARY = create(7, "legendary", Formatting.GOLD);
	public static final Rarity MYTHICAL = create(8, "mythical", Formatting.RED);

	public static List<Rarity> VALUES = List.of(FROZEN, CORRUPTED, VIBRANT, LEGENDARY, MYTHICAL);

	private static Rarity create(int index, String name, Formatting formatting)
	{
		return RarityMixin.invokeConstructor(name, index, index, "aerial_hell_"+name, formatting);
	}
}
