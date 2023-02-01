package fr.factionbedrock.aerialhell.Block.DungeonCores;

import java.util.Random;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DungeonCoreBlock extends Block
{	
	int coreProtectRange; //odd number is better
	
	public DungeonCoreBlock(Properties properties, int coreRangeIn)
	{
		super(properties);
		this.coreProtectRange = coreRangeIn;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
	{
		//setAreaProtected(worldIn, pos, true);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player)
	{
		super.onBlockHarvested(worldIn, pos, state, player);
		setAreaProtected(worldIn, pos, false);
	}
	
	public void setAreaProtected(World worldIn, BlockPos originPos, boolean protect)
	{		
		if (isMudCore(this))
		{
			setAreaProtected(worldIn, originPos, AerialHellTags.Blocks.MUD_DUNGEON, protect);
		}
		else if (isLunaticCore(this))
		{
			setAreaProtected(worldIn, originPos, AerialHellTags.Blocks.LUNATIC_DUNGEON, protect);
		}
		else if (isShadowCatacombsCore(this))
		{
			setAreaProtected(worldIn, originPos, AerialHellTags.Blocks.SHADOW_CATACOMBS_DUNGEON, protect);
		}
		else if (isGoldenNetherCore(this))
		{
			setAreaProtected(worldIn, originPos, AerialHellTags.Blocks.GOLDEN_NETHER_DUNGEON, protect);
		}
		else if (isVoluciteCore(this))
		{
			setAreaProtected(worldIn, originPos, AerialHellTags.Blocks.VOLUCITE_DUNGEON, protect);
		}
	}
	
	private void setAreaProtected(World worldIn, BlockPos originPos, ITag<Block> tag, boolean protect)
	{
		for(int x = originPos.getX() - (coreProtectRange - 1)/2; x <= originPos.getX() + (coreProtectRange - 1)/2; x++)
        {
			for(int y = originPos.getY() - (coreProtectRange - 1)/2; y <= originPos.getY() + (coreProtectRange - 1)/2; y++)
	        {
				for(int z = originPos.getZ() - (coreProtectRange - 1)/2; z <= originPos.getZ() + (coreProtectRange - 1)/2; z++)
		        {
					BlockPos newPos = new BlockPos(x, y, z);
					Block block = worldIn.getBlockState(newPos).getBlock();
					if (block.isIn(tag))
					{
						setBlockProtected(worldIn, newPos, protect);
					}
		        }
	        }
        }
	}
	
	private void setBlockProtected(World worldIn, BlockPos pos, boolean protect)
	{
		BlockState old_blockstate = worldIn.getBlockState(pos);
		Block block = old_blockstate.getBlock();
		if (block instanceof CoreProtectedBlock)
		{
			worldIn.setBlockState(pos, old_blockstate.with(CoreProtectedBlock.CORE_PROTECTED, protect));
		}
		else if (block instanceof CoreProtectedRotatedPillarBlock)
		{
			worldIn.setBlockState(pos, old_blockstate.with(CoreProtectedRotatedPillarBlock.CORE_PROTECTED, protect));
		}
		else if (block instanceof CoreProtectedSlabBlock)
		{
			worldIn.setBlockState(pos, old_blockstate.with(CoreProtectedSlabBlock.CORE_PROTECTED, protect));
		}
		else if (block instanceof CoreProtectedStairsBlock)
		{
			worldIn.setBlockState(pos, old_blockstate.with(CoreProtectedStairsBlock.CORE_PROTECTED, protect));
		}
		else if (block instanceof CoreProtectedChestBlock)
		{
			worldIn.setBlockState(pos, old_blockstate.with(CoreProtectedChestBlock.CORE_PROTECTED, protect));
		}
		else if (block instanceof CoreProtectedTrappedBlock)
		{
			worldIn.setBlockState(pos, old_blockstate.with(CoreProtectedTrappedBlock.CORE_PROTECTED, protect));
		}
		//else if (block instanceof CoreProtectedWallBlock)
		//{
		//	worldIn.setBlockState(pos, old_blockstate.with(CoreProtectedWallBlock.CORE_PROTECTED, protect));
		//}
	}
	
	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		float x = pos.getX() + 0.5F;
		float y = pos.getY() + 0.5F;
		float z = pos.getZ() + 0.5F;
		BasicParticleType particle = ParticleTypes.BARRIER;
		
		if (isMudCore(this))
		{
			particle = ParticleTypes.SMOKE;
		}
		else if (isLunaticCore(this))
		{
			particle = AerialHellParticleTypes.COPPER_PINE_LEAVES.get();
		}
		else if (isShadowCatacombsCore(this))
		{
			particle = AerialHellParticleTypes.SHADOW_PARTICLE.get();
		}
		else if (isGoldenNetherCore(this))
		{
			particle = AerialHellParticleTypes.GOD_FLAME.get();
		}
		else if (isVoluciteCore(this))
		{
			particle = ParticleTypes.ENCHANT;
		}
		
		world.addParticle(particle, x + 1.5F * (rand.nextFloat() - 0.5F), y + 1.5F * (rand.nextFloat() - 0.5F), z + 1.5F * (rand.nextFloat() - 0.5F), 0.4 * (rand.nextFloat() - 0.5F), 0.4 * (rand.nextFloat() - 0.5F), 0.4 * (rand.nextFloat() - 0.5F));
	}
	
	private boolean isMudCore(DungeonCoreBlock core) {return (core == AerialHellBlocksAndItems.MUD_DUNGEON_CORE.get());}
	private boolean isLunaticCore(DungeonCoreBlock core) {return (core == AerialHellBlocksAndItems.LUNATIC_DUNGEON_CORE.get());}
	private boolean isShadowCatacombsCore(DungeonCoreBlock core) {return (core == AerialHellBlocksAndItems.SHADOW_CATACOMBS_DUNGEON_CORE.get());}
	private boolean isGoldenNetherCore(DungeonCoreBlock core) {return (core == AerialHellBlocksAndItems.GOLDEN_NETHER_DUNGEON_CORE.get());}
	private boolean isVoluciteCore(DungeonCoreBlock core) {return (core == AerialHellBlocksAndItems.VOLUCITE_DUNGEON_CORE.get());}
}
