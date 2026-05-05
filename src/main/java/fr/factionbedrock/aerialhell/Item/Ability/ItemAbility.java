package fr.factionbedrock.aerialhell.Item.Ability;

import fr.factionbedrock.aerialhell.Item.Ability.Module.ActionModule;
import fr.factionbedrock.aerialhell.Item.Ability.Module.ConditionModule;
import fr.factionbedrock.aerialhell.Item.Ability.Module.SideEffectModule;

import javax.annotation.Nullable;
import java.util.List;

public class ItemAbility
{
    private String descId;
    private final ModuleList passiveModules;
    private final ModuleList onUseModules;
    private final ModuleList onDealDamageModules;
    private final ModuleList onTakeDamageModules;

    ItemAbility(String descId, ModuleList passiveModules, ModuleList onUseModules, ModuleList onDealDamageModules, ModuleList onTakeDamageModules)
    {
        this.descId = descId;
        this.passiveModules = passiveModules;
        this.onUseModules = onUseModules;
        this.onDealDamageModules = onDealDamageModules;
        this.onTakeDamageModules = onTakeDamageModules;
    }

    public String getDescId() {return this.descId;}

    public boolean tryApplyModules(AbilityUseSituation useSituation)
    {
        @Nullable ModuleList moduleList = switch (useSituation.category)
        {
            case TICK -> this.passiveModules;
            case ON_USE -> this.onUseModules;
            case ON_DEAL_DAMAGE -> this.onDealDamageModules;
            case ON_TAKE_DAMAGE -> this.onTakeDamageModules;
        };
        if (moduleList == null) {return false;}

        if (moduleList.conditions().conditionsMet(useSituation))
        {
            if (moduleList.actions().applyAll(useSituation))
            {
                moduleList.sideEffects().applyAll(useSituation);
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

        private boolean applyAll(AbilityUseSituation useSituation)
        {
            for(ActionModule module : this.actionModules) {module.apply(useSituation);}
            return !this.isEmpty();
        }

        private boolean isEmpty() {return this.moduleCount == 0;}
    }

    static class ConditionModuleList
    {
        private final List<ConditionModule> conditionModules;
        ConditionModuleList(List<ConditionModule> conditionModules) {this.conditionModules = conditionModules;}

        List<ConditionModule> getModules() {return this.conditionModules;}

        private boolean conditionsMet(AbilityUseSituation useSituation)
        {
            for(ConditionModule condition : this.conditionModules)
            {
                if (!condition.conditionMet(useSituation)) {return false;}
            }
            return true;
        }
    }

    static class SideEffectModuleList
    {
        private final List<SideEffectModule> sideEffectModules;

        SideEffectModuleList(List<SideEffectModule> sideEffectModules) {this.sideEffectModules = sideEffectModules;}

        List<SideEffectModule> getModules() {return this.sideEffectModules;}

        private void applyAll(AbilityUseSituation useSituation)
        {
            for(SideEffectModule module : this.sideEffectModules) {module.apply(useSituation);}
        }
    }

    public static class Builder
    {
        private String descId;
        private final ModuleList.Builder passiveModules;
        private final ModuleList.Builder onUseModules;
        private final ModuleList.Builder onDealDamageModules;
        private final ModuleList.Builder onTakeDamageModules;

        private Builder() {this(ModuleList.builder(), ModuleList.builder(), ModuleList.builder(), ModuleList.builder());}
        private Builder(ModuleList.Builder passiveModules, ModuleList.Builder onUseModules, ModuleList.Builder onDealDamageModules, ModuleList.Builder onTakeDamageModules)
        {
            this.passiveModules = passiveModules;
            this.onUseModules = onUseModules;
            this.onDealDamageModules = onDealDamageModules;
            this.onTakeDamageModules = onTakeDamageModules;
            this.descId = "";
        }

        public ItemAbility.Builder setDescId(String descId) {this.descId = descId; return this;}

        public ItemAbility.Builder addPassiveModules(ModuleList modules) {this.passiveModules.addAll(modules); return this;}
        public ItemAbility.Builder addOnUseModules(ModuleList modules) {this.onUseModules.addAll(modules); return this;}
        public ItemAbility.Builder addOnDealDamageModules(ModuleList modules) {this.onDealDamageModules.addAll(modules); return this;}
        public ItemAbility.Builder addOnTakeDamageModules(ModuleList modules) {this.onTakeDamageModules.addAll(modules); return this;}

        public ItemAbility.Builder inheritsOf(ItemAbility ability)
        {
            this.addPassiveModules(ability.passiveModules);
            this.addOnUseModules(ability.onUseModules);
            this.addOnDealDamageModules(ability.onDealDamageModules);
            this.addOnTakeDamageModules(ability.onTakeDamageModules);
            return this;
        }

        public ItemAbility.Builder copy()
        {
            return new ItemAbility.Builder(this.passiveModules.copy(), this.onUseModules.copy(), this.onDealDamageModules.copy(), this.onTakeDamageModules.copy());
        }

        public ItemAbility build()
        {
            return new ItemAbility(this.descId, this.passiveModules.build(), this.onUseModules.build(), this.onDealDamageModules.build(), this.onTakeDamageModules.build());
        }
    }
}
