package fr.factionbedrock.aerialhell.Item.Bucket;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;

public class RubyMilkBucketItem extends Item
{
    public RubyMilkBucketItem(Properties properties)
    {
        super(properties);
    }

    @Override public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity)
    {
        if (entity instanceof ServerPlayer serverplayer)
        {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, stack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        stack.consume(1, entity);
        if (!level.isClientSide) {entity.removeAllEffects();}

        return stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack;
    }

    @Override public int getUseDuration(ItemStack stack, LivingEntity livingEntity) {return 32;}

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {return ItemUseAnimation.DRINK;}

    @Override
    public InteractionResult use(Level worldIn, Player playerIn, InteractionHand handIn)
    {
        return ItemUtils.startUsingInstantly(worldIn, playerIn, handIn);
    }
}