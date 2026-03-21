package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class VoluciteWardenCollidablePartEntity extends VoluciteWardenPartEntity
{
    public VoluciteWardenCollidablePartEntity(EntityType<? extends VoluciteWardenPartEntity> type, Level level) {super(type, level);}

    @Override public boolean canBeCollidedWith(@Nullable Entity entity) {return true;} //block-like collision
}
