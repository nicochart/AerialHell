package fr.factionbedrock.aerialhell.Entity.MultipartEntity;

import org.jspecify.annotations.Nullable;

import java.util.Map;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;

public class PartInfo
{
    private final EntityType<?> type;
    private final String name;
    private final EntityDataAccessor<Integer> entityIdDataAccessor; //entity id (in level)
    @Nullable private PartEntity partEntity;
    @Nullable private String partEntityUUID; //entity uuid
    private final Vec3 unrotatedRelativePositionOffset;
    private int ticksInInvalidSituation;
    private boolean isHead;

    public PartInfo(EntityType<?> type, String name, EntityDataAccessor<Integer> entityIdDataAccessor, Map<String, PartInfo> partsMap) {this(type, name, entityIdDataAccessor, Vec3.ZERO, false, partsMap);}

    public PartInfo(EntityType<?> type, String name, EntityDataAccessor<Integer> entityIdDataAccessor, Vec3 unrotatedRelativePositionOffset, Map<String, PartInfo> partsMap) {this(type, name, entityIdDataAccessor, unrotatedRelativePositionOffset, false, partsMap);}

    public PartInfo(EntityType<?> type, String name, EntityDataAccessor<Integer> entityIdDataAccessor, Vec3 unrotatedRelativePositionOffset, boolean isHead, Map<String, PartInfo> partsMap)
    {
        this.type = type;
        this.name = name;
        this.entityIdDataAccessor = entityIdDataAccessor;
        this.unrotatedRelativePositionOffset = unrotatedRelativePositionOffset;
        partsMap.put(name, this);
        this.ticksInInvalidSituation = 0;
        this.isHead = isHead;
    }

    public EntityType<?> getType() {return type;}
    public String getName() {return name;}
    public EntityDataAccessor<Integer> getIdData() {return entityIdDataAccessor;}
    public Vec3 getUnrotatedRelativePositionOffset() {return unrotatedRelativePositionOffset;}

    public @Nullable PartEntity getPart() {return partEntity;}
    public void setPart(@Nullable PartEntity partEntity) {this.partEntity = partEntity;}
    public @Nullable String getPartUUID() {return partEntityUUID;}
    public void setPartUUID(@Nullable String partEntityUUID) {this.partEntityUUID = partEntityUUID;}
    public int getTicksInInvalidSituation() {return ticksInInvalidSituation;}
    public void incrementTicksInInvalidSituation() {this.ticksInInvalidSituation += 1;}
    public void resetTicksInInvalidSituation() {this.ticksInInvalidSituation = 0;}

    public boolean isHead() {return this.isHead;}
}
