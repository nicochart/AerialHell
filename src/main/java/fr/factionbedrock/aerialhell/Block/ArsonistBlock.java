package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ArsonistBlock extends Block
{
	public ArsonistBlock(AbstractBlock.Settings settings)
	{
		super(settings);
	}
	
	@Override public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity)
	{
		if (entity instanceof LivingEntity livingEntity)
		{
			if (!entity.isFireImmune() && !EntityHelper.hasEnchantment(livingEntity, Enchantments.FROST_WALKER))
			{
				entity.damage(world.getDamageSources().hotFloor(), 1.0F);
			}
			entity.setOnFireFor(2);
		}
	}
}
