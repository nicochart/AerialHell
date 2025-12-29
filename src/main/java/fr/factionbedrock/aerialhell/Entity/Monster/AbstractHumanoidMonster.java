package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;

public abstract class AbstractHumanoidMonster extends Zombie
{
    protected final float equipMainHandProbability, equipOffHandProbability;
    public AbstractHumanoidMonster(EntityType<? extends AbstractHumanoidMonster> type, Level world, float equipMainHandProbability, float equipOffHandProbability)
    {
        super(type, world);
        this.equipMainHandProbability = equipMainHandProbability;
        this.equipOffHandProbability = equipOffHandProbability;
    }

    @Override protected void registerGoals()
    {
        this.registerBaseGoals();
        this.registerSpecificGoals();
    }

    protected void registerBaseGoals()
    {
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }
    protected abstract void registerSpecificGoals();

    @Override public void setBaby(boolean isBaby)
    {
        super.setBaby(isBaby);
        if (!this.level().isClientSide())
        {
            AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
            attributeinstance.removeModifier(SPEED_MODIFIER_BABY.id());
        }
    }

    @Override protected void populateDefaultEquipmentSlots(RandomSource rand, DifficultyInstance difficulty)
    {
        boolean equipMainHand = (random.nextFloat() < this.equipMainHandProbability);
        boolean equipOffHand = (random.nextFloat() < this.equipOffHandProbability);
        if (equipMainHand)
        {
            this.populateHand(EquipmentSlot.MAINHAND, this.getRandomHandItem(EquipmentSlot.MAINHAND, rand));
        }
        if (equipOffHand)
        {
            this.populateHand(EquipmentSlot.OFFHAND, this.getRandomHandItem(EquipmentSlot.OFFHAND, rand));
        }
    }

    protected void populateHand(EquipmentSlot hand, @Nullable ItemStack weapon)
    {
        if (weapon == null || this.isBaby()) {return;}
        this.setItemSlot(hand, weapon);
    }

    @Nullable protected abstract ItemStack getRandomHandItem(EquipmentSlot hand, RandomSource rand);

    @Override @Nullable public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, EntitySpawnReason reason, @Nullable SpawnGroupData spawnDataIn)
    {
        this.setLeftHanded(random.nextFloat() < 0.5F);
        this.populateDefaultEquipmentSlots(this.random, difficultyIn);
        return spawnDataIn;
    }

    public static AttributeSupplier.Builder registerAttributes(double maxHealth, double attackDamage, double movementSpeed, double followRange)
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, maxHealth)
                .add(Attributes.ATTACK_DAMAGE, attackDamage)
                .add(Attributes.MOVEMENT_SPEED, movementSpeed)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0.0D)
        		.add(Attributes.FOLLOW_RANGE, followRange);
    }

    @Override protected boolean isSunSensitive() {return false;}
}
