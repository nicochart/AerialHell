package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public abstract class AbstractShurikenItem extends Item
{
	public AbstractShurikenItem(Item.Properties settings)
	{
		super(settings.stacksTo(16));
	}
	
	@Override
	public InteractionResult use(Level world, Player user, InteractionHand hand)
	{
		ItemStack heldItem = user.getItemInHand(hand);
		if (!user.isCreative() && !EntityHelper.hasEnchantment(user, Enchantments.INFINITY))
		{
			heldItem.shrink(1);
		}
		
		world.playSound(null, user.getX(), user.getY(), user.getZ(), AerialHellSoundEvents.ENTITY_SHURIKEN_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
		
		if (!world.isClientSide())
		{
			AbstractShurikenEntity shuriken = this.getShurikenEntity(user, world);
			shuriken.shootFromRotation(user, user.getXRot(), user.getYRot(), 0.0F, this.getVelocity(), this.getInaccuracy());
			//shuriken.setShooter(playerIn); shooter is not detected
			world.addFreshEntity(shuriken);
		}
		user.getCooldowns().addCooldown(heldItem, this.getCooldown());
		return InteractionResult.SUCCESS;
	}

	abstract protected float getVelocity();
	abstract protected float getInaccuracy();
	abstract protected int getCooldown();
	abstract protected AbstractShurikenEntity getShurikenEntity(Player player, Level world);
}