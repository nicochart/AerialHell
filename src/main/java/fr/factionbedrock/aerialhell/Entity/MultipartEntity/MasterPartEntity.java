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
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public interface MasterPartEntity extends BaseMobEntityInterface
{
    int MAX_TICKS_IN_INVALID_SITUATION = 5;

    /* ---------------------------------------------------- */
    /* ---------- Methods needing implementation ---------- */
    /* ---------------------------------------------------- */
    //You will also need to implement getSelf() from BaseEntityInterface

    Map<PartInfo, Supplier<PartEntity>> getAllParts();

    void tickPartRotation(PartInfo partInfo, @NotNull PartEntity partEntity);

    String getPartStringUUID(PartInfo part);
    void setPartStringUUID(PartInfo part, String uuid);

    int getTicksInInvalidSituation();
    void setTickInInvalidSituation(int newValue);

    void setPartRaw(PartInfo partInfo, PartEntity part);
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
        for (PartInfo partInfo : this.getAllParts().keySet())
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

    default void partDamage(boolean superDamaged, ServerWorld world, DamageSource source, float amount) //call in damage(world, source, amount)
    {
        if (superDamaged && this.getSelf().isDead()) {this.onHurtCausingDeath();}
        else if (superDamaged)
        {
            //attacking other parts just for attack animation (red overlay)
            for (Supplier<PartEntity> part : this.getAllParts().values())
            {
                falseAttackForRedAnimation(part.get(), world, source);
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
        for (Map.Entry<PartInfo, Supplier<PartEntity>> entry : this.getAllParts().entrySet())
        {
            PartInfo partInfo = entry.getKey(); PartEntity partEntity = entry.getValue().get();
            if (partEntity != null) {this.tickPartRotation(partInfo, entry.getValue().get());}
        }
    }

    default void setPartsPosition(double x, double y, double z) //call in setPosition(x, y, z)
    {
        //do not try to set pos of another entity on client side. Let server side do.
        //null getAllParts happens on entity creation (when constructor is called)
        if (this.getLevel().isClient() || this.getAllParts() == null) {return;}
        for (Map.Entry<PartInfo, Supplier<PartEntity>> entry : this.getAllParts().entrySet())
        {
            PartInfo partInfo = entry.getKey(); PartEntity partEntity = entry.getValue().get();
            Vec3d offset = partInfo.getRelativePositionOffset();
            if (partEntity != null)
            {
                partEntity.setPosition(x + offset.x, y + offset.y, z + offset.z);
            }
        }
    }

    default void setPartsPitch(float pitch) //call in setPitch(xRot)
    {
        //do not try to set rotation of another entity on client side. Let server side do.
        //null getAllParts happens on entity creation (when constructor is called)
        if (this.getLevel().isClient() || this.getAllParts() == null) {return;}
        for (Map.Entry<PartInfo, Supplier<PartEntity>> entry : this.getAllParts().entrySet())
        {
            PartInfo partInfo = entry.getKey(); PartEntity partEntity = entry.getValue().get();
            if (partEntity != null) {partEntity.setPitch(pitch);}
        }
    }

    default void setPartsYaw(float yaw) //call in setYaw(yRot)
    {
        //do not try to set rotation of another entity on client side. Let server side do.
        //null getAllParts happens on entity creation (when constructor is called)
        if (this.getLevel().isClient() || this.getAllParts() == null) {return;}
        for (Map.Entry<PartInfo, Supplier<PartEntity>> entry : this.getAllParts().entrySet())
        {
            PartInfo partInfo = entry.getKey(); PartEntity partEntity = entry.getValue().get();
            if (partEntity != null) {partEntity.setYaw(yaw);}
        }
    }

    default void partWriteCustomData(WriteView view) //call in writeCustomData(viewOutput)
    {
        for (Map.Entry<PartInfo, Supplier<PartEntity>> entry : this.getAllParts().entrySet())
        {
            PartInfo partInfo = entry.getKey(); PartEntity partEntity = entry.getValue().get();
            if (partEntity != null)
            {
                view.putString(partInfo.getName() + "_uuid", partEntity.getSelf().getUuidAsString());
            }
        }
    }

    default void partReadCustomData(ReadView view) //call in readCustomData(viewInput)
    {
        for (PartInfo partInfo : this.getAllParts().keySet())
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

    default boolean recognizesChildPart(Entity potentialChild) //call in isPartOf(entity)
    {
        if (!(potentialChild instanceof PartEntity)) {return false;}
        for (Supplier<PartEntity> partEntity : this.getAllParts().values())
        {
            if (partEntity.get() != null && partEntity.get() == potentialChild) {return true;}
        }
        return false;
    }
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */

    /* ----------------------------------------------------------- */
    /* -------- Other utility methods (for the interface) -------- */
    /* ----------------------------------------------------------- */
    default PartEntity getPartRaw(PartInfo partInfo) {return this.getAllParts().get(partInfo).get();}

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
        for (Supplier<PartEntity> partSupplier : this.getAllParts().values())
        {
            PartEntity part = partSupplier.get();
            if (part != null)
            {
                partSupplier.get().killPart();
            }
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

    default void resetSelf()
    {
        if (this.getLevel() instanceof ServerWorld world)
        {
            Entity self = this.getSelf();
            Entity entity = self.getType().spawn(world, this.getSelf().getBlockPos(), SpawnReason.NATURAL);
            entity.refreshPositionAndAngles(self.getX(), self.getY(), self.getZ(), self.getYaw(), self.getPitch());
        }

        for (Supplier<PartEntity> partSupplier : this.getAllParts().values())
        {
            PartEntity part = partSupplier.get();
            if (part != null) {part.getSelf().setRemoved(Entity.RemovalReason.DISCARDED);}
        }
        this.getSelf().setRemoved(Entity.RemovalReason.DISCARDED);
    }

    default void summonChildParts()
    {
        for (PartInfo partInfo : this.getAllParts().keySet())
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
        MobEntity master = this.getSelf();
        Entity entity = part.getType().create(this.getLevel(), SpawnReason.NATURAL);
        if (entity instanceof PartEntity summonedPart)
        {
            if (master.isPersistent() && summonedPart instanceof MobEntity mobEntity) {mobEntity.setPersistent();}
            summonedPart.setCustomName(master.getCustomName());
            summonedPart.setInvulnerable(master.isInvulnerable());
            summonedPart.setMaster(this);
            summonedPart.getSelf().refreshPositionAndAngles(this.getX() + part.getRelativePositionOffset().x, this.getY() + part.getRelativePositionOffset().y, this.getZ() + part.getRelativePositionOffset().z, this.getSelf().getYaw(), this.getSelf().getPitch());
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
}
