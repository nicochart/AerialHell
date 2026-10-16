package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

//copy of vanilla HugeMushroomFeatureConfiguration, removed in 26.3 (vanilla huge mushroom features now hold these fields directly)
public record HugeMushroomFeatureConfiguration(BlockStateProvider capProvider, BlockStateProvider stemProvider, int foliageRadius, BlockPredicate canPlaceOn)
{
    public static final MapCodec<HugeMushroomFeatureConfiguration> CODEC = RecordCodecBuilder.mapCodec(
        i -> i.group(
                BlockStateProvider.DIRECT_CODEC.fieldOf("cap_provider").forGetter(c -> c.capProvider),
                BlockStateProvider.DIRECT_CODEC.fieldOf("stem_provider").forGetter(c -> c.stemProvider),
                Codec.INT.optionalFieldOf("foliage_radius", 2).forGetter(c -> c.foliageRadius),
                BlockPredicate.CODEC.fieldOf("can_place_on").forGetter(c -> c.canPlaceOn)
            )
            .apply(i, HugeMushroomFeatureConfiguration::new)
    );
}
