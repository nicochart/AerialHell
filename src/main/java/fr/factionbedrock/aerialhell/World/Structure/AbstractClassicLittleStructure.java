package fr.factionbedrock.aerialhell.World.Structure;

import fr.factionbedrock.aerialhell.Util.StructureHelper;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.List;
import java.util.Optional;

public abstract class AbstractClassicLittleStructure extends AbstractAerialHellStructure
{
    public AbstractClassicLittleStructure(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter)
    {
        super(config, startPool, startJigsawName, size, startHeight, projectStartToHeightmap, maxDistanceFromCenter, List.of()); //TODO : empty list ? check JigsawStructure() calls (line 75 of net.minecraft.world.level.levelgen.structure.structures.JigsawStructure)
    }

    protected boolean isStructureChunk(Structure.GenerationContext context)
    {
    	//cannot spawn next to another structure
    	if (StructureHelper.hasDungeonNearby(context, 150, true, 100)) {return false;}

        int landHeight = getTerrainHeight(context);
        return landHeight > getMinY() && landHeight < getMaxY();
    }

    protected abstract int getMinY();
    protected abstract int getMaxY();
}