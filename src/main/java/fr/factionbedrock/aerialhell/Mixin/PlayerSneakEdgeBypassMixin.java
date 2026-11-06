package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Entity.Util.SneakEdgeBypassPlayer;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//see SneakEdgeBypassPlayer
@Mixin(Player.class)
public abstract class PlayerSneakEdgeBypassMixin implements SneakEdgeBypassPlayer
{
    @Unique private int aerialhell$sneakEdgeBypassEndTick = -1;

    @Override public void aerialhell$bypassSneakEdgeFor(int ticks) {this.aerialhell$sneakEdgeBypassEndTick = ((Player) (Object) this).tickCount + ticks;}
    @Override public boolean aerialhell$isBypassingSneakEdge() {return ((Player) (Object) this).tickCount <= this.aerialhell$sneakEdgeBypassEndTick;}

    //skipping the vanilla sneak edge back off (the movement is kept unchanged)
    @Inject(method = "maybeBackOffFromEdge", at = @At("HEAD"), cancellable = true)
    private void aerialhell$onMaybeBackOffFromEdge(Vec3 delta, MoverType moverType, CallbackInfoReturnable<Vec3> callbackInfo)
    {
        if (this.aerialhell$isBypassingSneakEdge()) {callbackInfo.setReturnValue(delta);}
    }
}
