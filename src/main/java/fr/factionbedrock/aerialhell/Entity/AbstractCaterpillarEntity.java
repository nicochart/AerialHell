package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class AbstractCaterpillarEntity extends SilverfishEntity
{
	public AbstractCaterpillarEntity(EntityType<? extends AbstractCaterpillarEntity> entityType, World world) {super(entityType, world);}
	
	@Override
	protected void initGoals()
	{
	      this.goalSelector.add(1, new SwimGoal(this));
	      this.goalSelector.add(4, new MeleeAttackGoal(this, 1.0D, false));
	      this.goalSelector.add(3, new PounceAtTargetGoal(this, 0.2F));
	      this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8D));
	      this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
	      this.targetSelector.add(1, (new RevengeGoal(this)).setGroupRevenge());
	}
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_FOREST_CATERPILLAR_AMBIENT;}

	public static boolean canCaterpillarSpawn(EntityType<? extends AbstractCaterpillarEntity> type, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random)
	{
		return random.nextInt(10) == 0 && world.toServerWorld().isDay();
	}
}
