package fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class VoluciteArrowEntity extends AbstractAerialArrowEntity
{
    public static final ItemStack DEFAULT_STACK = new ItemStack(AerialHellItems.VOLUCITE_BLOWPIPE_ARROW);

    public VoluciteArrowEntity(LivingEntity shooter, Level world, ItemStack pickupItemStack)
    {
        this(shooter, world, pickupItemStack, null);
    }

    public VoluciteArrowEntity(LivingEntity shooter, Level world, ItemStack pickupItemStack, @Nullable ItemStack weapon)
    {
        super(AerialHellEntities.VOLUCITE_BLOWPIPE_ARROW, shooter, world, pickupItemStack, weapon);
        this.setBaseDamage(9.0D);
    }

    public VoluciteArrowEntity(EntityType<VoluciteArrowEntity> type, Level world)
    {
        super(type, world);
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
    
    @Override protected ItemStack getPickupItem() {return new ItemStack(AerialHellItems.VOLUCITE_BLOWPIPE_ARROW);}

    @Override protected ItemStack getDefaultPickupItem() {return new ItemStack(AerialHellItems.VOLUCITE_BLOWPIPE_ARROW);}
}