package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractHumanoidMonster extends ZombieEntity
{
    protected final float equipMainHandProbability, equipOffHandProbability;
    public AbstractHumanoidMonster(EntityType<? extends AbstractHumanoidMonster> type, World world, float equipMainHandProbability, float equipOffHandProbability)
    {
        super(type, world);
        this.equipMainHandProbability = equipMainHandProbability;
        this.equipOffHandProbability = equipOffHandProbability;
    }

    @Override protected void initGoals()
    {
        this.registerBaseGoals();
        this.registerSpecificGoals();
    }

    protected void registerBaseGoals()
    {
        this.targetSelector.add(1, new RevengeGoal(this));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.6D));
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }
    protected abstract void registerSpecificGoals();

    @Override public void setBaby(boolean isBaby)
    {
        super.setBaby(isBaby);
        if (!this.getEntityWorld().isClient())
        {
            EntityAttributeInstance attributeinstance = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
            attributeinstance.removeModifier(BABY_SPEED_MODIFIER_ID);
        }
    }

    @Override protected void initEquipment(Random rand, LocalDifficulty difficulty)
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
        this.equipStack(hand, weapon);
    }

    @Nullable protected abstract ItemStack getRandomHandItem(EquipmentSlot hand, Random rand);

    @Override @Nullable public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, @Nullable EntityData spawnDataIn)
    {
        this.setLeftHanded(random.nextFloat() < 0.5F);
        this.initEquipment(this.random, difficulty);
        return spawnDataIn;
    }

    public static DefaultAttributeContainer.Builder registerAttributes(double maxHealth, double attackDamage, double movementSpeed, double followRange)
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, maxHealth)
                .add(EntityAttributes.ATTACK_DAMAGE, attackDamage)
                .add(EntityAttributes.MOVEMENT_SPEED, movementSpeed)
                .add(EntityAttributes.SPAWN_REINFORCEMENTS, 0.0D)
        		.add(EntityAttributes.FOLLOW_RANGE, followRange);
    }

    @Override protected boolean burnsInDaylight() {return false;}
}
