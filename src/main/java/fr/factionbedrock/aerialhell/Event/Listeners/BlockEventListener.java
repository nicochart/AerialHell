package fr.factionbedrock.aerialhell.Event.Listeners;

import com.mojang.blaze3d.systems.RenderSystem;

import com.mojang.blaze3d.vertex.*;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Block.DungeonCores.DungeonCoreBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderBlockScreenEffectEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Matrix4f;

@Mod.EventBusSubscriber
public class BlockEventListener
{
    @SubscribeEvent
    public static void onBlockEvent(BlockEvent.NeighborNotifyEvent event)
    {
        if (event.getLevel() instanceof Level world)
        {
            BlockPos pos = event.getPos();
            FluidState fluidstate = world.getFluidState(pos);
            BlockState blockstate = world.getBlockState(pos);
            if (world.dimension() == AerialHellDimensions.AERIAL_HELL_DIMENSION)
            {
	            if (fluidstate.getType().is(AerialHellTags.Fluids.CRYSTALLIZABLE))
	            {
	                world.setBlockAndUpdate(pos, AerialHellBlocksAndItems.CRYSTAL_BLOCK.get().defaultBlockState());
	                world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
	                event.setCanceled(true);
	            }
	            else if (blockstate.getBlock().equals(Blocks.MAGMA_BLOCK))
	            {
	            	world.setBlockAndUpdate(pos, AerialHellBlocksAndItems.MAGMATIC_GEL_ORE.get().defaultBlockState());
	            	world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
	            	event.setCanceled(true);
	            }
	            else if (blockstate.getBlock().equals(Blocks.FIRE) || blockstate.getBlock().equals(Blocks.SOUL_FIRE))
	            {
	            	world.setBlockAndUpdate(pos, AerialHellBlocksAndItems.CRYSTALLIZED_FIRE.get().defaultBlockState());
	            	world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
	            	if (world.getBlockState(pos.below()).getBlock() == Blocks.AIR)
	            	{
	            		world.destroyBlock(pos, true);
	            	}
	            	event.setCanceled(true);
	            }
	            else if (blockstate.getBlock().equals(Blocks.TORCH) || blockstate.getBlock().equals(Blocks.WALL_TORCH) || blockstate.is(AerialHellTags.Blocks.OVERWORLD_LANTERN))
	            {
	            	world.destroyBlock(pos, true);
	            	world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
	            	event.setCanceled(true);
	            }
            }
        }
    }
    
    @SubscribeEvent
    public static void onBlockEvent(BlockEvent.EntityPlaceEvent event)
    {
    	if (event.getLevel() instanceof Level world)
        {
    		BlockPos pos = event.getPos();
            BlockState blockstate = world.getBlockState(pos);
        	if (blockstate.is(AerialHellTags.Blocks.DUNGEON_CORES))
            {
            	((DungeonCoreBlock) blockstate.getBlock()).setAreaProtected(world, pos, true);
            	world.playSound(null, event.getPos(), SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        }
    }
    
    private static final ResourceLocation TEXTURE_WHITE_SOLID_ETHER = new ResourceLocation(AerialHell.MODID, "textures/block/white_solid_ether.png");
    private static final ResourceLocation TEXTURE_BLUE_SOLID_ETHER = new ResourceLocation(AerialHell.MODID, "textures/block/blue_solid_ether.png");
    private static final ResourceLocation TEXTURE_GOLDEN_SOLID_ETHER = new ResourceLocation(AerialHell.MODID, "textures/block/golden_solid_ether.png");
    private static final ResourceLocation TEXTURE_GREEN_SOLID_ETHER = new ResourceLocation(AerialHell.MODID, "textures/block/green_solid_ether.png");
    private static final ResourceLocation TEXTURE_PURPLE_SOLID_ETHER = new ResourceLocation(AerialHell.MODID, "textures/block/purple_solid_ether.png");
    private static final ResourceLocation TEXTURE_GHOST_BOAT_BLOCK = new ResourceLocation(AerialHell.MODID, "textures/block/ghost_boat_planks.png");

    @SubscribeEvent @OnlyIn(Dist.CLIENT)
    public static void onOverlay(RenderBlockScreenEffectEvent event)
    {
    	if (event.getOverlayType() == RenderBlockScreenEffectEvent.OverlayType.BLOCK)
    	{
    		Player player = event.getPlayer();
	    	PoseStack matrixStack = event.getPoseStack();
    		if (event.getBlockState().is(AerialHellTags.Blocks.SOLID_ETHER))
    		{
    			event.setCanceled(true);
    			if (event.getBlockState().is(AerialHellBlocksAndItems.WHITE_SOLID_ETHER.get())) {renderEther(player, matrixStack, TEXTURE_WHITE_SOLID_ETHER);}
    			else if (event.getBlockState().is(AerialHellBlocksAndItems.BLUE_SOLID_ETHER.get())) {renderEther(player, matrixStack, TEXTURE_BLUE_SOLID_ETHER);}
    			else if (event.getBlockState().is(AerialHellBlocksAndItems.GOLDEN_SOLID_ETHER.get())) {renderEther(player, matrixStack, TEXTURE_GOLDEN_SOLID_ETHER);}
                else if (event.getBlockState().is(AerialHellBlocksAndItems.GREEN_SOLID_ETHER.get())) {renderEther(player, matrixStack, TEXTURE_GREEN_SOLID_ETHER);}
                else if (event.getBlockState().is(AerialHellBlocksAndItems.PURPLE_SOLID_ETHER.get())) {renderEther(player, matrixStack, TEXTURE_PURPLE_SOLID_ETHER);}
    		}

            else if (event.getBlockState().is(AerialHellTags.Blocks.GHOST_BLOCK))
            {
                event.setCanceled(true);
                renderEther(player, matrixStack, TEXTURE_GHOST_BOAT_BLOCK);
            }
    	}
    }

    //function from net.minecraft.client.renderer.ScreenEffectRenderer
    @OnlyIn(Dist.CLIENT)
    private static void renderEther(Player player, PoseStack matrixStackIn, ResourceLocation texture)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture); //Minecraft.getInstance() client side only !
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        BlockPos blockpos = BlockPos.containing(player.getX(), player.getEyeY(), player.getZ());
        float brightness = LightTexture.getBrightness(player.level().dimensionType(), player.level().getMaxLocalRawBrightness(blockpos));
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(brightness, brightness, brightness, 1.0F);
        float yaw = -player.getYRot() / 64.0F;
        float pitch = player.getXRot() / 64.0F;
        Matrix4f matrix4f = matrixStackIn.last().pose();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix4f, -1.0F, -1.0F, -0.5F).color(brightness, brightness, brightness, 1.0F).uv(4.0F + yaw, 4.0F + pitch).endVertex();
        bufferbuilder.vertex(matrix4f, 1.0F, -1.0F, -0.5F).color(brightness, brightness, brightness, 1.0F).uv(0.0F + yaw, 4.0F + pitch).endVertex();
        bufferbuilder.vertex(matrix4f, 1.0F, 1.0F, -0.5F).color(brightness, brightness, brightness, 1.0F).uv(0.0F + yaw, 0.0F + pitch).endVertex();
        bufferbuilder.vertex(matrix4f, -1.0F, 1.0F, -0.5F).color(brightness, brightness, brightness, 1.0F).uv(4.0F + yaw, 0.0F + pitch).endVertex();
        BufferUploader.drawWithShader(bufferbuilder.end());
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
     }
}