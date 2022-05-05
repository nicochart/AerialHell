package fr.factionbedrock.aerialhell.Entity;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public class AbstractCaterpillarEntity extends SilverfishEntity
{
	public AbstractCaterpillarEntity(EntityType<? extends AbstractCaterpillarEntity> type, World worldIn)
    {
        super(type, worldIn);
    }
	
	@Override
	protected void registerGoals()
	{
	      this.goalSelector.addGoal(1, new SwimGoal(this));
	      this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
	      this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.2F));
	      this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
	      this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
	      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
	}
	
	@Override
	protected SoundEvent getAmbientSound()
	{
		return AerialHellSoundEvents.ENTITY_FOREST_CATERPILLAR_AMBIENT.get();
	}
	
	public static boolean canCaterpillarSpawn(EntityType<? extends AbstractCaterpillarEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn)
    {
        return randomIn.nextInt(10) == 0 && worldIn.getWorld().isDaytime();
    }
}
