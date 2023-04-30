package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.GoldShurikenEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class GoldShurikenItem extends AbstractShurikenItem
{
	public GoldShurikenItem(CreativeModeTab group)
	{
		super(new Item.Properties().rarity(Rarity.COMMON), group);
	}
	
	public GoldShurikenItem() //default group
	{
		super(new Item.Properties().rarity(Rarity.COMMON));
	}

	@Override protected float getVelocity() {return 2.0F;}
	@Override protected float getInaccuracy() {return 1.0F;}
	@Override protected int getCooldown() {return 7;}
	@Override protected AbstractShurikenEntity getKnifeEntity(Player playerIn, Level worldIn) {return new GoldShurikenEntity(playerIn, worldIn);}
}