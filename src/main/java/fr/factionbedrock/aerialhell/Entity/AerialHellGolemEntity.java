package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Entity.AI.ActiveLookAtPlayerGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveLookRandomlyGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveMeleeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveWaterAvoidingRandomWalkingGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class AerialHellGolemEntity extends AbstractActivableEntity
{
	public int attackTimer;
	
    public AerialHellGolemEntity(EntityType<? extends MonsterEntity> type, World world)
    {
        super(type, world);
        this.attackTimer = 0;
    }
    
    @Override
    protected void registerGoals()
    {
    	this.goalSelector.addGoal(1, new ActiveMeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(2, new ActiveWaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(3, new ActiveLookAtPlayerGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(3, new ActiveLookRandomlyGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    }

    public float getAttackDamage()
    {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }
	
	@Override
    public void livingTick()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		super.livingTick();
    }
	
    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
    	float attackDamage = this.getAttackDamage();
    	this.world.setEntityState(this, (byte)4);
        float f1 = (int)attackDamage > 0 ? attackDamage / 2.0F + (float)this.rand.nextInt((int)attackDamage) : attackDamage;
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f1);
        if (flag)
        {
           entityIn.setMotion(entityIn.getMotion().add(0.0D, (double)this.getYMotionOnAttack(), 0.0D)); //projection en hauteur
           this.applyEnchantments(this, entityIn);
        }

        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }
    
    public abstract float getYMotionOnAttack();
	
    @Override @OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 4)
		{
	         this.attackTimer = 10;
	         this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
	    }
		else {super.handleStatusUpdate(id);}
	}

    @Override public int getMinTimeToActivate() {return 60;}
    @Override public double getMinDistanceToActivate() {return 16;}
    @Override public double getMinDistanceToDeactivate() {return 32;}
    @Override protected SoundEvent getAmbientSound() {return SoundEvents.ENTITY_SNOW_GOLEM_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return SoundEvents.ENTITY_IRON_GOLEM_HURT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.ENTITY_IRON_GOLEM_DEATH;}
    @Override protected void playStepSound(BlockPos pos, BlockState blockIn) {this.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP, 0.15F, 0.5F);}
}