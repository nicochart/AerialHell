package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;

@Mixin(AbstractArrow.class)
public abstract class ImpactFromPersistentProjectileEntityMixin
{
    @ModifyVariable(method = "stepMoveAndHit", at = @At("STORE"), ordinal = 0)
    private EntityHitResult modifyHitEntitiesList(EntityHitResult hit)
    {
        if (hit == null) {return null;}
        AbstractArrow projectileEntity = (AbstractArrow) (Object) this;

        return EntityHelper.canProjectileImpact(projectileEntity, hit.getEntity()) ? hit : null;
    }
}
