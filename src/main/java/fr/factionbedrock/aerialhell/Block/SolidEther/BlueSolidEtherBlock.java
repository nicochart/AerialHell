package fr.factionbedrock.aerialhell.Block.SolidEther;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlueSolidEtherBlock extends SolidEtherBlock
{
	public BlueSolidEtherBlock(AbstractBlock.Properties properties)
	{
		super(properties);
	}
	
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		entity.fallDistance = 0.0F;
		if (entity.getMotion().y < 0.0)
		{
			entity.setMotion(entity.getMotion().mul(1.0, 0.005, 1.0));
		}
		if (!world.isRemote())
		{
			if (entity instanceof LivingEntity)
	    	{
				((LivingEntity) entity).addPotionEffect(new EffectInstance(AerialHellPotionEffects.HEAD_IN_THE_CLOUDS.get(), 10, 0));
	    	}
		}
	}

	@Override
	public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state)
	{
		super.onPlayerDestroy(worldIn, pos, state);
		worldIn.setBlockState(pos, AerialHellBlocksAndItems.WHITE_SOLID_ETHER.get().getDefaultState(), 3);
	}
}