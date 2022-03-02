package fr.factionbedrock.aerialhell.Entity;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public abstract class AerialHellAnimalEntity extends AnimalEntity
{
	protected AerialHellAnimalEntity(EntityType<? extends AnimalEntity> type, World worldIn)
	{
		super(type, worldIn);
	}
	
	@Override
	public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn)
	{
		return worldIn.getBlockState(pos.down()).getBlock() == AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get() ? 10.0F : worldIn.getLight(pos) - 0.5F;
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack)
	{
		return stack.getItem() == AerialHellBlocksAndItems.AERIAL_BERRY.get();
	}

	public static boolean canAerialHellAnimalSpawn(EntityType<? extends AerialHellAnimalEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random)
	{
		return worldIn.getBlockState(pos.down()).getBlock() == AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get() && worldIn.getLightSubtracted(pos, 0) > 8;
	}
}
