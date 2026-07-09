package fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class RubyArrowEntity extends AbstractAerialArrowEntity
{
    public static final ItemStack DEFAULT_STACK = new ItemStack(AerialHellItems.RUBY_BLOWPIPE_ARROW);

    public RubyArrowEntity(LivingEntity shooter, Level world, ItemStack pickupItemStack)
    {
        this(shooter, world, pickupItemStack, null);
    }

    public RubyArrowEntity(LivingEntity shooter, Level world, ItemStack pickupItemStack, @Nullable ItemStack weapon)
    {
        super(AerialHellEntities.RUBY_BLOWPIPE_ARROW, shooter, world, pickupItemStack, weapon);
        this.setBaseDamage(6.0D);
    }

    public RubyArrowEntity(EntityType<RubyArrowEntity> type, Level world)
    {
        super(type, world);
    }
    
    @Override protected ItemStack getPickupItem() {return new ItemStack(AerialHellItems.RUBY_BLOWPIPE_ARROW);}

    @Override protected ItemStack getDefaultPickupItem() {return new ItemStack(AerialHellItems.RUBY_BLOWPIPE_ARROW);}
}