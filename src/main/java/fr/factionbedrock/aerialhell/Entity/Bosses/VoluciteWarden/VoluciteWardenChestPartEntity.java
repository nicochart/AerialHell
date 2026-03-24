package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class VoluciteWardenChestPartEntity extends VoluciteWardenCollidablePartEntity
{
    private static final EntityDataAccessor<Boolean> SHOULD_RENDER = SynchedEntityData.defineId(VoluciteWardenChestPartEntity.class, EntityDataSerializers.BOOLEAN);
    public VoluciteWardenChestPartEntity(EntityType<? extends VoluciteWardenPartEntity> type, Level level)
    {
        super(type, level);
    }

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(SHOULD_RENDER, true);
    }

    public void setRenderOff() {this.entityData.set(SHOULD_RENDER, false);}
    public boolean shouldRender() {return this.entityData.get(SHOULD_RENDER);}

    @Override protected void addAdditionalSaveData(ValueOutput valueOutput)
    {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.putBoolean("should_render", this.shouldRender());
    }

    @Override protected void readAdditionalSaveData(ValueInput valueInput)
    {
        super.readAdditionalSaveData(valueInput);
        this.entityData.set(SHOULD_RENDER, valueInput.getBooleanOr("should_render", true));
    }
}
