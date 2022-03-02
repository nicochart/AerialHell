package fr.factionbedrock.aerialhell.Block.SolidEther;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class GreenSolidEtherBlock extends SolidEtherBlock
{
	protected static VoxelShape SHAPE = VoxelShapes.empty();

	public GreenSolidEtherBlock(AbstractBlock.Properties properties)
	{
		super(properties);
	}
	
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		entity.fallDistance = 0.0F;
		Vector3d motion = entity.getMotion();

		if (entity.isSneaking())
		{
			if (motion.y < 0)
			{
				entity.setMotion(motion.mul(0.9, 0.005, 0.9));
			}
			else if (motion.y > 0)
			{
				if (motion.x > 0.3 || motion.z > 0.3)
				{
					entity.setMotion(motion.mul(0.9, 0.99, 0.9));
					entity.attackEntityFrom(DamageSource.FLY_INTO_WALL.setDamageBypassesArmor(), 1.0F);
				}
				else
				{
					entity.setMotion(motion.mul(1.1, 0.99, 1.1));
				}
			}
			else //motion.y == 0
			{
				entity.setMotion(motion.mul(0.9, 1.0, 0.9));
			}
			return;
		}
		else
		{
			if (motion.x == 0 && motion.z == 0)
			{
				entity.setMotion(entity.getForward());
			}
			else if (motion.x > 0.3 || motion.z > 0.3)
			{
				entity.setMotion(-1*motion.x, 1.3, -1*motion.z);
				entity.attackEntityFrom(DamageSource.FLY_INTO_WALL.setDamageBypassesArmor(), 2.0F);
			}
			else
			{
				entity.setMotion(-1.3*motion.x, 1.3, -1.3*motion.z);
			}
		}
		
		
		
		if (world.isRemote)
		{
			for (int count = 0; count < 5; count++)
			{
				double xOffset = pos.getX() + world.rand.nextDouble();
				double yOffset = pos.getY() + world.rand.nextDouble();
				double zOffset = pos.getZ() + world.rand.nextDouble();
				
				world.addParticle(ParticleTypes.COMPOSTER, xOffset, yOffset, zOffset, 0.0, 0.0, 0.0);
			}
		}
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return SHAPE;
	}
}