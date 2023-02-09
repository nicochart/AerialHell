package fr.factionbedrock.aerialhell.Block;

import java.util.Random;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.TileEntity.OscillatorTileEntity;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import net.minecraft.block.AbstractBlock;

public class OscillatorBlock extends AbstractFurnaceBlock
{
	public OscillatorBlock(AbstractBlock.Properties properties)
	{
		super(properties);
	}
	
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn)
	{
		return new OscillatorTileEntity();
	}
	
	@Override
	protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player)
	{
		if (!worldIn.isRemote)
		{ 
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof OscillatorTileEntity)
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
			double x = pos.getX() + 0.5;
			double y = pos.getY() + 1.01;
			double z = pos.getZ() + 0.5;
			
			world.addParticle(AerialHellParticleTypes.OSCILLATOR.get(), x + (rand.nextFloat() - 0.5F), y + (rand.nextFloat() * 6.0) / 16.0, z + (rand.nextFloat() - 0.5F), 0.0, 0.00, 0.0);
			
			if (rand.nextDouble() < 0.1)
			{
				world.playSound(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			}
		}
	}
}
