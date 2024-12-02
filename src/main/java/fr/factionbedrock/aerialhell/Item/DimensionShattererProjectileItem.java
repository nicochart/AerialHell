package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Entity.Projectile.DimensionShattererProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EggItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DimensionShattererProjectileItem extends EggItem
{
    public DimensionShattererProjectileItem(Item.Settings settings) {super(settings);}

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) //copied from ThrownEgg, replacing ThrownEgg with DimensionShattererProjectile
    {
        ItemStack itemstack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClient)
        {
            DimensionShattererProjectileEntity projectile = new DimensionShattererProjectileEntity(world, user);
            projectile.setPos(user.getX(), user.getEyeY() - 0.1F, user.getZ());
            projectile.shootStraightForward(user, user.getPitch(), user.getYaw(), 0.0F, 0.3F, 0.0F);
            world.spawnEntity(projectile);
        }

        user.getItemCooldownManager().set(this, 40);
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemstack.decrementUnlessCreative(1, user);

        return TypedActionResult.success(itemstack, world.isClient());
    }
}
