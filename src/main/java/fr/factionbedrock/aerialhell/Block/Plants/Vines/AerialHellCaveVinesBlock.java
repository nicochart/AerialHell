package fr.factionbedrock.aerialhell.Block.Plants.Vines;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CaveVinesHeadBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class AerialHellCaveVinesBlock extends CaveVinesHeadBlock
{
    public AerialHellCaveVinesBlock(AbstractBlock.Settings settings) {super(settings);}

    @Override protected Block getPlant()
    {
        if (this == AerialHellBlocks.GLOWING_STICK_FRUIT_VINES) {return AerialHellBlocks.GLOWING_STICK_FRUIT_VINES_PLANT;}
        else /*if (this == AerialHellBlocks.BLOSSOMING_VINES)*/{return AerialHellBlocks.BLOSSOMING_VINES_PLANT;}
    }

    protected Item getBerryItem()
    {
        if (this == AerialHellBlocks.GLOWING_STICK_FRUIT_VINES) {return AerialHellItems.GLOWING_STICK_FRUIT;}
        else /*if (this == AerialHellBlocks.BLOSSOMING_VINES)*/{return AerialHellItems.VINE_BLOSSOM;}
    }

    @Override public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        return tryPickBerries(player, state, world, pos, this.getBerryItem());
    }

    //copy of net.minecraft.block.CaveVines pickBerries method, edited
    protected static ActionResult tryPickBerries(@Nullable Entity picker, BlockState state, World world, BlockPos pos, Item berryItem)
    {
        if (state.get(BERRIES))
        {
            Block.dropStack(world, pos, new ItemStack(berryItem, 1));
            float f = MathHelper.nextBetween(world.random, 0.8F, 1.2F);
            world.playSound(null, pos, SoundEvents.BLOCK_CAVE_VINES_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, f);
            BlockState blockState = state.with(BERRIES, false);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(picker, blockState));
            return ActionResult.SUCCESS;
        }
        else {return ActionResult.PASS;}
    }

    @Override public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
    {
        return super.canPlaceAt(state, world, pos) && !world.getBlockState(pos.offset((this.growthDirection.getOpposite()))).isIn(AerialHellTags.Blocks.SOLID_ETHER);
    }
}
