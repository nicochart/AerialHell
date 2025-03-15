package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class AerialHellModelLayers
{
    public static final EntityModelLayer CATERPILLAR = createEntityModelLayer("caterpillar");
    public static final EntityModelLayer BOAR = createEntityModelLayer("boar");
    public static final EntityModelLayer CHAINED_GOD = createEntityModelLayer("chained_god");
    public static final EntityModelLayer CHEST_MIMIC = createEntityModelLayer("chest_mimic");
    public static final EntityModelLayer CRYSTAL_GOLEM = createEntityModelLayer("crystal_golem");
    public static final EntityModelLayer CRYSTAL_SLIME = createEntityModelLayer("crystal_slime");
    public static final EntityModelLayer ELEMENT_SPIRIT = createEntityModelLayer("element_spirit");
    public static final EntityModelLayer FLYING_JELLYFISH = createEntityModelLayer( "flying_jellyfish");
    public static final EntityModelLayer GLIDING_TURTLE = createEntityModelLayer("gliding_turtle");
    public static final EntityModelLayer KODAMA = createEntityModelLayer("kodama");
    public static final EntityModelLayer LILITH = createEntityModelLayer("lilith");
    public static final EntityModelLayer LUNATIC_PRIEST = createEntityModelLayer("lunatic_priest");
    public static final EntityModelLayer MUD_CYCLE_MAGE = createEntityModelLayer("mud_cycle_mage");
    public static final EntityModelLayer MUD_GOLEM = createEntityModelLayer("mud_golem");
    public static final EntityModelLayer SANDY_SHEEP = createEntityModelLayer("sandy_sheep");
    public static final EntityModelLayer SHADOW_FLYING_SKULL = createEntityModelLayer("shadow_flying_skull");
    public static final EntityModelLayer SHADOW_TROLL = createEntityModelLayer("shadow_troll");
    public static final EntityModelLayer SHROOMBOOM = createEntityModelLayer("shroomboom");
    public static final EntityModelLayer SPIDER_BARREL_MIMIC = createEntityModelLayer("spider_barrel_mimic");
    public static final EntityModelLayer AUTOMATON = createEntityModelLayer("automaton");
    public static final EntityModelLayer TORN_SPIRIT = createEntityModelLayer("torn_spirit");
    public static final EntityModelLayer VERDIGRIS_ZOMBIE = createEntityModelLayer("verdigris_zombie");
    public static final EntityModelLayer SLIME_PIRATE = createEntityModelLayer("slime_pirate");
    public static final EntityModelLayer ENT = createEntityModelLayer("ent");
    public static final EntityModelLayer SNAKE = createEntityModelLayer("snake");
    public static final EntityModelLayer STELLAR_CHICKEN = createEntityModelLayer("stellar_chicken");
    public static final EntityModelLayer EMPTY = createEntityModelLayer("empty");

    public static final EntityModelLayer CORTINARIUS_COW_SHROOM = createEntityModelLayer( "cortinarius_cow_shroom", "shrooms");
    public static final EntityModelLayer CRYSTAL_GOLEM_CRYSTAL = createEntityModelLayer("crystal_golem_crystal", "crystals");
    public static final EntityModelLayer SPIDER_SPIKE = createEntityModelLayer("spider_spike", "spikes");

    private static EntityModelLayer createEntityModelLayer(String id) {return createEntityModelLayer(id, "main");}

    private static EntityModelLayer createEntityModelLayer(String id, String layer)
    {
        return new EntityModelLayer(Identifier.of(AerialHell.MODID, id), layer);
    }
}
