package fr.factionbedrock.aerialhell.Item.Bucket;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;

public class RubyMilkBucketItem extends Item
{
    public RubyMilkBucketItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving)
    {
        if (!worldIn.isClientSide()) entityLiving.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
        if (entityLiving instanceof Player && !((Player)entityLiving).getAbilities().instabuild)
        {
            stack.shrink(1);
        }
        return stack.isEmpty() ? new ItemStack(AerialHellBlocksAndItems.RUBY_BUCKET.get()) : stack;
    }

    @Override
    public int getUseDuration(ItemStack stack)
    {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {return UseAnim.DRINK;}

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn)
    {
        return ItemUtils.startUsingInstantly(worldIn, playerIn, handIn);
    }
}