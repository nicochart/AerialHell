package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MummyEntity extends AbstractHumanoidMonster
{
    public MummyEntity(EntityType<? extends MummyEntity> type, Level world) {super(type, world, 0.5F, 0.1F);}

    @Override public void remove(RemovalReason removalReason)
    {
        //todo
        super.remove(removalReason);
    }

    @Override protected void registerSpecificGoals()
    {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override public EntityType<? extends MummyEntity> getType()
    {
        return (EntityType<? extends MummyEntity>) super.getType();
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return AbstractHumanoidMonster.registerAttributes(22.0D, 3.0D, 0.22D, 35.0D);
    }
    
    @Override protected SoundEvent getAmbientSound(){return SoundEvents.HUSK_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return SoundEvents.HUSK_HURT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.HUSK_DEATH;}

    @Nullable @Override protected ItemStack getRandomHandItem(EquipmentSlot hand, RandomSource rand)
    {
        return rand.nextInt(2) == 0 ? new ItemStack(AerialHellItems.STELLAR_STONE_SWORD.get()) : new ItemStack(AerialHellItems.STELLAR_STONE_AXE.get());
    }
}
