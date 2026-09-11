package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.LivingEntity;

public interface ShadowMisleadableEntity extends MisleadableEntity
{
    //MisleadableEntity specific for Shadow Creatures
    //see MisleadableEntity for more info

    @Override default boolean isMisleadedBy(LivingEntity livingEntity)
    {
        return EntityHelper.isLivingEntityMisleadingShadow(livingEntity);
    }
}
