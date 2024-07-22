package fr.factionbedrock.aerialhell.Registry.Misc;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

import java.util.function.UnaryOperator;

public class AerialHellRarities
{
	public static final EnumProxy<Rarity> FROZEN = create("aerial_hell_frozen", ChatFormatting.DARK_AQUA);
	public static final EnumProxy<Rarity> CORRUPTED = create("aerial_hell_corrupted", ChatFormatting.DARK_PURPLE);
	public static final EnumProxy<Rarity> VIBRANT = create("aerial_hell_vibrant", ChatFormatting.DARK_GREEN);
	public static final EnumProxy<Rarity> LEGENDARY = create("aerial_hell_legendary", ChatFormatting.GOLD);
	public static final EnumProxy<Rarity> MYTHICAL = create("aerial_hell_mythical", ChatFormatting.RED);

	public static EnumProxy<Rarity> create(String name, ChatFormatting chatFormatting)
	{
		return new EnumProxy<>(Rarity.class, -1, AerialHell.MODID+":"+name, (UnaryOperator<Style>) style -> style.withColor(chatFormatting));
	}
}
