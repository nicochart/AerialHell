package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.IronShurikenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class IronShurikenItem extends AbstractShurikenItem
{
	public IronShurikenItem()
	{
		super(new Item.Settings().rarity(Rarity.COMMON));
	}

	@Override protected float getVelocity() {return 1.8F;}
	@Override protected float getInaccuracy() {return 1.0F;}
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractShurikenEntity getShurikenEntity(PlayerEntity player, World world) {return new IronShurikenEntity(player, world);}
}