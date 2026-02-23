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

    default void onTick() //call in tick()
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

    default void onHurtServer(boolean superDamaged, ServerLevel level, DamageSource source, float amount) //call in hurtServer(level, source, amount)
    {
        if (superDamaged && this.getSelf().isDeadOrDying()) {this.onHurtCausingDeath();}
        else if (superDamaged)
        {
            //attacking other parts just for attack animation (red overlay)
            for (Supplier<PartEntity> part : this.getAllParts().values())
            {
                falseAttackForRedAnimation(part.get(), level, source);
            }
        }
    }

    @Nullable default SpawnGroupData onFinalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason reason, @Nullable SpawnGroupData spawnData) //call in finalizeSpawn(level, difficulty, reason, spawnData)
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
        return spawnData;
    }

    default void onAiStep() //call in aiStep()
    {
        for (Map.Entry<PartInfo, Supplier<PartEntity>> entry : this.getAllParts().entrySet())
        {
            PartInfo partInfo = entry.getKey(); PartEntity partEntity = entry.getValue().get();
            if (partEntity != null) {this.tickPartRotation(partInfo, entry.getValue().get());}
        }
    }

    default void onSetPos(double x, double y, double z) //call in setPos(x, y, z)
    {
        //do not try to set pos of another entity on client side. Let server side do.
        //null getAllParts happens on entity creation (when constructor is called)
        if (this.getLevel().isClientSide() || this.getAllParts() == null) {return;}
        for (Map.Entry<PartInfo, Supplier<PartEntity>> entry : this.getAllParts().entrySet())
        {
            PartInfo partInfo = entry.getKey(); PartEntity partEntity = entry.getValue().get();
            Vec3 offset = partInfo.getRelativePositionOffset();
            if (partEntity != null)
            {
                partEntity.setPos(x + offset.x, y + offset.y, z + offset.z);
            }
        }
    }

    default void onSetXRot(float xRot) //call in setXRot(xRot)
    {
        //do not try to set rotation of another entity on client side. Let server side do.
        //null getAllParts happens on entity creation (when constructor is called)
        if (this.getLevel().isClientSide() || this.getAllParts() == null) {return;}
        for (Map.Entry<PartInfo, Supplier<PartEntity>> entry : this.getAllParts().entrySet())
        {
            PartInfo partInfo = entry.getKey(); PartEntity partEntity = entry.getValue().get();
            if (partEntity != null) {partEntity.setXRot(xRot);}
        }
    }

    default void onSetYRot(float yRot) //call in setYRot(yRot)
    {
        //do not try to set rotation of another entity on client side. Let server side do.
        //null getAllParts happens on entity creation (when constructor is called)
        if (this.getLevel().isClientSide() || this.getAllParts() == null) {return;}
        for (Map.Entry<PartInfo, Supplier<PartEntity>> entry : this.getAllParts().entrySet())
        {
            PartInfo partInfo = entry.getKey(); PartEntity partEntity = entry.getValue().get();
            if (partEntity != null) {partEntity.setYRot(yRot);}
        }
    }

    default void onAddAdditionalSaveData(ValueOutput valueOutput) //call in addAdditionalSaveData(valueOutput)
    {
        for (Map.Entry<PartInfo, Supplier<PartEntity>> entry : this.getAllParts().entrySet())
        {
            PartInfo partInfo = entry.getKey(); PartEntity partEntity = entry.getValue().get();
            if (partEntity != null)
            {
                valueOutput.putString(partInfo.getName() + "_uuid", partEntity.getSelf().getStringUUID());
            }
        }
    }

    default void onReadAdditionalSaveData(ValueInput valueInput) //call in readAdditionalSaveData(valueInput)
    {
        for (PartInfo partInfo : this.getAllParts().keySet())
        {
            if (valueInput.getString(partInfo.getName() +"_uuid").isPresent())
            {
                this.setPartStringUUID(partInfo, valueInput.getString(partInfo.getName() +"_uuid").get());
            }
        }
    }

    default boolean canBePushedBy(Entity other) //call in push(entity)
    {
        return !other.is(this.getSelf());
    }

    default boolean recognizesChildPart(Entity potentialChild) //call in is(entity)
    {
        if (!(potentialChild instanceof PartEntity)) {return false;}
        for (Supplier<PartEntity> partEntity : this.getAllParts().values())
        {
            if (partEntity.get() != null && partEntity.get().is(potentialChild)) {return true;}
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

    private static void falseAttackForRedAnimation(@Nullable PartEntity part, ServerLevel level, DamageSource source)
    {
        if (part != null)
        {
            part.doHurtServer(level, source, 0.5F, true);
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

        for (Supplier<PartEntity> partSupplier : this.getAllParts().values())
        {
            PartEntity part = partSupplier.get();
            if (part != null) {part.getSelf().setRemoved(Entity.RemovalReason.DISCARDED);}
        }
        this.getSelf().setRemoved(Entity.RemovalReason.DISCARDED);
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
    /* ----------------------------------------------------------- */
    /* ----------------------------------------------------------- */
    /* ----------------------------------------------------------- */
}
