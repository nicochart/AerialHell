package fr.factionbedrock.aerialhell.Item.Ability;

import fr.factionbedrock.aerialhell.Util.FieldAccessor;
import net.minecraft.world.level.block.state.BlockState;

public record MiningUseSituationInfo(BlockState blockState, FieldAccessor<Float> miningSpeedMultiplier) {}