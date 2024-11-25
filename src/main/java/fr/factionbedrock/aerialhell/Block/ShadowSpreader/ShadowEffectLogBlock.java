package fr.factionbedrock.aerialhell.Block.ShadowSpreader;

import fr.factionbedrock.aerialhell.Block.ShiftableLogBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class ShadowEffectLogBlock extends ShadowLogBlock
{
    public ShadowEffectLogBlock(AbstractBlock.Settings settings, Supplier<ShiftableLogBlock> shiftedVariant, BiomeShifter.ShiftType shiftType) {super(settings, shiftedVariant, shiftType);}

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player)
    {
        if (this == AerialHellBlocks.EYE_SHADOW_PINE_LOG && !EntityHelper.isLivingEntityShadowImmune(player) && !player.isCreative())
        {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 0));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 60, 1));
        }
        return super.onBreak(world, pos, state, player);
    }
}
