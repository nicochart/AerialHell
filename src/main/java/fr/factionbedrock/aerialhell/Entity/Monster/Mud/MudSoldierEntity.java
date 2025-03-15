package fr.factionbedrock.aerialhell.Entity.Monster.Mud;

import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MudSoldierEntity extends AbstractSkeletonEntity
{
	public MudSoldierEntity(EntityType<? extends MudSoldierEntity> type, World world)
    {
        super(type, world);
    }
	
	@Override
    protected void initGoals()
    {
		this.goalSelector.add(2, new AvoidSunlightGoal(this));
	    this.goalSelector.add(3, new EscapeSunlightGoal(this, 1.0D));
	    this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
	    this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
	    this.goalSelector.add(6, new LookAroundGoal(this));
	    this.targetSelector.add(1, new RevengeGoal(this));
	    this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	    this.targetSelector.add(3, new ActiveTargetGoal<>(this, TornSpiritEntity.class, true));
	    this.targetSelector.add(4, new ActiveTargetGoal<>(this, ChainedGodEntity.class, true));
    }
	
	public static DefaultAttributeContainer.Builder registerAttributes()
    {
		return HostileEntity.createHostileAttributes()
				.add(EntityAttributes.MAX_HEALTH, 20.0D)
				.add(EntityAttributes.FOLLOW_RANGE, 24.0D)
				.add(EntityAttributes.MOVEMENT_SPEED, 0.20D)
				.add(EntityAttributes.ATTACK_DAMAGE, 3.0D);
    }

	@Override
	protected void initEquipment(Random rand, LocalDifficulty difficulty)
	{
		this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
	}
	
	@Override
	@Nullable
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData)
	{
		this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
		this.initEquipment(this.random, difficulty);
		this.updateAttackType();
		return entityData;
	}
	
	@Override protected SoundEvent getAmbientSound() {return SoundEvents.ENTITY_WITHER_SKELETON_AMBIENT;}
	@Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.ENTITY_WITHER_SKELETON_HURT;}
	@Override protected SoundEvent getDeathSound() {return SoundEvents.ENTITY_WITHER_SKELETON_DEATH;}
	@Override public SoundEvent getStepSound() {return SoundEvents.ENTITY_WITHER_SKELETON_STEP;}
}
