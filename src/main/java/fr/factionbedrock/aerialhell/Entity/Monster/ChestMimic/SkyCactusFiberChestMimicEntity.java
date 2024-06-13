package fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class SkyCactusFiberChestMimicEntity extends AbstractChestMimicEntity
{
	public SkyCactusFiberChestMimicEntity(EntityType<? extends SkyCactusFiberChestMimicEntity> type, Level worldIn)
	{
		super(type, worldIn);
	}
	
	public static AttributeSupplier.Builder registerAttributes()
	{
		return createMobAttributes()
				.add(Attributes.MAX_HEALTH, 30.0D)
				.add(Attributes.ATTACK_DAMAGE, 7.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 2.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
				.add(Attributes.MOVEMENT_SPEED, 0.18D)
				.add(Attributes.FOLLOW_RANGE, 8.0D);
	}

	@Override
	protected Block getMimicBlock() {return AerialHellBlocksAndItems.SKY_CACTUS_FIBER_CHEST_MIMIC.get();}
}
