package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import static fr.factionbedrock.aerialhell.Block.DungeonCores.CoreProtectedBlock.CORE_PROTECTED;

public class CoreProtectedWallBlock extends WallBlock
{
	//In 1.20.1, WallBlock caches its shapes in maps built from defaultBlockState() in its constructor, so these maps only contain states with one CORE_PROTECTED value.
	//Shapes are looked up with this value, otherwise the shape of the states with the other value is null (crash on registry bake).
	private final boolean shapesCoreProtectedValue;

	public CoreProtectedWallBlock(Properties properties)
	{
		super(properties);
		this.shapesCoreProtectedValue = this.defaultBlockState().getValue(CORE_PROTECTED);
		this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false));
	}

	public boolean isProtected(BlockState state)
	{
		return state.getValue(CORE_PROTECTED);
	}

	@SuppressWarnings("deprecation")
	@Override
	public float getExplosionResistance(BlockState state, BlockGetter world, BlockPos pos, Explosion explosion)
	{
		return isProtected(state) ? 1200.0F : this.asBlock().getExplosionResistance();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
		builder.add(CORE_PROTECTED);
	}

	@Override public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
	{
		return super.getShape(state.setValue(CORE_PROTECTED, this.shapesCoreProtectedValue), level, pos, context);
	}

	@Override public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
	{
		return super.getCollisionShape(state.setValue(CORE_PROTECTED, this.shapesCoreProtectedValue), level, pos, context);
	}

	@Override
	public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos)
	{
		float f = state.getDestroySpeed(level, pos);
		if (f == -1.0F || isProtected(state))
		{
			return 0.0F;
		}
		else
		{
			int i = net.minecraftforge.common.ForgeHooks.isCorrectToolForDrops(state, player) ? 30 : 100;
			return player.getDigSpeed(state, pos) / f / (float)i;
		}
	}

	public WallBlock getCrackedVariant()
	{
		if (this == AerialHellBlocksAndItems.MUD_BRICKS_WALL.get()) {return AerialHellBlocksAndItems.CRACKED_MUD_BRICKS_WALL.get();}
		else if (this == AerialHellBlocksAndItems.LUNATIC_STONE_WALL.get()) {return AerialHellBlocksAndItems.CRACKED_LUNATIC_STONE_WALL.get();}
		else if (this == AerialHellBlocksAndItems.SHADOW_CATACOMBS_BRICKS_WALL.get()) {return AerialHellBlocksAndItems.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL.get();}
		else if (this == AerialHellBlocksAndItems.GOLDEN_NETHER_BRICKS_WALL.get()) {return AerialHellBlocksAndItems.CRACKED_GOLDEN_NETHER_BRICKS_WALL.get();}
		else if (this == AerialHellBlocksAndItems.VOLUCITE_STONE_WALL.get()) {return AerialHellBlocksAndItems.CRACKED_VOLUCITE_STONE_WALL.get();}
		else {return this;}
	}

	@Override public @Nullable PushReaction getPistonPushReaction(BlockState state) {return this.isProtected(state) ? PushReaction.BLOCK : super.getPistonPushReaction(state);}
}
