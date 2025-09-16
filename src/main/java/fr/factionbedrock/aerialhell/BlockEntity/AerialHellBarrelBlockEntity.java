package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.entity.ViewerCountManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class AerialHellBarrelBlockEntity extends LootableContainerBlockEntity
{
	private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(27, ItemStack.EMPTY);
	private final ViewerCountManager stateManager = new ViewerCountManager()
	{
		@Override protected void onContainerOpen(World world, BlockPos pos, BlockState state)
		{
			AerialHellBarrelBlockEntity.this.playSound(state, SoundEvents.BLOCK_BARREL_OPEN);
			AerialHellBarrelBlockEntity.this.setOpen(state, true);
		}

		@Override protected void onContainerClose(World world, BlockPos pos, BlockState state)
		{
			AerialHellBarrelBlockEntity.this.playSound(state, SoundEvents.BLOCK_BARREL_CLOSE);
			AerialHellBarrelBlockEntity.this.setOpen(state, false);
		}

		@Override protected void onViewerCountUpdate(World world, BlockPos pos, BlockState state, int oldViewerCount, int newViewerCount) {}

		@Override protected boolean isPlayerViewing(PlayerEntity player)
		{
			if (player.currentScreenHandler instanceof GenericContainerScreenHandler)
			{
				Inventory inventory = ((GenericContainerScreenHandler)player.currentScreenHandler).getInventory();
				return inventory == AerialHellBarrelBlockEntity.this;
			}
			else {return false;}
		}
	};

	public AerialHellBarrelBlockEntity(BlockPos pos, BlockState state) {super(AerialHellBlockEntities.BARREL, pos, state);}

	@Override protected void writeData(WriteView view)
	{
		super.writeData(view);
		if (!this.writeLootTable(view)) {Inventories.writeData(view, this.inventory);}
	}

	@Override protected void readData(ReadView view)
	{
		super.readData(view);
		this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
		if (!this.readLootTable(view)) {Inventories.readData(view, this.inventory);}
	}

	@Override public int size() {return 27;}

	@Override protected DefaultedList<ItemStack> getHeldStacks() {return this.inventory;}

	@Override protected void setHeldStacks(DefaultedList<ItemStack> inventory) {this.inventory = inventory;}

	@Override protected Text getContainerName() {return Text.translatable("container.barrel");}

	@Override protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory)
	{
		return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this);
	}

	@Override public void onOpen(PlayerEntity player)
	{
		if (!this.removed && !player.isSpectator()) {this.stateManager.openContainer(player, this.getWorld(), this.getPos(), this.getCachedState());}
	}

	@Override public void onClose(PlayerEntity player)
	{
		if (!this.removed && !player.isSpectator()) {this.stateManager.closeContainer(player, this.getWorld(), this.getPos(), this.getCachedState());}
	}

	public void recheckOpen()
	{
		if (!this.removed) {this.stateManager.updateViewerCount(this.getWorld(), this.getPos(), this.getCachedState());}
	}

	void setOpen(BlockState state, boolean open)
	{
		this.world.setBlockState(this.getPos(), state.with(BarrelBlock.OPEN, Boolean.valueOf(open)), Block.NOTIFY_ALL);
	}

	void playSound(BlockState state, SoundEvent soundEvent)
	{
		Vec3i vec3i = ((Direction)state.get(BarrelBlock.FACING)).getVector();
		double d = (double)this.pos.getX() + 0.5 + (double)vec3i.getX() / 2.0;
		double e = (double)this.pos.getY() + 0.5 + (double)vec3i.getY() / 2.0;
		double f = (double)this.pos.getZ() + 0.5 + (double)vec3i.getZ() / 2.0;
		this.world.playSound(null, d, e, f, soundEvent, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
	}
}