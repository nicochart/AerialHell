package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class SkyCactusBlock extends CactusBlock
{
	public SkyCactusBlock(Properties properties)
	{
		super(properties);
	}
	
	/*Edited isValidPosition to make the cactus can grow on slippery_sand and on itself*/
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
	{
		for(Direction direction : Direction.Plane.HORIZONTAL)
	    {
			BlockState blockstate = worldIn.getBlockState(pos.offset(direction));
	        Material material = blockstate.getMaterial();
	        if (material.isSolid() || worldIn.getFluidState(pos.offset(direction)).isTagged(FluidTags.LAVA))
	        {
	        	return false;
	        }
	    }
	    BlockState bottom_blockstate = worldIn.getBlockState(pos.down());
	    return (bottom_blockstate.getBlock().equals(AerialHellBlocksAndItems.SKY_CACTUS.get()) || bottom_blockstate.getBlock().equals(AerialHellBlocksAndItems.VIBRANT_SKY_CACTUS.get()) || bottom_blockstate.getBlock().equals(AerialHellBlocksAndItems.SLIPPERY_SAND.get()));
	}
	
	/*Edited onEntityCollision to deal more damage and to make the player jump*/
	@Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
		Vector3d motion = entityIn.getMotion();
		entityIn.attackEntityFrom(DamageSource.CACTUS, 2.0F);
		entityIn.setMotion(motion.x, 1.0, motion.z);
	}
}
