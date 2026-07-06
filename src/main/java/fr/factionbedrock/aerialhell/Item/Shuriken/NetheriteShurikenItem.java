package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.ShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class NetheriteShurikenItem extends AbstractShurikenItem
{
	public NetheriteShurikenItem()
	{
		super(new Item.Properties().rarity(Rarity.UNCOMMON));
	}

	@Override protected float getVelocity() {return 1.6F;}
	@Override protected float getInaccuracy() {return 1.0F;}
	@Override protected int getCooldown() {return 8;}
	@Override protected ShurikenEntity getShurikenEntity(Player playerIn, Level worldIn) {return AerialHellEntities.NETHERITE_SHURIKEN.get().create(worldIn);}
}