package fr.factionbedrock.aerialhell.Event.Listeners;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Block.DungeonCores.DungeonCoreBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellDimensions;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.fluid.FluidState;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockEventListener
{
    @SubscribeEvent
    public static void onBlockEvent(BlockEvent.NeighborNotifyEvent event)
    {
        if (event.getWorld() instanceof World)
        {
            World world = (World) event.getWorld();
            BlockPos pos = event.getPos();
            FluidState fluidstate = world.getFluidState(pos);
            BlockState blockstate = world.getBlockState(pos);
            if (world.getDimensionKey() == AerialHellDimensions.AERIAL_HELL_DIMENSION)
            {
	            if (fluidstate.getFluid().isIn(AerialHellTags.Fluids.CRYSTALLIZABLE))
	            {
	                world.setBlockState(pos, AerialHellBlocksAndItems.CRYSTAL_BLOCK.get().getDefaultState());
	                world.playSound(null, event.getPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0F, 1.0F);
	                event.setCanceled(true);
	            }
	            else if (blockstate.getBlock().equals(Blocks.MAGMA_BLOCK))
	            {
	            	world.setBlockState(pos, AerialHellBlocksAndItems.MAGMATIC_GEL_ORE.get().getDefaultState());
	            	world.playSound(null, event.getPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0F, 1.0F);
	            	event.setCanceled(true);
	            }
	            else if (blockstate.getBlock().equals(Blocks.FIRE) || blockstate.getBlock().equals(Blocks.SOUL_FIRE))
	            {
	            	world.setBlockState(pos, AerialHellBlocksAndItems.CRYSTALLIZED_FIRE.get().getDefaultState());
	            	world.playSound(null, event.getPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0F, 1.0F);
	            	if (world.getBlockState(pos.down()).getBlock() == Blocks.AIR)
	            	{
	            		world.destroyBlock(pos, true);
	            	}
	            	event.setCanceled(true);
	            }
	            else if (blockstate.getBlock().equals(Blocks.TORCH) || blockstate.getBlock().equals(Blocks.WALL_TORCH) || blockstate.isIn(AerialHellTags.Blocks.OVERWORLD_LANTERN))
	            {
	            	world.destroyBlock(pos, true);
	            	world.playSound(null, event.getPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0F, 1.0F);
	            	event.setCanceled(true);
	            }
            }
        }
    }
    
    @SubscribeEvent
    public static void onBlockEvent(BlockEvent.EntityPlaceEvent event)
    {
    	if (event.getWorld() instanceof World)
        {
    		World world = (World) event.getWorld();
    		BlockPos pos = event.getPos();
            BlockState blockstate = world.getBlockState(pos);
        	if (blockstate.getBlock().isIn(AerialHellTags.Blocks.DUNGEON_CORES))
            {
            	((DungeonCoreBlock) blockstate.getBlock()).setAreaProtected(world, pos, true);
            	world.playSound(null, event.getPos(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
        }
    }
    
    private static final ResourceLocation TEXTURE_WHITE_SOLID_ETHER = new ResourceLocation(AerialHell.MODID, "textures/block/white_solid_ether.png");
    private static final ResourceLocation TEXTURE_BLUE_SOLID_ETHER = new ResourceLocation(AerialHell.MODID, "textures/block/blue_solid_ether.png");
    private static final ResourceLocation TEXTURE_GOLDEN_SOLID_ETHER = new ResourceLocation(AerialHell.MODID, "textures/block/golden_solid_ether.png");
    private static final ResourceLocation TEXTURE_GREEN_SOLID_ETHER = new ResourceLocation(AerialHell.MODID, "textures/block/green_solid_ether.png");
    
    @SubscribeEvent @OnlyIn(Dist.CLIENT)
    public static void onOverlay(RenderBlockOverlayEvent event)
    {
    	if (event.getOverlayType() == RenderBlockOverlayEvent.OverlayType.BLOCK)
    	{
    		PlayerEntity player = event.getPlayer();
	    	MatrixStack matrixStack = event.getMatrixStack();
    		if (event.getBlockForOverlay().getBlock().isIn(AerialHellTags.Blocks.SOLID_ETHER))
    		{
    			event.setCanceled(true);
    			if (event.getBlockForOverlay().getBlock() == AerialHellBlocksAndItems.WHITE_SOLID_ETHER.get()) {renderEther(player, matrixStack, TEXTURE_WHITE_SOLID_ETHER);}
    			else if (event.getBlockForOverlay().getBlock() == AerialHellBlocksAndItems.BLUE_SOLID_ETHER.get()) {renderEther(player, matrixStack, TEXTURE_BLUE_SOLID_ETHER);}
    			else if (event.getBlockForOverlay().getBlock() == AerialHellBlocksAndItems.GOLDEN_SOLID_ETHER.get()) {renderEther(player, matrixStack, TEXTURE_GOLDEN_SOLID_ETHER);}
    			else if (event.getBlockForOverlay().getBlock() == AerialHellBlocksAndItems.GREEN_SOLID_ETHER.get()) {renderEther(player, matrixStack, TEXTURE_GREEN_SOLID_ETHER);}
    		}
    	}
    }
    
    //function from net.minecraft.client.renderer.OverlayRenderer
    @OnlyIn(Dist.CLIENT)
    private static void renderEther(PlayerEntity player, MatrixStack matrixStackIn, ResourceLocation texture)
    {
        RenderSystem.enableTexture();
        Minecraft.getInstance().getTextureManager().bindTexture(texture); //Minecraft.getInstance() client side only !
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        float brightness = player.getBrightness();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        float yaw = -player.rotationYaw / 64.0F;
        float pitch = player.rotationPitch / 64.0F;
        Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR_TEX);
        bufferbuilder.pos(matrix4f, -1.0F, -1.0F, -0.5F).color(brightness, brightness, brightness, 1.0F).tex(4.0F + yaw, 4.0F + pitch).endVertex();
        bufferbuilder.pos(matrix4f, 1.0F, -1.0F, -0.5F).color(brightness, brightness, brightness, 1.0F).tex(0.0F + yaw, 4.0F + pitch).endVertex();
        bufferbuilder.pos(matrix4f, 1.0F, 1.0F, -0.5F).color(brightness, brightness, brightness, 1.0F).tex(0.0F + yaw, 0.0F + pitch).endVertex();
        bufferbuilder.pos(matrix4f, -1.0F, 1.0F, -0.5F).color(brightness, brightness, brightness, 1.0F).tex(4.0F + yaw, 0.0F + pitch).endVertex();
        bufferbuilder.finishDrawing();
        WorldVertexBufferUploader.draw(bufferbuilder);
        RenderSystem.disableBlend();
     }
}