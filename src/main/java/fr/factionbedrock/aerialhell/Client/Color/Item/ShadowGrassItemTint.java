package fr.factionbedrock.aerialhell.Client.Color.Item;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Client.Util.ColorHandlerHelper;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ShadowGrassItemTint implements TintSource
{
    public static final ShadowGrassItemTint INSTANCE = new ShadowGrassItemTint();
    public static final MapCodec<ShadowGrassItemTint> MAP_CODEC = MapCodec.unit(INSTANCE);

    private ShadowGrassItemTint() {}

    @Override public int getTint(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user) {return ColorHandlerHelper.SHADOW_BLACK;}

    @Override public MapCodec<? extends TintSource> getCodec() {return MAP_CODEC;}
}
