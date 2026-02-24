package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.mob.MobEntity;

public interface BaseMobEntityInterface extends BaseEntityInterface
{
    @Override MobEntity getSelf();

    default LivingEntity getTarget() {return this.getSelf().getTarget();}
    default LookControl getLookControl() {return this.getSelf().getLookControl();}
}
