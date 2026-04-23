package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Block.AerialHellPortalBlock;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowAutomatonEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowTrollEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Snake.AbstractSnakeEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellEnchantments;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundChunksBiomesPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.EntityHitResult;

public class EntityHelper
{
    public static boolean isCreativePlayer(Entity entity) {return entity instanceof Player player && player.isCreative();}

    public static boolean isSpectatorPlayer(Entity entity) {return entity instanceof Player player && player.isSpectator();}

    public static boolean isCreaOrSpecPlayer(Entity entity) {return entity instanceof Player player && (player.isCreative() || player.isSpectator());}

    public static boolean isLivingEntityUnderInTheCloudsEffect(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS);}

    public static boolean isLivingEntityShadowImmune(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.SHADOW_IMMUNITY) || isLivingEntityShadowBind(entity);}

    public static boolean isLivingEntityShadowBind(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.SHADOW_BIND) && entity.getEffect(AerialHellMobEffects.SHADOW_BIND).getDuration() > 1;}

    public static boolean isLivingEntityVulnerable(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.VULNERABILITY);}

    public static boolean isLivingEntityATraitor(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.TRAITOR);}

    public static boolean isLivingEntityUnderAerialHellPortalEffect(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.AERIAL_HELL_PORTAL);}

    public static boolean isLivingEntityInAerialHellPortal(LivingEntity entity) {return entity.level().getBlockState(entity.blockPosition()).is(AerialHellBlocks.AERIAL_HELL_PORTAL);}

    public static boolean isLivingEntityOnPortalCooldown(LivingEntity entity) {return entity.hasEffect(AerialHellMobEffects.AERIAL_HELL_PORTAL_COOLDOWN) || entity.isOnPortalCooldown();}

    public static boolean isImmuneToSomeShadowDamage(Entity entity)
    {
        boolean isShadowImmune = entity instanceof LivingEntity && isLivingEntityShadowImmune((LivingEntity)entity);
        boolean isShadowEntity = isShadowEntity(entity);
        return isShadowImmune || isShadowEntity;
    }

    public static boolean isShadowEntity(Entity entity) {return entity.is(AerialHellTags.Entities.SHADOW);}
    public static boolean isLightEntity(Entity entity) {return entity.is(AerialHellTags.Entities.LIGHT);}
    public static boolean isGhostEntity(Entity entity) {return entity.is(AerialHellTags.Entities.GHOST_PIRATE);}
    public static boolean isLightProjectile(Entity entity) {return entity instanceof LunaticProjectileEntity;}
    public static boolean isProjectile(Entity entity) {return entity instanceof AbstractArrow || entity instanceof ThrowableItemProjectile;}
    public static boolean isMudEntity(Entity entity) {return entity.is(AerialHellTags.Entities.MUD);}
    public static boolean isBossEntity(Entity entity) {return entity.is(AerialHellTags.Entities.BOSS);}
    public static boolean isAerialHellAnimalEntity(Entity entity) {return entity.is(AerialHellTags.Entities.PASSIVE);}
    public static boolean isAggressive(Entity entity) {return entity.is(AerialHellTags.Entities.AGGRESSIVE);}
    public static boolean isFeatheryEntity(Entity entity) {return entity.is(AerialHellTags.Entities.FEATHERY);}

    public static boolean isImmuneToBramblesDamage(Entity entity)
    {
        boolean isImmuneToAllBrambles = isAerialHellAnimalEntity(entity) || isAggressive(entity) || isFeatheryEntity(entity);
        if (isImmuneToAllBrambles) {return true;}
        else {return isImmuneToSomeShadowDamage(entity);}
    }

    public static boolean isImmuneToSkyCactusCollision(Entity entity) {return entity.is(AerialHellTags.Entities.SKY_CACTUS_COLLISION_IMMUNE);}

    public static boolean isImmuneToSolidEtherCollision(Entity entity)
    {
        if (entity instanceof LivingEntity livingEntity)
        {
            if (hasSolidEtherWalkerEnchantment(livingEntity) || isLivingEntityUnderInTheCloudsEffect(livingEntity) || isFeatheryEntity(entity)) {return false;}
            Iterable<ItemStack> stuff = getEquippedHumanoidArmorItemList(livingEntity);
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
            Iterable<ItemStack> stuff = getEquippedHumanoidArmorItemList(livingEntity);
            for (ItemStack armorStack : stuff) {if (armorStack.getItem() == AerialHellItems.MAGMATIC_GEL_BOOTS) {return false;}}
            return true;
        }
        return false;
    }

    public static boolean isImmuneToChainedGodDrag(Entity entity) {return isCreaOrSpecPlayer(entity) || entity.is(AerialHellTags.Entities.CHAINED_GOD_DRAG_IMMUNE);}

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
        Optional<Holder.Reference<Enchantment>> enchantment = entity.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).get(enchantmentKey);
        if (enchantment.isPresent())
        {
            return EnchantmentHelper.getEnchantmentLevel(enchantment.get(), entity) > 0;
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
            entity.addEffect(new MobEffectInstance(AerialHellMobEffects.AERIAL_HELL_PORTAL, 120, 0));
        }
    }

    public static void setAfterTeleportationEffect(LivingEntity entity, int duration)
    {
        if (!entity.level().isClientSide())
        {
            entity.addEffect(new MobEffectInstance(AerialHellMobEffects.AERIAL_HELL_PORTAL_COOLDOWN, duration, 0));
        }
    }

    public static boolean shouldLivingEntityHavePortalEffect(LivingEntity entity)
    {
        return !isLivingEntityOnPortalCooldown(entity) && isLivingEntityInAerialHellPortal(entity);
    }

    public static boolean isLivingEntityReadyToTeleport(LivingEntity entity)
    {
        return isLivingEntityUnderAerialHellPortalEffect(entity) && entity.getEffect(AerialHellMobEffects.AERIAL_HELL_PORTAL).getDuration() < 20;
    }

    public static void tryTeleportEntityWithAerialHellPortal(Entity entity, AerialHellPortalBlock portalBlock, BlockPos pos)
    {
        if (entity.level() instanceof ServerLevel serverWorld)
        {
            TeleportTransition dimensiontransition = portalBlock.getPortalDestination(serverWorld, entity, pos);
            if (dimensiontransition != null)
            {
                ServerLevel destinationWorld = dimensiontransition.newLevel();
                if (serverWorld.isAllowedToEnterPortal(destinationWorld) && (destinationWorld.dimension() == serverWorld.dimension() || entity.canTeleport(serverWorld, destinationWorld)))
                {
                    entity.teleport(dimensiontransition);
                }
            }
        }
    }

    public static void addBatParticle(LivingEntity entity, RandomSource rand, int number)
    {
        for (int i=0; i<number; i++)
        {
            entity.level().addParticle(AerialHellParticleTypes.SHADOW_TROLL_BAT, entity.getX() + rand.nextFloat() - 0.5, entity.getY() + 2 * rand.nextFloat(), entity.getZ() + rand.nextFloat() - 0.5, 2 * (rand.nextFloat()) - 0.5, -0.3D, 2 * (rand.nextFloat() - 0.5));
        }
    }

    public static boolean isLivingEntityMisleadingLunar(LivingEntity entity)
    {
        return ItemHelper.getItemInTagCount(EntityHelper.getEquippedHumanoidArmorItemList(entity), AerialHellTags.Items.LUNATIC_STUFF) >= 4 && !isLivingEntityATraitor(entity);
    }

    public static boolean isLivingEntityMisleadingShadow(LivingEntity entity)
    {
        return isLivingEntityShadowBind(entity) && !isLivingEntityATraitor(entity);
    }

    public static List<ItemStack> getEquippedHumanoidArmorItemList(LivingEntity livingEntity)
    {
        List<ItemStack> list = new ArrayList<>();
        for(EquipmentSlot equipmentslot : EquipmentSlotGroup.ARMOR)
        {
            if (equipmentslot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR)
            {
                ItemStack itemstack = livingEntity.getItemBySlot(equipmentslot);
                if (!itemstack.isEmpty()) {list.add(itemstack);}
            }
        }
        return list;
    }

    public static List<ItemStack> getInHandsItemList(LivingEntity livingEntity)
    {
        List<ItemStack> list = new ArrayList<>();
        for(EquipmentSlot equipmentslot : EquipmentSlotGroup.HAND)
        {
            ItemStack itemstack = livingEntity.getItemBySlot(equipmentslot);
            if (!itemstack.isEmpty()) {list.add(itemstack);}
        }
        return list;
    }

    //from in net.minecraft.server.world.ServerChunkLoadingManager sendChunkBiomePackets(..) method
    public static void refreshChunkColors(ServerPlayer player, ServerLevel world, int radius)
    {
        BoundingBox boundingbox = BlockHelper.getQuantizedBoundingBox(player.getOnPos().above(), radius);
        List<ChunkAccess> chunkAccessList = BlockHelper.getChunkAccessListForBoundingBox(world, boundingbox);

        List<LevelChunk> chunkList = new ArrayList<>();
        for (ChunkAccess chunkaccess : chunkAccessList)
        {
            if (chunkaccess instanceof LevelChunk levelchunk) {chunkList.add(levelchunk);}
            else {chunkList.add(world.getChunk(chunkaccess.getPos().x(), chunkaccess.getPos().z()));}
        }

        player.connection.send(ClientboundChunksBiomesPacket.forChunks(chunkList));
    }

    public static boolean canProjectileImpact(Projectile projectileEntity, Entity hitEntity)
    {
        if (isCreativePlayer(projectileEntity.getOwner())) {return true;}
        boolean isLightProjectile = EntityHelper.isLightProjectile(projectileEntity);

        if ((hitEntity instanceof ShadowTrollEntity || hitEntity instanceof ShadowAutomatonEntity) && !isLightProjectile) {return false;}

        if (EntityHelper.isGhostEntity(hitEntity) && !isLightProjectile)
        {
            if (EntityHelper.isImmuneToGhostBlockCollision(projectileEntity.getOwner())) {return false;}
        }

        return true;
    }

    //copy of net.minecraft.client.gui.hud.InGameOverlayRenderer method of same name
    @Nullable public static BlockState getInWallBlockState(Player player)
    {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (int i = 0; i < 8; i++)
        {
            double eyeX = player.getX() + (double)(((float)((i >> 0) % 2) - 0.5F) * player.getBbWidth() * 0.8F);
            double eyeY = player.getEyeY() + (double)(((float)((i >> 1) % 2) - 0.5F) * 0.1F * player.getScale());
            double eyeZ = player.getZ() + (double)(((float)((i >> 2) % 2) - 0.5F) * player.getBbWidth() * 0.8F);
            mutable.set(eyeX, eyeY, eyeZ);
            BlockState blockState = player.level().getBlockState(mutable);
            if (blockState.getRenderShape() != RenderShape.INVISIBLE && blockState.isViewBlocking(player.level(), mutable)) {return blockState;}
        }
        return null;
    }

    @Nullable public static FluidState getInLiquidFluidState(Player player)
    {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (int i = 0; i < 8; i++)
        {
            double eyeX = player.getX() + (double)(((float)((i >> 0) % 2) - 0.5F) * player.getBbWidth() * 0.8F);
            double eyeY = player.getEyeY() + (double)(((float)((i >> 1) % 2) - 0.5F) * 0.1F * player.getScale());
            double eyeZ = player.getZ() + (double)(((float)((i >> 2) % 2) - 0.5F) * player.getBbWidth() * 0.8F);
            mutable.set(eyeX, eyeY, eyeZ);
            FluidState fluidState = player.level().getFluidState(mutable);
            if (!fluidState.is(Fluids.EMPTY)) {return fluidState;}
        }
        return null;
    }

    public static void debugSnakeEntity(AbstractSnakeEntity snakeEntity, Player messageReceiver)
    {
        if (!snakeEntity.level().isClientSide())
        {
            AbstractSnakeEntity nextBodyPart = snakeEntity.getNextBodyPart(), previousBodyPart = snakeEntity.getPreviousBodyPart();
            String nextBodyPartString = "[", previousBodyPartString = "[";
            if (nextBodyPart != null)
            {
                BlockPos pos = nextBodyPart.blockPosition();
                nextBodyPartString += (nextBodyPart.isDeadOrDying() ? "Dead" : "Alive, health = "+nextBodyPart.getHealth()) + ", pos = " + pos.getX()+" "+pos.getY()+" "+pos.getZ() + ", distance = "+ snakeEntity.distanceTo(nextBodyPart);
                if (nextBodyPart.hasEffect(MobEffects.GLOWING)) {nextBodyPartString += ", isGlowing = true";}
                if (nextBodyPart.isNoAi()) {nextBodyPartString += ", isAiDisabled = true";}
                if (!nextBodyPart.isAttackable()) {nextBodyPartString += ", isAttackable = false";}
                if (nextBodyPart.isRemoved()) {nextBodyPartString += ", isRemoved = true"+nextBodyPart.getRemovalReason()+")";}
                if (nextBodyPart.isInvisible()) {nextBodyPartString += ", isInvisible = true";}
                nextBodyPart.addEffect(new MobEffectInstance(MobEffects.GLOWING, 400, 0, false, false, false));
            }
            else {nextBodyPartString += "null";}
            if (previousBodyPart != null)
            {
                BlockPos pos = previousBodyPart.blockPosition();
                previousBodyPartString += (previousBodyPart.isDeadOrDying() ? "Dead" : "Alive, health = "+previousBodyPart.getHealth()) + ", pos = " + pos.getX()+" "+pos.getY()+" "+pos.getZ() + ", distance = "+snakeEntity.distanceTo(previousBodyPart);
                if (previousBodyPart.hasEffect(MobEffects.GLOWING)) {previousBodyPartString += ", isGlowing = true";}
                if (previousBodyPart.isNoAi()) {previousBodyPartString += ", isAiDisabled = true";}
                if (!previousBodyPart.isAttackable()) {previousBodyPartString += ", isAttackable = false";}
                if (previousBodyPart.isRemoved()) {previousBodyPartString += ", isRemoved = true ("+previousBodyPart.getRemovalReason()+")";}
                if (previousBodyPart.isInvisible()) {previousBodyPartString += ", isInvisible = true";}
                previousBodyPart.addEffect(new MobEffectInstance(MobEffects.GLOWING, 400, 0, false, false, false));
            }
            else {previousBodyPartString += "null";}
            nextBodyPartString += "]"; previousBodyPartString += "]";
            messageReceiver.sendSystemMessage(Component.literal("Entity "+snakeEntity.getType()+" : isHead = "+snakeEntity.isHead()));
            messageReceiver.sendSystemMessage(Component.literal("nextBodyPart = "+nextBodyPartString));
            messageReceiver.sendSystemMessage(Component.literal("previousBodyPart = "+previousBodyPartString));
        }
    }

    public static List<LivingEntity> getTargetableLivingEntitiesInInflatedBoundingBox(Entity searchSource, double inflateValue, Predicate<Entity> targetCondition)
    {
        return getTargetableLivingEntitiesInInflatedBoundingBox(searchSource, inflateValue, 0.0F, targetCondition);
    }

    public static List<LivingEntity> getTargetableLivingEntitiesInInflatedBoundingBox(Entity searchSource, double boundingBoxInflateValue, double boundingBoxYOffset, Predicate<Entity> targetCondition)
    {
        return getEntitiesInInflatedBoundingBox(searchSource, boundingBoxInflateValue,boundingBoxYOffset).stream()
                .filter(entity -> entity instanceof LivingEntity)
                .filter(entity -> targetCondition.test(entity))
                .map(entity -> (LivingEntity) entity)
                .collect(Collectors.toList());
    }

    public static List<Entity> getEntitiesInInflatedBoundingBox(Entity searchSource, double boundingBoxInflateValue)
    {
        return getEntitiesInInflatedBoundingBox(searchSource, boundingBoxInflateValue, 0.0F);
    }

    public static List<Entity> getEntitiesInInflatedBoundingBox(Entity searchSource, double boundingBoxInflateValue, double boundingBoxYOffset)
    {
        return searchSource.level().getEntities(searchSource, searchSource.getBoundingBox().move(0.0F, boundingBoxYOffset, 0.0F).inflate(boundingBoxInflateValue), EntitySelector.NO_CREATIVE_OR_SPECTATOR);
    }
}
