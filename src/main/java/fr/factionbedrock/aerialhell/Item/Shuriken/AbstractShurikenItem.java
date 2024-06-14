package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public abstract class AbstractShurikenItem extends Item
{
	public AbstractShurikenItem(Properties properties)
	{
		super(properties.stacksTo(16));
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand hand)
	{
		ItemStack heldItem = playerIn.getItemInHand(hand);
		
		if (!playerIn.isCreative() && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY, heldItem) == 0)
		{
			heldItem.shrink(1);
		}
		
		worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), AerialHellSoundEvents.ENTITY_SHURIKEN_SHOOT.get(), SoundSource.PLAYERS, 1.0F, 1.0F / (worldIn.getRandom().nextFloat() * 0.4F + 0.8F));
		
		if (!worldIn.isClientSide())
		{
			AbstractShurikenEntity shuriken = this.getKnifeEntity(playerIn, worldIn);
			shuriken.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, this.getVelocity(), this.getInaccuracy());
			//shuriken.setShooter(playerIn); shooter is not detected
			worldIn.addFreshEntity(shuriken);
		}
		playerIn.getCooldowns().addCooldown(this, this.getCooldown());
		return InteractionResultHolder.success(heldItem);
	}

	abstract protected float getVelocity();
	abstract protected float getInaccuracy();
	abstract protected int getCooldown();
	abstract protected AbstractShurikenEntity getKnifeEntity(Player playerIn, Level worldIn);
}