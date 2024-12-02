package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.AzuriteShurikenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class AzuriteShurikenItem extends AbstractShurikenItem
{
	public AzuriteShurikenItem()
	{
		super(new Item.Settings().rarity(Rarity.COMMON));
	}

	@Override protected float getVelocity() {return 2.0F;}
	@Override protected float getInaccuracy() {return 1.0F;}
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractShurikenEntity getKnifeEntity(PlayerEntity player, World world) {return new AzuriteShurikenEntity(player, world);}
}