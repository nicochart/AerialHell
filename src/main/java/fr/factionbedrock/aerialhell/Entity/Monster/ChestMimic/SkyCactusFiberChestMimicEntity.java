package fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic;

import fr.factionbedrock.aerialhell.Entity.AbstractChestMimicEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

public class SkyCactusFiberChestMimicEntity extends AbstractChestMimicEntity
{
	public SkyCactusFiberChestMimicEntity(EntityType<? extends SkyCactusFiberChestMimicEntity> type, World worldIn)
	{
		super(type, worldIn);
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes()
	{
		return CreatureEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 30.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 7.0D)
				.createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 2.0D)
				.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.18D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 8.0D);
	}
}
