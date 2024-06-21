package fr.factionbedrock.aerialhell.Block.Plants.Bushes;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.InteractionResult;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class VibrantAerialBerryBushBlock extends BushBlock implements BonemealableBlock
{
    public static final MapCodec<VibrantAerialBerryBushBlock> CODEC = simpleCodec(VibrantAerialBerryBushBlock::new);
	public static final IntegerProperty AGE = BlockStateProperties.AGE_15;
	
	public VibrantAerialBerryBushBlock(Properties properties)
	{
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override protected @NotNull MapCodec<? extends BushBlock> codec() {return CODEC;}

    @Override //TODO replace with RandomTick() ?
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand)
    {
        super.tick(state, worldIn, pos, rand);
        int age = state.getValue(AGE);
        if (age < 15 && net.neoforged.neoforge.common.CommonHooks.canCropGrow(worldIn, pos, state, rand.nextInt(5) == 0))
        {
            worldIn.setBlock(pos, state.setValue(AGE, age + 1), 2);
            net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(worldIn, pos, state);
        }
    }
    
    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos)
    {
        return state.is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get()) || state.is(AerialHellBlocksAndItems.CHISELED_STELLAR_GRASS_BLOCK.get());
    }
    
    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit)
    {
        int age = state.getValue(AGE);
        if (age != 15 && stack.getItem() == Items.BONE_MEAL)
        {
            return ItemInteractionResult.FAIL;
        }
        else if (age > 13)
        {
            int j = 1 + worldIn.random.nextInt(2);
            popResource(worldIn, pos, new ItemStack(AerialHellBlocksAndItems.VIBRANT_AERIAL_BERRY.get(), j + (age == 15 ? 1 : 0)));
            worldIn.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 1F + worldIn.random.nextFloat() * 0.2F);
            worldIn.setBlock(pos, state.setValue(AGE, 13), 2);
            return ItemInteractionResult.SUCCESS;
        }
        else
        {
            return super.useItemOn(stack, state, worldIn, pos, player, handIn, hit);
        }
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(AGE);}

    @Override public boolean isValidBonemealTarget(LevelReader level, BlockPos blockPos, BlockState blockState) {return blockState.getValue(AGE) < 15;}
    @Override public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos blockPos, BlockState blockState) {return blockState.getValue(AGE) < 13;}

    @Override
    public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state)
    {
        int new_age = Math.min(15, state.getValue(AGE) + 1);
        worldIn.setBlock(pos, state.setValue(AGE, new_age), 2);
    }
}
