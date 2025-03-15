package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Entity.Projectile.ThrownStellarEgg;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EggItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class StellarEggItem extends EggItem
{
    public StellarEggItem(Item.Settings settings) {super(settings);}

    public ActionResult use(World world, PlayerEntity user, Hand hand) //copied from ThrownEgg, replacing ThrownEgg with ThrownStellarEgg
    {
        ItemStack itemstack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClient)
        {
            ThrownStellarEgg projectile = new ThrownStellarEgg(world, user);
            projectile.setItem(itemstack);
            projectile.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(projectile);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemstack.decrementUnlessCreative(1, user);

        return ActionResult.SUCCESS;
    }
}
