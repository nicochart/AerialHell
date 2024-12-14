package fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RubyArrowEntity extends AbstractAerialArrowEntity
{
    public static final ItemStack DEFAULT_STACK = new ItemStack(AerialHellItems.RUBY_BLOWPIPE_ARROW);

    public RubyArrowEntity(LivingEntity shooter, World world, ItemStack pickupItemStack)
    {
        this(shooter, world, pickupItemStack, null);
    }

    public RubyArrowEntity(LivingEntity shooter, World world, ItemStack pickupItemStack, @Nullable ItemStack weapon)
    {
        super(AerialHellEntities.RUBY_BLOWPIPE_ARROW, shooter, world, pickupItemStack, weapon);
        this.setDamage(6.0D);
    }

    public RubyArrowEntity(EntityType<RubyArrowEntity> type, World world)
    {
        super(type, world);
    }
    
    @Override protected ItemStack asItemStack() {return new ItemStack(AerialHellItems.RUBY_BLOWPIPE_ARROW);}

    @Override protected ItemStack getDefaultItemStack() {return new ItemStack(AerialHellItems.RUBY_BLOWPIPE_ARROW);}
}