package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellBiomes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.CuboidBlockIterator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.ColorResolver;

import java.awt.*;
import java.util.function.Function;

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

    public static int calculateGrassTint(CalculateTintContextInfo contextInfo)
    {
        return calculateTint(contextInfo, (info) -> getLightGrassColor(info), (info) -> getShadowGrassColor(info));
    }

    public static int calculatePlantVegetationBlockTint(CalculateTintContextInfo contextInfo)
    {
        return calculateTint(contextInfo, (info) -> getLightPlantVegetationColor(info), (info) -> getShadowPlantVegetationColor(info));
    }

    public static int calculateFoliageTint(CalculateTintContextInfo contextInfo)
    {
        return calculateTint(contextInfo, (info) -> getLightFoliageColor(info), (info) -> getShadowFoliageColor(info));
    }

    //copy of net.minecraft.client.multiplayer.ClientWorld calculateBlockTint method, edited to have custom shadow / non shadow blend
    public static int calculateTint(CalculateTintContextInfo info, Function<CalculateTintContextInfo,Integer> getLightBiomeColor, Function<CalculateTintContextInfo,Integer> getShadowBiomeColor)
    {
        BlockPos pos = info.pos;
        ClientWorld level = MinecraftClient.getInstance().world; if (level == null) {return 0;}
        int biomeBlendRadius = MinecraftClient.getInstance().options.getBiomeBlendRadius().getValue();
        if (biomeBlendRadius == 0 || !needsBlend(pos, biomeBlendRadius))
        {
            RegistryEntry<Biome> biome = level.getBiome(pos);
            return biome.isIn(AerialHellTags.Biomes.IS_SHADOW) ? getShadowBiomeColor.apply(info) : getLightBiomeColor.apply(info);
        }
        else
        {
            int blend = (biomeBlendRadius * 2 + 1) * (biomeBlendRadius * 2 + 1);
            int r = 0, g = 0, b = 0;
            CuboidBlockIterator cursor3d = new CuboidBlockIterator(pos.getX() - biomeBlendRadius, pos.getY(), pos.getZ() - biomeBlendRadius, pos.getX() + biomeBlendRadius, pos.getY(), pos.getZ() + biomeBlendRadius);
            BlockPos.Mutable mutablepos = new BlockPos.Mutable();

            int color;
            while (cursor3d.step())
            {
                mutablepos.set(cursor3d.getX(), cursor3d.getY(), cursor3d.getZ());
                RegistryEntry<Biome> biome = level.getBiome(mutablepos);

                color = biome.isIn(AerialHellTags.Biomes.IS_SHADOW) ? getShadowBiomeColor.apply(new CalculateTintContextInfo(mutablepos, info.shiftedRender)) : getLightBiomeColor.apply(new CalculateTintContextInfo(mutablepos, info.shiftedRender));

                r += (color & 0xFF0000) >> 16; g += (color & 0xFF00) >> 8; b += color & 0xFF;
            }

            return (r / blend & 0xFF) << 16 | (g / blend & 0xFF) << 8 | b / blend & 0xFF;
        }
    }

    public static boolean needsBlend(BlockPos pos, int blendRadius)
    {
        ClientWorld level = MinecraftClient.getInstance().world;
        if (level == null) {return false;}
        RegistryEntry<Biome> localBiome = level.getBiome(pos);
        RegistryEntry<Biome> southBiome = level.getBiome(pos.south(blendRadius));
        if (localBiome != southBiome) {return true;}
        RegistryEntry<Biome> northBiome = level.getBiome(pos.north(blendRadius));
        if (localBiome != northBiome) {return true;}
        RegistryEntry<Biome> eastBiome = level.getBiome(pos.east(blendRadius));
        if (localBiome != eastBiome) {return true;}
        RegistryEntry<Biome> westBiome = level.getBiome(pos.west(blendRadius));
        if (localBiome != westBiome) {return true;}
        return false;
    }

    public static int getShiftedOrNotGrassColor(BlockPos pos) {return MinecraftClient.getInstance().world == null ? 0 : getShiftedOrNotGrassColor(MinecraftClient.getInstance().world.getBiome(pos), pos);}
    public static int getShiftedOrNotGrassColor(RegistryEntry<Biome> biome, BlockPos pos)
    {
        boolean shifted = BlocksAndItemsColorHandler.isShadowBindEnabled();
        if (biome.isIn(AerialHellTags.Biomes.IS_SHADOW))
        {
            if (biome.matchesKey(AerialHellBiomes.SHADOW_PLAIN)) {return shifted ? AERIAL_TREE_FOREST_GRASS_COLOR : SHADOW_BLACK;}
            else if (biome.matchesKey(AerialHellBiomes.SHADOW_FOREST)) {return shifted ? LAPIS_ROBINIA_SAVANA_GRASS_COLOR : SHADOW_BLACK;}
            else {return SHADOW_BLACK;}
        }
        else
        {
            return shifted ? SHADOW_BLACK : vanillaGetColor(biome, pos, BiomeColors.GRASS_COLOR);
        }
    }

    public static int getShadowGrassColor(CalculateTintContextInfo info) {return getShadowColor(info, BiomeColors.GRASS_COLOR, SHADOW_BLACK);}
    public static int getShadowPlantVegetationColor(CalculateTintContextInfo info) {return getShadowColor(info, BiomeColors.GRASS_COLOR, SHADOW_DEEP_BLACK);}
    public static int getShadowFoliageColor(CalculateTintContextInfo info) {return getShadowColor(info, BiomeColors.FOLIAGE_COLOR, SHADOW_BLACK);}

    public static int getLightGrassColor(CalculateTintContextInfo info) {return getLightColor(info, BiomeColors.GRASS_COLOR, SHADOW_BLACK);}
    public static int getLightPlantVegetationColor(CalculateTintContextInfo info) {return getLightColor(info, BiomeColors.GRASS_COLOR, SHADOW_DEEP_BLACK);}
    public static int getLightFoliageColor(CalculateTintContextInfo info) {return getLightColor(info, BiomeColors.FOLIAGE_COLOR, SHADOW_BLACK);}

    public static int getShadowColor(CalculateTintContextInfo info, ColorResolver colorResolver, int shadowBlack) {return MinecraftClient.getInstance().world == null ? 0 : getShadowColor(MinecraftClient.getInstance().world.getBiome(info.pos), info, colorResolver, shadowBlack);}
    public static int getShadowColor(RegistryEntry<Biome> biome, CalculateTintContextInfo info, ColorResolver colorResolver, int shadowBlack)
    {
        int SHADOW_COLOR = colorResolver == BiomeColors.GRASS_COLOR ? shadowBlack : SHADOW_PURPLE;
        if (biome.matchesKey(AerialHellBiomes.SHADOW_PLAIN)) {return info.shiftedRender ? AERIAL_TREE_FOREST_GRASS_COLOR : SHADOW_COLOR;}
        else if (biome.matchesKey(AerialHellBiomes.SHADOW_FOREST)) {return info.shiftedRender ? LAPIS_ROBINIA_SAVANA_GRASS_COLOR : SHADOW_COLOR;}
        else {return SHADOW_COLOR;}
    }

    public static int getLightColor(CalculateTintContextInfo info, ColorResolver colorResolver, int shadowBlack) {return MinecraftClient.getInstance().world == null ? 0 : getLightColor(MinecraftClient.getInstance().world.getBiome(info.pos), info, colorResolver, shadowBlack);}
    public static int getLightColor(RegistryEntry<Biome> biome, CalculateTintContextInfo info, ColorResolver colorResolver, int shadowBlack)
    {
        int SHADOW_COLOR = colorResolver == BiomeColors.GRASS_COLOR ? shadowBlack : SHADOW_PURPLE;
        return info.shiftedRender ? SHADOW_COLOR : vanillaGetColor(biome, info.pos, colorResolver);
    }

    public static int vanillaGetColor(BlockPos pos, ColorResolver colorResolver) {return MinecraftClient.getInstance().world == null ? 0 : vanillaGetColor(MinecraftClient.getInstance().world.getBiome(pos), pos, colorResolver);}
    public static int vanillaGetColor(RegistryEntry<Biome> biome, BlockPos pos, ColorResolver colorResolver) {return colorResolver.getColor(biome.value(), pos.getX(), pos.getZ());}
}
