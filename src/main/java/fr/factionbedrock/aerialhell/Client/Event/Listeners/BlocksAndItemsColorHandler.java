package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.Client.Util.CalculateTintContextInfo;
import fr.factionbedrock.aerialhell.Client.Util.ColorHandlerHelper;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
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
        event.getBlockColors().register((state, level, pos, tint) -> getColor(tint, level, pos),
                AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK.get(),
                AerialHellBlocks.STELLAR_JUNGLE_TREE_SAPLING.get(),
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
                AerialHellBlocks.POTTED_STELLAR_FERN.get()
        );

        event.getBlockColors().register((state, level, pos, tint) -> getCustomColor(state, tint, level, pos),
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

        event.getBlockColors().register((state, level, pos, tint) -> getVegetationColor(state, tint, level, pos),
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
                        return ColorHandlerHelper.calculateTint(new CalculateTintContextInfo(pos), ColorHandlerHelper::getLightGrassColor, (info) -> isCurrentPlayerInstanceShadowBind() ? ColorHandlerHelper.getShadowGrassColor(info) : ColorHandlerHelper.vanillaGetColor(info.pos, BiomeColors.GRASS_COLOR_RESOLVER));
                    }
                    else if (state.is(AerialHellBlocks.STELLAR_GRASS_BLOCK) || state.is(AerialHellBlocks.SHADOW_GRASS_BLOCK))
                    {
                        boolean shouldRenderBlack = (state.is(AerialHellBlocks.STELLAR_GRASS_BLOCK) && isCurrentPlayerInstanceShadowBind()) || (state.is(AerialHellBlocks.SHADOW_GRASS_BLOCK) && !isCurrentPlayerInstanceShadowBind());
                        return shouldRenderBlack ? ColorHandlerHelper.SHADOW_BLACK : ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos));
                    }
                    else if (state.is(AerialHellBlocks.STELLAR_GRASS) || state.is(AerialHellBlocks.SHADOW_GRASS))
                    {
                        boolean shouldRenderBlack = (state.is(AerialHellBlocks.STELLAR_GRASS) && isCurrentPlayerInstanceShadowBind()) || (state.is(AerialHellBlocks.SHADOW_GRASS) && !isCurrentPlayerInstanceShadowBind());
                        return shouldRenderBlack ? ColorHandlerHelper.SHADOW_BLACK : ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos));
                    }
                    else if (state.is(AerialHellBlocks.STELLAR_GRASS_BALL) || state.is(AerialHellBlocks.SHADOW_GRASS_BALL))
                    {
                        boolean shouldRenderBlack = (state.is(AerialHellBlocks.STELLAR_GRASS_BALL) && isCurrentPlayerInstanceShadowBind()) || (state.is(AerialHellBlocks.SHADOW_GRASS_BALL) && !isCurrentPlayerInstanceShadowBind());
                        return shouldRenderBlack ? ColorHandlerHelper.SHADOW_BLACK : ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos));
                    }
                    else if (state.is(AerialHellBlocks.BRAMBLES) || state.is(AerialHellBlocks.SHADOW_BRAMBLES))
                    {
                        boolean shouldRenderBlack = (state.is(AerialHellBlocks.BRAMBLES) && isCurrentPlayerInstanceShadowBind()) || (state.is(AerialHellBlocks.SHADOW_BRAMBLES) && !isCurrentPlayerInstanceShadowBind());
                        return shouldRenderBlack ? ColorHandlerHelper.SHADOW_BLACK : ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos));
                    }
                    else if (state.is(AerialHellBlocks.STELLAR_ROOTS) || state.is(AerialHellBlocks.STELLAR_ROOTS_PLANT) || state.is(AerialHellBlocks.BLOSSOMING_VINES) || state.is(AerialHellBlocks.BLOSSOMING_VINES_PLANT))
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
                        return ColorHandlerHelper.calculateTint(new CalculateTintContextInfo(pos), ColorHandlerHelper::getLightFoliageColor, (info) -> isCurrentPlayerInstanceShadowBind() ? ColorHandlerHelper.getShiftedOrNotGrassColor(info.pos) : ColorHandlerHelper.vanillaGetColor(info.pos, BiomeColors.FOLIAGE_COLOR_RESOLVER));
                    }
                    else if (state.is(AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES) || state.is(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES))
                    {
                        boolean isShadow = state.is(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES);
                        boolean isShifted = isCurrentPlayerInstanceShadowBind();
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
                        return isCurrentPlayerInstanceShadowBind() ? ColorHandlerHelper.WHITE : ColorHandlerHelper.calculateTint(new CalculateTintContextInfo(pos), (blockpos) -> ColorHandlerHelper.WHITE, (blockpos) -> ColorHandlerHelper.SHADOW_PURPLE);
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
        /* TODO use item model json to define tints
        event.getItemColors().register((stack, color) -> ColorHandlerHelper.AERIAL_HELL_PLAINS_GRASS_COLOR,
                AerialHellBlocksAndItems.CHISELED_STELLAR_GRASS_BLOCK.get(),
                AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get(),
                AerialHellBlocksAndItems.STELLAR_PODZOL.get(),
                AerialHellBlocksAndItems.STELLAR_GRASS.get(),
                AerialHellBlocksAndItems.STELLAR_TALL_GRASS.get(),
                AerialHellBlocksAndItems.STELLAR_GRASS_BALL.get(),
                AerialHellBlocksAndItems.STELLAR_FERN.get(),
                AerialHellBlocksAndItems.STELLAR_TALL_FERN.get(),
                AerialHellBlocksAndItems.STELLAR_VERY_TALL_GRASS.get(),
                AerialHellBlocksAndItems.STELLAR_CLOVERS.get(),
                AerialHellBlocksAndItems.STELLAR_ROOTS.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_STONE.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_WALL.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_WALL.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_SLAB.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_SLAB.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_STAIRS.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_STAIRS.get(),
                AerialHellBlocksAndItems.MOSSY_MUD_BRICKS.get(),
                AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_WALL.get(),
                AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_SLAB.get(),
                AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_STAIRS.get(),
                AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS.get(),
                AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL.get(),
                AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB.get(),
                AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS.get()
        );

        event.getItemColors().register((stack, color) -> new Color(92, 171, 102).getRGB(),
                AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_LEAVES.get()
        );

        event.getItemColors().register((stack, color) -> ColorHandlerHelper.SHADOW_BLACK,
                AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get()
        );

        event.getItemColors().register((stack, color) -> getCustomColor(stack, color),
                AerialHellBlocksAndItems.MUD_GLYPH_BLOCK.get(),
                AerialHellBlocksAndItems.LUNATIC_GLYPH_BLOCK.get(),
                AerialHellBlocksAndItems.GOLDEN_NETHER_PRISON_GLYPH_BLOCK.get(),
                AerialHellBlocksAndItems.VOLUCITE_GLYPH_BLOCK.get(),
                AerialHellBlocksAndItems.SHADOW_CATACOMBS_GLYPH_BLOCK.get()
        );
        */
    }

    private static int getCustomColor(ItemStack itemstack, int color)
    {
        if (itemstack.getItem() == AerialHellItems.MUD_GLYPH_BLOCK.get()) {return ColorHandlerHelper.MUD_GLYPH_COLOR;}
        else if (itemstack.getItem() == AerialHellItems.LUNATIC_GLYPH_BLOCK.get()) {return ColorHandlerHelper.LUNATIC_GLYPH_COLOR;}
        else if (itemstack.getItem() == AerialHellItems.GOLDEN_NETHER_PRISON_GLYPH_BLOCK.get()) {return ColorHandlerHelper.GOLDEN_NETHER_PRISON_GLYPH_COLOR;}
        else if (itemstack.getItem() == AerialHellItems.VOLUCITE_GLYPH_BLOCK.get()) {return ColorHandlerHelper.VOLUCITE_GLYPH_COLOR;}
        else if (itemstack.getItem() == AerialHellItems.SHADOW_CATACOMBS_GLYPH_BLOCK.get()) {return ColorHandlerHelper.SHADOW_CATACOMBS_GLYPH_COLOR;}
        else {return ColorHandlerHelper.DEFAULT_COLOR.getRGB();}
    }

    public static boolean isCurrentPlayerInstanceShadowBind() {return FMLEnvironment.dist == Dist.CLIENT && Minecraft.getInstance().player != null && EntityHelper.isLivingEntityShadowBind(Minecraft.getInstance().player);}
}
