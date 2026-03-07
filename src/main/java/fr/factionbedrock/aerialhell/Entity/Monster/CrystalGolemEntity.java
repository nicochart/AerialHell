package fr.factionbedrock.aerialhell.Entity.Monster;

import com.google.common.collect.ImmutableList;
import fr.factionbedrock.aerialhell.Entity.AI.ConditionalGoal;
import fr.factionbedrock.aerialhell.Entity.AI.FleeBlockGoal;
import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.List;

public class CrystalGolemEntity extends AerialHellGolemEntity implements MisleadableEntity, GoalConditionEntity.PhaseAwareGoalConditionEntity
{
    public static final int ACTIVE_GOALS = 0, DISAPPEARING_GOALS = 1;
	public static final EntityDataAccessor<Boolean> DISAPPEARING = SynchedEntityData.<Boolean>defineId(CrystalGolemEntity.class, EntityDataSerializers.BOOLEAN);
	private int timeDisappearing;
	
    public CrystalGolemEntity(EntityType<? extends Monster> type, Level world)
    {
        super(type, world);
        this.xpReward = 6;
    }

    /* ------- MisleadableEntity : Interface method implementation ------- */
    @Override public boolean isMisleadedBy(LivingEntity livingEntity)
    {
        return EntityHelper.isLivingEntityMisleadingLunar(livingEntity);
    }
    /* ------------------------------------------------------------------- */

    /* ------- MisleadableEntity : Superclass methods Overridden to delegate to interface ------- */
    @Override public boolean hurtServer(ServerLevel serverLevel, DamageSource source, float amount)
    {
        return this.misleadableHurtServer(serverLevel, source, amount, super::hurtServer);
    }
    /* ------------------------------------------------------------------------------------------ */

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(DISAPPEARING, false);
    }
    
    @Override public void addAdditionalSaveData(ValueOutput valueOutput)
    {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.putBoolean("Disappearing", this.isDisappearing());
    }

    @Override public void readAdditionalSaveData(ValueInput valueInput)
    {
        super.readAdditionalSaveData(valueInput);
        this.setDisappearing(valueInput.getBooleanOr("Disappearing", false));
    }
    
    public boolean isDisappearing() {return this.entityData.get(DISAPPEARING);}
    public void setDisappearing(boolean flag)
    {
    	this.entityData.set(DISAPPEARING, flag);
    }
    public int getTimeDisappearing() {return this.timeDisappearing;}
    public int getMaxLifeTime() {return 1200;}
    
    @Override public void tick()
    {
    	super.tick();
    	if (this.removeWhenFarAway(64) && this.tickCount > getMaxLifeTime() && !isDisappearing()) {this.setDisappearing(true);}
    	if (this.isDisappearing())
    	{    		
    		if (this.timeDisappearing < 95)
    		{
    			this.addDisappearingParticle(1);
    		}
    		else if (this.timeDisappearing < 100)
    		{
    			this.addDisappearingParticle(10);
    		}
    		else {this.discard();}
    		this.timeDisappearing++;
    	}
    }
    
    private void addDisappearingParticle(int number)
    {
    	for (int i=0; i<number; i++)
		{
			this.level().addParticle(ParticleTypes.CLOUD, this.getX() + random.nextFloat() - 0.5, this.getY() + 2 * random.nextFloat(), this.getZ() + random.nextFloat() - 0.5, 0.5 * (random.nextFloat() - 0.5), 0.5 * (random.nextFloat() - 0.5), 0.5 * (random.nextFloat() - 0.5));
		}
    }
    
    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 45.0D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.ATTACK_DAMAGE, 7.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.24D);
    }
    
    @Override public boolean fireImmune() {return true;}
	@Override public boolean displayFireAnimation() {return false;}

    /* ----- GoalConditionEntity.PhaseAwareGoalConditionEntity : Interface method implementation ----- */
    @Override public boolean checkGoalCondition(int conditionIndex) {return this.canUseGoalsAdditionalCondition(conditionIndex);} //need to override checkGoalCondition because Crystal Golem implements both GoalSimpleConditionEntity (from AbstractActivableEntity) and PhaseAwareGoalConditionEntity

    @Override public boolean canUseGoalsAdditionalCondition(int goalIndex)
    {
        if (goalIndex == ACTIVE_GOALS) {return this.isActive() && !this.isDisappearing();}
        else if (goalIndex == DISAPPEARING_GOALS)
        {
            return this.isDisappearing();
        }
        else {return false;}
    }
    /* ----------------------------------------------------------------------------------------------- */

    @Override protected void registerGoals()
    {
    	super.registerGoals();
        List<Block> blocksToAvoid = ImmutableList.of(AerialHellBlocks.SHADOW_TORCH.get(), AerialHellBlocks.SHADOW_WALL_TORCH.get());
        this.goalSelector.addGoal(1, new FleeBlockGoal<>(this, blocksToAvoid, 1.0D, 1.2D));
        this.targetSelector.addGoal(2, new ConditionalGoal(this, ACTIVE_GOALS, new NearestAttackableTargetGoal<>(this, Player.class, true, (potentialTarget, serverLevel) -> !this.isMisleadedBy(potentialTarget))));
        this.goalSelector.addGoal(1, new ConditionalGoal(this, DISAPPEARING_GOALS, new AvoidEntityGoal<>(this, Player.class, 16.0F, 1.2D, 1.5D)));
    }

	@Override public int getTicksToActivate() {return 10;}
	@Override public double getMinDistanceToActivate() {return 24;}
	@Override public float getYMotionOnAttack()
	{
		return 0.25F;
	}
    @Override public boolean updateTargetOnHurtByLivingEntity() {return false;}
}