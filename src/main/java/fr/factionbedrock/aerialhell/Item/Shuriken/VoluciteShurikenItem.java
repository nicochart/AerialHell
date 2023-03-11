package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.VoluciteShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellRarities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.World;

public class VoluciteShurikenItem extends AbstractShurikenItem
{
	public VoluciteShurikenItem(ItemGroup group)
	{
		super(new Item.Properties().rarity(AerialHellRarities.VIBRANT), group);
	}
	
	public VoluciteShurikenItem() //default group
	{
		super(new Item.Properties().rarity(AerialHellRarities.VIBRANT));
	}

	@Override protected float getVelocity() {return 1.6F;}
	@Override protected float getInaccuracy() {return 0.0F;}
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractShurikenEntity getKnifeEntity(PlayerEntity playerIn, World worldIn) {return new VoluciteShurikenEntity(playerIn, worldIn);}
}