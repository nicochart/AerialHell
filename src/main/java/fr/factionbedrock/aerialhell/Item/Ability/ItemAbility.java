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
    private final List<ActionModule> passiveModules;
    private final List<ActionModule> onUseModules;
    private final List<ActionModule> onHurtEnemyModules;

    private final List<ConditionModule> passiveConditions;
    private final List<ConditionModule> onUseConditions;
    private final List<ConditionModule> onHurtEnemyConditions;

    private final List<SideEffectModule> passiveSideEffects;
    private final List<SideEffectModule> onUseSideEffects;
    private final List<SideEffectModule> onHurtEnemySideEffects;

    public ItemAbility(AbilityModule... abilityModules) {this(List.of(abilityModules));}
    public ItemAbility(List<AbilityModule> abilityModules)
    {
        this.passiveModules = new ArrayList<>();
        this.onUseModules = new ArrayList<>();
        this.onHurtEnemyModules = new ArrayList<>();
        this.passiveConditions = new ArrayList<>();
        this.onUseConditions = new ArrayList<>();
        this.onHurtEnemyConditions = new ArrayList<>();
        this.passiveSideEffects = new ArrayList<>();
        this.onUseSideEffects = new ArrayList<>();
        this.onHurtEnemySideEffects = new ArrayList<>();

        for(AbilityModule module : abilityModules)
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
        }
    }

    public boolean tryApplyPassiveModules(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
    {
        return this.tryApplyModules(entity, itemStack, equipmentSlot, this.passiveModules, this.passiveConditions, this.passiveSideEffects);
    }

    public boolean tryApplyOnUseModules(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
    {
        return this.tryApplyModules(entity, itemStack, equipmentSlot, this.onUseModules, this.onUseConditions, this.onUseSideEffects);
    }

    public boolean tryApplyOnHurtEnemyModules(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
    {
        return this.tryApplyModules(entity, itemStack, equipmentSlot, this.onHurtEnemyModules, this.onHurtEnemyConditions, this.onHurtEnemySideEffects);
    }

    private boolean tryApplyModules(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, List<ActionModule> actionModules, List<ConditionModule> conditionModules, List<SideEffectModule> sideEffectModules)
    {
        if (this.canApplyModules(entity, conditionModules))
        {
            this.applyModules(entity, itemStack, equipmentSlot, actionModules);
            this.applySideEffectModules(entity, itemStack, equipmentSlot, sideEffectModules);
            return true;
        }
        return false;
    }

    private boolean canApplyModules(LivingEntity entity, List<ConditionModule> conditionModules)
    {
        for(ConditionModule condition : conditionModules)
        {
            if (!condition.isSituationFavorableToApplyModules(entity)) {return false;}
        }
        return true;
    }

    private void applyModules(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, List<ActionModule> actionModules)
    {
        for(ActionModule module : actionModules) {module.apply(entity, itemStack, equipmentSlot);}
    }

    private void applySideEffectModules(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, List<SideEffectModule> sideEffectModules)
    {
        for(SideEffectModule module : sideEffectModules) {module.apply(entity, itemStack, equipmentSlot);}
    }
}
