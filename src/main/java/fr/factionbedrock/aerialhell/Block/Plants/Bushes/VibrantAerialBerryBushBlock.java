package fr.factionbedrock.aerialhell.Block.Plants.Bushes;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.NotNull;

public class VibrantAerialBerryBushBlock extends PlantBlock implements Fertilizable
{
    public static final MapCodec<VibrantAerialBerryBushBlock> CODEC = createCodec(VibrantAerialBerryBushBlock::new);
	public static final IntProperty AGE = Properties.AGE_15;
	
	public VibrantAerialBerryBushBlock(AbstractBlock.Settings settings)
	{
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    @Override protected @NotNull MapCodec<? extends VibrantAerialBerryBushBlock> getCodec() {return CODEC;}

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand)
    {
        int age = state.get(AGE);
        if (age < 15 && rand.nextInt(5) == 0 && world.getBaseLightLevel(pos.up(), 0) >= 9)
        {
            BlockState blockState = state.with(AGE, age + 1);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
        }
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos)
    {
        return floor.isOf(AerialHellBlocks.STELLAR_GRASS_BLOCK) || floor.isOf(AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK);
    }

    @Override protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        int age = state.get(AGE);
        return age != 15 && stack.isOf(Items.BONE_MEAL) ? ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION : super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        int age = state.get(AGE);
        if (age > 13)
        {
            int j = 1 + world.random.nextInt(2);
            dropStack(world, pos, new ItemStack(AerialHellItems.VIBRANT_AERIAL_BERRY, j + (age == 15 ? 1 : 0)));
            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            BlockState blockState = state.with(AGE, 13);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
            return ActionResult.success(world.isClient);
        }
        else {return super.onUse(state, world, pos, player, hit);}
    }

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(AGE);}

    @Override public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {return state.get(AGE) < 15;}

    @Override public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {return state.get(AGE) < 13;}

    @Override public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state)
    {
        int new_age = Math.min(15, state.get(AGE) + 1);
        world.setBlockState(pos, state.with(AGE, Integer.valueOf(new_age)), Block.NOTIFY_LISTENERS);
    }
}
