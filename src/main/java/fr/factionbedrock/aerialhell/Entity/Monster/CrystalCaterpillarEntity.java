package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class CrystalCaterpillarEntity extends AbstractCaterpillarEntity
{
	public CrystalCaterpillarEntity(EntityType<? extends AbstractCaterpillarEntity> type, World worldIn)
    {
        super(type, worldIn);
    }
	
	public CrystalCaterpillarEntity(World worldIn)
    {
        this(AerialHellEntities.CRYSTAL_CATERPILLAR.get(), worldIn);
    }
	
	@Override
	protected void registerGoals()
	{
	      super.registerGoals();
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return CreatureEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 16.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 12.0D)
        		.createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D);
    }
}
