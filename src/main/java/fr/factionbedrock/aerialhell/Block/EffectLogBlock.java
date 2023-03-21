package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EffectLogBlock extends RotatedPillarBlock
{
	public EffectLogBlock(Properties properties) {super(properties);}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player)
	{
		super.onBlockHarvested(worldIn, pos, state, player);
		if (this == AerialHellBlocksAndItems.EYE_SHADOW_PINE_LOG.get() && !EntityHelper.isLivingEntityShadowImmune(player) && !player.isCreative())
		{
			player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 60, 0));
			player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 60, 1));
		}
		
		if (this == AerialHellBlocksAndItems.ENCHANTED_LAPIS_ROBINIA_LOG.get() && !player.isCreative())
		{
			player.addPotionEffect(new EffectInstance(Effects.HASTE, 30, 0));
		}
	}
}
