package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.client.renderer.Lightmap;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.event.ExtractBlockScreenEffectEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.joml.Matrix4f;

public class ScreenEffectListener
{
    public static void onOverlay(ExtractBlockScreenEffectEvent event)
    {
        if (event.getOverlayType() == ExtractBlockScreenEffectEvent.OverlayType.BLOCK)
        {
            BlockState state = event.getState();
            if (state.is(AerialHellTags.Blocks.SOLID_ETHER))
            {
                event.setCanceled(true);
                if (state.is(AerialHellBlocks.WHITE_SOLID_ETHER.get())) {setCustomOverlay(event, getBlockTextureLocation(AerialHellBlocks.WHITE_SOLID_ETHER));}
                else if (state.is(AerialHellBlocks.BLUE_SOLID_ETHER.get())) {setCustomOverlay(event, getBlockTextureLocation(AerialHellBlocks.BLUE_SOLID_ETHER));}
                else if (state.is(AerialHellBlocks.GOLDEN_SOLID_ETHER.get())) {setCustomOverlay(event, getBlockTextureLocation(AerialHellBlocks.GOLDEN_SOLID_ETHER));}
                else if (state.is(AerialHellBlocks.GREEN_SOLID_ETHER.get())) {setCustomOverlay(event, getBlockTextureLocation(AerialHellBlocks.GREEN_SOLID_ETHER));}
                else if (state.is(AerialHellBlocks.PURPLE_SOLID_ETHER.get())) {setCustomOverlay(event, getBlockTextureLocation(AerialHellBlocks.PURPLE_SOLID_ETHER));}
            }

            else if (state.is(AerialHellTags.Blocks.GHOST_BLOCK) && !state.is(AerialHellTags.Blocks.GHOST_BLOCK_NO_OVERLAY))
            {
                event.setCanceled(true);
                if (state.is(AerialHellTags.Blocks.STONE_GHOST_BLOCK))
                {
                    if (state.is(AerialHellBlocks.GHOST_RUBY_BLOCK.get())) {setCustomOverlay(event, getBlockTextureLocation(AerialHellBlocks.GHOST_RUBY_BLOCK));}
                    else if (state.is(AerialHellBlocks.GHOST_FLUORITE_BLOCK.get())) {setCustomOverlay(event, getBlockTextureLocation(AerialHellBlocks.GHOST_FLUORITE_BLOCK));}
                    else if (state.is(AerialHellBlocks.GHOST_AZURITE_BLOCK.get())) {setCustomOverlay(event, getBlockTextureLocation(AerialHellBlocks.GHOST_AZURITE_BLOCK));}
                    else if (state.is(AerialHellBlocks.GHOST_GOLD_BLOCK.get())) {setCustomOverlay(event, getBlockTextureLocation(AerialHellBlocks.GHOST_GOLD_BLOCK));}
                    else if (state.is(AerialHellBlocks.GHOST_STELLAR_COBBLESTONE.get()) || state.is(AerialHellBlocks.GHOST_STELLAR_FURNACE.get())) {setCustomOverlay(event, getBlockTextureLocation(AerialHellBlocks.GHOST_STELLAR_COBBLESTONE));}
                    else {setCustomOverlay(event, getBlockTextureLocation(AerialHellBlocks.GHOST_STELLAR_COBBLESTONE));}
                }
                else if (state.is(AerialHellBlocks.GHOST_BOAT_WOOL.get())) {setCustomOverlay(event, getBlockTextureLocation(AerialHellBlocks.GHOST_BOAT_WOOL));}
                else //if (state.is(AerialHellTags.Blocks.WOODEN_GHOST_BLOCK))
                {
                    if (state.is(AerialHellBlocks.GHOST_BOAT_WOOD.get()) || state.is(AerialHellBlocks.GHOST_BOAT_LOG.get())) {setCustomOverlay(event, getBlockTextureLocation(AerialHellBlocks.GHOST_BOAT_LOG));}
                    else {setCustomOverlay(event, getBlockTextureLocation(AerialHellBlocks.GHOST_BOAT_PLANKS));}
                }
            }

            else if (state.is(AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK.get()))
            {
                event.setCanceled(true);
                setCustomOverlay(event, getBlockTextureLocation(AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK));
            }
        }
    }

    private static void setCustomOverlay(ExtractBlockScreenEffectEvent event, Identifier texture)
    {
        Player player = event.getPlayer();
        event.setCustomRenderer((playerRenderState, cameraRenderState, submitNodeCollector, poseStack, sprites, partialTicks, hideGui) -> {renderCustomOverlay(player, submitNodeCollector, poseStack, texture); return true;});
    }

    //function from net.minecraft.client.renderer.ScreenEffectRenderer
    public static void renderCustomOverlay(Player player, SubmitNodeCollector submitNodeCollector, PoseStack poseStack, Identifier texture)
    {
        BlockPos pos = BlockPos.containing(player.getEyePosition());
        float brightness = Lightmap.getBrightness(player.level().dimensionType(), player.level().getMaxLocalRawBrightness(pos));
        int color = ARGB.colorFromFloat(1.0F, brightness, brightness, brightness);
        float u0 = -player.getYRot() / 64.0F;
        float v0 = player.getXRot() / 64.0F;
        submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.blockScreenEffect(texture), (pose, builder) ->
        {
            float uvSize = 4.0F;
            buildQuad(builder, pose.pose(), -1.0F, -1.0F, 1.0F, 1.0F, -0.5F, u0 + uvSize, v0 + uvSize, u0, v0, color);
        });
    }

    //function from net.minecraft.client.renderer.ScreenEffectRenderer
    private static void buildQuad(final VertexConsumer builder, final Matrix4f pose, final float x0, final float y0, final float x1, final float y1, final float z, final float u0, final float v0, final float u1, final float v1, final int color)
    {
        builder.addVertex(pose, x0, y0, z).setUv(u0, v0).setColor(color);
        builder.addVertex(pose, x1, y0, z).setUv(u1, v0).setColor(color);
        builder.addVertex(pose, x1, y1, z).setUv(u1, v1).setColor(color);
        builder.addVertex(pose, x0, y1, z).setUv(u0, v1).setColor(color);
    }

    private static Identifier getBlockTextureLocation(DeferredBlock<? extends Block> block) {return getBlockTextureLocation(block.getId().getPath());}

    private static Identifier getBlockTextureLocation(String id)
    {
        return Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/block/"+id+".png");
    }
}
