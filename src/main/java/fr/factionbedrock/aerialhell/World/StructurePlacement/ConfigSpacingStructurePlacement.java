package fr.factionbedrock.aerialhell.World.StructurePlacement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;

import java.util.Optional;

//RandomSpreadStructurePlacement but with spacing from config instead of structure set json file
public class ConfigSpacingStructurePlacement extends RandomSpreadStructurePlacement
{
    public static final MapCodec<ConfigSpacingStructurePlacement> CODEC = RecordCodecBuilder.<ConfigSpacingStructurePlacement>mapCodec(
                    configPlacementInstance -> placementCodec(configPlacementInstance)
                            .and(
                                    configPlacementInstance.group(
                                            Codec.intRange(0, 4096).fieldOf("separation").forGetter(ConfigSpacingStructurePlacement::separation),
                                            RandomSpreadType.CODEC
                                                    .optionalFieldOf("spread_type", RandomSpreadType.LINEAR)
                                                    .forGetter(ConfigSpacingStructurePlacement::spreadType)
                                    )
                            )
                            .apply(configPlacementInstance, ConfigSpacingStructurePlacement::new)
            )
            .validate(ConfigSpacingStructurePlacement::validate);

    private static DataResult<ConfigSpacingStructurePlacement> validate(ConfigSpacingStructurePlacement placement) {return placement.spacing() <= placement.separation() ? DataResult.error(() -> "Spacing has to be larger than separation") : DataResult.success(placement);}

    public ConfigSpacingStructurePlacement(Vec3i locateOffset, StructurePlacement.FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<ExclusionZone> exclusionZone, int separation, RandomSpreadType spreadType)
    {
        super(locateOffset, frequencyReductionMethod, frequency, salt, exclusionZone, LoadedConfigParams.OVERWORLD_ABANDONNED_PORTAL_SPACING_OVERRIDE, separation, spreadType);
    }
}
