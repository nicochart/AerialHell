package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Entity.AI.ActiveLookAtPlayerGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveRandomLookAroundGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveMeleeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveWaterAvoidingRandomWalkingGoal;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AerialHellGolemEntity extends AbstractActivableEntity
{
	public int attackTimer;
	
    public AerialHellGolemEntity(EntityType<? extends HostileEntity> type, World world)
    {
        super(type, world);
        this.attackTimer = 0;
    }
    
    @Override
    protected void initGoals()
    {
    	this.goalSelector.add(1, new ActiveMeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.add(2, new ActiveWaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.add(3, new ActiveLookAtPlayerGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(3, new ActiveRandomLookAroundGoal(this));
        this.targetSelector.add(0, new RevengeGoal(this));
    }

    public float getAttackDamage()
    {
        return (float)this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);
    }
	
	@Override
    public void tickMovement()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		super.tickMovement();
    }
	
    @Override public boolean tryAttack(ServerWorld serverWorld, Entity attackedEntity)
    {
        DamageSource damagesource = this.getDamageSources().mobAttack(this);
        float attackDamage = this.getAttackDamage();
        this.getEntityWorld().sendEntityStatus(this, (byte)4);
        float amount = (int)attackDamage > 0 ? attackDamage / 2.0F + (float)this.random.nextInt((int)attackDamage) : attackDamage;
        boolean flag = attackedEntity.damage(serverWorld, damagesource, amount);
        if (flag)
        {
            attackedEntity.setVelocity(attackedEntity.getVelocity().add(0.0D, (double)this.getYMotionOnAttack(), 0.0D)); //projection en hauteur
            EnchantmentHelper.onTargetDamaged(serverWorld, attackedEntity, damagesource);
        }

        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }

    @Override public boolean damage(ServerWorld serverWorld, DamageSource source, float amount)
    {
        boolean flag = super.damage(serverWorld, source, amount) && this.updateTargetOnHurtByLivingEntity();
        if (flag)
        {
            Entity immediateSourceEntity = source.getSource();
            Entity trueSourceEntity = source.getAttacker();
            if (trueSourceEntity instanceof LivingEntity && !(immediateSourceEntity instanceof PersistentProjectileEntity) && !EntityHelper.isCreativePlayer(trueSourceEntity))
            {
                this.setTarget((LivingEntity) trueSourceEntity);
            }
        }
        return flag;
    }
    
    public abstract float getYMotionOnAttack();
    public abstract boolean updateTargetOnHurtByLivingEntity();

    @Override
	public void handleStatus(byte id) //broadcastEntityEvent
	{
		if (id == 4)
		{
	         this.attackTimer = 10;
	         this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
	    }
		else {super.handleStatus(id);}
	}

    @Override public int getMinTimeToActivate() {return 60;}
    @Override public double getMinDistanceToActivate() {return 16;}
    @Override public double getMinDistanceToDeactivate() {return 32;}
    @Override protected SoundEvent getAmbientSound() {return SoundEvents.ENTITY_SNOW_GOLEM_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return SoundEvents.ENTITY_IRON_GOLEM_HURT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.ENTITY_IRON_GOLEM_DEATH;}
    @Override protected void playStepSound(BlockPos pos, BlockState blockIn) {this.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP, 0.15F, 0.5F);}
}