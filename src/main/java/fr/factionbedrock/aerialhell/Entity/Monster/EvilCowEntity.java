package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.PounceAtTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EvilCowEntity extends AerialHellHostileEntity
{
    public EvilCowEntity(EntityType<? extends EvilCowEntity> type, World world) {super(type, world);}

    public EvilCowEntity(World world) {this(AerialHellEntities.EVIL_COW, world);}

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 20.0D)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.20000000298023224D)
        		.add(EntityAttributes.ATTACK_DAMAGE, 3.0D);
    }
    
    @Override
    protected void initGoals()
    {
    	super.initGoals();
    	this.goalSelector.add(3, new PounceAtTargetGoal(this, 0.4F));
    }

    @Override public ActionResult interactMob(PlayerEntity player, Hand hand)
    {
        ItemStack itemstack = player.getStackInHand(hand);
        if (itemstack.isOf(Items.BUCKET) || itemstack.isOf(AerialHellItems.RUBY_BUCKET) && !this.isBaby())
        {
            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
            ItemStack filledBucket;
            if (itemstack.isOf(Items.BUCKET))
            {
                filledBucket = ItemUsage.exchangeStack(itemstack, player, Items.MILK_BUCKET.getDefaultStack());
            }
            else //RUBY_BUCKET
            {
                filledBucket = ItemUsage.exchangeStack(itemstack, player, AerialHellItems.RUBY_BUCKET.getDefaultStack());
            }
            player.setStackInHand(hand, filledBucket);
            return ActionResult.SUCCESS;
        }
        else {return super.interactMob(player, hand);}
    }

    @Nullable @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_EVIL_COW_HURT;}
    @Nullable @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_EVIL_COW_DEATH;}
    @Nullable @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_EVIL_COW_AMBIENT;}
}