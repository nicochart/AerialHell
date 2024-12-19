package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.ArsonistShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellRarities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class ArsonistShurikenItem extends AbstractShurikenItem
{
	public ArsonistShurikenItem()
	{
		super(new Item.Settings().rarity(AerialHellRarities.MYTHICAL));
	}

	@Override protected float getVelocity() {return 1.7F;}
	@Override protected float getInaccuracy() {return 1.0F;}
	@Override protected int getCooldown() {return 9;}
	@Override protected AbstractShurikenEntity getKnifeEntity(PlayerEntity player, World world) {return new ArsonistShurikenEntity(player, world);}
}