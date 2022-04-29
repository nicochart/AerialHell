package fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class VoluciteArrowEntity extends AbstractAerialArrowEntity
{
    public VoluciteArrowEntity(EntityType<? extends VoluciteArrowEntity> type, World worldIn)
    {
        super(type, worldIn);
        this.setDamage(9.0D);
    }

    public VoluciteArrowEntity(World worldIn, double x, double y, double z)
    {
        super(AerialHellEntities.VOLUCITE_BLOWPIPE_ARROW.get(), x, y, z, worldIn);
        this.setDamage(9.0D);
    }

    public VoluciteArrowEntity(World worldIn, LivingEntity shooter)
    {
        super(AerialHellEntities.VOLUCITE_BLOWPIPE_ARROW.get(), shooter, worldIn);
        this.setDamage(9.0D);
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
    protected ItemStack getArrowStack()
    {
        return new ItemStack(AerialHellBlocksAndItems.VOLUCITE_BLOWPIPE_ARROW.get());
    }
}