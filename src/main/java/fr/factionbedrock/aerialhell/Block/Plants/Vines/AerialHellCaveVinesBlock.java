package fr.factionbedrock.aerialhell.Block.Plants.Vines;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CaveVinesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AerialHellCaveVinesBlock extends CaveVinesBlock
{
    public AerialHellCaveVinesBlock(Properties properties) {super(properties);}

    @Override protected Block getBodyBlock()
    {
        if (this == AerialHellBlocks.GLOWING_STICK_FRUIT_VINES.get()) {return AerialHellBlocks.GLOWING_STICK_FRUIT_VINES_PLANT.get();}
        else /*if (this == AerialHellBlocksAndItems.BLOSSOMING_VINES.get())*/{return AerialHellBlocks.BLOSSOMING_VINES_PLANT.get();}
    }

    protected Item getBerryItem() {
        if (this == AerialHellBlocks.GLOWING_STICK_FRUIT_VINES.get()) {return AerialHellItems.GLOWING_STICK_FRUIT.get();}
        else /*if (this == AerialHellBlocksAndItems.BLOSSOMING_VINES.get())*/{return AerialHellItems.VINE_BLOSSOM.get();}
    }

    @Override public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult)
    {
        if (state.getValue(BERRIES))
        {
            Block.popResource(level, pos, new ItemStack(this.getBerryItem(), 1));
            float f = Mth.randomBetween(level.random, 0.8F, 1.2F);
            level.playSound((Player)null, pos, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, f);
            level.setBlock(pos, state.setValue(BERRIES, Boolean.valueOf(false)), 2);
            return InteractionResult.SUCCESS;
        }
        else {return InteractionResult.PASS;}
    }

    @Override public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {
        return super.canSurvive(state, level, pos) && !level.getBlockState(pos.relative(this.growthDirection.getOpposite())).is(AerialHellTags.Blocks.SOLID_ETHER);
    }
}
