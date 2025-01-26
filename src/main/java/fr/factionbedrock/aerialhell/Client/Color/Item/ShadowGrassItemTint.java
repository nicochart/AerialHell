package fr.factionbedrock.aerialhell.Client.Color.Item;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Client.Util.ColorHandlerHelper;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ShadowGrassItemTint implements ItemTintSource
{
    public static final ShadowGrassItemTint INSTANCE = new ShadowGrassItemTint();
    public static final MapCodec<ShadowGrassItemTint> MAP_CODEC = MapCodec.unit(INSTANCE);

    private ShadowGrassItemTint() {}

    @Override public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {return ColorHandlerHelper.SHADOW_BLACK;}

    @Override public MapCodec<? extends ItemTintSource> type() {return null;}
}
