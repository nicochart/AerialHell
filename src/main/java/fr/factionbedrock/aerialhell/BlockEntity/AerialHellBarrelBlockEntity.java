package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class AerialHellBarrelBlockEntity extends RandomizableContainerBlockEntity
{
	private NonNullList<ItemStack> inventory = NonNullList.withSize(27, ItemStack.EMPTY);
	private final ContainerOpenersCounter stateManager = new ContainerOpenersCounter()
	{
		@Override protected void onOpen(Level world, BlockPos pos, BlockState state)
		{
			AerialHellBarrelBlockEntity.this.playSound(state, SoundEvents.BARREL_OPEN);
			AerialHellBarrelBlockEntity.this.setOpen(state, true);
		}

		@Override protected void onClose(Level world, BlockPos pos, BlockState state)
		{
			AerialHellBarrelBlockEntity.this.playSound(state, SoundEvents.BARREL_CLOSE);
			AerialHellBarrelBlockEntity.this.setOpen(state, false);
		}

		@Override protected void openerCountChanged(Level world, BlockPos pos, BlockState state, int oldViewerCount, int newViewerCount) {}

		@Override public boolean isOwnContainer(Player player)
		{
			if (player.containerMenu instanceof ChestMenu)
			{
				Container inventory = ((ChestMenu)player.containerMenu).getContainer();
				return inventory == AerialHellBarrelBlockEntity.this;
			}
			else {return false;}
		}
	};

	public AerialHellBarrelBlockEntity(BlockPos pos, BlockState state) {super(AerialHellBlockEntities.BARREL, pos, state);}

	@Override protected void saveAdditional(ValueOutput view)
	{
		super.saveAdditional(view);
		if (!this.trySaveLootTable(view)) {ContainerHelper.saveAllItems(view, this.inventory);}
	}

	@Override protected void loadAdditional(ValueInput view)
	{
		super.loadAdditional(view);
		this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(view)) {ContainerHelper.loadAllItems(view, this.inventory);}
	}

	@Override public int getContainerSize() {return 27;}

	@Override protected NonNullList<ItemStack> getItems() {return this.inventory;}

	@Override protected void setItems(NonNullList<ItemStack> inventory) {this.inventory = inventory;}

	@Override protected Component getDefaultName() {return Component.translatable("container.barrel");}

	@Override protected AbstractContainerMenu createMenu(int syncId, Inventory playerInventory)
	{
		return ChestMenu.threeRows(syncId, playerInventory, this);
	}

	@Override public void startOpen(ContainerUser user)
	{
		if (!this.remove && !user.getLivingEntity().isSpectator()) {this.stateManager.incrementOpeners(user.getLivingEntity(), this.getLevel(), this.getBlockPos(), this.getBlockState(), user.getContainerInteractionRange());}

	}

	@Override public void stopOpen(ContainerUser user)
	{
		if (!this.remove && !user.getLivingEntity().isSpectator()) {this.stateManager.decrementOpeners(user.getLivingEntity(), this.getLevel(), this.getBlockPos(), this.getBlockState());}
	}

	public void recheckOpen()
	{
		if (!this.remove) {this.stateManager.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());}
	}

	void setOpen(BlockState state, boolean open)
	{
		this.level.setBlock(this.getBlockPos(), state.setValue(BarrelBlock.OPEN, Boolean.valueOf(open)), Block.UPDATE_ALL);
	}

	void playSound(BlockState state, SoundEvent soundEvent)
	{
		Vec3i vec3i = ((Direction)state.getValue(BarrelBlock.FACING)).getUnitVec3i();
		double d = (double)this.worldPosition.getX() + 0.5 + (double)vec3i.getX() / 2.0;
		double e = (double)this.worldPosition.getY() + 0.5 + (double)vec3i.getY() / 2.0;
		double f = (double)this.worldPosition.getZ() + 0.5 + (double)vec3i.getZ() / 2.0;
		this.level.playSound(null, d, e, f, soundEvent, SoundSource.BLOCKS, 0.5F, this.level.getRandom().nextFloat() * 0.1F + 0.9F);
	}
}