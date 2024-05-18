package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class GlyphBlock extends Block
{
	private static final int GLYPH_MAX_INDEX = 35;
	public static final IntegerProperty GLYPH_INDEX = IntegerProperty.create("glyph_index", 0, GLYPH_MAX_INDEX);

	public GlyphBlock(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(GLYPH_INDEX, Integer.valueOf(0)));
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(GLYPH_INDEX);}

	@Override public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
	{
		if (state.getBlock() instanceof GlyphBlock)
		{
			int newIndex = getNextIndex(state.getValue(GLYPH_INDEX), player);
			level.setBlockAndUpdate(pos, state.setValue(GLYPH_INDEX, newIndex));
			level.playSound(player, pos, this.getInteractSound(state, level, pos, player), SoundSource.BLOCKS, 1.0F, 0.9F + (0.2F * level.random.nextFloat()));
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	private static int getNextIndex(int previousIndex, @Nullable Player interactingPlayer)
	{
		if (interactingPlayer != null && interactingPlayer.isShiftKeyDown()) {return previousIndex == 0 ? GLYPH_MAX_INDEX : previousIndex - 1;}
		else {return previousIndex < GLYPH_MAX_INDEX ? previousIndex + 1 : 0;}
	}

	protected SoundEvent getInteractSound(BlockState state, Level level, BlockPos pos, Player entity) {return AerialHellSoundEvents.GLYPH_BLOCK_INTERACT.get();}
}