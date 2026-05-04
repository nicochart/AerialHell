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
    private final List<String> abilitiesDescIds;

    private AbilitySelector()
    {
        this.abilities = new ArrayList<>();
        this.abilitiesDescIds = new ArrayList<>();
    }

    public static AbilitySelector of(ItemAbility firstAbility)
    {
        AbilitySelector selector = new AbilitySelector();
        selector.nextAbility(firstAbility);
        return selector;
    }

    public AbilitySelector nextAbility(ItemAbility nextAbility)
    {
        this.abilities.add(nextAbility);
        this.abilitiesDescIds.add(nextAbility.getDescId());
        return this;
    }

    public boolean tryUseAbility(AbilityUseSituation useSituation)
    {
        for (ItemAbility ability : this.abilities)
        {
            if (ability.tryApplyModules(useSituation)) {return true;}
        }
        return false;
    }

    public List<String> getAbilitiesDescIds() {return this.abilitiesDescIds;}
}
