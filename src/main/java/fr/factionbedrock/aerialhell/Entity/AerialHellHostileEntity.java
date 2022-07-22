package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;

public abstract class AerialHellHostileEntity extends MonsterEntity {

    protected AerialHellHostileEntity(EntityType<? extends MonsterEntity> type, World worldIn) {super(type, worldIn);}
    
    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static boolean canHostileEntitySpawn(EntityType<? extends MonsterEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn)
    {
        if (type == AerialHellEntities.CRYSTAL_SPIDER_TYPE)
        {
        	return randomIn.nextInt(10) == 0 && canMonsterSpawnInLight(type, worldIn, reason, pos, randomIn);
        }
        else if (type == AerialHellEntities.EVIL_COW_TYPE)
        {
        	return randomIn.nextInt(50) == 0 && canMonsterSpawnInLight(type, worldIn, reason, pos, randomIn);
        }
        else
        {
        	return randomIn.nextInt(40) == 0 && canMonsterSpawnInLight(type, worldIn, reason, pos, randomIn);
        }
    }
}
