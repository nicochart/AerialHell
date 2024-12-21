package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record AerialHellTwistingVinesConfig(int spreadWidth, int spreadHeight, int maxHeight, String needsRoof) implements FeatureConfig
{
    public static final Codec<AerialHellTwistingVinesConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(Codecs.POSITIVE_INT.fieldOf("spread_width").forGetter(AerialHellTwistingVinesConfig::spreadWidth), Codecs.POSITIVE_INT.fieldOf("spread_height").forGetter(AerialHellTwistingVinesConfig::spreadHeight), Codecs.POSITIVE_INT.fieldOf("max_height").forGetter(AerialHellTwistingVinesConfig::maxHeight), Codecs.NON_EMPTY_STRING.fieldOf("needs_roof").forGetter(AerialHellTwistingVinesConfig::needsRoof)).apply(builder, AerialHellTwistingVinesConfig::new);
    });
}
