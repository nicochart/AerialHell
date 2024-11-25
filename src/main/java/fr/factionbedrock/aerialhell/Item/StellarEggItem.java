package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Entity.Projectile.ThrownStellarEgg;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StellarEggItem extends EggItem
{
    public StellarEggItem(Properties prop) {super(prop);}

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) //copied from ThrownEgg, replacing ThrownEgg with ThrownStellarEgg
    {
        ItemStack itemstack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!level.isClientSide)
        {
            ThrownStellarEgg projectile = new ThrownStellarEgg(level, player);
            projectile.setItem(itemstack);
            projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.spawnEntity(projectile);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {itemstack.shrink(1);}

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}
