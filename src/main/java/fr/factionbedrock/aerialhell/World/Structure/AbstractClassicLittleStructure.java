package fr.factionbedrock.aerialhell.World.Structure;

import fr.factionbedrock.aerialhell.Util.StructureHelper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.structure.Structure;
import java.util.List;
import java.util.Optional;

public abstract class AbstractClassicLittleStructure extends AbstractAerialHellStructure
{
    public AbstractClassicLittleStructure(Structure.Config config, RegistryEntry<StructurePool> startPool, Optional<Identifier> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Type> projectStartToHeightmap, int maxDistanceFromCenter)
    {
        super(config, startPool, startJigsawName, size, startHeight, projectStartToHeightmap, maxDistanceFromCenter, List.of()); //TODO : empty list ? check JigsawStructure() calls (line 75 of net.minecraft.world.level.levelgen.structure.structures.JigsawStructure)
    }

    @Override protected boolean isStructureChunk(Structure.Context context)
    {
    	//cannot spawn next to another structure
    	if (StructureHelper.hasDungeonNearby(context, 150, true, 100)) {return false;}

        int landHeight = getTerrainHeight(context);
        return landHeight > getMinY() && landHeight < getMaxY();
    }

    protected abstract int getMinY();
    protected abstract int getMaxY();
}