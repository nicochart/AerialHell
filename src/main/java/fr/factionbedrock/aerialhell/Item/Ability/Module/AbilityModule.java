package fr.factionbedrock.aerialhell.Item.Ability.Module;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public abstract class AbilityModule
{
    private final UseSituation useSituation;

    public AbilityModule(UseSituation useSituation)
    {
        this.useSituation = useSituation;
    }

    public boolean isPassive() {return this.useSituation == UseSituation.PASSIVE;}
    public boolean isOnUse() {return this.useSituation == UseSituation.ON_USE;}
    public boolean isOnHurtEnemy() {return this.useSituation == UseSituation.ON_HURT_ENEMY;}

    public enum UseSituation{PASSIVE, ON_USE, ON_HURT_ENEMY}

    @FunctionalInterface public interface ModuleAction {void apply(LivingEntity entity, ItemStack stack, @Nullable EquipmentSlot slot);}
}
