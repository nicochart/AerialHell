package fr.factionbedrock.aerialhell.Item.Ability.Module;

import fr.factionbedrock.aerialhell.Item.Ability.ModuleUseSituation;

public abstract class AbilityModule
{
    private final ModuleUseSituation useSituation;

    public AbilityModule(ModuleUseSituation useSituation)
    {
        this.useSituation = useSituation;
    }

    public boolean isPassive() {return this.useSituation == ModuleUseSituation.PASSIVE;}
    public boolean isOnUse() {return this.useSituation == ModuleUseSituation.ON_USE;}
    public boolean isOnHurtEnemy() {return this.useSituation == ModuleUseSituation.ON_HURT_ENEMY;}

}
