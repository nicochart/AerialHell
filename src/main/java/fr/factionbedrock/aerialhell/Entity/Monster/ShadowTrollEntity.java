package fr.factionbedrock.aerialhell.Entity.Monster;

import com.google.common.collect.ImmutableList;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.AI.FleeBlockGoal;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.Block;
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
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.List;

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
        List<Block> blocksToAvoid = ImmutableList.of(AerialHellBlocksAndItems.VOLUCITE_TORCH.get(), AerialHellBlocksAndItems.VOLUCITE_WALL_TORCH.get());
        this.goalSelector.addGoal(1, new FleeBlockGoal<>(this, blocksToAvoid, 1.0D, 1.2D));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
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
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 8.0D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.3F);
    }
    
    @Override
    public boolean attackEntityAsMob(Entity attackedEntity)
    {
    	if (super.attackEntityAsMob(attackedEntity))
    	{
    		if (attackedEntity instanceof LivingEntity)
            {
                if (!EntityHelper.isLivingEntityShadowImmune(((LivingEntity) attackedEntity)))
                {
                    if (!((LivingEntity) attackedEntity).isPotionActive(Effects.BLINDNESS))
                    {
                        ((LivingEntity) attackedEntity).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 35, 0));
                    }
                    else if (!((LivingEntity) attackedEntity).isPotionActive(AerialHellPotionEffects.VULNERABILITY.get()))
                    {
                        ((LivingEntity) attackedEntity).addPotionEffect(new EffectInstance(AerialHellPotionEffects.VULNERABILITY.get(), 60, 0));
                    }
                    else
                    {
                        ((LivingEntity) attackedEntity).addPotionEffect(new EffectInstance(AerialHellPotionEffects.VULNERABILITY.get(), 120, 0));
                    }
                }
                else //attacked entity is shadow immune
                {
                    ((LivingEntity) attackedEntity).addPotionEffect(new EffectInstance(AerialHellPotionEffects.VULNERABILITY.get(), 50, 0));
                }
            }
    		return true;
    	}
    	else {return false;}
    }
    
    @Override public void tick()
    {
    	super.tick();
    	if (rand.nextFloat() > 0.95) {EntityHelper.addBatParticle(this, this.rand, 1);}
    	
    	if (this.isDisappearing())
    	{    		
    		if (this.timeDisappearing < 95) {EntityHelper.addBatParticle(this, this.rand, 1);}
    		else if (this.timeDisappearing < 100) {EntityHelper.addBatParticle(this, this.rand, 10);}
    		else {this.remove();}
    		this.timeDisappearing++;
    	}
    }
    
    @Override public void livingTick()
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
    
    @Override protected void collideWithEntity(Entity entityIn)
    {
    	if (entityIn instanceof LivingEntity && !EntityHelper.isLivingEntityShadowImmune(((LivingEntity) entityIn)))
    	{
    		((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 60, 0));
    	}
        super.collideWithEntity(entityIn);
    }
    
    @Override protected void registerData()
    {
        super.registerData();
        this.dataManager.register(DISAPPEARING, false);
    }
    
    @Override public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
        compound.putBoolean("Disappearing", this.isDisappearing());
    }

    @Override public void readAdditional(CompoundNBT compound)
    {
        super.readAdditional(compound);
        this.setDisappearing(compound.getBoolean("Disappearing"));
    }
    
    public boolean isDisappearing() {return this.dataManager.get(DISAPPEARING);}
    public void setDisappearing(boolean flag) {this.dataManager.set(DISAPPEARING, flag);}
    public int getTimeDisappearing() {return this.timeDisappearing;}
    
    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_SHADOW_TROLL_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_SHADOW_TROLL_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_SHADOW_TROLL_DEATH.get();}
}