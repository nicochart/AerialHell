package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CrystalCaterpillarEntity extends AbstractCaterpillarEntity
{
	public CrystalCaterpillarEntity(EntityType<? extends AbstractCaterpillarEntity> type, Level worldIn)
    {
        super(type, worldIn);
    }
	
	public CrystalCaterpillarEntity(Level worldIn)
    {
        this(AerialHellEntities.CRYSTAL_CATERPILLAR.get(), worldIn);
    }
	
	@Override
	protected void registerGoals()
	{
	      super.registerGoals();
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}
	
	public static AttributeSupplier.Builder registerAttributes()
    {
        return createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.FOLLOW_RANGE, 12.0D)
        		.add(Attributes.ATTACK_DAMAGE, 2.0D);
    }
}
