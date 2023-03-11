package fr.factionbedrock.aerialhell.Item.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItemGroups;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public abstract class AbstractShurikenItem extends Item
{
	public AbstractShurikenItem(Properties properties, ItemGroup group)
	{
		super(properties.maxStackSize(16).group(group));
	}
	
	public AbstractShurikenItem(Properties properties) //default group
	{
		super(properties.maxStackSize(16).group(AerialHellItemGroups.AERIAL_HELL_COMBAT));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand hand)
	{
		ItemStack heldItem = playerIn.getHeldItem(hand);
		
		if (!playerIn.isCreative() && EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, heldItem) == 0)
		{
			heldItem.shrink(1);
		}
		
		worldIn.playSound(null, playerIn.getPosition(), AerialHellSoundEvents.ENTITY_SHURIKEN_SHOOT.get(), SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 0.8F));
		
		if (!worldIn.isRemote)
		{
			AbstractShurikenEntity shuriken = this.getKnifeEntity(playerIn, worldIn);
			shuriken.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, this.getVelocity(), this.getInaccuracy());
			//shuriken.setShooter(playerIn); shooter is not detected
			worldIn.addEntity(shuriken);
		}
		playerIn.getCooldownTracker().setCooldown(this, this.getCooldown());
		return ActionResult.resultSuccess(heldItem);
	}

	abstract protected float getVelocity();
	abstract protected float getInaccuracy();
	abstract protected int getCooldown();
	abstract protected AbstractShurikenEntity getKnifeEntity(PlayerEntity playerIn, World worldIn);
}