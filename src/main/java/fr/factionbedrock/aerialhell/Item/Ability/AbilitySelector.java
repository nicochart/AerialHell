package fr.factionbedrock.aerialhell.Item.Ability;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AbilitySelector
{
    private final List<ItemAbility> abilities;
    private AbilitySelector() {this.abilities = new ArrayList<>();}

    public static AbilitySelector of(ItemAbility firstAbility)
    {
        AbilitySelector selector = new AbilitySelector();
        selector.nextAbility(firstAbility);
        return selector;
    }

    public AbilitySelector nextAbility(ItemAbility nextAbility) {this.abilities.add(nextAbility); return this;}

    public boolean tryUseAbility(LivingEntity entity, ItemStack stack, @Nullable EquipmentSlot slot, AbilityUseSituation useSituation)
    {
        for (ItemAbility ability : this.abilities)
        {
            if (ability.tryApplyModules(entity, stack, slot, useSituation)) {return true;}
        }
        return false;
    }
}
