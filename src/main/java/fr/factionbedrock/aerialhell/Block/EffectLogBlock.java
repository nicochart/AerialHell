package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class EffectLogBlock extends ShiftableLogBlock
{
	public EffectLogBlock(BlockBehaviour.Properties settings, Supplier<ShiftableLogBlock> shiftedVariant, BiomeShifter.ShiftType shiftType) {super(settings, shiftedVariant, shiftType);}

	@Override
	public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player)
	{
		if (this == AerialHellBlocks.ENCHANTED_LAPIS_ROBINIA_LOG && !player.isCreative())
		{
			player.addEffect(new MobEffectInstance(MobEffects.HASTE, 30, 0));
		}
		return super.playerWillDestroy(world, pos, state, player);
	}
}
