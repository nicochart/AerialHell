package fr.factionbedrock.aerialhell.Item.Bucket;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class RubyMilkBucketItem extends Item
{
    public RubyMilkBucketItem(Item.Properties settings)
    {
        super(settings);
    }

    @Override public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user)
    {
        if (user instanceof ServerPlayer serverPlayerEntity)
        {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!world.isClientSide) {user.removeAllEffects();}

        if (user instanceof Player playerEntity) {return ItemUtils.createFilledResult(stack, playerEntity, new ItemStack(AerialHellItems.RUBY_BUCKET), false);}
        else
        {
            stack.consume(1, user);
            return stack;
        }
    }

    @Override public int getUseDuration(ItemStack stack, LivingEntity livingEntity) {return 32;}

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {return UseAnim.DRINK;}

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {return ItemUtils.startUsingInstantly(world, user, hand);}
}