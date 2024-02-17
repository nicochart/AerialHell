package fr.factionbedrock.aerialhell.Entity.Passive;

import fr.factionbedrock.aerialhell.Entity.AerialHellAnimalEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
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

import javax.annotation.Nullable;

public class StellarChickenEntity extends Chicken
{
    private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.<Integer>defineId(StellarChickenEntity.class, EntityDataSerializers.INT);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(AerialHellBlocksAndItems.AERIAL_BERRY_SEEDS.get(), AerialHellBlocksAndItems.VIBRANT_AERIAL_BERRY_SEEDS.get());

    public StellarChickenEntity(EntityType<? extends Chicken> entityType, Level level) {super(entityType, level);}

    @Override public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag tag)
    {
        this.setColor(BiomeColors.getAverageGrassColor(level, this.blockPosition()));
        return super.finalizeSpawn(level, difficulty, mobSpawnType, spawnGroupData, tag);
    }

    @Override public void tick()
    {
        if (this.getColor() == 0)
        {
            int color = BiomeColors.getAverageGrassColor(this.level(), this.blockPosition());
            this.setColor(color != 0 ? color : 1);
        }
        super.tick();
    }

    @Override protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(COLOR, 0);
    }

    @Override public void addAdditionalSaveData(CompoundTag compound)
    {
        super.addAdditionalSaveData(compound);
        compound.putInt("Color", this.getColor());
    }

    @Override public void readAdditionalSaveData(CompoundTag compound)
    {
        super.readAdditionalSaveData(compound);
        this.setColor(compound.getInt("Color"));
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

    @Nullable @Override public Chicken getBreedOffspring(ServerLevel world, AgeableMob mob) {return AerialHellEntities.STELLAR_CHICKEN.get().create(this.level());}

    public static AttributeSupplier.Builder registerAttributes()
    {
        return AerialHellAnimalEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3);
    }

    @Nullable @Override public ItemEntity spawnAtLocation(ItemLike item)
    {
        if (item == Items.EGG) {return super.spawnAtLocation(AerialHellBlocksAndItems.STELLAR_EGG.get());}
        else {return super.spawnAtLocation(item);}
    }

    @Override public float getWalkTargetValue(BlockPos pos, LevelReader worldIn)
    {
        return worldIn.getBlockState(pos.below()).is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get()) ? 10.0F : worldIn.getPathfindingCostFromLightLevels(pos) - 0.5F;
    }

    public static boolean canSpawn(EntityType<? extends Chicken> entityType, LevelAccessor worldIn, MobSpawnType spawnType, BlockPos pos, RandomSource random)
    {
        return worldIn.getBlockState(pos.below()).is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get()) && isBrightEnoughToSpawn(worldIn, pos);
    }
}
