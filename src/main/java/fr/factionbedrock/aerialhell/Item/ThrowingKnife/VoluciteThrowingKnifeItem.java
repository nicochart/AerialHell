package fr.factionbedrock.aerialhell.Item.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.AbstractThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife.VoluciteThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellRarities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.World;

public class VoluciteThrowingKnifeItem extends AbstractThrowingKnifeItem
{
	public VoluciteThrowingKnifeItem(ItemGroup group)
	{
		super(new Item.Properties().rarity(AerialHellRarities.VIBRANT), group);
	}
	
	public VoluciteThrowingKnifeItem() //default group
	{
		super(new Item.Properties().rarity(AerialHellRarities.VIBRANT));
	}
	
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractThrowingKnifeEntity getKnifeEntity(PlayerEntity playerIn, World worldIn) {return new VoluciteThrowingKnifeEntity(playerIn, worldIn);}
}