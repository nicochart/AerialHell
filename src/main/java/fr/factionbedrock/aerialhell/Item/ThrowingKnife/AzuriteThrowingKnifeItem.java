package fr.factionbedrock.aerialhell.Item.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.AbstractThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife.AzuriteThrowingKnifeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.world.World;

public class AzuriteThrowingKnifeItem extends AbstractThrowingKnifeItem
{
	public AzuriteThrowingKnifeItem(ItemGroup group)
	{
		super(new Item.Properties().rarity(Rarity.COMMON), group);
	}
	
	public AzuriteThrowingKnifeItem() //default group
	{
		super(new Item.Properties().rarity(Rarity.COMMON));
	}
	
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractThrowingKnifeEntity getKnifeEntity(PlayerEntity playerIn, World worldIn) {return new AzuriteThrowingKnifeEntity(playerIn, worldIn);}
}