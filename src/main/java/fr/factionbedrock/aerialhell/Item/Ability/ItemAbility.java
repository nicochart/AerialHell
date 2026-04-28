package fr.factionbedrock.aerialhell.Item.Ability;

import fr.factionbedrock.aerialhell.Item.Ability.Module.ActionModule;
import fr.factionbedrock.aerialhell.Item.Ability.Module.ConditionModule;
import fr.factionbedrock.aerialhell.Item.Ability.Module.SideEffectModule;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public class ItemAbility
{
    private final ModuleList passiveModules;
    private final ModuleList onUseModules;
    private final ModuleList onHurtEnemyModules;

    ItemAbility(ModuleList passiveModules, ModuleList onUseModules, ModuleList onHurtEnemyModules)
    {
        this.passiveModules = passiveModules;
        this.onUseModules = onUseModules;
        this.onHurtEnemyModules = onHurtEnemyModules;
    }

    public boolean tryApplyModules(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, AbilityUseSituation useSituation)
    {
        @Nullable ModuleList moduleList = switch (useSituation)
        {
            case TICK -> this.passiveModules;
            case ON_USE -> this.onUseModules;
            case ON_HURT_ENEMY -> this.onHurtEnemyModules;
        };
        if (moduleList == null) {return false;}
        return this.tryApplyModules(entity, itemStack, equipmentSlot, moduleList);
    }

    private boolean tryApplyModules(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, ModuleList modules)
    {
        if (modules.conditions().conditionsMet(entity, itemStack, equipmentSlot))
        {
            if (modules.actions().applyAll(entity, itemStack, equipmentSlot))
            {
                modules.sideEffects().applyAll(entity, itemStack, equipmentSlot);
                return true;
            }
        }
        return false;
    }

    public static Builder copyOf(ItemAbility.Builder source) {return source.copy();}
    public static Builder builder() {return new Builder();}

    static class ActionModuleList
    {
        private final int moduleCount;
        private final List<ActionModule> actionModules;

        ActionModuleList(List<ActionModule> actionModules)
        {
            this.moduleCount = actionModules.size();
            this.actionModules = actionModules;
        }

        List<ActionModule> getModules() {return this.actionModules;}

        private boolean applyAll(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
        {
            for(ActionModule module : this.actionModules) {module.apply(entity, itemStack, equipmentSlot);}
            return !this.isEmpty();
        }

        private boolean isEmpty() {return this.moduleCount == 0;}
    }

    static class ConditionModuleList
    {
        private final List<ConditionModule> conditionModules;
        ConditionModuleList(List<ConditionModule> conditionModules) {this.conditionModules = conditionModules;}

        List<ConditionModule> getModules() {return this.conditionModules;}

        private boolean conditionsMet(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
        {
            for(ConditionModule condition : this.conditionModules)
            {
                if (!condition.conditionMet(entity, itemStack, equipmentSlot)) {return false;}
            }
            return true;
        }
    }

    static class SideEffectModuleList
    {
        private final List<SideEffectModule> sideEffectModules;

        SideEffectModuleList(List<SideEffectModule> sideEffectModules) {this.sideEffectModules = sideEffectModules;}

        List<SideEffectModule> getModules() {return this.sideEffectModules;}

        private void applyAll(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
        {
            for(SideEffectModule module : this.sideEffectModules) {module.apply(entity, itemStack, equipmentSlot);}
        }
    }

    public static class Builder
    {
        private final ModuleList.Builder passiveModules;
        private final ModuleList.Builder onUseModules;
        private final ModuleList.Builder onHurtEnemyModules;

        private Builder() {this(ModuleList.builder(), ModuleList.builder(), ModuleList.builder());}
        private Builder(ModuleList.Builder passiveModules, ModuleList.Builder onUseModules, ModuleList.Builder onHurtEnemyModules)
        {
            this.passiveModules = passiveModules;
            this.onUseModules = onUseModules;
            this.onHurtEnemyModules = onHurtEnemyModules;
        }

        public ItemAbility.Builder addPassiveModules(ModuleList modules) {this.passiveModules.addAll(modules); return this;}
        public ItemAbility.Builder addOnUseModules(ModuleList modules) {this.onUseModules.addAll(modules); return this;}
        public ItemAbility.Builder addOnHurtEnemyModules(ModuleList modules) {this.onHurtEnemyModules.addAll(modules); return this;}

        public ItemAbility.Builder inheritsOf(ItemAbility ability)
        {
            this.addPassiveModules(ability.passiveModules);
            this.addOnUseModules(ability.onUseModules);
            this.addOnHurtEnemyModules(ability.onHurtEnemyModules);
            return this;
        }

        public ItemAbility.Builder copy()
        {
            return new ItemAbility.Builder(this.passiveModules.copy(), this.onUseModules.copy(), this.onHurtEnemyModules.copy());
        }

        public ItemAbility build()
        {
            return new ItemAbility(this.passiveModules.build(), this.onUseModules.build(), this.onHurtEnemyModules.build());
        }
    }
}
