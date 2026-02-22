package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.LookControl;

public interface BaseMobEntityInterface extends BaseEntityInterface
{
    @Override Mob getSelf();

    default LivingEntity getTarget() {return this.getSelf().getTarget();}
    default LookControl getLookControl() {return this.getSelf().getLookControl();}
}
