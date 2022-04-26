package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class CrystalGolemEntity extends AerialHellGolemEntity
{	
	private int timeUntilActivation;
	
    public CrystalGolemEntity(EntityType<? extends MonsterEntity> type, World world)
    {
        super(type, world);
        this.timeUntilActivation = 0;
    }
    
    @Override
    public void livingTick()
    {
    	super.livingTick();
    	if (this.isInDaylight())
    	{
    		if (this.isActive())
    		{
    			this.setActive(false);
    		}
    		this.timeUntilActivation = 100;
    	}
    	else
    	{
    		if (!this.isActive())
    		{
    			if (this.timeUntilActivation > 0)
    			{
    				this.timeUntilActivation--;
    			}
    			else
    			{
    				this.setActive(true);
    			}
    		}
    	}
    }
    
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 40.0D)
                .createMutableAttribute(Attributes.ARMOR, 3.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 7.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D);
    }
    
    @Override public boolean isImmuneToFire() {return true;}
	@Override public boolean canRenderOnFire() {return false;}
	
    @Override
    protected void registerGoals()
    {
    	super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

	@Override
	public float getYMotionOnAttack()
	{
		return 0.25F;
	}
}