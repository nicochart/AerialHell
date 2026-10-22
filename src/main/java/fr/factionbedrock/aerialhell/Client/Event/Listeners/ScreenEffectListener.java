package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.neoforged.neoforge.client.event.RenderBlockScreenEffectEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.joml.Matrix4f;

public class ScreenEffectListener
{
    public static void onOverlay(RenderBlockScreenEffectEvent event)
    {
        if (event.getOverlayType() == RenderBlockScreenEffectEvent.OverlayType.BLOCK)
        {
            Player player = event.getPlayer();
            PoseStack matrixStack = event.getPoseStack();
            BlockState state = event.getBlockState();
            if (event.getBlockState().is(AerialHellTags.Blocks.SOLID_ETHER))
            {
                event.setCanceled(true);
                if (state.is(AerialHellBlocksAndItems.WHITE_SOLID_ETHER.get())) {renderCustomOverlay(player, matrixStack, getBlockTextureLocation(AerialHellBlocksAndItems.WHITE_SOLID_ETHER));}
                else if (state.is(AerialHellBlocksAndItems.BLUE_SOLID_ETHER.get())) {renderCustomOverlay(player, matrixStack, getBlockTextureLocation(AerialHellBlocksAndItems.BLUE_SOLID_ETHER));}
                else if (state.is(AerialHellBlocksAndItems.GOLDEN_SOLID_ETHER.get())) {renderCustomOverlay(player, matrixStack, getBlockTextureLocation(AerialHellBlocksAndItems.GOLDEN_SOLID_ETHER));}
                else if (state.is(AerialHellBlocksAndItems.GREEN_SOLID_ETHER.get())) {renderCustomOverlay(player, matrixStack, getBlockTextureLocation(AerialHellBlocksAndItems.GREEN_SOLID_ETHER));}
                else if (state.is(AerialHellBlocksAndItems.PURPLE_SOLID_ETHER.get())) {renderCustomOverlay(player, matrixStack, getBlockTextureLocation(AerialHellBlocksAndItems.PURPLE_SOLID_ETHER));}
            }

            else if (state.is(AerialHellTags.Blocks.GHOST_BLOCK) && !state.is(AerialHellTags.Blocks.GHOST_BLOCK_NO_OVERLAY))
            {
                event.setCanceled(true);
                if (state.is(AerialHellTags.Blocks.STONE_GHOST_BLOCK))
                {
                    if (state.is(AerialHellBlocksAndItems.GHOST_RUBY_BLOCK.get())) {renderCustomOverlay(player, matrixStack, getBlockTextureLocation(AerialHellBlocksAndItems.GHOST_RUBY_BLOCK));}
                    else if (state.is(AerialHellBlocksAndItems.GHOST_FLUORITE_BLOCK.get())) {renderCustomOverlay(player, matrixStack, getBlockTextureLocation(AerialHellBlocksAndItems.GHOST_FLUORITE_BLOCK));}
                    else if (state.is(AerialHellBlocksAndItems.GHOST_AZURITE_BLOCK.get())) {renderCustomOverlay(player, matrixStack, getBlockTextureLocation(AerialHellBlocksAndItems.GHOST_AZURITE_BLOCK));}
                    else if (state.is(AerialHellBlocksAndItems.GHOST_GOLD_BLOCK.get())) {renderCustomOverlay(player, matrixStack, getBlockTextureLocation(AerialHellBlocksAndItems.GHOST_GOLD_BLOCK));}
                    else if (state.is(AerialHellBlocksAndItems.GHOST_STELLAR_COBBLESTONE.get()) || state.is(AerialHellBlocksAndItems.GHOST_STELLAR_FURNACE.get())) {renderCustomOverlay(player, matrixStack, getBlockTextureLocation(AerialHellBlocksAndItems.GHOST_STELLAR_COBBLESTONE));}
                    else {renderCustomOverlay(player, matrixStack, getBlockTextureLocation(AerialHellBlocksAndItems.GHOST_STELLAR_COBBLESTONE));}
                }
                else if (state.is(AerialHellBlocksAndItems.GHOST_BOAT_WOOL.get())) {renderCustomOverlay(player, matrixStack, getBlockTextureLocation(AerialHellBlocksAndItems.GHOST_BOAT_WOOL));}
                else //if (state.is(AerialHellTags.Blocks.WOODEN_GHOST_BLOCK))
                {
                    if (state.is(AerialHellBlocksAndItems.GHOST_BOAT_WOOD.get()) || state.is(AerialHellBlocksAndItems.GHOST_BOAT_LOG.get())) {renderCustomOverlay(player, matrixStack, getBlockTextureLocation(AerialHellBlocksAndItems.GHOST_BOAT_LOG));}
                    else {renderCustomOverlay(player, matrixStack, getBlockTextureLocation(AerialHellBlocksAndItems.GHOST_BOAT_PLANKS));}
                }
            }

            else if (state.is(AerialHellBlocksAndItems.INTANGIBLE_TEMPORARY_BLOCK.get()))
            {
                event.setCanceled(true);
                renderCustomOverlay(player, matrixStack, getBlockTextureLocation(AerialHellBlocksAndItems.INTANGIBLE_TEMPORARY_BLOCK));
            }
        }
    }

    //function from net.minecraft.client.renderer.ScreenEffectRenderer
    public static void renderCustomOverlay(Player player, PoseStack poseStack, ResourceLocation texture)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);
        BlockPos blockpos = BlockPos.containing(player.getX(), player.getEyeY(), player.getZ());
        float brightness = LightTexture.getBrightness(player.level().dimensionType(), player.level().getMaxLocalRawBrightness(blockpos));
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(brightness, brightness, brightness, 1.0F);
        float yaw = -player.getYRot() / 64.0F;
        float pitch = player.getXRot() / 64.0F;
        Matrix4f matrix4f = poseStack.last().pose();
        BufferBuilder bufferbuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.addVertex(matrix4f, -1.0F, -1.0F, -0.5F).setUv(4.0F + yaw, 4.0F + pitch);
        bufferbuilder.addVertex(matrix4f, 1.0F, -1.0F, -0.5F).setUv(0.0F + yaw, 4.0F + pitch);
        bufferbuilder.addVertex(matrix4f, 1.0F, 1.0F, -0.5F).setUv(0.0F + yaw, 0.0F + pitch);
        bufferbuilder.addVertex(matrix4f, -1.0F, 1.0F, -0.5F).setUv(4.0F + yaw, 0.0F + pitch);
        BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
    }

    private static ResourceLocation getBlockTextureLocation(DeferredBlock<? extends Block> block) {return getBlockTextureLocation(block.getId().getPath());}

    private static ResourceLocation getBlockTextureLocation(String id)
    {
        return ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/block/"+id+".png");
    }
}
