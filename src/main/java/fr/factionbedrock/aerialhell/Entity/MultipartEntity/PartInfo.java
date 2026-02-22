package fr.factionbedrock.aerialhell.Entity.MultipartEntity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;

public class PartInfo
{
    private final EntityType<?> type;
    private final String name;
    private final int id; //part id
    private final EntityDataAccessor<Integer> entityIdDataAccessor; //entity id (in level)
    private final Vec3 relativePositionOffset;

    public PartInfo(EntityType<?> type, String name, int id, EntityDataAccessor<Integer> entityIdDataAccessor) {this(type, name, id, entityIdDataAccessor, Vec3.ZERO);}

    public PartInfo(EntityType<?> type, String name, int id, EntityDataAccessor<Integer> entityIdDataAccessor, Vec3 relativePositionOffset)
    {
        this.type = type;
        this.name = name;
        this.id = id;
        this.entityIdDataAccessor = entityIdDataAccessor;
        this.relativePositionOffset = relativePositionOffset;
    }

    public EntityType<?> getType() {return type;}
    public String getName() {return name;}
    public int getId() {return id;}
    public EntityDataAccessor<Integer> getIdData() {return entityIdDataAccessor;}
    public Vec3 getRelativePositionOffset() {return relativePositionOffset;}
}
