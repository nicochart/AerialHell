package fr.factionbedrock.aerialhell.Entity.Monster.Shadow;

import com.google.common.collect.ImmutableList;
import fr.factionbedrock.aerialhell.Entity.AI.FleeBlockGoal;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import java.util.List;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class ShadowTrollEntity extends Monster
{
	public static final EntityDataAccessor<Boolean> DISAPPEARING = SynchedEntityData.<Boolean>defineId(ShadowTrollEntity.class, EntityDataSerializers.BOOLEAN);
	private int timeDisappearing;
	
    public ShadowTrollEntity(EntityType<? extends ShadowTrollEntity> type, Level world) {super(type, world);}
    
    @Override
    protected void registerGoals()
    {
        List<Block> blocksToAvoid = ImmutableList.of(AerialHellBlocks.VOLUCITE_TORCH, AerialHellBlocks.VOLUCITE_WALL_TORCH);
        this.goalSelector.addGoal(1, new FleeBlockGoal<>(this, blocksToAvoid, 1.0D, 1.2D));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.6D));
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 60.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.FOLLOW_RANGE, 24.0D)
                .add(Attributes.ATTACK_DAMAGE, 12.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.3F);
    }
    
    @Override
    public boolean doHurtTarget(ServerLevel serverWorld, Entity attackedEntity)
    {
    	if (super.doHurtTarget(serverWorld, attackedEntity))
    	{
    		if (attackedEntity instanceof LivingEntity attackedLivingEntity)
            {
                if (!EntityHelper.isLivingEntityShadowImmune((attackedLivingEntity)))
                {
                    if (!(attackedLivingEntity.hasEffect(MobEffects.BLINDNESS)))
                    {
                        attackedLivingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 35, 0));
                    }
                    else if (!attackedLivingEntity.hasEffect(AerialHellMobEffects.VULNERABILITY))
                    {
                        attackedLivingEntity.addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY, 60, 0));
                    }
                    else
                    {
                        attackedLivingEntity.addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY, 120, 0));
                    }
                }
                else //attacked entity is shadow immune
                {
                    attackedLivingEntity.addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY, 50, 0));
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
    
    @Override public void aiStep()
    {
    	super.aiStep();
    	if (this.isSunBurnTick() /*|| this.getBrightness() >= 2.5f*/ /*|| this.level.getLight(this.getPosition().below()) > 10*/)
    	{
    		if (!this.isDisappearing())
    		{
    			this.playSound(AerialHellSoundEvents.ENTITY_SHADOW_TROLL_DEATH, 1.0F, 0.9F);
    			this.addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.SLOWNESS, 100, 10, true, false)));
    			this.setDisappearing(true);
    		}
    	}
    }
    
    @Override protected void doPush(Entity entityIn)
    {
    	if (entityIn instanceof LivingEntity && !EntityHelper.isLivingEntityShadowImmune(((LivingEntity) entityIn)))
    	{
    		((LivingEntity) entityIn).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0));
    	}
        super.doPush(entityIn);
    }
    
    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(DISAPPEARING, false);
    }

    @Override protected void addAdditionalSaveData(ValueOutput view)
    {
        super.addAdditionalSaveData(view);
        view.putBoolean("Disappearing", this.isDisappearing());
    }

    @Override protected void readAdditionalSaveData(ValueInput view)
    {
        super.readAdditionalSaveData(view);
        this.setDisappearing(view.getBooleanOr("Disappearing", false));
    }
    
    public boolean isDisappearing() {return this.getEntityData().get(DISAPPEARING);}
    public void setDisappearing(boolean flag) {this.getEntityData().set(DISAPPEARING, flag);}
    public int getTimeDisappearing() {return this.timeDisappearing;}
    
    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_SHADOW_TROLL_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_SHADOW_TROLL_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_SHADOW_TROLL_DEATH;}
}