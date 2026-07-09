package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Util.WorldHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Level.class)
public class UpdateNeighborsAlwaysWorldMixin
{
    @Inject(method = "updateNeighborsAt", at = @At("HEAD"), cancellable = true)
    private void onNeighborsUpdate(BlockPos pos, Block sourceBlock, CallbackInfo callbackInfo)
    {
        Level world = (Level) (Object) this;
        WorldHelper.doAerialHellNeighborUpdate(world, pos);
    }
}
