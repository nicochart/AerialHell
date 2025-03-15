package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class CrystalCaterpillarEntity extends AbstractCaterpillarEntity
{
	public CrystalCaterpillarEntity(EntityType<? extends AbstractCaterpillarEntity> type, World world)
    {
        super(type, world);
    }
	
	public CrystalCaterpillarEntity(World world)
    {
        this(AerialHellEntities.CRYSTAL_CATERPILLAR, world);
    }
	
	@Override
	protected void initGoals()
	{
	      super.initGoals();
	      this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}
	
	public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 16.0D)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.23D)
                .add(EntityAttributes.FOLLOW_RANGE, 12.0D)
        		.add(EntityAttributes.ATTACK_DAMAGE, 2.0D);
    }
}
