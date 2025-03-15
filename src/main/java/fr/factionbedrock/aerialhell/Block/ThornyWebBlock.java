package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Entity.Monster.MummyEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.AbstractSlimePirateEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Snake.AbstractSnakeEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CobwebBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ThornyWebBlock extends CobwebBlock
{
	public ThornyWebBlock(AbstractBlock.Settings settings)
	{
		super(settings);
	}
	
	private boolean isEntityImmuneToDamage(Entity entityIn)
	{
		return ((entityIn instanceof SpiderEntity) || entityIn instanceof MummyEntity || entityIn instanceof AbstractSlimePirateEntity || EntityHelper.isShadowEntity(entityIn) || EntityHelper.isMudEntity(entityIn));
	}
	
	private boolean isEntityImmuneToCollision(Entity entityIn) {return entityIn instanceof AbstractSnakeEntity || entityIn instanceof SpiderEntity || EntityHelper.isShadowEntity(entityIn);}
	
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entityIn)
	{
		boolean isTotallyImmune = isEntityImmuneToCollision(entityIn);
		
		if (!isTotallyImmune) {entityIn.slowMovement(state, new Vec3d(0.45D, 0.25D, 0.45D));}
		if (world instanceof ServerWorld serverWorld && entityIn instanceof LivingEntity && !isTotallyImmune)
		{
			if (!isEntityImmuneToDamage(entityIn)) {entityIn.damage(serverWorld, AerialHellDamageTypes.getDamageSource(world, AerialHellDamageTypes.WEB_THORNS), 2.0F);}
		}
	}
}
