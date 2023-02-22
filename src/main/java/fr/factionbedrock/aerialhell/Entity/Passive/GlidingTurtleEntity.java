package fr.factionbedrock.aerialhell.Entity.Passive;

import fr.factionbedrock.aerialhell.Entity.AerialHellAnimalEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class GlidingTurtleEntity extends AerialHellAnimalEntity
{
	private int jumpTimer;
    public static final DataParameter<Boolean> GLIDING = EntityDataManager.<Boolean>createKey(GlidingTurtleEntity.class, DataSerializers.BOOLEAN);

    public GlidingTurtleEntity(EntityType<? extends GlidingTurtleEntity> type, World worldIn)
    {
        super(type, worldIn);
        this.jumpTimer = 0;
    }

    public GlidingTurtleEntity(World worldIn)
    {
        this(AerialHellEntities.GLIDING_TURTLE.get(), worldIn);
        this.jumpTimer = 0;
    }

    @Override
    protected void registerData()
    {
        super.registerData();
        this.dataManager.register(GLIDING, false);
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1, Ingredient.fromItems(AerialHellBlocksAndItems.AERIAL_BERRY.get()), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return AerialHellAnimalEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 50.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.26);
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_)
    {
        return AerialHellEntities.GLIDING_TURTLE.get().create(this.world);
    }
    
    public boolean isGliding() {return !this.dataManager.get(GLIDING);}
    public void setGliding(boolean flag) {this.dataManager.set(GLIDING, !flag);}

    @Override public boolean onLivingFall(float distance, float damageMultiplier) {return false;}

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_GLIDING_TURTLE_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_GLIDING_TURTLE_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_GLIDING_TURTLE_DEATH.get();}

    @Override public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
        compound.putBoolean("Glide", this.isGliding());
    }

    @Override public void readAdditional(CompoundNBT compound)
    {
        super.readAdditional(compound);
        this.setGliding(compound.getBoolean("Glide"));
    }

    @Override public void livingTick()
    {
        super.livingTick();
        if (!this.onGround)
        {
            if (this.isGliding()) {this.setGlidingMotion();}
            else if (this.getMotion().y < -0.3D) {this.setGliding(true);}
        }

        if (this.jumpTimer > 600 && !this.isGliding() && this.isOnGround())
        {
            this.glideJump();
        }
        if (!this.isGliding()) {this.jumpTimer += 1 + (int) Math.random()*10;}

        if (this.isGliding() && this.isOnGround() && this.getMotion().y>=-0.1F) {this.setGliding(false);}
    }

    private void setGlidingMotion()
    {
        if (this.getMotion().y < 0.0D)
        {
            this.setMotion(this.getMotion().mul(1.0D, 0.6D, 1.0D).add(this.getForward().x/100, 0, this.getForward().z/100));
        }
    }

    public void glideJump()
    {
        this.setMotion(this.getMotion().add(0.0D, 1.5D, 0.0D));
        this.jumpTimer = 0;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        boolean flag = super.attackEntityFrom(source, amount);
        if (flag && !this.isGliding()) {this.jumpTimer += 400;}
        return flag;
    }
}
