package fr.factionbedrock.aerialhell.Block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import fr.factionbedrock.aerialhell.Client.Util.ColorHandlerHelper;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ShiftableLeavesBlock extends LeavesBlock
{
    public static final MapCodec<ShiftableLeavesBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(ExtraCodecs.floatRange(0.0F, 1.0F).fieldOf("leaf_particle_chance").forGetter((block) -> block.leafParticleChance), propertiesCodec()).apply(instance, (g, a) -> new ShiftableLeavesBlock(g, a, AerialHellBlocks.SHADOW_AERIAL_TREE_LEAVES, BiomeShifter.ShiftType.CORRUPT)));
    private final Supplier<ShiftableLeavesBlock> shiftedVariant;
    private final BiomeShifter.ShiftType shiftType;

    public ShiftableLeavesBlock(Properties prop, Supplier<ShiftableLeavesBlock> shiftedVariant, BiomeShifter.ShiftType shiftType) {this(0.01F, prop, shiftedVariant, shiftType);}

    public ShiftableLeavesBlock(float leavesParticleChance, Properties prop, Supplier<ShiftableLeavesBlock> shiftedVariant, BiomeShifter.ShiftType shiftType)
    {
        super(leavesParticleChance, prop);
        this.shiftedVariant = shiftedVariant;
        this.shiftType = shiftType;
        this.registerDefaultState(this.defaultBlockState().setValue(AerialHellStateProperties.SHIFTED_RENDER, false));
    }

    @Override protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource randomSource)
    {
        Block block = BlocksAndItemsColorHandler.isShadowBindEnabled() ? this.getShiftedVariant().get() : this;
        int color;

        if (block == AerialHellBlocks.AERIAL_TREE_LEAVES.get()) {color = ColorHandlerHelper.AERIAL_TREE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.COPPER_PINE_LEAVES.get()) {color = ColorHandlerHelper.COPPER_PINE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.GOLDEN_BEECH_LEAVES.get()) {color = ColorHandlerHelper.GOLDEN_BEECH_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.LAPIS_ROBINIA_LEAVES.get()) {color = ColorHandlerHelper.LAPIS_ROBINIA_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES.get()) {color = level.getClientLeafTintColor(pos);}
        else if (block == AerialHellBlocks.SHADOW_PINE_LEAVES.get()) {color = ColorHandlerHelper.SHADOW_PINE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES.get()) {color = ColorHandlerHelper.PURPLE_SHADOW_PINE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.SHADOW_AERIAL_TREE_LEAVES.get()) {color = ColorHandlerHelper.SHADOW_AERIAL_TREE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.SHADOW_COPPER_PINE_LEAVES.get()) {color = ColorHandlerHelper.SHADOW_COPPER_PINE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.SHADOW_GOLDEN_BEECH_LEAVES.get()) {color = ColorHandlerHelper.SHADOW_DEEP_BLACK;}
        else if (block == AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LEAVES.get()) {color = ColorHandlerHelper.SHADOW_AERIAL_TREE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES.get()) {color = ColorHandlerHelper.SHADOW_PINE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.HOLLOW_SHADOW_PINE_LEAVES.get()) {color = ColorHandlerHelper.HOLLOW_SHADOW_PINE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.HOLLOW_PURPLE_SHADOW_PINE_LEAVES.get()) {color = ColorHandlerHelper.HOLLOW_PURPLE_SHADOW_PINE_LEAVES_COLOR;}
        else {color = ColorHandlerHelper.WHITE;}

        ParticleOptions particle = ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, color);
        ParticleUtils.spawnParticleBelow(level, pos, randomSource, particle);
    }

    @Override public MapCodec<? extends ShiftableLeavesBlock> codec() {return CODEC;}

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(AerialHellStateProperties.SHIFTED_RENDER);
    }

    public Supplier<ShiftableLeavesBlock> getShiftedVariant() {return this.shiftedVariant;}
    public BiomeShifter.ShiftType getShiftType() {return this.shiftType;}

    @Nullable
    public static BlockState getShiftedState(BlockState beforeState)
    {
        if (beforeState.getBlock() instanceof ShiftableLeavesBlock beforeLeavesBlock)
        {
            if (beforeLeavesBlock.getShiftedVariant().get() instanceof ShiftableLeavesBlock nextLeavesBlock)
            {
                return nextLeavesBlock.defaultBlockState().setValue(DISTANCE, beforeState.getValue(DISTANCE)).setValue(PERSISTENT, beforeState.getValue(PERSISTENT)).setValue(WATERLOGGED, beforeState.getValue(WATERLOGGED));
            }
            return beforeLeavesBlock.getShiftedVariant().get().defaultBlockState();
        }
        return null;
    }
}
