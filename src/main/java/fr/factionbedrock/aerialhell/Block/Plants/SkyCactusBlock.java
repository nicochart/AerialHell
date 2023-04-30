package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;

public class SkyCactusBlock extends CactusBlock
{
	public SkyCactusBlock(Properties properties)
	{
		super(properties);
	}
	
	/*Edited isValidPosition to make the cactus can grow on slippery_sand and on itself*/
	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
	{
		for(Direction direction : Direction.Plane.HORIZONTAL)
	    {
			BlockState blockstate = worldIn.getBlockState(pos.relative(direction));
	        Material material = blockstate.getMaterial();
	        if (material.isSolid() || worldIn.getFluidState(pos.relative(direction)).is(FluidTags.LAVA))
	        {
	        	return false;
	        }
	    }
	    BlockState bottom_blockstate = worldIn.getBlockState(pos.below());
	    return (bottom_blockstate.getBlock().equals(AerialHellBlocksAndItems.SKY_CACTUS.get()) || bottom_blockstate.getBlock().equals(AerialHellBlocksAndItems.VIBRANT_SKY_CACTUS.get()) || bottom_blockstate.getBlock().equals(AerialHellBlocksAndItems.SLIPPERY_SAND.get()));
	}
	
	/*Edited onEntityCollision to deal more damage and to make the player jump*/
	@Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn)
	{
		Vec3 motion = entityIn.getDeltaMovement();
		entityIn.hurt(DamageSource.CACTUS, 2.0F);
		entityIn.setDeltaMovement(motion.x, 1.0, motion.z);
	}
}
