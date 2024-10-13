package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellBiomes;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Cursor3D;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.awt.*;
import java.util.function.Function;

public class BlocksAndItemsColorHandler
{
    private static final Color DEFAULT_COLOR = new Color(12, 35, 26);
    private static final int WHITE = new Color(255, 255, 255).getRGB();
    private static final int SHADOW_BLACK = new Color(19, 17, 19).getRGB();
    private static final int AERIAL_HELL_PLAINS_GRASS_COLOR = new Color(49, 140, 102).getRGB();
    private static final int AERIAL_TREE_FOREST_GRASS_COLOR = new Color(49, 140, 83).getRGB();
    private static final int LAPIS_ROBINIA_SAVANA_GRASS_COLOR = new Color(48, 105, 79).getRGB();
    private static final int SHADOW_PURPLE = new Color(106, 49, 140).getRGB();
    private static final int MUD_GLYPH_COLOR = new Color(144, 95, 1).getRGB();
    private static final int LUNATIC_GLYPH_COLOR = new Color(175, 236, 181).getRGB();
    private static final int GOLDEN_NETHER_PRISON_GLYPH_COLOR = new Color(193, 25, 25).getRGB();
    private static final int VOLUCITE_GLYPH_COLOR = new Color(48, 66, 179).getRGB();
    private static final int SHADOW_CATACOMBS_GLYPH_COLOR = new Color(153, 99, 248).getRGB();

    public static void handleBlockColors(RegisterColorHandlersEvent.Block event)
    {
        event.getBlockColors().register((state, level, pos, tint) -> getColor(tint, level, pos),
                AerialHellBlocksAndItems.CHISELED_STELLAR_GRASS_BLOCK.get(),
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
                AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get(),
                AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get(),
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

        event.getBlockColors().register((state, level, pos, tint) -> getPlantColor(state, tint, level, pos),
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
                default: return DEFAULT_COLOR.getRGB();
            }
        }
        else {return DEFAULT_COLOR.getRGB();}
    }

