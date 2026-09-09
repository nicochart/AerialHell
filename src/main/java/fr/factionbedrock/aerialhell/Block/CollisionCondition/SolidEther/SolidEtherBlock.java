package fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther;

import fr.factionbedrock.aerialhell.Block.CollisionCondition.CollisionConditionHalfTransparentBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SolidEtherBlock extends CollisionConditionHalfTransparentBlock
{
	protected final static VoxelShape SOLID_ETHER_COLLISION_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 0.02, 16.0);
	
	public SolidEtherBlock(Properties settings)
	{
		super(settings.isValidSpawn(Blocks::never));
	}

	@Override public void livingEntityInside(BlockState state, Level world, BlockPos pos, LivingEntity entity)
	{
		if (!EntityHelper.isFeatheryEntity(entity)) {super.livingEntityInside(state, world, pos, entity);}
	}

	@Override public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool)
	{
		super.playerDestroy(level, player, pos, state, blockEntity, tool);

		if (!EntityHelper.hasEnchantment(player, Enchantments.SILK_TOUCH) && !state.is(AerialHellBlocks.WHITE_SOLID_ETHER))
		{
			level.setBlock(pos, AerialHellBlocks.WHITE_SOLID_ETHER.defaultBlockState(), Block.UPDATE_ALL);
		}
	}

	@Override protected boolean canEntityCollide(Entity entity) {return !EntityHelper.isImmuneToSolidEtherCollision(entity);}
	@Override protected VoxelShape getCollidingShape() {return SOLID_ETHER_COLLISION_SHAPE;}
}