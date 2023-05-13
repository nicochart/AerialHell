package fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class VoluciteArrowEntity extends AbstractAerialArrowEntity
{
    public VoluciteArrowEntity(EntityType<? extends VoluciteArrowEntity> type, Level worldIn)
    {
        super(type, worldIn);
        this.setBaseDamage(9.0D);
    }

    public VoluciteArrowEntity(Level worldIn, double x, double y, double z)
    {
        super(AerialHellEntities.VOLUCITE_BLOWPIPE_ARROW.get(), x, y, z, worldIn);
        this.setBaseDamage(9.0D);
    }

    public VoluciteArrowEntity(Level worldIn, LivingEntity shooter)
    {
        super(AerialHellEntities.VOLUCITE_BLOWPIPE_ARROW.get(), shooter, worldIn);
        this.setBaseDamage(9.0D);
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
        	this.setDeltaMovement(this.getDeltaMovement().x,this.getDeltaMovement().y-0.01,this.getDeltaMovement().z);
        }
        ++this.ticksLiving;
    }
    
    @Override protected ItemStack getPickupItem() {return new ItemStack(AerialHellBlocksAndItems.VOLUCITE_BLOWPIPE_ARROW.get());}
}