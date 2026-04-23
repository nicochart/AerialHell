package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.Client.Util.CalculateTintContextInfo;
import fr.factionbedrock.aerialhell.Client.Util.ColorHandlerHelper;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import java.awt.*;
import java.util.List;

public class BlocksAndItemsColorHandler
{
    public static void handleBlockColors()
    {
        BlockColorRegistry.register(List.of(BlockTintSources.grassBlock(), BlockTintSources.foliage(), BlockTintSources.water()),
                AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK,
                AerialHellBlocks.STELLAR_JUNGLE_TREE_SAPLING,
                AerialHellBlocks.POTTED_STELLAR_FERN
        );

        BlockColorRegistry.register(List.of(getCustomColor()),
                AerialHellBlocks.SLIPPERY_SAND,
                AerialHellBlocks.SLIPPERY_SAND_STONE,
                AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS,
                AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS,
                AerialHellBlocks.CUT_SLIPPERY_SAND_STONE,
                AerialHellBlocks.SLIPPERY_SAND_STONE_SLAB,
                AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_SLAB,
                AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB,
                AerialHellBlocks.SLIPPERY_SAND_STONE_STAIRS,
                AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_STAIRS,
                AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS,
                AerialHellBlocks.SLIPPERY_SAND_STONE_WALL,
                AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_WALL,
                AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL,
                AerialHellBlocks.SLIPPERY_SAND_STONE_BUTTON,
                AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_BUTTON,
                AerialHellBlocks.SLIPPERY_SAND_STONE_PRESSURE_PLATE,
                AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE,
                AerialHellBlocks.STELLAR_PODZOL,
                AerialHellBlocks.MUD_GLYPH_BLOCK,
                AerialHellBlocks.LUNATIC_GLYPH_BLOCK,
                AerialHellBlocks.GOLDEN_NETHER_PRISON_GLYPH_BLOCK,
                AerialHellBlocks.VOLUCITE_GLYPH_BLOCK,
                AerialHellBlocks.SHADOW_CATACOMBS_GLYPH_BLOCK
        );

        BlockColorRegistry.register(List.of(getVegetationColorFirstLayer(), getVegetationColorSecondLayer()),
                AerialHellBlocks.MOSSY_STELLAR_STONE,
                AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE,
                AerialHellBlocks.MOSSY_STELLAR_STONE_WALL,
                AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_WALL,
                AerialHellBlocks.MOSSY_STELLAR_STONE_SLAB,
                AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_SLAB,
                AerialHellBlocks.MOSSY_STELLAR_STONE_STAIRS,
                AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_STAIRS,
                AerialHellBlocks.MOSSY_MUD_BRICKS,
                AerialHellBlocks.MOSSY_MUD_BRICKS_WALL,
                AerialHellBlocks.MOSSY_MUD_BRICKS_SLAB,
                AerialHellBlocks.MOSSY_MUD_BRICKS_STAIRS,
                AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS,
                AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL,
                AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB,
                AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS,
                AerialHellBlocks.STELLAR_GRASS_BLOCK,
                AerialHellBlocks.SHADOW_GRASS_BLOCK,
                AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES,
                AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES,
                AerialHellBlocks.SHADOW_GRASS,
                AerialHellBlocks.SHADOW_GRASS_BALL,
                AerialHellBlocks.STELLAR_GRASS,
                AerialHellBlocks.STELLAR_TALL_GRASS,
                AerialHellBlocks.STELLAR_GRASS_BALL,
                AerialHellBlocks.STELLAR_FERN,
                AerialHellBlocks.STELLAR_TALL_FERN,
                AerialHellBlocks.STELLAR_VERY_TALL_GRASS,
                AerialHellBlocks.CLIMBING_VINE,
                AerialHellBlocks.BLOSSOMING_VINES,
                AerialHellBlocks.BLOSSOMING_VINES_PLANT,
                AerialHellBlocks.STELLAR_ROOTS,
                AerialHellBlocks.STELLAR_ROOTS_PLANT,
                AerialHellBlocks.BLUISH_FERN,
                AerialHellBlocks.TALL_BLUISH_FERN,
                AerialHellBlocks.POLYCHROME_FERN,
                AerialHellBlocks.TALL_POLYCHROME_FERN,
                AerialHellBlocks.BRAMBLES,
                AerialHellBlocks.SHADOW_BRAMBLES,
                AerialHellBlocks.PURPLISH_STELLAR_GRASS,
                AerialHellBlocks.STELLAR_CLOVERS,
                AerialHellBlocks.GLOWING_STELLAR_GRASS,
                AerialHellBlocks.BLACK_ROSE,
                AerialHellBlocks.BLUE_FLOWER,
                AerialHellBlocks.BELLFLOWER
        );
    }

