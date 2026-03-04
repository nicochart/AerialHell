package fr.factionbedrock.aerialhell.Entity.MultipartEntity;

import org.jspecify.annotations.Nullable;

public class PartEntityFieldsAccessor
{
    @Nullable private PartEntity partEntity;
    @Nullable private String partEntityUUID;

    public PartEntityFieldsAccessor() {}

    public @Nullable PartEntity getPartEntity() {return partEntity;}
    public void setPartEntity(@Nullable PartEntity partEntity) {this.partEntity = partEntity;}
    public @Nullable String getPartEntityUUID() {return partEntityUUID;}
    public void setPartEntityUUID(@Nullable String partEntityUUID) {this.partEntityUUID = partEntityUUID;}
}
