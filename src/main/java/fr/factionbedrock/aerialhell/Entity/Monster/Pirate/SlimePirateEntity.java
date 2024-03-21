package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SlimePirateEntity extends AbstractSlimePirateEntity
{
    public SlimePirateEntity(EntityType<? extends SlimePirateEntity> type, Level world) {super(type, world);}

    @Override protected void registerSpecificGoals()
    {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override protected ItemStack getRandomWeapon(RandomSource rand)
    {
        return rand.nextInt(2) == 0 ? new ItemStack(AerialHellBlocksAndItems.RUBY_SWORD.get()) : new ItemStack(AerialHellBlocksAndItems.RUBY_AXE.get());
    }
}
