package fr.factionbedrock.aerialhell.Entity.Monster.Spider;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public abstract class AbstractAerialHellSpiderEntity extends SpiderEntity
{
    public AbstractAerialHellSpiderEntity(EntityType<? extends SpiderEntity> type, World world) {super(type, world);}
    
    @Override
    public void initGoals()
    {
    	this.goalSelector.add(2, new SwimGoal(this));
        this.goalSelector.add(3, new PounceAtTargetGoal(this, 0.4F));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
    }
}
