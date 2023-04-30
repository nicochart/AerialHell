package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Registry.AerialHellStructures;
import net.minecraft.world.level.chunk.ChunkGenerator;

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

	public static boolean hasMudDungeonNearby(ChunkGenerator chunkGenerator, long seed, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
	{
		return chunkGenerator.hasFeatureChunkInRange(AerialHellStructures.MUD_DUNGEON_STRUCTURE.get(), seed, chunkX, chunkZ, checkRadius);
	}

	public static boolean hasLunaticTempleNearby(ChunkGenerator chunkGenerator, long seed, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
	{
		return chunkGenerator.hasFeatureChunkInRange(AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get(), seed, chunkX, chunkZ, checkRadius);
	}

	public static boolean hasGoldenNetherPrisonNearby(ChunkGenerator chunkGenerator, long seed, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
	{
		return chunkGenerator.hasFeatureChunkInRange(AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get(), seed, chunkX, chunkZ, checkRadius);
	}

	public static boolean hasShadowCatacombsNearby(ChunkGenerator chunkGenerator, long seed, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
	{
		return chunkGenerator.hasFeatureChunkInRange(AerialHellStructures.SHADOW_CATACOMBS_STRUCTURE.get(), seed, chunkX, chunkZ, checkRadius);
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
