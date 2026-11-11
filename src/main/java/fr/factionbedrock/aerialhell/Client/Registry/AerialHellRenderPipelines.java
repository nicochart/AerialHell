package fr.factionbedrock.aerialhell.Client.Registry;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.renderpearl.api.pipeline.BlendFunction;
import com.mojang.renderpearl.api.pipeline.ColorTargetState;
import com.mojang.renderpearl.api.pipeline.CompareOp;
import com.mojang.renderpearl.api.pipeline.DepthStencilState;
import com.mojang.renderpearl.api.pipeline.RenderPipeline;
import com.mojang.renderpearl.api.textures.FilterMode;
import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.blockentity.AbstractEndPortalRenderer;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.neoforged.neoforge.client.event.AddClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.RegisterRenderPipelinesEvent;
import net.neoforged.neoforge.client.resources.VanillaClientListeners;

public class AerialHellRenderPipelines
{
    //volucite barrier depth illusion : particle layers behind the visible faces, anchored in the world (parallax), see shaders/core/volucite_barrier.vsh & .fsh
    //vanilla end portal snippet for the bindings (globals, projection, transforms, fog, 2 samplers) and the position only vertex format
    //premultiplied alpha : the shader output alpha is the opacity of the dark void (what is behind stays partly visible), the particles are added on top
    //no depth write : it does not hide what is drawn after it
    public static final RenderPipeline VOLUCITE_BARRIER = RenderPipeline.builder(RenderPipelines.END_PORTAL_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath(AerialHell.MODID, "pipeline/volucite_barrier"))
            .withVertexShader(Identifier.fromNamespaceAndPath(AerialHell.MODID, "core/volucite_barrier"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(AerialHell.MODID, "core/volucite_barrier"))
            .withColorTargetState(new ColorTargetState(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA))
            .withDepthStencilState(new DepthStencilState(CompareOp.GREATER_THAN_OR_EQUAL, false))
            .build();

    //vanilla end portal (particles) and end sky (nebula) textures, repeated (world anchored coordinates), no filtering (pixel particles)
    public static final RenderType VOLUCITE_BARRIER_RENDER_TYPE = RenderType.create("aerialhell_volucite_barrier", RenderSetup.builder(VOLUCITE_BARRIER)
            .withTexture("Sampler0", AbstractEndPortalRenderer.END_PORTAL_LOCATION, () -> RenderSystem.getSamplerCache().getRepeat(FilterMode.NEAREST))
            .withTexture("Sampler1", AbstractEndPortalRenderer.END_SKY_LOCATION, () -> RenderSystem.getSamplerCache().getRepeat(FilterMode.NEAREST))
            .sortOnUpload()
            .createRenderSetup());

    //the pipeline is optional : if its shaders fail to compile (graphics driver), the game does not crash, the effect is just not drawn (the block model stays)
    //drawing with a pipeline that failed would throw, and asking for it again tries to compile it again : the failure is remembered until the next shaders reload
    private static boolean voluciteBarrierFailed = false;

    public static boolean isVoluciteBarrierAvailable() //render thread
    {
        if (!voluciteBarrierFailed && RenderSystem.getCompiledPipelineNullable(VOLUCITE_BARRIER) == null) {voluciteBarrierFailed = true;}
        return !voluciteBarrierFailed;
    }

    public static void registerPipelines(RegisterRenderPipelinesEvent event)
    {
        event.registerOptionalPipeline(VOLUCITE_BARRIER);
    }

    //after each shaders reload (pipelines compiled again), the availability of optional pipelines is checked again
    public static void addReloadListeners(AddClientReloadListenersEvent event)
    {
        Identifier key = Identifier.fromNamespaceAndPath(AerialHell.MODID, "optional_pipelines_availability");
        event.addListener(key, (ResourceManagerReloadListener) resourceManager -> voluciteBarrierFailed = false);
        event.addDependency(VanillaClientListeners.SHADERS, key);
    }
}
