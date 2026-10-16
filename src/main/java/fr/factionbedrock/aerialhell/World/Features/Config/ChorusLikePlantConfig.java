package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;

public record ChorusLikePlantConfig(String needsRoof)
{
    public static final MapCodec<ChorusLikePlantConfig> CODEC = RecordCodecBuilder.mapCodec((builder) -> {
        return builder.group(ExtraCodecs.NON_EMPTY_STRING.fieldOf("needs_roof").forGetter(ChorusLikePlantConfig::needsRoof)).apply(builder, ChorusLikePlantConfig::new);
    });
}
