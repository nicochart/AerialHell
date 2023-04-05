package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AerialHellHostileEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AutomatonEntity extends AerialHellHostileEntity
{
    public int attackTimer;
    public AutomatonEntity(EntityType<? extends MonsterEntity> type, World world) {super(type, world); this.attackTimer = 0;}

    @Override
    public void livingTick()
    {
        if (this.attackTimer > 0) {this.attackTimer--;}
        super.livingTick();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 4) {this.attackTimer = 10;}
        else {super.handleStatusUpdate(id);}
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);
        this.world.setEntityState(this, (byte)4);
        return flag;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        Entity immediateSourceEntity = source.getImmediateSource();
        Entity trueSourceEntity = source.getTrueSource();
        boolean flag = super.attackEntityFrom(source, amount);
        if (flag)
        {
            if (trueSourceEntity instanceof LivingEntity && !(immediateSourceEntity instanceof AbstractArrowEntity))
            {
                if (!(trueSourceEntity instanceof PlayerEntity && ((PlayerEntity)trueSourceEntity).isCreative()))
                {
                    this.setAttackTarget((LivingEntity) trueSourceEntity);
                }
            }
        }
        return flag;
    }
}