package fr.factionbedrock.aerialhell.World.StructurePlacement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;

import java.util.Optional;

public class ConfigSpacingStructurePlacement extends RandomSpreadStructurePlacement
{
    public static final MapCodec<ConfigSpacingStructurePlacement> CODEC = RecordCodecBuilder.<ConfigSpacingStructurePlacement>mapCodec(
                    instance -> buildCodec(instance)
                            .and(
                                    instance.group(
                                            Codec.intRange(0, 4096).fieldOf("separation").forGetter(ConfigSpacingStructurePlacement::getSeparation),
                                            SpreadType.CODEC.optionalFieldOf("spread_type", SpreadType.LINEAR).forGetter(ConfigSpacingStructurePlacement::getSpreadType)
                                    )
                            )
                            .apply(instance, ConfigSpacingStructurePlacement::new)
            )
            .validate(ConfigSpacingStructurePlacement::validate);

    private static DataResult<ConfigSpacingStructurePlacement> validate(ConfigSpacingStructurePlacement structurePlacement)
    {
        return structurePlacement.getSpacing() <= structurePlacement.getSeparation() ? DataResult.error(() -> "Spacing has to be larger than separation") : DataResult.success(structurePlacement);
    }

    public ConfigSpacingStructurePlacement(Vec3i locateOffset, StructurePlacement.FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<ExclusionZone> exclusionZone, int separation, SpreadType spreadType)
    {
        super(locateOffset, frequencyReductionMethod, frequency, salt, exclusionZone, LoadedConfigParams.OVERWORLD_ABANDONNED_PORTAL_SPACING_OVERRIDE, separation, spreadType);
    }
}
