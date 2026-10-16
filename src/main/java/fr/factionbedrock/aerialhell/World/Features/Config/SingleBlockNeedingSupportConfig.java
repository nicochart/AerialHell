package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

public record SingleBlockNeedingSupportConfig(int maxTries, SimpleStateProvider support, BlockStateProvider block)
{
    public static final MapCodec<SingleBlockNeedingSupportConfig> CODEC = RecordCodecBuilder.mapCodec((builder) -> {
        return builder.group(ExtraCodecs.POSITIVE_INT.fieldOf("max_tries").forGetter(SingleBlockNeedingSupportConfig::maxTries), SimpleStateProvider.CODEC.fieldOf("support").forGetter(SingleBlockNeedingSupportConfig::support), BlockStateProvider.DIRECT_CODEC.fieldOf("block").forGetter(SingleBlockNeedingSupportConfig::support)).apply(builder, SingleBlockNeedingSupportConfig::new);
    });
}
