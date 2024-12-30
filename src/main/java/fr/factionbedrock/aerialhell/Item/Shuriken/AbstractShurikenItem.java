package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public abstract class AbstractShurikenItem extends Item
{
	public AbstractShurikenItem(Item.Settings settings)
	{
		super(settings.maxCount(16));
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
	{
		ItemStack heldItem = user.getStackInHand(hand);
		if (!user.isCreative() && !EntityHelper.hasEnchantment(user, Enchantments.INFINITY))
		{
			heldItem.decrement(1);
		}
		
		world.playSound(null, user.getX(), user.getY(), user.getZ(), AerialHellSoundEvents.ENTITY_SHURIKEN_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
		
		if (!world.isClient())
		{
			AbstractShurikenEntity shuriken = this.getShurikenEntity(user, world);
			shuriken.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, this.getVelocity(), this.getInaccuracy());
			//shuriken.setShooter(playerIn); shooter is not detected
			world.spawnEntity(shuriken);
		}
		user.getItemCooldownManager().set(this, this.getCooldown());
		return TypedActionResult.success(heldItem);
	}

	abstract protected float getVelocity();
	abstract protected float getInaccuracy();
	abstract protected int getCooldown();
	abstract protected AbstractShurikenEntity getShurikenEntity(PlayerEntity player, World world);
}