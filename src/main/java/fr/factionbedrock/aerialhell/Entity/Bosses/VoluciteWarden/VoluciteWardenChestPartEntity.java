package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

//the lower chest model also renders the upper chest plate and the core ribs (upper chest is not rendered, core ribs have no model)
public class VoluciteWardenChestPartEntity extends VoluciteWardenCollidablePartEntity
{
    private static final EntityDataAccessor<Boolean> SHOULD_RENDER = SynchedEntityData.defineId(VoluciteWardenChestPartEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SHOULD_RENDER_UPPER_STRUCTURE = SynchedEntityData.defineId(VoluciteWardenChestPartEntity.class, EntityDataSerializers.BOOLEAN); //upper chest plate & core ribs
    public VoluciteWardenChestPartEntity(EntityType<? extends VoluciteWardenPartEntity> type, Level level)
    {
        super(type, level);
    }

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(SHOULD_RENDER, true);
        builder.define(SHOULD_RENDER_UPPER_STRUCTURE, true);
    }

    public void setRenderOff() {this.entityData.set(SHOULD_RENDER, false);}
    public boolean shouldRender() {return this.entityData.get(SHOULD_RENDER);}

    public void setUpperStructureRenderOff() {this.entityData.set(SHOULD_RENDER_UPPER_STRUCTURE, false);} //final phase : upper chest & core ribs are dead
    public boolean shouldRenderUpperStructure() {return this.entityData.get(SHOULD_RENDER_UPPER_STRUCTURE);}

    @Override protected void addAdditionalSaveData(ValueOutput valueOutput)
    {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.putBoolean("should_render", this.shouldRender());
        valueOutput.putBoolean("should_render_upper_structure", this.shouldRenderUpperStructure());
    }

    @Override protected void readAdditionalSaveData(ValueInput valueInput)
    {
        super.readAdditionalSaveData(valueInput);
        this.entityData.set(SHOULD_RENDER, valueInput.getBooleanOr("should_render", true));
        this.entityData.set(SHOULD_RENDER_UPPER_STRUCTURE, valueInput.getBooleanOr("should_render_upper_structure", true));
    }
}
