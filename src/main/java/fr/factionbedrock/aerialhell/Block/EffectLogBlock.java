package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;

import java.util.function.Supplier;

public class EffectLogBlock extends ShiftableLogBlock
{
	public EffectLogBlock(Properties properties, Supplier<ShiftableLogBlock> shiftedVariant, BiomeShifter.ShiftType shiftType) {super(properties, shiftedVariant, shiftType);}
	
	@Override
	public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid)
	{
		boolean flag = super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
		if (flag && this == AerialHellBlocksAndItems.ENCHANTED_LAPIS_ROBINIA_LOG.get() && !player.isCreative())
		{
			player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 30, 0));
		}
		return flag;
	}
}
