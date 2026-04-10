package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;

public abstract class AerialHellAnimalEntity extends Animal
{
	protected AerialHellAnimalEntity(EntityType<? extends Animal> type, Level world)
	{
		super(type, world);
	}

	@Override protected void registerGoals()
	{
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.1, this::isFood, false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, LevelReader world)
	{
		return world.getBlockState(pos.below()).is(AerialHellBlocks.STELLAR_GRASS_BLOCK) ? 10.0F : world.getPathfindingCostFromLightLevels(pos) - 0.5F;
	}

	@Override public int getAmbientSoundInterval() {return 160;}

	@Override public boolean isFood(ItemStack stack) {return stack.getItem() == AerialHellItems.AERIAL_BERRY;}

	public static boolean canAerialHellAnimalSpawn(EntityType<? extends AerialHellAnimalEntity> type, ServerLevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource randomIn)
	{
		return world.getBlockState(pos.below()).is(AerialHellBlocks.STELLAR_GRASS_BLOCK) && isBrightEnoughToSpawn(world, pos);
	}
}
