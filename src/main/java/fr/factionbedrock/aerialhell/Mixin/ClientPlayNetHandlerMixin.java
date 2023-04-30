package fr.factionbedrock.aerialhell.Mixin;

//See class net.minecraft.client.network.play.ClientPlayNetHandler

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fr.factionbedrock.aerialhell.Client.Gui.SignBlockEditScreen;
import fr.factionbedrock.aerialhell.BlockEntity.AerialHellSignBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.server.SOpenSignMenuPacket;
import net.minecraft.network.play.server.SUpdateBlockEntityPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

@Mixin(ClientPlayNetHandler.class)
public class ClientPlayNetHandlerMixin
{
	@Shadow
	private Minecraft client;

	@Shadow
	private ClientLevel world;

	@Inject(method = "handleSignEditorOpen", at = @At(value = "HEAD"), cancellable = true)
	public void be_openSignEditor(SOpenSignMenuPacket packet, CallbackInfo info)
	{
		PacketThreadUtil.checkThreadAndEnqueue(packet, (ClientPlayNetHandler) (Object) this, client);
		BlockEntity blockEntity = this.world.getBlockEntity(packet.getSignPosition());
		if (blockEntity instanceof AerialHellSignBlockEntity)
		{
			AerialHellSignBlockEntity sign = (AerialHellSignBlockEntity) blockEntity;
			client.displayGuiScreen(new SignBlockEditScreen(sign));
			info.cancel();
		}
	}

	@Inject(method = "handleUpdateBlockEntity", at = @At(value = "HEAD"), cancellable = true)
	public void be_onEntityUpdate(SUpdateBlockEntityPacket packet, CallbackInfo info)
	{
		PacketThreadUtil.checkThreadAndEnqueue(packet, (ClientPlayNetHandler) (Object) this, client);
		BlockPos blockPos = packet.getPos();
		BlockEntity blockEntity = this.client.world.getBlockEntity(blockPos);
		if (blockEntity instanceof AerialHellSignBlockEntity)
		{
			blockEntity.read(this.client.world.getBlockState(blockPos), packet.getNbtCompound());
			info.cancel();
		}
	}
}