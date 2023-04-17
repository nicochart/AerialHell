package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Registry.AerialHellStructures;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

public class StructureHelper
{
	public static boolean hasDungeonNearby(ChunkGenerator chunkGenerator, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, int checkRadius)
	{
		return hasDungeonNearby(chunkGenerator, seed, chunkRandom, chunkX, chunkZ, checkRadius, true);
	}

	public static boolean hasDungeonNearby(ChunkGenerator chunkGenerator, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
    {
		//This function does not check the presence / absence of nearby dungeon, but the presence / absence of chunk where the structure could have been potentially generated.
		//See min and mean distance separation settings. This ignores "isFeatureChunk" function in structures class (if it returns false, the chunk is still a potential structure generation chunk !)
    	//.func_235957_b_() = .getSettings() ; .func_236197_a_() = .getConfig()
    	StructureSeparationSettings MudDungeonConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.MUD_DUNGEON_STRUCTURE.get());
    	StructureSeparationSettings LunaticTempleConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get());
		StructureSeparationSettings GoldenNetherPrisonConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get());
		StructureSeparationSettings ShadowCatacombsConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.SHADOW_CATACOMBS_STRUCTURE.get());
		//AnyStructureConfig.func_236668_a_() = mean distance between spawn attempts (chunks) ; AnyStructureConfig.func_236671_b_() = min distance between spawn attempts (chunks)


		boolean isMudDungeonConfigValid = MudDungeonConfig != null && isSeparationSettingValidForNearbyDetection(MudDungeonConfig, checkRadius); //Temporary solution to too common potential structure chunks
		boolean isLunaticTempleConfigValid = LunaticTempleConfig != null && isSeparationSettingValidForNearbyDetection(LunaticTempleConfig, checkRadius); //Temporary solution to too common potential structure chunks
		boolean isGoldenNetherPrisonConfigValid = GoldenNetherPrisonConfig != null && isSeparationSettingValidForNearbyDetection(GoldenNetherPrisonConfig, checkRadius); //Temporary solution to too common potential structure chunks
		boolean isShadowCatacombsConfigValid = ShadowCatacombsConfig != null && isSeparationSettingValidForNearbyDetection(ShadowCatacombsConfig, checkRadius); //Temporary solution to too common potential structure chunks

		if (!isMudDungeonConfigValid && !isLunaticTempleConfigValid && !isGoldenNetherPrisonConfigValid && !isShadowCatacombsConfigValid) {return false;}
		else
		{
			for (int cx = chunkX - checkRadius; cx <= chunkX + checkRadius; cx++)
			{
				for (int cz = chunkZ - checkRadius; cz <= chunkZ + checkRadius; cz++)
				{
					if (checkBaseChunk || cx != chunkX || cz != chunkZ)
					{
						ChunkPos chunkPos;
						if (isMudDungeonConfigValid)
						{
							chunkPos = AerialHellStructures.MUD_DUNGEON_STRUCTURE.get().getChunkPosForStructure(MudDungeonConfig, seed, chunkRandom, cx, cz);
							if (cx == chunkPos.x && cz == chunkPos.z) {return true;}
						}
						if (isLunaticTempleConfigValid)
						{
							chunkPos = AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get().getChunkPosForStructure(LunaticTempleConfig, seed, chunkRandom, cx, cz);
							if (cx == chunkPos.x && cz == chunkPos.z) {return true;}
						}
						if (isGoldenNetherPrisonConfigValid)
						{
							chunkPos = AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get().getChunkPosForStructure(GoldenNetherPrisonConfig, seed, chunkRandom, cx, cz);
							if (cx == chunkPos.x && cz == chunkPos.z) {return true;}
						}
						if (isShadowCatacombsConfigValid)
						{
							chunkPos = AerialHellStructures.SHADOW_CATACOMBS_STRUCTURE.get().getChunkPosForStructure(ShadowCatacombsConfig, seed, chunkRandom, cx, cz);
							if (cx == chunkPos.x && cz == chunkPos.z) {return true;}
						}
					}
				}
			}
		}
		return false;
    }

	public static boolean hasMudDungeonNearby(ChunkGenerator chunkGenerator, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
	{
		StructureSeparationSettings MudDungeonConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.MUD_DUNGEON_STRUCTURE.get());
		if (MudDungeonConfig == null) {return false;}

		if (!isSeparationSettingValidForNearbyDetection(MudDungeonConfig, checkRadius)) {return false;} //Temporary solution to too common potential structure chunk

		for (int cx = chunkX - checkRadius; cx <= chunkX + checkRadius; cx++)
		{
			for (int cz = chunkZ - checkRadius; cz <= chunkZ + checkRadius; cz++)
			{
				if (checkBaseChunk || cx != chunkX || cz != chunkZ)
				{
					ChunkPos chunkPos = AerialHellStructures.MUD_DUNGEON_STRUCTURE.get().getChunkPosForStructure(MudDungeonConfig, seed, chunkRandom, cx, cz);
					if (cx == chunkPos.x && cz == chunkPos.z) {return true;}
				}
			}
		}
		return false;
	}

	public static boolean hasLunaticTempleNearby(ChunkGenerator chunkGenerator, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
	{
		StructureSeparationSettings LunaticTempleConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get());
		if (LunaticTempleConfig == null) {return false;}

		if (!isSeparationSettingValidForNearbyDetection(LunaticTempleConfig, checkRadius)) {return false;} //Temporary solution to too common potential structure chunk

		for (int cx = chunkX - checkRadius; cx <= chunkX + checkRadius; cx++)
		{
			for (int cz = chunkZ - checkRadius; cz <= chunkZ + checkRadius; cz++)
			{
				if (checkBaseChunk || cx != chunkX || cz != chunkZ)
				{
					ChunkPos chunkPos = AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get().getChunkPosForStructure(LunaticTempleConfig, seed, chunkRandom, cx, cz);
					if (cx == chunkPos.x && cz == chunkPos.z) {return true;}
				}
			}
		}
		return false;
	}

	public static boolean hasGoldenNetherPrisonNearby(ChunkGenerator chunkGenerator, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
	{
		StructureSeparationSettings GoldenNetherPrisonConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get());
		if (GoldenNetherPrisonConfig == null) {return false;}

		if (!isSeparationSettingValidForNearbyDetection(GoldenNetherPrisonConfig, checkRadius)) {return false;} //Temporary solution to too common potential structure chunk

		for (int cx = chunkX - checkRadius; cx <= chunkX + checkRadius; cx++)
		{
			for (int cz = chunkZ - checkRadius; cz <= chunkZ + checkRadius; cz++)
			{
				if (checkBaseChunk || cx != chunkX || cz != chunkZ)
				{
					ChunkPos chunkPos = AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get().getChunkPosForStructure(GoldenNetherPrisonConfig, seed, chunkRandom, cx, cz);
					if (cx == chunkPos.x && cz == chunkPos.z) {return true;}
				}
			}
		}
		return false;
	}

	public static boolean hasShadowCatacombsNearby(ChunkGenerator chunkGenerator, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
	{
		StructureSeparationSettings ShadowCatacombsConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.SHADOW_CATACOMBS_STRUCTURE.get());
		if (ShadowCatacombsConfig == null) {return false;}

		if (!isSeparationSettingValidForNearbyDetection(ShadowCatacombsConfig, checkRadius)) {return false;} //Temporary solution to too common potential structure chunk

		for (int cx = chunkX - checkRadius; cx <= chunkX + checkRadius; cx++)
		{
			for (int cz = chunkZ - checkRadius; cz <= chunkZ + checkRadius; cz++)
			{
				if (checkBaseChunk || cx != chunkX || cz != chunkZ)
				{
					ChunkPos chunkPos = AerialHellStructures.SHADOW_CATACOMBS_STRUCTURE.get().getChunkPosForStructure(ShadowCatacombsConfig, seed, chunkRandom, cx, cz);
					if (cx == chunkPos.x && cz == chunkPos.z) {return true;}
				}
			}
		}
		return false;
	}

	private static boolean isSeparationSettingValidForNearbyDetection(StructureSeparationSettings separationConfig, int checkRadius)
	{
		/*
		TEMPORARY SOLUTION to too common potential structures chunk
		(warning : in this case, we do not check the actual presence of structure. The return is false even if there is a generated structure nearby. The return value will always be true if we do not do this)
		*/
		int minSeparationDistance =  separationConfig.func_236671_b_();
		return minSeparationDistance >= 2 * checkRadius;
	}
}
