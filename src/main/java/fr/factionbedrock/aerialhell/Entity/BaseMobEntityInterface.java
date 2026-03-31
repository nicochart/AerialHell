package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public interface BaseMobEntityInterface extends BaseEntityInterface
{
    default Vec3 getUnrotatedRelativePosOf(@NotNull Mob entity) {return this.toUnrotatedRelativePos(entity.position());}
    default Vec3 toUnrotatedRelativePos(Vec3 levelPos) {return this.toUnrotatePos(this.toRelativePos(levelPos));}
    default Vec3 toRotatedPos(Vec3 unrotatedRelativePos) {return unrotatedRelativePos.yRot(-this.getRotateAngle());}
    default Vec3 toUnrotatePos(Vec3 rotatedRelativePos) {return rotatedRelativePos.yRot(this.getRotateAngle());}
    default float getRotateAngle() {return (float) Math.toRadians(this.getSelf().yBodyRot);}
    default Vec3 getRelativePosOf(@NotNull Mob mob) {return toRelativePos(mob.position());}
    default Vec3 toRelativePos(Vec3 levelPos) {return levelPos.subtract(this.getSelf().position());}
    default Vec3 fromUnrotatedRelativeToLevelPos(Vec3 unrotatedRelativePos) {return this.toLevelPos(this.toRotatedPos(unrotatedRelativePos));}
    default Vec3 toLevelPos(Vec3 rotatedRelativePos) {return this.getSelf().position().add(rotatedRelativePos);}

    @Override Mob getSelf();

    default LivingEntity getTarget() {return this.getSelf().getTarget();}
    default LookControl getLookControl() {return this.getSelf().getLookControl();}
}
