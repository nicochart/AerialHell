package fr.factionbedrock.aerialhell.Block.ShadowSpreader;

import fr.factionbedrock.aerialhell.Block.ShiftableLogBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ShadowEffectLogBlock extends ShadowLogBlock
{
    public ShadowEffectLogBlock(BlockBehaviour.Properties settings, Supplier<ShiftableLogBlock> shiftedVariant, BiomeShifter.ShiftType shiftType) {super(settings, shiftedVariant, shiftType);}

    @Override
    public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player)
    {
        if (this == AerialHellBlocks.EYE_SHADOW_PINE_LOG && !EntityHelper.isLivingEntityShadowImmune(player) && !player.isCreative())
        {
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0));
            player.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 60, 1));
        }
        return super.playerWillDestroy(world, pos, state, player);
    }
}
