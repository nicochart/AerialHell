package fr.factionbedrock.aerialhell.Entity.MultipartEntity;

import fr.factionbedrock.aerialhell.Util.FieldAccessor;
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
    private final FieldAccessor<PartEntity> entityAccessor;
    private final Vec3 relativePositionOffset;

    public PartInfo(EntityType<?> type, String name, EntityDataAccessor<Integer> entityIdDataAccessor, FieldAccessor<PartEntity> partEntityAccessor, Map<String, PartInfo> partsMap) {this(type, name, entityIdDataAccessor, partEntityAccessor, Vec3.ZERO, partsMap);}

    public PartInfo(EntityType<?> type, String name, EntityDataAccessor<Integer> entityIdDataAccessor, FieldAccessor<PartEntity> partEntityAccessor, Vec3 relativePositionOffset, Map<String, PartInfo> partsMap)
    {
        this.type = type;
        this.name = name;
        this.entityIdDataAccessor = entityIdDataAccessor;
        this.entityAccessor = partEntityAccessor;
        this.relativePositionOffset = relativePositionOffset;
        partsMap.put(name, this);
    }

    public EntityType<?> getType() {return type;}
    public String getName() {return name;}
    public EntityDataAccessor<Integer> getIdData() {return entityIdDataAccessor;}
    public Vec3 getRelativePositionOffset() {return relativePositionOffset;}

    @Nullable public PartEntity getPart() {return this.entityAccessor.get();}
    public void setPart(@Nullable PartEntity part) {this.entityAccessor.set(part);}
}
