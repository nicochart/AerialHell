package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import org.joml.Vector3fc;

//visual only lightning, going from its position to an end position (damage is applied by the attack spawning it)
//see net.minecraft.world.entity.LightningBolt
public class VoluciteLightningEntity extends Entity
{
    private static final EntityDataAccessor<Vector3fc> END_OFFSET = SynchedEntityData.defineId(VoluciteLightningEntity.class, EntityDataSerializers.VECTOR3);
    public static final int LIFETIME = 8;
    private static final int FLICKER_INTERVAL = 2; //ticks between two bolt shapes (client side)
    public long seed;

    public VoluciteLightningEntity(EntityType<? extends VoluciteLightningEntity> type, Level level)
    {
        super(type, level);
        this.seed = this.random.nextLong();
    }

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder) {builder.define(END_OFFSET, new Vector3f());}

    //call before adding the entity to the level, after setting its position
    public void setEndPos(Vec3 endPos) {this.entityData.set(END_OFFSET, endPos.subtract(this.position()).toVector3f());}
    public Vec3 getEndOffset() {return new Vec3(this.entityData.get(END_OFFSET));}

    @Override public void tick()
    {
        super.tick();
        if (this.level().isClientSide())
        {
            if (this.tickCount % FLICKER_INTERVAL == 0) {this.seed = this.random.nextLong();}
        }
        else if (this.tickCount >= LIFETIME) {this.discard();}
    }

    @Override public boolean shouldRenderAtSqrDistance(double distance)
    {
        double maxDistance = 128.0D * getViewScale();
        return distance < maxDistance * maxDistance;
    }

    @Override protected void readAdditionalSaveData(ValueInput input) {}
    @Override protected void addAdditionalSaveData(ValueOutput output) {}

    @Override public final boolean hurtServer(ServerLevel level, DamageSource source, float damage) {return false;}
}
