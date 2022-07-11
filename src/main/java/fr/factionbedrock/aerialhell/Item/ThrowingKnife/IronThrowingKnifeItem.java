package fr.factionbedrock.aerialhell.Item.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.AbstractThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife.IronThrowingKnifeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.world.World;

public class IronThrowingKnifeItem extends AbstractThrowingKnifeItem
{
	public IronThrowingKnifeItem(ItemGroup group)
	{
		super(new Item.Properties().rarity(Rarity.COMMON), group);
	}
	
	public IronThrowingKnifeItem() //default group
	{
		super(new Item.Properties().rarity(Rarity.COMMON));
	}
	
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractThrowingKnifeEntity getKnifeEntity(PlayerEntity playerIn, World worldIn) {return new IronThrowingKnifeEntity(playerIn, worldIn);}
}