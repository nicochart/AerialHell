package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AerialHellHangingSignBlockEntity extends SignBlockEntity
{
	private static final int MAX_TEXT_WIDTH = 60;
	private static final int TEXT_LINE_HEIGHT = 9;

	public AerialHellHangingSignBlockEntity(BlockPos pos, BlockState state) {super(AerialHellBlockEntities.HANGING_SIGN, pos, state);}

	@Override public int getTextLineHeight() {return TEXT_LINE_HEIGHT;}
	@Override public int getMaxTextLineWidth() {return MAX_TEXT_WIDTH;}

	@Override public SoundEvent getSignInteractionFailedSoundEvent() {return SoundEvents.WAXED_HANGING_SIGN_INTERACT_FAIL;}
}