package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.StructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.FeatureContext;
import net.minecraft.world.level.levelgen.feature.configurations.DefaultFeatureConfig;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.Optional;

public class StructureHelper
{
	public static boolean hasDungeonNearby(ChunkGenerator chunkGenerator, long seed, int chunkX, int chunkZ, int checkRadius)
	{
		return hasDungeonNearby(chunkGenerator, seed, chunkX, chunkZ, checkRadius, true);
	}

	//TODO : WARNING - Problem with separation settings. All chunks will be Feature Chunks. Those methods will always return true if separation settings are unchanged
	public static boolean hasDungeonNearby(ChunkGenerator chunkGenerator, long seed, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
    {
		boolean mudDungeonNearby = hasMudDungeonNearby(chunkGenerator, seed, chunkX, chunkZ, checkRadius, checkBaseChunk);
		boolean lunaticTempleNearby = hasLunaticTempleNearby(chunkGenerator, seed, chunkX, chunkZ, checkRadius, checkBaseChunk);
		boolean goldenNetherPrisonNearby = hasGoldenNetherPrisonNearby(chunkGenerator, seed, chunkX, chunkZ, checkRadius, checkBaseChunk);
		boolean shadowCatacombsNearby = hasShadowCatacombsNearby(chunkGenerator, seed, chunkX, chunkZ, checkRadius, checkBaseChunk);
		return mudDungeonNearby || lunaticTempleNearby || goldenNetherPrisonNearby || shadowCatacombsNearby;
    }

	public static boolean hasDungeonNearby(Structure.GenerationContext context, /*ServerLevel level,*/ int blockCheckDistance, boolean checkAltitude, int yCheck) //the ServerLevel is missing in Structure.GenerationContext ...
	{
		/*BlockPos generationPos = context.chunkPos().getMiddleBlockPosition(yCheck);
		//ServerLevel : ServerLifecycleHooks.getCurrentServer().getLevel(dimensionKey) - How to get the dimensionKey ?
		BlockPos nearestDungeonPos = level.findNearestMapStructure(AerialHellTags.Structures.DUNGEONS, generationPos, 100, false);
		if (nearestDungeonPos != null)
		{
			if (!checkAltitude) {nearestDungeonPos = new BlockPos(nearestDungeonPos.getX(), yCheck, nearestDungeonPos.getZ());}
			return generationPos.getSquaredDistance(nearestDungeonPos) < blockCheckDistance;
		}
		else {return false;}
		---------------------------
		if (temporaryCheck_isStructureGeneratingInAerialHell(context))
		{
			BlockPos generationPos = context.chunkPos().getMiddleBlockPosition(yCheck);
			ServerLevel serverLevel = ServerLifecycleHooks.getCurrentServer().getLevel(AerialHellDimensions.AERIAL_HELL_DIMENSION);
			BlockPos nearestDungeonPos = serverLevel.findNearestMapStructure(AerialHellTags.Structures.DUNGEONS, generationPos, 100, false);
			if (nearestDungeonPos != null)
			{
				if (!checkAltitude) {nearestDungeonPos = new BlockPos(nearestDungeonPos.getX(), yCheck, nearestDungeonPos.getZ());}
				return generationPos.getSquaredDistance(nearestDungeonPos) < blockCheckDistance;
			}
			else {return true;}
		}
		else {return false;}*/ return false;
	}

	public static boolean temporaryCheck_isStructureGeneratingInAerialHell(Structure.GenerationContext context)
	{
		BlockPos genPos = context.chunkPos().getMiddleBlockPosition(100);
		int x=genPos.getX(), z=genPos.getZ(); int y = context.getGenerator().getFirstOccupiedHeight(x, z, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, context.heightAccessor(), context.randomState());
		BlockState surfaceBlock = context.getGenerator().getBaseColumn(x, z, context.heightAccessor(), context.randomState()).getBlock(y);

		boolean isSurfaceBlockAnAerialHellSurfaceBlock = surfaceBlock.isOf(AerialHellBlocks.STELLAR_GRASS_BLOCK.get()) || surfaceBlock.isOf(AerialHellBlocks.STELLAR_STONE.get()) || surfaceBlock.isOf(AerialHellBlocks.SHADOW_GRASS_BLOCK.get()) || surfaceBlock.isOf(AerialHellBlocks.SLIPPERY_SAND.get());
		return isSurfaceBlockAnAerialHellSurfaceBlock;
	}

	//TODO : update those methods
	public static boolean hasMudDungeonNearby(ChunkGenerator chunkGenerator, long seed, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
	{
		int temporaryCheckRadius = Math.min(3, checkRadius); //TEMPORARY SOLUTION to too common potential structures chunk
		return false;//chunkGenerator.hasFeatureChunkInRange(AerialHellStructures.Keys.MUD_DUNGEON_STRUCTURE, seed, chunkX, chunkZ, temporaryCheckRadius);
	}

	public static boolean hasLunaticTempleNearby(ChunkGenerator chunkGenerator, long seed, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
	{
		return false;//chunkGenerator.hasFeatureChunkInRange(AerialHellStructures.Keys.LUNATIC_TEMPLE_STRUCTURE, seed, chunkX, chunkZ, checkRadius);
	}

	public static boolean hasGoldenNetherPrisonNearby(ChunkGenerator chunkGenerator, long seed, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
	{
		return false;//chunkGenerator.hasFeatureChunkInRange(AerialHellStructures.Keys.GOLDEN_NETHER_PRISON_STRUCTURE, seed, chunkX, chunkZ, checkRadius);
	}

	public static boolean hasShadowCatacombsNearby(ChunkGenerator chunkGenerator, long seed, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
	{
		return false;//chunkGenerator.hasFeatureChunkInRange(AerialHellStructures.Keys.SHADOW_CATACOMBS_STRUCTURE, seed, chunkX, chunkZ, checkRadius);
	}

	public static HolderSet<StructureSet> getDungeonsSetHolderSet(RegistryAccess registryAccess)
	{
		HolderGetter<StructureSet> holderGetter = registryAccess.lookupOrThrow(Registries.STRUCTURE_SET);
		HolderSet<StructureSet> dungeonsHolderSet = HolderSet.direct(holderGetter.getOrThrow(AerialHellStructures.Sets.MUD_DUNGEON_STRUCTURE), holderGetter.getOrThrow(AerialHellStructures.Sets.LUNATIC_TEMPLE_STRUCTURE), holderGetter.getOrThrow(AerialHellStructures.Sets.GOLDEN_NETHER_PRISON_STRUCTURE), holderGetter.getOrThrow(AerialHellStructures.Sets.SHADOW_CATACOMBS_STRUCTURE));
		return dungeonsHolderSet;
	}

	public static HolderSet<Structure> getDungeonsHolderSet(RegistryAccess registryAccess)
	{
		Optional<HolderSet.Named<Structure>> optional = registryAccess.registryOrThrow(Registries.STRUCTURE).getTag(AerialHellTags.Structures.DUNGEONS);
		return optional.orElse(null);
	}

	/* TODO find something to replace
	private static boolean isSeparationSettingValidForNearbyDetection(StructureSeparationSettings separationConfig, int checkRadius)
	{
		TEMPORARY SOLUTION to too common potential structures chunk
		(warning : in this case, we do not check the actual presence of structure. The return is false even if there is a generated structure nearby. The return value will always be true if we do not do this)
		int minSeparationDistance =  separationConfig.func_236671_b_();
		return minSeparationDistance >= 2 * checkRadius;
	}*/
}
