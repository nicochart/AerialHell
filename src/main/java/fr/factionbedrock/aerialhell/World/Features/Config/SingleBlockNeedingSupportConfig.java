package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public record SingleBlockNeedingSupportConfig(int maxTries, SimpleBlockStateProvider support, BlockStateProvider block) implements FeatureConfig
{
    public static final Codec<SingleBlockNeedingSupportConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(Codecs.POSITIVE_INT.fieldOf("max_tries").forGetter(SingleBlockNeedingSupportConfig::maxTries), SimpleBlockStateProvider.CODEC.fieldOf("support").forGetter(SingleBlockNeedingSupportConfig::support), BlockStateProvider.TYPE_CODEC.fieldOf("block").forGetter(SingleBlockNeedingSupportConfig::support)).apply(builder, SingleBlockNeedingSupportConfig::new);
    });
}
