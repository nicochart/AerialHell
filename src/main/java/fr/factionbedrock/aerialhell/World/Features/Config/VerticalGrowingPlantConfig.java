package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record VerticalGrowingPlantConfig(int spreadWidth, int spreadHeight, int minHeight, int maxHeight, int minTries, int maxTries) implements FeatureConfig
{
    public static final Codec<VerticalGrowingPlantConfig> CODEC = RecordCodecBuilder.create((p_191375_) -> {
        return p_191375_.group(Codecs.POSITIVE_INT.fieldOf("spread_width").forGetter(VerticalGrowingPlantConfig::spreadWidth), Codecs.POSITIVE_INT.fieldOf("spread_height").forGetter(VerticalGrowingPlantConfig::spreadHeight), Codecs.POSITIVE_INT.fieldOf("min_height").forGetter(VerticalGrowingPlantConfig::maxHeight), Codecs.POSITIVE_INT.fieldOf("max_height").forGetter(VerticalGrowingPlantConfig::maxHeight), Codecs.POSITIVE_INT.fieldOf("min_tries").forGetter(VerticalGrowingPlantConfig::minTries), Codecs.POSITIVE_INT.fieldOf("max_tries").forGetter(VerticalGrowingPlantConfig::maxTries)).apply(p_191375_, VerticalGrowingPlantConfig::new);
    });
}
