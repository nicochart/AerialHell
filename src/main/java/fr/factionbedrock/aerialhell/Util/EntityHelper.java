package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Entity.*;
import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.*;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.ShadowPineBarrelMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Passive.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellEnchantments;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.item.ItemStack;

public class EntityHelper
{
    public static boolean isLivingEntityShadowImmune(LivingEntity entity) {return entity.isPotionActive(AerialHellPotionEffects.SHADOW_IMMUNITY.get());}

    public static boolean isLivingEntityVulnerable(LivingEntity entity) {return entity.isPotionActive(AerialHellPotionEffects.VULNERABILITY.get());}

    public static boolean isImmuneToSomeShadowDamage(Entity entity)
    {
        boolean isShadowImmune = entity instanceof LivingEntity && isLivingEntityShadowImmune((LivingEntity)entity);
        boolean isShadowEntity = isShadowEntity(entity);
        return isShadowImmune || isShadowEntity;
    }

    public static boolean isShadowEntity(Entity entity)
    {
        return entity instanceof EvilCowEntity || entity instanceof ShadowAutomatonEntity || entity instanceof ShadowTrollEntity || entity instanceof ShadowSpiderEntity || entity instanceof ShadowPineBarrelMimicEntity || entity instanceof LilithEntity || entity instanceof EndermanEntity;
    }

    public static boolean isLightEntity(Entity entity)
    {
        return entity instanceof CrystalGolemEntity || entity instanceof CrystalCaterpillarEntity || entity instanceof CrystalSlimeEntity || entity instanceof CrystalSpiderEntity || entity instanceof LunaticPriestEntity;
    }

    public static boolean isFeatheryEntity(Entity entity)
    {
        return entity instanceof SilverfishEntity || entity instanceof FlyingEntity;
    }

    public static boolean isImmuneToBramblesDamage(Entity entity)
    {
        boolean isImmuneToAllBrambles = entity instanceof SandySheepEntity || entity instanceof GlidingTurtleEntity || entity instanceof ShroomBoomEntity || entity instanceof EvilCowEntity || entity instanceof AbstractAerialHellSpiderEntity;
        if (isImmuneToAllBrambles) {return true;}
        else {return isImmuneToSomeShadowDamage(entity);}
    }

    public static boolean hasSolidEtherWalkerEnchantment(LivingEntity entity)
    {
        for (ItemStack equipmentItem : entity.getEquipmentAndArmor())
        {
            if (EnchantmentHelper.getEnchantmentLevel(AerialHellEnchantments.SOLID_ETHER_WALKER.get(), equipmentItem) > 0) {return true;}
        }
        return false;
    }
}
