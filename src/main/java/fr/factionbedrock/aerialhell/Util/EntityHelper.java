package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Block.AerialHellPortalBlock;
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
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.game.ClientboundChunksBiomesPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.portal.DimensionTransition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityHelper
{
    public static boolean isCreativePlayer(Entity entity) {return entity instanceof Player player && player.isCreative();}

    public static boolean isSpectatorPlayer(Entity entity) {return entity instanceof Player player && player.isSpectator();}

    public static boolean isCreaOrSpecPlayer(Entity entity) {return entity instanceof Player player && (player.isCreative() || player.isSpectator());}

    public static boolean isLivingEntityUnderInTheCloudsEffect(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.getDelegate());}

    public static boolean isLivingEntityShadowImmune(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.SHADOW_IMMUNITY.getDelegate()) || isLivingEntityShadowBind(entity);}

    public static boolean isLivingEntityShadowBind(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.SHADOW_BIND.getDelegate()) && entity.getEffect(AerialHellMobEffects.SHADOW_BIND.getDelegate()).getDuration() > 1;}

    public static boolean isLivingEntityVulnerable(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.VULNERABILITY.getDelegate());}

    public static boolean isLivingEntityATraitor(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.TRAITOR.getDelegate());}

    public static boolean isLivingEntityUnderAerialHellPortalEffect(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.AERIAL_HELL_PORTAL.getDelegate());}

    public static boolean isLivingEntityInAerialHellPortal(LivingEntity entity) {return entity.level().getBlockState(entity.blockPosition()).is(AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get());}

    public static boolean isLivingEntityOnPortalCooldown(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.AERIAL_HELL_PORTAL_COOLDOWN.getDelegate()) || entity.isOnPortalCooldown();}

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
        return hasEnchantment(entity, AerialHellEnchantments.SOLID_ETHER_WALKER);
        /*for (ItemStack equipmentItem : entity.getAllSlots())
        {
            return EnchantmentHelper.getTagEnchantmentLevel(enchantment.get(), equipmentItem) > 0;
        }*/
    }

    public static boolean hasEnchantment(LivingEntity entity, ResourceKey<Enchantment> enchantmentKey)
    {
        Optional<Holder.Reference<Enchantment>> enchantment = entity.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolder(enchantmentKey);
        if (enchantment.isPresent())
        {
            return EnchantmentHelper.getEnchantmentLevel(enchantment.get().getDelegate(), entity) > 0;

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
            entity.addEffect(new MobEffectInstance(AerialHellMobEffects.AERIAL_HELL_PORTAL.getDelegate(), 120, 0));
        }
    }

    public static void setAfterTeleportationEffect(LivingEntity entity, int duration)
    {
        if (!entity.level().isClientSide())
        {
            entity.addEffect(new MobEffectInstance(AerialHellMobEffects.AERIAL_HELL_PORTAL_COOLDOWN.getDelegate(), duration, 0));
        }
    }

    public static boolean shouldLivingEntityHavePortalEffect(LivingEntity entity)
    {
        return !isLivingEntityOnPortalCooldown(entity) && isLivingEntityInAerialHellPortal(entity);
    }

    public static boolean isLivingEntityReadyToTeleport(LivingEntity entity)
    {
        return isLivingEntityUnderAerialHellPortalEffect(entity) && entity.getEffect(AerialHellMobEffects.AERIAL_HELL_PORTAL.getDelegate()).getDuration() < 20;
    }

    public static void tryTeleportEntityWithAerialHellPortal(Entity entity, AerialHellPortalBlock portalBlock, BlockPos pos)
    {
        if (entity.level() instanceof ServerLevel serverlevel)
        {
            DimensionTransition dimensiontransition = portalBlock.getPortalDestination(serverlevel, entity, pos);
            if (dimensiontransition != null)
            {
                ServerLevel serverlevel1 = dimensiontransition.newLevel();
                if (serverlevel.getServer().isLevelEnabled(serverlevel1) && (serverlevel1.dimension() == serverlevel.dimension() || entity.canChangeDimensions(serverlevel, serverlevel1)))
                {
                    entity.changeDimension(dimensiontransition);
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
        return isLivingEntityShadowBind(entity) && !isLivingEntityATraitor(entity);
    }

    //from in net.minecraft.server.level.ChunkMap resendBiomesForChunks(..) method
    public static void refreshChunkColors(ServerPlayer player, ServerLevel level, int radius)
    {
        BoundingBox boundingbox = BlockHelper.getQuantizedBoundingBox(player.getOnPos().above(), radius);
        List<ChunkAccess> chunkAccessList = BlockHelper.getChunkAccessListForBoundingBox(level, boundingbox);

        List<LevelChunk> chunkList = new ArrayList<>();
        for (ChunkAccess chunkaccess : chunkAccessList)
        {
            if (chunkaccess instanceof LevelChunk levelchunk) {chunkList.add(levelchunk);}
            else {chunkList.add(level.getChunk(chunkaccess.getPos().x, chunkaccess.getPos().z));}
        }

        player.connection.send(ClientboundChunksBiomesPacket.forChunks(chunkList));
    }
}