    private static int getPlantColor(BlockState state, int tint, BlockAndTintGetter level, BlockPos pos)
    {
        if (level != null && pos != null)
        {
            switch (tint)
            {
                case 0 :
                {
                    if (state.is(AerialHellBlocksAndItems.BLUISH_FERN.get()) || state.is(AerialHellBlocksAndItems.TALL_BLUISH_FERN.get()) || state.is(AerialHellBlocksAndItems.POLYCHROME_FERN.get()) || state.is(AerialHellBlocksAndItems.TALL_POLYCHROME_FERN.get()))
                    {
                        return calculateTint(new CalculateTintContextInfo(pos), (info) -> getLightGrassColor(info), (info) -> EntityHelper.isCurrentPlayerInstanceShadowBind() ? getShadowGrassColor(info) : vanillaGetColor(info.pos, BiomeColors.GRASS_COLOR_RESOLVER));
                    }
                    else
                    {
                        return calculateTint(new CalculateTintContextInfo(pos), (info) -> getLightGrassColor(info), (info) -> getShadowGrassColor(info));
                    }
                }
                case 1 :
                {
                    if (state.is(AerialHellBlocksAndItems.BLUISH_FERN.get()) || state.is(AerialHellBlocksAndItems.TALL_BLUISH_FERN.get()) || state.is(AerialHellBlocksAndItems.POLYCHROME_FERN.get()) || state.is(AerialHellBlocksAndItems.TALL_POLYCHROME_FERN.get()))
                    {
                        return calculateTint(new CalculateTintContextInfo(pos), (info) -> getLightFoliageColor(info), (info) -> EntityHelper.isCurrentPlayerInstanceShadowBind() ? getShiftedOrNotGrassColor(info.pos) : vanillaGetColor(info.pos, BiomeColors.FOLIAGE_COLOR_RESOLVER));
                    }
                    else {return BiomeColors.getAverageFoliageColor(level, pos);}
                }
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
                    if (state.is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK) || state.is(AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK))
                    {
                        return calculateTint(new CalculateTintContextInfo(pos), (blockpos) -> getLightGrassColor(blockpos), (blockpos) -> getShadowGrassColor(blockpos));
                    }
                    else if (state.is(AerialHellTags.Blocks.SLIPPERY_SAND))
                    {
                        return EntityHelper.isCurrentPlayerInstanceShadowBind() ? WHITE : calculateTint(new CalculateTintContextInfo(pos), (blockpos) -> WHITE, (blockpos) -> SHADOW_PURPLE);
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
        event.getItemColors().register((stack, color) -> AERIAL_HELL_PLAINS_GRASS_COLOR,
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

    private static class CalculateTintContextInfo
    {
        public final BlockPos pos;
        public final boolean shiftedRender;

        public CalculateTintContextInfo(BlockPos pos) {this(pos, EntityHelper.isCurrentPlayerInstanceShadowBind());}
        public CalculateTintContextInfo(BlockPos pos, boolean shiftedRender) {this.pos = pos; this.shiftedRender = shiftedRender;}
    }

    protected static boolean needsBlend(BlockPos pos, int blendRadius)
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

    private static int getShiftedOrNotGrassColor(BlockPos pos) {return getShiftedOrNotGrassColor(Minecraft.getInstance().level.getBiome(pos), pos);}
    private static int getShiftedOrNotGrassColor(Holder<Biome> biome, BlockPos pos)
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

    private static int getShadowGrassColor(CalculateTintContextInfo info) {return getShadowColor(info, BiomeColors.GRASS_COLOR_RESOLVER);}
    private static int getShadowFoliageColor(CalculateTintContextInfo info) {return getShadowColor(info, BiomeColors.FOLIAGE_COLOR_RESOLVER);}

    private static int getLightGrassColor(CalculateTintContextInfo info) {return getLightColor(info, BiomeColors.GRASS_COLOR_RESOLVER);}
    private static int getLightFoliageColor(CalculateTintContextInfo info) {return getLightColor(info, BiomeColors.FOLIAGE_COLOR_RESOLVER);}

    private static int getShadowColor(CalculateTintContextInfo info, ColorResolver colorResolver) {return getShadowColor(Minecraft.getInstance().level.getBiome(info.pos), info, colorResolver);}
    private static int getShadowColor(Holder<Biome> biome, CalculateTintContextInfo info, ColorResolver colorResolver)
    {
        int SHADOW_COLOR = colorResolver == BiomeColors.GRASS_COLOR_RESOLVER ? SHADOW_BLACK : SHADOW_PURPLE;
        if (biome.is(AerialHellBiomes.SHADOW_PLAIN)) {return info.shiftedRender ? AERIAL_TREE_FOREST_GRASS_COLOR : SHADOW_COLOR;}
        else if (biome.is(AerialHellBiomes.SHADOW_FOREST)) {return info.shiftedRender ? LAPIS_ROBINIA_SAVANA_GRASS_COLOR : SHADOW_COLOR;}
        else {return SHADOW_COLOR;}
    }

    private static int getLightColor(CalculateTintContextInfo info, ColorResolver colorResolver) {return getLightColor(Minecraft.getInstance().level.getBiome(info.pos), info, colorResolver);}
    private static int getLightColor(Holder<Biome> biome, CalculateTintContextInfo info, ColorResolver colorResolver)
    {
        int SHADOW_COLOR = colorResolver == BiomeColors.GRASS_COLOR_RESOLVER ? SHADOW_BLACK : SHADOW_PURPLE;
        return info.shiftedRender ? SHADOW_COLOR : vanillaGetColor(biome, info.pos, colorResolver);
    }

    private static int vanillaGetColor(BlockPos pos, ColorResolver colorResolver) {return vanillaGetColor(Minecraft.getInstance().level.getBiome(pos), pos, colorResolver);}
    private static int vanillaGetColor(Holder<Biome> biome, BlockPos pos, ColorResolver colorResolver) {return colorResolver.getColor(biome.value(), pos.getX(), pos.getZ());}
}
