package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Util.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class UpdateNeighborsAlwaysServerWorldMixin
{
    @Inject(method = "updateNeighborsAlways", at = @At("HEAD"), cancellable = true)
    private void onNeighborsUpdate(BlockPos pos, Block sourceBlock, CallbackInfo callbackInfo)
    {
        ServerWorld serverWorld = (ServerWorld) (Object) this;
        WorldHelper.doAerialHellNeighborUpdate(serverWorld, pos);
    }
}
