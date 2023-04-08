package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AI.ActiveAvoidEntityGoal;
import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;

public class CrystalGolemEntity extends AerialHellGolemEntity
{	
	private int timeUntilActivation;
	public static final DataParameter<Boolean> DISAPPEARING = EntityDataManager.<Boolean>createKey(CrystalGolemEntity.class, DataSerializers.BOOLEAN);
	private int timeDisappearing;
	
    public CrystalGolemEntity(EntityType<? extends MonsterEntity> type, World world)
    {
        super(type, world);
        this.timeUntilActivation = 0;
        this.experienceValue = 6;
    }
    
    @Override protected void registerData()
    {
        super.registerData();
        this.dataManager.register(DISAPPEARING, false);
    }
    
    @Override public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
        compound.putBoolean("Disappearing", this.isDisappearing());
    }

    @Override public void readAdditional(CompoundNBT compound)
    {
        super.readAdditional(compound);
        this.setDisappearing(compound.getBoolean("Disappearing"));
    }
    
    public boolean isDisappearing() {return this.dataManager.get(DISAPPEARING);}
    public void setDisappearing(boolean flag)
    {
    	this.dataManager.set(DISAPPEARING, flag);
    }
    public int getTimeDisappearing() {return this.timeDisappearing;}
    public int getMaxLifeTime() {return 1200;}
    
    @Override public void tick()
    {
    	super.tick();
    	if (this.canDespawn(64) && this.ticksExisted > getMaxLifeTime() && !isDisappearing()) {this.setDisappearing(true);}
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
    		else {this.remove();}
    		this.timeDisappearing++;
    	}
    }
    
    private void addDisappearingParticle(int number)
    {
    	for (int i=0; i<number; i++)
		{
			this.world.addParticle(ParticleTypes.CLOUD, this.getPosX() + rand.nextFloat() - 0.5, this.getPosY() + 2 * rand.nextFloat(), this.getPosZ() + rand.nextFloat() - 0.5, 0.5 * (rand.nextFloat() - 0.5), 0.5 * (rand.nextFloat() - 0.5), 0.5 * (rand.nextFloat() - 0.5));
		}
    }
    
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 45.0D)
                .createMutableAttribute(Attributes.ARMOR, 2.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 7.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.24D);
    }
    
    @Override public boolean isImmuneToFire() {return true;}
	@Override public boolean canRenderOnFire() {return false;}
	
    @Override
    protected void registerGoals()
    {
    	super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.addGoal(1, new CrystalGolemAvoidEntityGoal<>(this, PlayerEntity.class, 16.0F, 1.2D, 1.5D));
    }
    
    protected static class CrystalGolemAvoidEntityGoal<T extends LivingEntity> extends ActiveAvoidEntityGoal<T>
    {
    	public CrystalGolemAvoidEntityGoal(CrystalGolemEntity golemIn, Class<T> avoidClassIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {super(golemIn, avoidClassIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);}
    	@Override public boolean shouldExecute() {return ((CrystalGolemEntity)this.activableEntity).isDisappearing() && super.shouldExecute();}
		@Override public boolean shouldContinueExecuting() {return ((CrystalGolemEntity)this.activableEntity).isDisappearing() && super.shouldContinueExecuting();}
    }

	@Override public int getMinTimeToActivate() {return 10;}
	@Override public double getMinDistanceToActivate() {return 24;}
	@Override public float getYMotionOnAttack()
	{
		return 0.25F;
	}
}