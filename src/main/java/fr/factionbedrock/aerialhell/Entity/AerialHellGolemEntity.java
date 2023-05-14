package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Entity.AI.ActiveLookAtPlayerGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveRandomLookAroundGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveMeleeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveWaterAvoidingRandomWalkingGoal;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class AerialHellGolemEntity extends AbstractActivableEntity
{
	public int attackTimer;
	
    public AerialHellGolemEntity(EntityType<? extends Monster> type, Level world)
    {
        super(type, world);
        this.attackTimer = 0;
    }
    
    @Override
    protected void registerGoals()
    {
    	this.goalSelector.addGoal(1, new ActiveMeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(2, new ActiveWaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(3, new ActiveLookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new ActiveRandomLookAroundGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    }

    public float getAttackDamage()
    {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }
	
	@Override
    public void aiStep()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		super.aiStep();
    }
	
    @Override
    public boolean doHurtTarget(Entity entityIn)
    {
    	float attackDamage = this.getAttackDamage();
    	this.level.broadcastEntityEvent(this, (byte)4);
        float f1 = (int)attackDamage > 0 ? attackDamage / 2.0F + (float)this.random.nextInt((int)attackDamage) : attackDamage;
        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), f1);
        if (flag)
        {
           entityIn.setDeltaMovement(entityIn.getDeltaMovement().add(0.0D, (double)this.getYMotionOnAttack(), 0.0D)); //projection en hauteur
           this.doEnchantDamageEffects(this, entityIn);
        }

        this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }
    
    public abstract float getYMotionOnAttack();

    @Override @OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) //broadcastEntityEvent
	{
		if (id == 4)
		{
	         this.attackTimer = 10;
	         this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
	    }
		else {super.handleEntityEvent(id);}
	}

    @Override public int getMinTimeToActivate() {return 60;}
    @Override public double getMinDistanceToActivate() {return 16;}
    @Override public double getMinDistanceToDeactivate() {return 32;}
    @Override protected SoundEvent getAmbientSound() {return SoundEvents.SNOW_GOLEM_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return SoundEvents.IRON_GOLEM_HURT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.IRON_GOLEM_DEATH;}
    @Override protected void playStepSound(BlockPos pos, BlockState blockIn) {this.playSound(SoundEvents.IRON_GOLEM_STEP, 0.15F, 0.5F);}
}