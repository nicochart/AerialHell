package fr.factionbedrock.aerialhell.Entity.Projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public abstract class AbstractAerialArrowEntity extends AbstractArrow
{
    public int ticksLiving;

    public AbstractAerialArrowEntity(EntityType<? extends AbstractArrow> type, LivingEntity shooter, Level worldIn, ItemStack pickupItemStack)
    {
        this(type, shooter, worldIn, pickupItemStack, null);
    }

    public AbstractAerialArrowEntity(EntityType<? extends AbstractArrow> type, LivingEntity shooter, Level worldIn, ItemStack pickupItemStack, @Nullable ItemStack weapon)
    {
        super(type, shooter, worldIn, pickupItemStack, weapon);
        this.ticksLiving = 0;
    }

    public AbstractAerialArrowEntity(EntityType<? extends AbstractAerialArrowEntity> type, Level level)
    {
        super(type, level);
    }

    @Override
    protected void onHitBlock(BlockHitResult result)
    {
        super.onHitBlock(result);
        this.setNoGravity(false);
    }

    //public Packet<ClientGamePacketListener> getAddEntityPacket() {return ForgeHooks.getEntitySpawnPacket(this);}
}