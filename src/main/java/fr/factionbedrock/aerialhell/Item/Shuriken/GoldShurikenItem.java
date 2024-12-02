package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.GoldShurikenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class GoldShurikenItem extends AbstractShurikenItem
{
	public GoldShurikenItem()
	{
		super(new Item.Settings().rarity(Rarity.COMMON));
	}

	@Override protected float getVelocity() {return 2.0F;}
	@Override protected float getInaccuracy() {return 1.0F;}
	@Override protected int getCooldown() {return 7;}
	@Override protected AbstractShurikenEntity getKnifeEntity(PlayerEntity player, World world) {return new GoldShurikenEntity(player, world);}
}