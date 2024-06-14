package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

public class CoreProtectedBlock extends Block
{
	public static final BooleanProperty CORE_PROTECTED = BooleanProperty.create("core_protected");
	
	public CoreProtectedBlock(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false));
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
	
	@Override
	public float getDestroyProgress(BlockState state, Player player, BlockGetter worldIn, BlockPos pos)
	{
		float f = state.getDestroySpeed(worldIn, pos);
	    if (f == -1.0F || isProtected(state))
	    {
	         return 0.0F;
	    }
	    else
	    {
	         int i = net.minecraftforge.common.ForgeHooks.isCorrectToolForDrops(state, player) ? 30 : 100;
	         return player.getDestroySpeed(state, pos) / f / (float)i;
	    }
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
}
