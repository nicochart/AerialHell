package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Entity.Monster.MummyEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.AbstractSlimePirateEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Snake.AbstractSnakeEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ThornyWebBlock extends WebBlock
{
	public ThornyWebBlock(Properties properties)
	{
		super(properties);
	}
	
	private boolean isEntityImmuneToDamage(Entity entityIn)
	{
		return ((entityIn instanceof Spider) || entityIn instanceof MummyEntity || entityIn instanceof AbstractSlimePirateEntity || EntityHelper.isShadowEntity(entityIn) || EntityHelper.isMudEntity(entityIn));
	}
	
	private boolean isEntityImmuneToCollision(Entity entityIn) {return entityIn instanceof AbstractSnakeEntity || entityIn instanceof Spider || EntityHelper.isShadowEntity(entityIn);}
	
	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier)
	{
		boolean isTotallyImmune = isEntityImmuneToCollision(entity);
		
		if (!isTotallyImmune) {entity.makeStuckInBlock(state, new Vec3(0.45D, 0.25D, 0.45D));}
		if (entity instanceof LivingEntity && !isTotallyImmune)
		{
			if (!isEntityImmuneToDamage(entity)) {entity.hurt(AerialHellDamageTypes.getDamageSource(worldIn, AerialHellDamageTypes.WEB_THORNS), 2.0F);}
		}
	}
}
