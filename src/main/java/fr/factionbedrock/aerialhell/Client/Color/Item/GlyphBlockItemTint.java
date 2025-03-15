package fr.factionbedrock.aerialhell.Client.Color.Item;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Client.Util.ColorHandlerHelper;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class GlyphBlockItemTint implements TintSource
{
    public static final GlyphBlockItemTint INSTANCE = new GlyphBlockItemTint();
    public static final MapCodec<GlyphBlockItemTint> MAP_CODEC = MapCodec.unit(INSTANCE);

    private GlyphBlockItemTint() {}

    @Override public int getTint(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user)
    {
        if (stack.getItem() == AerialHellItems.MUD_GLYPH_BLOCK) {return ColorHandlerHelper.MUD_GLYPH_COLOR;}
        else if (stack.getItem() == AerialHellItems.LUNATIC_GLYPH_BLOCK) {return ColorHandlerHelper.LUNATIC_GLYPH_COLOR;}
        else if (stack.getItem() == AerialHellItems.GOLDEN_NETHER_PRISON_GLYPH_BLOCK) {return ColorHandlerHelper.GOLDEN_NETHER_PRISON_GLYPH_COLOR;}
        else if (stack.getItem() == AerialHellItems.VOLUCITE_GLYPH_BLOCK) {return ColorHandlerHelper.VOLUCITE_GLYPH_COLOR;}
        else if (stack.getItem() == AerialHellItems.SHADOW_CATACOMBS_GLYPH_BLOCK) {return ColorHandlerHelper.SHADOW_CATACOMBS_GLYPH_COLOR;}
        else {return ColorHandlerHelper.DEFAULT_COLOR.getRGB();}
    }

    @Override public MapCodec<? extends TintSource> getCodec() {return MAP_CODEC;}
}
