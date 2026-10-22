package fr.factionbedrock.aerialhell.Entity.Monster.Spider;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractAerialHellSpiderEntity extends Spider
{
    public AbstractAerialHellSpiderEntity(EntityType<? extends Spider> type, Level worldIn)
    {
        super(type, worldIn);
    }
    
    @Override
    public void registerGoals()
    {
    	this.goalSelector.addGoal(2, new FloatGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    //vanilla Spider.finalizeSpawn can add a riding skeleton (spider jockey), not expected on aerial hell spiders
    //so we remove it
    @Override @Nullable public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData groupData, @Nullable CompoundTag tag)
    {
        SpawnGroupData spawnGroupData = super.finalizeSpawn(level, difficulty, spawnType, groupData, tag);
        this.removeSkeletonJockey();
        return spawnGroupData;
    }

    private void removeSkeletonJockey()
    {
        for (Entity passenger : List.copyOf(this.getPassengers()))
        {
            if (passenger instanceof Skeleton)
            {
                passenger.stopRiding();
                passenger.discard();
            }
        }
    }
}
