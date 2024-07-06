package fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class VoluciteArrowEntity extends AbstractAerialArrowEntity
{
    public static final ItemStack DEFAULT_STACK = new ItemStack(AerialHellBlocksAndItems.VOLUCITE_BLOWPIPE_ARROW.get());

    public VoluciteArrowEntity(LivingEntity shooter, Level level, ItemStack pickupItemStack)
    {
        this(shooter, level, pickupItemStack, null);
    }

    public VoluciteArrowEntity(LivingEntity shooter, Level level, ItemStack pickupItemStack, @Nullable ItemStack weapon)
    {
        super(AerialHellEntities.VOLUCITE_BLOWPIPE_ARROW.get(), shooter, level, pickupItemStack, weapon);
        this.setBaseDamage(9.0D);
    }

    public VoluciteArrowEntity(EntityType<VoluciteArrowEntity> type, Level level)
    {
        super(type, level);
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

    @Override protected ItemStack getDefaultPickupItem() {return new ItemStack(AerialHellBlocksAndItems.VOLUCITE_BLOWPIPE_ARROW.get());}
}