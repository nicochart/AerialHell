package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;

@Mixin(AbstractArrow.class)
public abstract class ImpactFromPersistentProjectileEntityMixin
{
    @ModifyVariable(method = "stepMoveAndHit", at = @At("STORE"), ordinal = 0)
    private ArrayList<EntityHitResult> modifyHitEntitiesList(ArrayList<EntityHitResult> original)
    {
        AbstractArrow projectileEntity = (AbstractArrow) (Object) this;

        ArrayList<EntityHitResult> filtered = new ArrayList<>();

        for (EntityHitResult hit : original)
        {
            if (EntityHelper.canProjectileImpact(projectileEntity, hit.getEntity())) {filtered.add(hit);}
        }

        return filtered;
    }
}
