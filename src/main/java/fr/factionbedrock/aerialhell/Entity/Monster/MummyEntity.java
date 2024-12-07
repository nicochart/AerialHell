package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MummyEntity extends AbstractHumanoidMonster
{
    public MummyEntity(EntityType<? extends MummyEntity> type, World world) {super(type, world, 0.5F, 0.1F);}

    @Override public void remove(RemovalReason removalReason)
    {
        //todo
        super.remove(removalReason);
    }

    @Override protected void registerSpecificGoals()
    {
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override public EntityType<? extends MummyEntity> getType()
    {
        return (EntityType<? extends MummyEntity>) super.getType();
    }

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return AbstractHumanoidMonster.registerAttributes(22.0D, 3.0D, 0.22D, 35.0D);
    }
    
    @Override protected SoundEvent getAmbientSound(){return SoundEvents.ENTITY_HUSK_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return SoundEvents.ENTITY_HUSK_HURT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.ENTITY_HUSK_DEATH;}

    @Nullable @Override protected ItemStack getRandomHandItem(EquipmentSlot hand, Random rand)
    {
        return rand.nextInt(2) == 0 ? new ItemStack(AerialHellItems.STELLAR_STONE_SWORD) : new ItemStack(AerialHellItems.STELLAR_STONE_AXE);
    }
}
