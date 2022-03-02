package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.ForestCaterpillarModel;
import fr.factionbedrock.aerialhell.Entity.Monster.ForestCaterpillarEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ForestCaterpillarRender extends MobRenderer<ForestCaterpillarEntity, ForestCaterpillarModel<ForestCaterpillarEntity>>
{
	private static String name = "forest_caterpillar";
    private static final ResourceLocation texture = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public ForestCaterpillarRender(EntityRendererManager manager)
    {
        super(manager, new ForestCaterpillarModel(), 0.2f);
    }

    @Override
    public ResourceLocation getEntityTexture(ForestCaterpillarEntity entity)
    {return texture;}
}
