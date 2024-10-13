package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellBiomes;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Cursor3D;
import net.minecraft.core.Holder;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.biome.Biome;

import java.awt.*;
import java.util.function.Function;

public class ColorHandlerHelper
{
    public static final Color DEFAULT_COLOR = new Color(12, 35, 26);
    public static final int WHITE = new Color(255, 255, 255).getRGB();
    public static final int SHADOW_BLACK = new Color(19, 17, 19).getRGB();
    public static final int AERIAL_HELL_PLAINS_GRASS_COLOR = new Color(49, 140, 102).getRGB();
    public static final int AERIAL_TREE_FOREST_GRASS_COLOR = new Color(49, 140, 83).getRGB();
    public static final int LAPIS_ROBINIA_SAVANA_GRASS_COLOR = new Color(48, 105, 79).getRGB();
    public static final int SHADOW_PURPLE = new Color(106, 49, 140).getRGB();
    public static final int MUD_GLYPH_COLOR = new Color(144, 95, 1).getRGB();
    public static final int LUNATIC_GLYPH_COLOR = new Color(175, 236, 181).getRGB();
    public static final int GOLDEN_NETHER_PRISON_GLYPH_COLOR = new Color(193, 25, 25).getRGB();
    public static final int VOLUCITE_GLYPH_COLOR = new Color(48, 66, 179).getRGB();
    public static final int SHADOW_CATACOMBS_GLYPH_COLOR = new Color(153, 99, 248).getRGB();

    public static int calculateGrassTint(CalculateTintContextInfo contextInfo)
    {
        return calculateTint(contextInfo, (info) -> getLightGrassColor(info), (info) -> getShadowGrassColor(info));
    }

    //copy of net.minecraft.client.multiplayer.ClientLevel calculateBlockTint method, edited to have custom shadow / non shadow blend
    public static int calculateTint(CalculateTintContextInfo info, Function<CalculateTintContextInfo,Integer> getLightBiomeColor, Function<CalculateTintContextInfo,Integer> getShadowBiomeColor)
    {
        BlockPos pos = info.pos;
        ClientLevel level = Minecraft.getInstance().level;
        int biomeBlendRadius = Minecraft.getInstance().options.biomeBlendRadius().get();
        if (biomeBlendRadius == 0 || !needsBlend(pos, biomeBlendRadius))
        {
            Holder<Biome> biome = level.getBiome(pos);
            return biome.is(AerialHellTags.Biomes.IS_SHADOW) ? getShadowBiomeColor.apply(info) : getLightBiomeColor.apply(info);
        }
        else
        {
            int blend = (biomeBlendRadius * 2 + 1) * (biomeBlendRadius * 2 + 1);
            int r = 0, g = 0, b = 0;
            Cursor3D cursor3d = new Cursor3D(pos.getX() - biomeBlendRadius, pos.getY(), pos.getZ() - biomeBlendRadius, pos.getX() + biomeBlendRadius, pos.getY(), pos.getZ() + biomeBlendRadius);
            BlockPos.MutableBlockPos mutablepos = new BlockPos.MutableBlockPos();

            int color;
            while (cursor3d.advance())
            {
                mutablepos.set(cursor3d.nextX(), cursor3d.nextY(), cursor3d.nextZ());
                Holder<Biome> biome = level.getBiome(mutablepos);

                color = biome.is(AerialHellTags.Biomes.IS_SHADOW) ? getShadowBiomeColor.apply(new CalculateTintContextInfo(mutablepos, info.shiftedRender)) : getLightBiomeColor.apply(new CalculateTintContextInfo(mutablepos, info.shiftedRender));

                r += (color & 0xFF0000) >> 16; g += (color & 0xFF00) >> 8; b += color & 0xFF;
            }

            return (r / blend & 0xFF) << 16 | (g / blend & 0xFF) << 8 | b / blend & 0xFF;
        }
    }

