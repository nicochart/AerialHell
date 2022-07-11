package fr.factionbedrock.aerialhell.Item.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.AbstractThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife.LightningThrowingKnifeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.world.World;

public class LightningThrowingKnifeItem extends AbstractThrowingKnifeItem
{
	public LightningThrowingKnifeItem(ItemGroup group)
	{
		super(new Item.Properties().rarity(Rarity.UNCOMMON), group);
	}
	
	public LightningThrowingKnifeItem() //default group
	{
		super(new Item.Properties().rarity(Rarity.UNCOMMON));
	}
	
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractThrowingKnifeEntity getKnifeEntity(PlayerEntity playerIn, World worldIn) {return new LightningThrowingKnifeEntity(playerIn, worldIn);}
}