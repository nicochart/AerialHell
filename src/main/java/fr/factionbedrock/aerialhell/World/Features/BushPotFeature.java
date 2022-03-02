package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.*;

import java.util.Random;

public class BushPotFeature extends Feature<NoFeatureConfig> {

    private static final ResourceLocation BUSH = new ResourceLocation(AerialHell.MODID, "floating_bush/floating_bush");

    public BushPotFeature(Codec<NoFeatureConfig> codec) {super(codec);}
    
    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        Rotation rotation = Rotation.randomRotation(rand);
        TemplateManager templatemanager = reader.getWorld().getServer().getTemplateManager();
        Template bush = templatemanager.getTemplateDefaulted(BUSH);
        ChunkPos chunkpos = new ChunkPos(pos);
        MutableBoundingBox mutableboundingbox = new MutableBoundingBox(chunkpos.getXStart(), 0, chunkpos.getZStart(), chunkpos.getXEnd(), 256, chunkpos.getZEnd());
        PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setBoundingBox(mutableboundingbox).setRandom(rand).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
        BlockPos blockpos = bush.transformedSize(rotation);
        int x = rand.nextInt(16 - blockpos.getX()); //le random permet de ne pas toujours poser au coin d'un chunk
        int z = rand.nextInt(16 - blockpos.getZ()); //le random permet de ne pas toujours poser au coin d'un chunk

        int y = reader.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, pos.getX() + x, pos.getZ() + z);
        
        if (y>60)
        {
        	BlockPos blockpos1 = bush.getZeroPositionWithTransform(pos.add(x, y, z), Mirror.NONE, rotation);
        	bush.func_237146_a_(reader, blockpos1, blockpos1, placementsettings, rand, 4);
        	return true;
    	}
    	return false;
    }
}