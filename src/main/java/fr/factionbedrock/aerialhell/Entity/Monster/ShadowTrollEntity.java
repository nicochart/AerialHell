package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ShadowTrollEntity extends MonsterEntity
{
	public static final DataParameter<Boolean> DISAPPEARING = EntityDataManager.<Boolean>createKey(ShadowTrollEntity.class, DataSerializers.BOOLEAN);
	private int timeDisappearing;
	
    public ShadowTrollEntity(EntityType<? extends ShadowTrollEntity> type, World worldIn)
    {
        super(type, worldIn);
    }
    
    @Override
    protected void registerGoals()
    {
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 60.0F)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3F)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 24.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 4.0D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.3F);
    }
    
    @Override
    public boolean attackEntityAsMob(Entity attackedEntity)
    {
    	if (super.attackEntityAsMob(attackedEntity))
    	{
    		if (attackedEntity instanceof LivingEntity)
        	{
    			((LivingEntity) attackedEntity).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 40, 0));
        	}
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    @Override
    public void tick()
    {
    	super.tick();
    	if (rand.nextFloat() > 0.95)
    	{
    		this.addBatParticle(1);
    	}
    	
    	if (this.isDisappearing())
    	{    		
    		if (this.timeDisappearing < 95)
    		{
    			this.addBatParticle(1);
    		}
    		else if (this.timeDisappearing < 100)
    		{
    			this.addBatParticle(10);
    		}
    		else
    		{
    			this.remove();
    		}
    		this.timeDisappearing++;
    	}
    }
    
    private void addBatParticle(int number)
    {
    	for (int i=0; i<number; i++)
		{
			this.world.addParticle(AerialHellParticleTypes.SHADOW_TROLL_BAT.get(), this.getPosX() + rand.nextFloat() - 0.5, this.getPosY() + 2 * rand.nextFloat(), this.getPosZ() + rand.nextFloat() - 0.5, 2 * (rand.nextFloat()) - 0.5, -0.3D, 2 * (rand.nextFloat() - 0.5));
		}
    }
    
    @Override
    public void livingTick()
    {
    	super.livingTick();
    	if (this.isInDaylight() /*|| this.getBrightness() >= 2.5f*/ /*|| this.world.getLight(this.getPosition().down()) > 10*/)
    	{
    		if (!this.isDisappearing())
    		{
    			this.playSound(AerialHellSoundEvents.ENTITY_SHADOW_TROLL_DEATH.get(), 1.0F, 0.9F);
    			this.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 100, 10, true, false)));
    			this.setDisappearing(true);
    		}
    	}
    }
    
    @Override
    protected void collideWithEntity(Entity entityIn)
    {
    	if (entityIn instanceof LivingEntity)
    	{
    		((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 60, 0));
    	}
        super.collideWithEntity(entityIn);
    }
    
    @Override
    protected void registerData()
    {
        super.registerData();
        this.dataManager.register(DISAPPEARING, false);
    }
    
    @Override
    public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
        compound.putBoolean("Disappearing", this.isDisappearing());
    }

    @Override
    public void readAdditional(CompoundNBT compound)
    {
        super.readAdditional(compound);
        this.setDisappearing(compound.getBoolean("Disappearing"));
    }
    
    public boolean isDisappearing()
    {
    	return this.dataManager.get(DISAPPEARING);
    }
    
    public void setDisappearing(boolean flag)
    {
    	this.dataManager.set(DISAPPEARING, flag);
    }
    
    public int getTimeDisappearing()
    {
    	return this.timeDisappearing;
    }
    
    @Override
    protected SoundEvent getAmbientSound()
    {
        return AerialHellSoundEvents.ENTITY_SHADOW_TROLL_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
    	return AerialHellSoundEvents.ENTITY_SHADOW_TROLL_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound()
    {
    	return AerialHellSoundEvents.ENTITY_SHADOW_TROLL_DEATH.get();
    }
}