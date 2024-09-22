package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.ReactorMenu;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ReactorBlockEntity extends BaseContainerBlockEntity implements BiomeShifter
{
    protected NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    public static int MAX_PROTECTION_DISTANCE = 100;
    private int activeTimer;
    private int fieldSize;

    public ReactorBlockEntity(BlockPos pos, BlockState blockState, int fieldSize)
    {
        super(AerialHellBlockEntities.REACTOR.get(), pos, blockState);
        this.fieldSize = fieldSize;
        this.activeTimer = 0;
    }

    @Override public int getFieldSize() {return this.activeTimer > 0 ? this.fieldSize : (int) (0.25F * this.fieldSize);}

    @Override @NotNull protected Component getDefaultName()
    {
        return Component.translatable("container." + AerialHell.MODID + ".reactor");
    }

    @Override protected AbstractContainerMenu createMenu(int id, @NotNull Inventory inv) {return new ReactorMenu(id, inv, this);}

    public static void tick(Level level, BlockPos pos, BlockState state, ReactorBlockEntity blockEntity)
    {
        blockEntity.updateActiveTimer();
        BiomeShifter.transformRandomBlocks(level, pos, state, blockEntity);
    }

    public void updateActiveTimer()
    {
        boolean isActive = this.activeTimer > 0;
        if (isActive) {this.activeTimer--;}
        else if (!this.items.isEmpty())
        {
            ItemStack stack = this.items.get(0);
            if (OscillatorBlockEntity.getOscillatingMap().containsKey(stack.getItem()))
            {
                this.activeTimer += 10 * OscillatorBlockEntity.getOscillatingMap().get(stack.getItem());
                stack.shrink(1);
            }
        }
    }

    @Override protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, this.items, registries);
        tag.putInt("field_size", fieldSize);
        tag.putInt("active_timer", activeTimer);
    }

    @Override protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.loadAdditional(tag, registries);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items, registries);
        this.fieldSize = tag.getInt("field_size");
        this.activeTimer = tag.getInt("active_timer");
    }

    @Override public NonNullList<ItemStack> getItems() {return this.items;}
    @Override protected void setItems(NonNullList<ItemStack> items) {this.items = items;}
    @Override public int getContainerSize() {return this.items.size();}
}
