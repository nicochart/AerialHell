package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Effect.AerialHellEffect;
import fr.factionbedrock.aerialhell.Entity.*;
import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.*;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.ShadowPineBarrelMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.AerialTreeChestMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Passive.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import fr.factionbedrock.aerialhell.Registry.*;
import fr.factionbedrock.aerialhell.World.AerialHellTeleporter;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class EntityHelper
{
    public static boolean isCreativePlayer(Entity entity) {return entity instanceof PlayerEntity && ((PlayerEntity)entity).isCreative();}

    public static boolean isLivingEntityShadowImmune(LivingEntity entity) {return entity.isPotionActive(AerialHellPotionEffects.SHADOW_IMMUNITY.get());}

    public static boolean isLivingEntityVulnerable(LivingEntity entity) {return entity.isPotionActive(AerialHellPotionEffects.VULNERABILITY.get());}

    public static boolean isLivingEntityATraitor(LivingEntity entity) {return entity.isPotionActive(AerialHellPotionEffects.TRAITOR.get());}

    public static boolean isLivingEntityUnderAerialHellPortalEffect(LivingEntity entity) {return entity.isPotionActive(AerialHellPotionEffects.AERIAL_HELL_PORTAL.get());}

    public static boolean isLivingEntityInAerialHellPortal(LivingEntity entity) {return entity.getEntityWorld().getBlockState(entity.getPosition()).isIn(AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get());}

    public static boolean isLivingEntityOnPortalCooldown(LivingEntity entity) {return entity.isPotionActive(AerialHellPotionEffects.AERIAL_HELL_PORTAL_COOLDOWN.get()) || entity.func_242280_ah(); /*isOnPortalCooldown*/}

    public static boolean isPiercingArrow(Entity projectile) {return projectile instanceof ArrowEntity && ((ArrowEntity) projectile).getPierceLevel() > 0;}

    public static boolean isImmuneToSomeShadowDamage(Entity entity)
    {
        boolean isShadowImmune = entity instanceof LivingEntity && isLivingEntityShadowImmune((LivingEntity)entity);
        boolean isShadowEntity = isShadowEntity(entity);
        return isShadowImmune || isShadowEntity;
    }

    public static boolean isShadowEntity(Entity entity)
    {
        return entity instanceof EvilCowEntity || entity instanceof ShadowAutomatonEntity || entity instanceof ShadowTrollEntity || entity instanceof ShadowFlyingSkullEntity || entity instanceof ShadowSpiderEntity || entity instanceof ShadowPineBarrelMimicEntity || entity instanceof LilithEntity || entity instanceof EndermanEntity;
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

    public static void setAerialHellPortalEffect(LivingEntity entity)
    {
        if (!entity.getEntityWorld().isRemote())
        {
            entity.addPotionEffect(new EffectInstance(AerialHellPotionEffects.AERIAL_HELL_PORTAL.get(), 120, 0));
        }
    }

    public static void setAfterTeleportationEffect(LivingEntity entity)
    {
        if (!entity.getEntityWorld().isRemote())
        {
            entity.addPotionEffect(new EffectInstance(AerialHellPotionEffects.AERIAL_HELL_PORTAL_COOLDOWN.get(), 110, 0));
        }
    }

    public static boolean shouldLivingEntityHavePortalEffect(LivingEntity entity)
    {
        return !isLivingEntityOnPortalCooldown(entity) && isLivingEntityInAerialHellPortal(entity);
    }

    public static boolean isLivingEntityReadyToTeleport(LivingEntity entity)
    {
        return isLivingEntityUnderAerialHellPortalEffect(entity) && entity.getActivePotionEffect(AerialHellPotionEffects.AERIAL_HELL_PORTAL.get()).getDuration() < 20;
    }

    public static void tryTeleportEntityWithAerialHellPortal(Entity entity, BlockPos pos)
    {
        if(!entity.world.isRemote && !pos.equals(entity.field_242271_ac)) {entity.field_242271_ac = pos.toImmutable();}
        World serverworld = entity.world;
        if(serverworld != null)
        {
            MinecraftServer minecraftserver = serverworld.getServer();
            RegistryKey<World> worldDestination = entity.world.getDimensionKey() == AerialHellDimensions.AERIAL_HELL_DIMENSION ? World.OVERWORLD : AerialHellDimensions.AERIAL_HELL_DIMENSION;
            if (minecraftserver != null)
            {
                ServerWorld destination = minecraftserver.getWorld(worldDestination);
                if(destination != null && minecraftserver.getAllowNether() && !entity.isPassenger())
                {
                    entity.world.getProfiler().startSection("aerialhell_portal");
                    entity.func_242279_ag();
                    entity.changeDimension(destination, new AerialHellTeleporter(destination));
                    entity.world.getProfiler().endSection();
                }
            }
        }
    }

    public static void addBatParticle(LivingEntity entity, Random rand, int number)
    {
        for (int i=0; i<number; i++)
        {
            entity.world.addParticle(AerialHellParticleTypes.SHADOW_TROLL_BAT.get(), entity.getPosX() + rand.nextFloat() - 0.5, entity.getPosY() + 2 * rand.nextFloat(), entity.getPosZ() + rand.nextFloat() - 0.5, 2 * (rand.nextFloat()) - 0.5, -0.3D, 2 * (rand.nextFloat() - 0.5));
        }
    }

    public static boolean isLivingEntityMisleadingLunar(LivingEntity entity)
    {
        return ItemHelper.getItemInTagCount(entity.getArmorInventoryList(), AerialHellTags.Items.LUNATIC_STUFF) >= 4 && !isLivingEntityATraitor(entity);
    }

    public static boolean isLivingEntityMisleadingShadow(LivingEntity entity)
    {
        return ItemHelper.getItemInTagCount(entity.getArmorInventoryList(), AerialHellTags.Items.SHADOW_ARMOR) >= 4 && !isLivingEntityATraitor(entity);
    }
}
