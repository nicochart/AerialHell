package fr.factionbedrock.aerialhell.Entity;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

public abstract class AerialHellAnimalEntity extends Animal
{
	protected AerialHellAnimalEntity(EntityType<? extends Animal> type, Level worldIn)
	{
		super(type, worldIn);
	}
	
	@Override
	public float getWalkTargetValue(BlockPos pos, LevelReader worldIn)
	{
		return worldIn.getBlockState(pos.below()).is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get()) ? 10.0F : worldIn.getBrightness(pos) - 0.5F;
	}

	@Override public int getAmbientSoundInterval() {return 160;}

	@Override
	public boolean isFood(ItemStack stack)
	{
		return stack.getItem() == AerialHellBlocksAndItems.AERIAL_BERRY.get();
	}

	public static boolean canAerialHellAnimalSpawn(EntityType<? extends AerialHellAnimalEntity> entityType, LevelAccessor worldIn, MobSpawnType spawnType, BlockPos pos, Random random)
	{
		return worldIn.getBlockState(pos.below()).is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get()) && isBrightEnoughToSpawn(worldIn, pos);
	}
}
