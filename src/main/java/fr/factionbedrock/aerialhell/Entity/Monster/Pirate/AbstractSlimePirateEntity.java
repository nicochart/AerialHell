package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public abstract class AbstractSlimePirateEntity extends Zombie
{
    public AbstractSlimePirateEntity(EntityType<? extends AbstractSlimePirateEntity> type, Level world) {super(type, world);}

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

    @Override public void remove(Entity.RemovalReason removalReason)
    {
        if (!this.level().isClientSide && !this.isBaby() && this.isDeadOrDying())
        {
            int number = 1 + this.random.nextInt(3);
            for (int l = 0; l < number; ++l)
            {
                float x = ((float) (l % 2) - 0.5F) * 0.5F;
                float z = ((float) (l / 2) - 0.5F) * 0.5F;
                AbstractSlimePirateEntity littlePirate = this.getLittlePirateType().create(this.level());
                if (littlePirate != null)
                {
                    if (this.isPersistenceRequired()) {littlePirate.setPersistenceRequired();}
                    littlePirate.setCustomName(this.getCustomName());
                    littlePirate.setNoAi(this.isNoAi());
                    littlePirate.setInvulnerable(this.isInvulnerable());
                    littlePirate.setBaby(true);
                    littlePirate.moveTo(this.getX() + (double) x, this.getY() + 0.5D, this.getZ() + (double) z, this.random.nextFloat() * 360.0F, 0.0F);
                    this.level().addFreshEntity(littlePirate);
                    //No weapon
                    //littlePirate.populateDefaultEquipmentSlots(this.getRandom(), this.level().getCurrentDifficultyAt(this.blockPosition()));
                }
            }
        }
        super.remove(removalReason);
    }

    protected EntityType<? extends AbstractSlimePirateEntity> getLittlePirateType() {return this.getType();}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getType()
    {
        return (EntityType<? extends AbstractSlimePirateEntity>) super.getType();
    }

    @Override public void setBaby(boolean isBaby)
    {
        super.setBaby(isBaby);
        if (!this.level().isClientSide)
        {
            AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
            attributeinstance.removeModifier(SPEED_MODIFIER_BABY.getId());
        }
    }

    @Override protected void populateDefaultEquipmentSlots(RandomSource rand, DifficultyInstance difficulty)
    {
        if (rand.nextInt(2) == 0)
        {
            this.populateHand(EquipmentSlot.MAINHAND, this.getRandomWeapon(rand));
            if (rand.nextInt(3) == 0)
            {
                this.populateHand(EquipmentSlot.OFFHAND, this.getRandomWeapon(rand));
            }
        }
        else
        {
            this.populateHand(EquipmentSlot.OFFHAND, this.getRandomWeapon(rand));
            if (rand.nextInt(3) == 0)
            {
                this.populateHand(EquipmentSlot.MAINHAND, this.getRandomWeapon(rand));
            }
        }
    }

    protected void populateHand(EquipmentSlot hand, @Nullable ItemStack weapon)
    {
        if (weapon == null || this.isBaby()) {return;}
        this.setItemSlot(hand, weapon);
    }

    @Nullable protected abstract ItemStack getRandomWeapon(RandomSource rand);

    @Override @Nullable public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag)
    {
        this.populateDefaultEquipmentSlots(this.random, difficultyIn);
        return spawnDataIn;
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0.0D)
        		.add(Attributes.FOLLOW_RANGE, 35.0D);
    }

    @Override protected boolean isSunSensitive() {return false;}
    
    @Override protected SoundEvent getAmbientSound(){return SoundEvents.SLIME_SQUISH;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return SoundEvents.SLIME_HURT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.SLIME_DEATH;}
    @Override protected void playStepSound(BlockPos pos, BlockState blockIn) {this.playSound(SoundEvents.SLIME_BLOCK_STEP, 0.15F, 0.5F);}
}
