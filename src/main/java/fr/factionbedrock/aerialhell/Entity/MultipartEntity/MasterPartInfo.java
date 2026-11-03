package fr.factionbedrock.aerialhell.Entity.MultipartEntity;

import net.minecraft.network.syncher.EntityDataAccessor;
import org.jspecify.annotations.Nullable;

public class MasterPartInfo
{
    private final EntityDataAccessor<Integer> entityIdDataAccessor; //entity id (in level)
    @Nullable private final EntityDataAccessor<Integer> statusDataAccessor; //part status. null if the part can't be detached (always attached). synced
    @Nullable private MasterPartEntity masterEntity;
    private int ticksInInvalidSituation;

    public MasterPartInfo(EntityDataAccessor<Integer> entityIdDataAccessor) {this(entityIdDataAccessor, null);}

    public MasterPartInfo(EntityDataAccessor<Integer> entityIdDataAccessor, @Nullable EntityDataAccessor<Integer> statusDataAccessor)
    {
        this.entityIdDataAccessor = entityIdDataAccessor;
        this.statusDataAccessor = statusDataAccessor;
        this.ticksInInvalidSituation = 0;
    }
    public EntityDataAccessor<Integer> getIdData() {return entityIdDataAccessor;}
    public @Nullable EntityDataAccessor<Integer> getStatusData() {return statusDataAccessor;}

    public @Nullable MasterPartEntity getMaster() {return masterEntity;}
    public void setMaster(@Nullable MasterPartEntity masterEntity) {this.masterEntity = masterEntity;}
    public int getTicksInInvalidSituation() {return ticksInInvalidSituation;}
    public void incrementTicksInInvalidSituation() {this.ticksInInvalidSituation += 1;}
    public void resetTicksInInvalidSituation() {this.ticksInInvalidSituation = 0;}
}
