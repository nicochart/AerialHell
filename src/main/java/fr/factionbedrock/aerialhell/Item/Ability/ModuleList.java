package fr.factionbedrock.aerialhell.Item.Ability;

import fr.factionbedrock.aerialhell.Item.Ability.Module.ActionModule;
import fr.factionbedrock.aerialhell.Item.Ability.Module.ConditionModule;
import fr.factionbedrock.aerialhell.Item.Ability.Module.SideEffectModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModuleList
{
    private final ItemAbility.ActionModuleList actionModules;
    private final ItemAbility.ConditionModuleList conditionModules;
    private final ItemAbility.SideEffectModuleList sideEffectModules;

    private ModuleList(List<ActionModule> actionModules, List<ConditionModule> conditionModules, List<SideEffectModule> sideEffectModules)
    {
        this(new ItemAbility.ActionModuleList(actionModules), new ItemAbility.ConditionModuleList(conditionModules), new ItemAbility.SideEffectModuleList(sideEffectModules));
    }

    private ModuleList(ItemAbility.ActionModuleList actionModules, ItemAbility.ConditionModuleList conditionModules, ItemAbility.SideEffectModuleList sideEffectModules)
    {
        this.actionModules = actionModules;
        this.conditionModules = conditionModules;
        this.sideEffectModules = sideEffectModules;
    }

    public static ModuleList.Builder builder() {return new ModuleList.Builder();}

    ItemAbility.ActionModuleList actions() {return this.actionModules;}
    ItemAbility.ConditionModuleList conditions() {return this.conditionModules;}
    ItemAbility.SideEffectModuleList sideEffects() {return this.sideEffectModules;}

    public static class Builder
    {
        private final List<ActionModule> actionModules;
        private final List<ConditionModule> conditionModules;
        private final List<SideEffectModule> sideEffectModules;

        private Builder()
        {
            this.actionModules = new ArrayList<>();
            this.conditionModules = new ArrayList<>();
            this.sideEffectModules = new ArrayList<>();
        }

        void addAll(ModuleList modules)
        {
            this.actionModules.addAll(modules.actions().getModules());
            this.conditionModules.addAll(modules.conditions().getModules());
            this.sideEffectModules.addAll(modules.sideEffects().getModules());
        }

        public ModuleList.Builder addActions(ActionModule... modules) {this.actionModules.addAll(Arrays.asList(modules)); return this;}
        public ModuleList.Builder addConditions(ConditionModule... modules) {this.conditionModules.addAll(Arrays.asList(modules)); return this;}
        public ModuleList.Builder addSideEffects(SideEffectModule... modules) {this.sideEffectModules.addAll(Arrays.asList(modules)); return this;}

        public ModuleList.Builder copy()
        {
            ModuleList.Builder copy = new ModuleList.Builder();
            copy.actionModules.addAll(this.actionModules);
            copy.conditionModules.addAll(this.conditionModules);
            copy.sideEffectModules.addAll(this.sideEffectModules);
            return copy;
        }

        public ModuleList build() {return new ModuleList(this.actionModules, this.conditionModules, this.sideEffectModules);}
    }
}