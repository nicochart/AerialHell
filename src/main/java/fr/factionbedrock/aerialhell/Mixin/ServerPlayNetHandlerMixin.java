package fr.factionbedrock.aerialhell.Mixin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fr.factionbedrock.aerialhell.TileEntity.AerialHellSignTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.ServerPlayNetHandler;
import net.minecraft.network.play.client.CUpdateSignPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.server.ServerWorld;

@Mixin(ServerPlayNetHandler.class)
public class ServerPlayNetHandlerMixin
{
	@Shadow
	private static final Logger LOGGER = LogManager.getLogger();

	@Shadow
	public ServerPlayerEntity player;

	@Inject(method = "processUpdateSign", at = @At(value = "HEAD"), cancellable = true)
	private void be_signUpdate(CUpdateSignPacket packet, CallbackInfo info)
	{
		PacketThreadUtil.checkThreadAndEnqueue(packet, (ServerPlayNetHandler) (Object) this, (ServerWorld) this.player.getServerWorld());
		this.player.markPlayerActive();
		ServerWorld serverWorld = this.player.getServerWorld();
		BlockPos blockPos = packet.getPosition();
		if (serverWorld.isBlockLoaded(blockPos))
		{
			BlockState blockState = serverWorld.getBlockState(blockPos);
			TileEntity blockEntity = serverWorld.getTileEntity(blockPos);
			if (blockEntity instanceof AerialHellSignTileEntity)
			{
				AerialHellSignTileEntity signBlockEntity = (AerialHellSignTileEntity) blockEntity;
				if (!signBlockEntity.isEditable() || signBlockEntity.getEditor() != this.player)
				{
					LOGGER.warn("Player {} just tried to change non-editable sign", this.player.getName().getString());
					return;
				}

				String[] strings = packet.getLines();

				for (int i = 0; i < strings.length; ++i)
				{
					signBlockEntity.setTextOnRow(i, new StringTextComponent(TextFormatting.getTextWithoutFormattingCodes(strings[i])));
				}

				signBlockEntity.markDirty();
				serverWorld.notifyBlockUpdate(blockPos, blockState, blockState, 3);

				info.cancel();
			}
		}
	}
}