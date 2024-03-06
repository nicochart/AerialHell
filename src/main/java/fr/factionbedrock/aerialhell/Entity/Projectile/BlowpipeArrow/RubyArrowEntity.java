package fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RubyArrowEntity extends AbstractAerialArrowEntity
{
    public RubyArrowEntity(EntityType<? extends RubyArrowEntity> type, Level worldIn)
    {
        super(type, worldIn);
        this.setBaseDamage(6.0D);
    }

    public RubyArrowEntity(Level worldIn, double x, double y, double z)
    {
        super(AerialHellEntities.RUBY_BLOWPIPE_ARROW.get(), x, y, z, worldIn);
        this.setBaseDamage(6.0D);
    }

    public RubyArrowEntity(Level worldIn, LivingEntity shooter)
    {
        super(AerialHellEntities.RUBY_BLOWPIPE_ARROW.get(), shooter, worldIn);
        this.setBaseDamage(6.0D);
    }

    @Override protected ItemStack getPickupItem() {return new ItemStack(AerialHellBlocksAndItems.RUBY_BLOWPIPE_ARROW.get());}
}