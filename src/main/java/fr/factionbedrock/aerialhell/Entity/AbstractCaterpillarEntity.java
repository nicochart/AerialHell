package fr.factionbedrock.aerialhell.Entity;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class AbstractCaterpillarEntity extends Silverfish
{
	public AbstractCaterpillarEntity(EntityType<? extends AbstractCaterpillarEntity> type, Level worldIn)
    {
        super(type, worldIn);
    }
	
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
	
	@Override
	protected SoundEvent getAmbientSound()
	{
		return AerialHellSoundEvents.ENTITY_FOREST_CATERPILLAR_AMBIENT.get();
	}
	
	public static boolean canCaterpillarSpawn(EntityType<? extends AbstractCaterpillarEntity> type, ServerLevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random randomIn)
    {
        return randomIn.nextInt(10) == 0 && worldIn.getLevel().isDay();
    }
}
