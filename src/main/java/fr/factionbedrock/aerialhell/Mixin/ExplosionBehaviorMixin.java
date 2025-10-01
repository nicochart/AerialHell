package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Block.DungeonCores.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(ExplosionBehavior.class)
public class ExplosionBehaviorMixin
{
    @Inject(method = "getBlastResistance", at = @At("HEAD"), cancellable = true)
    private void onGetBlastResistance(Explosion explosion, BlockView world, BlockPos pos, BlockState state, FluidState fluidState, CallbackInfoReturnable<Optional<Float>> cir)
    {
        Block block = state.getBlock();
        if (block instanceof CoreProtectedPropertyBlock coreProtectedBlock)
        {
            Optional<Float> optionalValue = coreProtectedBlock.getModifiedBlastResistance(state, world, pos, explosion);
            if (optionalValue.isPresent()) {cir.setReturnValue(optionalValue);}
        }
    }
}
