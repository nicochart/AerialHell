package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class AbstractCaterpillarEntity extends Silverfish
{
	public AbstractCaterpillarEntity(EntityType<? extends AbstractCaterpillarEntity> entityType, Level world) {super(entityType, world);}
	
	@Override
	protected void registerGoals()
	{
	      this.goalSelector.addGoal(1, new FloatGoal(this));
	      this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
	      this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.2F));
	      this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
	      this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
	      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
	}
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_FOREST_CATERPILLAR_AMBIENT;}

	public static boolean canCaterpillarSpawn(EntityType<? extends AbstractCaterpillarEntity> type, ServerLevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource random)
	{
		return random.nextInt(10) == 0 && world.getLevel().isBrightOutside();
	}
}
