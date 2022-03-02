package fr.factionbedrock.aerialhell.TileEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Container.FreezerContainer;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellTileEntityTypes;

import com.google.common.collect.Maps;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Map;

public class FreezerTileEntity extends AbstractFurnaceTileEntity
{
	private static final Map<Item, Integer> freezingMap = Maps.newLinkedHashMap();

	public FreezerTileEntity()
	{
		super(AerialHellTileEntityTypes.FREEZER.get(), RecipeTypes.FREEZING);
	}
	
	@Override
	protected ITextComponent getDefaultName()
	{
		return new TranslationTextComponent("container." + AerialHell.MODID + ".freezer");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player)
	{
		return new FreezerContainer(id, player, this, this.furnaceData);
	}

	public static Map<Item, Integer> getFreezingMap()
	{
		return freezingMap;
	}

	private static void addItemTagFreezingTime(ITag<Item> itemTag, int burnTimeIn)
	{
		for (Item item : itemTag.getAllElements())
		{
			freezingMap.put(item, burnTimeIn);
		}
	}

	public static void addItemFreezingTime(IItemProvider itemProvider, int burnTimeIn)
	{
		Item item = itemProvider.asItem();
		freezingMap.put(item, burnTimeIn);
	}

	@Override
	protected int getBurnTime(ItemStack fuel)
	{
		if (fuel.isEmpty() || !getFreezingMap().containsKey(fuel.getItem()))
		{
			return 0;
		}
		else
		{
			return getFreezingMap().get(fuel.getItem());
		}
	}
}