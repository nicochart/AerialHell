package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Color.Item.*;
import fr.factionbedrock.aerialhell.Client.Util.CalculateTintContextInfo;
import fr.factionbedrock.aerialhell.Client.Util.ColorHandlerHelper;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.awt.*;

public class BlocksAndItemsColorHandler
{
    public static void handleBlockColors(RegisterColorHandlersEvent.Block event)
    {
        event.register((state, level, pos, tint) -> getColor(tint, level, pos),
                AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK.get(),
                AerialHellBlocks.STELLAR_JUNGLE_TREE_SAPLING.get(),
                AerialHellBlocks.POTTED_STELLAR_FERN.get()
        );

        event.register((state, level, pos, tint) -> getCustomColor(state, tint, level, pos),
                AerialHellBlocks.SLIPPERY_SAND.get(),
                AerialHellBlocks.SLIPPERY_SAND_STONE.get(),
                AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS.get(),
                AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS.get(),
                AerialHellBlocks.CUT_SLIPPERY_SAND_STONE.get(),
                AerialHellBlocks.SLIPPERY_SAND_STONE_SLAB.get(),
                AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_SLAB.get(),
                AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB.get(),
                AerialHellBlocks.SLIPPERY_SAND_STONE_STAIRS.get(),
                AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_STAIRS.get(),
                AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS.get(),
                AerialHellBlocks.SLIPPERY_SAND_STONE_WALL.get(),
                AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_WALL.get(),
                AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL.get(),
                AerialHellBlocks.SLIPPERY_SAND_STONE_BUTTON.get(),
                AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_BUTTON.get(),
                AerialHellBlocks.SLIPPERY_SAND_STONE_PRESSURE_PLATE.get(),
                AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE.get(),
                AerialHellBlocks.STELLAR_PODZOL.get(),
                AerialHellBlocks.MUD_GLYPH_BLOCK.get(),
                AerialHellBlocks.LUNATIC_GLYPH_BLOCK.get(),
                AerialHellBlocks.GOLDEN_NETHER_PRISON_GLYPH_BLOCK.get(),
                AerialHellBlocks.VOLUCITE_GLYPH_BLOCK.get(),
                AerialHellBlocks.SHADOW_CATACOMBS_GLYPH_BLOCK.get()
        );

        event.register((state, level, pos, tint) -> getVegetationColor(state, tint, level, pos),
                AerialHellBlocks.MOSSY_STELLAR_STONE.get(),
                AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE.get(),
                AerialHellBlocks.MOSSY_STELLAR_STONE_WALL.get(),
                AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_WALL.get(),
                AerialHellBlocks.MOSSY_STELLAR_STONE_SLAB.get(),
                AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_SLAB.get(),
                AerialHellBlocks.MOSSY_STELLAR_STONE_STAIRS.get(),
                AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_STAIRS.get(),
                AerialHellBlocks.MOSSY_MUD_BRICKS.get(),
                AerialHellBlocks.MOSSY_MUD_BRICKS_WALL.get(),
                AerialHellBlocks.MOSSY_MUD_BRICKS_SLAB.get(),
                AerialHellBlocks.MOSSY_MUD_BRICKS_STAIRS.get(),
                AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS.get(),
                AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL.get(),
                AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB.get(),
                AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS.get(),
                AerialHellBlocks.STELLAR_GRASS_BLOCK.get(),
                AerialHellBlocks.SHADOW_GRASS_BLOCK.get(),
                AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES.get(),
                AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES.get(),
                AerialHellBlocks.SHADOW_GRASS.get(),
                AerialHellBlocks.SHADOW_GRASS_BALL.get(),
                AerialHellBlocks.STELLAR_GRASS.get(),
                AerialHellBlocks.STELLAR_TALL_GRASS.get(),
                AerialHellBlocks.STELLAR_GRASS_BALL.get(),
                AerialHellBlocks.STELLAR_FERN.get(),
                AerialHellBlocks.STELLAR_TALL_FERN.get(),
                AerialHellBlocks.STELLAR_VERY_TALL_GRASS.get(),
                AerialHellBlocks.CLIMBING_VINE.get(),
                AerialHellBlocks.BLOSSOMING_VINES.get(),
                AerialHellBlocks.BLOSSOMING_VINES_PLANT.get(),
                AerialHellBlocks.STELLAR_ROOTS.get(),
                AerialHellBlocks.STELLAR_ROOTS_PLANT.get(),
                AerialHellBlocks.BLUISH_FERN.get(),
                AerialHellBlocks.TALL_BLUISH_FERN.get(),
                AerialHellBlocks.POLYCHROME_FERN.get(),
                AerialHellBlocks.TALL_POLYCHROME_FERN.get(),
                AerialHellBlocks.BRAMBLES.get(),
                AerialHellBlocks.SHADOW_BRAMBLES.get(),
                AerialHellBlocks.PURPLISH_STELLAR_GRASS.get(),
                AerialHellBlocks.STELLAR_CLOVERS.get(),
                AerialHellBlocks.GLOWING_STELLAR_GRASS.get(),
                AerialHellBlocks.BLACK_ROSE.get(),
                AerialHellBlocks.BLUE_FLOWER.get(),
                AerialHellBlocks.BELLFLOWER.get()
        );
    }

