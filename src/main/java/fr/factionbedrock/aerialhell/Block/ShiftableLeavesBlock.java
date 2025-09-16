package fr.factionbedrock.aerialhell.Block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import fr.factionbedrock.aerialhell.Client.Util.ColorHandlerHelper;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.particle.TintedParticleEffect;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ShiftableLeavesBlock extends LeavesBlock
{
    public static final MapCodec<ShiftableLeavesBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(Codecs.rangedInclusiveFloat(0.0F, 1.0F).fieldOf("leaf_particle_chance").forGetter(tintedParticleLeavesBlock -> tintedParticleLeavesBlock.leafParticleChance), createSettingsCodec()).apply(instance, (chance, settings) -> new ShiftableLeavesBlock(chance, settings, () -> AerialHellBlocks.SHADOW_AERIAL_TREE_LEAVES, BiomeShifter.ShiftType.CORRUPT)));

    private final Supplier<ShiftableLeavesBlock> shiftedVariant;
    private final BiomeShifter.ShiftType shiftType;
    public static final BooleanProperty SHIFTED_RENDER = AerialHellBooleanProperties.SHIFTED_RENDER; //only used for render purposes

    public ShiftableLeavesBlock(Settings settings, Supplier<ShiftableLeavesBlock> shiftedVariant, BiomeShifter.ShiftType shiftType) {this(0.01F, settings, shiftedVariant, shiftType);}

    public ShiftableLeavesBlock(float leavesParticleChance, Settings settings, Supplier<ShiftableLeavesBlock> shiftedVariant, BiomeShifter.ShiftType shiftType)
    {
        super(leavesParticleChance, settings);
        this.shiftedVariant = shiftedVariant;
        this.shiftType = shiftType;
        this.setDefaultState(this.getDefaultState().with(SHIFTED_RENDER, false));
    }

    @Override protected void spawnLeafParticle(World world, BlockPos pos, Random random)
    {
        Block block = BlocksAndItemsColorHandler.isShadowBindEnabled() ? this.getShiftedVariant().get() : this;
        int color;

        if (block == AerialHellBlocks.AERIAL_TREE_LEAVES) {color = ColorHandlerHelper.AERIAL_TREE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.COPPER_PINE_LEAVES) {color = ColorHandlerHelper.COPPER_PINE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.GOLDEN_BEECH_LEAVES) {color = ColorHandlerHelper.GOLDEN_BEECH_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.LAPIS_ROBINIA_LEAVES) {color = ColorHandlerHelper.LAPIS_ROBINIA_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES) {color = world.getBlockColor(pos);}
        else if (block == AerialHellBlocks.SHADOW_PINE_LEAVES) {color = ColorHandlerHelper.SHADOW_PINE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES) {color = ColorHandlerHelper.PURPLE_SHADOW_PINE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.SHADOW_AERIAL_TREE_LEAVES) {color = ColorHandlerHelper.SHADOW_AERIAL_TREE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.SHADOW_COPPER_PINE_LEAVES) {color = ColorHandlerHelper.SHADOW_COPPER_PINE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.SHADOW_GOLDEN_BEECH_LEAVES) {color = ColorHandlerHelper.SHADOW_DEEP_BLACK;}
        else if (block == AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LEAVES) {color = ColorHandlerHelper.SHADOW_AERIAL_TREE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES) {color = ColorHandlerHelper.SHADOW_PINE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.HOLLOW_SHADOW_PINE_LEAVES) {color = ColorHandlerHelper.HOLLOW_SHADOW_PINE_LEAVES_COLOR;}
        else if (block == AerialHellBlocks.HOLLOW_PURPLE_SHADOW_PINE_LEAVES) {color = ColorHandlerHelper.HOLLOW_PURPLE_SHADOW_PINE_LEAVES_COLOR;}
        else {color = ColorHandlerHelper.WHITE;}

        ParticleEffect particle = TintedParticleEffect.create(ParticleTypes.TINTED_LEAVES, color);
        ParticleUtil.spawnParticle(world, pos, random, particle);
    }

    @Override public MapCodec<? extends ShiftableLeavesBlock> getCodec() {return CODEC;}

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        super.appendProperties(builder);
        builder.add(SHIFTED_RENDER);
    }

    public Supplier<ShiftableLeavesBlock> getShiftedVariant() {return this.shiftedVariant;}
    public BiomeShifter.ShiftType getShiftType() {return this.shiftType;}

    @Nullable public static BlockState getShiftedState(BlockState beforeState)
    {
        if (beforeState.getBlock() instanceof ShiftableLeavesBlock beforeLeavesBlock)
        {
            if (beforeLeavesBlock.getShiftedVariant().get() instanceof ShiftableLeavesBlock nextLeavesBlock)
            {
                return nextLeavesBlock.getDefaultState().with(DISTANCE, beforeState.get(DISTANCE)).with(PERSISTENT, beforeState.get(PERSISTENT)).with(WATERLOGGED, beforeState.get(WATERLOGGED));
            }
            return beforeLeavesBlock.getShiftedVariant().get().getDefaultState();
        }
        return null;
    }
}
