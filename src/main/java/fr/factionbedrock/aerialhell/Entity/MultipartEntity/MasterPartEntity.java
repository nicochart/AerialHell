package fr.factionbedrock.aerialhell.Entity.MultipartEntity;

import fr.factionbedrock.aerialhell.Entity.BaseMobEntityInterface;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public interface MasterPartEntity extends BaseMobEntityInterface
{
    int MAX_TICKS_IN_INVALID_SITUATION = 5;

    /* ---------------------------------------------------- */
    /* ---------- Methods needing implementation ---------- */
    /* ---------------------------------------------------- */
    //You will also need to implement getSelf() from BaseEntityInterface

    Map<String, PartInfo> getPartInfoMap();
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */

    /* ----------------------------------------------- */
    /* -------- Delegate methods needing call -------- */
    /* ----------------------------------------------- */
    default void partEntityTick() //call in tick()
    {
        if (!this.getSelf().isAlive()) {return;}
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            PartEntity synchedPartEntity = this.syncPart(partInfo); //server-client part sync
            if (synchedPartEntity == null || synchedPartEntity.getSelf().isRemoved())
            {
                partInfo.incrementTicksInInvalidSituation();
                if (partInfo.getTicksInInvalidSituation() > MAX_TICKS_IN_INVALID_SITUATION)  //should not happen if head is not removed or if the uuid changed (if the entity is loaded from a structure nbt for example)
                {
                    boolean shouldBreak = this.reactToInvalidSituationWithPart(partInfo);
                    if (shouldBreak) {break;}
                }
                this.tryToFindBackPart(partInfo);
            }
            else
            {
                partInfo.resetTicksInInvalidSituation();

                //below sometimes happens client-side (if player is far-away)
                MasterPartEntity partMaster = synchedPartEntity.getMaster();
                if (partMaster == null || partMaster.getId() != this.getId()) {synchedPartEntity.setMasterRaw(this);}
            }
        }
    }

    default void partDamage(boolean superDamaged, ServerWorld world, DamageSource source, float amount) //call in damage(world, source, amount)
    {
        if (superDamaged && this.getSelf().isDead()) {this.onHurtCausingDeath();}
        else if (superDamaged)
        {
            //attacking other parts just for attack animation (red overlay)
            for (PartInfo partInfo : this.getPartInfoMap().values())
            {
                falseAttackForRedAnimation(partInfo.getPart(), world, source);
            }
        }
    }

    @Nullable default EntityData initializePart(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) //call in initialize(world, difficulty, reason, entityData)
    {
        this.summonChildParts();
        return entityData;
    }

    default void partEntityTickMovement() //call in tickMovement()
    {
        if (this.getLevel().isClient())
        {
            this.fixPartsXRot();
        }
        else
        {
            this.setPartsPosition(this.getX(), this.getY(), this.getZ());
            this.setPartsRotation();
        }
    }

    default void setPartsPitch(float pitch) //call in setPitch(xRot)
    {
        //do not try to set rotation of another entity on client side. Let server side do.
        //null getAllParts happens on entity creation (when constructor is called)
        if (this.getLevel().isClient() || this.getPartInfoMap() == null) {return;}
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            PartEntity part = partInfo.getPart();
            if (part != null) {part.setPitch(pitch);}
        }
    }

    default void setPartsYaw(float yaw) //call in setYaw(yRot)
    {
        //do not try to set rotation of another entity on client side. Let server side do.
        //null getAllParts happens on entity creation (when constructor is called)
        if (this.getLevel().isClient() || this.getPartInfoMap() == null) {return;}
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            PartEntity part = partInfo.getPart();
            if (part != null) {part.setYaw(yaw);}
        }
    }

    default void partWriteCustomData(WriteView view) //call in addAdditionalSaveData(valueOutput)
    {
        view.putFloat("master_body_rot", this.getSelf().bodyYaw);

        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            PartEntity part = partInfo.getPart();
            if (part != null)
            {
                view.putString(partInfo.getName() + "_uuid", part.getSelf().getUuidAsString());
            }
        }
    }

    default void partReadCustomData(ReadView view) //call in readAdditionalSaveData(valueInput)
    {
        this.getSelf().bodyYaw = view.getFloat("master_body_rot", 0);

        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            if (view.getOptionalString(partInfo.getName() +"_uuid").isPresent())
            {
                this.setPartStringUUID(partInfo, view.getOptionalString(partInfo.getName() +"_uuid").get());
            }
        }
    }

    default boolean partCanBePushedAwayBy(Entity other) //call in pushAwayFrom(entity) and if return false, do nothing
    {
        return !other.isPartOf(this.getSelf());
    }

    default boolean recognizesChildPart(Entity potentialChild) //call in is(entity)
    {
        if (!(potentialChild instanceof PartEntity)) {return false;}
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            PartEntity part = partInfo.getPart();
            if (part != null && part == potentialChild) {return true;}
        }
        return false;
    }
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */

    /* --------------------------------------------------------------------- */
    /* ------- Other methods to override for specific part behaviors ------- */
    /* --------------------------------------------------------------------- */
    default MasterInvalidSituationBehavior getInvalidSituationBehavior() {return MasterInvalidSituationBehavior.RESET;}

    default Vec3d adjustPartOffset(PartInfo partInfo, PartEntity partEntity, Vec3d masterPos, Vec3d unadjustedPosOffset)
    {
        return unadjustedPosOffset;
    }

    default void tickHeadPartRotation(PartInfo partInfo)
    {
        if (partInfo.getPart() == null) {return;}
        MobEntity part = partInfo.getPart().getSelf();
        MobEntity self = this.getSelf();
        part.lastBodyYaw = part.bodyYaw;
        part.lastHeadYaw = part.headYaw;

        part.bodyYaw = self.headYaw; //the whole "body" is head
        part.headYaw = self.headYaw;
        part.setPitch(self.getPitch());
        part.setYaw(self.getYaw());
    }

    default void tickNonHeadPartRotation(PartInfo partInfo)
    {
        if (partInfo.getPart() == null) {return;}
        MobEntity part = partInfo.getPart().getSelf();
        MobEntity self = this.getSelf();
        part.lastBodyYaw = part.bodyYaw;
        part.lastHeadYaw = part.headYaw;

        part.bodyYaw = self.bodyYaw; //the whole "body" is head
        part.headYaw = self.headYaw;
        part.setPitch(self.getPitch());
        part.setYaw(self.getYaw());
        part.lastPitch = part.getPitch();
    }

    default void onPartSummoned(PartInfo partInfo) {} //additional things to do when part is summoned (write data, set values, for example)
    /* --------------------------------------------------------------------- */
    /* --------------------------------------------------------------------- */
    /* --------------------------------------------------------------------- */

    /* ----------------------------------------------------------- */
    /* -------- Other utility methods (for the interface) -------- */
    /* ----------------------------------------------------------- */
    default @Nullable String getPartStringUUID(PartInfo part) {return part.getPartUUID();}
    default void setPartStringUUID(PartInfo part, String uuid) {part.setPartUUID(uuid);}

    default PartEntity getPartRaw(PartInfo partInfo) {return partInfo.getPart();}
    default void setPartRaw(PartInfo partInfo, PartEntity part) {partInfo.setPart(part);}

    default int getPartEntityId(PartInfo part) {return this.getEntityData().get(part.getIdData());}
    default boolean hasPartEntityId(PartInfo part) {return this.getPartEntityId(part) != 0;}
    default void setPartEntityId(PartInfo part, int entityId) {this.getEntityData().set(part.getIdData(), entityId);}

    private boolean tryToFindBackPart(PartInfo partInfo)
    {
        this.setPartRaw(partInfo, this.getPartByUUID(this.getPartStringUUID(partInfo)));
        if (this.getPartRaw(partInfo) != null)
        {
            this.setPartEntityId(partInfo, this.getPartRaw(partInfo).getId());
            this.getPartRaw(partInfo).setMaster(this);
            return true; //return true if part is found
        }
        return false; //return false if part is still not found
    }

    @Nullable default PartEntity getPartByUUID(@Nullable String stringUUID)
    {
        if (stringUUID == null) {return null;}

        Entity entity = this.getLevel().getEntity(UUID.fromString(stringUUID));
        return entity instanceof PartEntity foundPart ? foundPart : null;
    }

    private void onHurtCausingDeath()
    {
        //kill other parts
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            PartEntity part = partInfo.getPart();
            if (part != null) {part.killPart();}
        }
    }

    private static void falseAttackForRedAnimation(@Nullable PartEntity part, ServerWorld world, DamageSource source)
    {
        if (part != null)
        {
            part.partDamage(world, source, 0.5F, true);
            part.getSelf().heal(0.5F);
        }
    }

    //if the part is not found (for example after being placed with a structure nbt, or if the part was deleted for whatever reason)
    //returns true if other parts are impacted by the reaction
    default boolean reactToInvalidSituationWithPart(PartInfo part)
    {
        return switch (this.getInvalidSituationBehavior())
        {
            case NONE -> false;
            case PART_RESPAWN ->
            {
                this.trySummonAndRegisterPart(part); //only reset the part. Can cause sync problem.
                yield false;
            }
            case RESET ->
            {
                this.resetSelf(); //full reset is the best to do to avoid unsync animation.
                yield true;
            }
        };
    }

    default void resetSelf()
    {
        if (this.getLevel() instanceof ServerWorld world)
        {
            Entity self = this.getSelf();
            Entity entity = self.getType().spawn(world, this.getSelf().getBlockPos(), SpawnReason.NATURAL);
            if (entity == null) {return;}
            entity.refreshPositionAndAngles(self.getX(), self.getY(), self.getZ(), self.getYaw(), self.getPitch());

            for (PartInfo partInfo : this.getPartInfoMap().values())
            {
                PartEntity part = partInfo.getPart();
                if (part != null) {part.getSelf().setRemoved(Entity.RemovalReason.DISCARDED);}
            }
            this.getSelf().setRemoved(Entity.RemovalReason.DISCARDED);
        }
    }

    default void summonChildParts()
    {
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            this.trySummonAndRegisterPart(partInfo);
        }
    }

    default void trySummonAndRegisterPart(PartInfo partInfo)
    {
        PartEntity partEntity = this.summonPart(partInfo);
        if (partEntity != null)
        {
            this.setPartRaw(partInfo, partEntity);
            this.setPartEntityId(partInfo, partEntity.getId());
            this.onPartSummoned(partInfo);
        }
    }

    default PartEntity summonPart(PartInfo part)
    {
        MobEntity master = this.getSelf();
        Entity entity = part.getType().create(this.getLevel(), SpawnReason.NATURAL);
        if (entity instanceof PartEntity summonedPart)
        {
            if (master.isPersistent() && summonedPart instanceof MobEntity mobEntity) {mobEntity.setPersistent();}
            summonedPart.setCustomName(master.getCustomName());
            summonedPart.setInvulnerable(master.isInvulnerable());
            summonedPart.setMaster(this);
            summonedPart.getSelf().refreshPositionAndAngles(this.getX() + part.getUnrotatedRelativePositionOffset().x, this.getY() + part.getUnrotatedRelativePositionOffset().y, this.getZ() + part.getUnrotatedRelativePositionOffset().z, this.getSelf().getYaw(), this.getSelf().getPitch());

            this.getLevel().spawnEntity(summonedPart.getSelf());
            return summonedPart;
        }
        else {return null;}
    }

    @Nullable default PartEntity syncPart(PartInfo partInfo)
    {
        int partId = this.getPartEntityId(partInfo);
        if (!this.hasPartEntityId(partInfo)) {return null;}
        else if (this.getLevel().isClient()) //Client side
        {
            PartEntity currentPart = this.getPartRaw(partInfo);
            if (currentPart != null && currentPart.getId() == partId) //if client cached target exists & is valid (same id as synced id)
            {
                return currentPart;
            }
            else //trying to update part
            {
                Entity entity = this.getLevel().getEntityById(partId);
                if (entity instanceof PartEntity partEntity)
                {
                    this.setPartRaw(partInfo, partEntity);
                    return partEntity;
                }
                else {return null;}
            }
        }
        else //Server side
        {
            return getPartRaw(partInfo);
        }
    }

    default void setPartsPosition(double masterX, double masterY, double masterZ) //call in setPos(x, y, z)
    {
        //do not try to set pos of another entity on client side. Let server side do.
        //null getAllParts happens on entity creation (when constructor is called)
        if (this.getLevel().isClient() || this.getPartInfoMap() == null) {return;}
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            Vec3d partPos = this.calculatePartPos(partInfo, masterX, masterY, masterZ);
            if (partPos != null && partInfo.getPart() != null)
            {
                partInfo.getPart().setPosition(partPos.x, partPos.y, partPos.z);
            }
        }
    }

    default void fixPartsXRot()
    {
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            this.fixPartXRot(partInfo);
        }
    }

    default void fixPartXRot(PartInfo partInfo)
    {
        //client-side, by default (if you don't call this method for all parts), if a multi-part entity was already present after disconnect-reconnect,
        //the interpolator loops setting xRot to 0 if you don't set xRot0 to xRot.
        //to avoid this problem, need to manually set xRot0 to the right value.
        if (partInfo.getPart() == null) {return;}
        partInfo.getPart().getSelf().setPitch(this.getSelf().getPitch());
        partInfo.getPart().getSelf().lastPitch = this.getSelf().lastPitch;
    }

    default void setPartsRotation()
    {
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            this.tickPartRotation(partInfo);
        }
    }

    default void tickPartRotation(PartInfo partInfo)
    {
        if (partInfo.isHead())
        {
            this.tickHeadPartRotation(partInfo);
        }
        else
        {
            this.tickNonHeadPartRotation(partInfo);
        }
    }

    @Nullable default Vec3d calculatePartPos(PartInfo partInfo, double masterX, double masterY, double masterZ)
    {
        PartEntity part = partInfo.getPart();
        Vec3d offset = partInfo.getUnrotatedRelativePositionOffset();
        if (part != null)
        {
            Vec3d adjustedOffset = this.adjustPartOffset(partInfo, part, new Vec3d(masterX, masterY, masterZ), offset);
            Vec3d partRelativePos = this.toRotatedPos(adjustedOffset);
            return new Vec3d(masterX + partRelativePos.x, masterY + partRelativePos.y, masterZ + partRelativePos.z);
        }
        return null;
    }

    default float calculatePitchFromOriginToTarget(Vec3d origin, Vec3d target)
    {
        double x = origin.x, y = origin.y, z = origin.z;
        double tx = target.x, ty = target.y, tz = target.z;
        double dx = x - tx;
        double dy = y - ty;
        double dz = z - tz;
        double xzDistance = Math.sqrt(dx * dx + dz * dz);
        return (float)(Math.atan2(dy, xzDistance) * (180F / Math.PI));
    }
    /* ----------------------------------------------------------- */
    /* ----------------------------------------------------------- */
    /* ----------------------------------------------------------- */

    enum MasterInvalidSituationBehavior {RESET, PART_RESPAWN, NONE}
}
