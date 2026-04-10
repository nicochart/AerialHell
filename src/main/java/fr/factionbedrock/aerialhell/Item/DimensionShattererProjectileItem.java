package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Entity.Projectile.DimensionShattererProjectileEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DimensionShattererProjectileItem extends EggItem
{
    public DimensionShattererProjectileItem(Item.Properties settings) {super(settings);}

    public InteractionResult use(Level world, Player user, InteractionHand hand) //copied from ThrownEgg, replacing ThrownEgg with DimensionShattererProjectile
    {
        ItemStack itemstack = user.getItemInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide())
        {
            DimensionShattererProjectileEntity projectile = new DimensionShattererProjectileEntity(world, user);
            projectile.setPosRaw(user.getX(), user.getEyeY() - 0.1F, user.getZ());
            projectile.shootStraightForward(user, user.getXRot(), user.getYRot(), 0.0F, 0.3F, 0.0F);
            world.addFreshEntity(projectile);
        }

        user.getCooldowns().addCooldown(itemstack, 40);
        user.awardStat(Stats.ITEM_USED.get(this));
        itemstack.consume(1, user);

        return InteractionResult.SUCCESS;
    }
}