    public static boolean needsBlend(BlockPos pos, int blendRadius)
    {
        ClientLevel level = Minecraft.getInstance().level;
        Holder<Biome> localBiome = level.getBiome(pos);
        Holder<Biome> southBiome = level.getBiome(pos.south(blendRadius));
        if (localBiome != southBiome) {return true;}
        Holder<Biome> northBiome = level.getBiome(pos.north(blendRadius));
        if (localBiome != northBiome) {return true;}
        Holder<Biome> eastBiome = level.getBiome(pos.east(blendRadius));
        if (localBiome != eastBiome) {return true;}
        Holder<Biome> westBiome = level.getBiome(pos.west(blendRadius));
        if (localBiome != westBiome) {return true;}
        return false;
    }

    public static int getShiftedOrNotGrassColor(BlockPos pos) {return getShiftedOrNotGrassColor(Minecraft.getInstance().level.getBiome(pos), pos);}
    public static int getShiftedOrNotGrassColor(Holder<Biome> biome, BlockPos pos)
    {
        boolean shifted = EntityHelper.isCurrentPlayerInstanceShadowBind();
        if (biome.is(AerialHellTags.Biomes.IS_SHADOW))
        {
            if (biome.is(AerialHellBiomes.SHADOW_PLAIN)) {return shifted ? AERIAL_TREE_FOREST_GRASS_COLOR : SHADOW_BLACK;}
            else if (biome.is(AerialHellBiomes.SHADOW_FOREST)) {return shifted ? LAPIS_ROBINIA_SAVANA_GRASS_COLOR : SHADOW_BLACK;}
            else {return SHADOW_BLACK;}
        }
        else
        {
            return shifted ? SHADOW_BLACK : vanillaGetColor(biome, pos, BiomeColors.GRASS_COLOR_RESOLVER);
        }
    }

    public static int getShadowGrassColor(CalculateTintContextInfo info) {return getShadowColor(info, BiomeColors.GRASS_COLOR_RESOLVER);}
    public static int getShadowFoliageColor(CalculateTintContextInfo info) {return getShadowColor(info, BiomeColors.FOLIAGE_COLOR_RESOLVER);}

    public static int getLightGrassColor(CalculateTintContextInfo info) {return getLightColor(info, BiomeColors.GRASS_COLOR_RESOLVER);}
    public static int getLightFoliageColor(CalculateTintContextInfo info) {return getLightColor(info, BiomeColors.FOLIAGE_COLOR_RESOLVER);}

    public static int getShadowColor(CalculateTintContextInfo info, ColorResolver colorResolver) {return getShadowColor(Minecraft.getInstance().level.getBiome(info.pos), info, colorResolver);}
    public static int getShadowColor(Holder<Biome> biome, CalculateTintContextInfo info, ColorResolver colorResolver)
    {
        int SHADOW_COLOR = colorResolver == BiomeColors.GRASS_COLOR_RESOLVER ? SHADOW_BLACK : SHADOW_PURPLE;
        if (biome.is(AerialHellBiomes.SHADOW_PLAIN)) {return info.shiftedRender ? AERIAL_TREE_FOREST_GRASS_COLOR : SHADOW_COLOR;}
        else if (biome.is(AerialHellBiomes.SHADOW_FOREST)) {return info.shiftedRender ? LAPIS_ROBINIA_SAVANA_GRASS_COLOR : SHADOW_COLOR;}
        else {return SHADOW_COLOR;}
    }

    public static int getLightColor(CalculateTintContextInfo info, ColorResolver colorResolver) {return getLightColor(Minecraft.getInstance().level.getBiome(info.pos), info, colorResolver);}
    public static int getLightColor(Holder<Biome> biome, CalculateTintContextInfo info, ColorResolver colorResolver)
    {
        int SHADOW_COLOR = colorResolver == BiomeColors.GRASS_COLOR_RESOLVER ? SHADOW_BLACK : SHADOW_PURPLE;
        return info.shiftedRender ? SHADOW_COLOR : vanillaGetColor(biome, info.pos, colorResolver);
    }

    public static int vanillaGetColor(BlockPos pos, ColorResolver colorResolver) {return vanillaGetColor(Minecraft.getInstance().level.getBiome(pos), pos, colorResolver);}
    public static int vanillaGetColor(Holder<Biome> biome, BlockPos pos, ColorResolver colorResolver) {return colorResolver.getColor(biome.value(), pos.getX(), pos.getZ());}
}
