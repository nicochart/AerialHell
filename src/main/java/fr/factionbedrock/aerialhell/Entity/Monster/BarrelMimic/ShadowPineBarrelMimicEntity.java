package fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.world.World;

public class ShadowPineBarrelMimicEntity extends AbstractBarrelMimicEntity
{
	public ShadowPineBarrelMimicEntity(EntityType<? extends ShadowPineBarrelMimicEntity> type, World world)
	{
		super(type, world);
	}
	
	public static DefaultAttributeContainer.Builder registerAttributes()
	{
		return createMobAttributes()
				.add(EntityAttributes.MAX_HEALTH, 40.0D)
				.add(EntityAttributes.ATTACK_DAMAGE, 3.0D)
				.add(EntityAttributes.MOVEMENT_SPEED, 0.3D)
				.add(EntityAttributes.FOLLOW_RANGE, 16.0D);
	}

	@Override
	protected Block getMimicBlock() {return AerialHellBlocks.SHADOW_PINE_BARREL_MIMIC;}
}
