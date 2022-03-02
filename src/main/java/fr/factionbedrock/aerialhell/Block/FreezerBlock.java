package fr.factionbedrock.aerialhell.Block;

import java.util.Random;

import fr.factionbedrock.aerialhell.TileEntity.FreezerTileEntity;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import net.minecraft.block.AbstractBlock;

public class FreezerBlock extends AbstractFurnaceBlock
{
	public FreezerBlock(AbstractBlock.Properties properties)
	{
		super(properties);
	}
	
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn)
	{
		return new FreezerTileEntity();
	}
	
	@Override
	protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player)
	{
		if (!worldIn.isRemote)
		{ 
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof FreezerTileEntity)
			{
				player.openContainer((INamedContainerProvider) tileentity);
			}
		}
	}
	
	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		if (state.get(LIT))
		{
			float x = pos.getX() + 0.5F;
			float y = pos.getY() + 0.7F + (rand.nextFloat() * 6.0F) / 16.0F;
			float z = pos.getZ() + 0.5F;

			world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.0, 0.0);

			for (int i = 0; i < 4; ++i)
			{
				world.addParticle(AerialHellParticleTypes.SNOWFLAKE.get(), x + 1.5F * (rand.nextFloat() - 0.5F), y, z + 1.5F * (rand.nextFloat() - 0.5F), 0.4 * (rand.nextFloat() - 0.5F), 0.0F, 0.4 * (rand.nextFloat() - 0.5F));
			}

			world.playSound(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
		}
	}
}
