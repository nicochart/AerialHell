package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.LunaticCrystalShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellRarities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class LunaticCrystalShurikenItem extends AbstractShurikenItem
{
	public LunaticCrystalShurikenItem()
	{
		super(new Item.Properties().rarity(AerialHellRarities.LEGENDARY.getValue()));
	}

	@Override protected float getVelocity() {return 1.8F;}
	@Override protected float getInaccuracy() {return 0.0F;}
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractShurikenEntity getShurikenEntity(Player playerIn, Level worldIn) {return new LunaticCrystalShurikenEntity(playerIn, worldIn, this.getDefaultInstance());}
}