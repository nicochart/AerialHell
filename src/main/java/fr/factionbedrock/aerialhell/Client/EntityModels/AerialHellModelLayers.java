package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class AerialHellModelLayers
{
    public static final ModelLayerLocation CATERPILLAR = createEntityModelLayer("caterpillar");
    public static final ModelLayerLocation BOAR = createEntityModelLayer("boar");
    public static final ModelLayerLocation CHAINED_GOD = createEntityModelLayer("chained_god");
    public static final ModelLayerLocation CHEST_MIMIC = createEntityModelLayer("chest_mimic");
    public static final ModelLayerLocation CRYSTAL_GOLEM = createEntityModelLayer("crystal_golem");
    public static final ModelLayerLocation CRYSTAL_SLIME = createEntityModelLayer("crystal_slime");
    public static final ModelLayerLocation ELEMENT_SPIRIT = createEntityModelLayer("element_spirit");
    public static final ModelLayerLocation FLYING_JELLYFISH = createEntityModelLayer( "flying_jellyfish");
    public static final ModelLayerLocation GLIDING_TURTLE = createEntityModelLayer("gliding_turtle");
    public static final ModelLayerLocation KODAMA = createEntityModelLayer("kodama");
    public static final ModelLayerLocation LILITH = createEntityModelLayer("lilith");
    public static final ModelLayerLocation LUNATIC_PRIEST = createEntityModelLayer("lunatic_priest");
    public static final ModelLayerLocation MUD_CYCLE_MAGE = createEntityModelLayer("mud_cycle_mage");
    public static final ModelLayerLocation VOLUCITE_GOLEM = createEntityModelLayer("volucite_golem");
    public static final ModelLayerLocation VOLUCITE_GOLEM_HEAD = createEntityModelLayer("volucite_golem_head");
    public static final ModelLayerLocation MUD_GOLEM = createEntityModelLayer("mud_golem");
    public static final ModelLayerLocation SANDY_SHEEP = createEntityModelLayer("sandy_sheep");
    public static final ModelLayerLocation SHADOW_FLYING_SKULL = createEntityModelLayer("shadow_flying_skull");
    public static final ModelLayerLocation SHADOW_TROLL = createEntityModelLayer("shadow_troll");
    public static final ModelLayerLocation SHROOMBOOM = createEntityModelLayer("shroomboom");
    public static final ModelLayerLocation SPIDER_BARREL_MIMIC = createEntityModelLayer("spider_barrel_mimic");
    public static final ModelLayerLocation AUTOMATON = createEntityModelLayer("automaton");
    public static final ModelLayerLocation TORN_SPIRIT = createEntityModelLayer("torn_spirit");
    public static final ModelLayerLocation VERDIGRIS_ZOMBIE = createEntityModelLayer("verdigris_zombie");
    public static final ModelLayerLocation SLIME_PIRATE = createEntityModelLayer("slime_pirate");
    public static final ModelLayerLocation ENT = createEntityModelLayer("ent");
    public static final ModelLayerLocation SNAKE = createEntityModelLayer("snake");
    public static final ModelLayerLocation STELLAR_CHICKEN = createEntityModelLayer("stellar_chicken");
    public static final ModelLayerLocation EMPTY = createEntityModelLayer("empty");

    public static final ModelLayerLocation CORTINARIUS_COW_SHROOM = createEntityModelLayer( "cortinarius_cow_shroom", "shrooms");
    public static final ModelLayerLocation CRYSTAL_GOLEM_CRYSTAL = createEntityModelLayer("crystal_golem_crystal", "crystals");
    public static final ModelLayerLocation SPIDER_SPIKE = createEntityModelLayer("spider_spike", "spikes");

    private static ModelLayerLocation createEntityModelLayer(String id) {return createEntityModelLayer(id, "main");}

    private static ModelLayerLocation createEntityModelLayer(String id, String layer)
    {
        return new ModelLayerLocation(Identifier.fromNamespaceAndPath(AerialHell.MODID, id), layer);
    }
}
