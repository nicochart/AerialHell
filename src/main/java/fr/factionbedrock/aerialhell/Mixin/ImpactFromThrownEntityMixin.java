package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrownEntity.class)
public class ImpactFromThrownEntityMixin
{
    @Inject(method = "tick", at = @At("RETURN"), cancellable = true)
    private void onImpact(CallbackInfo callbackInfo)
    {
        ThrownEntity thrownEntity = (ThrownEntity) (Object) this;
        HitResult hitResult = ProjectileUtil.getCollision(thrownEntity, thrownEntity::canHit);
        if (hitResult.getType() == HitResult.Type.ENTITY)
        {
            EntityHelper.handleProjectileImpactWithEntity(thrownEntity, (EntityHitResult) hitResult, callbackInfo);
        }
    }
}
