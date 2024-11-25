package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class CoreProtectedBlock extends Block
{
	public static final BooleanProperty CORE_PROTECTED = BooleanProperty.of("core_protected");
	
	public CoreProtectedBlock(AbstractBlock.Settings settings)
	{
		super(settings);
		this.setDefaultState(this.getDefaultState().with(CORE_PROTECTED, false));
	}
	
	public boolean isProtected(BlockState state) {return state.get(CORE_PROTECTED);}
	
	/*@Override TODO make a mixin to intercept net.minecraft.world.explosion.ExplosionBehavior getBlastResistance(..) with a CallbackInfoReturnable<float> ci and ci.setReturnValue(1200.0F)
	public float getExplosionResistance(BlockState state, BlockGetter world, BlockPos pos, Explosion explosion)
    {
        return isProtected(state) ? 1200.0F : this.asBlock().getExplosionResistance();
    }*/
	
	@Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(CORE_PROTECTED);}
	
	@Override
	public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos)
	{
		float f = state.getHardness(world, pos);
	    if (f == -1.0F || isProtected(state))
	    {
	         return 0.0F;
	    }
	    else
	    {
	         int i = player.canHarvest(state) ? 30 : 100;
	         return player.getBlockBreakingSpeed(state) / f / (float)i;
	    }
	}

	public Block getCrackedVariant()
	{
		if (this == AerialHellBlocks.MUD_BRICKS) {return AerialHellBlocks.CRACKED_MUD_BRICKS;}
		else if (this == AerialHellBlocks.LIGHT_MUD_BRICKS) {return AerialHellBlocks.CRACKED_LIGHT_MUD_BRICKS;}
		else if (this == AerialHellBlocks.LUNATIC_STONE) {return AerialHellBlocks.CRACKED_LUNATIC_STONE;}
		else if (this == AerialHellBlocks.LIGHT_LUNATIC_STONE) {return AerialHellBlocks.CRACKED_LIGHT_LUNATIC_STONE;}
		else if (this == AerialHellBlocks.SHADOW_CATACOMBS_BRICKS) {return AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS;}
		else if (this == AerialHellBlocks.LIGHT_SHADOW_CATACOMBS_BRICKS) {return AerialHellBlocks.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS;}
		else if (this == AerialHellBlocks.GOLDEN_NETHER_BRICKS) {return AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS;}
		else if (this == AerialHellBlocks.LIGHT_GOLDEN_NETHER_BRICKS) {return AerialHellBlocks.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS;}
		else if (this == AerialHellBlocks.VOLUCITE_STONE) {return AerialHellBlocks.CRACKED_VOLUCITE_STONE;}
		else if (this == AerialHellBlocks.LIGHT_VOLUCITE_STONE) {return AerialHellBlocks.CRACKED_LIGHT_VOLUCITE_STONE;}
		else {return this;}
	}
}
