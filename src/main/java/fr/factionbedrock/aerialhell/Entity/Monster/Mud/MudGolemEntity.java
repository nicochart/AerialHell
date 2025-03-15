package fr.factionbedrock.aerialhell.Entity.Monster.Mud;

import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class MudGolemEntity extends AerialHellGolemEntity
{
	private float timeClosePlayer = 0.0F;
	
    public MudGolemEntity(EntityType<? extends HostileEntity> type, World world)
    {
        super(type, world);
        this.experiencePoints = 12;
    }
    
    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 60.0D)
                .add(EntityAttributes.ARMOR, 3.0D)
                .add(EntityAttributes.ATTACK_DAMAGE, 7.0D)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.23D);
    }
    
    @Override
	public boolean damage(ServerWorld serverWorld, DamageSource source, float amount)
	{
		boolean flag = super.damage(serverWorld, source, amount);
		if (flag)
		{
			Entity immediateSourceEntity = source.getSource();
			Entity trueSourceEntity = source.getAttacker();
			if (trueSourceEntity instanceof LivingEntity && !(immediateSourceEntity instanceof PersistentProjectileEntity))
			{
				if (!(trueSourceEntity instanceof PlayerEntity playerEntity && playerEntity.isCreative()))
				{
					this.setTarget((LivingEntity) trueSourceEntity);
				}
			}
		}
		return flag;
	}
    
    @Override
    protected void initGoals()
    {
    	super.initGoals();
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, AnimalEntity.class, true));
    }
	
	@Override public float getYMotionOnAttack() {return 0.4F;}
    @Override public boolean canImmediatelyDespawn(double distanceToClosestPlayer) {return false;}
}