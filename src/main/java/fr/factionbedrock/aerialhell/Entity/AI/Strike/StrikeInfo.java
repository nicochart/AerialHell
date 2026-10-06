package fr.factionbedrock.aerialhell.Entity.AI.Strike;

import net.minecraft.world.entity.LivingEntity;

import java.util.function.Supplier;

public class StrikeInfo
{
    public final Supplier<LivingEntity> entityUsedToStrikeSupplier;
    public final float explosionRadius;
    public final float bonusDamageAmount;
    public final float bonusDamageRange;
    public final float knockbackScale;
    public final boolean destroyBlocks;

    public StrikeInfo(Supplier<LivingEntity> entityUsedToStrikeSupplier, float explosionRadius, boolean destroyBlocks) {this(entityUsedToStrikeSupplier, explosionRadius, 0.0F, 0.0F, 0.0F, destroyBlocks);}
    public StrikeInfo(Supplier<LivingEntity> entityUsedToStrikeSupplier, float explosionRadius, float bonusDamageAmount, float bonusDamageRange, float knockbackScale, boolean destroyBlocks)
    {
        this.entityUsedToStrikeSupplier = entityUsedToStrikeSupplier;
        this.explosionRadius = explosionRadius;
        this.bonusDamageAmount = bonusDamageAmount;
        this.bonusDamageRange = bonusDamageRange;
        this.knockbackScale = knockbackScale;
        this.destroyBlocks = destroyBlocks;
    }
}