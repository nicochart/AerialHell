package fr.factionbedrock.aerialhell.TileEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import fr.factionbedrock.aerialhell.Inventory.Container.VibratorContainer;

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

public class VibratorTileEntity extends AbstractFurnaceTileEntity
{
	private static final Map<Item, Integer> vibrationMap = Maps.newLinkedHashMap();

	public VibratorTileEntity()
	{
		super(AerialHellTileEntityTypes.VIBRATOR.get(), RecipeTypes.VIBRATION);
	}

	@Override
	protected ITextComponent getDefaultName()
	{
		return new TranslationTextComponent("container." + AerialHell.MODID + ".vibrator");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player)
	{
		return new VibratorContainer(id, player, this, this.furnaceData);
	}

	public static Map<Item, Integer> getVibrationMap()
	{
		return vibrationMap;
	}

	private static void addItemTagVibrationTime(ITag<Item> itemTag, int burnTimeIn)
	{
		for (Item item : itemTag.getAllElements())
		{
			vibrationMap.put(item, burnTimeIn);
		}
	}

	public static void addItemVibrationTime(IItemProvider itemProvider, int burnTimeIn)
	{
		Item item = itemProvider.asItem();
		vibrationMap.put(item, burnTimeIn);
	}

	@Override
	protected int getBurnTime(ItemStack fuel)
	{
		if (fuel.isEmpty() || !getVibrationMap().containsKey(fuel.getItem()))
		{
			return 0;
		}
		else
		{
			return getVibrationMap().get(fuel.getItem());
		}
	}
}
