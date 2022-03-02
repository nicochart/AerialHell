package fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic;

import fr.factionbedrock.aerialhell.Entity.AbstractChestMimicEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

public class GoldenBeechChestMimicEntity extends AbstractChestMimicEntity
{
	public GoldenBeechChestMimicEntity(EntityType<? extends GoldenBeechChestMimicEntity> type, World worldIn)
	{
		super(type, worldIn);
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes()
	{
		return CreatureEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 40.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D)
				.createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0D)
				.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.3D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 8.0D);
	}
}
