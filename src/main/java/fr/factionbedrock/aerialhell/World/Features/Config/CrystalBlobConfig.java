package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record CrystalBlobConfig(BlockStateProvider crystalStateProvider)
{
    public static final MapCodec<CrystalBlobConfig> CODEC = RecordCodecBuilder.mapCodec((builder) -> {
        return builder.group(BlockStateProvider.DIRECT_CODEC.fieldOf("crystal_state_provider").forGetter(CrystalBlobConfig::crystalStateProvider)).apply(builder, CrystalBlobConfig::new);
    });
}