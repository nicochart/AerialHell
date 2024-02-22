package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class AerialHellModelLayers
{
    public static final ModelLayerLocation CATERPILLAR = createModelLayerLocation("caterpillar");
    public static final ModelLayerLocation BOAR = createModelLayerLocation("boar");
    public static final ModelLayerLocation CHAINED_GOD = createModelLayerLocation("chained_god");
    public static final ModelLayerLocation CHEST_MIMIC = createModelLayerLocation("chest_mimic");
    public static final ModelLayerLocation CRYSTAL_GOLEM = createModelLayerLocation("crystal_golem");
    public static final ModelLayerLocation CRYSTAL_SLIME = createModelLayerLocation("crystal_slime");
    public static final ModelLayerLocation ELEMENT_SPIRIT = createModelLayerLocation("element_spirit");
    public static final ModelLayerLocation FLYING_JELLYFISH = createModelLayerLocation( "flying_jellyfish");
    public static final ModelLayerLocation GLIDING_TURTLE = createModelLayerLocation("gliding_turtle");
    public static final ModelLayerLocation KODAMA = createModelLayerLocation("kodama");
    public static final ModelLayerLocation LILITH = createModelLayerLocation("lilith");
    public static final ModelLayerLocation LUNATIC_PRIEST = createModelLayerLocation("lunatic_priest");
    public static final ModelLayerLocation MUD_CYCLE_MAGE = createModelLayerLocation("mud_cycle_mage");
    public static final ModelLayerLocation MUD_GOLEM = createModelLayerLocation("mud_golem");
    public static final ModelLayerLocation SANDY_SHEEP = createModelLayerLocation("sandy_sheep");
    public static final ModelLayerLocation SHADOW_FLYING_SKULL = createModelLayerLocation("shadow_flying_skull");
    public static final ModelLayerLocation SHADOW_TROLL = createModelLayerLocation("shadow_troll");
    public static final ModelLayerLocation SHROOMBOOM = createModelLayerLocation("shroomboom");
    public static final ModelLayerLocation SPIDER_BARREL_MIMIC = createModelLayerLocation("spider_barrel_mimic");
    public static final ModelLayerLocation AUTOMATON = createModelLayerLocation("automaton");
    public static final ModelLayerLocation TORN_SPIRIT = createModelLayerLocation("torn_spirit");
    public static final ModelLayerLocation VERDIGRIS_ZOMBIE = createModelLayerLocation("verdigris_zombie");
    public static final ModelLayerLocation SLIME_PIRATE = createModelLayerLocation("slime_pirate");
    public static final ModelLayerLocation ENT = createModelLayerLocation("ent");

    public static final ModelLayerLocation CORTINARIUS_COW_SHROOM = createModelLayerLocation( "cortinarius_cow_shroom", "shrooms");
    public static final ModelLayerLocation CRYSTAL_GOLEM_CRYSTAL = createModelLayerLocation("crystal_golem_crystal", "crystals");
    public static final ModelLayerLocation SPIDER_SPIKE = createModelLayerLocation("spider_spike", "spikes");

    private static ModelLayerLocation createModelLayerLocation(String id) {return createModelLayerLocation(id, "main");}

    private static ModelLayerLocation createModelLayerLocation(String id, String layer)
    {
        return new ModelLayerLocation(new ResourceLocation(AerialHell.MODID, id), layer);
    }
}
