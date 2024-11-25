package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GlyphBlock extends Block
{
	private static final int GLYPH_MAX_INDEX = 45;
	public static final IntProperty GLYPH_INDEX = IntProperty.of("glyph_index", 0, GLYPH_MAX_INDEX);
	public static final BooleanProperty NORTH = Properties.NORTH;
	public static final BooleanProperty EAST = Properties.EAST;
	public static final BooleanProperty SOUTH = Properties.SOUTH;
	public static final BooleanProperty WEST = Properties.WEST;

	public GlyphBlock(AbstractBlock.Settings settings)
	{
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(GLYPH_INDEX, 0).with(NORTH, true).with(EAST, true).with(SOUTH, true).with(WEST, true));
	}

	@Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(GLYPH_INDEX, NORTH, EAST, SOUTH, WEST);}

	@Override public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
	{
		Direction face = hit.getSide();
		if (face == Direction.UP || face == Direction.DOWN) {return ActionResult.PASS;}

		if (!player.isOnGround()) //disable / able glyph block face
		{
			BooleanProperty property = face == Direction.NORTH ? NORTH : face == Direction.EAST ? EAST : face == Direction.SOUTH ? SOUTH : WEST;
			world.setBlockState(pos, state.with(property, !state.get(property)));
			world.playSound(player, pos, this.getInteractSound(state, world, pos, player), SoundCategory.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
			return ActionResult.SUCCESS;
		}
		else //change glyph if valid face
		{
			if ((face == Direction.NORTH && !state.get(NORTH)) || (face == Direction.EAST && !state.get(EAST)) || (face == Direction.SOUTH && !state.get(SOUTH)) || (face == Direction.WEST && !state.get(WEST))) {return ActionResult.PASS;}
			if (state.getBlock() instanceof GlyphBlock)
			{
				int newIndex = getNextIndex(state.get(GLYPH_INDEX), player);
				world.setBlockState(pos, state.with(GLYPH_INDEX, newIndex));
				world.playSound(player, pos, this.getInteractSound(state, world, pos, player), SoundCategory.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
				return ActionResult.SUCCESS;
			}
		}
		return ActionResult.PASS;
	}

	private static int getNextIndex(int previousIndex, @Nullable PlayerEntity interactingPlayer)
	{
		if (interactingPlayer != null && interactingPlayer.isSneaking()) {return previousIndex == 0 ? GLYPH_MAX_INDEX : previousIndex - 1;}
		else {return previousIndex < GLYPH_MAX_INDEX ? previousIndex + 1 : 0;}
	}

	protected SoundEvent getInteractSound(BlockState state, World world, BlockPos pos, PlayerEntity entity) {return AerialHellSoundEvents.GLYPH_BLOCK_INTERACT;}
}