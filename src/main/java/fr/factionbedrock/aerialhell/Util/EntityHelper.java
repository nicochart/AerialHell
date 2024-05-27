package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.*;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.ShadowPineBarrelMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.AerialTreeChestMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudGolemEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.*;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowAutomatonEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowFlyingSkullEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowTrollEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Snake.AbstractSnakeEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Spider.AbstractAerialHellSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Spider.CrystalSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Neutral.BoarEntity;
import fr.factionbedrock.aerialhell.Entity.Passive.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEnchantments;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import fr.factionbedrock.aerialhell.World.AerialHellTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EntityHelper
{
    public static boolean isCreativePlayer(Entity entity) {return entity instanceof Player player && player.isCreative();}

    public static boolean isSpectatorPlayer(Entity entity) {return entity instanceof Player player && player.isSpectator();}

    public static boolean isCreaOrSpecPlayer(Entity entity) {return entity instanceof Player player && (player.isCreative() || player.isSpectator());}

    public static boolean isLivingEntityUnderInTheCloudsEffect(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.get());}

    public static boolean isLivingEntityShadowImmune(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.SHADOW_IMMUNITY.get());}

    public static boolean isLivingEntityVulnerable(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.VULNERABILITY.get());}

    public static boolean isLivingEntityATraitor(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.TRAITOR.get());}

    public static boolean isLivingEntityUnderAerialHellPortalEffect(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.AERIAL_HELL_PORTAL.get());}

    public static boolean isLivingEntityInAerialHellPortal(LivingEntity entity) {return entity.level().getBlockState(entity.blockPosition()).is(AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get());}

    public static boolean isLivingEntityOnPortalCooldown(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.AERIAL_HELL_PORTAL_COOLDOWN.get()) || entity.isOnPortalCooldown();}

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

    public static boolean isGhostEntity(Entity entity)
    {
        return entity instanceof GhostSlimePirateEntity || entity instanceof GhostSlimeNinjaPirateEntity;
    }

    public static boolean isLightProjectile(Entity entity) {return entity instanceof LunaticProjectileEntity;}

    public static boolean isProjectile(Entity entity) {return entity instanceof AbstractArrow || entity instanceof ThrowableProjectile;}

    public static boolean isMudEntity(Entity entity)
    {
        return entity instanceof MudSoldierEntity || entity instanceof MudGolemEntity || entity instanceof MudCycleMageEntity || entity instanceof AerialTreeChestMimicEntity;
    }

    public static boolean isBossEntity(Entity entity)
    {
        return entity instanceof MudCycleMageEntity || entity instanceof LunaticPriestEntity || entity instanceof ChainedGodEntity || entity instanceof LilithEntity;
    }

    public static boolean isFeatheryEntity(Entity entity)
    {
        return entity instanceof Silverfish || entity instanceof FlyingMob || entity instanceof Chicken || entity instanceof Vex; //Vex includes FlyingSkulls
    }

    public static boolean isImmuneToBramblesDamage(Entity entity)
    {
        boolean isImmuneToAllBrambles = entity instanceof SandySheepEntity || entity instanceof BoarEntity || entity instanceof GlidingTurtleEntity || entity instanceof KodamaEntity || entity instanceof ShroomBoomEntity || entity instanceof EntEntity || entity instanceof AbstractSnakeEntity || entity instanceof AbstractSlimePirateEntity || entity instanceof EvilCowEntity || entity instanceof AbstractAerialHellSpiderEntity || isFeatheryEntity(entity) || entity instanceof VerdigrisZombieEntity;
        if (isImmuneToAllBrambles) {return true;}
        else {return isImmuneToSomeShadowDamage(entity);}
    }

    public static boolean isImmuneToSkyCactusCollision(Entity entity)
    {
        boolean isImmune = entity instanceof SandySheepEntity || entity instanceof KodamaEntity || entity instanceof AbstractSnakeEntity || entity instanceof MummyEntity || entity instanceof AbstractAerialHellSpiderEntity || isFeatheryEntity(entity);
        return isImmune;
    }

    public static boolean isImmuneToSolidEtherCollision(Entity entity)
    {
        if (entity instanceof LivingEntity livingEntity)
        {
            if (hasSolidEtherWalkerEnchantment(livingEntity) || isLivingEntityUnderInTheCloudsEffect(livingEntity) || isFeatheryEntity(entity)) {return false;}
            Iterable<ItemStack> stuff = livingEntity.getArmorSlots();
            for (ItemStack armorStack : stuff) {if (armorStack.getItem() == AerialHellBlocksAndItems.MAGMATIC_GEL_BOOTS.get()) {return false;}}
            return true;
        }
        return false;
    }

    public static boolean isImmuneToGhostBlockCollision(Entity entity)
    {
        if (entity instanceof LivingEntity livingEntity)
        {
            if (hasSolidEtherWalkerEnchantment(livingEntity) || isLivingEntityUnderInTheCloudsEffect(livingEntity) || isGhostEntity(livingEntity)) {return false;}
            Iterable<ItemStack> stuff = livingEntity.getArmorSlots();
            for (ItemStack armorStack : stuff) {if (armorStack.getItem() == AerialHellBlocksAndItems.MAGMATIC_GEL_BOOTS.get()) {return false;}}
            return true;
        }
        return false;
    }

    public static boolean isImmuneToChainedGodDrag(Entity entity)
    {
        return isCreaOrSpecPlayer(entity) || entity.getType() == AerialHellEntities.TORN_SPIRIT.get() || isBossEntity(entity);
    }

    public static boolean hasSolidEtherWalkerEnchantment(LivingEntity entity)
    {
        for (ItemStack equipmentItem : entity.getAllSlots())
        {
            if (EnchantmentHelper.getItemEnchantmentLevel(AerialHellEnchantments.SOLID_ETHER_WALKER.get(), equipmentItem) > 0) {return true;}
        }
        return false;
    }

    public static void multiplyDeltaMovement(Entity entity, double xzFactor, double yFactor)
    {
        entity.setDeltaMovement(entity.getDeltaMovement().multiply(xzFactor, yFactor, xzFactor));
    }

    public static void setAerialHellPortalEffect(LivingEntity entity)
    {
        if (!entity.level().isClientSide())
        {
            entity.addEffect(new MobEffectInstance(AerialHellMobEffects.AERIAL_HELL_PORTAL.get(), 120, 0));
        }
    }

    public static void setAfterTeleportationEffect(LivingEntity entity)
    {
        if (!entity.level().isClientSide())
        {
            entity.addEffect(new MobEffectInstance(AerialHellMobEffects.AERIAL_HELL_PORTAL_COOLDOWN.get(), 110, 0));
        }
    }

    public static boolean shouldLivingEntityHavePortalEffect(LivingEntity entity)
    {
        return !isLivingEntityOnPortalCooldown(entity) && isLivingEntityInAerialHellPortal(entity);
    }

    public static boolean isLivingEntityReadyToTeleport(LivingEntity entity)
    {
        return isLivingEntityUnderAerialHellPortalEffect(entity) && entity.getEffect(AerialHellMobEffects.AERIAL_HELL_PORTAL.get()).getDuration() < 20;
    }

    public static void tryTeleportEntityWithAerialHellPortal(Entity entity, BlockPos pos)
    {
        if(!entity.level().isClientSide && !pos.equals(entity.portalEntrancePos)) {entity.portalEntrancePos = pos.immutable();}
        Level level = entity.level();
        if(level != null)
        {
            MinecraftServer minecraftserver = level.getServer();
            ResourceKey<Level> destination = entity.level().dimension() == AerialHellDimensions.AERIAL_HELL_DIMENSION ? Level.OVERWORLD : AerialHellDimensions.AERIAL_HELL_DIMENSION;
            if(minecraftserver != null) {
                ServerLevel destinationWorld = minecraftserver.getLevel(destination);
                if(destinationWorld != null && minecraftserver.isNetherEnabled() && !entity.isPassenger()) {
                    entity.level().getProfiler().push("aerialhell_portal");
                    entity.setPortalCooldown();
                    entity.changeDimension(destinationWorld, new AerialHellTeleporter(destinationWorld));
                    entity.level().getProfiler().pop();
                }
            }
        }
    }

    public static void addBatParticle(LivingEntity entity, RandomSource rand, int number)
    {
        for (int i=0; i<number; i++)
        {
            entity.level().addParticle(AerialHellParticleTypes.SHADOW_TROLL_BAT.get(), entity.getX() + rand.nextFloat() - 0.5, entity.getY() + 2 * rand.nextFloat(), entity.getZ() + rand.nextFloat() - 0.5, 2 * (rand.nextFloat()) - 0.5, -0.3D, 2 * (rand.nextFloat() - 0.5));
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