    private static int getColor(int tint, BlockAndTintGetter level, BlockPos pos)
    {
        if (level != null && pos != null)
        {
            switch (tint)
            {
                case 0 : return BiomeColors.getAverageGrassColor(level, pos);
                case 1 : return BiomeColors.getAverageFoliageColor(level, pos);
                case 2 : return BiomeColors.getAverageWaterColor(level, pos);
                default: return ColorHandlerHelper.DEFAULT_COLOR.getRGB();
            }
        }
        else {return ColorHandlerHelper.DEFAULT_COLOR.getRGB();}
    }

    private static int getVegetationColor(BlockState state, int tint, BlockAndTintGetter level, BlockPos pos)
    {
        if (level != null && pos != null)
        {
            switch (tint)
            {
                case 0 :
                {
                    if (state.is(AerialHellBlocks.BLUISH_FERN.get()) || state.is(AerialHellBlocks.TALL_BLUISH_FERN.get()) || state.is(AerialHellBlocks.POLYCHROME_FERN.get()) || state.is(AerialHellBlocks.TALL_POLYCHROME_FERN.get()))
                    {
                        return ColorHandlerHelper.calculateTint(new CalculateTintContextInfo(pos), ColorHandlerHelper::getLightGrassColor, (info) -> isShadowBindEnabled() ? ColorHandlerHelper.getShadowGrassColor(info) : ColorHandlerHelper.vanillaGetColor(info.pos, BiomeColors.GRASS_COLOR_RESOLVER));
                    }
                    else if (state.is(AerialHellBlocks.STELLAR_GRASS_BLOCK) || state.is(AerialHellBlocks.SHADOW_GRASS_BLOCK))
                    {
                        boolean shouldRenderBlack = (state.is(AerialHellBlocks.STELLAR_GRASS_BLOCK) && isShadowBindEnabled()) || (state.is(AerialHellBlocks.SHADOW_GRASS_BLOCK) && !isShadowBindEnabled());
                        return shouldRenderBlack ? ColorHandlerHelper.SHADOW_BLACK : ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos));
                    }
                    else if (state.is(AerialHellBlocks.STELLAR_GRASS) || state.is(AerialHellBlocks.SHADOW_GRASS))
                    {
                        boolean shouldRenderBlack = (state.is(AerialHellBlocks.STELLAR_GRASS) && isShadowBindEnabled()) || (state.is(AerialHellBlocks.SHADOW_GRASS) && !isShadowBindEnabled());
                        return shouldRenderBlack ? ColorHandlerHelper.SHADOW_BLACK : ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos));
                    }
                    else if (state.is(AerialHellBlocks.STELLAR_GRASS_BALL) || state.is(AerialHellBlocks.SHADOW_GRASS_BALL))
                    {
                        boolean shouldRenderBlack = (state.is(AerialHellBlocks.STELLAR_GRASS_BALL) && isShadowBindEnabled()) || (state.is(AerialHellBlocks.SHADOW_GRASS_BALL) && !isShadowBindEnabled());
                        return shouldRenderBlack ? ColorHandlerHelper.SHADOW_BLACK : ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos));
                    }
                    else if (state.is(AerialHellBlocks.BRAMBLES) || state.is(AerialHellBlocks.SHADOW_BRAMBLES))
                    {
                        boolean shouldRenderBlack = (state.is(AerialHellBlocks.BRAMBLES) && isShadowBindEnabled()) || (state.is(AerialHellBlocks.SHADOW_BRAMBLES) && !isShadowBindEnabled());
                        return shouldRenderBlack ? ColorHandlerHelper.SHADOW_BLACK : ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos));
                    }
                    else if (state.is(AerialHellBlocks.STELLAR_ROOTS) || state.is(AerialHellBlocks.STELLAR_ROOTS_PLANT) || state.is(AerialHellBlocks.BLOSSOMING_VINES) || state.is(AerialHellBlocks.BLOSSOMING_VINES_PLANT))
                    {
                        return ColorHandlerHelper.calculateTint(new CalculateTintContextInfo(pos), (info) -> ColorHandlerHelper.getLightColor(info, BiomeColors.GRASS_COLOR_RESOLVER, ColorHandlerHelper.SHADOW_PURPLE), (info) -> ColorHandlerHelper.getShadowColor(info, BiomeColors.GRASS_COLOR_RESOLVER, ColorHandlerHelper.SHADOW_PURPLE));
                    }
                    else if (state.is(AerialHellBlocks.MOSSY_STELLAR_STONE.get()) || state.is(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE.get()) || state.is(AerialHellBlocks.MOSSY_STELLAR_STONE_WALL.get()) || state.is(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_WALL.get()) || state.is(AerialHellBlocks.MOSSY_STELLAR_STONE_SLAB.get()) || state.is(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_SLAB.get()) || state.is(AerialHellBlocks.MOSSY_STELLAR_STONE_STAIRS.get()) || state.is(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_STAIRS.get()) || state.is(AerialHellBlocks.MOSSY_MUD_BRICKS.get()) || state.is(AerialHellBlocks.MOSSY_MUD_BRICKS_WALL.get()) || state.is(AerialHellBlocks.MOSSY_MUD_BRICKS_SLAB.get()) || state.is(AerialHellBlocks.MOSSY_MUD_BRICKS_STAIRS.get()) || state.is(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS.get()) || state.is(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL.get()) || state.is(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB.get()) || state.is(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS.get()))
                    {
                        return ColorHandlerHelper.calculateTint(new CalculateTintContextInfo(pos), (info) -> ColorHandlerHelper.getLightColor(info, BiomeColors.GRASS_COLOR_RESOLVER, ColorHandlerHelper.SHADOW_PURPLE), (info) -> ColorHandlerHelper.getShadowColor(info, BiomeColors.GRASS_COLOR_RESOLVER, ColorHandlerHelper.SHADOW_PURPLE));
                    }
                    else
                    {
                        return ColorHandlerHelper.calculatePlantVegetationBlockTint(new CalculateTintContextInfo(pos));
                    }
                }
                case 1 :
                {
                    if (state.is(AerialHellBlocks.BLUISH_FERN.get()) || state.is(AerialHellBlocks.TALL_BLUISH_FERN.get()) || state.is(AerialHellBlocks.POLYCHROME_FERN.get()) || state.is(AerialHellBlocks.TALL_POLYCHROME_FERN.get()))
                    {
                        return ColorHandlerHelper.calculateTint(new CalculateTintContextInfo(pos), ColorHandlerHelper::getLightFoliageColor, (info) -> isShadowBindEnabled() ? ColorHandlerHelper.getShiftedOrNotGrassColor(info.pos) : ColorHandlerHelper.vanillaGetColor(info.pos, BiomeColors.FOLIAGE_COLOR_RESOLVER));
                    }
                    else if (state.is(AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES) || state.is(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES))
                    {
                        boolean isShadow = state.is(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES);
                        boolean isShifted = isShadowBindEnabled();
                        boolean shouldRenderWhite = (isShifted && !isShadow) || (!isShifted && isShadow);
                        return shouldRenderWhite ? ColorHandlerHelper.WHITE : ColorHandlerHelper.calculateFoliageTint(new CalculateTintContextInfo(pos));
                    }
                    else {return BiomeColors.getAverageFoliageColor(level, pos);}
                }
                case 2 : return BiomeColors.getAverageWaterColor(level, pos);
                default: return ColorHandlerHelper.DEFAULT_COLOR.getRGB();
            }
        }
        else {return ColorHandlerHelper.DEFAULT_COLOR.getRGB();}
    }

    private static int getCustomColor(BlockState state, int tint, BlockAndTintGetter level, BlockPos pos)
    {
        if (level != null && pos != null)
        {
            switch (tint)
            {
                case 0 :
                {

                    if (state.is(AerialHellTags.Blocks.SLIPPERY_SAND))
                    {
                        return isShadowBindEnabled() ? ColorHandlerHelper.WHITE : ColorHandlerHelper.calculateTint(new CalculateTintContextInfo(pos), (blockpos) -> ColorHandlerHelper.WHITE, (blockpos) -> ColorHandlerHelper.SHADOW_PURPLE);
                    }
                    else if (state.getBlock() == AerialHellBlocks.STELLAR_PODZOL.get())
                    {
                        Color baseColor = new Color(ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos)));
                        int r = baseColor.getRed(), g = baseColor.getGreen(), b = baseColor.getBlue();
                        return new Color((int) Math.min(255, r * 1.5), (int) (g / 1.5), b).getRGB();
                    }
                    else if (state.getBlock() == AerialHellBlocks.MUD_GLYPH_BLOCK.get()) {return ColorHandlerHelper.MUD_GLYPH_COLOR;}
                    else if (state.getBlock() == AerialHellBlocks.LUNATIC_GLYPH_BLOCK.get()) {return ColorHandlerHelper.LUNATIC_GLYPH_COLOR;}
                    else if (state.getBlock() == AerialHellBlocks.GOLDEN_NETHER_PRISON_GLYPH_BLOCK.get()) {return ColorHandlerHelper.GOLDEN_NETHER_PRISON_GLYPH_COLOR;}
                    else if (state.getBlock() == AerialHellBlocks.VOLUCITE_GLYPH_BLOCK.get()) {return ColorHandlerHelper.VOLUCITE_GLYPH_COLOR;}
                    else if (state.getBlock() == AerialHellBlocks.SHADOW_CATACOMBS_GLYPH_BLOCK.get()) {return ColorHandlerHelper.SHADOW_CATACOMBS_GLYPH_COLOR;}
                }
                case 1 : return BiomeColors.getAverageFoliageColor(level, pos);
                case 2 : return BiomeColors.getAverageWaterColor(level, pos);
                default: return ColorHandlerHelper.DEFAULT_COLOR.getRGB();
            }
        }
        else {return ColorHandlerHelper.DEFAULT_COLOR.getRGB();}
    }

    public static void handleItemColors(RegisterColorHandlersEvent.ItemTintSources event)
    {
        event.register(Identifier.fromNamespaceAndPath(AerialHell.MODID, "stellar_grass"), StellarGrassItemTint.MAP_CODEC);
        event.register(Identifier.fromNamespaceAndPath(AerialHell.MODID, "shadow_grass"), ShadowGrassItemTint.MAP_CODEC);
        event.register(Identifier.fromNamespaceAndPath(AerialHell.MODID, "glyph_block"), GlyphBlockItemTint.MAP_CODEC);
    }

    public static boolean isShadowBindEnabled() {return LoadedConfigParams.ENABLE_SHADOW_BIND_TEXTURE_SHIFT && isCurrentPlayerInstanceShadowBind();}

    public static boolean isCurrentPlayerInstanceShadowBind() {return FMLEnvironment.getDist() == Dist.CLIENT && Minecraft.getInstance().player != null && EntityHelper.isLivingEntityShadowBind(Minecraft.getInstance().player);}
}
