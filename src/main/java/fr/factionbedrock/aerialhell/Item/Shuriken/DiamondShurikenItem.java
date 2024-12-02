package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.DiamondShurikenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class DiamondShurikenItem extends AbstractShurikenItem
{
	public DiamondShurikenItem()
	{
		super(new Item.Settings().rarity(Rarity.COMMON));
	}

	@Override protected float getVelocity() {return 1.8F;}
	@Override protected float getInaccuracy() {return 1.0F;}
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractShurikenEntity getKnifeEntity(PlayerEntity player, World world) {return new DiamondShurikenEntity(player, world);}
}