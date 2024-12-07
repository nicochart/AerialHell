package fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.world.World;

public class SkyCactusFiberChestMimicEntity extends AbstractChestMimicEntity
{
	public SkyCactusFiberChestMimicEntity(EntityType<? extends SkyCactusFiberChestMimicEntity> type, World world)
	{
		super(type, world);
	}
	
	public static DefaultAttributeContainer.Builder registerAttributes()
	{
		return createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7.0D)
				.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 2.0D)
				.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.5D)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.18D)
				.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8.0D);
	}

	@Override
	protected Block getMimicBlock() {return AerialHellBlocks.SKY_CACTUS_FIBER_CHEST_MIMIC;}
}
