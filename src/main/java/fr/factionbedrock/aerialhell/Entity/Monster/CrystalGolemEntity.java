package fr.factionbedrock.aerialhell.Entity.Monster;

import com.google.common.collect.ImmutableList;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveAvoidEntityGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveMisleadableNearestAttackableTargetGoal;
import fr.factionbedrock.aerialhell.Entity.AI.FleeBlockGoal;
import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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

public class CrystalGolemEntity extends AerialHellGolemEntity
{	
	private int timeUntilActivation;
	public static final EntityDataAccessor<Boolean> DISAPPEARING = SynchedEntityData.<Boolean>defineId(CrystalGolemEntity.class, EntityDataSerializers.BOOLEAN);
	private int timeDisappearing;
	
    public CrystalGolemEntity(EntityType<? extends Monster> type, Level world)
    {
        super(type, world);
        this.timeUntilActivation = 0;
        this.xpReward = 6;
    }
    
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
        this.setDisappearing(valueInput.getBooleanOr("Disappearing", false)); //TODO default values should never be used
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
	
    @Override
    protected void registerGoals()
    {
    	super.registerGoals();
        List<Block> blocksToAvoid = ImmutableList.of(AerialHellBlocks.SHADOW_TORCH.get(), AerialHellBlocks.SHADOW_WALL_TORCH.get());
        this.goalSelector.addGoal(1, new FleeBlockGoal<>(this, blocksToAvoid, 1.0D, 1.2D));
        this.targetSelector.addGoal(2, new CrystalGolemNearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(1, new CrystalGolemAvoidEntityGoal<>(this, Player.class, 16.0F, 1.2D, 1.5D));
    }
    
    protected static class CrystalGolemAvoidEntityGoal<T extends LivingEntity> extends ActiveAvoidEntityGoal<T>
    {
    	public CrystalGolemAvoidEntityGoal(CrystalGolemEntity golemIn, Class<T> avoidClassIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {super(golemIn, avoidClassIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);}
    	@Override public boolean canUse() {return ((CrystalGolemEntity)this.activableEntity).isDisappearing() && super.canUse();}
		@Override public boolean canContinueToUse() {return ((CrystalGolemEntity)this.activableEntity).isDisappearing() && super.canContinueToUse();}
    }

    protected static class CrystalGolemNearestAttackableTargetGoal<T extends LivingEntity> extends ActiveMisleadableNearestAttackableTargetGoal<T>
    {
        public CrystalGolemNearestAttackableTargetGoal(AbstractActivableEntity entityIn, Class<T> targetClassIn, boolean checkSight) {super(entityIn, targetClassIn, checkSight);}
        @Override public boolean isPlayerMisleadingGoalOwner(Player player)
        {
            return EntityHelper.isLivingEntityMisleadingLunar(player);
        }
    }

	@Override public int getMinTimeToActivate() {return 10;}
	@Override public double getMinDistanceToActivate() {return 24;}
	@Override public float getYMotionOnAttack()
	{
		return 0.25F;
	}
}