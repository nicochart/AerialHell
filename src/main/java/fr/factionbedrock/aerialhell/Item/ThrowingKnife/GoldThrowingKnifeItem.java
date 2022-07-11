package fr.factionbedrock.aerialhell.Item.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.AbstractThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife.GoldThrowingKnifeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.world.World;

public class GoldThrowingKnifeItem extends AbstractThrowingKnifeItem
{
	public GoldThrowingKnifeItem(ItemGroup group)
	{
		super(new Item.Properties().rarity(Rarity.COMMON), group);
	}
	
	public GoldThrowingKnifeItem() //default group
	{
		super(new Item.Properties().rarity(Rarity.COMMON));
	}
	
	@Override protected int getCooldown() {return 7;}
	@Override protected AbstractThrowingKnifeEntity getKnifeEntity(PlayerEntity playerIn, World worldIn) {return new GoldThrowingKnifeEntity(playerIn, worldIn);}
}