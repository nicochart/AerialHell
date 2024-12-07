package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public abstract class AerialHellAnimalEntity extends AnimalEntity
{
	protected AerialHellAnimalEntity(EntityType<? extends AnimalEntity> type, World world)
	{
		super(type, world);
	}

	@Override protected void initGoals()
	{
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25));
		this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));
		this.goalSelector.add(3, new TemptGoal(this, 1.1, stack -> stack.isOf(AerialHellItems.AERIAL_BERRY), false));
		this.goalSelector.add(4, new FollowParentGoal(this, 1.1));
		this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0));
		this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.add(8, new LookAroundGoal(this));
	}

	@Override
	public float getPathfindingFavor(BlockPos pos, WorldView world)
	{
		return world.getBlockState(pos.down()).isOf(AerialHellBlocks.STELLAR_GRASS_BLOCK) ? 10.0F : world.getPhototaxisFavor(pos) - 0.5F;
	}

	@Override public int getMinAmbientSoundDelay() {return 160;}

	@Override public boolean isBreedingItem(ItemStack stack) {return stack.getItem() == AerialHellItems.AERIAL_BERRY;}

	public static boolean canAerialHellAnimalSpawn(EntityType<? extends AerialHellAnimalEntity> type, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random randomIn)
	{
		return world.getBlockState(pos.down()).isOf(AerialHellBlocks.STELLAR_GRASS_BLOCK) && isLightLevelValidForNaturalSpawn(world, pos);
	}
}
