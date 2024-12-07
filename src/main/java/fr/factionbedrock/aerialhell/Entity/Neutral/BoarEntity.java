package fr.factionbedrock.aerialhell.Entity.Neutral;

import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BoarEntity extends PigEntity
{
    private static final Ingredient FOOD_ITEMS = Ingredient.ofItems(AerialHellItems.AERIAL_BERRY, AerialHellItems.VIBRANT_AERIAL_BERRY, Items.CARROT, Items.POTATO, Items.BEETROOT);

    public BoarEntity(EntityType<? extends PigEntity> entityType, World world) {super(entityType, world);}

    protected void initGoals()
    {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25D));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.add(3, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, (new RevengeGoal(this)).setGroupRevenge());
    }

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return AbstractCaterpillarEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D);
    }

    @Override public boolean canBeSaddled() {return false;}

    @Override @Nullable public PigEntity createChild(ServerWorld serverWorld, PassiveEntity mob) {return AerialHellEntities.STELLAR_BOAR.create(serverWorld);}
    @Override public boolean isBreedingItem(ItemStack stack) {return FOOD_ITEMS.test(stack);}

    public static boolean canSpawn(EntityType<? extends PigEntity> type, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random)
    {
        return world.getBlockState(pos.down()).isOf(AerialHellBlocks.STELLAR_GRASS_BLOCK) && isLightLevelValidForNaturalSpawn(world, pos);
    }
}
