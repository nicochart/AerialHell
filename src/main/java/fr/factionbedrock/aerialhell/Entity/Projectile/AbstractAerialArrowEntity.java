package fr.factionbedrock.aerialhell.Entity.Projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class AbstractAerialArrowEntity extends AbstractArrowEntity
{
    private int ticksLiving;
    protected AbstractAerialArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn)
    {
        super(type, worldIn);
        this.ticksLiving = 0;
    }

    public AbstractAerialArrowEntity(EntityType<? extends AbstractArrowEntity> type, double x, double y, double z, World worldIn)
    {
        super(type, x, y, z, worldIn);
        this.ticksLiving = 0;
    }

    public AbstractAerialArrowEntity(EntityType<? extends AbstractArrowEntity> type, LivingEntity shooter, World worldIn)
    {
        super(type, shooter, worldIn);
        this.ticksLiving = 0;
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.ticksLiving == 100)
        {
        	this.setNoGravity(false);
        }
        if (this.ticksLiving > 50 && this.ticksLiving < 100)
        {
        	this.setMotion(this.getMotion().getX(),this.getMotion().getY()-0.01,this.getMotion().getZ());
        }
        ++this.ticksLiving;
    }

    @Override
    protected void func_230299_a_(BlockRayTraceResult result)
    {
        super.func_230299_a_(result);
        this.setNoGravity(false);
    }

    @Override
    public IPacket<?> createSpawnPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}