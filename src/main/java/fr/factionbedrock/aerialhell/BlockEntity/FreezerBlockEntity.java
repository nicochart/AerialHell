package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Container.FreezerMenu;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;

import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class FreezerBlockEntity extends AbstractFurnaceBlockEntity
{
	private static final Map<Item, Integer> freezingMap = Maps.newLinkedHashMap();

	protected FreezerBlockEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state, RecipeType<? extends AbstractCookingRecipe> recipeType) {super(blockEntity, pos, state, recipeType);}

	public FreezerBlockEntity(BlockPos pos, BlockState state) {this(AerialHellBlockEntities.FREEZER.get(), pos, state, RecipeTypes.FREEZING);}

	@Override protected Component getDefaultName()
	{
		return new TranslatableComponent("container." + AerialHell.MODID + ".freezer");
	}

	@Override protected AbstractContainerMenu createMenu(int id, Inventory inv) {return new FreezerMenu(id, inv, this, this.dataAccess);}

	public static Map<Item, Integer> getFreezingMap() {return freezingMap;}

	private static void addItemTagFreezingTime(Tag<Item> itemTag, int burnTimeIn)
	{
		for (Item item : itemTag.getValues())
		{
			freezingMap.put(item, burnTimeIn);
		}
	}

	public static void addItemFreezingTime(Item item, int burnTimeIn)
	{
		freezingMap.put(item, burnTimeIn);
	}

	@Override
	protected int getBurnDuration(ItemStack fuel)
	{
		if (fuel.isEmpty() || !getFreezingMap().containsKey(fuel.getItem())) {return 0;}
		else {return getFreezingMap().get(fuel.getItem());}
	}
}