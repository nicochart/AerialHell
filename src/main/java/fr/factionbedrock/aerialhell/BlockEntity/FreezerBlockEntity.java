package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.FreezerMenu;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;

import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class FreezerBlockEntity extends AbstractFurnaceBlockEntity
{
	protected FreezerBlockEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state, RecipeType<? extends AbstractCookingRecipe> recipeType) {super(blockEntity, pos, state, recipeType);}

	public FreezerBlockEntity(BlockPos pos, BlockState state) {this(AerialHellBlockEntities.FREEZER.get(), pos, state, RecipeTypes.FREEZING.get());}

	@Override protected Component getDefaultName()
	{
		return Component.translatable("container." + AerialHell.MODID + ".freezer");
	}

	@Override protected AbstractContainerMenu createMenu(int id, Inventory inv) {return new FreezerMenu(id, inv, this, this.dataAccess);}

	public static Map<Item, Integer> getFreezingMap()
	{
		Map<Item, Integer> map = Maps.newLinkedHashMap();
		map.put(AerialHellBlocksAndItems.MAGMATIC_GEL.get(), 600);
		map.put(AerialHellBlocksAndItems.MAGMATIC_GEL_BLOCK_ITEM.get(), 5400);
		return map;
	}

	@Override
	protected int getBurnDuration(FuelValues fuelValues, ItemStack fuel)
	{
		if (fuel.isEmpty() || !getFreezingMap().containsKey(fuel.getItem())) {return 0;}
		else {return getFreezingMap().get(fuel.getItem());}
	}
}