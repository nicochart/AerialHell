package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrowableProjectile.class)
public class ImpactFromThrownEntityMixin
{
    @Inject(method = "tick", at = @At("RETURN"), cancellable = true)
    private void onImpact(CallbackInfo callbackInfo)
    {
        ThrowableProjectile thrownEntity = (ThrowableProjectile) (Object) this;
        HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(thrownEntity, thrownEntity::canHitEntity);
        if (hitResult.getType() == HitResult.Type.ENTITY)
        {
            EntityHelper.handleProjectileImpactWithEntity(thrownEntity, (EntityHitResult) hitResult, callbackInfo);
        }
    }
}
