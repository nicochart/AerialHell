package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

//beacon healing the volucite warden (like end crystals with the ender dragon), looking like a set of volucite blocks
public class VoluciteWardenHealBeaconEntity extends Entity
{
    public VoluciteWardenHealBeaconEntity(EntityType<? extends VoluciteWardenHealBeaconEntity> type, Level level) {super(type, level);}

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder) {}

    @Override protected MovementEmission getMovementEmission() {return MovementEmission.NONE;}

    @Override public boolean isPickable() {return true;}
    @Override public boolean canBeCollidedWith(@Nullable Entity entity) {return true;} //block-like collision

    @Override public final boolean hurtServer(ServerLevel level, DamageSource source, float damage) {return false;} //TODO

    @Override protected void readAdditionalSaveData(ValueInput input) {}
    @Override protected void addAdditionalSaveData(ValueOutput output) {}
}
