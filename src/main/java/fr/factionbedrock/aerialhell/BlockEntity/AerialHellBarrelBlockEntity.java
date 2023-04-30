package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
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
	private int viewerCount;

	protected AerialHellBarrelBlockEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state) {super(blockEntity, pos, state);}

	public AerialHellBarrelBlockEntity(BlockPos pos, BlockState state) {this(AerialHellBlockEntities.BARREL.get(), pos, state);}

	@Override protected Component getDefaultName() {return new TranslatableComponent("container.barrel");}

	@Override
	protected AbstractContainerMenu createMenu(int id, Inventory inv) {return ChestMenu.threeRows(id, inv, this);}

	@Override protected NonNullList<ItemStack> getItems() {return this.inventory;}
	@Override protected void setItems(NonNullList<ItemStack> itemList) {this.inventory = itemList;}
	@Override public int getContainerSize() {return 27;}


	/*private AerialHellBarrelBlockEntity(BlockEntityType<?> type)
	{
		super(type);
		this.inventory = NonNullList.withSize(27, ItemStack.EMPTY);
	}

	public AerialHellBarrelBlockEntity()
	{
		this();
	}

	@Override
	public CompoundNBT write(CompoundNBT compound)
	{
		super.write(compound);
		if (!this.checkLootAndWrite(compound))
		{
			ItemStackHelper.saveAllItems(compound, this.inventory);
		}

		return compound;
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt)
	{
		super.read(state, nbt);
		this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		if (!this.checkLootAndRead(nbt))
		{
			ItemStackHelper.loadAllItems(nbt, this.inventory);
		}
	}

	@Override
	public int getSizeInventory()
	{
		return 27;
	}

	@Override
	protected NonNullList<ItemStack> getItems()
	{
		return this.inventory;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> list)
	{
		this.inventory = list;
	}

	@Override
	protected Component getDefaultName()
	{

	}

	@Override
	protected Container createMenu(int syncId, PlayerInventory playerInventory)
	{
		return ChestContainer.createGeneric9X3(syncId, playerInventory, this);
	}

	@Override
	public void openInventory(Player player)
	{
		if (!player.isSpectator())
		{
			if (this.viewerCount < 0)
			{
				this.viewerCount = 0;
			}
			++this.viewerCount;
			BlockState blockState = this.getBlockState();
			boolean bl = (Boolean) blockState.get(BarrelBlock.PROPERTY_OPEN);
			if (!bl)
			{
				this.playSound(blockState, SoundEvents.BLOCK_BARREL_OPEN);
				this.setOpen(blockState, true);
			}
			this.scheduleTick();
		}
	}

	private void scheduleTick()
	{
		this.world.getPendingBlockTicks().scheduleTick(this.getPos(), this.getBlockState().getBlock(), 5);
	}

	public void tick()
	{
		int i = this.pos.getX();
		int j = this.pos.getY();
		int k = this.pos.getZ();
		this.viewerCount = ChestBlockEntity.calculatePlayersUsing(this.world, this, i, j, k);
		if (this.viewerCount > 0)
		{
			this.scheduleTick();
		}
		else
		{
			BlockState blockState = this.getBlockState();
			if (!(blockState.getBlock() instanceof AerialHellBarrelBlock))
			{
				this.remove();
				return;
			}

			boolean bl = (Boolean) blockState.get(BarrelBlock.PROPERTY_OPEN);
			if (bl)
			{
				this.playSound(blockState, SoundEvents.BLOCK_BARREL_CLOSE);
				this.setOpen(blockState, false);
			}
		}
	}

	@Override
	public void closeInventory(Player player)
	{
		if (!player.isSpectator())
		{
			--this.viewerCount;
		}
	}

	private void setOpen(BlockState state, boolean open)
	{
		this.world.setBlockState(this.getPos(), (BlockState) state.setValue(BarrelBlock.PROPERTY_OPEN, open), 3);
	}

	private void playSound(BlockState blockState, SoundEvent soundEvent)
	{
		Vector3i vec3i = ((Direction) blockState.get(BarrelBlock.PROPERTY_FACING)).getDirectionVec();
		double d = (double) this.pos.getX() + 0.5D + (double) vec3i.getX() / 2.0D;
		double e = (double) this.pos.getY() + 0.5D + (double) vec3i.getY() / 2.0D;
		double f = (double) this.pos.getZ() + 0.5D + (double) vec3i.getZ() / 2.0D;
		this.world.playSound((Player) null, d, e, f, soundEvent, SoundSource.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
	}*/
}