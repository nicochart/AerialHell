package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;

public interface BaseEntityInterface
{
    Entity getSelf();
    default DataTracker getEntityData() {return getSelf().getDataTracker();}

    default void setNeedsSync() {this.getSelf().velocityDirty = true;} //TODO rename
    default World getLevel() {return this.getSelf().getEntityWorld();} //TODO rename

    default int getId() {return this.getSelf().getId();}
    default void setCustomName(@Nullable Text name) {this.getSelf().setCustomName(name);}
    default boolean isInvulnerable() {return this.getSelf().isInvulnerable();}
    default void setInvulnerable(boolean isInvulnerable) {this.getSelf().setInvulnerable(isInvulnerable);}
    default void refreshPositionAndAngles(double x, double y, double z, float yRot, float xRot) {this.getSelf().refreshPositionAndAngles(x, y, z, yRot, xRot);} //TODO rename
    default double getX() {return this.getSelf().getX();}
    default double getY() {return this.getSelf().getY();}
    default double getZ() {return this.getSelf().getZ();}
    default boolean is(Entity entity) {return this.getSelf().isPartOf(entity);} //TODO rename

    default void setPosition(double x, double y, double z) {this.getSelf().setPosition(x, y, z);}
    default void setPitch(float xRot) {this.getSelf().setPitch(xRot);}
    default void setYaw(float yRot) {this.getSelf().setYaw(yRot);}
}
