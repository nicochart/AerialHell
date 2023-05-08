package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

//see class net.minecraft.tileentity.BarrelBlockEntity

public class AerialHellBarrelBlockEntity extends RandomizableContainerBlockEntity
{
	private NonNullList<ItemStack> inventory;
	private ContainerOpenersCounter openersCounter = new ContainerOpenersCounter()
	{
		protected void onOpen(Level p_155062_, BlockPos p_155063_, BlockState p_155064_)
		{
			AerialHellBarrelBlockEntity.this.playSound(p_155064_, SoundEvents.BARREL_OPEN);
			AerialHellBarrelBlockEntity.this.updateBlockState(p_155064_, true);
		}

		protected void onClose(Level p_155072_, BlockPos p_155073_, BlockState p_155074_)
		{
			AerialHellBarrelBlockEntity.this.playSound(p_155074_, SoundEvents.BARREL_CLOSE);
			AerialHellBarrelBlockEntity.this.updateBlockState(p_155074_, false);
		}

		protected void openerCountChanged(Level p_155066_, BlockPos p_155067_, BlockState p_155068_, int p_155069_, int p_155070_) {}

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

	protected AerialHellBarrelBlockEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state) {super(blockEntity, pos, state);}

	public AerialHellBarrelBlockEntity(BlockPos pos, BlockState state) {this(AerialHellBlockEntities.BARREL.get(), pos, state);}

	@Override protected Component getDefaultName() {return new TranslatableComponent("container.barrel");}

	@Override
	protected AbstractContainerMenu createMenu(int id, Inventory inv) {return ChestMenu.threeRows(id, inv, this);}

	@Override protected NonNullList<ItemStack> getItems() {return this.inventory;}
	@Override protected void setItems(NonNullList<ItemStack> itemList) {this.inventory = itemList;}
	@Override public int getContainerSize() {return 27;}

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

	void updateBlockState(BlockState p_58607_, boolean p_58608_) {this.level.setBlock(this.getBlockPos(), p_58607_.setValue(BarrelBlock.OPEN, Boolean.valueOf(p_58608_)), 3);}

	void playSound(BlockState state, SoundEvent sound)
	{
		Vec3i vec3i = state.getValue(BarrelBlock.FACING).getNormal();
		double d0 = (double)this.worldPosition.getX() + 0.5D + (double)vec3i.getX() / 2.0D;
		double d1 = (double)this.worldPosition.getY() + 0.5D + (double)vec3i.getY() / 2.0D;
		double d2 = (double)this.worldPosition.getZ() + 0.5D + (double)vec3i.getZ() / 2.0D;
		this.level.playSound((Player)null, d0, d1, d2, sound, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
	}
}