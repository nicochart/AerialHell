package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.VoluciteArrowEntity;
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
        return new VoluciteArrowEntity(world, shooter);
    }
}