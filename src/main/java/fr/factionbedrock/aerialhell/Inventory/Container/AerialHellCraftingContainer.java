package fr.factionbedrock.aerialhell.Inventory.Container;

import fr.factionbedrock.aerialhell.Block.AerialHellCraftingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.util.IWorldPosCallable;

public class AerialHellCraftingContainer extends WorkbenchContainer
{
	private IWorldPosCallable worldPosCallable;

	public AerialHellCraftingContainer(int syncid, PlayerInventory playerInv, IWorldPosCallable posCallable)
	{
		super(syncid, playerInv, posCallable);
		this.worldPosCallable = posCallable;
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn)
	{
		return worldPosCallable.applyOrElse((world, pos) ->
		{
			return !(world.getBlockState(pos).getBlock() instanceof AerialHellCraftingTableBlock) ? false : playerIn.getDistanceSq(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= 64d;
		}, true);
	}
}