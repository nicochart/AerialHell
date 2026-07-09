package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Util.WorldHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public class UpdateNeighborsAlwaysServerWorldMixin
{
    @Inject(method = "updateNeighborsAt", at = @At("HEAD"), cancellable = true)
    private void onNeighborsUpdate(BlockPos pos, Block sourceBlock, CallbackInfo callbackInfo)
    {
        ServerLevel serverWorld = (ServerLevel) (Object) this;
        WorldHelper.doAerialHellNeighborUpdate(serverWorld, pos);
    }
}
