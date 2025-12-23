package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;

import static fr.factionbedrock.aerialhell.Registry.AerialHellStateProperties.CORE_PROTECTED;

public class DungeonCoreBlock extends Block
{	
	int coreProtectRange; //odd number is better
	
	public DungeonCoreBlock(Properties properties, int coreRangeIn)
	{
		super(properties);
		this.coreProtectRange = coreRangeIn;
	}
	
	@Override
	public boolean onDestroyedByPlayer(BlockState state, Level worldIn, BlockPos pos, Player player, ItemStack toolStack, boolean willHarvest, FluidState fluid)
	{
		boolean flag = super.onDestroyedByPlayer(state, worldIn, pos, player, toolStack, willHarvest, fluid);
		if (flag) {setAreaProtected(worldIn, pos, false);}
		return flag;
	}
	
	public void setAreaProtected(Level worldIn, BlockPos originPos, boolean protect)
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
	
	private void setAreaProtected(Level worldIn, BlockPos originPos, TagKey<Block> tag, boolean protect)
	{
		for(int x = originPos.getX() - (coreProtectRange - 1)/2; x <= originPos.getX() + (coreProtectRange - 1)/2; x++)
        {
			for(int y = originPos.getY() - (coreProtectRange - 1)/2; y <= originPos.getY() + (coreProtectRange - 1)/2; y++)
	        {
				for(int z = originPos.getZ() - (coreProtectRange - 1)/2; z <= originPos.getZ() + (coreProtectRange - 1)/2; z++)
		        {
					BlockPos newPos = new BlockPos(x, y, z);
					BlockState blockstate = worldIn.getBlockState(newPos);
					if (blockstate.is(tag))
					{
						setBlockProtected(worldIn, newPos, protect);
					}
		        }
	        }
        }
	}
	
	private void setBlockProtected(Level level, BlockPos pos, boolean protect)
	{
		BlockState old_blockstate = level.getBlockState(pos);
		if (old_blockstate.getBlock() instanceof CoreProtectedPropertyBlock)
		{
			level.setBlockAndUpdate(pos, old_blockstate.setValue(CORE_PROTECTED, protect));
		}
	}
	
	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand)
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
	
	private boolean isMudCore(DungeonCoreBlock core) {return (core == AerialHellBlocks.MUD_DUNGEON_CORE.get());}
	private boolean isLunaticCore(DungeonCoreBlock core) {return (core == AerialHellBlocks.LUNATIC_DUNGEON_CORE.get());}
	private boolean isShadowCatacombsCore(DungeonCoreBlock core) {return (core == AerialHellBlocks.SHADOW_CATACOMBS_DUNGEON_CORE.get());}
	private boolean isGoldenNetherCore(DungeonCoreBlock core) {return (core == AerialHellBlocks.GOLDEN_NETHER_DUNGEON_CORE.get());}
	private boolean isVoluciteCore(DungeonCoreBlock core) {return (core == AerialHellBlocks.VOLUCITE_DUNGEON_CORE.get());}
}
