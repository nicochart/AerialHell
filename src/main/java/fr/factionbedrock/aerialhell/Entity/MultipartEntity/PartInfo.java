package fr.factionbedrock.aerialhell.Entity.MultipartEntity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.util.math.Vec3d;

public class PartInfo
{
    private final EntityType<?> type;
    private final String name;
    private final TrackedData<Integer> entityIdDataAccessor; //entity id (in level)
    private final Vec3d relativePositionOffset;

    public PartInfo(EntityType<?> type, String name, TrackedData<Integer> entityIdDataAccessor) {this(type, name, entityIdDataAccessor, Vec3d.ZERO);}

    public PartInfo(EntityType<?> type, String name, TrackedData<Integer> entityIdDataAccessor, Vec3d relativePositionOffset)
    {
        this.type = type;
        this.name = name;
        this.entityIdDataAccessor = entityIdDataAccessor;
        this.relativePositionOffset = relativePositionOffset;
    }

    public EntityType<?> getType() {return type;}
    public String getName() {return name;}
    public TrackedData<Integer> getIdData() {return entityIdDataAccessor;}
    public Vec3d getRelativePositionOffset() {return relativePositionOffset;}
}
