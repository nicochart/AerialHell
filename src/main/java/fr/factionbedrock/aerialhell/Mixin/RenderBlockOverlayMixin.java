package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.*;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class RenderBlockOverlayMixin
{
    @Inject(method = "renderOverlays", at = @At("HEAD"), cancellable = true)
    private void renderOverlays(boolean sleeping, float tickProgress, OrderedRenderCommandQueue queue, CallbackInfo callbackInfo)
    {
        InGameOverlayRenderer renderer = (InGameOverlayRenderer) (Object) this;
        PlayerEntity player = renderer.client.player;
        VertexConsumerProvider vertexConsumers = renderer.vertexConsumers;
        MatrixStack matrices = new MatrixStack();
        BlockState state = EntityHelper.getInWallBlockState(player);
        if (state == null || player == null) {return ;}

        if (state.isIn(AerialHellTags.Blocks.SOLID_ETHER))
        {
            callbackInfo.cancel();
            if (state.isOf(AerialHellBlocks.WHITE_SOLID_ETHER)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.WHITE_SOLID_ETHER));}
            else if (state.isOf(AerialHellBlocks.BLUE_SOLID_ETHER)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.BLUE_SOLID_ETHER));}
            else if (state.isOf(AerialHellBlocks.GOLDEN_SOLID_ETHER)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GOLDEN_SOLID_ETHER));}
            else if (state.isOf(AerialHellBlocks.GREEN_SOLID_ETHER)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GREEN_SOLID_ETHER));}
            else if (state.isOf(AerialHellBlocks.PURPLE_SOLID_ETHER)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.PURPLE_SOLID_ETHER));}
        }

        else if (state.isIn(AerialHellTags.Blocks.GHOST_BLOCK) && !state.isIn(AerialHellTags.Blocks.GHOST_BLOCK_NO_OVERLAY))
        {
            callbackInfo.cancel();
            if (state.isIn(AerialHellTags.Blocks.STONE_GHOST_BLOCK))
            {
                if (state.isOf(AerialHellBlocks.GHOST_RUBY_BLOCK)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_RUBY_BLOCK));}
                else if (state.isOf(AerialHellBlocks.GHOST_FLUORITE_BLOCK)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_FLUORITE_BLOCK));}
                else if (state.isOf(AerialHellBlocks.GHOST_AZURITE_BLOCK)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_AZURITE_BLOCK));}
                else if (state.isOf(AerialHellBlocks.GHOST_GOLD_BLOCK)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_GOLD_BLOCK));}
                else if (state.isOf(AerialHellBlocks.GHOST_STELLAR_COBBLESTONE) || state.isOf(AerialHellBlocks.GHOST_STELLAR_FURNACE)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_STELLAR_COBBLESTONE));}
                else {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_STELLAR_COBBLESTONE));}
            }
            else if (state.isOf(AerialHellBlocks.GHOST_BOAT_WOOL)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_BOAT_WOOL));}
            else //if (state.isIn(AerialHellTags.Blocks.WOODEN_GHOST_BLOCK))
            {
                if (state.isOf(AerialHellBlocks.GHOST_BOAT_WOOD) || state.isOf(AerialHellBlocks.GHOST_BOAT_LOG)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_BOAT_LOG));}
                else {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_BOAT_PLANKS));}
            }
        }

        else if (state.isOf(AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK))
        {
            callbackInfo.cancel();
            renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK));
        }
    }

    //copy of net.minecraft.client.gui.hud.InGameOverlayRenderer renderUnderwaterOverlay method, edited
    private static void renderCustomOverlay(PlayerEntity player, MatrixStack matrices, VertexConsumerProvider vertexConsumers, Identifier texture)
    {
        BlockPos blockPos = BlockPos.ofFloored(player.getX(), player.getEyeY(), player.getZ());
        float brightness = LightmapTextureManager.getBrightness(player.getEntityWorld().getDimension(), player.getEntityWorld().getLightLevel(blockPos));
        int color = ColorHelper.fromFloats(0.9F, brightness, brightness, brightness);
        float yaw = -player.getYaw() / 64.0F;
        float pitch = player.getPitch() / 64.0F;
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayers.blockScreenEffect(texture));
        vertexConsumer.vertex(matrix4f, -1.0F, -1.0F, -0.5F).texture(4.0F + yaw, 4.0F + pitch).color(color);
        vertexConsumer.vertex(matrix4f, 1.0F, -1.0F, -0.5F).texture(0.0F + yaw, 4.0F + pitch).color(color);
        vertexConsumer.vertex(matrix4f, 1.0F, 1.0F, -0.5F).texture(0.0F + yaw, 0.0F + pitch).color(color);
        vertexConsumer.vertex(matrix4f, -1.0F, 1.0F, -0.5F).texture(4.0F + yaw, 0.0F + pitch).color(color);
    }

    private static Identifier getBlockTextureLocation(Block block) {return getBlockTextureLocation(Registries.BLOCK.getId(block).getPath());}

    private static Identifier getBlockTextureLocation(String id)
    {
        return AerialHell.id("textures/block/"+id+".png");
    }
}
