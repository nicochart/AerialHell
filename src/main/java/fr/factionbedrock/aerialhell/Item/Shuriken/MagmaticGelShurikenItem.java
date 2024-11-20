package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.MagmaticGelShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellRarities;
import net.minecraft.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class MagmaticGelShurikenItem extends AbstractShurikenItem
{
	public MagmaticGelShurikenItem()
	{
		super(new Item.Settings().rarity(AerialHellRarities.FROZEN.getValue()));
	}

	@Override protected float getVelocity() {return 1.7F;}
	@Override protected float getInaccuracy() {return 1.5F;}
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractShurikenEntity getKnifeEntity(Player playerIn, Level worldIn) {return new MagmaticGelShurikenEntity(playerIn, worldIn);}
}