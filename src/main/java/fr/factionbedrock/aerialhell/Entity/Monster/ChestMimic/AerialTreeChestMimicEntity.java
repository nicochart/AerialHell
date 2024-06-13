package fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class AerialTreeChestMimicEntity extends AbstractChestMimicEntity
{
	public AerialTreeChestMimicEntity(EntityType<? extends AerialTreeChestMimicEntity> type, Level worldIn)
	{
		super(type, worldIn);
	}
	
	public static AttributeSupplier.Builder registerAttributes()
	{
		return createMobAttributes()
				.add(Attributes.MAX_HEALTH, 50.0D)
				.add(Attributes.ATTACK_DAMAGE, 3.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.FOLLOW_RANGE, 12.0D);
	}

	@Override
	protected Block getMimicBlock() {return AerialHellBlocksAndItems.AERIAL_TREE_CHEST_MIMIC.get();}
}
