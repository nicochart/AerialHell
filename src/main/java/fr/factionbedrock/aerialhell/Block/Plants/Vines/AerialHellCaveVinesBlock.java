package fr.factionbedrock.aerialhell.Block.Plants.Vines;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CaveVinesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class AerialHellCaveVinesBlock extends CaveVinesBlock
{
    public AerialHellCaveVinesBlock(BlockBehaviour.Properties settings) {super(settings);}

    @Override protected Block getBodyBlock()
    {
        if (this == AerialHellBlocks.GLOWING_STICK_FRUIT_VINES) {return AerialHellBlocks.GLOWING_STICK_FRUIT_VINES_PLANT;}
        else /*if (this == AerialHellBlocks.BLOSSOMING_VINES)*/{return AerialHellBlocks.BLOSSOMING_VINES_PLANT;}
    }

    protected Item getBerryItem()
    {
        if (this == AerialHellBlocks.GLOWING_STICK_FRUIT_VINES) {return AerialHellItems.GLOWING_STICK_FRUIT;}
        else /*if (this == AerialHellBlocks.BLOSSOMING_VINES)*/{return AerialHellItems.VINE_BLOSSOM;}
    }

    @Override public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
    {
        return tryPickBerries(player, state, world, pos, this.getBerryItem());
    }

    //copy of net.minecraft.block.CaveVines pickBerries method, edited
    protected static InteractionResult tryPickBerries(@Nullable Entity picker, BlockState state, Level world, BlockPos pos, Item berryItem)
    {
        if (state.getValue(BERRIES))
        {
            Block.popResource(world, pos, new ItemStack(berryItem, 1));
            float f = Mth.randomBetween(world.getRandom(), 0.8F, 1.2F);
            world.playSound(null, pos, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, f);
            BlockState blockState = state.setValue(BERRIES, false);
            world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(picker, blockState));
            return InteractionResult.SUCCESS;
        }
        else {return InteractionResult.PASS;}
    }

    @Override public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        return super.canSurvive(state, world, pos) && !world.getBlockState(pos.relative((this.growthDirection.getOpposite()))).is(AerialHellTags.Blocks.SOLID_ETHER);
    }
}
