package fr.factionbedrock.aerialhell.Entity.Monster.Flying;

import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;

import fr.factionbedrock.aerialhell.Entity.Projectile.PoisonballEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class FlyingJellyfishEntity extends AbstractFlyingProjectileShooterMob
{
	public FlyingJellyfishEntity(EntityType<? extends FlyingJellyfishEntity> type, World world) {super(type, world);}

	public FlyingJellyfishEntity(World world) {this(AerialHellEntities.FLYING_JELLYFISH, world);}

	@Override public ProjectileEntity createProjectile(World level, LivingEntity shooter, double accX, double accY, double accZ) {return new PoisonballEntity(level, shooter, accX, accY, accZ);}
	@Override public SoundEvent getShootSound() {return this.getAmbientSound();}

	@Override protected void initGoals()
	{
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true, false));
		super.initGoals();
	}

	public static DefaultAttributeContainer.Builder registerAttributes()
	{
		return createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0D)
				.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0D);
	}

	@Override protected float getSoundVolume() {return 1.2F;}

	public static boolean canJellyfishSpawn(EntityType<? extends AbstractCaterpillarEntity> jellyfish, WorldAccess world, SpawnReason reason, BlockPos pos, Random random)
	{
		return world.getDifficulty() != Difficulty.PEACEFUL && random.nextInt(15) == 0 && canMobSpawn(jellyfish, world, reason, pos, random);
	}

	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_FLYING_JELLYFISH_AMBIENT;}
	@Override protected SoundEvent getHurtSound(DamageSource source) {return AerialHellSoundEvents.ENTITY_FLYING_JELLYFISH_HURT;}
	@Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_FLYING_JELLYFISH_HURT;}
}