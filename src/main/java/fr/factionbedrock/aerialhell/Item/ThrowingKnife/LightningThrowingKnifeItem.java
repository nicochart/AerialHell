package fr.factionbedrock.aerialhell.Item.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife.LightningThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class LightningThrowingKnifeItem extends AbstractThrowingKnifeItem
{
	public LightningThrowingKnifeItem(ItemGroup group)
	{
		super(new Item.Properties().rarity(Rarity.RARE), group);
	}
	
	public LightningThrowingKnifeItem() //default group
	{
		super(new Item.Properties().rarity(Rarity.RARE));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand hand)
	{
		ItemStack heldItem = playerIn.getHeldItem(hand);
		
		if (!playerIn.isCreative() && EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, heldItem) == 0)
		{
			heldItem.shrink(1);
		}
		
		worldIn.playSound(null, playerIn.getPosition(), AerialHellSoundEvents.ENTITY_THROWING_KNIFE_SHOOT.get(), SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 0.8F));
		
		if (!worldIn.isRemote)
		{
			LightningThrowingKnifeEntity lightningThrowingKnife = new LightningThrowingKnifeEntity(playerIn, worldIn);
			lightningThrowingKnife.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.8F, 1.0F);
			worldIn.addEntity(lightningThrowingKnife);
		}
		playerIn.getCooldownTracker().setCooldown(this, 8);
		return ActionResult.resultSuccess(heldItem);
	}
}