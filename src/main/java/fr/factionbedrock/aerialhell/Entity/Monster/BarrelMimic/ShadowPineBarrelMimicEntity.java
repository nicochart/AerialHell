package fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic;

import fr.factionbedrock.aerialhell.Entity.AbstractBarrelMimicEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class ShadowPineBarrelMimicEntity extends AbstractBarrelMimicEntity
{
	public ShadowPineBarrelMimicEntity(EntityType<? extends ShadowPineBarrelMimicEntity> type, Level worldIn)
	{
		super(type, worldIn);
	}
	
	public static AttributeSupplier.Builder registerAttributes()
	{
		return createMobAttributes()
				.add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.ATTACK_DAMAGE, 3.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D)
				.add(Attributes.FOLLOW_RANGE, 16.0D);
	}

	@Override
	protected Block getMimicBlock() {return AerialHellBlocksAndItems.SHADOW_PINE_BARREL_MIMIC.get();}
}
