package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.LivingEntity;

public interface LunarMisleadableEntity extends MisleadableEntity
{
    //MisleadableEntity specific for Lunar Creatures
    //see MisleadableEntity for more info

    @Override default boolean isMisleadedBy(LivingEntity livingEntity)
    {
        return EntityHelper.isLivingEntityMisleadingLunar(livingEntity);
    }
}
