package fr.factionbedrock.aerialhell.Block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ShiftableLeavesBlock extends LeavesBlock
{
    //TODO this codec works ?
    public static final MapCodec<ShiftableLeavesBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(ExtraCodecs.floatRange(0.0F, 1.0F).fieldOf("leaf_particle_chance").forGetter((block) -> block.leafParticleChance), propertiesCodec()).apply(instance, (g, a) -> new ShiftableLeavesBlock(g, a, AerialHellBlocks.SHADOW_AERIAL_TREE_LEAVES, BiomeShifter.ShiftType.CORRUPT)));
    private final Supplier<ShiftableLeavesBlock> shiftedVariant;
    private final BiomeShifter.ShiftType shiftType;
    public static final BooleanProperty SHIFTED_RENDER = BooleanProperty.create("shifted_render"); //only used for render purposes

    public ShiftableLeavesBlock(Properties prop, Supplier<ShiftableLeavesBlock> shiftedVariant, BiomeShifter.ShiftType shiftType) {this(0.5F, prop, shiftedVariant, shiftType);}

    public ShiftableLeavesBlock(float leavesParticleChance, Properties prop, Supplier<ShiftableLeavesBlock> shiftedVariant, BiomeShifter.ShiftType shiftType)
    {
        //TODO check (new leavesParticleChance in json)
        super(leavesParticleChance, prop);
        this.shiftedVariant = shiftedVariant;
        this.shiftType = shiftType;
        this.registerDefaultState(this.defaultBlockState().setValue(SHIFTED_RENDER, false));
    }

    @Override protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource randomSource)
    {
        //TODO edit particle
        ColorParticleOption colorparticleoption = ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, level.getClientLeafTintColor(pos));
        ParticleUtils.spawnParticleBelow(level, pos, randomSource, colorparticleoption);
    }

    @Override public MapCodec<? extends ShiftableLeavesBlock> codec() {return CODEC;}

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(SHIFTED_RENDER);
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
