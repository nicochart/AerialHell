package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Entity.AerialHellHostileEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;

public class EvilCowEntity extends AerialHellHostileEntity
{
    public EvilCowEntity(EntityType<? extends EvilCowEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    public EvilCowEntity(World worldIn)
    {
        this(AerialHellEntities.EVIL_COW.get(), worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.20000000298023224D)
        		.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D);
    }
    
    @Override
    protected void registerGoals()
    {
    	super.registerGoals();
    	this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
    }
    
    @Override
    public ActionResultType func_230254_b_(PlayerEntity player, Hand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        if (itemstack.getItem() == Items.BUCKET)
        {
            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
            ItemStack itemstack1 = DrinkHelper.fill(itemstack, player, Items.MILK_BUCKET.getDefaultInstance());
            player.setHeldItem(hand, itemstack1);
            return ActionResultType.func_233537_a_(this.world.isRemote);
        }
        else
        {
            return super.func_230254_b_(player, hand);
        }
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