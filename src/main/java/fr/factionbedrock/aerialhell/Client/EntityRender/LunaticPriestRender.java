package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.LunaticPriestModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.LunaticPriestRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class LunaticPriestRender extends MobEntityRenderer<LunaticPriestEntity, LunaticPriestRenderState, LunaticPriestModel>
{
	private static String name = "lunatic_priest";
    private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public LunaticPriestRender(EntityRendererFactory.Context context)
    {
        super(context, new LunaticPriestModel(context.getPart(AerialHellModelLayers.LUNATIC_PRIEST)), 0.5f);
    }

    @Override public LunaticPriestRenderState createRenderState() {return new LunaticPriestRenderState();}

    @Override public void updateRenderState(LunaticPriestEntity entity, LunaticPriestRenderState renderState, float partialTick)
    {
        super.updateRenderState(entity, renderState, partialTick);
        renderState.attackTimer = entity.attackTimer;
    }

    @Override public Identifier getTexture(LunaticPriestRenderState renderState) {return TEXTURE;}
}