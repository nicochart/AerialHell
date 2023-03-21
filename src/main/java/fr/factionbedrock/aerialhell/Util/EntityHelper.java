package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Entity.*;
import fr.factionbedrock.aerialhell.Entity.Monster.*;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.ShadowPineBarrelMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Passive.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class EntityHelper
{
    public static boolean isLivingEntityShadowImmune(LivingEntity entity)
    {
        return entity.getActivePotionEffect(AerialHellPotionEffects.SHADOW_IMMUNITY.get()) != null;
    }

    public static boolean isImmuneToSomeShadowDamage(Entity entity)
    {
        boolean isShadowImmune = entity instanceof LivingEntity && isLivingEntityShadowImmune((LivingEntity)entity);
        boolean isShadowEntity = entity instanceof EvilCowEntity || entity instanceof ShadowTrollEntity || entity instanceof ShadowSpiderEntity || entity instanceof ShadowPineBarrelMimicEntity;
        return isShadowImmune || isShadowEntity;
    }

    public static boolean isImmuneToBramblesDamage(Entity entity)
    {
        boolean isImmuneToAllBrambles = entity instanceof SandySheepEntity || entity instanceof GlidingTurtleEntity || entity instanceof ShroomBoomEntity || entity instanceof EvilCowEntity || entity instanceof AbstractAerialHellSpiderEntity;
        if (isImmuneToAllBrambles) {return true;}
        else {return isImmuneToSomeShadowDamage(entity);}
    }
}
