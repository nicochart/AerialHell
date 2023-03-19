package fr.factionbedrock.aerialhell.Block.SolidEther;

import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PurpleSolidEtherBlock extends SolidEtherBlock
{
	public PurpleSolidEtherBlock(Properties properties) {super(properties);}
	
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		super.onEntityCollision(state, world, pos, entity);
		if (!world.isRemote() && entity instanceof LivingEntity)
		{
			if (canEntityCollide(entity))
			{
				((LivingEntity) entity).addPotionEffect(new EffectInstance(AerialHellPotionEffects.SHADOW_IMMUNITY.get(), 30, 0, true, true));
			}
			else
			{
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 40, 0, false, false));
			}
		}
	}
}