package fr.factionbedrock.aerialhell.Block.Plants.Bushes;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class AerialBerryBushBlock extends VegetationBlock implements BonemealableBlock
{
    public static final MapCodec<AerialBerryBushBlock> CODEC = simpleCodec(AerialBerryBushBlock::new);
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape SHAPE = Block.column((double)16.0F, (double)0.0F, (double)13.0F);

	public AerialBerryBushBlock(Properties properties)
	{
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override protected VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext p_401402_) {return SHAPE;}

    @Override public @NotNull MapCodec<? extends AerialBerryBushBlock> codec() {return CODEC;}

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand)
    {
        int age = state.getValue(AGE);
        if (age < 3 && net.neoforged.neoforge.common.CommonHooks.canCropGrow(worldIn, pos, state, rand.nextInt(5) == 0))
        {
            worldIn.setBlock(pos, state.setValue(AGE, age + 1), 2);
            net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(worldIn, pos, state);
        }
    }
    
    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos)
    {
        return state.is(AerialHellBlocks.STELLAR_GRASS_BLOCK.get()) || state.is(AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK.get());
    }
    
    @Override
    public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit)
    {
        int age = state.getValue(AGE);
        if (age != 3 && stack.getItem() == Items.BONE_MEAL)
        {
            return InteractionResult.FAIL;
        }
        else if (age > 1)
        {
            int j = 1 + worldIn.random.nextInt(2);
            popResource(worldIn, pos, new ItemStack(AerialHellItems.AERIAL_BERRY.get(), j + (age == 3 ? 1 : 0)));
            worldIn.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.9F + worldIn.random.nextFloat() * 0.3F);
            worldIn.setBlock(pos, state.setValue(AGE, 1), 2);
            return InteractionResult.SUCCESS;
        }
        else
        {
            return super.useItemOn(stack, state, worldIn, pos, player, handIn, hit);
        }
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(AGE);}

    @Override public boolean isValidBonemealTarget(LevelReader level, BlockPos blockPos, BlockState blockState) {return blockState.getValue(AGE) < 3;}

    @Override public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos blockPos, BlockState blockState) {return true;}

    @Override
    public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state)
    {
        int new_age = Math.min(3, state.getValue(AGE) + 1);
        worldIn.setBlock(pos, state.setValue(AGE, new_age), 2);
    }
}
