package fr.factionbedrock.aerialhell.Entity.Passive;

import fr.factionbedrock.aerialhell.Entity.AerialHellAnimalEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.chicken.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

public class StellarChickenEntity extends Chicken
{
    private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.<Integer>defineId(StellarChickenEntity.class, EntityDataSerializers.INT);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(AerialHellItems.STELLAR_WHEAT_SEEDS, AerialHellItems.AERIAL_BERRY_SEEDS, AerialHellItems.VIBRANT_AERIAL_BERRY_SEEDS);

    public StellarChickenEntity(EntityType<? extends Chicken> entityType, Level world) {super(entityType, world);}

    @Override public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason mobSpawnType, @Nullable SpawnGroupData spawnGroupData)
    {
        this.setColor(getBlockPositionTint());
        return super.finalizeSpawn(world, difficulty, mobSpawnType, spawnGroupData);
    }

    @Override public void tick()
    {
        if (this.getColor() == 0)
        {
            int color = getBlockPositionTint();
            this.setColor(color != 0 ? color : 1);
        }
        super.tick();
    }

    private int getBlockPositionTint() {return this.level().getBlockTint(this.blockPosition(), Biome::getGrassColor);}

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(COLOR, 0);
    }

    @Override protected void addAdditionalSaveData(ValueOutput view)
    {
        super.addAdditionalSaveData(view);
        view.putInt("Color", this.getColor());
    }

    @Override protected void readAdditionalSaveData(ValueInput view)
    {
        super.readAdditionalSaveData(view);
        this.setColor(view.getIntOr("Color", 0));
    }

    public int getColor() {return this.getEntityData().get(COLOR);}
    public void setColor(int color) {this.getEntityData().set(COLOR, color);}

    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 5.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override public boolean isFood(ItemStack stack) {return FOOD_ITEMS.test(stack);}

    @Nullable @Override public Chicken getBreedOffspring(ServerLevel world, AgeableMob mob)
    {
        StellarChickenEntity baby = AerialHellEntities.STELLAR_CHICKEN.create(this.level(), EntitySpawnReason.BREEDING);
        baby.setColor(this.getColor());
        return baby;
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return AerialHellAnimalEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.TEMPT_RANGE, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3);
    }

    @Nullable @Override public ItemEntity spawnAtLocation(ServerLevel serverWorld, ItemStack itemStack)
    {
        if (itemStack.getItem() == Items.EGG) {return super.spawnAtLocation(serverWorld, AerialHellItems.STELLAR_EGG.getDefaultInstance());}
        else {return super.spawnAtLocation(serverWorld, itemStack);}
    }

    @Override public float getWalkTargetValue(BlockPos pos, LevelReader world)
    {
        return world.getBlockState(pos.below()).is(AerialHellBlocks.STELLAR_GRASS_BLOCK) ? 10.0F : world.getPathfindingCostFromLightLevels(pos) - 0.5F;
    }

    public static boolean canSpawn(EntityType<? extends Chicken> type, ServerLevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource randomIn)
    {
        return world.getBlockState(pos.below()).is(AerialHellBlocks.STELLAR_GRASS_BLOCK) && isBrightEnoughToSpawn(world, pos);
    }
}
