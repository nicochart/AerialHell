package fr.factionbedrock.aerialhell.Entity.Bosses;

import fr.factionbedrock.aerialhell.Entity.AbstractBossEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.MudSpectralSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.FleeSunGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class MudCycleMageEntity extends AbstractBossEntity
{
	public MudCycleMageEntity(EntityType<? extends MudCycleMageEntity> type, World world)
	{
		super(type, world);
	}
	
	@Override
    protected void registerGoals()
    {
		this.goalSelector.addGoal(2, new RestrictSunGoal(this));
	    this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
	    this.goalSelector.addGoal(3, new BossMeleeAttackGoal(this, 1.25D, false));
	    this.goalSelector.addGoal(5, new BossWaterAvoidingRandomWalkingGoal(this, 1.0D));
	    this.goalSelector.addGoal(6, new BossLookAtPlayerGoal(this, PlayerEntity.class, 8.0F));
	    this.goalSelector.addGoal(6, new BossLookRandomlyGoal(this));
	    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	    this.targetSelector.addGoal(2, new BossNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TornSpiritEntity.class, true));
	    this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
    }
	
	public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
		return MonsterEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 200.0D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 32.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D);
    }
	
	@Override
	public void tick()
	{		
		super.tick();
		if (this.isActive() && (this.ticksExisted % 600 == 0 || (this.ticksExisted % 300 == 0 && rand.nextInt(2) == 0)))
		{
			if (this.getHealth() < this.getMaxHealth() / 2)
			{
				this.summonSpectralSoldiersAndGolems();
			}
			else
			{
				this.summonSpectralSoldiers();
			}
			if (this.world.isRemote)
			{
				this.spawnSmokeParticle();
			}
			this.playSound(SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, 1.5F, 0.95F + rand.nextFloat() * 0.1F);
		}
	}
	
	private void summonSpectralSoldiers()
	{		
		double x,y,z;
		x = this.getPosX(); y = this.getPosY(); z = this.getPosZ();
		
		MudSpectralSoldierEntity spectralSoldier1 = AerialHellEntities.MUD_SPECTRAL_SOLDIER_TYPE.create(this.world);
		MudSpectralSoldierEntity spectralSoldier2 = AerialHellEntities.MUD_SPECTRAL_SOLDIER_TYPE.create(this.world);
		MudSpectralSoldierEntity spectralSoldier3 = AerialHellEntities.MUD_SPECTRAL_SOLDIER_TYPE.create(this.world);
		spectralSoldier1.setPosition(x, y, z);
		spectralSoldier2.setPosition(x, y, z);
		spectralSoldier3.setPosition(x, y, z);
		spectralSoldier1.setMotion(0.5, 0, 0);//(1, 0, 0);
		spectralSoldier2.setMotion(-0.250000112583355, 0, 0.4333882291756956);//(-0.50000022516671, 0, 0.8667764583513912);
		spectralSoldier3.setMotion(-0.250000112583355, 0, -0.4333882291756956);//(-0.50000022516671, 0, -0.8667764583513912);
		spectralSoldier1.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.STONE_SWORD));
		spectralSoldier2.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.STONE_SWORD));
		spectralSoldier3.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.STONE_SWORD));
		spectralSoldier1.setCombatTask();
		spectralSoldier2.setCombatTask();
		spectralSoldier3.setCombatTask();
		
		this.world.addEntity(spectralSoldier1);
		this.world.addEntity(spectralSoldier2);
		this.world.addEntity(spectralSoldier3);
	}
	
	private void summonSpectralSoldiersAndGolems()
	{		
		double x,y,z;
		x = this.getPosX(); y = this.getPosY(); z = this.getPosZ();
		
		LivingEntity spectralEntity1;
		LivingEntity spectralEntity2;
		LivingEntity spectralEntity3;
		
		if (rand.nextInt(2) == 0)
		{
			spectralEntity1 = AerialHellEntities.MUD_SPECTRAL_GOLEM_TYPE.create(this.world);
			spectralEntity2 = AerialHellEntities.MUD_SPECTRAL_SOLDIER_TYPE.create(this.world);
			spectralEntity3 = AerialHellEntities.MUD_SPECTRAL_SOLDIER_TYPE.create(this.world);
		}
		else
		{
			spectralEntity1 = AerialHellEntities.MUD_SPECTRAL_GOLEM_TYPE.create(this.world);
			spectralEntity2 = AerialHellEntities.MUD_SPECTRAL_GOLEM_TYPE.create(this.world);
			spectralEntity3 = AerialHellEntities.MUD_SPECTRAL_SOLDIER_TYPE.create(this.world);
		}
		spectralEntity1.setPosition(x, y, z);
		spectralEntity2.setPosition(x, y, z);
		spectralEntity3.setPosition(x, y, z);
		spectralEntity1.setMotion(0.5, 0, 0);//(1, 0, 0);
		spectralEntity2.setMotion(-0.250000112583355, 0, 0.4333882291756956);//(-0.50000022516671, 0, 0.8667764583513912);
		spectralEntity3.setMotion(-0.250000112583355, 0, -0.4333882291756956);//(-0.50000022516671, 0, -0.8667764583513912);
		
		this.world.addEntity(spectralEntity1);
		this.world.addEntity(spectralEntity2);
		this.world.addEntity(spectralEntity3);
	}
	
	public void spawnSmokeParticle()
	{
		for(int i = 0; i < 30; ++i)
        {
        	double d0 = this.rand.nextGaussian() * 0.02D;
        	double d1 = this.rand.nextGaussian() * 0.02D;
        	double d2 = this.rand.nextGaussian() * 0.02D;
        	this.world.addParticle(ParticleTypes.LARGE_SMOKE, this.getPosXWidth(1.0D) - d0 * 10.0D, this.getPosYRandom() - d1 * 10.0D, this.getPosZRandom(1.0D) - d2 * 10.0D, 0.25 * (rand.nextFloat() - 0.5), 0.3D, 0.25 * (rand.nextFloat() - 0.5));
        }
	}
	
	@Override protected SoundEvent getAmbientSound() {return SoundEvents.ENTITY_WITHER_SKELETON_AMBIENT;}
	@Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.ENTITY_WITHER_SKELETON_HURT;}
	@Override protected SoundEvent getDeathSound() {return SoundEvents.ENTITY_WITHER_SKELETON_DEATH;}
}
