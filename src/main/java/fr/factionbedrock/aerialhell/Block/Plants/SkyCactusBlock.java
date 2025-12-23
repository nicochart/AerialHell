package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SkyCactusBlock extends CactusBlock
{
	public SkyCactusBlock(Properties properties)
	{
		super(properties);
	}
	
	/*Edited isValidPosition to make the cactus can grow on slippery_sand and on itself*/
	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
	{
		for(Direction direction : Direction.Plane.HORIZONTAL)
	    {
			BlockState blockstate = level.getBlockState(pos.relative(direction));
	        if (blockstate.isSolid() || level.getFluidState(pos.relative(direction)).is(FluidTags.LAVA)) {return false;}
	    }
	    BlockState bottom_blockstate = level.getBlockState(pos.below());
	    return (bottom_blockstate.getBlock().equals(AerialHellBlocks.SKY_CACTUS.get()) || bottom_blockstate.getBlock().equals(AerialHellBlocks.VIBRANT_SKY_CACTUS.get()) || bottom_blockstate.getBlock().equals(AerialHellBlocks.SLIPPERY_SAND.get()));
	}
	
	/*Edited onEntityCollision to deal more damage and to make the player jump*/
	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn, InsideBlockEffectApplier effectApplier, boolean intersects)
	{
		if (EntityHelper.isImmuneToSkyCactusCollision(entityIn)) {return;}
		Vec3 motion = entityIn.getDeltaMovement();
		entityIn.hurt(level.damageSources().cactus(), 2.0F);
		entityIn.setDeltaMovement(motion.x, 1.0, motion.z);
	}
}
