package fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther;

import fr.factionbedrock.aerialhell.Block.CollisionCondition.CollisionConditionHalfTransparentBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class SolidEtherBlock extends CollisionConditionHalfTransparentBlock
{
	protected final static VoxelShape SOLID_ETHER_COLLISION_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 0.02, 16.0);
	
	public SolidEtherBlock(BlockBehaviour.Properties properties)
	{
		super(properties.isValidSpawn(Blocks::never));
		this.registerDefaultState(this.defaultBlockState());
	}

	@Override public void livingEntityInside(BlockState state, Level level, BlockPos pos, LivingEntity entity)
	{
		if (!EntityHelper.isFeatheryEntity(entity)) {super.livingEntityInside(state, level, pos, entity);}
	}

	@Override public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool)
	{
		super.playerDestroy(level, player, pos, state, blockEntity, tool);

		if (!EntityHelper.hasEnchantment(player, Enchantments.SILK_TOUCH) && !state.is(AerialHellBlocksAndItems.WHITE_SOLID_ETHER.get()))
		{
			level.setBlock(pos, AerialHellBlocksAndItems.WHITE_SOLID_ETHER.get().defaultBlockState(), Block.UPDATE_ALL);
		}
	}

	@Override protected boolean canEntityCollide(Entity entity) {return !EntityHelper.isImmuneToSolidEtherCollision(entity);}
	@Override protected VoxelShape getCollidingShape() {return SOLID_ETHER_COLLISION_SHAPE;}
}