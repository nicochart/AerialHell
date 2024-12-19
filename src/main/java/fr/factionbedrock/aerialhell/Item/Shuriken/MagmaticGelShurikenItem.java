package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.MagmaticGelShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellRarities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class MagmaticGelShurikenItem extends AbstractShurikenItem
{
	public MagmaticGelShurikenItem()
	{
		super(new Item.Settings().rarity(AerialHellRarities.FROZEN));
	}

	@Override protected float getVelocity() {return 1.7F;}
	@Override protected float getInaccuracy() {return 1.5F;}
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractShurikenEntity getKnifeEntity(PlayerEntity player, World world) {return new MagmaticGelShurikenEntity(player, world);}
}