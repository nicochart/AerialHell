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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import javax.annotation.Nullable;

public class StellarChickenEntity extends Chicken
{
    private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.<Integer>defineId(StellarChickenEntity.class, EntityDataSerializers.INT);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(AerialHellItems.STELLAR_WHEAT_SEEDS.get(), AerialHellItems.AERIAL_BERRY_SEEDS.get(), AerialHellItems.VIBRANT_AERIAL_BERRY_SEEDS.get());

    public StellarChickenEntity(EntityType<? extends Chicken> entityType, Level level) {super(entityType, level);}

    @Override public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason mobSpawnType, @Nullable SpawnGroupData spawnGroupData)
    {
        this.setColor(getBlockPositionTint());
        return super.finalizeSpawn(level, difficulty, mobSpawnType, spawnGroupData);
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

    private int getBlockPositionTint()
    {
        return this.level().getBlockTint(this.blockPosition(), Biome::getGrassColor);
    }

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(COLOR, 0);
    }

    @Override public void addAdditionalSaveData(ValueOutput valueOutput)
    {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.putInt("Color", this.getColor());
    }

    @Override public void readAdditionalSaveData(ValueInput valueInput)
    {
        super.readAdditionalSaveData(valueInput);
        this.setColor(valueInput.getInt("Color").get());
    }

    public int getColor() {return this.entityData.get(COLOR);}
    public void setColor(int color) {this.entityData.set(COLOR, color);}

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
        StellarChickenEntity baby = AerialHellEntities.STELLAR_CHICKEN.get().create(this.level(), EntitySpawnReason.BREEDING);
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

    @Nullable @Override public ItemEntity spawnAtLocation(ServerLevel serverLevel, ItemStack itemStack)
    {
        if (itemStack.getItem() == Items.EGG) {return super.spawnAtLocation(serverLevel, AerialHellItems.STELLAR_EGG.toStack());}
        else {return super.spawnAtLocation(serverLevel, itemStack);}
    }

    @Override public float getWalkTargetValue(BlockPos pos, LevelReader worldIn)
    {
        return worldIn.getBlockState(pos.below()).is(AerialHellBlocks.STELLAR_GRASS_BLOCK.get()) ? 10.0F : worldIn.getPathfindingCostFromLightLevels(pos) - 0.5F;
    }

    public static boolean canSpawn(EntityType<? extends Chicken> entityType, LevelAccessor worldIn, EntitySpawnReason spawnType, BlockPos pos, RandomSource random)
    {
        return worldIn.getBlockState(pos.below()).is(AerialHellBlocks.STELLAR_GRASS_BLOCK.get()) && isBrightEnoughToSpawn(worldIn, pos);
    }
}
