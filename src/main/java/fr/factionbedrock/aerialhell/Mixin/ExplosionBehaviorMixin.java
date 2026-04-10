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
        if (block instanceof CoreProtectedPropertyBlock coreProtectedBlock)
        {
            Optional<Float> optionalValue = coreProtectedBlock.getModifiedBlastResistance(state, world, pos, explosion);
            if (optionalValue.isPresent()) {cir.setReturnValue(optionalValue);}
        }
    }
}
