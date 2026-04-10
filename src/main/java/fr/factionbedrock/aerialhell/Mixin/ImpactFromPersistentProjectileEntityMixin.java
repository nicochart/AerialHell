package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public class ImpactFromPersistentProjectileEntityMixin
{
    @Inject(method = "tick", at = @At("RETURN"), cancellable = true)
    private void onImpact(CallbackInfo callbackInfo)
    {
        AbstractArrow projectileEntity = (AbstractArrow) (Object) this;

        Vec3 velocityVec = projectileEntity.getDeltaMovement();
        Vec3 posVec = projectileEntity.position();
        Vec3 moveVec = posVec.add(velocityVec);
        HitResult hitResult = projectileEntity.level().clip(new ClipContext(posVec, moveVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, projectileEntity));
        if (hitResult.getType() != HitResult.Type.MISS) {moveVec = hitResult.getLocation();}
        else {return;}

        EntityHitResult entityHitResult = projectileEntity.findHitEntity(posVec, moveVec);
        if (entityHitResult != null) {hitResult = entityHitResult;}

        if (hitResult instanceof EntityHitResult ehitResult)
        {
            EntityHelper.handleProjectileImpactWithEntity(projectileEntity, ehitResult, callbackInfo);
        }
    }
}
