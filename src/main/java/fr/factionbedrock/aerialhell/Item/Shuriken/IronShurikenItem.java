package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.IronShurikenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.world.World;

public class IronShurikenItem extends AbstractShurikenItem
{
	public IronShurikenItem(ItemGroup group)
	{
		super(new Item.Properties().rarity(Rarity.COMMON), group);
	}
	
	public IronShurikenItem() //default group
	{
		super(new Item.Properties().rarity(Rarity.COMMON));
	}

	@Override protected float getVelocity() {return 1.8F;}
	@Override protected float getInaccuracy() {return 1.0F;}
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractShurikenEntity getKnifeEntity(PlayerEntity playerIn, World worldIn) {return new IronShurikenEntity(playerIn, worldIn);}
}