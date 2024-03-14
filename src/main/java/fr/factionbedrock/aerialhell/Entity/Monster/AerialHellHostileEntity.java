package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.level.ServerLevelAccessor;

public abstract class AerialHellHostileEntity extends Monster
{
    protected AerialHellHostileEntity(EntityType<? extends Monster> type, Level worldIn) {super(type, worldIn);}
    
    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static boolean canHostileEntitySpawn(EntityType<? extends Monster> type, ServerLevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource randomIn)
    {
        if (type == AerialHellEntities.CRYSTAL_SPIDER.get())
        {
        	return randomIn.nextInt(10) == 0 && checkMonsterSpawnRules(type, worldIn, reason, pos, randomIn);
        }
        else if (type == AerialHellEntities.EVIL_COW.get() || type == AerialHellEntities.CORTINARIUS_COW.get())
        {
        	return randomIn.nextInt(50) == 0 && checkMonsterSpawnRules(type, worldIn, reason, pos, randomIn);
        }
        else
        {
        	return randomIn.nextInt(40) == 0 && checkMonsterSpawnRules(type, worldIn, reason, pos, randomIn);
        }
    }
}
