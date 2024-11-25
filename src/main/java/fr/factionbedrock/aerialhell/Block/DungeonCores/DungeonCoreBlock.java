package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class DungeonCoreBlock extends Block
{	
	int coreProtectRange; //odd number is better
	
	public DungeonCoreBlock(AbstractBlock.Settings settings, int coreRangeIn)
	{
		super(settings);
		this.coreProtectRange = coreRangeIn;
	}

	public void onPlaced(World world, BlockPos pos, BlockState state, @org.jetbrains.annotations.Nullable LivingEntity placer, ItemStack itemStack)
	{
		//setAreaProtected(worldIn, pos, true);
	}
	
	@Override
	public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player)
	{
		setAreaProtected(world, pos, false);
		return super.onBreak(world, pos, state, player);
	}
	
	public void setAreaProtected(World world, BlockPos originPos, boolean protect)
	{		
		if (isMudCore(this))
		{
			setAreaProtected(world, originPos, AerialHellTags.Blocks.MUD_DUNGEON, protect);
		}
		else if (isLunaticCore(this))
		{
			setAreaProtected(world, originPos, AerialHellTags.Blocks.LUNATIC_DUNGEON, protect);
		}
		else if (isShadowCatacombsCore(this))
		{
			setAreaProtected(world, originPos, AerialHellTags.Blocks.SHADOW_CATACOMBS_DUNGEON, protect);
		}
		else if (isGoldenNetherCore(this))
		{
			setAreaProtected(world, originPos, AerialHellTags.Blocks.GOLDEN_NETHER_DUNGEON, protect);
		}
		else if (isVoluciteCore(this))
		{
			setAreaProtected(world, originPos, AerialHellTags.Blocks.VOLUCITE_DUNGEON, protect);
		}
	}
	
	private void setAreaProtected(World world, BlockPos originPos, TagKey<Block> tag, boolean protect)
	{
		for(int x = originPos.getX() - (coreProtectRange - 1)/2; x <= originPos.getX() + (coreProtectRange - 1)/2; x++)
        {
			for(int y = originPos.getY() - (coreProtectRange - 1)/2; y <= originPos.getY() + (coreProtectRange - 1)/2; y++)
	        {
				for(int z = originPos.getZ() - (coreProtectRange - 1)/2; z <= originPos.getZ() + (coreProtectRange - 1)/2; z++)
		        {
					BlockPos newPos = new BlockPos(x, y, z);
					BlockState blockstate = world.getBlockState(newPos);
					if (blockstate.isIn(tag))
					{
						setBlockProtected(world, newPos, protect);
					}
		        }
	        }
        }
	}
	
	private void setBlockProtected(World world, BlockPos pos, boolean protect)
	{
		BlockState old_blockstate = world.getBlockState(pos);
		Block block = old_blockstate.getBlock();
		if (block instanceof CoreProtectedBlock)
		{
			world.setBlockState(pos, old_blockstate.with(CoreProtectedBlock.CORE_PROTECTED, protect));
		}
		else if (block instanceof CoreProtectedRotatedPillarBlock)
		{
			world.setBlockState(pos, old_blockstate.with(CoreProtectedRotatedPillarBlock.CORE_PROTECTED, protect));
		}
		else if (block instanceof CoreProtectedSlabBlock)
		{
			world.setBlockState(pos, old_blockstate.with(CoreProtectedSlabBlock.CORE_PROTECTED, protect));
		}
		else if (block instanceof CoreProtectedStairsBlock)
		{
			world.setBlockState(pos, old_blockstate.with(CoreProtectedStairsBlock.CORE_PROTECTED, protect));
		}
		else if (block instanceof CoreProtectedChestBlock)
		{
			world.setBlockState(pos, old_blockstate.with(CoreProtectedChestBlock.CORE_PROTECTED, protect));
		}
		else if (block instanceof CoreProtectedTrappedBlock)
		{
			world.setBlockState(pos, old_blockstate.with(CoreProtectedTrappedBlock.CORE_PROTECTED, protect));
		}
		else if (block instanceof CoreProtectedGlyphBlock)
		{
			world.setBlockState(pos, old_blockstate.with(CoreProtectedTrappedBlock.CORE_PROTECTED, protect));
		}
		//else if (block instanceof CoreProtectedWallBlock)
		//{
		//	worldIn.setBlockState(pos, old_blockstate.with(CoreProtectedWallBlock.CORE_PROTECTED, protect));
		//}
	}
	
	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		float x = pos.getX() + 0.5F;
		float y = pos.getY() + 0.5F;
		float z = pos.getZ() + 0.5F;
		SimpleParticleType particle = ParticleTypes.SMOKE;
		
		if (isMudCore(this))
		{
			particle = ParticleTypes.CLOUD;
		}
		else if (isLunaticCore(this))
		{
			particle = AerialHellParticleTypes.COPPER_PINE_LEAVES;
		}
		else if (isShadowCatacombsCore(this))
		{
			particle = AerialHellParticleTypes.SHADOW_PARTICLE;
		}
		else if (isGoldenNetherCore(this))
		{
			particle = AerialHellParticleTypes.GOD_FLAME;
		}
		else if (isVoluciteCore(this))
		{
			particle = ParticleTypes.ENCHANT;
		}
		
		world.addParticle(particle, x + 1.5F * (rand.nextFloat() - 0.5F), y + 1.5F * (rand.nextFloat() - 0.5F), z + 1.5F * (rand.nextFloat() - 0.5F), 0.4 * (rand.nextFloat() - 0.5F), 0.4 * (rand.nextFloat() - 0.5F), 0.4 * (rand.nextFloat() - 0.5F));
	}
	
	private boolean isMudCore(DungeonCoreBlock core) {return (core == AerialHellBlocks.MUD_DUNGEON_CORE);}
	private boolean isLunaticCore(DungeonCoreBlock core) {return (core == AerialHellBlocks.LUNATIC_DUNGEON_CORE);}
	private boolean isShadowCatacombsCore(DungeonCoreBlock core) {return (core == AerialHellBlocks.SHADOW_CATACOMBS_DUNGEON_CORE);}
	private boolean isGoldenNetherCore(DungeonCoreBlock core) {return (core == AerialHellBlocks.GOLDEN_NETHER_DUNGEON_CORE);}
	private boolean isVoluciteCore(DungeonCoreBlock core) {return (core == AerialHellBlocks.VOLUCITE_DUNGEON_CORE);}
}
