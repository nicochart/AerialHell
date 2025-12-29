package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.level.Level;

public class AutomatonEntity extends AerialHellHostileEntity
{
    public int attackTimer;
    public AutomatonEntity(EntityType<? extends Monster> type, Level world) {super(type, world); this.attackTimer = 0;}

    @Override
    public void aiStep()
    {
        if (this.attackTimer > 0) {this.attackTimer--;}
        super.aiStep();
    }

    @Override
    public void handleEntityEvent(byte id)
    {
        if (id == 4) {this.attackTimer = 10;}
        else {super.handleEntityEvent(id);}
    }

    @Override
    public boolean doHurtTarget(ServerLevel serverLevel, Entity entityIn)
    {
        boolean flag = super.doHurtTarget(serverLevel, entityIn);
        this.level().broadcastEntityEvent(this, (byte)4);
        return flag;
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float amount)
    {
        Entity immediateSourceEntity = source.getDirectEntity();
        Entity trueSourceEntity = source.getEntity();
        boolean flag = super.hurtServer(level, source, amount);
        if (flag)
        {
            if (trueSourceEntity instanceof LivingEntity && !(immediateSourceEntity instanceof AbstractArrow))
            {
                if (!(trueSourceEntity instanceof Player && ((Player)trueSourceEntity).isCreative()))
                {
                    this.setTarget((LivingEntity) trueSourceEntity);
                }
            }
        }
        return flag;
    }
}