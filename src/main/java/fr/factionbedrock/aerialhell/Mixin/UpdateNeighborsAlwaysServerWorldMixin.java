package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Util.WorldHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.redstone.Orientation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public class UpdateNeighborsAlwaysServerWorldMixin
{
    @Inject(method = "updateNeighborsAt(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/level/redstone/Orientation;)V", at = @At("HEAD"), cancellable = true)
    private void onNeighborsUpdate(BlockPos pos, Block sourceBlock, @Nullable Orientation orientation, CallbackInfo callbackInfo)
    {
        ServerLevel serverWorld = (ServerLevel) (Object) this;
        WorldHelper.doAerialHellNeighborUpdate(serverWorld, pos);
    }
}
