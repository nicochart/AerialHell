package fr.factionbedrock.aerialhell.Event.Listeners;

import com.mojang.blaze3d.vertex.*;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Block.DungeonCores.DungeonCoreBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.client.renderer.Lightmap;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.event.RenderBlockScreenEffectEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.joml.Matrix4f;

public class BlockEventListener
{
    public static void onNeighborNotifyEvent(BlockEvent.NeighborNotifyEvent event)
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
	                world.setBlockAndUpdate(pos, AerialHellBlocks.CRYSTAL_BLOCK.get().defaultBlockState());
	                world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
	                event.setCanceled(true);
	            }
	            else if (blockstate.is(Blocks.MAGMA_BLOCK))
	            {
	            	world.setBlockAndUpdate(pos, AerialHellBlocks.MAGMATIC_GEL_ORE.get().defaultBlockState());
	            	world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
	            	event.setCanceled(true);
	            }
	            else if (blockstate.is(Blocks.FIRE))
	            {
	            	world.setBlockAndUpdate(pos, AerialHellBlocks.CRYSTALLIZED_FIRE.get().defaultBlockState());
	            	world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
	            	if (world.getBlockState(pos.below()).getBlock() == Blocks.AIR)
	            	{
	            		world.destroyBlock(pos, true);
	            	}
	            	event.setCanceled(true);
	            }
	            else if (blockstate.is(Blocks.TORCH))
	            {
                    world.setBlockAndUpdate(pos, AerialHellBlocks.CRYSTALLIZED_TORCH.get().defaultBlockState());
	            	world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
	            	event.setCanceled(true);
	            }
                else if (blockstate.is(Blocks.WALL_TORCH))
                {
                    world.setBlockAndUpdate(pos, AerialHellBlocks.CRYSTALLIZED_WALL_TORCH.get().defaultBlockState().setValue(WallTorchBlock.FACING, blockstate.getValue(WallTorchBlock.FACING)));
                    world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
                    event.setCanceled(true);
                }
                else if (blockstate.is(Blocks.LANTERN))
                {
                    world.setBlockAndUpdate(pos, AerialHellBlocks.CRYSTALLIZED_LANTERN.get().defaultBlockState().setValue(LanternBlock.HANGING, blockstate.getValue(LanternBlock.HANGING)).setValue(LanternBlock.WATERLOGGED, blockstate.getValue(LanternBlock.WATERLOGGED)));
                    world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
                    event.setCanceled(true);
                }
                else if (blockstate.is(AerialHellBlocks.RUBY_LANTERN))
                {
                    world.setBlockAndUpdate(pos, AerialHellBlocks.RUBY_CRYSTALLIZED_LANTERN.get().defaultBlockState().setValue(LanternBlock.HANGING, blockstate.getValue(LanternBlock.HANGING)).setValue(LanternBlock.WATERLOGGED, blockstate.getValue(LanternBlock.WATERLOGGED)));
                    world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
                    event.setCanceled(true);
                }
                else if (blockstate.is(AerialHellBlocks.VOLUCITE_LANTERN))
                {
                    world.setBlockAndUpdate(pos, AerialHellBlocks.VOLUCITE_CRYSTALLIZED_LANTERN.get().defaultBlockState().setValue(LanternBlock.HANGING, blockstate.getValue(LanternBlock.HANGING)).setValue(LanternBlock.WATERLOGGED, blockstate.getValue(LanternBlock.WATERLOGGED)));
                    world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
                    event.setCanceled(true);
                }
            }
        }
    }

    public static void onEntityPlaceEvent(BlockEvent.EntityPlaceEvent event)
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

    public static void onOverlay(RenderBlockScreenEffectEvent event)
    {
    	if (event.getOverlayType() == RenderBlockScreenEffectEvent.OverlayType.BLOCK)
    	{
    		Player player = event.getPlayer();
	    	PoseStack matrixStack = event.getPoseStack();
            SubmitNodeCollector submitNodeCollector = event.getSubmitNodeCollector();
            BlockState state = event.getBlockState();
    		if (event.getBlockState().is(AerialHellTags.Blocks.SOLID_ETHER))
    		{
    			event.setCanceled(true);
    			if (state.is(AerialHellBlocks.WHITE_SOLID_ETHER.get())) {renderCustomOverlay(player, submitNodeCollector, matrixStack, getBlockTextureLocation(AerialHellBlocks.WHITE_SOLID_ETHER));}
    			else if (state.is(AerialHellBlocks.BLUE_SOLID_ETHER.get())) {renderCustomOverlay(player, submitNodeCollector, matrixStack, getBlockTextureLocation(AerialHellBlocks.BLUE_SOLID_ETHER));}
    			else if (state.is(AerialHellBlocks.GOLDEN_SOLID_ETHER.get())) {renderCustomOverlay(player, submitNodeCollector, matrixStack, getBlockTextureLocation(AerialHellBlocks.GOLDEN_SOLID_ETHER));}
                else if (state.is(AerialHellBlocks.GREEN_SOLID_ETHER.get())) {renderCustomOverlay(player, submitNodeCollector, matrixStack, getBlockTextureLocation(AerialHellBlocks.GREEN_SOLID_ETHER));}
                else if (state.is(AerialHellBlocks.PURPLE_SOLID_ETHER.get())) {renderCustomOverlay(player, submitNodeCollector, matrixStack, getBlockTextureLocation(AerialHellBlocks.PURPLE_SOLID_ETHER));}
    		}

            else if (state.is(AerialHellTags.Blocks.GHOST_BLOCK) && !state.is(AerialHellTags.Blocks.GHOST_BLOCK_NO_OVERLAY))
            {
                event.setCanceled(true);
                if (state.is(AerialHellTags.Blocks.STONE_GHOST_BLOCK))
                {
                    if (state.is(AerialHellBlocks.GHOST_RUBY_BLOCK.get())) {renderCustomOverlay(player, submitNodeCollector, matrixStack, getBlockTextureLocation(AerialHellBlocks.GHOST_RUBY_BLOCK));}
                    else if (state.is(AerialHellBlocks.GHOST_FLUORITE_BLOCK.get())) {renderCustomOverlay(player, submitNodeCollector, matrixStack, getBlockTextureLocation(AerialHellBlocks.GHOST_FLUORITE_BLOCK));}
                    else if (state.is(AerialHellBlocks.GHOST_AZURITE_BLOCK.get())) {renderCustomOverlay(player, submitNodeCollector, matrixStack, getBlockTextureLocation(AerialHellBlocks.GHOST_AZURITE_BLOCK));}
                    else if (state.is(AerialHellBlocks.GHOST_GOLD_BLOCK.get())) {renderCustomOverlay(player, submitNodeCollector, matrixStack, getBlockTextureLocation(AerialHellBlocks.GHOST_GOLD_BLOCK));}
                    else if (state.is(AerialHellBlocks.GHOST_STELLAR_COBBLESTONE.get()) || state.is(AerialHellBlocks.GHOST_STELLAR_FURNACE.get())) {renderCustomOverlay(player, submitNodeCollector, matrixStack, getBlockTextureLocation(AerialHellBlocks.GHOST_STELLAR_COBBLESTONE));}
                    else {renderCustomOverlay(player, submitNodeCollector, matrixStack, getBlockTextureLocation(AerialHellBlocks.GHOST_STELLAR_COBBLESTONE));}
                }
                else if (state.is(AerialHellBlocks.GHOST_BOAT_WOOL.get())) {renderCustomOverlay(player, submitNodeCollector, matrixStack, getBlockTextureLocation(AerialHellBlocks.GHOST_BOAT_WOOL));}
                else //if (state.is(AerialHellTags.Blocks.WOODEN_GHOST_BLOCK))
                {
                    if (state.is(AerialHellBlocks.GHOST_BOAT_WOOD.get()) || state.is(AerialHellBlocks.GHOST_BOAT_LOG.get())) {renderCustomOverlay(player, submitNodeCollector, matrixStack, getBlockTextureLocation(AerialHellBlocks.GHOST_BOAT_LOG));}
                    else {renderCustomOverlay(player, submitNodeCollector, matrixStack, getBlockTextureLocation(AerialHellBlocks.GHOST_BOAT_PLANKS));}
                }
            }

            else if (state.is(AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK.get()))
            {
                event.setCanceled(true);
                renderCustomOverlay(player, submitNodeCollector, matrixStack, getBlockTextureLocation(AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK));
            }
    	}
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