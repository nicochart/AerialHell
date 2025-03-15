package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class AutomatonEntity extends AerialHellHostileEntity
{
    public int attackTimer;
    public AutomatonEntity(EntityType<? extends HostileEntity> type, World world) {super(type, world); this.attackTimer = 0;}

    @Override
    public void tickMovement()
    {
        if (this.attackTimer > 0) {this.attackTimer--;}
        super.tickMovement();
    }

    @Override
    public void handleStatus(byte id)
    {
        if (id == 4) {this.attackTimer = 10;}
        else {super.handleStatus(id);}
    }

    @Override
    public boolean tryAttack(ServerWorld serverWorld, Entity entityIn)
    {
        boolean flag = super.tryAttack(serverWorld, entityIn);
        this.getWorld().sendEntityStatus(this, (byte)4);
        return flag;
    }

    @Override
    public boolean damage(ServerWorld serverWorld, DamageSource source, float amount)
    {
        Entity immediateSourceEntity = source.getSource();
        Entity trueSourceEntity = source.getAttacker();
        boolean flag = super.damage(serverWorld, source, amount);
        if (flag)
        {
            if (trueSourceEntity instanceof LivingEntity && !(immediateSourceEntity instanceof PersistentProjectileEntity))
            {
                if (!(trueSourceEntity instanceof PlayerEntity && ((PlayerEntity)trueSourceEntity).isCreative()))
                {
                    this.setTarget((LivingEntity) trueSourceEntity);
                }
            }
        }
        return flag;
    }
}