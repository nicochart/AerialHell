package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record ChorusLikePlantConfig(String needsRoof) implements FeatureConfiguration
{
    public static final Codec<ChorusLikePlantConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(ExtraCodecs.NON_EMPTY_STRING.fieldOf("needs_roof").forGetter(ChorusLikePlantConfig::needsRoof)).apply(builder, ChorusLikePlantConfig::new);
    });
}
