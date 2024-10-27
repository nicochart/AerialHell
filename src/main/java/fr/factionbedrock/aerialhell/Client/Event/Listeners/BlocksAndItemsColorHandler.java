package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.Client.Util.CalculateTintContextInfo;
import fr.factionbedrock.aerialhell.Client.Util.ColorHandlerHelper;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
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
                AerialHellBlocksAndItems.CHISELED_STELLAR_GRASS_BLOCK.get(),
                AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_SAPLING.get(),
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
                AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS.get(),
                AerialHellBlocksAndItems.POTTED_STELLAR_FERN.get()
        );

        event.getBlockColors().register((state, level, pos, tint) -> getCustomColor(state, tint, level, pos),
                AerialHellBlocksAndItems.SLIPPERY_SAND.get(),
                AerialHellBlocksAndItems.SLIPPERY_SAND_STONE.get(),
                AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BRICKS.get(),
                AerialHellBlocksAndItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS.get(),
                AerialHellBlocksAndItems.CUT_SLIPPERY_SAND_STONE.get(),
                AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_SLAB.get(),
                AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BRICKS_SLAB.get(),
                AerialHellBlocksAndItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB.get(),
                AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_STAIRS.get(),
                AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BRICKS_STAIRS.get(),
                AerialHellBlocksAndItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS.get(),
                AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_WALL.get(),
                AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BRICKS_WALL.get(),
                AerialHellBlocksAndItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL.get(),
                AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BUTTON.get(),
                AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BRICKS_BUTTON.get(),
                AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_PRESSURE_PLATE.get(),
                AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE.get(),
                AerialHellBlocksAndItems.STELLAR_PODZOL.get(),
                AerialHellBlocksAndItems.MUD_GLYPH_BLOCK.get(),
                AerialHellBlocksAndItems.LUNATIC_GLYPH_BLOCK.get(),
                AerialHellBlocksAndItems.GOLDEN_NETHER_PRISON_GLYPH_BLOCK.get(),
                AerialHellBlocksAndItems.VOLUCITE_GLYPH_BLOCK.get(),
                AerialHellBlocksAndItems.SHADOW_CATACOMBS_GLYPH_BLOCK.get()
        );

        event.getBlockColors().register((state, level, pos, tint) -> getVegetationColor(state, tint, level, pos),
                AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get(),
                AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get(),
                AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_LEAVES.get(),
                AerialHellBlocksAndItems.SHADOW_STELLAR_JUNGLE_TREE_LEAVES.get(),
                AerialHellBlocksAndItems.SHADOW_GRASS.get(),
                AerialHellBlocksAndItems.SHADOW_GRASS_BALL.get(),
                AerialHellBlocksAndItems.STELLAR_GRASS.get(),
                AerialHellBlocksAndItems.STELLAR_TALL_GRASS.get(),
                AerialHellBlocksAndItems.STELLAR_GRASS_BALL.get(),
                AerialHellBlocksAndItems.STELLAR_FERN.get(),
                AerialHellBlocksAndItems.STELLAR_TALL_FERN.get(),
                AerialHellBlocksAndItems.STELLAR_VERY_TALL_GRASS.get(),
                AerialHellBlocksAndItems.CLIMBING_VINE.get(),
                AerialHellBlocksAndItems.BLOSSOMING_VINES.get(),
                AerialHellBlocksAndItems.BLOSSOMING_VINES_PLANT.get(),
                AerialHellBlocksAndItems.STELLAR_ROOTS.get(),
                AerialHellBlocksAndItems.STELLAR_ROOTS_PLANT.get(),
                AerialHellBlocksAndItems.BLUISH_FERN.get(),
                AerialHellBlocksAndItems.TALL_BLUISH_FERN.get(),
                AerialHellBlocksAndItems.POLYCHROME_FERN.get(),
                AerialHellBlocksAndItems.TALL_POLYCHROME_FERN.get(),
                AerialHellBlocksAndItems.BRAMBLES.get(),
                AerialHellBlocksAndItems.PURPLISH_STELLAR_GRASS.get(),
                AerialHellBlocksAndItems.STELLAR_CLOVERS.get(),
                AerialHellBlocksAndItems.GLOWING_STELLAR_GRASS.get(),
                AerialHellBlocksAndItems.BLACK_ROSE.get(),
                AerialHellBlocksAndItems.BLUE_FLOWER.get(),
                AerialHellBlocksAndItems.BELLFLOWER.get()
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
                    if (state.is(AerialHellBlocksAndItems.BLUISH_FERN.get()) || state.is(AerialHellBlocksAndItems.TALL_BLUISH_FERN.get()) || state.is(AerialHellBlocksAndItems.POLYCHROME_FERN.get()) || state.is(AerialHellBlocksAndItems.TALL_POLYCHROME_FERN.get()))
                    {
                        return ColorHandlerHelper.calculateTint(new CalculateTintContextInfo(pos), ColorHandlerHelper::getLightGrassColor, (info) -> isCurrentPlayerInstanceShadowBind() ? ColorHandlerHelper.getShadowGrassColor(info) : ColorHandlerHelper.vanillaGetColor(info.pos, BiomeColors.GRASS_COLOR_RESOLVER));
                    }
                    else if (state.is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK) || state.is(AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK))
                    {
                        boolean shouldRenderBlack = (state.is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK) && isCurrentPlayerInstanceShadowBind()) || (state.is(AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK) && !isCurrentPlayerInstanceShadowBind());
                        return shouldRenderBlack ? ColorHandlerHelper.SHADOW_BLACK : ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos));
                    }
                    else if (state.is(AerialHellBlocksAndItems.STELLAR_GRASS) || state.is(AerialHellBlocksAndItems.SHADOW_GRASS))
                    {
                        boolean shouldRenderBlack = (state.is(AerialHellBlocksAndItems.STELLAR_GRASS) && isCurrentPlayerInstanceShadowBind()) || (state.is(AerialHellBlocksAndItems.SHADOW_GRASS) && !isCurrentPlayerInstanceShadowBind());
                        return shouldRenderBlack ? ColorHandlerHelper.SHADOW_BLACK : ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos));
                    }
                    else if (state.is(AerialHellBlocksAndItems.STELLAR_GRASS_BALL) || state.is(AerialHellBlocksAndItems.SHADOW_GRASS_BALL))
                    {
                        boolean shouldRenderBlack = (state.is(AerialHellBlocksAndItems.STELLAR_GRASS_BALL) && isCurrentPlayerInstanceShadowBind()) || (state.is(AerialHellBlocksAndItems.SHADOW_GRASS_BALL) && !isCurrentPlayerInstanceShadowBind());
                        return shouldRenderBlack ? ColorHandlerHelper.SHADOW_BLACK : ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos));
                    }
                    else
                    {
                        return ColorHandlerHelper.calculatePlantVegetationBlockTint(new CalculateTintContextInfo(pos));
                    }
                }
                case 1 :
                {
                    if (state.is(AerialHellBlocksAndItems.BLUISH_FERN.get()) || state.is(AerialHellBlocksAndItems.TALL_BLUISH_FERN.get()) || state.is(AerialHellBlocksAndItems.POLYCHROME_FERN.get()) || state.is(AerialHellBlocksAndItems.TALL_POLYCHROME_FERN.get()))
                    {
                        return ColorHandlerHelper.calculateTint(new CalculateTintContextInfo(pos), ColorHandlerHelper::getLightFoliageColor, (info) -> isCurrentPlayerInstanceShadowBind() ? ColorHandlerHelper.getShiftedOrNotGrassColor(info.pos) : ColorHandlerHelper.vanillaGetColor(info.pos, BiomeColors.FOLIAGE_COLOR_RESOLVER));
                    }
                    else if (state.is(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_LEAVES) || state.is(AerialHellBlocksAndItems.SHADOW_STELLAR_JUNGLE_TREE_LEAVES))
                    {
                        boolean isShadow = state.is(AerialHellBlocksAndItems.SHADOW_STELLAR_JUNGLE_TREE_LEAVES);
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
                    else if (state.getBlock() == AerialHellBlocksAndItems.STELLAR_PODZOL.get())
                    {
                        Color baseColor = new Color(ColorHandlerHelper.calculateGrassTint(new CalculateTintContextInfo(pos)));
                        int r = baseColor.getRed(), g = baseColor.getGreen(), b = baseColor.getBlue();
                        return new Color((int) Math.min(255, r * 1.5), (int) (g / 1.5), b).getRGB();
                    }
                    else if (state.getBlock() == AerialHellBlocksAndItems.MUD_GLYPH_BLOCK.get()) {return ColorHandlerHelper.MUD_GLYPH_COLOR;}
                    else if (state.getBlock() == AerialHellBlocksAndItems.LUNATIC_GLYPH_BLOCK.get()) {return ColorHandlerHelper.LUNATIC_GLYPH_COLOR;}
                    else if (state.getBlock() == AerialHellBlocksAndItems.GOLDEN_NETHER_PRISON_GLYPH_BLOCK.get()) {return ColorHandlerHelper.GOLDEN_NETHER_PRISON_GLYPH_COLOR;}
                    else if (state.getBlock() == AerialHellBlocksAndItems.VOLUCITE_GLYPH_BLOCK.get()) {return ColorHandlerHelper.VOLUCITE_GLYPH_COLOR;}
                    else if (state.getBlock() == AerialHellBlocksAndItems.SHADOW_CATACOMBS_GLYPH_BLOCK.get()) {return ColorHandlerHelper.SHADOW_CATACOMBS_GLYPH_COLOR;}
                }
                case 1 : return BiomeColors.getAverageFoliageColor(level, pos);
                case 2 : return BiomeColors.getAverageWaterColor(level, pos);
                default: return ColorHandlerHelper.DEFAULT_COLOR.getRGB();
            }
        }
        else {return ColorHandlerHelper.DEFAULT_COLOR.getRGB();}
    }

    public static void handleItemColors(RegisterColorHandlersEvent.Item event)
    {
        event.getItemColors().register((stack, color) -> ColorHandlerHelper.AERIAL_HELL_PLAINS_GRASS_COLOR,
                AerialHellBlocksAndItems.CHISELED_STELLAR_GRASS_BLOCK_ITEM.get(),
                AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK_ITEM.get(),
                AerialHellBlocksAndItems.STELLAR_PODZOL_ITEM.get(),
                AerialHellBlocksAndItems.STELLAR_GRASS_ITEM.get(),
                AerialHellBlocksAndItems.STELLAR_TALL_GRASS_ITEM.get(),
                AerialHellBlocksAndItems.STELLAR_GRASS_BALL_ITEM.get(),
                AerialHellBlocksAndItems.STELLAR_FERN_ITEM.get(),
                AerialHellBlocksAndItems.STELLAR_TALL_FERN_ITEM.get(),
                AerialHellBlocksAndItems.STELLAR_VERY_TALL_GRASS_ITEM.get(),
                AerialHellBlocksAndItems.STELLAR_CLOVERS_ITEM.get(),
                AerialHellBlocksAndItems.STELLAR_ROOTS_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_WALL_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_WALL_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_SLAB_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_SLAB_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_STAIRS_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_STAIRS_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_WALL_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_SLAB_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_STAIRS_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS_ITEM.get()
        );

        event.getItemColors().register((stack, color) -> new Color(92, 171, 102).getRGB(),
                AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_LEAVES.get()
        );

        event.getItemColors().register((stack, color) -> ColorHandlerHelper.SHADOW_BLACK,
                AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get()
        );

        event.getItemColors().register((stack, color) -> getCustomColor(stack, color),
                AerialHellBlocksAndItems.MUD_GLYPH_BLOCK_ITEM.get(),
                AerialHellBlocksAndItems.LUNATIC_GLYPH_BLOCK_ITEM.get(),
                AerialHellBlocksAndItems.GOLDEN_NETHER_PRISON_GLYPH_BLOCK_ITEM.get(),
                AerialHellBlocksAndItems.VOLUCITE_GLYPH_BLOCK_ITEM.get(),
                AerialHellBlocksAndItems.SHADOW_CATACOMBS_GLYPH_BLOCK_ITEM.get()
        );
    }

    private static int getCustomColor(ItemStack itemstack, int color)
    {
        if (itemstack.getItem() == AerialHellBlocksAndItems.MUD_GLYPH_BLOCK_ITEM.get()) {return ColorHandlerHelper.MUD_GLYPH_COLOR;}
        else if (itemstack.getItem() == AerialHellBlocksAndItems.LUNATIC_GLYPH_BLOCK_ITEM.get()) {return ColorHandlerHelper.LUNATIC_GLYPH_COLOR;}
        else if (itemstack.getItem() == AerialHellBlocksAndItems.GOLDEN_NETHER_PRISON_GLYPH_BLOCK_ITEM.get()) {return ColorHandlerHelper.GOLDEN_NETHER_PRISON_GLYPH_COLOR;}
        else if (itemstack.getItem() == AerialHellBlocksAndItems.VOLUCITE_GLYPH_BLOCK_ITEM.get()) {return ColorHandlerHelper.VOLUCITE_GLYPH_COLOR;}
        else if (itemstack.getItem() == AerialHellBlocksAndItems.SHADOW_CATACOMBS_GLYPH_BLOCK_ITEM.get()) {return ColorHandlerHelper.SHADOW_CATACOMBS_GLYPH_COLOR;}
        else {return ColorHandlerHelper.DEFAULT_COLOR.getRGB();}
    }

    public static boolean isCurrentPlayerInstanceShadowBind() {return FMLEnvironment.dist == Dist.CLIENT && Minecraft.getInstance().player != null && EntityHelper.isLivingEntityShadowBind(Minecraft.getInstance().player);}
}
