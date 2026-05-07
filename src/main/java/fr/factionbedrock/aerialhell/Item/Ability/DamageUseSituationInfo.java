package fr.factionbedrock.aerialhell.Item.Ability;

import fr.factionbedrock.aerialhell.Util.FieldAccessor;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public record DamageUseSituationInfo(@Nullable Entity otherEntity, @Nullable DamageSource damageSource, FieldAccessor<Float> damageAmountMultiplier) {}