package fr.factionbedrock.aerialhell.Entity.MultipartEntity;

import fr.factionbedrock.aerialhell.Entity.BaseMobEntityInterface;
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
        for (PartInfo partInfo : this.getPartInfoMap().values())
        {
            PartEntity synchedPartEntity = this.syncPart(partInfo); //server-client part sync
            if (synchedPartEntity == null)
            {
                partInfo.incrementTicksInInvalidSituation();
                if (partInfo.getTicksInInvalidSituation() > MAX_TICKS_IN_INVALID_SITUATION)  //should not happen if head is not removed or if the uuid changed (if the entity is loaded from a structure nbt for example)
                {
                    boolean shouldBreak = this.reactToInvalidSituationWithPart(partInfo);
                    if (shouldBreak) {break;}
                }
                this.tryToFindBackPart(partInfo);
            }
            else {partInfo.resetTicksInInvalidSituation();}
        }
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
    default InvalidSituationBehavior getInvalidSituationBehavior() {return InvalidSituationBehavior.RESET;}

    default Vec3 adjustPartOffset(PartInfo partInfo, Vec3 masterPos, Vec3 unadjustedPosOffset)
    {
        return unadjustedPosOffset;
    }

    default void tickHeadPartRotation(PartInfo partInfo)
    {
        if (partInfo.getPart() == null) {return;}
        Mob part = partInfo.getPart().getSelf();
        Mob self = this.getSelf();

        part.yBodyRot = self.yHeadRot; //the whole "body" is head
        part.yHeadRot = self.yHeadRot;
        part.setXRot(self.getXRot());
        part.setYRot(self.getYRot());
    }

    default void tickNonHeadPartRotation(PartInfo partInfo)
    {
        if (partInfo.getPart() == null) {return;}
        Mob part = partInfo.getPart().getSelf();
        Mob self = this.getSelf();

        part.yBodyRot = self.yBodyRot;
        part.yHeadRot = self.yHeadRot;
        part.setXRot(self.getXRot());
        part.setYRot(self.yBodyRot);
    }
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
            this.getPartRaw(partInfo).setPartName(partInfo.getName());
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

    private static void falseAttackForRedAnimation(@Nullable PartEntity part, ServerLevel level, DamageSource source)
    {
        if (part != null)
        {
            part.partDoHurtServer(level, source, 0.5F, true);
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
        if (this.getLevel() instanceof ServerLevel serverLevel)
        {
            Entity self = this.getSelf();
            Entity entity = self.getType().spawn(serverLevel, this.getSelf().blockPosition(), EntitySpawnReason.NATURAL);
            if (entity == null) {return;}
            entity.snapTo(self.getX(), self.getY(), self.getZ(), self.getYRot(), self.getXRot());

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
            summonedPart.setPartName(part.getName());
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

    default void setPartPos(PartInfo partInfo)
    {
        if (partInfo.getPart() != null)
        {
            Vec3 partPos = this.calculatePartPos(partInfo);
            partInfo.getPart().setPos(partPos.x, partPos.y, partPos.z);
        }
    }

    default void setPartRotation(PartInfo partInfo)
    {
        if (partInfo.isHead()) {this.tickHeadPartRotation(partInfo);}
        else {this.tickNonHeadPartRotation(partInfo);}
    }

    default Vec3 calculatePartPos(PartInfo partInfo)
    {
        Vec3 masterPos = this.getSelf().position();
        Vec3 offset = partInfo.getRelativePositionOffset();
        Vec3 adjustedOffset = this.adjustPartOffset(partInfo, new Vec3(masterPos.x, masterPos.y, masterPos.z), offset);
        Vec3 partRelativePos = this.rotatePartPos(adjustedOffset);
        return new Vec3(masterPos.x + partRelativePos.x, masterPos.y + partRelativePos.y, masterPos.z + partRelativePos.z);
    }

    default Vec3 rotatePartPos(Vec3 vec)
    {
        float yRot = (float) Math.toRadians(this.getSelf().yBodyRot);
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

    enum InvalidSituationBehavior {RESET, PART_RESPAWN, NONE}
}
