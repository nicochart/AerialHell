package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record AerialHellTwistingVinesConfig(int spreadWidth, int spreadHeight, int maxHeight, String needsRoof) implements FeatureConfiguration
{
    public static final Codec<AerialHellTwistingVinesConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(ExtraCodecs.POSITIVE_INT.fieldOf("spread_width").forGetter(AerialHellTwistingVinesConfig::spreadWidth), ExtraCodecs.POSITIVE_INT.fieldOf("spread_height").forGetter(AerialHellTwistingVinesConfig::spreadHeight), ExtraCodecs.POSITIVE_INT.fieldOf("max_height").forGetter(AerialHellTwistingVinesConfig::maxHeight), ExtraCodecs.NON_EMPTY_STRING.fieldOf("needs_roof").forGetter(AerialHellTwistingVinesConfig::needsRoof)).apply(builder, AerialHellTwistingVinesConfig::new);
    });
}
