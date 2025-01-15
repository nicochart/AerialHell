package fr.factionbedrock.aerialhell.Entity.Monster.Mud;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class MudSoldierEntity extends AbstractSkeleton
{
	public MudSoldierEntity(EntityType<? extends MudSoldierEntity> type, Level world)
    {
        super(type, world);
    }
	
	@Override
    protected void registerGoals()
    {
		this.goalSelector.addGoal(2, new RestrictSunGoal(this));
	    this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
	    this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
	    this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
	    this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
	    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TornSpiritEntity.class, true));
	    this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, ChainedGodEntity.class, true));
    }
	
	public static AttributeSupplier.Builder registerAttributes()
    {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.FOLLOW_RANGE, 24.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.20D)
				.add(Attributes.ATTACK_DAMAGE, 3.0D);
    }

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource rand, DifficultyInstance difficulty)
	{
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
	}
	
	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, EntitySpawnReason reason, @Nullable SpawnGroupData spawnDataIn)
	{
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
		this.populateDefaultEquipmentSlots(this.random, difficultyIn);
		this.reassessWeaponGoal();
		return spawnDataIn;
	}
	
	@Override protected SoundEvent getAmbientSound() {return SoundEvents.WITHER_SKELETON_AMBIENT;}
	@Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.WITHER_SKELETON_HURT;}
	@Override protected SoundEvent getDeathSound() {return SoundEvents.WITHER_SKELETON_DEATH;}
	@Override protected SoundEvent getStepSound() {return SoundEvents.WITHER_SKELETON_STEP;}
}
