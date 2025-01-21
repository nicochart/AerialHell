package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import fr.factionbedrock.aerialhell.Inventory.Menu.OscillatorMenu;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class OscillatorBlockEntity extends AbstractFurnaceBlockEntity
{
	protected OscillatorBlockEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state, RecipeType<? extends AbstractCookingRecipe> recipeType) {super(blockEntity, pos, state, recipeType);}

	public OscillatorBlockEntity(BlockPos pos, BlockState state) {this(AerialHellBlockEntities.OSCILLATOR.get(), pos, state, RecipeTypes.OSCILLATING.get());}

	@Override
	protected Component getDefaultName()
	{
		return Component.translatable("container." + AerialHell.MODID + ".oscillator");
	}

	@Override protected AbstractContainerMenu createMenu(int id, Inventory inv) {return new OscillatorMenu(id, inv, this, this.dataAccess);}

	public static Map<Item, Integer> getOscillatingMap()
	{
		Map<Item, Integer> map = Maps.newLinkedHashMap();
		map.put(AerialHellItems.FLUORITE.get(), 1200);
		map.put(AerialHellItems.FLUORITE_BLOCK.get(), 10800);
		map.put(AerialHellItems.CRYSTAL.get(), 300);
		map.put(AerialHellItems.CRYSTAL_BLOCK.get(), 1200);
		return map;
	}

	@Override
	protected int getBurnDuration(FuelValues fuelValues, ItemStack fuel)
	{
		if (fuel.isEmpty() || !getOscillatingMap().containsKey(fuel.getItem())) {return 0;}
		else {return getOscillatingMap().get(fuel.getItem());}
	}
}
