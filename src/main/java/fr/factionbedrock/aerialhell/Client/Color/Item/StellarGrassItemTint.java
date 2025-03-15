package fr.factionbedrock.aerialhell.Client.Color.Item;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Client.Util.ColorHandlerHelper;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class StellarGrassItemTint implements TintSource
{
    public static final StellarGrassItemTint INSTANCE = new StellarGrassItemTint();
    public static final MapCodec<StellarGrassItemTint> MAP_CODEC = MapCodec.unit(INSTANCE);

    private StellarGrassItemTint() {}

    @Override public int getTint(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user) {return ColorHandlerHelper.AERIAL_HELL_PLAINS_GRASS_COLOR;}

    @Override public MapCodec<StellarGrassItemTint> getCodec() {return MAP_CODEC;}
}
