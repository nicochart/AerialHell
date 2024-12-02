package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.LunaticCrystalShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellRarities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class LunaticCrystalShurikenItem extends AbstractShurikenItem
{
	public LunaticCrystalShurikenItem()
	{
		super(new Item.Settings().rarity(AerialHellRarities.LEGENDARY.getValue()));
	}

	@Override protected float getVelocity() {return 1.8F;}
	@Override protected float getInaccuracy() {return 0.0F;}
	@Override protected int getCooldown() {return 8;}
	@Override protected AbstractShurikenEntity getKnifeEntity(PlayerEntity player, World world) {return new LunaticCrystalShurikenEntity(player, world);}
}