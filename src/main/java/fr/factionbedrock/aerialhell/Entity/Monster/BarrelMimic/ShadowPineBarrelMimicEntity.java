package fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic;

import fr.factionbedrock.aerialhell.Entity.AbstractBarrelMimicEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

public class ShadowPineBarrelMimicEntity extends AbstractBarrelMimicEntity
{
	public ShadowPineBarrelMimicEntity(EntityType<? extends ShadowPineBarrelMimicEntity> type, World worldIn)
	{
		super(type, worldIn);
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes()
	{
		return CreatureEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 40.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 16.0D);
	}
}
