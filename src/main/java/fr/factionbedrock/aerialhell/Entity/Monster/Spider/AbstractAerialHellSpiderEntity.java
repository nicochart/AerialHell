package fr.factionbedrock.aerialhell.Entity.Monster.Spider;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

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

    @Override
    public int getExperienceReward()
    {
        return this.level().getRandom().nextInt(10);
    }
}
