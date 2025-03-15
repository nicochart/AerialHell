package fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.world.World;

public class CopperPineChestMimicEntity extends AbstractChestMimicEntity
{
	public CopperPineChestMimicEntity(EntityType<? extends CopperPineChestMimicEntity> type, World world)
	{
		super(type, world);
	}
	
	public static DefaultAttributeContainer.Builder registerAttributes()
	{
		return createMobAttributes()
				.add(EntityAttributes.MAX_HEALTH, 50.0D)
				.add(EntityAttributes.ATTACK_DAMAGE, 5.0D)
				.add(EntityAttributes.MOVEMENT_SPEED, 0.3D)
				.add(EntityAttributes.FOLLOW_RANGE, 8.0D);
	}

	@Override
	protected Block getMimicBlock() {return AerialHellBlocks.COPPER_PINE_CHEST_MIMIC;}
}
