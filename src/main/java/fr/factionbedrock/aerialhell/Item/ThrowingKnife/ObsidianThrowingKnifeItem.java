package fr.factionbedrock.aerialhell.Item.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife.ObsidianThrowingKnifeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.world.World;

public class ObsidianThrowingKnifeItem extends AbstractThrowingKnifeItem
{
	public ObsidianThrowingKnifeItem(ItemGroup group)
	{
		super(new Item.Properties().rarity(Rarity.EPIC), group);
	}
	
	public ObsidianThrowingKnifeItem() //default group
	{
		super(new Item.Properties().rarity(Rarity.EPIC));
	}
	
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractThrowingKnifeEntity getKnifeEntity(PlayerEntity playerIn, World worldIn) {return new ObsidianThrowingKnifeEntity(playerIn, worldIn);}
}