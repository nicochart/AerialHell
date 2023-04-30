package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.ArsonistShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellRarities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.Level;

public class ArsonistShurikenItem extends AbstractShurikenItem
{
	public ArsonistShurikenItem(CreativeModeTab group)
	{
		super(new Item.Properties().rarity(AerialHellRarities.MYTHICAL), group);
	}
	
	public ArsonistShurikenItem() //default group
	{
		super(new Item.Properties().rarity(AerialHellRarities.MYTHICAL));
	}

	@Override protected float getVelocity() {return 1.7F;}
	@Override protected float getInaccuracy() {return 1.0F;}
	@Override protected int getCooldown() {return 9;}
	@Override protected AbstractShurikenEntity getKnifeEntity(Player playerIn, Level worldIn) {return new ArsonistShurikenEntity(playerIn, worldIn);}
}