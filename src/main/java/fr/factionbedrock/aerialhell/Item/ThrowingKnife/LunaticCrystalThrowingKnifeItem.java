package fr.factionbedrock.aerialhell.Item.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife.LunaticCrystalThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellRarities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.World;

public class LunaticCrystalThrowingKnifeItem extends AbstractThrowingKnifeItem
{
	public LunaticCrystalThrowingKnifeItem(ItemGroup group)
	{
		super(new Item.Properties().rarity(AerialHellRarities.LEGENDARY), group);
	}
	
	public LunaticCrystalThrowingKnifeItem() //default group
	{
		super(new Item.Properties().rarity(AerialHellRarities.LEGENDARY));
	}
	
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractThrowingKnifeEntity getKnifeEntity(PlayerEntity playerIn, World worldIn) {return new LunaticCrystalThrowingKnifeEntity(playerIn, worldIn);}
}