package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.SandySheepModel;
import fr.factionbedrock.aerialhell.Entity.Passive.SandySheepEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SandySheepRender extends MobRenderer<SandySheepEntity, SandySheepModel>
{
	private static String name = "sandy_sheep";
    private static final ResourceLocation SANDYSHEEP_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/"+ name +"/" + name + ".png");
    
    public SandySheepRender(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new SandySheepModel(), 0.75F);
    }

    @Override
    public ResourceLocation getEntityTexture(SandySheepEntity entity)
    {
        return SANDYSHEEP_TEXTURE;
    }
}