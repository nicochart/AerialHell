package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Entity.AerialHellHostileEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;

public class EvilCowEntity extends AerialHellHostileEntity
{
    public EvilCowEntity(EntityType<? extends EvilCowEntity> type, Level worldIn)
    {
        super(type, worldIn);
    }

    public EvilCowEntity(Level worldIn)
    {
        this(AerialHellEntities.EVIL_COW.get(), worldIn);
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.20000000298023224D)
        		.add(Attributes.ATTACK_DAMAGE, 3.0D);
    }
    
    @Override
    protected void registerGoals()
    {
    	super.registerGoals();
    	this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
    }

    //TODO : remove this ?
    @Override public InteractionResult mobInteract(Player player, InteractionHand hand)
    {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.is(Items.BUCKET) && !this.isBaby())
        {
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack filledFucket = ItemUtils.createFilledResult(itemstack, player, Items.MILK_BUCKET.getDefaultInstance());
            player.setItemInHand(hand, filledFucket);
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }
        else {return super.mobInteract(player, hand);}
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return AerialHellSoundEvents.ENTITY_EVIL_COW_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound()
    {
        return AerialHellSoundEvents.ENTITY_EVIL_COW_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound()
    {
        return AerialHellSoundEvents.ENTITY_EVIL_COW_AMBIENT.get();
    }
}