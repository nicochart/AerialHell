package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Level;

public class MudGolemEntity extends AerialHellGolemEntity
{
	private float timeClosePlayer = 0.0F;
	
    public MudGolemEntity(EntityType<? extends Monster> type, Level world)
    {
        super(type, world);
        this.xpReward = 12;
    }
    
    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.ARMOR, 3.0D)
                .add(Attributes.ATTACK_DAMAGE, 7.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D);
    }
    
    @Override
	public boolean hurt(DamageSource source, float amount)
	{
		boolean flag = super.hurt(source, amount);
		if (flag)
		{
			Entity immediateSourceEntity = source.getDirectEntity();
			Entity trueSourceEntity = source.getEntity();
			if (trueSourceEntity instanceof LivingEntity && !(immediateSourceEntity instanceof AbstractArrow))
			{
				if (!(trueSourceEntity instanceof Player && ((Player)trueSourceEntity).isCreative()))
				{
					this.setTarget((LivingEntity) trueSourceEntity);
				}
			}
		}
		return flag;
	}
    
    @Override
    protected void registerGoals()
    {
    	super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Animal.class, true));
    }
	
	@Override public float getYMotionOnAttack() {return 0.4F;}
    @Override public boolean removeWhenFarAway(double distanceToClosestPlayer) {return false;}
}