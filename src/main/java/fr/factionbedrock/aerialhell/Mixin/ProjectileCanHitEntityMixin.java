package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Projectile.class)
public abstract class ProjectileCanHitEntityMixin
{

    @Inject(method = "canHitEntity", at = @At("RETURN"), cancellable = true)
    private void canHitEntityAdditionalCondition(Entity hitEntity, CallbackInfoReturnable<Boolean> cir)
    {
        Projectile projectileEntity = (Projectile) (Object) this;

        if (!EntityHelper.canProjectileImpact(projectileEntity, hitEntity)) {cir.setReturnValue(false);}
    }
}
