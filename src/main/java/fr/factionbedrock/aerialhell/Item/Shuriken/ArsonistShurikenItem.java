package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.FlamingShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellRarities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class ArsonistShurikenItem extends AbstractShurikenItem
{
	public ArsonistShurikenItem()
	{
		super(new Item.Properties().rarity(AerialHellRarities.MYTHICAL.getValue()));
	}

	@Override protected float getVelocity() {return 1.7F;}
	@Override protected float getInaccuracy() {return 1.0F;}
	@Override protected int getCooldown() {return 9;}
	@Override protected FlamingShurikenEntity getShurikenEntity(Player playerIn, Level worldIn) {return AerialHellEntities.ARSONIST_SHURIKEN.get().create(worldIn);}
}