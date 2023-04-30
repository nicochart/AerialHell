package fr.factionbedrock.aerialhell.World.Structure;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

public abstract class AbstractAerialHellStructure extends StructureFeature<NoneFeatureConfiguration>
{
    public AbstractAerialHellStructure(Codec<NoneFeatureConfiguration> codec, PieceGeneratorSupplier<NoneFeatureConfiguration> pieceGeneratorSupplier)
    {
        super(codec, pieceGeneratorSupplier);
    }

    @Override
    public GenerationStep.Decoration step() //What stage of generation your structure should be generated during
    {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    public static abstract class Start extends StructureStart
    {

        public Start(Structure<NoneFeatureConfiguration> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn)
        {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public BlockPos getLowestLand(ChunkGenerator chunkGenerator)
        {
            BlockPos.Mutable mutable = new BlockPos.Mutable().setPos(this.bounds.func_215126_f().getX(), 32, this.bounds.func_215126_f().getZ());
            IBlockReader blockView = chunkGenerator.func_230348_a_(mutable.getX(), mutable.getZ());
            BlockState currentBlockstate = blockView.getBlockState(mutable);
            while (mutable.getY() <= 250)
            {

                if(blockView.getBlockState(mutable).getMaterial() != Material.AIR && blockView.getBlockState(mutable.above()).getMaterial() == Material.AIR && blockView.getBlockState(mutable.up(5)).getMaterial() == Material.AIR && isValidBlock(currentBlockstate))
                {
                    mutable.move(Direction.UP);
                    break;
                }

                mutable.move(Direction.UP);
                currentBlockstate = blockView.getBlockState(mutable);
            }

            return mutable;
        }

        public BlockPos getHighestLand(ChunkGenerator chunkGenerator)
        {
            BlockPos.Mutable mutable = new BlockPos.Mutable().setPos(this.bounds.func_215126_f().getX(), 250, this.bounds.func_215126_f().getZ());
            IBlockReader blockView = chunkGenerator.func_230348_a_(mutable.getX(), mutable.getZ());
            BlockState currentBlockstate;
            while (mutable.getY() > 32)
            {
                currentBlockstate = blockView.getBlockState(mutable);
                if (!currentBlockstate.isNormalCube(blockView, mutable))
                {
                    mutable.move(Direction.DOWN);
                    continue;
                }
                else if (blockView.getBlockState(mutable.add(0, 3, 0)).getMaterial() == Material.AIR && isValidBlock(currentBlockstate))
                {break;}
                mutable.move(Direction.DOWN);
            }

            return mutable;
        }

        private boolean isValidBlock(BlockState currentBlockstate)
        {
            return currentBlockstate.isIn(Blocks.AIR.getBlock());
        }
    }
}