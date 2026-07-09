package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Block.DungeonCores.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

@Mixin(ExplosionDamageCalculator.class)
public class ExplosionBehaviorMixin
{
    @Inject(method = "getBlockExplosionResistance", at = @At("HEAD"), cancellable = true)
    private void onGetBlastResistance(Explosion explosion, BlockGetter world, BlockPos pos, BlockState state, FluidState fluidState, CallbackInfoReturnable<Optional<Float>> cir)
    {
        Block block = state.getBlock();
        if (block instanceof CoreProtectedBlock)
        {
            if (state.getValue(CoreProtectedBlock.CORE_PROTECTED)) {cir.setReturnValue(Optional.of(1200F));}
        }
        else if (block instanceof CoreProtectedChestBlock)
        {
            if (state.getValue(CoreProtectedChestBlock.CORE_PROTECTED)) {cir.setReturnValue(Optional.of(1200F));}
        }
        else if (block instanceof CoreProtectedGlyphBlock)
        {
            if (state.getValue(CoreProtectedGlyphBlock.CORE_PROTECTED)) {cir.setReturnValue(Optional.of(1200F));}
        }
        else if (block instanceof CoreProtectedRotatedPillarBlock)
        {
            if (state.getValue(CoreProtectedRotatedPillarBlock.CORE_PROTECTED)) {cir.setReturnValue(Optional.of(1200F));}
        }
        else if (block instanceof CoreProtectedSlabBlock)
        {
            if (state.getValue(CoreProtectedSlabBlock.CORE_PROTECTED)) {cir.setReturnValue(Optional.of(1200F));}
        }
        else if (block instanceof CoreProtectedStairsBlock)
        {
            if (state.getValue(CoreProtectedStairsBlock.CORE_PROTECTED)) {cir.setReturnValue(Optional.of(1200F));}
        }
        /*else if (block instanceof CoreProtectedWallBlock)
        {
            if (state.get(CoreProtectedWallBlock.CORE_PROTECTED)) {cir.setReturnValue(Optional.of(1200F));}
        }*/
    }
}
