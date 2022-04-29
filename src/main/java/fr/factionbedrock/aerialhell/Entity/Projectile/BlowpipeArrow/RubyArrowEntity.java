package fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RubyArrowEntity extends AbstractAerialArrowEntity
{
    public RubyArrowEntity(EntityType<? extends RubyArrowEntity> type, World worldIn)
    {
        super(type, worldIn);
        this.setDamage(6.0D);
    }

    public RubyArrowEntity(World worldIn, double x, double y, double z)
    {
        super(AerialHellEntities.RUBY_BLOWPIPE_ARROW.get(), x, y, z, worldIn);
        this.setDamage(6.0D);
    }

    public RubyArrowEntity(World worldIn, LivingEntity shooter)
    {
        super(AerialHellEntities.RUBY_BLOWPIPE_ARROW.get(), shooter, worldIn);
        this.setDamage(6.0D);
    }
    
    @Override
    protected ItemStack getArrowStack()
    {
        return new ItemStack(AerialHellBlocksAndItems.RUBY_BLOWPIPE_ARROW.get());
    }
}