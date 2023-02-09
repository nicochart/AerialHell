package fr.factionbedrock.aerialhell.TileEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import fr.factionbedrock.aerialhell.Inventory.Container.OscillatorContainer;

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

public class OscillatorTileEntity extends AbstractFurnaceTileEntity
{
	private static final Map<Item, Integer> oscillatingMap = Maps.newLinkedHashMap();

	public OscillatorTileEntity()
	{
		super(AerialHellTileEntityTypes.OSCILLATOR.get(), RecipeTypes.OSCILLATING);
	}

	@Override
	protected ITextComponent getDefaultName()
	{
		return new TranslationTextComponent("container." + AerialHell.MODID + ".oscillator");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player)
	{
		return new OscillatorContainer(id, player, this, this.furnaceData);
	}

	public static Map<Item, Integer> getOscillatingMap()
	{
		return oscillatingMap;
	}

	private static void addItemTagOscillatingTime(ITag<Item> itemTag, int burnTimeIn)
	{
		for (Item item : itemTag.getAllElements())
		{
			oscillatingMap.put(item, burnTimeIn);
		}
	}

	public static void addItemOscillatingTime(IItemProvider itemProvider, int burnTimeIn)
	{
		Item item = itemProvider.asItem();
		oscillatingMap.put(item, burnTimeIn);
	}

	@Override
	protected int getBurnTime(ItemStack fuel)
	{
		if (fuel.isEmpty() || !getOscillatingMap().containsKey(fuel.getItem())) {return 0;}
		else {return getOscillatingMap().get(fuel.getItem());}
	}
}
