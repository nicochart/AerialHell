package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow.RubyArrowEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow.VoluciteArrowEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AerialArrowItem extends Item
{
    public AerialArrowItem(Properties properties)
    {
        super(properties);
    }

    public AbstractAerialArrowEntity createArrow(Level level, ItemStack stack, LivingEntity shooter)
    {
    	if (this == AerialHellItems.RUBY_BLOWPIPE_ARROW.get())
    	{
    		return new RubyArrowEntity(shooter, level, RubyArrowEntity.DEFAULT_STACK);
    	}
    	else //if (this == AerialHellItems.VOLUCITE_BLOWPIPE_ARROW.get())
    	{
    		return new VoluciteArrowEntity(shooter, level, VoluciteArrowEntity.DEFAULT_STACK);
    	}
    }
}