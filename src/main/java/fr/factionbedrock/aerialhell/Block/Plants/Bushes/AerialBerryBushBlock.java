package fr.factionbedrock.aerialhell.Block.Plants.Bushes;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class AerialBerryBushBlock extends VegetationBlock implements BonemealableBlock
{
    public static final MapCodec<AerialBerryBushBlock> CODEC = simpleCodec(AerialBerryBushBlock::new);
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	
	public AerialBerryBushBlock(BlockBehaviour.Properties settings)
	{
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override protected @NotNull MapCodec<? extends AerialBerryBushBlock> codec() {return CODEC;}

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand)
    {
        int age = state.getValue(AGE);
        if (age < 3 && rand.nextInt(5) == 0 && world.getRawBrightness(pos.above(), 0) >= 9)
        {
            BlockState blockState = state.setValue(AGE, age + 1);
            world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockState));
        }
    }
    
    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos)
    {
        return floor.is(AerialHellBlocks.STELLAR_GRASS_BLOCK) || floor.is(AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK);
    }

    @Override protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        int age = state.getValue(AGE);
        return age != 3 && stack.is(Items.BONE_MEAL) ? InteractionResult.PASS : super.useItemOn(stack, state, world, pos, player, hand, hit);
    }

    @Override protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
    {
        int age = state.getValue(AGE);
        if (age > 1)
        {
            int j = 1 + world.getRandom().nextInt(2);
            popResource(world, pos, new ItemStack(AerialHellItems.AERIAL_BERRY, j + (age == 3 ? 1 : 0)));
            world.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + world.getRandom().nextFloat() * 0.4F);
            BlockState blockState = state.setValue(AGE, 1);
            world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
            return InteractionResult.SUCCESS;
        }
        else {return super.useWithoutItem(state, world, pos, player, hit);}
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(AGE);}

    @Override public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {return state.getValue(AGE) < 3;}

    @Override public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {return true;}

    @Override public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state)
    {
        int new_age = Math.min(3, state.getValue(AGE) + 1);
        world.setBlock(pos, state.setValue(AGE, Integer.valueOf(new_age)), Block.UPDATE_CLIENTS);
    }
}
