package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.GoldShurikenEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class GoldShurikenItem extends AbstractShurikenItem
{
	public GoldShurikenItem()
	{
		super(new Item.Properties().rarity(Rarity.COMMON));
	}

	@Override protected float getVelocity() {return 2.0F;}
	@Override protected float getInaccuracy() {return 1.0F;}
	@Override protected int getCooldown() {return 7;}
	@Override protected AbstractShurikenEntity getShurikenEntity(Player playerIn, Level worldIn) {return new GoldShurikenEntity(playerIn, worldIn);}
}