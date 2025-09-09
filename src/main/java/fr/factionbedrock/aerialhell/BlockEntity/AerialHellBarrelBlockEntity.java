package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class AerialHellBarrelBlockEntity extends RandomizableContainerBlockEntity
{
	private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
	private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter()
	{
		protected void onOpen(Level level, BlockPos pos, BlockState state)
		{
			AerialHellBarrelBlockEntity.this.playSound(state, SoundEvents.BARREL_OPEN);
			AerialHellBarrelBlockEntity.this.updateBlockState(state, true);
		}

		protected void onClose(Level level, BlockPos pos, BlockState state)
		{
			AerialHellBarrelBlockEntity.this.playSound(state, SoundEvents.BARREL_CLOSE);
			AerialHellBarrelBlockEntity.this.updateBlockState(state, false);
		}

		protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int p_155069_, int p_155070_) {}

		protected boolean isOwnContainer(Player p_155060_)
		{
			if (p_155060_.containerMenu instanceof ChestMenu)
			{
				Container container = ((ChestMenu)p_155060_.containerMenu).getContainer();
				return container == AerialHellBarrelBlockEntity.this;
			}
			else {return false;}
		}
	};

	public AerialHellBarrelBlockEntity(BlockPos pos, BlockState state) {super(AerialHellBlockEntities.BARREL.get(), pos, state);}

	@Override protected void saveAdditional(ValueOutput valueOutput)
	{
		super.saveAdditional(valueOutput);
		if (!this.trySaveLootTable(valueOutput)) {ContainerHelper.saveAllItems(valueOutput, this.items);}
	}

	@Override protected void loadAdditional(ValueInput valueInput)
	{
		super.loadAdditional(valueInput);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(valueInput)) {ContainerHelper.loadAllItems(valueInput, this.items);}
	}

	@Override public int getContainerSize() {return 27;}

	@Override protected NonNullList<ItemStack> getItems() {return this.items;}

	@Override protected void setItems(NonNullList<ItemStack> itemStackList) {this.items = itemStackList;}

	@Override protected Component getDefaultName() {return Component.translatable("container.barrel");}

	@Override protected AbstractContainerMenu createMenu(int p_58598_, Inventory inv) {return ChestMenu.threeRows(p_58598_, inv, this);}

	@Override public void startOpen(Player player)
	{
		if (!this.remove && !player.isSpectator()) {this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());}
	}

	@Override public void stopOpen(Player player)
	{
		if (!this.remove && !player.isSpectator()) {this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());}
	}

	public void recheckOpen()
	{
		if (!this.remove) {this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());}
	}

	void updateBlockState(BlockState state, boolean bool)
	{
		this.level.setBlock(this.getBlockPos(), state.setValue(BarrelBlock.OPEN, Boolean.valueOf(bool)), 3);
	}

	void playSound(BlockState state, SoundEvent event)
	{
		Vec3i vec3i = state.getValue(BarrelBlock.FACING).getUnitVec3i();
		double d0 = (double)this.worldPosition.getX() + 0.5D + (double)vec3i.getX() / 2.0D;
		double d1 = (double)this.worldPosition.getY() + 0.5D + (double)vec3i.getY() / 2.0D;
		double d2 = (double)this.worldPosition.getZ() + 0.5D + (double)vec3i.getZ() / 2.0D;
		this.level.playSound((Player)null, d0, d1, d2, event, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
	}
}