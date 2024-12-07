package fr.factionbedrock.aerialhell.Inventory.Menu;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class ReactorMenu extends ScreenHandler
{
	private final Inventory container;

	public ReactorMenu(int containerId, PlayerInventory playerInventory)
	{
		this(containerId, playerInventory, new SimpleInventory(1));
	}

	public ReactorMenu(int containerId, PlayerInventory playerInventory, SimpleInventory container)
	{
		super(AerialHellMenuTypes.REACTOR, containerId);
		this.container = container;

		createPlayerHotbar(playerInventory);
		createPlayerInventory(playerInventory);
		createBlockEntityInventory(container);
	}

	private void createBlockEntityInventory(Inventory container)
	{
		addSlot(new Slot(container,0,80,36)
		{
			@Override public boolean canInsert(ItemStack stack)
			{
				return stack.isOf(AerialHellItems.FLUORITE) || stack.isOf(AerialHellItems.FLUORITE_BLOCK_ITEM)
					|| stack.isOf(AerialHellItems.SHADOW_CRYSTAL) || stack.isOf(AerialHellItems.SHADOW_SHARD) || stack.isOf(AerialHellItems.CURSED_CRYSAL) || stack.isOf(AerialHellItems.CURSED_CRYSAL_BLOCK_ITEM);
			}
		});
	}

	private void createPlayerInventory(Inventory container)
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

	private void createPlayerHotbar(Inventory container)
	{
		for (int column = 0; column < 9; column++) {
			addSlot(new Slot(container,
					column,
					8 + (column * 18),
					142));
		}
	}

	@Override public ItemStack quickMove(PlayerEntity player, int index)
	{
		Slot fromSlot = getSlot(index);
		ItemStack fromStack = fromSlot.getStack();

		if (fromStack.getCount() <= 0) {fromSlot.setStack(ItemStack.EMPTY);}
		if (!fromSlot.hasStack()) {return ItemStack.EMPTY;}

		ItemStack copyFromStack = fromStack.copy();

		if (index < 36) //player inventory
		{
			if(!insertItem(fromStack, 36, 37, false)) {return ItemStack.EMPTY;}
		}
		else if (index == 36) //reactor inventory
		{
			if(!insertItem(fromStack, 0, 36, false)) {return ItemStack.EMPTY;}
		}

		fromSlot.markDirty();
		fromSlot.onTakeItem(player, fromStack);

		return copyFromStack;
	}

	@Override public boolean canUse(PlayerEntity player)
	{
		return this.container.canPlayerUse(player);
	}
}
