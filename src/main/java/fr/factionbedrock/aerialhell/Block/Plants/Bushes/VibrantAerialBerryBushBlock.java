package fr.factionbedrock.aerialhell.Block.Plants.Bushes;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.Block;
import net.minecraft.block.BushBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class VibrantAerialBerryBushBlock extends BushBlock implements IGrowable
{
	public static final IntegerProperty AGE = BlockStateProperties.AGE_0_15;
	
	public VibrantAerialBerryBushBlock(Properties properties)
	{
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0));
    }
 
    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state)
    {
        return new ItemStack(AerialHellBlocksAndItems.VIBRANT_AERIAL_BERRY.get());
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        super.tick(state, worldIn, pos, rand);
        int age = state.get(AGE);
        if (age < 15 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(5) == 0))
        {
            worldIn.setBlockState(pos, state.with(AGE, age + 1), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
        }
    }
    
    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return state.isIn(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get()) || state.isIn(AerialHellBlocksAndItems.CHISELED_STELLAR_GRASS_BLOCK.get());
    }
    
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        int age = state.get(AGE);
        if (age != 15 && player.getHeldItem(handIn).getItem() == Items.BONE_MEAL)
        {
            return ActionResultType.PASS;
        }
        else if (age > 13)
        {
            int j = 1 + worldIn.rand.nextInt(2);
            spawnAsEntity(worldIn, pos, new ItemStack(AerialHellBlocksAndItems.VIBRANT_AERIAL_BERRY.get(), j + (age == 15 ? 1 : 0)));
            worldIn.playSound(null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 1F + worldIn.rand.nextFloat() * 0.2F);
            worldIn.setBlockState(pos, state.with(AGE, 13), 2);
            return ActionResultType.SUCCESS;
        }
        else
        {
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(AGE);
    }

    @Override
    public boolean canGrow(IBlockReader iBlockReader, BlockPos blockPos, BlockState blockState, boolean b)
    {
        return blockState.get(AGE) < 15;
    }

    @Override
    public boolean canUseBonemeal(World world, Random random, BlockPos blockPos, BlockState blockState)
    {
        return blockState.get(AGE) < 13;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state)
    {
        int new_age = Math.min(15, state.get(AGE) + 1);
        worldIn.setBlockState(pos, state.with(AGE, new_age), 2);
    }
}
