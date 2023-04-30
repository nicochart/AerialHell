package fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic;

import fr.factionbedrock.aerialhell.Entity.AbstractChestMimicEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class GoldenBeechChestMimicEntity extends AbstractChestMimicEntity
{
	public GoldenBeechChestMimicEntity(EntityType<? extends GoldenBeechChestMimicEntity> type, Level worldIn)
	{
		super(type, worldIn);
	}
	
	public static AttributeSupplier.Builder registerAttributes()
	{
		return createMobAttributes()
				.add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.ATTACK_DAMAGE, 5.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 1.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.3D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D)
				.add(Attributes.FOLLOW_RANGE, 8.0D);
	}

	@Override
	protected Block getMimicBlock() {return AerialHellBlocksAndItems.GOLDEN_BEECH_CHEST_MIMIC.get();}
}
