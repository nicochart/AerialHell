package fr.factionbedrock.aerialhell.Item.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife.MagmaticGelThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellRarities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.World;

public class MagmaticGelThrowingKnifeItem extends AbstractThrowingKnifeItem
{
	public MagmaticGelThrowingKnifeItem(ItemGroup group)
	{
		super(new Item.Properties().rarity(AerialHellRarities.FROZEN), group);
	}
	
	public MagmaticGelThrowingKnifeItem() //default group
	{
		super(new Item.Properties().rarity(AerialHellRarities.FROZEN));
	}
	
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractThrowingKnifeEntity getKnifeEntity(PlayerEntity playerIn, World worldIn) {return new MagmaticGelThrowingKnifeEntity(playerIn, worldIn);}
}