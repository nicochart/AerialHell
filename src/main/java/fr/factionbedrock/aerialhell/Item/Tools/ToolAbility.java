package fr.factionbedrock.aerialhell.Item.Tools;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ToolAbility
{
    private final List<ToolAbilityModule.Action> passiveModules;
    private final List<ToolAbilityModule.Action> onUseModules;
    private final List<ToolAbilityModule.Action> onHurtEnemyModules;

    private final List<ToolAbilityModule.Condition> passiveConditions;
    private final List<ToolAbilityModule.Condition> onUseConditions;
    private final List<ToolAbilityModule.Condition> onHurtEnemyConditions;

    public ToolAbility(ToolAbilityModule... toolAbilityModules) {this(List.of(toolAbilityModules));}
    public ToolAbility(List<ToolAbilityModule> toolAbilityModules)
    {
        this.passiveModules = new ArrayList<>();
        this.onUseModules = new ArrayList<>();
        this.onHurtEnemyModules = new ArrayList<>();
        this.passiveConditions = new ArrayList<>();
        this.onUseConditions = new ArrayList<>();
        this.onHurtEnemyConditions = new ArrayList<>();

        for(ToolAbilityModule module : toolAbilityModules)
        {
            if (module instanceof ToolAbilityModule.Action actionModule)
            {
                if (actionModule.isPassive()) {this.passiveModules.add(actionModule);}
                if (actionModule.isOnUse()) {this.onUseModules.add(actionModule);}
                if (actionModule.isOnHurtEnemy()) {this.onHurtEnemyModules.add(actionModule);}
            }
            if (module instanceof ToolAbilityModule.Condition conditionModule)
            {
                if (conditionModule.isPassive()) {this.passiveConditions.add(conditionModule);}
                if (conditionModule.isOnUse()) {this.onUseConditions.add(conditionModule);}
                if (conditionModule.isOnHurtEnemy()) {this.onHurtEnemyConditions.add(conditionModule);}
            }
        }
    }

    public boolean tryApplyPassiveModules(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
    {
        return this.tryApplyModules(entity, itemStack, equipmentSlot, this.passiveModules, this.passiveConditions);
    }

    public boolean tryApplyOnUseModules(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
    {
        return this.tryApplyModules(entity, itemStack, equipmentSlot, this.onUseModules, this.onUseConditions);
    }

    public boolean tryApplyOnHurtEnemyModules(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
    {
        return this.tryApplyModules(entity, itemStack, equipmentSlot, this.onHurtEnemyModules, this.onHurtEnemyConditions);
    }

    private boolean tryApplyModules(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, List<ToolAbilityModule.Action> actionModules, List<ToolAbilityModule.Condition> conditionModules)
    {
        if (this.canApplyModules(entity, conditionModules))
        {
            this.applyModules(entity, itemStack, equipmentSlot, actionModules);
            return true;
        }
        return false;
    }

    private boolean canApplyModules(LivingEntity entity, List<ToolAbilityModule.Condition> conditionModules)
    {
        for(ToolAbilityModule.Condition condition : conditionModules)
        {
            if (!condition.isSituationFavorableToApplyModules(entity)) {return false;}
        }
        return true;
    }

    private void applyModules(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, List<ToolAbilityModule.Action> actionModules)
    {
        for(ToolAbilityModule.Action module : actionModules) {module.apply(entity, itemStack, equipmentSlot);}
    }
}
