package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Util.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class UpdateNeighborsExceptMixin
{
    @Inject(method = "updateNeighborsExcept", at = @At("HEAD"), cancellable = true)
    private void onNeighborsUpdate(BlockPos pos, Block sourceBlock, Direction direction, @Nullable WireOrientation orientation, CallbackInfo callbackInfo)
    {
        ServerWorld serverWorld = (ServerWorld) (Object) this;
        WorldHelper.doAerialHellNeighborUpdate(serverWorld, pos);
    }
}
