package fr.factionbedrock.aerialhell.World.Features.GiantTree;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.World.Features.DungeonSensitiveFeatureCheck;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;

//shared behaviour of giant tree features (same pattern as vanilla AbstractHugeMushroomFeature)
public interface AbstractGiantTreeFeature extends Feature, DungeonSensitiveFeatureCheck
{
    default boolean canPlace(WorldGenLevel level, BlockPos origin)
    {
        return isValidTreePos(level, origin) && this.isDungeonSensitiveValid(level, origin);
    }

    default boolean isValidTreePos(WorldGenLevel level, BlockPos pos) {return isValidTreeSupport(level.getBlockState(pos.below())) && (level.isEmptyBlock(pos) || level.getBlockState(pos).is(AerialHellTags.Blocks.AERIALHELL_SAPLINGS)) && thereIsAirAbovePosition(level, pos);}
    default boolean isValidTreeSupport(BlockState state) {return state.is(AerialHellTags.Blocks.STELLAR_DIRT);}
    default boolean thereIsAirAbovePosition(WorldGenLevel level, BlockPos pos) {return thereIsAirColumnAbovePos(level, pos);}
    default boolean thereIsAirColumnAbovePos(WorldGenLevel reader, BlockPos pos)
    {
        for (int y=1; y<=8; y++) {if (!reader.getBlockState(pos.above(y)).isAir()) {return false;}} return true;
    }
}
