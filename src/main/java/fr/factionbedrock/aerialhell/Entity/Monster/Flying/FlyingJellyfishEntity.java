package fr.factionbedrock.aerialhell.Entity.Monster.Flying;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import fr.factionbedrock.aerialhell.Entity.Projectile.PoisonballEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;

public class FlyingJellyfishEntity extends AbstractFlyingProjectileShooterMob
{
	public FlyingJellyfishEntity(EntityType<? extends FlyingJellyfishEntity> type, Level world) {super(type, world);}

	public FlyingJellyfishEntity(Level world) {this(AerialHellEntities.FLYING_JELLYFISH, world);}

	@Override public Projectile createProjectile(Level world, LivingEntity shooter, double accX, double accY, double accZ) {return new PoisonballEntity(world, shooter, accX, accY, accZ);}
	@Override public SoundEvent getShootSound() {return this.getAmbientSound();}

	@Override protected void registerGoals()
	{
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
		super.registerGoals();
	}

	public static AttributeSupplier.Builder registerAttributes()
	{
		return createMobAttributes()
				.add(Attributes.MAX_HEALTH, 5.0D)
				.add(Attributes.FOLLOW_RANGE, 100.0D);
	}

	@Override protected float getSoundVolume() {return 1.2F;}

	public static boolean canJellyfishSpawn(EntityType<? extends FlyingJellyfishEntity> jellyfish, LevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource random)
	{
		return world.getDifficulty() != Difficulty.PEACEFUL && random.nextInt(15) == 0 && checkMobSpawnRules(jellyfish, world, reason, pos, random);
	}

	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_FLYING_JELLYFISH_AMBIENT;}
	@Override protected SoundEvent getHurtSound(DamageSource source) {return AerialHellSoundEvents.ENTITY_FLYING_JELLYFISH_HURT;}
	@Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_FLYING_JELLYFISH_HURT;}
}