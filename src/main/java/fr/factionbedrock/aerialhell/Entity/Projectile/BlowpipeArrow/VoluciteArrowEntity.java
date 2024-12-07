package fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class VoluciteArrowEntity extends AbstractAerialArrowEntity
{
    public static final ItemStack DEFAULT_STACK = new ItemStack(AerialHellItems.VOLUCITE_BLOWPIPE_ARROW);

    public VoluciteArrowEntity(LivingEntity shooter, World world, ItemStack pickupItemStack)
    {
        this(shooter, world, pickupItemStack, null);
    }

    public VoluciteArrowEntity(LivingEntity shooter, World world, ItemStack pickupItemStack, @Nullable ItemStack weapon)
    {
        super(AerialHellEntities.VOLUCITE_BLOWPIPE_ARROW, shooter, world, pickupItemStack, weapon);
        this.setDamage(9.0D);
    }

    public VoluciteArrowEntity(EntityType<VoluciteArrowEntity> type, World world)
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
        	this.setVelocity(this.getVelocity().x,this.getVelocity().y-0.01,this.getVelocity().z);
        }
        ++this.ticksLiving;
    }
    
    @Override protected ItemStack asItemStack() {return new ItemStack(AerialHellItems.VOLUCITE_BLOWPIPE_ARROW);}

    @Override protected ItemStack getDefaultItemStack() {return new ItemStack(AerialHellItems.VOLUCITE_BLOWPIPE_ARROW);}
}