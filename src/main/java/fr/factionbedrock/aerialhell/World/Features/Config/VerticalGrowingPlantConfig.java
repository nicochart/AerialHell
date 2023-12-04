package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record VerticalGrowingPlantConfig(int spreadWidth, int spreadHeight, int minHeight, int maxHeight) implements FeatureConfiguration
{
    public static final Codec<VerticalGrowingPlantConfig> CODEC = RecordCodecBuilder.create((p_191375_) -> {
        return p_191375_.group(ExtraCodecs.POSITIVE_INT.fieldOf("spread_width").forGetter(VerticalGrowingPlantConfig::spreadWidth), ExtraCodecs.POSITIVE_INT.fieldOf("spread_height").forGetter(VerticalGrowingPlantConfig::spreadHeight), ExtraCodecs.POSITIVE_INT.fieldOf("min_height").forGetter(VerticalGrowingPlantConfig::maxHeight), ExtraCodecs.POSITIVE_INT.fieldOf("max_height").forGetter(VerticalGrowingPlantConfig::maxHeight)).apply(p_191375_, VerticalGrowingPlantConfig::new);
    });
}
