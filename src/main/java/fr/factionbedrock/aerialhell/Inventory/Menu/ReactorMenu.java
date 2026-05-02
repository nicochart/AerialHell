package fr.factionbedrock.aerialhell.Inventory.Menu;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ReactorMenu extends AbstractContainerMenu
{
	private final Container container;
	private final ContainerData data;

	public ReactorMenu(int containerId, Inventory playerInventory)
	{
		this(containerId, playerInventory, new SimpleContainer(1), new SimpleContainerData(2));
	}

	public ReactorMenu(int containerId, Inventory playerInventory, Container container, ContainerData data)
	{
		super(AerialHellMenuTypes.REACTOR, containerId);
		this.container = container;
		this.data = data;
		this.addDataSlots(data);

		createPlayerHotbar(playerInventory);
		createPlayerInventory(playerInventory);
		createBlockEntityInventory(container);
	}

	public int getActivePercent() {return this.data.get(0);}
	public boolean isLightReactor() {return this.data.get(1) == 0;}

	private void createBlockEntityInventory(Container container)
	{
		addSlot(new Slot(container, 0, 80, 36)
		{
			@Override public boolean mayPlace(ItemStack stack)
			{
				return stack.is(AerialHellItems.FLUORITE) || stack.is(AerialHellItems.FLUORITE_BLOCK)
						|| stack.is(AerialHellItems.SHADOW_CRYSTAL) || stack.is(AerialHellItems.SHADOW_CRYSTAL_BLOCK) || stack.is(AerialHellItems.SHADOW_SHARD) || stack.is(AerialHellItems.CURSED_CRYSTAL) || stack.is(AerialHellItems.CURSED_CRYSAL_BLOCK);
			}
		});
	}

	private void createPlayerInventory(Container container)
	{
		for (int row = 0; row < 3; row++) for (int column = 0; column < 9; column++)
		{
			addSlot(new Slot(container, 9 + column + (row * 9), 8 + (column * 18), 84 + (row * 18)));
		}
	}

	private void createPlayerHotbar(Container container)
	{
		for (int column = 0; column < 9; column++) {addSlot(new Slot(container, column, 8 + (column * 18), 142));}
	}

	@Override public ItemStack quickMoveStack(Player player, int index)
	{
		Slot fromSlot = getSlot(index);
		ItemStack fromStack = fromSlot.getItem();

		if (fromStack.getCount() <= 0) {fromSlot.set(ItemStack.EMPTY);}
		if (!fromSlot.hasItem()) { return ItemStack.EMPTY; }

		ItemStack copyFromStack = fromStack.copy();

		if (index < 36) //player inventory
		{
			if(!moveItemStackTo(fromStack, 36, 37, false)) {return ItemStack.EMPTY;}
		}
		else if (index == 36) //reactor inventory
		{
			if(!moveItemStackTo(fromStack, 0, 36, false)) {return ItemStack.EMPTY;}
		}

		fromSlot.setChanged();
		fromSlot.onTake(player, fromStack);

		return copyFromStack;
	}

	@Override public boolean stillValid(Player player)
	{
		return this.container.stillValid(player);
	}
}