package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.MagmaticGelShurikenEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class MagmaticGelShurikenItem extends AbstractShurikenItem
{
	public MagmaticGelShurikenItem(Item.Properties settings) {super(settings);}

	@Override protected float getVelocity() {return 1.7F;}
	@Override protected float getInaccuracy() {return 1.5F;}
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractShurikenEntity getShurikenEntity(Player player, Level world) {return new MagmaticGelShurikenEntity(player, world, this.getDefaultInstance());}
}