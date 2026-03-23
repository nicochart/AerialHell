package fr.factionbedrock.aerialhell.Entity.MultipartEntity;

import net.minecraft.network.syncher.EntityDataAccessor;
import org.jspecify.annotations.Nullable;

public class PartContext
{
    private final EntityDataAccessor<Integer> masterIdDataAccessor; //master entity id (in level)
    private final EntityDataAccessor<String> partNameDataAccessor; //part name (will be the same as in master's part info)
    @Nullable private MasterPartEntity masterEntity;
    private int ticksInInvalidSituation;

    public PartContext(EntityDataAccessor<Integer> entityIdDataAccessor, EntityDataAccessor<String> partNameDataAccessor)
    {
        this.masterIdDataAccessor = entityIdDataAccessor;
        this.partNameDataAccessor = partNameDataAccessor;
        this.ticksInInvalidSituation = 0;
    }
    public EntityDataAccessor<Integer> getMasterIdData() {return masterIdDataAccessor;}
    public EntityDataAccessor<String> getPartNameData() {return partNameDataAccessor;}

    public @Nullable MasterPartEntity getMaster() {return masterEntity;}
    public void setMaster(@Nullable MasterPartEntity masterEntity) {this.masterEntity = masterEntity;}
    public int getTicksInInvalidSituation() {return ticksInInvalidSituation;}
    public void incrementTicksInInvalidSituation() {this.ticksInInvalidSituation += 1;}
    public void resetTicksInInvalidSituation() {this.ticksInInvalidSituation = 0;}
}
