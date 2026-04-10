package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.DiamondShurikenEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class DiamondShurikenItem extends AbstractShurikenItem
{
	public DiamondShurikenItem(Item.Properties settings) {super(settings);}

	@Override protected float getVelocity() {return 1.8F;}
	@Override protected float getInaccuracy() {return 1.0F;}
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractShurikenEntity getShurikenEntity(Player player, Level world) {return new DiamondShurikenEntity(player, world, this.getDefaultInstance());}
}