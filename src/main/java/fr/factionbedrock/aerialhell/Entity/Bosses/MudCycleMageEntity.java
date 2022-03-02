package fr.factionbedrock.aerialhell.Entity.Bosses;

import fr.factionbedrock.aerialhell.Entity.Monster.MudSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.MudSpectralSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.FleeSunGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class MudCycleMageEntity extends MudSoldierEntity
{

	public MudCycleMageEntity(EntityType<? extends MudSoldierEntity> type, World world)
	{
		super(type, world);
	}
	
	@Override
    protected void registerGoals()
    {
		this.goalSelector.addGoal(2, new RestrictSunGoal(this));
	    this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
	    this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	    this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
	    this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
	    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TornSpiritEntity.class, true));
	    this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
    }
	
	public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
		return MonsterEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 150.0D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 32.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D);
    }
	
	@Override
	public void tick()
	{
		super.tick();
		if (this.ticksExisted % 600 == 0)
		{
			this.summonSpectralSoldiers();
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
		
		for (int i=0; i<30; i++) this.world.addParticle(ParticleTypes.LARGE_SMOKE, x + rand.nextFloat() - 0.5, y + 2 * rand.nextFloat(), z + rand.nextFloat(), 0.25 * (rand.nextFloat()) - 0.5, 0.3D, 0.25 * (rand.nextFloat() - 0.5));
		this.playSound(SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, 1.5F, 0.95F + rand.nextFloat() * 0.1F);
		this.world.addEntity(spectralSoldier1);
		this.world.addEntity(spectralSoldier2);
		this.world.addEntity(spectralSoldier3);
	}
}