    public static BlockTintSource getVegetationColorFirstLayer() //tintindex 0
    {
        return new BlockTintSource()
        {
            public int color(BlockState state) {return ColorHandlerHelper.DEFAULT_COLOR.getRGB();}

            public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos)
            {
                if (state.is(AerialHellBlocks.BLUISH_FERN) || state.is(AerialHellBlocks.TALL_BLUISH_FERN) || state.is(AerialHellBlocks.POLYCHROME_FERN) || state.is(AerialHellBlocks.TALL_POLYCHROME_FERN))
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
                else if (state.is(AerialHellBlocks.MOSSY_STELLAR_STONE) || state.is(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE) || state.is(AerialHellBlocks.MOSSY_STELLAR_STONE_WALL) || state.is(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_WALL) || state.is(AerialHellBlocks.MOSSY_STELLAR_STONE_SLAB) || state.is(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_SLAB) || state.is(AerialHellBlocks.MOSSY_STELLAR_STONE_STAIRS) || state.is(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_STAIRS) || state.is(AerialHellBlocks.MOSSY_MUD_BRICKS) || state.is(AerialHellBlocks.MOSSY_MUD_BRICKS_WALL) || state.is(AerialHellBlocks.MOSSY_MUD_BRICKS_SLAB) || state.is(AerialHellBlocks.MOSSY_MUD_BRICKS_STAIRS) || state.is(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS) || state.is(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL) || state.is(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB) || state.is(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS))
                {
                    return ColorHandlerHelper.calculateTint(new CalculateTintContextInfo(pos), (info) -> ColorHandlerHelper.getLightColor(info, BiomeColors.GRASS_COLOR_RESOLVER, ColorHandlerHelper.SHADOW_PURPLE), (info) -> ColorHandlerHelper.getShadowColor(info, BiomeColors.GRASS_COLOR_RESOLVER, ColorHandlerHelper.SHADOW_PURPLE));
                }
                else
                {
                    return ColorHandlerHelper.calculatePlantVegetationBlockTint(new CalculateTintContextInfo(pos));
                }
            }
        };
    }

    public static BlockTintSource getVegetationColorSecondLayer() //tintindex 1
    {
        return new BlockTintSource()
        {
            public int color(BlockState state) {return ColorHandlerHelper.DEFAULT_COLOR.getRGB();}

            public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos)
            {
                if (state.is(AerialHellBlocks.BLUISH_FERN) || state.is(AerialHellBlocks.TALL_BLUISH_FERN) || state.is(AerialHellBlocks.POLYCHROME_FERN) || state.is(AerialHellBlocks.TALL_POLYCHROME_FERN))
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
        };
    }

    public static BlockTintSource getCustomColor()
    {
        return new BlockTintSource()
        {
            public int color(BlockState state)
            {
                return ColorHandlerHelper.DEFAULT_COLOR.getRGB();
            }

            public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos)
            {
                if (state.is(AerialHellTags.Blocks.SLIPPERY_SAND))
                {
                    return isShadowBindEnabled() ? ColorHandlerHelper.WHITE : ColorHandlerHelper.calculateTint(new CalculateTintContextInfo(pos), (blockpos) -> ColorHandlerHelper.WHITE, (blockpos) -> ColorHandlerHelper.SHADOW_PURPLE);
                }
                else if (state.getBlock() == AerialHellBlocks.STELLAR_PODZOL)
                {
                    Color baseColor = new Color(ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos)));
                    int r = baseColor.getRed(), g = baseColor.getGreen(), b = baseColor.getBlue();
                    return new Color((int) Math.min(255, r * 1.5), (int) (g / 1.5), b).getRGB();
                }
                else if (state.getBlock() == AerialHellBlocks.MUD_GLYPH_BLOCK) {return ColorHandlerHelper.MUD_GLYPH_COLOR;}
                else if (state.getBlock() == AerialHellBlocks.LUNATIC_GLYPH_BLOCK) {return ColorHandlerHelper.LUNATIC_GLYPH_COLOR;}
                else if (state.getBlock() == AerialHellBlocks.GOLDEN_NETHER_PRISON_GLYPH_BLOCK) {return ColorHandlerHelper.GOLDEN_NETHER_PRISON_GLYPH_COLOR;}
                else if (state.getBlock() == AerialHellBlocks.VOLUCITE_GLYPH_BLOCK) {return ColorHandlerHelper.VOLUCITE_GLYPH_COLOR;}
                else if (state.getBlock() == AerialHellBlocks.SHADOW_CATACOMBS_GLYPH_BLOCK) {return ColorHandlerHelper.SHADOW_CATACOMBS_GLYPH_COLOR;}

                return ColorHandlerHelper.DEFAULT_COLOR.getRGB();
            }
        };
    }

    public static boolean isShadowBindEnabled() {return LoadedConfigParams.ENABLE_SHADOW_BIND_TEXTURE_SHIFT && isCurrentPlayerInstanceShadowBind();}

    public static boolean isCurrentPlayerInstanceShadowBind() {return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT && Minecraft.getInstance().player != null && EntityHelper.isLivingEntityShadowBind(Minecraft.getInstance().player);}
}
