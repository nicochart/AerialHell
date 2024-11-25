package fr.factionbedrock.aerialhell.Block.Plants.Vines;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CaveVinesBodyBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class AerialHellCaveVinesPlantBlock extends CaveVinesBodyBlock
{
    public AerialHellCaveVinesPlantBlock(AbstractBlock.Settings settings) {super(settings);}

    @Override protected AbstractPlantStemBlock getStem()
    {
        if (this == AerialHellBlocks.GLOWING_STICK_FRUIT_VINES_PLANT) {return AerialHellBlocks.GLOWING_STICK_FRUIT_VINES;}
        else /*if (this == AerialHellBlocks.BLOSSOMING_VINES_PLANT)*/{return AerialHellBlocks.BLOSSOMING_VINES;}
    }

    protected Item getBerryItem()
    {
        if (this == AerialHellBlocks.GLOWING_STICK_FRUIT_VINES_PLANT) {return AerialHellItems.GLOWING_STICK_FRUIT;}
        else /*if (this == AerialHellBlocks.BLOSSOMING_VINES_PLANT)*/{return AerialHellItems.VINE_BLOSSOM;}
    }

    @Override public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {return new ItemStack(this.getBerryItem());}

    @Override public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        return AerialHellCaveVinesBlock.tryPickBerries(player, state, world, pos, this.getBerryItem());
    }

    @Override public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
    {
        return super.canPlaceAt(state, world, pos) && !world.getBlockState(pos.offset((this.growthDirection.getOpposite()))).isIn(AerialHellTags.Blocks.SOLID_ETHER);
    }
}
