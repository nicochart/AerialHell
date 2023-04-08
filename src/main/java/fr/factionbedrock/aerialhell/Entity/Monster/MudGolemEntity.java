package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class MudGolemEntity extends AerialHellGolemEntity
{
	private float timeClosePlayer = 0.0F;
	
    public MudGolemEntity(EntityType<? extends MonsterEntity> type, World world)
    {
        super(type, world);
        this.experienceValue = 12;
    }
    
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 60.0D)
                .createMutableAttribute(Attributes.ARMOR, 3.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 7.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23D);
    }
    
    @Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		boolean flag = super.attackEntityFrom(source, amount);
		if (flag)
		{
			Entity immediateSourceEntity = source.getImmediateSource();
			Entity trueSourceEntity = source.getTrueSource();
			if (trueSourceEntity instanceof LivingEntity && !(immediateSourceEntity instanceof AbstractArrowEntity))
			{
				if (!(trueSourceEntity instanceof PlayerEntity && ((PlayerEntity)trueSourceEntity).isCreative()))
				{
					this.setAttackTarget((LivingEntity) trueSourceEntity);
				}
			}
		}
		return flag;
	}
    
    @Override
    protected void registerGoals()
    {
    	super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AnimalEntity.class, true));
    }
	
	@Override public float getYMotionOnAttack() {return 0.4F;}
    @Override public boolean canDespawn(double distanceToClosestPlayer) {return false;}
}