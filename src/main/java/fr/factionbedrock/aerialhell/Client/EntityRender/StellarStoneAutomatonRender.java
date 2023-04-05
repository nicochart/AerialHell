package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.matrix.MatrixStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AutomatonModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.StellarStoneAutomatonEyesLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.AutomatonEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StellarStoneAutomatonRender<T extends AutomatonEntity> extends MobRenderer<T, AutomatonModel<T>>
{
	private static String name = "stellar_stone_automaton";
    private static final ResourceLocation texture = new ResourceLocation(AerialHell.MODID, "textures/entity/automaton/" + name + ".png");

    public StellarStoneAutomatonRender(EntityRendererManager manager)
    {
        super(manager, new AutomatonModel<T>(), 0.3f);
        this.addLayer(new StellarStoneAutomatonEyesLayer<>(this));
    }

    @Override public ResourceLocation getEntityTexture(T entity) {return texture;}
}