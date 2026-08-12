package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Block.DungeonCores.CoreProtectedBlock;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonBaseBlock.class)
public class PistonIsPushableMixin
{
    @Inject(method = "isPushable", at = @At("RETURN"), cancellable = true)
    private static void onIsPushable(BlockState state, Level level, BlockPos pos, Direction direction, boolean allowDestroyable, Direction connectionDirection, CallbackInfoReturnable<Boolean> cir)
    {
        if (state.getOptionalValue(CoreProtectedBlock.CORE_PROTECTED).orElse(false)) {cir.setReturnValue(false);}
    }
}
