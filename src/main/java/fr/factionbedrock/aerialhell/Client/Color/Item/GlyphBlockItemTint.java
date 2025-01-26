package fr.factionbedrock.aerialhell.Client.Color.Item;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Client.Util.ColorHandlerHelper;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class GlyphBlockItemTint implements ItemTintSource
{
    public static final GlyphBlockItemTint INSTANCE = new GlyphBlockItemTint();
    public static final MapCodec<GlyphBlockItemTint> MAP_CODEC = MapCodec.unit(INSTANCE);

    private GlyphBlockItemTint() {}

    @Override public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity)
    {
        if (stack.getItem() == AerialHellItems.MUD_GLYPH_BLOCK.get()) {return ColorHandlerHelper.MUD_GLYPH_COLOR;}
        else if (stack.getItem() == AerialHellItems.LUNATIC_GLYPH_BLOCK.get()) {return ColorHandlerHelper.LUNATIC_GLYPH_COLOR;}
        else if (stack.getItem() == AerialHellItems.GOLDEN_NETHER_PRISON_GLYPH_BLOCK.get()) {return ColorHandlerHelper.GOLDEN_NETHER_PRISON_GLYPH_COLOR;}
        else if (stack.getItem() == AerialHellItems.VOLUCITE_GLYPH_BLOCK.get()) {return ColorHandlerHelper.VOLUCITE_GLYPH_COLOR;}
        else if (stack.getItem() == AerialHellItems.SHADOW_CATACOMBS_GLYPH_BLOCK.get()) {return ColorHandlerHelper.SHADOW_CATACOMBS_GLYPH_COLOR;}
        else {return ColorHandlerHelper.DEFAULT_COLOR.getRGB();}
    }

    @Override public MapCodec<? extends ItemTintSource> type() {return null;}
}
