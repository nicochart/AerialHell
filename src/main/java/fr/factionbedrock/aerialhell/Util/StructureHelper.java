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
		/* BUG : always returns true
    	//.func_235957_b_() = .getSettings() ; .func_236197_a_() = .getConfig()
    	StructureSeparationSettings MudDungeonConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.MUD_DUNGEON_STRUCTURE.get());
    	StructureSeparationSettings LunaticTempleConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get());
		StructureSeparationSettings GoldenNetherPrisonConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get());
		StructureSeparationSettings ShadowCatacombsConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.SHADOW_CATACOMBS_STRUCTURE.get());

    	if (MudDungeonConfig == null && LunaticTempleConfig == null && GoldenNetherPrisonConfig == null && ShadowCatacombsConfig == null) {return false;}
    	else
    	{
    		for (int cx = chunkX - checkRadius; cx <= chunkX + checkRadius; cx++)
        	{
                for (int cz = chunkZ - checkRadius; cz <= chunkZ + checkRadius; cz++)
                {
					if (checkBaseChunk || cx != chunkX || cz != chunkZ)
					{
						ChunkPos chunkPos;
						if (MudDungeonConfig != null) {
							chunkPos = AerialHellStructures.MUD_DUNGEON_STRUCTURE.get().getChunkPosForStructure(MudDungeonConfig, seed, chunkRandom, cx, cz);
							if (cx == chunkPos.x && cz == chunkPos.z) {return true;}
						}
						if (LunaticTempleConfig != null) {
							chunkPos = AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get().getChunkPosForStructure(LunaticTempleConfig, seed, chunkRandom, cx, cz);
							if (cx == chunkPos.x && cz == chunkPos.z) {return true;}
						}
						if (GoldenNetherPrisonConfig != null) {
							chunkPos = AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get().getChunkPosForStructure(GoldenNetherPrisonConfig, seed, chunkRandom, cx, cz);
							if (cx == chunkPos.x && cz == chunkPos.z) {return true;}
						}
						if (ShadowCatacombsConfig != null) {
							chunkPos = AerialHellStructures.SHADOW_CATACOMBS_STRUCTURE.get().getChunkPosForStructure(ShadowCatacombsConfig, seed, chunkRandom, cx, cz);
							if (cx == chunkPos.x && cz == chunkPos.z) {return true;}
						}
					}
                }
        	}
    	}
    	return false;
    	*/
		return false;
    }

	public static boolean hasMudDungeonNearby(ChunkGenerator chunkGenerator, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
	{
		/* BUG : always returns true
		StructureSeparationSettings MudDungeonConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.MUD_DUNGEON_STRUCTURE.get());
		if (MudDungeonConfig == null) {return false;}
		else
		{
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
		}
		return false;
		*/
		return false;
	}

	public static boolean hasLunaticTempleNearby(ChunkGenerator chunkGenerator, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
	{
		/* BUG : always returns true
		StructureSeparationSettings LunaticTempleConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get());
		if (LunaticTempleConfig == null) {return false;}
		else
		{
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
		}
		return false;
		*/
		return false;
	}

	public static boolean hasGoldenNetherPrisonNearby(ChunkGenerator chunkGenerator, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
	{
		/* BUG : always returns true
		StructureSeparationSettings GoldenNetherPrisonConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get());
		if (GoldenNetherPrisonConfig == null) {return false;}
		else
		{
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
		}
		return false;
		*/
		return false;
	}

	public static boolean hasShadowCatacombsNearby(ChunkGenerator chunkGenerator, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, int checkRadius, boolean checkBaseChunk)
	{
		/* BUG : always returns true
		StructureSeparationSettings ShadowCatacombsConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.SHADOW_CATACOMBS_STRUCTURE.get());
		if (ShadowCatacombsConfig == null) {return false;}
		else
		{
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
		}
		return false;
		*/
		return false;
	}
}
