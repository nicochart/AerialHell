package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.OscillatorMenu;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import com.google.common.collect.Maps;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.Map;

public class OscillatorBlockEntity extends AbstractFurnaceBlockEntity
{
	protected OscillatorBlockEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state, RecipeType<? extends AbstractCookingRecipe> recipeType) {super(blockEntity, pos, state, recipeType);}

	public OscillatorBlockEntity(BlockPos pos, BlockState state) {this(AerialHellBlockEntities.OSCILLATOR, pos, state, RecipeTypes.OSCILLATING);}

	@Override protected Text getContainerName()
	{
		return Text.translatable("container." + AerialHell.MODID + ".oscillator");
	}

	@Override protected ScreenHandler createScreenHandler(int id, PlayerInventory inv) {return new OscillatorMenu(id, inv, this, this.propertyDelegate);}

	public static Map<Item, Integer> getOscillatingMap()
	{
		Map<Item, Integer> map = Maps.newLinkedHashMap();
		map.put(AerialHellItems.FLUORITE, 1200);
		map.put(AerialHellItems.FLUORITE_BLOCK, 10800);
		map.put(AerialHellItems.CRYSTAL, 300);
		map.put(AerialHellItems.CRYSTAL_BLOCK, 1200);
		return map;
	}

	@Override
	protected int getFuelTime(ItemStack fuel)
	{
		if (fuel.isEmpty() || !getOscillatingMap().containsKey(fuel.getItem())) {return 0;}
		else {return getOscillatingMap().get(fuel.getItem());}
	}
}
