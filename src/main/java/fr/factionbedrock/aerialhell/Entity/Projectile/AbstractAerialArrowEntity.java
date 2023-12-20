package fr.factionbedrock.aerialhell.Entity.Projectile;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeHooks;

public abstract class AbstractAerialArrowEntity extends AbstractArrow
{
    public int ticksLiving;

    protected AbstractAerialArrowEntity(EntityType<? extends AbstractArrow> type, Level worldIn, ItemStack itemStack)
    {
        super(type, worldIn, itemStack);
        this.ticksLiving = 0;
    }

    public AbstractAerialArrowEntity(EntityType<? extends AbstractArrow> type, double x, double y, double z, Level worldIn, ItemStack itemStack)
    {
        super(type, x, y, z, worldIn, itemStack);
        this.ticksLiving = 0;
    }

    public AbstractAerialArrowEntity(EntityType<? extends AbstractArrow> type, LivingEntity shooter, Level worldIn, ItemStack itemStack)
    {
        super(type, shooter, worldIn, itemStack);
        this.ticksLiving = 0;
    }


    @Override
    protected void onHitBlock(BlockHitResult result)
    {
        super.onHitBlock(result);
        this.setNoGravity(false);
    }

    //public Packet<ClientGamePacketListener> getAddEntityPacket() {return ForgeHooks.getEntitySpawnPacket(this);}
}