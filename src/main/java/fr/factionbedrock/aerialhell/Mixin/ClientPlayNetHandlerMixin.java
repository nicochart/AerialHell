package fr.factionbedrock.aerialhell.Mixin;

//See class net.minecraft.client.network.play.ClientPlayNetHandler

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fr.factionbedrock.aerialhell.Client.Gui.SignBlockEditScreen;
import fr.factionbedrock.aerialhell.TileEntity.AerialHellSignTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.server.SOpenSignMenuPacket;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

@Mixin(ClientPlayNetHandler.class)
public class ClientPlayNetHandlerMixin
{
	@Shadow
	private Minecraft client;

	@Shadow
	private ClientWorld world;

	@Inject(method = "handleSignEditorOpen", at = @At(value = "HEAD"), cancellable = true)
	public void be_openSignEditor(SOpenSignMenuPacket packet, CallbackInfo info)
	{
		PacketThreadUtil.checkThreadAndEnqueue(packet, (ClientPlayNetHandler) (Object) this, client);
		TileEntity blockEntity = this.world.getTileEntity(packet.getSignPosition());
		if (blockEntity instanceof AerialHellSignTileEntity)
		{
			AerialHellSignTileEntity sign = (AerialHellSignTileEntity) blockEntity;
			client.displayGuiScreen(new SignBlockEditScreen(sign));
			info.cancel();
		}
	}

	@Inject(method = "handleUpdateTileEntity", at = @At(value = "HEAD"), cancellable = true)
	public void be_onEntityUpdate(SUpdateTileEntityPacket packet, CallbackInfo info)
	{
		PacketThreadUtil.checkThreadAndEnqueue(packet, (ClientPlayNetHandler) (Object) this, client);
		BlockPos blockPos = packet.getPos();
		TileEntity blockEntity = this.client.world.getTileEntity(blockPos);
		if (blockEntity instanceof AerialHellSignTileEntity)
		{
			blockEntity.read(this.client.world.getBlockState(blockPos), packet.getNbtCompound());
			info.cancel();
		}
	}
}