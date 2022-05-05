package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.ForestCaterpillarModel;
import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import fr.factionbedrock.aerialhell.Entity.Neutral.ForestCaterpillarEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CaterpillarRender<T extends AbstractCaterpillarEntity> extends MobRenderer<T, ForestCaterpillarModel<T>>
{
	private static String name_forest = "forest_caterpillar";
	private static String name_crystal = "crystal_caterpillar";
	private static final ResourceLocation FOREST = new ResourceLocation(AerialHell.MODID, "textures/entity/caterpillar/" + name_forest + ".png");
	private static final ResourceLocation CRYSTAL = new ResourceLocation(AerialHell.MODID, "textures/entity/caterpillar/" + name_crystal + ".png");

    public CaterpillarRender(EntityRendererManager manager)
    {
        super(manager, new ForestCaterpillarModel<T>(), 0.2f);
    }

    @Override
    public ResourceLocation getEntityTexture(T entity)
    {
    	if (entity instanceof ForestCaterpillarEntity)
    	{
    		return FOREST;
    	}
    	else
    	{
    		return CRYSTAL;
    	}
    }
}
