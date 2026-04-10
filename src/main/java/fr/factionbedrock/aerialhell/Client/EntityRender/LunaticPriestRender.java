package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.LunaticPriestModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.LunaticPriestRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class LunaticPriestRender extends MobRenderer<LunaticPriestEntity, LunaticPriestRenderState, LunaticPriestModel>
{
	private static String name = "lunatic_priest";
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public LunaticPriestRender(EntityRendererProvider.Context context)
    {
        super(context, new LunaticPriestModel(context.bakeLayer(AerialHellModelLayers.LUNATIC_PRIEST)), 0.5f);
    }

    @Override public LunaticPriestRenderState createRenderState() {return new LunaticPriestRenderState();}

    @Override public void extractRenderState(LunaticPriestEntity entity, LunaticPriestRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.attackTimer = entity.attackTimer;
    }

    @Override public Identifier getTextureLocation(LunaticPriestRenderState renderState) {return TEXTURE;}
}