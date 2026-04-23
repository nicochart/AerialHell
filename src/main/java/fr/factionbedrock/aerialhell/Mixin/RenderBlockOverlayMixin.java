package fr.factionbedrock.aerialhell.Mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.client.renderer.Lightmap;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
public class RenderBlockOverlayMixin
{
    @Inject(method = "renderScreenEffect", at = @At("HEAD"), cancellable = true)
    private void renderOverlays(final boolean isFirstPerson, final boolean isSleeping, final float partialTicks, final SubmitNodeCollector submitNodeCollector, final boolean hideGui, CallbackInfo callbackInfo)
    {
        ScreenEffectRenderer renderer = (ScreenEffectRenderer) (Object) this;
        Player player = renderer.minecraft.player;
        MultiBufferSource vertexConsumers = renderer.bufferSource;
        PoseStack matrices = new PoseStack();
        BlockState state = EntityHelper.getInWallBlockState(player);
        if (state == null || player == null) {return ;}

        if (state.is(AerialHellTags.Blocks.SOLID_ETHER))
        {
            callbackInfo.cancel();
            if (state.is(AerialHellBlocks.WHITE_SOLID_ETHER)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.WHITE_SOLID_ETHER));}
            else if (state.is(AerialHellBlocks.BLUE_SOLID_ETHER)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.BLUE_SOLID_ETHER));}
            else if (state.is(AerialHellBlocks.GOLDEN_SOLID_ETHER)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GOLDEN_SOLID_ETHER));}
            else if (state.is(AerialHellBlocks.GREEN_SOLID_ETHER)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GREEN_SOLID_ETHER));}
            else if (state.is(AerialHellBlocks.PURPLE_SOLID_ETHER)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.PURPLE_SOLID_ETHER));}
        }

        else if (state.is(AerialHellTags.Blocks.GHOST_BLOCK) && !state.is(AerialHellTags.Blocks.GHOST_BLOCK_NO_OVERLAY))
        {
            callbackInfo.cancel();
            if (state.is(AerialHellTags.Blocks.STONE_GHOST_BLOCK))
            {
                if (state.is(AerialHellBlocks.GHOST_RUBY_BLOCK)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_RUBY_BLOCK));}
                else if (state.is(AerialHellBlocks.GHOST_FLUORITE_BLOCK)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_FLUORITE_BLOCK));}
                else if (state.is(AerialHellBlocks.GHOST_AZURITE_BLOCK)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_AZURITE_BLOCK));}
                else if (state.is(AerialHellBlocks.GHOST_GOLD_BLOCK)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_GOLD_BLOCK));}
                else if (state.is(AerialHellBlocks.GHOST_STELLAR_COBBLESTONE) || state.is(AerialHellBlocks.GHOST_STELLAR_FURNACE)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_STELLAR_COBBLESTONE));}
                else {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_STELLAR_COBBLESTONE));}
            }
            else if (state.is(AerialHellBlocks.GHOST_BOAT_WOOL)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_BOAT_WOOL));}
            else //if (state.isIn(AerialHellTags.Blocks.WOODEN_GHOST_BLOCK))
            {
                if (state.is(AerialHellBlocks.GHOST_BOAT_WOOD) || state.is(AerialHellBlocks.GHOST_BOAT_LOG)) {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_BOAT_LOG));}
                else {renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.GHOST_BOAT_PLANKS));}
            }
        }

        else if (state.is(AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK))
        {
            callbackInfo.cancel();
            renderCustomOverlay(player, matrices, vertexConsumers, getBlockTextureLocation(AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK));
        }
    }

    //copy of net.minecraft.client.gui.hud.InGameOverlayRenderer renderUnderwaterOverlay method, edited
    private static void renderCustomOverlay(Player player, PoseStack matrices, MultiBufferSource vertexConsumers, Identifier texture)
    {
        BlockPos blockPos = BlockPos.containing(player.getX(), player.getEyeY(), player.getZ());
        float brightness = Lightmap.getBrightness(player.level().dimensionType(), player.level().getMaxLocalRawBrightness(blockPos));
        int color = ARGB.colorFromFloat(0.9F, brightness, brightness, brightness);
        float yaw = -player.getYRot() / 64.0F;
        float pitch = player.getXRot() / 64.0F;
        Matrix4f matrix4f = matrices.last().pose();
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderTypes.blockScreenEffect(texture));
        vertexConsumer.addVertex(matrix4f, -1.0F, -1.0F, -0.5F).setUv(4.0F + yaw, 4.0F + pitch).setColor(color);
        vertexConsumer.addVertex(matrix4f, 1.0F, -1.0F, -0.5F).setUv(0.0F + yaw, 4.0F + pitch).setColor(color);
        vertexConsumer.addVertex(matrix4f, 1.0F, 1.0F, -0.5F).setUv(0.0F + yaw, 0.0F + pitch).setColor(color);
        vertexConsumer.addVertex(matrix4f, -1.0F, 1.0F, -0.5F).setUv(4.0F + yaw, 0.0F + pitch).setColor(color);
    }

    private static Identifier getBlockTextureLocation(Block block) {return getBlockTextureLocation(BuiltInRegistries.BLOCK.getKey(block).getPath());}

    private static Identifier getBlockTextureLocation(String id)
    {
        return AerialHell.id("textures/block/"+id+".png");
    }
}
