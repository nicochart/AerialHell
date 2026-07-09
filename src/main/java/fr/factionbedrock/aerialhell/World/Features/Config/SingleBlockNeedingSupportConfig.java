package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

public record SingleBlockNeedingSupportConfig(int maxTries, SimpleStateProvider support, BlockStateProvider block) implements FeatureConfiguration
{
    public static final Codec<SingleBlockNeedingSupportConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(ExtraCodecs.POSITIVE_INT.fieldOf("max_tries").forGetter(SingleBlockNeedingSupportConfig::maxTries), SimpleStateProvider.CODEC.fieldOf("support").forGetter(SingleBlockNeedingSupportConfig::support), BlockStateProvider.CODEC.fieldOf("block").forGetter(SingleBlockNeedingSupportConfig::support)).apply(builder, SingleBlockNeedingSupportConfig::new);
    });
}
