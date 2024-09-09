package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Entity.Projectile.DimensionShattererProjectileEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DimensionShattererProjectileItem extends EggItem
{
    public DimensionShattererProjectileItem(Properties prop) {super(prop);}

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) //copied from ThrownEgg, replacing ThrownEgg with DimensionShattererProjectile
    {
        ItemStack itemstack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!level.isClientSide)
        {
            DimensionShattererProjectileEntity projectile = new DimensionShattererProjectileEntity(level, player);
            projectile.setPos(player.getX(), player.getEyeY() - 0.1F, player.getZ());
            projectile.shootStraightForwars(player, player.getXRot(), player.getYRot(), 0.0F, 0.3F, 0.0F);
            level.addFreshEntity(projectile);
        }

        player.getCooldowns().addCooldown(this, 40);
        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {itemstack.shrink(1);}

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}
