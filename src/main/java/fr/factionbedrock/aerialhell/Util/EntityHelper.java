package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.*;
import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.*;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.ShadowPineBarrelMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.AerialTreeChestMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Passive.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellEnchantments;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

public class EntityHelper
{
    public static boolean isLivingEntityShadowImmune(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.SHADOW_IMMUNITY.get());}

    public static boolean isLivingEntityVulnerable(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.VULNERABILITY.get());}

    public static boolean isLivingEntityATraitor(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.TRAITOR.get());}

    public static boolean isImmuneToSomeShadowDamage(Entity entity)
    {
        boolean isShadowImmune = entity instanceof LivingEntity && isLivingEntityShadowImmune((LivingEntity)entity);
        boolean isShadowEntity = isShadowEntity(entity);
        return isShadowImmune || isShadowEntity;
    }

    public static boolean isShadowEntity(Entity entity)
    {
        return entity instanceof EvilCowEntity || entity instanceof ShadowAutomatonEntity || entity instanceof ShadowTrollEntity || entity instanceof ShadowFlyingSkullEntity || entity instanceof ShadowSpiderEntity || entity instanceof ShadowPineBarrelMimicEntity || entity instanceof LilithEntity || entity instanceof EnderMan;
    }

    public static boolean isLightEntity(Entity entity)
    {
        return entity instanceof CrystalGolemEntity || entity instanceof CrystalCaterpillarEntity || entity instanceof CrystalSlimeEntity || entity instanceof CrystalSpiderEntity || entity instanceof LunaticPriestEntity;
    }

    public static boolean isLightProjectile(Entity entity)
    {
        return entity instanceof LunaticProjectileEntity;
    }

    public static boolean isMudEntity(Entity entity)
    {
        return entity instanceof MudSoldierEntity || entity instanceof MudGolemEntity || entity instanceof MudCycleMageEntity || entity instanceof AerialTreeChestMimicEntity;
    }

    public static boolean isFeatheryEntity(Entity entity)
    {
        return entity instanceof Silverfish || entity instanceof FlyingMob;
    }

    public static boolean isImmuneToBramblesDamage(Entity entity)
    {
        boolean isImmuneToAllBrambles = entity instanceof SandySheepEntity || entity instanceof GlidingTurtleEntity || entity instanceof ShroomBoomEntity || entity instanceof EvilCowEntity || entity instanceof AbstractAerialHellSpiderEntity;
        if (isImmuneToAllBrambles) {return true;}
        else {return isImmuneToSomeShadowDamage(entity);}
    }

    public static boolean hasSolidEtherWalkerEnchantment(LivingEntity entity)
    {
        for (ItemStack equipmentItem : entity.getAllSlots())
        {
            if (EnchantmentHelper.getItemEnchantmentLevel(AerialHellEnchantments.SOLID_ETHER_WALKER.get(), equipmentItem) > 0) {return true;}
        }
        return false;
    }

    public static void addBatParticle(LivingEntity entity, Random rand, int number)
    {
        for (int i=0; i<number; i++)
        {
            entity.level.addParticle(AerialHellParticleTypes.SHADOW_TROLL_BAT.get(), entity.getX() + rand.nextFloat() - 0.5, entity.getY() + 2 * rand.nextFloat(), entity.getZ() + rand.nextFloat() - 0.5, 2 * (rand.nextFloat()) - 0.5, -0.3D, 2 * (rand.nextFloat() - 0.5));
        }
    }

    public static boolean isLivingEntityMisleadingLunar(LivingEntity entity)
    {
        return ItemHelper.getItemInTagCount(entity.getArmorSlots(), AerialHellTags.Items.LUNATIC_STUFF) >= 4 && !isLivingEntityATraitor(entity);
    }

    public static boolean isLivingEntityMisleadingShadow(LivingEntity entity)
    {
        return ItemHelper.getItemInTagCount(entity.getArmorSlots(), AerialHellTags.Items.SHADOW_ARMOR) >= 4 && !isLivingEntityATraitor(entity);
    }
}
