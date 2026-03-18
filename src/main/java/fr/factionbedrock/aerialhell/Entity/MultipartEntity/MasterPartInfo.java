package fr.factionbedrock.aerialhell.Entity.MultipartEntity;

import net.minecraft.entity.data.TrackedData;
import org.jspecify.annotations.Nullable;

public class MasterPartInfo
{
    private final TrackedData<Integer> entityIdDataAccessor; //entity id (in level)
    @Nullable private MasterPartEntity masterEntity;
    private int ticksInInvalidSituation;

    public MasterPartInfo(TrackedData<Integer> entityIdDataAccessor)
    {
        this.entityIdDataAccessor = entityIdDataAccessor;
        this.ticksInInvalidSituation = 0;
    }
    public TrackedData<Integer> getIdData() {return entityIdDataAccessor;}

    public @Nullable MasterPartEntity getMaster() {return masterEntity;}
    public void setMaster(@Nullable MasterPartEntity masterEntity) {this.masterEntity = masterEntity;}
    public int getTicksInInvalidSituation() {return ticksInInvalidSituation;}
    public void incrementTicksInInvalidSituation() {this.ticksInInvalidSituation += 1;}
    public void resetTicksInInvalidSituation() {this.ticksInInvalidSituation = 0;}
}
