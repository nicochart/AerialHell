package fr.factionbedrock.aerialhell.Entity.MultipartEntity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Map;

public class PartInfo
{
    private final EntityType<?> type;
    private final String name;
    private final EntityDataAccessor<Integer> entityIdDataAccessor; //entity id (in level)
    @Nullable private PartEntity partEntity;
    @Nullable private String partEntityUUID; //entity uuid
    private final Vec3 relativePositionOffset;

    public PartInfo(EntityType<?> type, String name, EntityDataAccessor<Integer> entityIdDataAccessor, Map<String, PartInfo> partsMap) {this(type, name, entityIdDataAccessor, Vec3.ZERO, partsMap);}

    public PartInfo(EntityType<?> type, String name, EntityDataAccessor<Integer> entityIdDataAccessor, Vec3 relativePositionOffset, Map<String, PartInfo> partsMap)
    {
        this.type = type;
        this.name = name;
        this.entityIdDataAccessor = entityIdDataAccessor;
        this.relativePositionOffset = relativePositionOffset;
        partsMap.put(name, this);
    }

    public EntityType<?> getType() {return type;}
    public String getName() {return name;}
    public EntityDataAccessor<Integer> getIdData() {return entityIdDataAccessor;}
    public Vec3 getRelativePositionOffset() {return relativePositionOffset;}

    public @Nullable PartEntity getPart() {return partEntity;}
    public void setPart(@Nullable PartEntity partEntity) {this.partEntity = partEntity;}
    public @Nullable String getPartUUID() {return partEntityUUID;}
    public void setPartUUID(@Nullable String partEntityUUID) {this.partEntityUUID = partEntityUUID;}
}
