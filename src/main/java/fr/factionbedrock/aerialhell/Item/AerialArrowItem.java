package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow.RubyArrowEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow.VoluciteArrowEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.item.Item;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AerialArrowItem extends Item
{
    public AerialArrowItem(Item.Settings settings)
    {
        super(settings);
    }

    public AbstractAerialArrowEntity createArrow(Level level, ItemStack stack, LivingEntity shooter)
    {
    	if (this == AerialHellBlocksAndItems.RUBY_BLOWPIPE_ARROW.get())
    	{
    		return new RubyArrowEntity(shooter, level, RubyArrowEntity.DEFAULT_STACK);
    	}
    	else //if (this == AerialHellBlocksAndItems.VOLUCITE_BLOWPIPE_ARROW.get())
    	{
    		return new VoluciteArrowEntity(shooter, level, VoluciteArrowEntity.DEFAULT_STACK);
    	}
    }
}