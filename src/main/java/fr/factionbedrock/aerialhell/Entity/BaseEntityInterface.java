package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public interface BaseEntityInterface
{
    Entity getSelf();
    default SynchedEntityData getEntityData() {return getSelf().getEntityData();}

    default void setNeedsSync() {this.getSelf().needsSync = true;}
    default Level getLevel() {return this.getSelf().level();}

    default int getId() {return this.getSelf().getId();}
    default void setCustomName(@Nullable Component name) {this.getSelf().setCustomName(name);}
    default boolean isInvulnerable() {return this.getSelf().isInvulnerable();}
    default void setInvulnerable(boolean isInvulnerable) {this.getSelf().setInvulnerable(isInvulnerable);}
    default void snapTo(double x, double y, double z, float yRot, float xRot) {this.getSelf().snapTo(x, y, z, yRot, xRot);}
    default double getX() {return this.getSelf().getX();}
    default double getY() {return this.getSelf().getY();}
    default double getZ() {return this.getSelf().getZ();}
    default boolean is(Entity entity) {return this.getSelf().is(entity);}

    default void setPos(double x, double y, double z) {this.getSelf().setPos(x, y, z);}
    default void setXRot(float xRot) {this.setXRot(xRot);}
    default void setYRot(float xRot) {this.setYRot(xRot);}
}
