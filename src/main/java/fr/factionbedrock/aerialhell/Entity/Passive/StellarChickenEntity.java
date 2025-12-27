package fr.factionbedrock.aerialhell.Entity.Passive;

import fr.factionbedrock.aerialhell.Entity.AerialHellAnimalEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

public class StellarChickenEntity extends ChickenEntity
{
    private static final TrackedData<Integer> COLOR = DataTracker.<Integer>registerData(StellarChickenEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final Ingredient FOOD_ITEMS = Ingredient.ofItems(AerialHellItems.STELLAR_WHEAT_SEEDS, AerialHellItems.AERIAL_BERRY_SEEDS, AerialHellItems.VIBRANT_AERIAL_BERRY_SEEDS);

    public StellarChickenEntity(EntityType<? extends ChickenEntity> entityType, World world) {super(entityType, world);}

    @Override public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason mobSpawnType, @Nullable EntityData spawnGroupData)
    {
        this.setColor(getBlockPositionTint());
        return super.initialize(world, difficulty, mobSpawnType, spawnGroupData);
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

    private int getBlockPositionTint() {return this.getEntityWorld().getColor(this.getBlockPos(), Biome::getGrassColorAt);}

    @Override protected void initDataTracker(DataTracker.Builder builder)
    {
        super.initDataTracker(builder);
        builder.add(COLOR, 0);
    }

    @Override protected void writeCustomData(WriteView view)
    {
        super.writeCustomData(view);
        view.putInt("Color", this.getColor());
    }

    @Override protected void readCustomData(ReadView view)
    {
        super.readCustomData(view);
        this.setColor(view.getInt("Color", 0));
    }

    public int getColor() {return this.getDataTracker().get(COLOR);}
    public void setColor(int color) {this.getDataTracker().set(COLOR, color);}

    protected void initGoals()
    {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.5D));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 5.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    @Override public boolean isBreedingItem(ItemStack stack) {return FOOD_ITEMS.test(stack);}

    @Nullable @Override public ChickenEntity createChild(ServerWorld world, PassiveEntity mob)
    {
        StellarChickenEntity baby = AerialHellEntities.STELLAR_CHICKEN.create(this.getEntityWorld(), SpawnReason.BREEDING);
        baby.setColor(this.getColor());
        return baby;
    }

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return AerialHellAnimalEntity.createLivingAttributes()
                .add(EntityAttributes.MAX_HEALTH, 6.0D)
                .add(EntityAttributes.FOLLOW_RANGE, 16.0D)
                .add(EntityAttributes.TEMPT_RANGE, 10.0D)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.3);
    }

    @Nullable @Override public ItemEntity dropStack(ServerWorld serverWorld, ItemStack itemStack)
    {
        if (itemStack.getItem() == Items.EGG) {return super.dropStack(serverWorld, AerialHellItems.STELLAR_EGG.getDefaultStack());}
        else {return super.dropStack(serverWorld, itemStack);}
    }

    @Override public float getPathfindingFavor(BlockPos pos, WorldView world)
    {
        return world.getBlockState(pos.down()).isOf(AerialHellBlocks.STELLAR_GRASS_BLOCK) ? 10.0F : world.getPhototaxisFavor(pos) - 0.5F;
    }

    public static boolean canSpawn(EntityType<? extends ChickenEntity> type, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random randomIn)
    {
        return world.getBlockState(pos.down()).isOf(AerialHellBlocks.STELLAR_GRASS_BLOCK) && isLightLevelValidForNaturalSpawn(world, pos);
    }
}
