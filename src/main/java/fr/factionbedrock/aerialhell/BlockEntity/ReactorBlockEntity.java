package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.ReactorBlock;
import fr.factionbedrock.aerialhell.Inventory.Menu.ReactorMenu;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ReactorBlockEntity extends BaseContainerBlockEntity implements BiomeShifter
{
    protected NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    public static int MAX_PROTECTION_DISTANCE = 100;
    public static int FACTOR = 10;
    public static int MAX_ACTIVE_TIMER = 500000;
    private int activeTimer;
    private int fieldSize;
    private ShiftType shiftType;
    @Nullable private final Supplier<Block> shiftedOrBrokenVariant;

    protected final ContainerData containerData = new ContainerData()
    {
        @Override public int get(int index)
        {
            return switch (index)
            {
                case 0 -> (int) (ReactorBlockEntity.this.getActiveTimerProportion() * 100.0F);
                case 1 -> (ReactorBlockEntity.this.shiftType == ShiftType.UNCORRUPT) ? 0 : 1;
                default -> 0;
            };
        }

        @Override public void set(int index, int value)
        {

        }

        @Override public int getCount() {return 2;}
    };

    public ReactorBlockEntity(BlockPos pos, BlockState blockState, int fieldSize, ShiftType shiftType, @Nullable Supplier<Block> shiftedOrBrokenVariant)
    {
        super(AerialHellBlockEntities.REACTOR.get(), pos, blockState);
        this.fieldSize = fieldSize;
        this.shiftType = shiftType;
        this.shiftedOrBrokenVariant = shiftedOrBrokenVariant;
        this.activeTimer = 0;
    }

    @Override public int getFieldSize() {return (int) (Mth.clamp(this.getActiveTimerProportion() + 0.25F, 0.25F, 1.0F) * this.fieldSize);}
    @Override public ShiftType getShiftType() {return this.shiftType;}
    @Override @Nullable public Supplier<Block> getShiftedOrBrokenVariant() {return this.shiftedOrBrokenVariant;}
    public int getActiveTimer() {return activeTimer;}
    public float getActiveTimerProportion() {return (float) this.activeTimer / (float) MAX_ACTIVE_TIMER;}

    @Override @NotNull protected Component getDefaultName()
    {
        String type = this.shiftType == ShiftType.UNCORRUPT ? "light" : "shadow";
        return Component.translatable("container." + AerialHell.MODID + "." + type + "_reactor");
    }

    @Override protected AbstractContainerMenu createMenu(int id, @NotNull Inventory inv) {return new ReactorMenu(id, inv, this, this.containerData);}

    public static void tick(Level level, BlockPos pos, BlockState state, ReactorBlockEntity blockEntity)
    {
        boolean isActive = blockEntity.updateActiveTimer();
        if (state.getValue(ReactorBlock.ACTIVE) != isActive) {level.setBlockAndUpdate(pos, state.setValue(ReactorBlock.ACTIVE, isActive));}

        BiomeShifter.transformRandomBlocks(level, pos, state, blockEntity);
        ReactorBlock.tickParticleAndSoundAnimation((ServerLevel) level, state, pos, level.random, blockEntity.shiftType);
    }

    public boolean updateActiveTimer() //returns true if isActive, else false
    {
        boolean isActive = this.activeTimer > 0;
        if (isActive) {this.activeTimer--;}

        if (!this.items.isEmpty() && this.activeTimer < MAX_ACTIVE_TIMER)
        {
            ItemStack stack = this.items.get(0);

            if (this.shiftType == ShiftType.UNCORRUPT)
            {
                if (ItemHelper.getOscillatingMap().containsKey(stack.getItem()))
                {
                    this.activeTimer += FACTOR * ItemHelper.getOscillatingMap().get(stack.getItem());
                    stack.shrink(1);
                    isActive = true;
                }
            }
            else
            {
                if (ItemHelper.getCorruptingMap().containsKey(stack.getItem()))
                {
                    this.activeTimer += FACTOR * ItemHelper.getCorruptingMap().get(stack.getItem());
                    stack.shrink(1);
                    isActive = true;
                }
            }
        }
        return isActive;
    }

    @Override protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, this.items);
        tag.putInt("field_size", fieldSize);
        tag.putInt("active_timer", activeTimer);
        boolean isShadow = this.shiftType == ShiftType.CORRUPT;
        tag.putBoolean("is_shadow", isShadow);
    }

    @Override public void load(CompoundTag tag)
    {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);
        this.fieldSize = tag.getInt("field_size");
        this.activeTimer = tag.getInt("active_timer");
        boolean isShadow = tag.getBoolean("is_shadow");
        this.shiftType = isShadow ? ShiftType.CORRUPT : ShiftType.UNCORRUPT;
    }

    public NonNullList<ItemStack> getItems() {return this.items;}
    protected void setItems(NonNullList<ItemStack> items) {this.items = items;}
    @Override public int getContainerSize() {return this.items.size();}

    @Override public boolean isEmpty()
    {
        for (ItemStack stack : this.getItems())
        {
            if (!stack.isEmpty()) {return false;}
        }
        return true;
    }

    @Override public ItemStack getItem(int slot)
    {
        return this.getItems().get(slot);
    }

    @Override public ItemStack removeItem(int slot, int amount)
    {
        ItemStack result = ContainerHelper.removeItem(this.getItems(), slot, amount);
        if (!result.isEmpty())
        {
            this.setChanged();
        }
        return result;
    }

    @Override public ItemStack removeItemNoUpdate(int slot)
    {
        return ContainerHelper.takeItem(this.getItems(), slot);
    }

    @Override public void setItem(int slot, ItemStack stack)
    {
        this.getItems().set(slot, stack);
        if (stack.getCount() > this.getMaxStackSize())
        {
            stack.setCount(this.getMaxStackSize());
        }
        this.setChanged();
    }

    @Override public boolean stillValid(Player player) {return Container.stillValidBlockEntity(this, player);}
    @Override public void clearContent() {this.getItems().clear();}
}
