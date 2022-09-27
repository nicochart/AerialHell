package fr.factionbedrock.aerialhell.Item.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife.ArsonistThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellRarities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.World;

public class ArsonistThrowingKnifeItem extends AbstractThrowingKnifeItem
{
	public ArsonistThrowingKnifeItem(ItemGroup group)
	{
		super(new Item.Properties().rarity(AerialHellRarities.MYTHICAL), group);
	}
	
	public ArsonistThrowingKnifeItem() //default group
	{
		super(new Item.Properties().rarity(AerialHellRarities.MYTHICAL));
	}
	
	@Override protected int getCooldown() {return 9;}
	@Override protected AbstractThrowingKnifeEntity getKnifeEntity(PlayerEntity playerIn, World worldIn) {return new ArsonistThrowingKnifeEntity(playerIn, worldIn);}
}