package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public class ImpactFromPersistentProjectileEntityMixin
{
    @Inject(method = "tick", at = @At("RETURN"), cancellable = true)
    private void onImpact(CallbackInfo callbackInfo)
    {
        PersistentProjectileEntity projectileEntity = (PersistentProjectileEntity) (Object) this;

        Vec3d velocityVec = projectileEntity.getVelocity();
        Vec3d posVec = projectileEntity.getEntityPos();
        Vec3d moveVec = posVec.add(velocityVec);
        HitResult hitResult = projectileEntity.getEntityWorld().raycast(new RaycastContext(posVec, moveVec, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, projectileEntity));
        if (hitResult.getType() != HitResult.Type.MISS) {moveVec = hitResult.getPos();}
        else {return;}

        EntityHitResult entityHitResult = projectileEntity.getEntityCollision(posVec, moveVec);
        if (entityHitResult != null) {hitResult = entityHitResult;}

        if (hitResult instanceof EntityHitResult ehitResult)
        {
            EntityHelper.handleProjectileImpactWithEntity(projectileEntity, ehitResult, callbackInfo);
        }
    }
}
