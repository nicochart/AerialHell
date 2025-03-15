package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.AerialHellAnimalEntity;
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
import fr.factionbedrock.aerialhell.Registry.AerialHellEnchantments;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ChunkBiomeDataS2CPacket;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityHelper
{
    public static boolean isCreativePlayer(Entity entity) {return entity instanceof PlayerEntity player && player.isCreative();}

    public static boolean isSpectatorPlayer(Entity entity) {return entity instanceof PlayerEntity player && player.isSpectator();}

    public static boolean isCreaOrSpecPlayer(Entity entity) {return entity instanceof PlayerEntity player && (player.isCreative() || player.isSpectator());}

    public static boolean isLivingEntityUnderInTheCloudsEffect(LivingEntity entity) {return entity.hasStatusEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS);}

    public static boolean isLivingEntityShadowImmune(LivingEntity entity) {return entity.hasStatusEffect(AerialHellMobEffects.SHADOW_IMMUNITY) || isLivingEntityShadowBind(entity);}

    public static boolean isLivingEntityShadowBind(LivingEntity entity) {return entity.hasStatusEffect(AerialHellMobEffects.SHADOW_BIND) && entity.getStatusEffect(AerialHellMobEffects.SHADOW_BIND).getDuration() > 1;}

    public static boolean isLivingEntityVulnerable(LivingEntity entity) {return entity.hasStatusEffect(AerialHellMobEffects.VULNERABILITY);}

    public static boolean isLivingEntityATraitor(LivingEntity entity) {return entity.hasStatusEffect(AerialHellMobEffects.TRAITOR);}

    public static boolean isLivingEntityUnderAerialHellPortalEffect(LivingEntity entity) {return entity.hasStatusEffect(AerialHellMobEffects.AERIAL_HELL_PORTAL);}

    public static boolean isLivingEntityInAerialHellPortal(LivingEntity entity) {return /*entity.getWorld().getBlockState(entity.getBlockPos()).isOf(AerialHellBlocks.AERIAL_HELL_PORTAL)*/false;}

    public static boolean isLivingEntityOnPortalCooldown(LivingEntity entity) {return /*entity.hasStatusEffect(AerialHellMobEffects.AERIAL_HELL_PORTAL_COOLDOWN) || entity.isOnPortalCooldown()*/false;}

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

    public static boolean isGhostEntity(Entity entity)
    {
        return entity instanceof GhostSlimePirateEntity || entity instanceof GhostSlimeNinjaPirateEntity;
    }

    public static boolean isLightProjectile(Entity entity) {return entity instanceof LunaticProjectileEntity;}

    public static boolean isProjectile(Entity entity) {return entity instanceof PersistentProjectileEntity || entity instanceof ThrownItemEntity;}

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
        return entity instanceof SilverfishEntity || entity instanceof FlyingEntity || entity instanceof ChickenEntity || entity instanceof VexEntity; //Vex includes FlyingSkulls
    }

    public static boolean isImmuneToBramblesDamage(Entity entity)
    {
        boolean isImmuneToAllBrambles = entity instanceof SandySheepEntity || entity instanceof BoarEntity || entity instanceof GlidingTurtleEntity || entity instanceof KodamaEntity || entity instanceof ShroomBoomEntity || entity instanceof EntEntity || entity instanceof AbstractSnakeEntity || entity instanceof AbstractSlimePirateEntity || entity instanceof EvilCowEntity || entity instanceof AbstractAerialHellSpiderEntity || isFeatheryEntity(entity) || entity instanceof VerdigrisZombieEntity;
        if (isImmuneToAllBrambles) {return true;}
        else {return isImmuneToSomeShadowDamage(entity);}
    }

    public static boolean isImmuneToSkyCactusCollision(Entity entity)
    {
        boolean isImmune = entity instanceof AerialHellAnimalEntity || entity instanceof BoarEntity || entity instanceof AbstractSnakeEntity || entity instanceof MummyEntity || entity instanceof AbstractAerialHellSpiderEntity || isFeatheryEntity(entity);
        return isImmune;
    }

    public static boolean isImmuneToSolidEtherCollision(Entity entity)
    {
        if (entity instanceof LivingEntity livingEntity)
        {
            if (hasSolidEtherWalkerEnchantment(livingEntity) || isLivingEntityUnderInTheCloudsEffect(livingEntity) || isFeatheryEntity(entity)) {return false;}
            Iterable<ItemStack> stuff = livingEntity.getArmorItems();
            for (ItemStack armorStack : stuff) {if (armorStack.getItem() == AerialHellItems.MAGMATIC_GEL_BOOTS) {return false;}}
            return true;
        }
        return false;
    }

    public static boolean isImmuneToGhostBlockCollision(Entity entity)
    {
        if (entity instanceof LivingEntity livingEntity)
        {
            if (hasSolidEtherWalkerEnchantment(livingEntity) || isLivingEntityUnderInTheCloudsEffect(livingEntity) || isGhostEntity(livingEntity)) {return false;}
            Iterable<ItemStack> stuff = livingEntity.getArmorItems();
            for (ItemStack armorStack : stuff) {if (armorStack.getItem() == AerialHellItems.MAGMATIC_GEL_BOOTS) {return false;}}
            return true;
        }
        return false;
    }

    public static boolean isImmuneToChainedGodDrag(Entity entity)
    {
        return isCreaOrSpecPlayer(entity) || entity.getType() == AerialHellEntities.TORN_SPIRIT || isBossEntity(entity);
    }

    public static boolean hasSolidEtherWalkerEnchantment(LivingEntity entity)
    {
        return hasEnchantment(entity, AerialHellEnchantments.SOLID_ETHER_WALKER);
        /*for (ItemStack equipmentItem : entity.getAllSlots())
        {
            return EnchantmentHelper.getTagEnchantmentLevel(enchantment.get(), equipmentItem) > 0;
        }*/
    }

    public static boolean hasEnchantment(LivingEntity entity, RegistryKey<Enchantment> enchantmentKey)
    {
        Optional<RegistryEntry.Reference<Enchantment>> enchantment = entity.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getOptional(enchantmentKey);
        if (enchantment.isPresent())
        {
            return EnchantmentHelper.getEquipmentLevel(enchantment.get(), entity) > 0;
        }
        return false;
    }

    public static void multiplyDeltaMovement(Entity entity, double xzFactor, double yFactor)
    {
        entity.setVelocity(entity.getVelocity().multiply(xzFactor, yFactor, xzFactor));
    }

    public static void setAerialHellPortalEffect(LivingEntity entity)
    {
        if (!entity.getWorld().isClient())
        {
            entity.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.AERIAL_HELL_PORTAL, 120, 0));
        }
    }

    public static void setAfterTeleportationEffect(LivingEntity entity, int duration)
    {
        if (!entity.getWorld().isClient())
        {
            entity.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.AERIAL_HELL_PORTAL_COOLDOWN, duration, 0));
        }
    }

    public static boolean shouldLivingEntityHavePortalEffect(LivingEntity entity)
    {
        return !isLivingEntityOnPortalCooldown(entity) && isLivingEntityInAerialHellPortal(entity);
    }

    public static boolean isLivingEntityReadyToTeleport(LivingEntity entity)
    {
        return isLivingEntityUnderAerialHellPortalEffect(entity) && entity.getStatusEffect(AerialHellMobEffects.AERIAL_HELL_PORTAL).getDuration() < 20;
    }

    public static void addBatParticle(LivingEntity entity, Random rand, int number)
    {
        for (int i=0; i<number; i++)
        {
            entity.getWorld().addParticle(AerialHellParticleTypes.SHADOW_TROLL_BAT, entity.getX() + rand.nextFloat() - 0.5, entity.getY() + 2 * rand.nextFloat(), entity.getZ() + rand.nextFloat() - 0.5, 2 * (rand.nextFloat()) - 0.5, -0.3D, 2 * (rand.nextFloat() - 0.5));
        }
    }

    public static boolean isLivingEntityMisleadingLunar(LivingEntity entity)
    {
        return ItemHelper.getItemInTagCount(entity.getArmorItems(), AerialHellTags.Items.LUNATIC_STUFF) >= 4 && !isLivingEntityATraitor(entity);
    }

    public static boolean isLivingEntityMisleadingShadow(LivingEntity entity)
    {
        return isLivingEntityShadowBind(entity) && !isLivingEntityATraitor(entity);
    }

    //from in net.minecraft.server.world.ServerChunkLoadingManager sendChunkBiomePackets(..) method
    public static void refreshChunkColors(ServerPlayerEntity player, ServerWorld world, int radius)
    {
        BlockBox boundingbox = BlockHelper.getQuantizedBoundingBox(player.getSteppingPos().up(), radius);
        List<Chunk> chunkAccessList = BlockHelper.getChunkAccessListForBoundingBox(world, boundingbox);

        List<WorldChunk> chunkList = new ArrayList<>();
        for (Chunk chunkaccess : chunkAccessList)
        {
            if (chunkaccess instanceof WorldChunk levelchunk) {chunkList.add(levelchunk);}
            else {chunkList.add(world.getChunk(chunkaccess.getPos().x, chunkaccess.getPos().z));}
        }

        player.networkHandler.sendPacket(ChunkBiomeDataS2CPacket.create(chunkList));
    }

    public static void handleProjectileImpactWithEntity(ProjectileEntity projectileEntity, EntityHitResult hitResult, CallbackInfo ci)
    {
        boolean isLightProjectile = EntityHelper.isLightProjectile(projectileEntity);

        Entity hitEntity = hitResult.getEntity();

        if ((hitEntity instanceof ShadowTrollEntity || hitEntity instanceof ShadowAutomatonEntity) && !isLightProjectile) {ci.cancel();}

        if (EntityHelper.isGhostEntity(hitEntity) && !isLightProjectile)
        {
            if (EntityHelper.isImmuneToGhostBlockCollision(projectileEntity.getOwner())) {ci.cancel();}
        }
    }

    //copy of net.minecraft.client.gui.hud.InGameOverlayRenderer method of same name
    @Nullable public static BlockState getInWallBlockState(PlayerEntity player)
    {
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (int i = 0; i < 8; i++)
        {
            double eyeX = player.getX() + (double)(((float)((i >> 0) % 2) - 0.5F) * player.getWidth() * 0.8F);
            double eyeY = player.getEyeY() + (double)(((float)((i >> 1) % 2) - 0.5F) * 0.1F * player.getScale());
            double eyeZ = player.getZ() + (double)(((float)((i >> 2) % 2) - 0.5F) * player.getWidth() * 0.8F);
            mutable.set(eyeX, eyeY, eyeZ);
            BlockState blockState = player.getWorld().getBlockState(mutable);
            if (blockState.getRenderType() != BlockRenderType.INVISIBLE && blockState.shouldBlockVision(player.getWorld(), mutable)) {return blockState;}
        }
        return null;
    }

    @Nullable public static FluidState getInLiquidFluidState(PlayerEntity player)
    {
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (int i = 0; i < 8; i++)
        {
            double eyeX = player.getX() + (double)(((float)((i >> 0) % 2) - 0.5F) * player.getWidth() * 0.8F);
            double eyeY = player.getEyeY() + (double)(((float)((i >> 1) % 2) - 0.5F) * 0.1F * player.getScale());
            double eyeZ = player.getZ() + (double)(((float)((i >> 2) % 2) - 0.5F) * player.getWidth() * 0.8F);
            mutable.set(eyeX, eyeY, eyeZ);
            FluidState fluidState = player.getWorld().getFluidState(mutable);
            if (!fluidState.isOf(Fluids.EMPTY)) {return fluidState;}
        }
        return null;
    }

    public static void debugSnakeEntity(AbstractSnakeEntity snakeEntity, PlayerEntity messageReceiver)
    {
        if (!snakeEntity.getWorld().isClient)
        {
            AbstractSnakeEntity nextBodyPart = snakeEntity.getNextBodyPart(), previousBodyPart = snakeEntity.getPreviousBodyPart();
            String nextBodyPartString = "[", previousBodyPartString = "[";
            if (nextBodyPart != null)
            {
                BlockPos pos = nextBodyPart.getBlockPos();
                nextBodyPartString += (nextBodyPart.isDead() ? "Dead" : "Alive, health = "+nextBodyPart.getHealth()) + ", pos = " + pos.getX()+" "+pos.getY()+" "+pos.getZ() + ", distance = "+ snakeEntity.distanceTo(nextBodyPart);
                if (nextBodyPart.hasStatusEffect(StatusEffects.GLOWING)) {nextBodyPartString += ", isGlowing = true";}
                if (nextBodyPart.isAiDisabled()) {nextBodyPartString += ", isAiDisabled = true";}
                if (!nextBodyPart.isAttackable()) {nextBodyPartString += ", isAttackable = false";}
                if (nextBodyPart.isRemoved()) {nextBodyPartString += ", isRemoved = true"+nextBodyPart.getRemovalReason()+")";}
                if (nextBodyPart.isInvisible()) {nextBodyPartString += ", isInvisible = true";}
                nextBodyPart.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 400, 0, false, false, false));
            }
            else {nextBodyPartString += "null";}
            if (previousBodyPart != null)
            {
                BlockPos pos = previousBodyPart.getBlockPos();
                previousBodyPartString += (previousBodyPart.isDead() ? "Dead" : "Alive, health = "+previousBodyPart.getHealth()) + ", pos = " + pos.getX()+" "+pos.getY()+" "+pos.getZ() + ", distance = "+snakeEntity.distanceTo(previousBodyPart);
                if (previousBodyPart.hasStatusEffect(StatusEffects.GLOWING)) {previousBodyPartString += ", isGlowing = true";}
                if (previousBodyPart.isAiDisabled()) {previousBodyPartString += ", isAiDisabled = true";}
                if (!previousBodyPart.isAttackable()) {previousBodyPartString += ", isAttackable = false";}
                if (previousBodyPart.isRemoved()) {previousBodyPartString += ", isRemoved = true ("+previousBodyPart.getRemovalReason()+")";}
                if (previousBodyPart.isInvisible()) {previousBodyPartString += ", isInvisible = true";}
                previousBodyPart.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 400, 0, false, false, false));
            }
            else {previousBodyPartString += "null";}
            nextBodyPartString += "]"; previousBodyPartString += "]";
            messageReceiver.sendMessage(Text.literal("Entity "+snakeEntity.getType()+" : isHead = "+snakeEntity.isHead()), false);
            messageReceiver.sendMessage(Text.literal("nextBodyPart = "+nextBodyPartString), false);
            messageReceiver.sendMessage(Text.literal("previousBodyPart = "+previousBodyPartString), false);
        }
    }
}
