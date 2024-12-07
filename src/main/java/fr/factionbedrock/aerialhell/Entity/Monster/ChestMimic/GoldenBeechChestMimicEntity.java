package fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.world.World;

public class GoldenBeechChestMimicEntity extends AbstractChestMimicEntity
{
	public GoldenBeechChestMimicEntity(EntityType<? extends GoldenBeechChestMimicEntity> type, World world)
	{
		super(type, world);
	}
	
	public static DefaultAttributeContainer.Builder registerAttributes()
	{
		return createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0D)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D)
				.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0D)
				.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.3D)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D)
				.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8.0D);
	}

	@Override
	protected Block getMimicBlock() {return AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC;}
}
