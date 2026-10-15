package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record AerialHellTwistingVinesConfig(int spreadWidth, int spreadHeight, int maxHeight, BlockStateProvider headBlockProvider, BlockStateProvider bodyBlockProvider, String needsRoof)
{
    public static final MapCodec<AerialHellTwistingVinesConfig> CODEC = RecordCodecBuilder.mapCodec((builder) -> {
        return builder.group(ExtraCodecs.POSITIVE_INT.fieldOf("spread_width").forGetter(AerialHellTwistingVinesConfig::spreadWidth), ExtraCodecs.POSITIVE_INT.fieldOf("spread_height").forGetter(AerialHellTwistingVinesConfig::spreadHeight), ExtraCodecs.POSITIVE_INT.fieldOf("max_height").forGetter(AerialHellTwistingVinesConfig::maxHeight), BlockStateProvider.DIRECT_CODEC.fieldOf("head_state_provider").forGetter(AerialHellTwistingVinesConfig::headBlockProvider), BlockStateProvider.DIRECT_CODEC.fieldOf("body_state_provider").forGetter(AerialHellTwistingVinesConfig::bodyBlockProvider), ExtraCodecs.NON_EMPTY_STRING.fieldOf("needs_roof").forGetter(AerialHellTwistingVinesConfig::needsRoof)).apply(builder, AerialHellTwistingVinesConfig::new);
    });
}
