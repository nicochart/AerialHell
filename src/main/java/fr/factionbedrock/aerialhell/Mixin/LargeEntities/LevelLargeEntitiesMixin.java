package fr.factionbedrock.aerialhell.Mixin.LargeEntities;

import fr.factionbedrock.aerialhell.Entity.Util.LargeEntityLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/* ----------------------------------------------------- */
/* ---------- Large entities lookup : summary ---------- */
/* ----------------------------------------------------- */
//classes : LevelLargeEntitiesMixin, ServerLevelEntityCallbacksMixin, ClientLevelEntityCallbacksMixin (this package) and LargeEntityLevel (Entity.Util)
//problem : entity lookups only check entity sections (16 blocks) near the searched area : 2 blocks around it horizontally, 4 blocks below and 0 above
//an entity is stored in the section of its position (bottom center of its bounding box). an entity larger than 4 blocks (width or height) can be missed :
//when the searched area is touching its bounding box but is too far from its position, collisions, attacks and projectiles go through it (see LargeEntityLevel)
//fix (same principle as vanilla dragon parts) :
//  - each level (each ServerLevel and the ClientLevel) has its own list of large entities : aerialhell$largeEntities, added to Level by this mixin, accessed through LargeEntityLevel
//  - only VoluciteWardenPartEntity larger than 4 blocks (width or height, see LargeEntityLevel.isLarge) are added to the list (arm segments and neck are always found by the section lookup)
//  - the list is updated when level tracking starts / ends (onTrackingStart / onTrackingEnd), server side (ServerLevelEntityCallbacksMixin) and client side (ClientLevelEntityCallbacksMixin), like vanilla / NeoForge dragon parts
//  - the list is used at the end of Level.getEntities, after the section lookup (unchanged) :
//      - getEntities(Entity, AABB, Predicate) : collisions, attack and projectile targeting
//      - getEntities(EntityTypeTest, AABB, Predicate, List, int) : typed lookups (getEntitiesOfClass for example)
//  - a listed entity is added to the result only if : its bounding box intersects the searched area, it matches the lookup predicate, it is not the excluded entity,
//    max results count is not reached, and it is not already in the result (listed entities are also in sections, unlike dragon parts)
/* ----------------------------------------------------- */
/* ----------------------------------------------------- */
/* ----------------------------------------------------- */
@Mixin(Level.class)
public abstract class LevelLargeEntitiesMixin implements LargeEntityLevel
{
    @Unique private final Set<Entity> aerialhell$largeEntities = new HashSet<>();

    @Override public Set<Entity> aerialhell$getLargeEntities() {return this.aerialhell$largeEntities;}

    //used for collisions (EntityGetter.getEntityCollisions)
    @Inject(method = "getEntities(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;)Ljava/util/List;", at = @At("RETURN"))
    private void onGetEntities(@Nullable Entity except, AABB bb, Predicate<? super Entity> selector, CallbackInfoReturnable<List<Entity>> callbackInfo)
    {
        List<Entity> output = callbackInfo.getReturnValue();
        for (Entity entity : this.aerialhell$largeEntities)
        {
            if (entity != except && !entity.isRemoved() && entity.getBoundingBox().intersects(bb) && selector.test(entity) && !output.contains(entity)) {output.add(entity);}
        }
    }

    //used for typed lookups (getEntitiesOfClass for example)
    @Inject(method = "getEntities(Lnet/minecraft/world/level/entity/EntityTypeTest;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;Ljava/util/List;I)V", at = @At("TAIL"))
    private <T extends Entity> void onGetEntitiesOfType(EntityTypeTest<Entity, T> type, AABB bb, Predicate<? super T> selector, List<? super T> output, int maxResults, CallbackInfo callbackInfo)
    {
        for (Entity entity : this.aerialhell$largeEntities)
        {
            if (output.size() >= maxResults) {return;}
            T castEntity = type.tryCast(entity);
            if (castEntity != null && !castEntity.isRemoved() && castEntity.getBoundingBox().intersects(bb) && selector.test(castEntity) && !output.contains(castEntity)) {output.add(castEntity);}
        }
    }
}
