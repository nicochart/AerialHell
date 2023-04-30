package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.LunaticPriestModel;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LunaticPriestRender extends MobRenderer<LunaticPriestEntity, LunaticPriestModel>
{
	private static String name = "lunatic_priest";
    private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public LunaticPriestRender(EntityRendererManager manager)
    {
        super(manager, new LunaticPriestModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(LunaticPriestEntity entity) {return TEXTURE;}
}