package fr.factionbedrock.aerialhell.Block.Plants.Vines;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CaveVinesPlantBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AerialHellCaveVinesPlantBlock extends CaveVinesPlantBlock
{
    public AerialHellCaveVinesPlantBlock(BlockBehaviour.Properties settings) {super(settings);}

    @Override protected GrowingPlantHeadBlock getHeadBlock()
    {
        if (this == AerialHellBlocks.GLOWING_STICK_FRUIT_VINES_PLANT) {return AerialHellBlocks.GLOWING_STICK_FRUIT_VINES;}
        else /*if (this == AerialHellBlocks.BLOSSOMING_VINES_PLANT)*/{return AerialHellBlocks.BLOSSOMING_VINES;}
    }

    protected Item getBerryItem()
    {
        if (this == AerialHellBlocks.GLOWING_STICK_FRUIT_VINES_PLANT) {return AerialHellItems.GLOWING_STICK_FRUIT;}
        else /*if (this == AerialHellBlocks.BLOSSOMING_VINES_PLANT)*/{return AerialHellItems.VINE_BLOSSOM;}
    }

    @Override public ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData) {return new ItemStack(this.getBerryItem());}

    @Override public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
    {
        return AerialHellCaveVinesBlock.tryPickBerries(player, state, world, pos, this.getBerryItem());
    }

    @Override public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        return super.canSurvive(state, world, pos) && !world.getBlockState(pos.relative((this.growthDirection.getOpposite()))).is(AerialHellTags.Blocks.SOLID_ETHER);
    }
}
