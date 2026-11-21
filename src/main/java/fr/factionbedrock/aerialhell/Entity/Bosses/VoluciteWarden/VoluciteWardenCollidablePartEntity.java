package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class VoluciteWardenCollidablePartEntity extends VoluciteWardenPartEntity
{
    public VoluciteWardenCollidablePartEntity(EntityType<? extends VoluciteWardenPartEntity> type, Level level) {super(type, level);}

    //block-like collision, only for an anchored warden
    @Override public boolean canBeCollidedWith(@Nullable Entity entity)
    {
        if (this.getMaster() != null && this.getMaster().getSelf() instanceof VoluciteWardenEntity warden) {return warden.isAnchored();}
        return true;
    }
}
