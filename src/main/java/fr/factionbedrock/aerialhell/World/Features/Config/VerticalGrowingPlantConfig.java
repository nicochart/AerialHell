package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record VerticalGrowingPlantConfig(BlockStateProvider plantStateProvider, int spreadWidth, int spreadHeight, int minHeight, int maxHeight, int minTries, int maxTries)
{
    public static final MapCodec<VerticalGrowingPlantConfig> CODEC = RecordCodecBuilder.mapCodec((p_191375_) -> {
        return p_191375_.group(BlockStateProvider.DIRECT_CODEC.fieldOf("plant_state_provider").forGetter(VerticalGrowingPlantConfig::plantStateProvider), ExtraCodecs.POSITIVE_INT.fieldOf("spread_width").forGetter(VerticalGrowingPlantConfig::spreadWidth), ExtraCodecs.POSITIVE_INT.fieldOf("spread_height").forGetter(VerticalGrowingPlantConfig::spreadHeight), ExtraCodecs.POSITIVE_INT.fieldOf("min_height").forGetter(VerticalGrowingPlantConfig::maxHeight), ExtraCodecs.POSITIVE_INT.fieldOf("max_height").forGetter(VerticalGrowingPlantConfig::maxHeight), ExtraCodecs.POSITIVE_INT.fieldOf("min_tries").forGetter(VerticalGrowingPlantConfig::minTries), ExtraCodecs.POSITIVE_INT.fieldOf("max_tries").forGetter(VerticalGrowingPlantConfig::maxTries)).apply(p_191375_, VerticalGrowingPlantConfig::new);
    });
}
