package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record ChorusLikePlantConfig(String needsRoof) implements FeatureConfig
{
    public static final Codec<ChorusLikePlantConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(Codecs.NON_EMPTY_STRING.fieldOf("needs_roof").forGetter(ChorusLikePlantConfig::needsRoof)).apply(builder, ChorusLikePlantConfig::new);
    });
}
