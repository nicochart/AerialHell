package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class CoreProtectedBlock extends Block
{
	public static final BooleanProperty CORE_PROTECTED = BooleanProperty.create("core_protected");

	public CoreProtectedBlock(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false));
	}

	//Called once on common setup. Protected states get the same hardness as bedrock (-1), so that anything checking the hardness
	//(vanilla pistons, Create drills and contraptions, other mods block breakers) can't break or move them.
	//need accesstransformer to access and modify state.destroySpeed
	public static void makeProtectedStatesUnbreakable()
	{
		for (Block block : ForgeRegistries.BLOCKS.getValues())
		{
			if (!block.getStateDefinition().getProperties().contains(CORE_PROTECTED)) {continue;}
			for (BlockState state : block.getStateDefinition().getPossibleStates())
			{
				if (state.getValue(CORE_PROTECTED)) {state.destroySpeed = -1.0F;}
			}
		}
	}
	
	public void setProtected(boolean protect)
	{
		this.registerDefaultState(this.stateDefinition.any().setValue(CORE_PROTECTED, protect));
	}
	
	public boolean isProtected(BlockState state)
	{
		return state.getValue(CORE_PROTECTED);
	}
	
	@Override
	public float getExplosionResistance(BlockState state, BlockGetter world, BlockPos pos, Explosion explosion)
    {
        return isProtected(state) ? 1200.0F : this.asBlock().getExplosionResistance();
    }
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(CORE_PROTECTED);
	}

	public Block getCrackedVariant()
	{
		if (this == AerialHellBlocksAndItems.MUD_BRICKS.get()) {return AerialHellBlocksAndItems.CRACKED_MUD_BRICKS.get();}
		else if (this == AerialHellBlocksAndItems.LIGHT_MUD_BRICKS.get()) {return AerialHellBlocksAndItems.CRACKED_LIGHT_MUD_BRICKS.get();}
		else if (this == AerialHellBlocksAndItems.LUNATIC_STONE.get()) {return AerialHellBlocksAndItems.CRACKED_LUNATIC_STONE.get();}
		else if (this == AerialHellBlocksAndItems.LIGHT_LUNATIC_STONE.get()) {return AerialHellBlocksAndItems.CRACKED_LIGHT_LUNATIC_STONE.get();}
		else if (this == AerialHellBlocksAndItems.SHADOW_CATACOMBS_BRICKS.get()) {return AerialHellBlocksAndItems.CRACKED_SHADOW_CATACOMBS_BRICKS.get();}
		else if (this == AerialHellBlocksAndItems.LIGHT_SHADOW_CATACOMBS_BRICKS.get()) {return AerialHellBlocksAndItems.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS.get();}
		else if (this == AerialHellBlocksAndItems.GOLDEN_NETHER_BRICKS.get()) {return AerialHellBlocksAndItems.CRACKED_GOLDEN_NETHER_BRICKS.get();}
		else if (this == AerialHellBlocksAndItems.LIGHT_GOLDEN_NETHER_BRICKS.get()) {return AerialHellBlocksAndItems.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS.get();}
		else if (this == AerialHellBlocksAndItems.VOLUCITE_STONE.get()) {return AerialHellBlocksAndItems.CRACKED_VOLUCITE_STONE.get();}
		else if (this == AerialHellBlocksAndItems.LIGHT_VOLUCITE_STONE.get()) {return AerialHellBlocksAndItems.CRACKED_LIGHT_VOLUCITE_STONE.get();}
		else {return this;}
	}

	@Override public @Nullable PushReaction getPistonPushReaction(BlockState state) {return this.isProtected(state) ? PushReaction.BLOCK : super.getPistonPushReaction(state);}
}
