package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.LunaticPriestModel;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LunaticPriestRender extends MobRenderer<LunaticPriestEntity, LunaticPriestModel>
{
	private static String name = "lunatic_priest";
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public LunaticPriestRender(EntityRendererProvider.Context context)
    {
        super(context, new LunaticPriestModel(context.bakeLayer(AerialHellModelLayers.LUNATIC_PRIEST)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(LunaticPriestEntity entity) {return TEXTURE;}
}