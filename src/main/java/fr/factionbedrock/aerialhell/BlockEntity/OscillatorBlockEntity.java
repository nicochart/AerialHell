package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import fr.factionbedrock.aerialhell.Inventory.Container.OscillatorMenu;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class OscillatorBlockEntity extends AbstractFurnaceBlockEntity
{
	private static final Map<Item, Integer> oscillatingMap = Maps.newLinkedHashMap();

	protected OscillatorBlockEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state, RecipeType<? extends AbstractCookingRecipe> recipeType) {super(blockEntity, pos, state, recipeType);}

	public OscillatorBlockEntity(BlockPos pos, BlockState state) {this(AerialHellBlockEntities.OSCILLATOR.get(), pos, state, RecipeTypes.OSCILLATING);}

	@Override
	protected Component getDefaultName()
	{
		return new TranslatableComponent("container." + AerialHell.MODID + ".oscillator");
	}

	@Override protected AbstractContainerMenu createMenu(int id, Inventory inv) {return new OscillatorMenu(id, inv, this, this.dataAccess);}

	public static Map<Item, Integer> getOscillatingMap()
	{
		return oscillatingMap;
	}

	private static void addItemTagOscillatingTime(Tag<Item> itemTag, int burnTimeIn)
	{
		for (Item item : itemTag.getValues())
		{
			oscillatingMap.put(item, burnTimeIn);
		}
	}

	public static void addItemOscillatingTime(Item item, int burnTimeIn)
	{
		oscillatingMap.put(item, burnTimeIn);
	}

	@Override
	protected int getBurnDuration(ItemStack fuel)
	{
		if (fuel.isEmpty() || !getOscillatingMap().containsKey(fuel.getItem())) {return 0;}
		else {return getOscillatingMap().get(fuel.getItem());}
	}
}
