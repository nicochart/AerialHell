package fr.factionbedrock.aerialhell.Entity.Monster.Shadow;

import com.google.common.collect.ImmutableList;
import fr.factionbedrock.aerialhell.Entity.AI.FleeBlockGoal;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

import java.util.List;

public class ShadowTrollEntity extends HostileEntity
{
	public static final TrackedData<Boolean> DISAPPEARING = DataTracker.<Boolean>registerData(ShadowTrollEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private int timeDisappearing;
	
    public ShadowTrollEntity(EntityType<? extends ShadowTrollEntity> type, World world) {super(type, world);}
    
    @Override
    protected void initGoals()
    {
        List<Block> blocksToAvoid = ImmutableList.of(AerialHellBlocks.VOLUCITE_TORCH, AerialHellBlocks.VOLUCITE_WALL_TORCH);
        this.goalSelector.add(1, new FleeBlockGoal<>(this, blocksToAvoid, 1.0D, 1.2D));
		this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
		this.goalSelector.add(3, new MeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.6D));
    }

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 60.0F)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3F)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 24.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 12.0D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.3F);
    }
    
    @Override
    public boolean tryAttack(Entity attackedEntity)
    {
    	if (super.tryAttack(attackedEntity))
    	{
    		if (attackedEntity instanceof LivingEntity attackedLivingEntity)
            {
                if (!EntityHelper.isLivingEntityShadowImmune((attackedLivingEntity)))
                {
                    if (!(attackedLivingEntity.hasStatusEffect(StatusEffects.BLINDNESS)))
                    {
                        attackedLivingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 35, 0));
                    }
                    else if (!attackedLivingEntity.hasStatusEffect(AerialHellMobEffects.VULNERABILITY))
                    {
                        attackedLivingEntity.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.VULNERABILITY, 60, 0));
                    }
                    else
                    {
                        attackedLivingEntity.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.VULNERABILITY, 120, 0));
                    }
                }
                else //attacked entity is shadow immune
                {
                    attackedLivingEntity.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.VULNERABILITY, 50, 0));
                }
            }
    		return true;
    	}
    	else {return false;}
    }
    
    @Override public void tick()
    {
    	super.tick();
    	if (random.nextFloat() > 0.95) {EntityHelper.addBatParticle(this, this.random, 1);}
    	
    	if (this.isDisappearing())
    	{    		
    		if (this.timeDisappearing < 95) {EntityHelper.addBatParticle(this, this.random, 1);}
    		else if (this.timeDisappearing < 100) {EntityHelper.addBatParticle(this, this.random, 10);}
    		else {this.discard();}
    		this.timeDisappearing++;
    	}
    }
    
    @Override public void tickMovement()
    {
    	super.tickMovement();
    	if (this.isAffectedByDaylight() /*|| this.getBrightness() >= 2.5f*/ /*|| this.level.getLight(this.getPosition().below()) > 10*/)
    	{
    		if (!this.isDisappearing())
    		{
    			this.playSound(AerialHellSoundEvents.ENTITY_SHADOW_TROLL_DEATH, 1.0F, 0.9F);
    			this.addStatusEffect(new StatusEffectInstance(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 10, true, false)));
    			this.setDisappearing(true);
    		}
    	}
    }
    
    @Override protected void pushAway(Entity entityIn)
    {
    	if (entityIn instanceof LivingEntity && !EntityHelper.isLivingEntityShadowImmune(((LivingEntity) entityIn)))
    	{
    		((LivingEntity) entityIn).addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 0));
    	}
        super.pushAway(entityIn);
    }
    
    @Override protected void initDataTracker(DataTracker.Builder builder)
    {
        super.initDataTracker(builder);
        builder.add(DISAPPEARING, false);
    }
    
    @Override public void writeCustomDataToNbt(NbtCompound nbt)
    {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Disappearing", this.isDisappearing());
    }

    @Override public void readCustomDataFromNbt(NbtCompound nbt)
    {
        super.readCustomDataFromNbt(nbt);
        this.setDisappearing(nbt.getBoolean("Disappearing"));
    }
    
    public boolean isDisappearing() {return this.getDataTracker().get(DISAPPEARING);}
    public void setDisappearing(boolean flag) {this.getDataTracker().set(DISAPPEARING, flag);}
    public int getTimeDisappearing() {return this.timeDisappearing;}
    
    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_SHADOW_TROLL_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_SHADOW_TROLL_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_SHADOW_TROLL_DEATH;}
}