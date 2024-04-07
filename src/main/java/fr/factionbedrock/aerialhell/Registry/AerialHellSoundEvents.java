package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellSoundEvents
{
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, AerialHell.MODID);
	
	public static final RegistryObject<SoundEvent> ENTITY_EVIL_COW_AMBIENT = register("entity.evil_cow.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_EVIL_COW_HURT = register("entity.evil_cow.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_EVIL_COW_DEATH = register("entity.evil_cow.death");
	
	public static final RegistryObject<SoundEvent> ENTITY_SANDY_SHEEP_AMBIENT = register("entity.sandy_sheep.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_SANDY_SHEEP_HURT = register("entity.sandy_sheep.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_SANDY_SHEEP_DEATH = register("entity.sandy_sheep.death");
	public static final RegistryObject<SoundEvent> ENTITY_SANDY_SHEEP_SHEAR = register("entity.sandy_sheep.shear");

	public static final RegistryObject<SoundEvent> ENTITY_GLIDING_TURTLE_AMBIENT = register("entity.gliding_turtle.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_GLIDING_TURTLE_HURT = register("entity.gliding_turtle.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_GLIDING_TURTLE_DEATH = register("entity.gliding_turtle.death");

	public static final RegistryObject<SoundEvent> ENTITY_SNAKE_AMBIENT = register("entity.snake.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_SNAKE_HURT = register("entity.snake.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_SNAKE_DEATH = register("entity.snake.death");

	public static final RegistryObject<SoundEvent> ENTITY_KODAMA_RATTLE = register("entity.kodama.rattle");
	public static final RegistryObject<SoundEvent> ENTITY_KODAMA_AMBIENT = register("entity.kodama.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_KODAMA_HURT = register("entity.kodama.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_KODAMA_DEATH = register("entity.kodama.death");

	public static final RegistryObject<SoundEvent> ENTITY_STELLAR_STONE_AUTOMATON_ACTIVATION = register("entity.stellar_stone_automaton.activation");
	public static final RegistryObject<SoundEvent> ENTITY_STELLAR_STONE_AUTOMATON_HURT = register("entity.stellar_stone_automaton.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_STELLAR_STONE_AUTOMATON_DEATH = register("entity.stellar_stone_automaton.death");

	public static final RegistryObject<SoundEvent> ENTITY_VOLUCITE_GOLEM_AMBIENT = register("entity.volucite_golem.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_VOLUCITE_GOLEM_AMBIENT_ANGRY = register("entity.volucite_golem.ambient_angry");
	public static final RegistryObject<SoundEvent> ENTITY_VOLUCITE_GOLEM_AMBIENT_ALERT = register("entity.volucite_golem.ambient_alert");
	public static final RegistryObject<SoundEvent> ENTITY_VOLUCITE_GOLEM_HURT = register("entity.volucite_golem.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_VOLUCITE_GOLEM_SHOOT = register("entity.volucite_golem.shoot");

	public static final RegistryObject<SoundEvent> ENTITY_WARDEN_VOLUCITE_GOLEM_AMBIENT = register("entity.warden_volucite_golem.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_WARDEN_VOLUCITE_GOLEM_ACTIVATION = register("entity.warden_volucite_golem.activation");
	public static final RegistryObject<SoundEvent> ENTITY_WARDEN_VOLUCITE_GOLEM_HURT = register("entity.warden_volucite_golem.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_WARDEN_VOLUCITE_GOLEM_DEATH = register("entity.warden_volucite_golem.death");

	public static final RegistryObject<SoundEvent> ENTITY_KEEPER_OF_KEYS_AMBIENT = register("entity.keeper_of_volucite_keys.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_KEEPER_OF_KEYS_HURT = register("entity.keeper_of_volucite_keys.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_KEEPER_OF_KEYS_DEATH = register("entity.keeper_of_volucite_keys.death");

	public static final RegistryObject<SoundEvent> ENTITY_CHAINED_GOD_AMBIENT = register("entity.chained_god.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_CHAINED_GOD_HURT = register("entity.chained_god.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_CHAINED_GOD_DEATH = register("entity.chained_god.death");
	public static final RegistryObject<SoundEvent> ENTITY_CHAINED_GOD_FAST_DEATH = register("entity.chained_god.fast_death");
	public static final RegistryObject<SoundEvent> ENTITY_CHAINED_GOD_STEP = register("entity.chained_god.step");
	public static final RegistryObject<SoundEvent> ENTITY_CHAINED_GOD_ROAR = register("entity.chained_god.roar");
	public static final RegistryObject<SoundEvent> ENTITY_CHAINED_GOD_UNCHAIN = register("entity.chained_god.unchain");
	public static final RegistryObject<SoundEvent> ENTITY_CHAINED_GOD_TRANSITION = register("entity.chained_god.transition");

	public static final RegistryObject<SoundEvent> ENTITY_LUNATIC_PRIEST_AMBIENT = register("entity.lunatic_priest.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_LUNATIC_PRIEST_HURT = register("entity.lunatic_priest.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_LUNATIC_PRIEST_DEATH = register("entity.lunatic_priest.death");
	
	public static final RegistryObject<SoundEvent> ENTITY_LUNATIC_PROJECTILE_SHOOT = register("entity.lunatic_projectile.shoot");
	public static final RegistryObject<SoundEvent> ENTITY_LUNATIC_PROJECTILE_DISAPPEAR = register("entity.lunatic_projectile.disappear");

	public static final RegistryObject<SoundEvent> ENTITY_SHADOW_PROJECTILE_SHOOT = register("entity.shadow_projectile.shoot");
	
	public static final RegistryObject<SoundEvent> ENTITY_TORN_SPIRIT_AMBIENT = register("entity.torn_spirit.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_TORN_SPIRIT_HURT = register("entity.torn_spirit.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_TORN_SPIRIT_DEATH = register("entity.torn_spirit.death");
	
	public static final RegistryObject<SoundEvent> ENTITY_ICE_SPIRIT_AMBIENT = register("entity.ice_spirit.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_ICE_SPIRIT_HURT = register("entity.ice_spirit.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_ICE_SPIRIT_DEATH = register("entity.ice_spirit.death");
	
	public static final RegistryObject<SoundEvent> ENTITY_FIRE_SPIRIT_HURT = register("entity.fire_spirit.hurt");
	
	public static final RegistryObject<SoundEvent> ENTITY_ELECTRO_SPIRIT_AMBIENT = register("entity.electro_spirit.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_ELECTRO_SPIRIT_HURT = register("entity.electro_spirit.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_ELECTRO_SPIRIT_DEATH = register("entity.electro_spirit.death");
	
	public static final RegistryObject<SoundEvent> ENTITY_SHADOW_TROLL_AMBIENT = register("entity.shadow_troll.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_SHADOW_TROLL_HURT = register("entity.shadow_troll.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_SHADOW_TROLL_DEATH = register("entity.shadow_troll.death");

	public static final RegistryObject<SoundEvent> ENTITY_SHADOW_AUTOMATON_AMBIENT = register("entity.shadow_automaton.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_SHADOW_AUTOMATON_HURT = register("entity.shadow_automaton.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_SHADOW_AUTOMATON_DEATH = register("entity.shadow_automaton.death");

	public static final RegistryObject<SoundEvent> ENTITY_LILITH_TRANSFORMATION = register("entity.lilith.transformation");
	public static final RegistryObject<SoundEvent> ENTITY_LILITH_HURT = register("entity.lilith.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_LILITH_DEATH = register("entity.lilith.death");
	
	public static final RegistryObject<SoundEvent> ENTITY_FOREST_CATERPILLAR_AMBIENT = register("entity.forest_caterpillar.ambient");
	
	public static final RegistryObject<SoundEvent> ENTITY_FLYING_JELLYFISH_AMBIENT = register("entity.flying_jellyfish.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_FLYING_JELLYFISH_HURT = register("entity.flying_jellyfish.hurt");
	
	public static final RegistryObject<SoundEvent> ENTITY_SHURIKEN_SHOOT = register("entity.shuriken.throw");
	public static final RegistryObject<SoundEvent> ENTITY_VOLUCITE_BLOWPIPE_SHOOT = register("entity.volucite_blowpipe.shoot");
	
	public static final RegistryObject<SoundEvent> ITEM_FORGOTTEN_BATTLE_TRIDENT_USE = register("item.forgotten_battle_trident.use");
	
	public static final RegistryObject<SoundEvent> TRAPPED_BLOCK_STEP = register("block.trapped_block.step");
	public static final RegistryObject<SoundEvent> BLOCK_AERIAL_HELL_PORTAL_AMBIENT = register("block.aerial_hell_portal.ambient");
	public static final RegistryObject<SoundEvent> BLOCK_BONE_PILE_STEP_BREAK = register("block.bone_pile_block.step_break");
	
	public static final RegistryObject<SoundEvent> MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP = register("music_disc.aerial_hell_theme_tommaup");
	public static final RegistryObject<SoundEvent> MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER = register("music_disc.sweden_andreas_zoeller");
	public static final RegistryObject<SoundEvent> MUSIC_DISC_ENTHUSIAST_TOURS = register("music_disc.enthusiast_tours");
	public static final RegistryObject<SoundEvent> MUSIC_DISC_BMINOR_ARULO = register("music_disc.bminor_arulo");
	public static final RegistryObject<SoundEvent> MUSIC_DISC_RETRO_EXPLORATION_TOMMAUP = register("music_disc.retro_exploration_tommaup");

	public static final RegistryObject<SoundEvent> AERIALHELL_DIMENSION_MUSIC = register("aerialhell.dimension_music");
	public static final RegistryObject<SoundEvent> CRYSTAL_MUSIC = register("aerialhell.crystal_music");
	public static final RegistryObject<SoundEvent> SHADOW_MUSIC = register("aerialhell.shadow_music");

	public static final RegistryObject<SoundEvent> ADVANCEMENT_CLASSIC = register("advancement.classic");
	public static final RegistryObject<SoundEvent> ADVANCEMENT_STORY = register("advancement.story");
	public static final RegistryObject<SoundEvent> ADVANCEMENT_SECRET = register("advancement.secret");
	public static final RegistryObject<SoundEvent> ADVANCEMENT_LUNATIC = register("advancement.lunatic");
	
	private static RegistryObject<SoundEvent> register(String name)
	{
		return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AerialHell.MODID, name)));
	}
}