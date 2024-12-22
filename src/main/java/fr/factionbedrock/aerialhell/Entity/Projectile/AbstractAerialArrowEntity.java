package fr.factionbedrock.aerialhell.Entity.Projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractAerialArrowEntity extends PersistentProjectileEntity
{
    public int ticksLiving;

    public AbstractAerialArrowEntity(EntityType<? extends PersistentProjectileEntity> type, LivingEntity shooter, World world, ItemStack pickupItemStack)
    {
        this(type, shooter, world, pickupItemStack, null);
    }

    public AbstractAerialArrowEntity(EntityType<? extends PersistentProjectileEntity> type, LivingEntity shooter, World world, ItemStack pickupItemStack, @Nullable ItemStack weapon)
    {
        super(type, shooter, world, pickupItemStack, weapon);
        this.ticksLiving = 0;
    }

    public AbstractAerialArrowEntity(EntityType<? extends AbstractAerialArrowEntity> type, World world)
    {
        super(type, world);
    }

    @Override
    protected void onBlockHit(BlockHitResult result)
    {
        super.onBlockHit(result);
        this.setNoGravity(false);
    }

    //public Packet<ClientGamePacketListener> getAddEntityPacket() {return ForgeHooks.getEntitySpawnPacket(this);}
}