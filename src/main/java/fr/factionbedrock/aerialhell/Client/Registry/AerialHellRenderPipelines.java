package fr.factionbedrock.aerialhell.Client.Registry;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.renderpearl.api.pipeline.BlendFunction;
import com.mojang.renderpearl.api.pipeline.ColorTargetState;
import com.mojang.renderpearl.api.pipeline.CompareOp;
import com.mojang.renderpearl.api.pipeline.DepthStencilState;
import com.mojang.renderpearl.api.pipeline.RenderPipeline;
import com.mojang.renderpearl.api.textures.FilterMode;
import fr.factionbedrock.aerialhell.AerialHell;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.reloader.ResourceReloaderKeys;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.blockentity.AbstractEndPortalRenderer;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;

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

    //particles and vanilla end sky (nebula) textures, repeated (world anchored coordinates), no filtering (pixel particles)
    public static final Identifier VOLUCITE_BARRIER_PARTICLES_LOCATION = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/volucite_barrier/particles.png");
    public static final RenderType VOLUCITE_BARRIER_RENDER_TYPE = RenderType.create("aerialhell_volucite_barrier", RenderSetup.builder(VOLUCITE_BARRIER)
            .withTexture("Sampler0", VOLUCITE_BARRIER_PARTICLES_LOCATION, () -> RenderSystem.getSamplerCache().getRepeat(FilterMode.NEAREST))
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

    //compiles the pipeline after each shaders reload, and remembers if it failed
    //neoforge : registered with RegisterRenderPipelinesEvent.registerOptionalPipeline
    public static void registerReloadListener()
    {
        Identifier key = AerialHell.id("optional_pipelines_availability");
        ResourceLoader resourceLoader = ResourceLoader.get(PackType.CLIENT_RESOURCES);
        resourceLoader.registerReloadListener(key, (ResourceManagerReloadListener) resourceManager -> voluciteBarrierFailed = RenderSystem.getCompiledPipelineNullable(VOLUCITE_BARRIER) == null);
        resourceLoader.addListenerOrdering(ResourceReloaderKeys.Client.SHADERS, key);
    }
}
