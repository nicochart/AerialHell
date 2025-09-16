package fr.factionbedrock.aerialhell.Entity.Monster;

import com.google.common.collect.ImmutableList;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveAvoidEntityGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveMisleadableNearestAttackableTargetGoal;
import fr.factionbedrock.aerialhell.Entity.AI.FleeBlockGoal;
import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.world.World;

import java.util.List;

public class CrystalGolemEntity extends AerialHellGolemEntity
{
	private int timeUntilActivation;
	public static final TrackedData<Boolean> DISAPPEARING = DataTracker.<Boolean>registerData(CrystalGolemEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private int timeDisappearing;

    public CrystalGolemEntity(EntityType<? extends HostileEntity> type, World world)
    {
        super(type, world);
        this.timeUntilActivation = 0;
        this.experiencePoints = 6;
    }

    @Override protected void initDataTracker(DataTracker.Builder builder)
    {
        super.initDataTracker(builder);
        builder.add(DISAPPEARING, false);
    }

    @Override protected void writeCustomData(WriteView view)
    {
        super.writeCustomData(view);
        view.putBoolean("Disappearing", this.isDisappearing());
    }

    @Override protected void readCustomData(ReadView view)
    {
        super.readCustomData(view);
        this.setDisappearing(view.getBoolean("Disappearing", false));
    }

    public boolean isDisappearing() {return this.getDataTracker().get(DISAPPEARING);}
    public void setDisappearing(boolean flag)
    {
    	this.getDataTracker().set(DISAPPEARING, flag);
    }
    public int getTimeDisappearing() {return this.timeDisappearing;}
    public int getMaxLifeTime() {return 1200;}

    @Override public void tick()
    {
    	super.tick();
    	if (this.canImmediatelyDespawn(64) && this.age > getMaxLifeTime() && !isDisappearing()) {this.setDisappearing(true);}
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
			this.getWorld().addParticleClient(ParticleTypes.CLOUD, this.getX() + random.nextFloat() - 0.5, this.getY() + 2 * random.nextFloat(), this.getZ() + random.nextFloat() - 0.5, 0.5 * (random.nextFloat() - 0.5), 0.5 * (random.nextFloat() - 0.5), 0.5 * (random.nextFloat() - 0.5));
		}
    }

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 45.0D)
                .add(EntityAttributes.ARMOR, 2.0D)
                .add(EntityAttributes.ATTACK_DAMAGE, 7.0D)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.24D);
    }

    @Override public boolean isFireImmune() {return true;}
	@Override public boolean doesRenderOnFire() {return false;}

    @Override
    protected void initGoals()
    {
    	super.initGoals();
        List<Block> blocksToAvoid = ImmutableList.of(AerialHellBlocks.SHADOW_TORCH, AerialHellBlocks.SHADOW_WALL_TORCH);
        this.goalSelector.add(1, new FleeBlockGoal<>(this, blocksToAvoid, 1.0D, 1.2D));
        this.targetSelector.add(2, new CrystalGolemNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.add(1, new CrystalGolemFleeEntityGoal<>(this, PlayerEntity.class, 16.0F, 1.2D, 1.5D));
    }
    
    protected static class CrystalGolemFleeEntityGoal<T extends LivingEntity> extends ActiveAvoidEntityGoal<T>
    {
    	public CrystalGolemFleeEntityGoal(CrystalGolemEntity golemIn, Class<T> avoidClassIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {super(golemIn, avoidClassIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);}
    	@Override public boolean canStart() {return ((CrystalGolemEntity)this.activableEntity).isDisappearing() && super.canStart();}
		@Override public boolean shouldContinue() {return ((CrystalGolemEntity)this.activableEntity).isDisappearing() && super.shouldContinue();}
    }

    protected static class CrystalGolemNearestAttackableTargetGoal<T extends LivingEntity> extends ActiveMisleadableNearestAttackableTargetGoal<T>
    {
        public CrystalGolemNearestAttackableTargetGoal(AbstractActivableEntity entityIn, Class<T> targetClassIn, boolean checkSight) {super(entityIn, targetClassIn, checkSight);}
        @Override public boolean isPlayerMisleadingGoalOwner(PlayerEntity player)
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