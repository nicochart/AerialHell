package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class SkyCactusBlock extends CactusBlock
{
	public SkyCactusBlock(AbstractBlock.Settings settings)
	{
		super(settings);
	}
	
	/*Edited isValidPosition to make the cactus can grow on slippery_sand and on itself*/
	@Override
	public boolean canPlaceAt(BlockState state, WorldView level, BlockPos pos)
	{
		for(Direction direction : Direction.Type.HORIZONTAL)
	    {
			BlockState blockstate = level.getBlockState(pos.offset(direction));
	        if (blockstate.isSolid() || level.getFluidState(pos.offset(direction)).isIn(FluidTags.LAVA)) {return false;}
	    }
	    BlockState bottom_blockstate = level.getBlockState(pos.down());
	    return (bottom_blockstate.getBlock().equals(AerialHellBlocks.SKY_CACTUS) || bottom_blockstate.getBlock().equals(AerialHellBlocks.VIBRANT_SKY_CACTUS) || bottom_blockstate.getBlock().equals(AerialHellBlocks.SLIPPERY_SAND));
	}
	
	/*Edited onEntityCollision to deal more damage and to make the player jump*/
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler, boolean intersects)
	{
		if (EntityHelper.isImmuneToSkyCactusCollision(entity)) {return;}
		Vec3d motion = entity.getVelocity();
		entity.serverDamage(world.getDamageSources().cactus(), 2.0F);
		entity.setVelocity(motion.x, 1.0, motion.z);
	}
}
