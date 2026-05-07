package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import fr.factionbedrock.aerialhell.Inventory.Menu.OscillatorMenu;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;

public class OscillatorBlockEntity extends AbstractFurnaceBlockEntity
{
	protected OscillatorBlockEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state, RecipeType<? extends AbstractCookingRecipe> recipeType) {super(blockEntity, pos, state, recipeType);}

	public OscillatorBlockEntity(BlockPos pos, BlockState state) {this(AerialHellBlockEntities.OSCILLATOR.get(), pos, state, RecipeTypes.OSCILLATING.get());}

	@Override protected Component getDefaultName()
	{
		return Component.translatable("container." + AerialHell.MODID + ".oscillator");
	}

	@Override protected AbstractContainerMenu createMenu(int id, Inventory inv) {return new OscillatorMenu(id, inv, this, this.dataAccess);}

	@Override protected int getBurnDuration(FuelValues fuelValues, ItemStack fuel)
	{
		if (fuel.isEmpty() || !ItemHelper.getOscillatingMap().containsKey(fuel.getItem())) {return 0;}
		else {return ItemHelper.getOscillatingMap().get(fuel.getItem());}
	}
}
