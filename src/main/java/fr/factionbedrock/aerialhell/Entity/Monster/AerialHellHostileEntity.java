package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;

public abstract class AerialHellHostileEntity extends Monster
{
    protected AerialHellHostileEntity(EntityType<? extends Monster> type, Level world) {super(type, world);}
    
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

    //spawners (in structures) : hostile entities can spawn up to block light 7, whatever the sky light (day or night)
    //light sources placed near the spawner still disable it
    public static final int SPAWNER_MAX_BLOCK_LIGHT = 7;
    public static boolean checkSpawnerSpawnRules(EntityType<? extends Mob> type, ServerLevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource random)
    {
        return world.getBrightness(LightLayer.BLOCK, pos) <= SPAWNER_MAX_BLOCK_LIGHT && checkMobSpawnRules(type, world, reason, pos, random);
    }

    public static boolean canHostileEntitySpawn(EntityType<? extends Monster> type, ServerLevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource random)
    {
        if (EntitySpawnReason.isSpawner(reason))
        {
            return checkSpawnerSpawnRules(type, world, reason, pos, random);
        }
        else if (type == AerialHellEntities.CRYSTAL_SPIDER)
        {
        	return random.nextInt(10) == 0 && checkMonsterSpawnRules(type, world, reason, pos, random);
        }
        else if (type == AerialHellEntities.EVIL_COW || type == AerialHellEntities.CORTINARIUS_COW)
        {
        	return random.nextInt(50) == 0 && checkMonsterSpawnRules(type, world, reason, pos, random);
        }
        else
        {
        	return random.nextInt(40) == 0 && checkMonsterSpawnRules(type, world, reason, pos, random);
        }
    }
}
