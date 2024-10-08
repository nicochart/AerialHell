package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Cursor3D;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.awt.*;

public class BlocksAndItemsColorHandler
{
    private static final Color DEFAULT_COLOR = new Color(12, 35, 26);
    private static final int WHITE = new Color(255, 255, 255).getRGB();
    private static final int SHADOW_SLIPPERY_SAND_COLOR = new Color(106, 49, 140).getRGB();
    private static final int MUD_GLYPH_COLOR = new Color(144, 95, 1).getRGB();
    private static final int LUNATIC_GLYPH_COLOR = new Color(175, 236, 181).getRGB();
    private static final int GOLDEN_NETHER_PRISON_GLYPH_COLOR = new Color(193, 25, 25).getRGB();
    private static final int VOLUCITE_GLYPH_COLOR = new Color(48, 66, 179).getRGB();
    private static final int SHADOW_CATACOMBS_GLYPH_COLOR = new Color(153, 99, 248).getRGB();

    public static void handleBlockColors(RegisterColorHandlersEvent.Block event)
    {
        event.getBlockColors().register((state, level, pos, tint) -> getColor(tint, level, pos),
                AerialHellBlocksAndItems.CHISELED_STELLAR_GRASS_BLOCK.get(),
                AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get(),
                AerialHellBlocksAndItems.STELLAR_GRASS.get(),
                AerialHellBlocksAndItems.STELLAR_TALL_GRASS.get(),
                AerialHellBlocksAndItems.STELLAR_GRASS_BALL.get(),
                AerialHellBlocksAndItems.STELLAR_FERN.get(),
                AerialHellBlocksAndItems.STELLAR_TALL_FERN.get(),
                AerialHellBlocksAndItems.STELLAR_VERY_TALL_GRASS.get(),
                AerialHellBlocksAndItems.BLUISH_FERN.get(),
                AerialHellBlocksAndItems.TALL_BLUISH_FERN.get(),
                AerialHellBlocksAndItems.POLYCHROME_FERN.get(),
                AerialHellBlocksAndItems.TALL_POLYCHROME_FERN.get(),
                AerialHellBlocksAndItems.BLOSSOMING_VINES.get(),
                AerialHellBlocksAndItems.BLOSSOMING_VINES_PLANT.get(),
                AerialHellBlocksAndItems.CLIMBING_VINE.get(),
                AerialHellBlocksAndItems.STELLAR_ROOTS.get(),
                AerialHellBlocksAndItems.STELLAR_ROOTS_PLANT.get(),
                AerialHellBlocksAndItems.BRAMBLES.get(),
                AerialHellBlocksAndItems.PURPLISH_STELLAR_GRASS.get(),
                AerialHellBlocksAndItems.STELLAR_CLOVERS.get(),
                AerialHellBlocksAndItems.GLOWING_STELLAR_GRASS.get(),
                AerialHellBlocksAndItems.BLACK_ROSE.get(),
                AerialHellBlocksAndItems.BLUE_FLOWER.get(),
                AerialHellBlocksAndItems.BELLFLOWER.get(),
                AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_SAPLING.get(),
                AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_LEAVES.get(),
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
                default: return DEFAULT_COLOR.getRGB();
            }
        }
        else {return DEFAULT_COLOR.getRGB();}
    }

    private static int getCustomColor(BlockState state, int tint, BlockAndTintGetter level, BlockPos pos)
    {
        if (level != null && pos != null)
        {
            switch (tint)
            {
                case 0 :
                {
                    Color baseColor = new Color(BiomeColors.getAverageGrassColor(level, pos));
                    int r = baseColor.getRed(), g = baseColor.getGreen(), b = baseColor.getBlue();
                    if (state.is(AerialHellTags.Blocks.SLIPPERY_SAND))
                    {
                        if (EntityHelper.isLivingEntityShadowBind(Minecraft.getInstance().player)) {return WHITE;}
                        return calculateSlipperySandTint(pos);
                    }
                    else if (state.getBlock() == AerialHellBlocksAndItems.STELLAR_PODZOL.get())
                    {
                        return new Color((int) Math.min(255, r * 1.5), (int) (g / 1.5), b).getRGB();
                    }
                    else if (state.getBlock() == AerialHellBlocksAndItems.MUD_GLYPH_BLOCK.get()) {return MUD_GLYPH_COLOR;}
                    else if (state.getBlock() == AerialHellBlocksAndItems.LUNATIC_GLYPH_BLOCK.get()) {return LUNATIC_GLYPH_COLOR;}
                    else if (state.getBlock() == AerialHellBlocksAndItems.GOLDEN_NETHER_PRISON_GLYPH_BLOCK.get()) {return GOLDEN_NETHER_PRISON_GLYPH_COLOR;}
                    else if (state.getBlock() == AerialHellBlocksAndItems.VOLUCITE_GLYPH_BLOCK.get()) {return VOLUCITE_GLYPH_COLOR;}
                    else if (state.getBlock() == AerialHellBlocksAndItems.SHADOW_CATACOMBS_GLYPH_BLOCK.get()) {return SHADOW_CATACOMBS_GLYPH_COLOR;}
                }
                case 1 : return BiomeColors.getAverageFoliageColor(level, pos);
                case 2 : return BiomeColors.getAverageWaterColor(level, pos);
                default: return DEFAULT_COLOR.getRGB();
            }
        }
        else {return DEFAULT_COLOR.getRGB();}
    }

    public static void handleItemColors(RegisterColorHandlersEvent.Item event)
    {
        event.getItemColors().register((stack, color) -> new Color(50, 140, 102).getRGB(),
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
        if (itemstack.getItem() == AerialHellBlocksAndItems.MUD_GLYPH_BLOCK_ITEM.get()) {return MUD_GLYPH_COLOR;}
        else if (itemstack.getItem() == AerialHellBlocksAndItems.LUNATIC_GLYPH_BLOCK_ITEM.get()) {return LUNATIC_GLYPH_COLOR;}
        else if (itemstack.getItem() == AerialHellBlocksAndItems.GOLDEN_NETHER_PRISON_GLYPH_BLOCK_ITEM.get()) {return GOLDEN_NETHER_PRISON_GLYPH_COLOR;}
        else if (itemstack.getItem() == AerialHellBlocksAndItems.VOLUCITE_GLYPH_BLOCK_ITEM.get()) {return VOLUCITE_GLYPH_COLOR;}
        else if (itemstack.getItem() == AerialHellBlocksAndItems.SHADOW_CATACOMBS_GLYPH_BLOCK_ITEM.get()) {return SHADOW_CATACOMBS_GLYPH_COLOR;}
        else {return DEFAULT_COLOR.getRGB();}
    }

    //copy of net.minecraft.client.multiplayer.ClientLevel calculateBlockTint method, edited to only get shadow / non shadow blend
    public static int calculateSlipperySandTint(BlockPos pos)
    {
        ClientLevel level = Minecraft.getInstance().level;
        int biomeBlendRadius = Minecraft.getInstance().options.biomeBlendRadius().get();
        if (biomeBlendRadius == 0)
        {
            return level.getBiome(pos).is(AerialHellTags.Biomes.IS_SHADOW) ? SHADOW_SLIPPERY_SAND_COLOR : WHITE;
        }
        else
        {
            int blend = (biomeBlendRadius * 2 + 1) * (biomeBlendRadius * 2 + 1);
            int r = 0;
            int g = 0;
            int b = 0;
            Cursor3D cursor3d = new Cursor3D(pos.getX() - biomeBlendRadius, pos.getY(), pos.getZ() - biomeBlendRadius, pos.getX() + biomeBlendRadius, pos.getY(), pos.getZ() + biomeBlendRadius);
            BlockPos.MutableBlockPos mutablepos = new BlockPos.MutableBlockPos();

            int color;
            while (cursor3d.advance())
            {
                mutablepos.set(cursor3d.nextX(), cursor3d.nextY(), cursor3d.nextZ());

                if (level.getBiome(mutablepos).is(AerialHellTags.Biomes.IS_SHADOW)) {color = SHADOW_SLIPPERY_SAND_COLOR;}
                else {color = new Color(255, 255, 255).getRGB();}

                r += (color & 0xFF0000) >> 16;
                g += (color & 0xFF00) >> 8;
                b += color & 0xFF;
            }

            return (r / blend & 0xFF) << 16 | (g / blend & 0xFF) << 8 | b / blend & 0xFF;
        }
    }
}
