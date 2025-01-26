package fr.factionbedrock.aerialhell.Client.Color.Item;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Client.Util.ColorHandlerHelper;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class StellarGrassItemTint implements ItemTintSource
{
    public static final StellarGrassItemTint INSTANCE = new StellarGrassItemTint();
    public static final MapCodec<StellarGrassItemTint> MAP_CODEC = MapCodec.unit(INSTANCE);

    private StellarGrassItemTint() {}

    @Override public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {return ColorHandlerHelper.AERIAL_HELL_PLAINS_GRASS_COLOR;}

    @Override public MapCodec<? extends ItemTintSource> type() {return null;}
}
