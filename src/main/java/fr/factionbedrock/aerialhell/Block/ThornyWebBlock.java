package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.damagesource.DamageSource;
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
		return ((entityIn instanceof Spider) || EntityHelper.isShadowEntity(entityIn) || EntityHelper.isMudEntity(entityIn));
	}
	
	private boolean isEntityImmuneToCollision(Entity entityIn) {return entityIn instanceof Spider || EntityHelper.isShadowEntity(entityIn);}
	
	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn)
	{
		boolean isTotallyImmune = isEntityImmuneToCollision(entityIn);
		
		if (!isTotallyImmune) {entityIn.makeStuckInBlock(state, new Vec3(0.45D, 0.25D, 0.45D));}
		if (entityIn instanceof LivingEntity && !isTotallyImmune)
		{
			if (!isEntityImmuneToDamage(entityIn)) {((LivingEntity) entityIn).hurt(new DamageSource("web_thorns"), 2.0F);}
		}
	}
}
