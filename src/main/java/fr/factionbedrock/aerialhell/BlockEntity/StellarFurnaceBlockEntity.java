package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.StellarFurnaceMenu;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class StellarFurnaceBlockEntity extends AbstractFurnaceBlockEntity
{
	protected StellarFurnaceBlockEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state, RecipeType<? extends AbstractCookingRecipe> recipeType) {super(blockEntity, pos, state, recipeType);}

	public StellarFurnaceBlockEntity(BlockPos pos, BlockState state) {this(AerialHellBlockEntities.STELLAR_FURNACE, pos, state, RecipeType.SMELTING);}

	@Override protected Text getContainerName()
	{
		return Text.translatable("container." + AerialHell.MODID + ".stellar_furnace");
	}

	@Override protected ScreenHandler createScreenHandler(int id, PlayerInventory inv) {return new StellarFurnaceMenu(id, inv, this, this.propertyDelegate);}
}
