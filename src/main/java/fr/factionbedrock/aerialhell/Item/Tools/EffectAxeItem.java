package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class EffectAxeItem extends AerialHellAxeItem
{
	public EffectAxeItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, float movementSpeedIn,float maxHealthIn, Properties builderIn)
	{
		super(tier, attackDamageIn, attackSpeedIn, movementSpeedIn, maxHealthIn, builderIn);
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
		ItemStack heldItem = playerIn.getHeldItem(handIn);
		Random rand = new Random();
		
		if (this == AerialHellBlocksAndItems.BERSERK_AXE.get())
		{
			for (int i=0 ; i<20; i++)
			{
				worldIn.addParticle(ParticleTypes.SMOKE, playerIn.getPosX() + 4*(rand.nextFloat() - 0.5F), playerIn.getPosY() + 4*rand.nextFloat(), playerIn.getPosZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
			}
			playerIn.playSound(SoundEvents.ENTITY_RAVAGER_ROAR, 1.0F, 0.5F + rand.nextFloat());
			if (worldIn.isRemote)
			{
				Vector3d forward = playerIn.getForward().mul(2,1.5,2);
				if (forward.getY() < 1) {forward = new Vector3d(forward.getX(), 1, forward.getZ());}
				playerIn.setMotion(playerIn.getMotion().add(forward));
			}
			else //!worldIn.isRemote
			{
				playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 200, 0));
			}
			playerIn.getCooldownTracker().setCooldown(this, 500);
			heldItem.damageItem(1, playerIn, (player) -> {player.sendBreakAnimation(playerIn.getActiveHand());});
	        return ActionResult.resultConsume(heldItem);
		}
		else
		{
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
    }
}
