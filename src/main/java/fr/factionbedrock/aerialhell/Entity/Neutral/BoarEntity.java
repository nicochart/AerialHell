package fr.factionbedrock.aerialhell.Entity.Neutral;

import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import javax.annotation.Nullable;

public class BoarEntity extends Pig
{
    private static final Ingredient FOOD_ITEMS = Ingredient.of(AerialHellBlocksAndItems.AERIAL_BERRY.get(), AerialHellBlocksAndItems.VIBRANT_AERIAL_BERRY.get(), Items.CARROT, Items.POTATO, Items.BEETROOT);

    public BoarEntity(EntityType<? extends Pig> entityType, Level level) {super(entityType, level);}

    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return AbstractCaterpillarEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 10.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D);
    }

    @Override public boolean isSaddleable() {return false;}

    @Override @Nullable public Pig getBreedOffspring(ServerLevel level, AgeableMob mob) {return AerialHellEntities.STELLAR_BOAR.get().create(level, EntitySpawnReason.BREEDING);}
    @Override public boolean isFood(ItemStack stack) {return FOOD_ITEMS.test(stack);}

    public static boolean canSpawn(EntityType<? extends Pig> entityType, LevelAccessor worldIn, EntitySpawnReason spawnType, BlockPos pos, RandomSource random)
    {
        return worldIn.getBlockState(pos.below()).is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get()) && isBrightEnoughToSpawn(worldIn, pos);
    }
}
