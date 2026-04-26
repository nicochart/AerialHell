package fr.factionbedrock.aerialhell.Item.Ability;

import fr.factionbedrock.aerialhell.Item.Ability.Module.AbilityModule;
import fr.factionbedrock.aerialhell.Item.Ability.Module.ActionModule;
import fr.factionbedrock.aerialhell.Item.Ability.Module.ConditionModule;
import fr.factionbedrock.aerialhell.Item.Ability.Module.SideEffectModule;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ItemAbility
{
    //fallbackAbility is attempted if this ability's conditions are not met
    @Nullable private ItemAbility fallbackAbility;

    private final ActionModulesList passiveModules;
    private final ActionModulesList onUseModules;
    private final ActionModulesList onHurtEnemyModules;

    private final ConditionModulesList passiveConditions;
    private final ConditionModulesList onUseConditions;
    private final ConditionModulesList onHurtEnemyConditions;

    private final SideEffectModulesList passiveSideEffects;
    private final SideEffectModulesList onUseSideEffects;
    private final SideEffectModulesList onHurtEnemySideEffects;

    public ItemAbility(AbilityModule... abilityModules) {this(List.of(abilityModules));}
    public ItemAbility(List<AbilityModule> abilityModules)
    {
        this.passiveModules = new ActionModulesList();
        this.onUseModules = new ActionModulesList();
        this.onHurtEnemyModules = new ActionModulesList();
        this.passiveConditions = new ConditionModulesList();
        this.onUseConditions = new ConditionModulesList();
        this.onHurtEnemyConditions = new ConditionModulesList();
        this.passiveSideEffects = new SideEffectModulesList();
        this.onUseSideEffects = new SideEffectModulesList();
        this.onHurtEnemySideEffects = new SideEffectModulesList();

        for(AbilityModule module : abilityModules) {this.addModule(module);}
    }

    private ItemAbility(ActionModulesList passiveModules, ActionModulesList onUseModules, ActionModulesList onHurtEnemyModules, ConditionModulesList passiveConditions, ConditionModulesList onUseConditions, ConditionModulesList onHurtEnemyConditions, SideEffectModulesList passiveSideEffects, SideEffectModulesList onUseSideEffects, SideEffectModulesList onHurtEnemySideEffects)
    {
        this.passiveModules = passiveModules;
        this.onUseModules = onUseModules;
        this.onHurtEnemyModules = onHurtEnemyModules;
        this.passiveConditions = passiveConditions;
        this.onUseConditions = onUseConditions;
        this.onHurtEnemyConditions = onHurtEnemyConditions;
        this.passiveSideEffects = passiveSideEffects;
        this.onUseSideEffects = onUseSideEffects;
        this.onHurtEnemySideEffects = onHurtEnemySideEffects;
    }

    public boolean tryApplyPassiveModules(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
    {
        if (this.tryApplyModules(entity, itemStack, equipmentSlot, this.passiveModules, this.passiveConditions, this.passiveSideEffects)) {return true;}
        return this.fallbackAbility != null && this.fallbackAbility.tryApplyPassiveModules(entity, itemStack, equipmentSlot);
    }

    public boolean tryApplyOnUseModules(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
    {
        if ( this.tryApplyModules(entity, itemStack, equipmentSlot, this.onUseModules, this.onUseConditions, this.onUseSideEffects)) {return true;}
        return this.fallbackAbility != null && this.fallbackAbility.tryApplyOnUseModules(entity, itemStack, equipmentSlot);
    }

    public boolean tryApplyOnHurtEnemyModules(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
    {
        if (this.tryApplyModules(entity, itemStack, equipmentSlot, this.onHurtEnemyModules, this.onHurtEnemyConditions, this.onHurtEnemySideEffects)) {return true;}
        return this.fallbackAbility != null && this.fallbackAbility.tryApplyOnHurtEnemyModules(entity, itemStack, equipmentSlot);
    }

    private boolean tryApplyModules(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, ActionModulesList actionModules, ConditionModulesList conditionModules, SideEffectModulesList sideEffectModules)
    {
        if (conditionModules.conditionsMet(entity, itemStack, equipmentSlot))
        {
            actionModules.applyAll(entity, itemStack, equipmentSlot);
            sideEffectModules.applyAll(entity, itemStack, equipmentSlot);
            return true;
        }
        return false;
    }

    public ItemAbility copy()
    {
        return new ItemAbility(this.passiveModules.copy(), this.onUseModules.copy(), this.onHurtEnemyModules.copy(), this.passiveConditions.copy(), this.onUseConditions.copy(), this.onHurtEnemyConditions.copy(), this.passiveSideEffects.copy(), this.onUseSideEffects.copy(), this.onHurtEnemySideEffects.copy());
    }

    public ItemAbility setFallback(ItemAbility fallbackAbility) {this.fallbackAbility = fallbackAbility; return this;}

    public ItemAbility addModules(AbilityModule... modules) {for (AbilityModule module : modules) {this.addModule(module);} return this;}
    public ItemAbility addModule(AbilityModule module)
    {
        if (module instanceof ActionModule actionModule)
        {
            if (actionModule.isPassive()) {this.passiveModules.add(actionModule);}
            if (actionModule.isOnUse()) {this.onUseModules.add(actionModule);}
            if (actionModule.isOnHurtEnemy()) {this.onHurtEnemyModules.add(actionModule);}
        }
        if (module instanceof ConditionModule conditionModule)
        {
            if (conditionModule.isPassive()) {this.passiveConditions.add(conditionModule);}
            if (conditionModule.isOnUse()) {this.onUseConditions.add(conditionModule);}
            if (conditionModule.isOnHurtEnemy()) {this.onHurtEnemyConditions.add(conditionModule);}
        }
        if (module instanceof SideEffectModule sideEffectModule)
        {
            if (sideEffectModule.isPassive()) {this.passiveSideEffects.add(sideEffectModule);}
            if (sideEffectModule.isOnUse()) {this.onUseSideEffects.add(sideEffectModule);}
            if (sideEffectModule.isOnHurtEnemy()) {this.onHurtEnemySideEffects.add(sideEffectModule);}
        }
        return this;
    }

    private static class ActionModulesList
    {
        private final List<ActionModule.MobEffect> mobEffectModules;
        private final List<ActionModule.Particle> particleModules;
        private final List<ActionModule.RemoveMobEffect> removeMobEffectModules;
        private final List<ActionModule.Sound> soundModules;
        private final List<ActionModule.ThrowProjectile> throwProjectileModules;
        private final List<ActionModule> customModules;

        private ActionModulesList()
        {
            this.mobEffectModules = new ArrayList<>();
            this.particleModules = new ArrayList<>();
            this.removeMobEffectModules = new ArrayList<>();
            this.soundModules = new ArrayList<>();
            this.throwProjectileModules = new ArrayList<>();
            this.customModules = new ArrayList<>();
        }

        private void add(ActionModule module)
        {
            if (module instanceof ActionModule.MobEffect mobEffectModule) {this.mobEffectModules.add(mobEffectModule); return;}
            if (module instanceof ActionModule.Particle particleModule) {this.particleModules.add(particleModule); return;}
            if (module instanceof ActionModule.RemoveMobEffect removeMobEffectModule) {this.removeMobEffectModules.add(removeMobEffectModule); return;}
            if (module instanceof ActionModule.Sound soundModule) {this.soundModules.add(soundModule); return;}
            if (module instanceof ActionModule.ThrowProjectile throwProjectilesModule) {this.throwProjectileModules.add(throwProjectilesModule); return;}
            if (module instanceof ActionModule customModule) {this.customModules.add(customModule); return;}
        }

        private void applyAll(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
        {
            for(ActionModule module : this.mobEffectModules) {module.apply(entity, itemStack, equipmentSlot);}
            for(ActionModule module : this.particleModules) {module.apply(entity, itemStack, equipmentSlot);}
            for(ActionModule module : this.removeMobEffectModules) {module.apply(entity, itemStack, equipmentSlot);}
            for(ActionModule module : this.soundModules) {module.apply(entity, itemStack, equipmentSlot);}
            for(ActionModule module : this.throwProjectileModules) {module.apply(entity, itemStack, equipmentSlot);}
            for(ActionModule module : this.customModules) {module.apply(entity, itemStack, equipmentSlot);}
        }

        public ActionModulesList copy()
        {
            ActionModulesList copy = new ActionModulesList();
            copy.mobEffectModules.addAll(this.mobEffectModules);
            copy.particleModules.addAll(this.particleModules);
            copy.removeMobEffectModules.addAll(this.removeMobEffectModules);
            copy.soundModules.addAll(this.soundModules);
            copy.throwProjectileModules.addAll(this.throwProjectileModules);
            copy.customModules.addAll(this.customModules);
            return copy;
        }
    }

    private static class ConditionModulesList
    {
        private final List<ConditionModule> conditionModules;
        private ConditionModulesList() {this.conditionModules = new ArrayList<>();}
        private void add(ConditionModule module) {this.conditionModules.add(module);}

        private boolean conditionsMet(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
        {
            for(ConditionModule condition : this.conditionModules)
            {
                if (!condition.conditionMet(entity, itemStack, equipmentSlot)) {return false;}
            }
            return true;
        }

        public ConditionModulesList copy()
        {
            ConditionModulesList copy = new ConditionModulesList();
            copy.conditionModules.addAll(this.conditionModules);
            return copy;
        }
    }

    private static class SideEffectModulesList
    {
        private final List<SideEffectModule.Cooldown> cooldownModules;
        private final List<SideEffectModule.DamageItem> damageItemModules;

        private SideEffectModulesList()
        {
            this.cooldownModules = new ArrayList<>();
            this.damageItemModules = new ArrayList<>();
        }

        private void add(SideEffectModule module)
        {
            if (module instanceof SideEffectModule.Cooldown cooldownModule) {this.cooldownModules.add(cooldownModule);}
            if (module instanceof SideEffectModule.DamageItem damageItemModule) {this.damageItemModules.add(damageItemModule);}
        }

        private void applyAll(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
        {
            for(SideEffectModule module : this.cooldownModules) {module.apply(entity, itemStack, equipmentSlot);}
            for(SideEffectModule module : this.damageItemModules) {module.apply(entity, itemStack, equipmentSlot);}
        }

        public SideEffectModulesList copy()
        {
            SideEffectModulesList copy = new SideEffectModulesList();
            copy.cooldownModules.addAll(this.cooldownModules);
            copy.damageItemModules.addAll(this.damageItemModules);
            return copy;
        }
    }
}
