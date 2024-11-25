package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class EffectLogBlock extends ShiftableLogBlock
{
	public EffectLogBlock(AbstractBlock.Settings settings, Supplier<ShiftableLogBlock> shiftedVariant, BiomeShifter.ShiftType shiftType) {super(settings, shiftedVariant, shiftType);}

	@Override
	public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player)
	{
		if (this == AerialHellBlocks.ENCHANTED_LAPIS_ROBINIA_LOG && !player.isCreative())
		{
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 30, 0));
		}
		return super.onBreak(world, pos, state, player);
	}
}
