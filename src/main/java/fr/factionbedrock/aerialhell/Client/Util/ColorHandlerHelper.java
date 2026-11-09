package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellBiomes;
import java.awt.*;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.biome.Biome;

public class ColorHandlerHelper
{
    public static final Color DEFAULT_COLOR = new Color(12, 35, 26);
    public static final int WHITE = new Color(255, 255, 255).getRGB();
    public static final int SHADOW_BLACK = new Color(19, 17, 19).getRGB();
    public static final int SHADOW_DEEP_BLACK = new Color(7, 6, 7).getRGB();
    public static final int AERIAL_HELL_PLAINS_GRASS_COLOR = new Color(49, 140, 102).getRGB();
    public static final int AERIAL_TREE_FOREST_GRASS_COLOR = new Color(49, 140, 83).getRGB();
    public static final int LAPIS_ROBINIA_SAVANA_GRASS_COLOR = new Color(48, 105, 79).getRGB();
    public static final int SHADOW_PURPLE = new Color(106, 49, 140).getRGB();
    public static final int MUD_GLYPH_COLOR = new Color(144, 95, 1).getRGB();
    public static final int LUNATIC_GLYPH_COLOR = new Color(175, 236, 181).getRGB();
    public static final int GOLDEN_NETHER_PRISON_GLYPH_COLOR = new Color(193, 25, 25).getRGB();
    public static final int VOLUCITE_GLYPH_COLOR = new Color(48, 66, 179).getRGB();
    public static final int SHADOW_CATACOMBS_GLYPH_COLOR = new Color(153, 99, 248).getRGB();
    public static final int AERIAL_TREE_LEAVES_COLOR = new Color(30, 94, 108).getRGB();
    public static final int COPPER_PINE_LEAVES_COLOR = new Color(72, 149, 100).getRGB();
    public static final int GOLDEN_BEECH_LEAVES_COLOR = new Color(234, 202, 0).getRGB();
    public static final int LAPIS_ROBINIA_LEAVES_COLOR = new Color(35, 77, 156).getRGB();
    public static final int SHADOW_PINE_LEAVES_COLOR = new Color(36, 22, 42).getRGB();
    public static final int PURPLE_SHADOW_PINE_LEAVES_COLOR = new Color(91, 54, 110).getRGB();
    public static final int SHADOW_AERIAL_TREE_LEAVES_COLOR = new Color(101, 60, 91).getRGB();
    public static final int SHADOW_COPPER_PINE_LEAVES_COLOR = new Color(89, 62, 102).getRGB();
    public static final int HOLLOW_SHADOW_PINE_LEAVES_COLOR = new Color(57, 74, 31).getRGB();
    public static final int HOLLOW_PURPLE_SHADOW_PINE_LEAVES_COLOR = new Color(76, 60, 118).getRGB();
    public static final int CRYSTALLIZED_LEAVES_COLOR = new Color(88, 56, 123).getRGB();

    //color resolvers : each one gives the color of a single biome sample. the blend around the block (and its cache) is done by the level asking for the tint (vanilla, Sodium, Xaero's World Map...)
    //the shadow bind shifted render has its own resolvers, so that colors cached for one render are never reused for the other
    public static final ColorResolver GRASS_RESOLVER = shadowAwareResolver(BiomeColors.GRASS_COLOR_RESOLVER, SHADOW_BLACK, false);
    public static final ColorResolver GRASS_SHIFTED_RESOLVER = shadowAwareResolver(BiomeColors.GRASS_COLOR_RESOLVER, SHADOW_BLACK, true);
    public static final ColorResolver PLANT_VEGETATION_RESOLVER = shadowAwareResolver(BiomeColors.GRASS_COLOR_RESOLVER, SHADOW_DEEP_BLACK, false);
    public static final ColorResolver PLANT_VEGETATION_SHIFTED_RESOLVER = shadowAwareResolver(BiomeColors.GRASS_COLOR_RESOLVER, SHADOW_DEEP_BLACK, true);
    public static final ColorResolver PURPLE_SHADOW_VEGETATION_RESOLVER = shadowAwareResolver(BiomeColors.GRASS_COLOR_RESOLVER, SHADOW_PURPLE, false); //roots, vines & mossy blocks
    public static final ColorResolver PURPLE_SHADOW_VEGETATION_SHIFTED_RESOLVER = shadowAwareResolver(BiomeColors.GRASS_COLOR_RESOLVER, SHADOW_PURPLE, true);
    public static final ColorResolver FOLIAGE_RESOLVER = shadowAwareResolver(BiomeColors.FOLIAGE_COLOR_RESOLVER, SHADOW_PURPLE, false);
    public static final ColorResolver FOLIAGE_SHIFTED_RESOLVER = shadowAwareResolver(BiomeColors.FOLIAGE_COLOR_RESOLVER, SHADOW_PURPLE, true);
    //when not shifted, bluish & polychrome ferns use vanilla grass (first layer) and foliage (second layer) colors in every biome
    public static final ColorResolver COLORED_FERN_FIRST_LAYER_SHIFTED_RESOLVER = coloredFernShiftedResolver(SHADOW_BLACK);
    public static final ColorResolver COLORED_FERN_SECOND_LAYER_SHIFTED_RESOLVER = coloredFernShiftedResolver(SHADOW_PURPLE);
    //when shifted, slippery sand is white (no resolver needed)
    public static final ColorResolver SLIPPERY_SAND_RESOLVER = (biome, x, z) -> isShadowBiome(getBiomeHolder(biome)) ? SHADOW_PURPLE : WHITE;

