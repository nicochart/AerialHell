package fr.factionbedrock.aerialhell.Inventory.Menu;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

public class ReactorMenu extends AbstractContainerMenu
{
	private final Container container;

	public ReactorMenu(int containerId, Inventory playerInventory)
	{
		this(containerId, playerInventory, new SimpleContainer(1));
	}

	public ReactorMenu(int containerId, Inventory playerInventory, Container container)
	{
		super(AerialHellMenuTypes.REACTOR.get(), containerId);
		this.container = container;

		createPlayerHotbar(playerInventory);
		createPlayerInventory(playerInventory);
		createBlockEntityInventory(container);
	}

	private void createBlockEntityInventory(Container container)
	{
		addSlot(new Slot(container,0,80,36)
		{
			@Override public boolean mayPlace(ItemStack stack)
			{
				return stack.is(AerialHellItems.FLUORITE.get()) || stack.is(AerialHellItems.FLUORITE_BLOCK.get())
					|| stack.is(AerialHellItems.SHADOW_CRYSTAL.get()) || stack.is(AerialHellItems.SHADOW_SHARD.get()) || stack.is(AerialHellItems.CURSED_CRYSAL.get()) || stack.is(AerialHellItems.CURSED_CRYSAL_BLOCK.get());
			}
		});
	}

	private void createPlayerInventory(Container container)
	{
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				addSlot(new Slot(container,
						9 + column + (row * 9),
						8 + (column * 18),
						84 + (row * 18)));
			}
		}
	}

	private void createPlayerHotbar(Container container)
	{
		for (int column = 0; column < 9; column++) {
			addSlot(new Slot(container,
					column,
					8 + (column * 18),
					142));
		}
	}

	@Override public ItemStack quickMoveStack(Player player, int index)
	{
		Slot fromSlot = getSlot(index);
		ItemStack fromStack = fromSlot.getItem();

		if (fromStack.getCount() <= 0) {fromSlot.set(ItemStack.EMPTY);}
		if (!fromSlot.hasItem()) {return ItemStack.EMPTY;}

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
