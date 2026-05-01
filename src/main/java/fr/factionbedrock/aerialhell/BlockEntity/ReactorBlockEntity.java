package fr.factionbedrock.aerialhell.BlockEntity;

import com.google.common.collect.Maps;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.ReactorBlock;
import fr.factionbedrock.aerialhell.Inventory.Menu.ReactorMenu;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Supplier;

public class ReactorBlockEntity extends BaseContainerBlockEntity implements BiomeShifter
{
    protected NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    public static int MAX_PROTECTION_DISTANCE = 100;
    public static int MAX_ACTIVE_TIMER = 500000;
    private int activeTimer;
    private int fieldSize;
    private ShiftType shiftType;
    @Nullable private final Supplier<Block> shiftedOrBrokenVariant;

    protected final ContainerData containerData = new ContainerData()
    {
        @Override public int get(int index)
        {
            return (int) ((float) ReactorBlockEntity.this.activeTimer / (float) ReactorBlockEntity.MAX_ACTIVE_TIMER * 100.0F);
        }

        @Override public void set(int index, int value)
        {

        }

        @Override public int getCount() {return 1;}
    };

    public ContainerData getContainerData() {return this.containerData;}

    public ReactorBlockEntity(BlockPos pos, BlockState blockState, int fieldSize, ShiftType shiftType, @Nullable Supplier<Block> shiftedOrBrokenVariant)
    {
        super(AerialHellBlockEntities.REACTOR.get(), pos, blockState);
        this.fieldSize = fieldSize;
        this.shiftType = shiftType;
        this.shiftedOrBrokenVariant = shiftedOrBrokenVariant;
        this.activeTimer = 0;
    }

    @Override public int getFieldSize() { return this.activeTimer > 0 ? this.fieldSize : (int)(0.25F * this.fieldSize); }
    @Override public ShiftType getShiftType() { return this.shiftType; }
    @Override @Nullable public Supplier<Block> getShiftedOrBrokenVariant() { return this.shiftedOrBrokenVariant; }
    public int getActiveTimer() { return activeTimer; }

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
        ReactorBlock.tickParticleAndSoundAnimation((ServerLevel) level, state, pos, level.getRandom(), blockEntity.shiftType);
    }

    public boolean updateActiveTimer() //returns true if isActive, else false
    {
        int active_factor = 10;
        boolean isActive = this.activeTimer > 0;
        if (isActive) {this.activeTimer--;}

        if (!this.items.isEmpty() && this.activeTimer < MAX_ACTIVE_TIMER)
        {
            ItemStack stack = this.items.get(0);

            if (this.shiftType == ShiftType.UNCORRUPT)
            {
                if (OscillatorBlockEntity.getOscillatingMap().containsKey(stack.getItem()))
                {
                    this.activeTimer += active_factor * OscillatorBlockEntity.getOscillatingMap().get(stack.getItem());
                    stack.shrink(1);
                    isActive = true;
                }
            }
            else
            {
                if (ReactorBlockEntity.getCorruptingMap().containsKey(stack.getItem()))
                {
                    this.activeTimer += active_factor * ReactorBlockEntity.getCorruptingMap().get(stack.getItem());
                    stack.shrink(1);
                    isActive = true;
                }
            }
        }
        return isActive;
    }

    @Override protected void saveAdditional(ValueOutput valueOutput)
    {
        super.saveAdditional(valueOutput);
        ContainerHelper.saveAllItems(valueOutput, this.items);
        valueOutput.putInt("field_size", fieldSize);
        valueOutput.putInt("active_timer", activeTimer);
        valueOutput.putBoolean("is_shadow", this.shiftType == ShiftType.CORRUPT);
    }

    @Override protected void loadAdditional(ValueInput valueInput)
    {
        super.loadAdditional(valueInput);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(valueInput, this.items);
        this.fieldSize  = valueInput.getInt("field_size").get();
        this.activeTimer = valueInput.getInt("active_timer").get();
        this.shiftType  = valueInput.getBooleanOr("is_shadow", false) ? ShiftType.CORRUPT : ShiftType.UNCORRUPT;
    }

    @Override public NonNullList<ItemStack> getItems() {return this.items;}
    @Override protected void setItems(NonNullList<ItemStack> items) {this.items = items;}
    @Override public int getContainerSize() {return this.items.size();}

    public static Map<Item, Integer> getCorruptingMap()
    {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        map.put(AerialHellItems.SHADOW_CRYSTAL.get(), 400);
        map.put(AerialHellItems.SHADOW_SHARD.get(), 1000);
        map.put(AerialHellItems.CURSED_CRYSAL.get(), 2000);
        map.put(AerialHellItems.CURSED_CRYSAL_BLOCK.get(), 18000);
        return map;
    }
}
