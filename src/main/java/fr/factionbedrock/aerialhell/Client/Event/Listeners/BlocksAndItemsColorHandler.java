package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.Client.Util.CalculateTintContextInfo;
import fr.factionbedrock.aerialhell.Client.Util.ColorHandlerHelper;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;

import java.awt.*;

public class BlocksAndItemsColorHandler
{
    public static void handleBlockColors()
    {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tint) -> getColor(tint, world, pos),
                AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK,
                AerialHellBlocks.STELLAR_JUNGLE_TREE_SAPLING,
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
                AerialHellBlocks.POTTED_STELLAR_FERN
        );

        ColorProviderRegistry.BLOCK.register((state, world, pos, tint) -> getCustomColor(state, tint, world, pos),
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

        ColorProviderRegistry.BLOCK.register((state, world, pos, tint) -> getVegetationColor(state, tint, world, pos),
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

    private static int getColor(int tint, BlockRenderView world, BlockPos pos)
    {
        if (world != null && pos != null)
        {
            switch (tint)
            {
                case 0 : return BiomeColors.getGrassColor(world, pos);
                case 1 : return BiomeColors.getFoliageColor(world, pos);
                case 2 : return BiomeColors.getWaterColor(world, pos);
                default: return ColorHandlerHelper.DEFAULT_COLOR.getRGB();
            }
        }
        else {return ColorHandlerHelper.DEFAULT_COLOR.getRGB();}
    }

    private static int getVegetationColor(BlockState state, int tint, BlockRenderView world, BlockPos pos)
    {
        if (world != null && pos != null)
        {
            switch (tint)
            {
                case 0 :
                {
                    if (state.isOf(AerialHellBlocks.BLUISH_FERN) || state.isOf(AerialHellBlocks.TALL_BLUISH_FERN) || state.isOf(AerialHellBlocks.POLYCHROME_FERN) || state.isOf(AerialHellBlocks.TALL_POLYCHROME_FERN))
                    {
                        return ColorHandlerHelper.calculateTint(new CalculateTintContextInfo(pos), ColorHandlerHelper::getLightGrassColor, (info) -> isShadowBindEnabled() ? ColorHandlerHelper.getShadowGrassColor(info) : ColorHandlerHelper.vanillaGetColor(info.pos, BiomeColors.GRASS_COLOR));
                    }
                    else if (state.isOf(AerialHellBlocks.STELLAR_GRASS_BLOCK) || state.isOf(AerialHellBlocks.SHADOW_GRASS_BLOCK))
                    {
                        boolean shouldRenderBlack = (state.isOf(AerialHellBlocks.STELLAR_GRASS_BLOCK) && isShadowBindEnabled()) || (state.isOf(AerialHellBlocks.SHADOW_GRASS_BLOCK) && !isShadowBindEnabled());
                        return shouldRenderBlack ? ColorHandlerHelper.SHADOW_BLACK : ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos));
                    }
                    else if (state.isOf(AerialHellBlocks.STELLAR_GRASS) || state.isOf(AerialHellBlocks.SHADOW_GRASS))
                    {
                        boolean shouldRenderBlack = (state.isOf(AerialHellBlocks.STELLAR_GRASS) && isShadowBindEnabled()) || (state.isOf(AerialHellBlocks.SHADOW_GRASS) && !isShadowBindEnabled());
                        return shouldRenderBlack ? ColorHandlerHelper.SHADOW_BLACK : ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos));
                    }
                    else if (state.isOf(AerialHellBlocks.STELLAR_GRASS_BALL) || state.isOf(AerialHellBlocks.SHADOW_GRASS_BALL))
                    {
                        boolean shouldRenderBlack = (state.isOf(AerialHellBlocks.STELLAR_GRASS_BALL) && isShadowBindEnabled()) || (state.isOf(AerialHellBlocks.SHADOW_GRASS_BALL) && !isShadowBindEnabled());
                        return shouldRenderBlack ? ColorHandlerHelper.SHADOW_BLACK : ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos));
                    }
                    else if (state.isOf(AerialHellBlocks.BRAMBLES) || state.isOf(AerialHellBlocks.SHADOW_BRAMBLES))
                    {
                        boolean shouldRenderBlack = (state.isOf(AerialHellBlocks.BRAMBLES) && isShadowBindEnabled()) || (state.isOf(AerialHellBlocks.SHADOW_BRAMBLES) && !isShadowBindEnabled());
                        return shouldRenderBlack ? ColorHandlerHelper.SHADOW_BLACK : ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos));
                    }
                    else if (state.isOf(AerialHellBlocks.STELLAR_ROOTS) || state.isOf(AerialHellBlocks.STELLAR_ROOTS_PLANT) || state.isOf(AerialHellBlocks.BLOSSOMING_VINES) || state.isOf(AerialHellBlocks.BLOSSOMING_VINES_PLANT))
                    {
                        return ColorHandlerHelper.calculateTint(new CalculateTintContextInfo(pos), (info) -> ColorHandlerHelper.getLightColor(info, BiomeColors.GRASS_COLOR, ColorHandlerHelper.SHADOW_PURPLE), (info) -> ColorHandlerHelper.getShadowColor(info, BiomeColors.GRASS_COLOR, ColorHandlerHelper.SHADOW_PURPLE));
                    }
                    else
                    {
                        return ColorHandlerHelper.calculatePlantVegetationBlockTint(new CalculateTintContextInfo(pos));
                    }
                }
                case 1 :
                {
                    if (state.isOf(AerialHellBlocks.BLUISH_FERN) || state.isOf(AerialHellBlocks.TALL_BLUISH_FERN) || state.isOf(AerialHellBlocks.POLYCHROME_FERN) || state.isOf(AerialHellBlocks.TALL_POLYCHROME_FERN))
                    {
                        return ColorHandlerHelper.calculateTint(new CalculateTintContextInfo(pos), ColorHandlerHelper::getLightFoliageColor, (info) -> isShadowBindEnabled() ? ColorHandlerHelper.getShiftedOrNotGrassColor(info.pos) : ColorHandlerHelper.vanillaGetColor(info.pos, BiomeColors.FOLIAGE_COLOR));
                    }
                    else if (state.isOf(AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES) || state.isOf(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES))
                    {
                        boolean isShadow = state.isOf(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES);
                        boolean isShifted = isShadowBindEnabled();
                        boolean shouldRenderWhite = (isShifted && !isShadow) || (!isShifted && isShadow);
                        return shouldRenderWhite ? ColorHandlerHelper.WHITE : ColorHandlerHelper.calculateFoliageTint(new CalculateTintContextInfo(pos));
                    }
                    else {return BiomeColors.getFoliageColor(world, pos);}
                }
                case 2 : return BiomeColors.getWaterColor(world, pos);
                default: return ColorHandlerHelper.DEFAULT_COLOR.getRGB();
            }
        }
        else {return ColorHandlerHelper.DEFAULT_COLOR.getRGB();}
    }

    private static int getCustomColor(BlockState state, int tint, BlockRenderView world, BlockPos pos)
    {
        if (world != null && pos != null)
        {
            switch (tint)
            {
                case 0 :
                {

                    if (state.isIn(AerialHellTags.Blocks.SLIPPERY_SAND))
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
                }
                case 1 : return BiomeColors.getFoliageColor(world, pos);
                case 2 : return BiomeColors.getWaterColor(world, pos);
                default: return ColorHandlerHelper.DEFAULT_COLOR.getRGB();
            }
        }
        else {return ColorHandlerHelper.DEFAULT_COLOR.getRGB();}
    }

    private static int getCustomColor(ItemStack itemstack, int color)
    {
        if (itemstack.getItem() == AerialHellItems.MUD_GLYPH_BLOCK) {return ColorHandlerHelper.MUD_GLYPH_COLOR;}
        else if (itemstack.getItem() == AerialHellItems.LUNATIC_GLYPH_BLOCK) {return ColorHandlerHelper.LUNATIC_GLYPH_COLOR;}
        else if (itemstack.getItem() == AerialHellItems.GOLDEN_NETHER_PRISON_GLYPH_BLOCK) {return ColorHandlerHelper.GOLDEN_NETHER_PRISON_GLYPH_COLOR;}
        else if (itemstack.getItem() == AerialHellItems.VOLUCITE_GLYPH_BLOCK) {return ColorHandlerHelper.VOLUCITE_GLYPH_COLOR;}
        else if (itemstack.getItem() == AerialHellItems.SHADOW_CATACOMBS_GLYPH_BLOCK) {return ColorHandlerHelper.SHADOW_CATACOMBS_GLYPH_COLOR;}
        else {return ColorHandlerHelper.DEFAULT_COLOR.getRGB();}
    }

    public static boolean isShadowBindEnabled() {return LoadedConfigParams.ENABLE_SHADOW_BIND_TEXTURE_SHIFT && isCurrentPlayerInstanceShadowBind();}

    public static boolean isCurrentPlayerInstanceShadowBind() {return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT && MinecraftClient.getInstance().player != null && EntityHelper.isLivingEntityShadowBind(MinecraftClient.getInstance().player);}
}
