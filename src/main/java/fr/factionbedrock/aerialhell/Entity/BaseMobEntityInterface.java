package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

public interface BaseMobEntityInterface extends BaseEntityInterface
{
    default Vec3d getUnrotatedRelativePosOf(@NotNull MobEntity entity) {return this.toUnrotatedRelativePos(entity.getEntityPos());}
    default Vec3d toUnrotatedRelativePos(Vec3d levelPos) {return this.toUnrotatePos(this.toRelativePos(levelPos));}
    default Vec3d toRotatedPos(Vec3d unrotatedRelativePos) {return unrotatedRelativePos.rotateY(-this.getRotateAngle());}
    default Vec3d toUnrotatePos(Vec3d rotatedRelativePos) {return rotatedRelativePos.rotateY(this.getRotateAngle());}
    default float getRotateAngle() {return (float) Math.toRadians(this.getSelf().bodyYaw);}
    default Vec3d getRelativePosOf(@NotNull MobEntity entity) {return toRelativePos(entity.getEntityPos());}
    default Vec3d toRelativePos(Vec3d levelPos) {return levelPos.subtract(this.getSelf().getEntityPos());}
    default Vec3d fromUnrotatedRelativeToLevelPos(Vec3d unrotatedRelativePos) {return this.toLevelPos(this.toRotatedPos(unrotatedRelativePos));}
    default Vec3d toLevelPos(Vec3d rotatedRelativePos) {return this.getSelf().getEntityPos().add(rotatedRelativePos);}
    
    @Override MobEntity getSelf();

    default LivingEntity getTarget() {return this.getSelf().getTarget();}
    default LookControl getLookControl() {return this.getSelf().getLookControl();}
}
