package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.ReactorBlock;
import fr.factionbedrock.aerialhell.Inventory.Menu.ReactorMenu;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class ReactorBlockEntity extends RandomizableContainerBlockEntity implements BiomeShifter
{
    private NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    public static int MAX_PROTECTION_DISTANCE = 100;
    public static int MAX_ACTIVE_TIMER = 500000;
    private int activeTimer;
    private int fieldSize;
    private ShiftType shiftType;
    @Nullable private final Supplier<Block> shiftedOrBrokenVariant;

    public ReactorBlockEntity(BlockPos pos, BlockState blockState, int fieldSize, ShiftType shiftType, @Nullable Supplier<Block> shiftedOrBrokenVariant)
    {
        super(AerialHellBlockEntities.REACTOR, pos, blockState);
        this.fieldSize = fieldSize;
        this.shiftType = shiftType;
        this.shiftedOrBrokenVariant = shiftedOrBrokenVariant;
        this.activeTimer = 0;
    }

    @Override public int getFieldSize() {return this.activeTimer > 0 ? this.fieldSize : (int) (0.25F * this.fieldSize);}
    @Override public ShiftType getShiftType() {return this.shiftType;}
    @Override @Nullable public Supplier<Block> getShiftedOrBrokenVariant() {return this.shiftedOrBrokenVariant;}
    public int getActiveTimer() {return activeTimer;}

    @Override protected Component getDefaultName()
    {
        String type = this.shiftType == ShiftType.UNCORRUPT ? "light" : "shadow";
        return Component.translatable("container." + AerialHell.MODID + "." + type + "_reactor");
    }

    @Override protected AbstractContainerMenu createMenu(int id, Inventory inv) {return new ReactorMenu(id, inv, this);}

    public static void tick(Level world, BlockPos pos, BlockState state, ReactorBlockEntity blockEntity)
    {
        boolean isActive = blockEntity.updateActiveTimer();
        if (state.getValue(ReactorBlock.ACTIVE) != isActive) {world.setBlockAndUpdate(pos, state.setValue(ReactorBlock.ACTIVE, isActive));}

        BiomeShifter.transformRandomBlocks(world, pos, state, blockEntity);
        ReactorBlock.tickParticleAndSoundAnimation((ServerLevel) world, state, pos, world.getRandom(), blockEntity.shiftType);
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
                if (stack.is(AerialHellItems.SHADOW_CRYSTAL)) {activeTimerIncrement = 400 * active_factor;}
                else if (stack.is(AerialHellItems.SHADOW_SHARD)) {activeTimerIncrement = 1000 * active_factor;}
                else if (stack.is(AerialHellItems.CURSED_CRYSAL)) {activeTimerIncrement = 2000 * active_factor;}
                else if (stack.is(AerialHellItems.CURSED_CRYSAL_BLOCK)) {activeTimerIncrement = 18000 * active_factor;}
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

    @Override protected void saveAdditional(ValueOutput view)
    {
        super.saveAdditional(view);
        ContainerHelper.saveAllItems(view, this.items);
        view.putInt("field_size", fieldSize);
        view.putInt("active_timer", activeTimer);
        boolean isShadow = this.shiftType == ShiftType.CORRUPT;
        view.putBoolean("is_shadow", isShadow);
    }

    @Override protected void loadAdditional(ValueInput view)
    {
        super.loadAdditional(view);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(view, this.items);
        this.fieldSize = view.getInt("field_size").get();
        this.activeTimer = view.getInt("active_timer").get();
        boolean isShadow = view.getBooleanOr("is_shadow", false);
        this.shiftType = isShadow ? ShiftType.CORRUPT : ShiftType.UNCORRUPT;
    }

    @Override public NonNullList<ItemStack> getItems() {return this.items;}
    @Override protected void setItems(NonNullList<ItemStack> items) {this.items = items;}
    @Override public int getContainerSize() {return this.items.size();}
}
