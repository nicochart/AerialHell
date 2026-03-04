package fr.factionbedrock.aerialhell.Entity.MultipartEntity;

import fr.factionbedrock.aerialhell.Entity.BaseMobEntityInterface;
import fr.factionbedrock.aerialhell.Util.FieldAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
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

    void tickPartRotation(PartInfo partInfo, @NotNull PartEntity partEntity);

    FieldAccessor<Integer> getTicksInInvalidSituationAccessor();

    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */

    /* ----------------------------------------------- */
    /* -------- Delegate methods needing call -------- */
    /* ----------------------------------------------- */
    default void initMaster() //call in constructor
    {
        this.resetTicksInInvalidSituation();
    }

    default void partEntityTick() //call in tick()
    {
        boolean onePartIsNotFount = false;
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            PartEntity synchedPartEntity = this.syncPart(partInfo); //server-client part sync
            if (synchedPartEntity == null)
            {
                onePartIsNotFount = !this.tryToFindBackPart(partInfo);
            }
        }

        if (onePartIsNotFount)
        {
            this.incrementTicksInInvalidSituation();

            if (this.getTicksInInvalidSituation() > MAX_TICKS_IN_INVALID_SITUATION) //should not happen if head is not removed or if the uuid changed (if the entity is loaded from a structure nbt for example)
            {
                this.resetSelf();
            }
        }
        else {this.resetTicksInInvalidSituation();}
    }

    default void partHurtServer(boolean superDamaged, ServerLevel level, DamageSource source, float amount) //call in hurtServer(level, source, amount)
    {
        if (superDamaged && this.getSelf().isDeadOrDying()) {this.onHurtCausingDeath();}
        else if (superDamaged)
        {
            //attacking other parts just for attack animation (red overlay)
            for (PartInfo partInfo : this.getPartInfoMap().values())
            {
                falseAttackForRedAnimation(partInfo.getPart(), level, source);
            }
        }
    }

    @Nullable default SpawnGroupData finalizePartSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason reason, @Nullable SpawnGroupData spawnData) //call in finalizeSpawn(level, difficulty, reason, spawnData)
    {
        this.summonChildParts();
        return spawnData;
    }

    default void partAiStep() //call in aiStep()
    {
        if (this.getLevel().isClientSide()) {return;}
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            PartEntity part = partInfo.getPart();
            if (part != null) {this.tickPartRotation(partInfo, part);}
        }
    }

    default void setPartsPos(double masterX, double masterY, double masterZ) //call in setPos(x, y, z)
    {
        //do not try to set pos of another entity on client side. Let server side do.
        //null getAllParts happens on entity creation (when constructor is called)
        if (this.getLevel().isClientSide() || this.getPartInfoMap() == null) {return;}
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            PartEntity part = partInfo.getPart();
            Vec3 offset = partInfo.getRelativePositionOffset();
            if (part != null)
            {
                Vec3 adjustedOffset = this.adjustPartOffset(partInfo, part, new Vec3(masterX, masterY, masterZ), offset);
                Vec3 partPos = this.rotatePartPos(adjustedOffset);
                part.setPos(masterX + partPos.x, masterY + partPos.y, masterZ + partPos.z);
            }
        }
    }

    default void setPartsXRot(float xRot) //call in setXRot(xRot)
    {
        //do not try to set rotation of another entity on client side. Let server side do.
        //null getAllParts happens on entity creation (when constructor is called)
        if (this.getLevel().isClientSide() || this.getPartInfoMap() == null) {return;}
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            PartEntity part = partInfo.getPart();
            if (part != null) {part.setXRot(xRot);}
        }
    }

    default void setPartsYRot(float yRot) //call in setYRot(yRot)
    {
        //do not try to set rotation of another entity on client side. Let server side do.
        //null getAllParts happens on entity creation (when constructor is called)
        if (this.getLevel().isClientSide() || this.getPartInfoMap() == null) {return;}
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            PartEntity part = partInfo.getPart();
            if (part != null) {part.setYRot(yRot);}
        }
    }

    default void partAddAdditionalSaveData(ValueOutput valueOutput) //call in addAdditionalSaveData(valueOutput)
    {
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            PartEntity part = partInfo.getPart();
            if (part != null)
            {
                valueOutput.putString(partInfo.getName() + "_uuid", part.getSelf().getStringUUID());
            }
        }
    }

    default void partReadAdditionalSaveData(ValueInput valueInput) //call in readAdditionalSaveData(valueInput)
    {
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            if (valueInput.getString(partInfo.getName() +"_uuid").isPresent())
            {
                this.setPartStringUUID(partInfo, valueInput.getString(partInfo.getName() +"_uuid").get());
            }
        }
    }

    default boolean partCanBePushedBy(Entity other) //call in push(entity) and if return false, do nothing
    {
        return !other.is(this.getSelf());
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
    default Vec3 adjustPartOffset(PartInfo partInfo, PartEntity partEntity, Vec3 masterPos, Vec3 unadjustedPosOffset)
    {
        return unadjustedPosOffset;
    }
    /* --------------------------------------------------------------------- */
    /* --------------------------------------------------------------------- */
    /* --------------------------------------------------------------------- */

    /* ----------------------------------------------------------- */
    /* -------- Other utility methods (for the interface) -------- */
    /* ----------------------------------------------------------- */
    default int getTicksInInvalidSituation() {return this.getTicksInInvalidSituationAccessor().get();}
    default void setTickInInvalidSituation(int newValue) {this.getTicksInInvalidSituationAccessor().set(newValue);}

    default @Nullable String getPartStringUUID(PartInfo part) {return part.getPartUUID();}
    default void setPartStringUUID(PartInfo part, String uuid) {part.setPartUUID(uuid);}

    default PartEntity getPartRaw(PartInfo partInfo) {return partInfo.getPart();}
    default void setPartRaw(PartInfo partInfo, PartEntity part) {partInfo.setPart(part);}

    default void incrementTicksInInvalidSituation() {this.setTickInInvalidSituation(this.getTicksInInvalidSituation() + 1);}
    default void resetTicksInInvalidSituation() {this.setTickInInvalidSituation(0);}

    default int getPartEntityId(PartInfo part) {return this.getEntityData().get(part.getIdData());}
    default boolean hasPartEntityId(PartInfo part) {return this.getPartEntityId(part) != 0;}
    default void setPartEntityId(PartInfo part, int entityId) {this.getEntityData().set(part.getIdData(), entityId);}

    private boolean tryToFindBackPart(PartInfo partInfo)
    {
        this.setPartRaw(partInfo, this.getHeadByUUID(this.getPartStringUUID(partInfo)));
        if (this.getPartRaw(partInfo) != null)
        {
            this.setPartEntityId(partInfo, this.getPartRaw(partInfo).getId());
            this.getPartRaw(partInfo).setMaster(this);
            return true; //return true if part is found
        }
        return false; //return false if part is still not found
    }

    @Nullable default PartEntity getHeadByUUID(@Nullable String stringUUID)
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

    private static void falseAttackForRedAnimation(@Nullable PartEntity part, ServerLevel level, DamageSource source)
    {
        if (part != null)
        {
            part.partDoHurtServer(level, source, 0.5F, true);
            part.getSelf().heal(0.5F);
        }
    }

    default void resetSelf()
    {
        if (this.getLevel() instanceof ServerLevel serverLevel)
        {
            Entity self = this.getSelf();
            Entity entity = self.getType().spawn(serverLevel, this.getSelf().blockPosition(), EntitySpawnReason.NATURAL);
            entity.snapTo(self.getX(), self.getY(), self.getZ(), self.getYRot(), self.getXRot());
        }

        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            PartEntity part = partInfo.getPart();
            if (part != null) {part.getSelf().setRemoved(Entity.RemovalReason.DISCARDED);}
        }
        this.getSelf().setRemoved(Entity.RemovalReason.DISCARDED);
    }

    default void summonChildParts()
    {
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            PartEntity partEntity = this.summonPart(partInfo);
            if (partEntity != null)
            {
                this.setPartRaw(partInfo, partEntity);
                this.setPartEntityId(partInfo, partEntity.getId());
            }
        }
    }

    default PartEntity summonPart(PartInfo part)
    {
        Mob master = this.getSelf();
        Entity entity = part.getType().create(this.getLevel(), EntitySpawnReason.NATURAL);
        if (entity instanceof PartEntity summonedPart)
        {
            if (master.isPersistenceRequired() && summonedPart instanceof Mob mobEntity) {mobEntity.setPersistenceRequired();}
            summonedPart.setCustomName(master.getCustomName());
            summonedPart.setInvulnerable(master.isInvulnerable());
            summonedPart.setMaster(this);
            summonedPart.getSelf().snapTo(this.getX() + part.getRelativePositionOffset().x, this.getY() + part.getRelativePositionOffset().y, this.getZ() + part.getRelativePositionOffset().z, this.getSelf().getYRot(), this.getSelf().getXRot());
            this.getLevel().addFreshEntity(summonedPart.getSelf());
            return summonedPart;
        }
        else {return null;}
    }

    @Nullable default PartEntity syncPart(PartInfo partInfo)
    {
        int partId = this.getPartEntityId(partInfo);
        if (!this.hasPartEntityId(partInfo)) {return null;}
        else if (this.getLevel().isClientSide()) //Client side
        {
            PartEntity currentPart = this.getPartRaw(partInfo);
            if (currentPart != null && currentPart.getId() == partId) //if client cached target exists & is valid (same id as synced id)
            {
                return currentPart;
            }
            else //trying to update part
            {
                Entity entity = this.getLevel().getEntity(partId);
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

    default Vec3 rotatePartPos(Vec3 vec)
    {
        float yRot = (float) Math.toRadians(this.getSelf().getYRot());
        return vec.yRot(-yRot);
    }

    default float calculateXRotFromOriginToTarget(Vec3 origin, Vec3 target)
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
}