    //custom resolvers must be registered, so that the client level creates their tint caches
    public static final List<ColorResolver> CUSTOM_RESOLVERS = List.of(
            GRASS_RESOLVER, GRASS_SHIFTED_RESOLVER,
            PLANT_VEGETATION_RESOLVER, PLANT_VEGETATION_SHIFTED_RESOLVER,
            PURPLE_SHADOW_VEGETATION_RESOLVER, PURPLE_SHADOW_VEGETATION_SHIFTED_RESOLVER,
            FOLIAGE_RESOLVER, FOLIAGE_SHIFTED_RESOLVER,
            COLORED_FERN_FIRST_LAYER_SHIFTED_RESOLVER, COLORED_FERN_SECOND_LAYER_SHIFTED_RESOLVER,
            SLIPPERY_SAND_RESOLVER
    );

    public static int getGrassTint(BlockAndTintGetter level, BlockPos pos) {return getTint(level, pos, GRASS_RESOLVER, GRASS_SHIFTED_RESOLVER);}
    public static int getPlantVegetationTint(BlockAndTintGetter level, BlockPos pos) {return getTint(level, pos, PLANT_VEGETATION_RESOLVER, PLANT_VEGETATION_SHIFTED_RESOLVER);}
    public static int getPurpleShadowVegetationTint(BlockAndTintGetter level, BlockPos pos) {return getTint(level, pos, PURPLE_SHADOW_VEGETATION_RESOLVER, PURPLE_SHADOW_VEGETATION_SHIFTED_RESOLVER);}
    public static int getFoliageTint(BlockAndTintGetter level, BlockPos pos) {return getTint(level, pos, FOLIAGE_RESOLVER, FOLIAGE_SHIFTED_RESOLVER);}

    public static int getTint(BlockAndTintGetter level, BlockPos pos, ColorResolver resolver, ColorResolver shiftedResolver)
    {
        return level.getBlockTint(pos, BlocksAndItemsColorHandler.isShadowBindEnabled() ? shiftedResolver : resolver);
    }

    //light biome : biome color, or shadow color when shifted
    //shadow biome : shadow color, or the color of its light variant when shifted (shadow plain & shadow forest)
    public static ColorResolver shadowAwareResolver(ColorResolver lightBiomeResolver, int shadowColor, boolean shifted)
    {
        return (biome, x, z) ->
        {
            Holder<Biome> biomeHolder = getBiomeHolder(biome);
            if (isShadowBiome(biomeHolder)) {return getShadowBiomeColor(biomeHolder, shadowColor, shifted);}
            else {return shifted ? shadowColor : lightBiomeResolver.getColor(biome, x, z);}
        };
    }

    public static ColorResolver coloredFernShiftedResolver(int lightBiomeColor)
    {
        return (biome, x, z) ->
        {
            Holder<Biome> biomeHolder = getBiomeHolder(biome);
            return isShadowBiome(biomeHolder) ? getShadowBiomeColor(biomeHolder, SHADOW_BLACK, true) : lightBiomeColor;
        };
    }

    public static int getShadowBiomeColor(Holder<Biome> biome, int shadowColor, boolean shifted)
    {
        if (shifted && biome.is(AerialHellBiomes.SHADOW_PLAIN)) {return AERIAL_TREE_FOREST_GRASS_COLOR;}
        else if (shifted && biome.is(AerialHellBiomes.SHADOW_FOREST)) {return LAPIS_ROBINIA_SAVANA_GRASS_COLOR;}
        else {return shadowColor;}
    }

    public static boolean isShadowBiome(Holder<Biome> biome) {return biome.is(AerialHellTags.Biomes.IS_SHADOW);}

    //resolvers only receive the biome value : its holder (needed for tags and keys) comes from the client biome registry
    public static Holder<Biome> getBiomeHolder(Biome biome)
    {
        ClientLevel level = Minecraft.getInstance().level;
        return level == null ? Holder.direct(biome) : level.registryAccess().lookupOrThrow(Registries.BIOME).wrapAsHolder(biome);
    }
}
