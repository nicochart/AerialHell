package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.ReactorBlock;
import fr.factionbedrock.aerialhell.Inventory.Menu.ReactorMenu;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
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
    public static int MAX_ACTIVE_TIMER = 500000;
    private int activeTimer;
    private int fieldSize;
    private ShiftType shiftType;
    @Nullable private Supplier<Block> shiftedOrBrokenVariant;

    public ReactorBlockEntity(BlockPos pos, BlockState blockState, int fieldSize, ShiftType shiftType, @Nullable Supplier<Block> shiftedOrBrokenVariant)
    {
        super(AerialHellBlockEntities.REACTOR.get(), pos, blockState);
        this.fieldSize = fieldSize;
        this.shiftType = shiftType;
        this.shiftedOrBrokenVariant = shiftedOrBrokenVariant;
        this.activeTimer = 0;
    }

    @Override public int getFieldSize() {return this.activeTimer > 0 ? this.fieldSize : (int) (0.25F * this.fieldSize);}
    @Override public ShiftType getShiftType() {return this.shiftType;}
    @Override @Nullable public Supplier<Block> getShiftedOrBrokenVariant() {return this.shiftedOrBrokenVariant;}
    public int getActiveTimer() {return activeTimer;}

    @Override @NotNull protected Component getDefaultName()
    {
        String type = this.shiftType == ShiftType.UNCORRUPT ? "light" : "shadow";
        return Component.translatable("container." + AerialHell.MODID + "." + type + "_reactor");
    }

    @Override protected AbstractContainerMenu createMenu(int id, @NotNull Inventory inv) {return new ReactorMenu(id, inv, this);}

    public static void tick(Level level, BlockPos pos, BlockState state, ReactorBlockEntity blockEntity)
    {
        boolean isActive = blockEntity.updateActiveTimer();
        if (state.getValue(ReactorBlock.ACTIVE) != isActive) {level.setBlockAndUpdate(pos, state.setValue(ReactorBlock.ACTIVE, isActive));}

        BiomeShifter.transformRandomBlocks(level, pos, state, blockEntity);
        ReactorBlock.tickParticleAndSoundAnimation((ServerLevel) level, state, pos, level.random, blockEntity.shiftType);
    }

    public boolean updateActiveTimer() //returns true if isActive, else false
    {
        int active_factor = 10;
        boolean isActive = this.activeTimer > 0;
        if (isActive) {this.activeTimer--;}

        if (!this.items.isEmpty())
        {
            ItemStack stack = this.items.get(0);

            if (this.shiftType == ShiftType.UNCORRUPT)
            {
                if (OscillatorBlockEntity.getOscillatingMap().containsKey(stack.getItem()))
                {
                    int activeTimerIncrement = active_factor * OscillatorBlockEntity.getOscillatingMap().get(stack.getItem());
                    if (this.activeTimer + activeTimerIncrement <= MAX_ACTIVE_TIMER)
                    {
                        this.activeTimer += activeTimerIncrement;
                        stack.shrink(1);
                        isActive = true;
                    }
                }
            }
            else
            {
                int activeTimerIncrement = 0;
                if (stack.is(AerialHellBlocksAndItems.SHADOW_CRYSTAL)) {activeTimerIncrement = 400 * active_factor;}
                else if (stack.is(AerialHellBlocksAndItems.SHADOW_SHARD)) {activeTimerIncrement = 1000 * active_factor;}
                else if (stack.is(AerialHellBlocksAndItems.CURSED_CRYSAL)) {activeTimerIncrement = 2000 * active_factor;}
                else if (stack.is(AerialHellBlocksAndItems.CURSED_CRYSAL_BLOCK_ITEM)) {activeTimerIncrement = 18000 * active_factor;}
                if (activeTimerIncrement > 0 && this.activeTimer + activeTimerIncrement <= MAX_ACTIVE_TIMER)
                {
                    this.activeTimer += activeTimerIncrement;
                    stack.shrink(1);
                    isActive = true;
                }
            }
        }

        return isActive;
    }

    @Override protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, this.items, registries);
        tag.putInt("field_size", fieldSize);
        tag.putInt("active_timer", activeTimer);
        boolean isShadow = this.shiftType == ShiftType.CORRUPT;
        tag.putBoolean("is_shadow", isShadow);
    }

    @Override protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.loadAdditional(tag, registries);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items, registries);
        this.fieldSize = tag.getInt("field_size");
        this.activeTimer = tag.getInt("active_timer");
        boolean isShadow = tag.getBoolean("is_shadow");
        this.shiftType = isShadow ? ShiftType.CORRUPT : ShiftType.UNCORRUPT;
    }

    @Override public NonNullList<ItemStack> getItems() {return this.items;}
    @Override protected void setItems(NonNullList<ItemStack> items) {this.items = items;}
    @Override public int getContainerSize() {return this.items.size();}
}
