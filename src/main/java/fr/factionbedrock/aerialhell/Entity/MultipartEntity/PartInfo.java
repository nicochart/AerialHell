package fr.factionbedrock.aerialhell.Entity.MultipartEntity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.util.math.Vec3d;
import org.jspecify.annotations.Nullable;

import java.util.Map;

public class PartInfo
{
    private final EntityType<?> type;
    private final String name;
    private final TrackedData<Integer> entityIdDataAccessor; //entity id (in level)
    @Nullable private PartEntity partEntity;
    @Nullable private String partEntityUUID; //entity uuid
    private final Vec3d relativePositionOffset;
    private int ticksInInvalidSituation;

    public PartInfo(EntityType<?> type, String name, TrackedData<Integer> entityIdDataAccessor, Map<String, PartInfo> partsMap) {this(type, name, entityIdDataAccessor, Vec3d.ZERO, partsMap);}

    public PartInfo(EntityType<?> type, String name, TrackedData<Integer> entityIdDataAccessor, Vec3d relativePositionOffset, Map<String, PartInfo> partsMap)
    {
        this.type = type;
        this.name = name;
        this.entityIdDataAccessor = entityIdDataAccessor;
        this.relativePositionOffset = relativePositionOffset;
        partsMap.put(name, this);
        this.ticksInInvalidSituation = 0;
    }

    public EntityType<?> getType() {return type;}
    public String getName() {return name;}
    public TrackedData<Integer> getIdData() {return entityIdDataAccessor;}
    public Vec3d getRelativePositionOffset() {return relativePositionOffset;}

    public @Nullable PartEntity getPart() {return partEntity;}
    public void setPart(@Nullable PartEntity partEntity) {this.partEntity = partEntity;}
    public @Nullable String getPartUUID() {return partEntityUUID;}
    public void setPartUUID(@Nullable String partEntityUUID) {this.partEntityUUID = partEntityUUID;}
    public int getTicksInInvalidSituation() {return ticksInInvalidSituation;}
    public void incrementTicksInInvalidSituation() {this.ticksInInvalidSituation += 1;}
    public void resetTicksInInvalidSituation() {this.ticksInInvalidSituation = 0;}
}
