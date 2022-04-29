package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow.RubyArrowEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow.VoluciteArrowEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AerialArrowItem extends Item
{
    public AerialArrowItem(Properties properties)
    {
        super(properties);
    }

    public AbstractAerialArrowEntity createArrow(World world, ItemStack stack, LivingEntity shooter)
    {
    	if (this == AerialHellBlocksAndItems.RUBY_BLOWPIPE_ARROW.get())
    	{
    		return new RubyArrowEntity(world, shooter);
    	}
    	else //if (this == AerialHellBlocksAndItems.VOLUCITE_BLOWPIPE_ARROW.get())
    	{
    		return new VoluciteArrowEntity(world, shooter);
    	}
    }
}