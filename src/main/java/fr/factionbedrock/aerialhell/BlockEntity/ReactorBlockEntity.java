package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.ReactorBlock;
import fr.factionbedrock.aerialhell.Inventory.Menu.ReactorMenu;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ReactorBlockEntity extends LootableContainerBlockEntity implements BiomeShifter
{
    private DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);
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

    @Override protected Text getContainerName()
    {
        String type = this.shiftType == ShiftType.UNCORRUPT ? "light" : "shadow";
        return Text.translatable("container." + AerialHell.MODID + "." + type + "_reactor");
    }

    @Override protected ScreenHandler createScreenHandler(int id, PlayerInventory inv) {return new ReactorMenu(id, inv, this);}

    public static void tick(World world, BlockPos pos, BlockState state, ReactorBlockEntity blockEntity)
    {
        boolean isActive = blockEntity.updateActiveTimer();
        if (state.get(ReactorBlock.ACTIVE) != isActive) {world.setBlockState(pos, state.with(ReactorBlock.ACTIVE, isActive));}

        BiomeShifter.transformRandomBlocks(world, pos, state, blockEntity);
        ReactorBlock.tickParticleAndSoundAnimation((ServerWorld) world, state, pos, world.random, blockEntity.shiftType);
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
                        stack.decrement(1);
                        isActive = true;
                    }
                }
            }
            else
            {
                int activeTimerIncrement = 0;
                if (stack.isOf(AerialHellItems.SHADOW_CRYSTAL)) {activeTimerIncrement = 400 * active_factor;}
                else if (stack.isOf(AerialHellItems.SHADOW_SHARD)) {activeTimerIncrement = 1000 * active_factor;}
                else if (stack.isOf(AerialHellItems.CURSED_CRYSAL)) {activeTimerIncrement = 2000 * active_factor;}
                else if (stack.isOf(AerialHellItems.CURSED_CRYSAL_BLOCK)) {activeTimerIncrement = 18000 * active_factor;}
                if (activeTimerIncrement > 0 && this.activeTimer + activeTimerIncrement <= MAX_ACTIVE_TIMER)
                {
                    this.activeTimer += activeTimerIncrement;
                    stack.decrement(1);
                    isActive = true;
                }
            }
        }

        return isActive;
    }
    
    @Override protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, this.items, registryLookup);
        nbt.putInt("field_size", fieldSize);
        nbt.putInt("active_timer", activeTimer);
        boolean isShadow = this.shiftType == ShiftType.CORRUPT;
        nbt.putBoolean("is_shadow", isShadow);
    }

    @Override protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        super.readNbt(nbt, registryLookup);
        this.items = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.items, registryLookup);
        this.fieldSize = nbt.getInt("field_size");
        this.activeTimer = nbt.getInt("active_timer");
        boolean isShadow = nbt.getBoolean("is_shadow");
        this.shiftType = isShadow ? ShiftType.CORRUPT : ShiftType.UNCORRUPT;
    }

    @Override public DefaultedList<ItemStack> getHeldStacks() {return this.items;}
    @Override protected void setHeldStacks(DefaultedList<ItemStack> items) {this.items = items;}
    @Override public int size() {return this.items.size();}
}
