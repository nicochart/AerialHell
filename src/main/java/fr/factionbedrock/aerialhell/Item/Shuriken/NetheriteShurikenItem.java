package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.NetheriteShurikenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.world.World;

public class NetheriteShurikenItem extends AbstractShurikenItem
{
	public NetheriteShurikenItem(ItemGroup group)
	{
		super(new Item.Properties().rarity(Rarity.UNCOMMON), group);
	}
	
	public NetheriteShurikenItem() //default group
	{
		super(new Item.Properties().rarity(Rarity.UNCOMMON));
	}

	@Override protected float getVelocity() {return 1.6F;}
	@Override protected float getInaccuracy() {return 1.0F;}
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractShurikenEntity getKnifeEntity(PlayerEntity playerIn, World worldIn) {return new NetheriteShurikenEntity(playerIn, worldIn);}
}