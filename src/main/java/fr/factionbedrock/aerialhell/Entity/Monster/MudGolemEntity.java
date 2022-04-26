package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EntityPredicates;
import net.minecraft.world.World;

public class MudGolemEntity extends AerialHellGolemEntity
{
	private float timeClosePlayer = 0.0F;
	
    public MudGolemEntity(EntityType<? extends MonsterEntity> type, World world)
    {
        super(type, world);
    }
    
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 60.0D)
                .createMutableAttribute(Attributes.ARMOR, 3.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 7.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23D);
    }
    
    @Override
    protected void registerGoals()
    {
    	super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AnimalEntity.class, true));
    }
	
	@Override
	public void tick()
	{
		if (this.world.getClosestPlayer(this.getPosX(), this.getPosY(), this.getPosZ(), 16.0, EntityPredicates.CAN_AI_TARGET) != null)
		{
			if (!this.isActive() && this.timeClosePlayer >= 50)
			{
				this.setActive(true);
			}
			this.timeClosePlayer++;
		}
		else
		{
			if (timeClosePlayer == 0)
			{
				this.setActive(false);
			}
			else
			{
				this.timeClosePlayer--;
			}
		}
		super.tick();
	}
	
	@Override
	public float getYMotionOnAttack()
	{
		return 0.4F;
	}
    
    @Override
	public boolean canDespawn(double distanceToClosestPlayer)
	{
	      return false;
	}
}