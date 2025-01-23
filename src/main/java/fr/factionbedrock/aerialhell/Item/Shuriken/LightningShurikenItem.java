package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.LightningShurikenEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class LightningShurikenItem extends AbstractShurikenItem
{
	public LightningShurikenItem(Item.Properties properties)
	{
		super(properties);
	}

	@Override protected float getVelocity() {return 1.7F;}
	@Override protected float getInaccuracy() {return 1.0F;}
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractShurikenEntity getShurikenEntity(Player playerIn, Level worldIn) {return new LightningShurikenEntity(playerIn, worldIn, this.getDefaultInstance());}
}