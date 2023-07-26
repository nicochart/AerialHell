package fr.factionbedrock.aerialhell.Entity.Bosses;

import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.AbstractBossEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.MudSpectralSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;

public class MudCycleMageEntity extends AbstractBossEntity
{
	private float damageAmountSinceLastSummon;
	public MudCycleMageEntity(EntityType<? extends MudCycleMageEntity> type, Level world)
	{
		super(type, world);
		this.damageAmountSinceLastSummon = 0;
	}
	
	@Override
    protected void registerGoals()
    {
		this.goalSelector.addGoal(2, new RestrictSunGoal(this));
	    this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
	    this.goalSelector.addGoal(3, new ActiveMeleeAttackGoal(this, 1.25D, false));
	    this.goalSelector.addGoal(5, new ActiveWaterAvoidingRandomWalkingGoal(this, 1.0D));
	    this.goalSelector.addGoal(6, new ActiveLookAtPlayerGoal(this, Player.class, 8.0F));
	    this.goalSelector.addGoal(6, new ActiveRandomLookAroundGoal(this));
	    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	    this.targetSelector.addGoal(2, new ActiveNearestAttackableTargetGoal<>(this, Player.class, true));
	    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TornSpiritEntity.class, true));
	    this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
    }
	
	public static AttributeSupplier.Builder registerAttributes()
    {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 200.0D)
				.add(Attributes.FOLLOW_RANGE, 32.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ATTACK_DAMAGE, 3.0D);
    }
	
	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		boolean flag = super.hurt(source, amount);
		if (flag) {this.damageAmountSinceLastSummon += amount;}
		return flag;
	}
	
	@Override
	public void tick()
	{		
		super.tick();
		if (this.isActive() && (this.tickCount % 600 == 0 || this.damageAmountSinceLastSummon > 65))
		{
			this.damageAmountSinceLastSummon = 0;
			if (this.getHealth() < this.getMaxHealth() / 2)
			{
				this.summonSpectralSoldiersAndGolems();
			}
			else
			{
				this.summonSpectralSoldiers();
			}
			if (this.level().isClientSide())
			{
				this.spawnSmokeParticle();
			}
			this.playSound(SoundEvents.EVOKER_PREPARE_SUMMON, 1.5F, 0.95F + random.nextFloat() * 0.1F);
		}
	}
	
	private void summonSpectralSoldiers()
	{		
		double x,y,z;
		x = this.getX(); y = this.getY(); z = this.getZ();
		
		MudSpectralSoldierEntity spectralSoldier1 = AerialHellEntities.MUD_SPECTRAL_SOLDIER.get().create(this.level());
		MudSpectralSoldierEntity spectralSoldier2 = AerialHellEntities.MUD_SPECTRAL_SOLDIER.get().create(this.level());
		MudSpectralSoldierEntity spectralSoldier3 = AerialHellEntities.MUD_SPECTRAL_SOLDIER.get().create(this.level());
		spectralSoldier1.setPos(x, y, z);
		spectralSoldier2.setPos(x, y, z);
		spectralSoldier3.setPos(x, y, z);
		spectralSoldier1.setDeltaMovement(0.5, 0, 0);//(1, 0, 0);
		spectralSoldier2.setDeltaMovement(-0.250000112583355, 0, 0.4333882291756956);//(-0.50000022516671, 0, 0.8667764583513912);
		spectralSoldier3.setDeltaMovement(-0.250000112583355, 0, -0.4333882291756956);//(-0.50000022516671, 0, -0.8667764583513912);
		spectralSoldier1.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
		spectralSoldier2.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
		spectralSoldier3.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
		spectralSoldier1.reassessWeaponGoal();
		spectralSoldier2.reassessWeaponGoal();
		spectralSoldier3.reassessWeaponGoal();
		
		this.level().addFreshEntity(spectralSoldier1);
		this.level().addFreshEntity(spectralSoldier2);
		this.level().addFreshEntity(spectralSoldier3);
	}
	
	private void summonSpectralSoldiersAndGolems()
	{		
		double x,y,z;
		x = this.getX(); y = this.getY(); z = this.getZ();
		
		LivingEntity spectralEntity1;
		LivingEntity spectralEntity2;
		LivingEntity spectralEntity3;
		
		if (random.nextInt(2) == 0)
		{
			spectralEntity1 = AerialHellEntities.MUD_SPECTRAL_GOLEM.get().create(this.level());
			spectralEntity2 = AerialHellEntities.MUD_SPECTRAL_SOLDIER.get().create(this.level());
			spectralEntity3 = AerialHellEntities.MUD_SPECTRAL_SOLDIER.get().create(this.level());
		}
		else
		{
			spectralEntity1 = AerialHellEntities.MUD_SPECTRAL_GOLEM.get().create(this.level());
			spectralEntity2 = AerialHellEntities.MUD_SPECTRAL_GOLEM.get().create(this.level());
			spectralEntity3 = AerialHellEntities.MUD_SPECTRAL_SOLDIER.get().create(this.level());
		}
		spectralEntity1.setPos(x, y, z);
		spectralEntity2.setPos(x, y, z);
		spectralEntity3.setPos(x, y, z);
		spectralEntity1.setDeltaMovement(0.5, 0, 0);//(1, 0, 0);
		spectralEntity2.setDeltaMovement(-0.250000112583355, 0, 0.4333882291756956);//(-0.50000022516671, 0, 0.8667764583513912);
		spectralEntity3.setDeltaMovement(-0.250000112583355, 0, -0.4333882291756956);//(-0.50000022516671, 0, -0.8667764583513912);
		
		this.level().addFreshEntity(spectralEntity1);
		this.level().addFreshEntity(spectralEntity2);
		this.level().addFreshEntity(spectralEntity3);
	}
	
	public void spawnSmokeParticle()
	{
		for(int i = 0; i < 30; ++i)
        {
        	double d0 = this.random.nextGaussian() * 0.02D;
        	double d1 = this.random.nextGaussian() * 0.02D;
        	double d2 = this.random.nextGaussian() * 0.02D;
        	this.level().addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(1.0D) - d0 * 10.0D, this.getRandomY() - d1 * 10.0D, this.getRandomZ(1.0D) - d2 * 10.0D, 0.25 * (random.nextFloat() - 0.5), 0.3D, 0.25 * (random.nextFloat() - 0.5));
        }
	}
	
	@Override protected SoundEvent getAmbientSound() {return SoundEvents.WITHER_SKELETON_AMBIENT;}
	@Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.WITHER_SKELETON_HURT;}
	@Override protected SoundEvent getDeathSound() {return SoundEvents.WITHER_SKELETON_DEATH;}
}
