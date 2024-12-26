package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.FreezerMenu;
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

public class FreezerBlockEntity extends AbstractFurnaceBlockEntity
{
	protected FreezerBlockEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state, RecipeType<? extends AbstractCookingRecipe> recipeType) {super(blockEntity, pos, state, recipeType);}

	public FreezerBlockEntity(BlockPos pos, BlockState state) {this(AerialHellBlockEntities.FREEZER, pos, state, RecipeTypes.FREEZING);}

	@Override protected Text getContainerName()
	{
		return Text.translatable("container." + AerialHell.MODID + ".freezer");
	}

	@Override protected ScreenHandler createScreenHandler(int id, PlayerInventory inv) {return new FreezerMenu(id, inv, this, this.propertyDelegate);}

	public static Map<Item, Integer> getFreezingMap()
	{
		Map<Item, Integer> map = Maps.newLinkedHashMap();
		map.put(AerialHellItems.MAGMATIC_GEL, 600);
		map.put(AerialHellItems.MAGMATIC_GEL_BLOCK, 5400);
		return map;
	}

	@Override
	protected int getFuelTime(ItemStack fuel)
	{
		if (fuel.isEmpty() || !getFreezingMap().containsKey(fuel.getItem())) {return 0;}
		else {return getFreezingMap().get(fuel.getItem());}
	}
}