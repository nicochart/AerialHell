package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class SlimePirateEntity extends AbstractSlimePirateEntity
{
    public SlimePirateEntity(EntityType<? extends SlimePirateEntity> type, World world) {super(type, world);}

    @Override protected void registerSpecificGoals()
    {
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override protected ItemStack getRandomHandItem(EquipmentSlot hand, Random rand)
    {
        return rand.nextInt(2) == 0 ? new ItemStack(AerialHellItems.RUBY_SWORD) : new ItemStack(AerialHellItems.RUBY_AXE);
    }
}
