package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
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
	
	public static final RegistryObject<SoundEvent> ENTITY_CHAINED_GOD_AMBIENT = register("entity.chained_god.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_CHAINED_GOD_HURT = register("entity.chained_god.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_CHAINED_GOD_DEATH = register("entity.chained_god.death");
	public static final RegistryObject<SoundEvent> ENTITY_CHAINED_GOD_STEP = register("entity.chained_god.step");
	public static final RegistryObject<SoundEvent> ENTITY_CHAINED_GOD_ROAR = register("entity.chained_god.roar");
	
	public static final RegistryObject<SoundEvent> ENTITY_LUNATIC_PRIEST_AMBIENT = register("entity.lunatic_priest.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_LUNATIC_PRIEST_HURT = register("entity.lunatic_priest.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_LUNATIC_PRIEST_DEATH = register("entity.lunatic_priest.death");
	
	public static final RegistryObject<SoundEvent> ENTITY_LUNATIC_PROJECTILE_SHOOT = register("entity.lunatic_projectile.shoot");
	public static final RegistryObject<SoundEvent> ENTITY_LUNATIC_PROJECTILE_DISAPPEAR = register("entity.lunatic_projectile.disappear");
	
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
	
	public static final RegistryObject<SoundEvent> ENTITY_FOREST_CATERPILLAR_AMBIENT = register("entity.forest_caterpillar.ambient");
	
	public static final RegistryObject<SoundEvent> ENTITY_FLYING_JELLYFISH_AMBIENT = register("entity.flying_jellyfish.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_FLYING_JELLYFISH_HURT = register("entity.flying_jellyfish.hurt");
	
	public static final RegistryObject<SoundEvent> ENTITY_THROWING_KNIFE_SHOOT = register("entity.throwing_knife.throw");
	public static final RegistryObject<SoundEvent> ENTITY_VOLUCITE_BLOWPIPE_SHOOT = register("entity.volucite_blowpipe.shoot");
	
	public static final RegistryObject<SoundEvent> ITEM_FORGOTTEN_BATTLE_TRIDENT_USE = register("item.forgotten_battle_trident.use");
	
	public static final RegistryObject<SoundEvent> TRAPPED_BLOCK_STEP = register("block.trapped_block.step");
	public static final RegistryObject<SoundEvent> BLOCK_AERIAL_HELL_PORTAL_AMBIENT = register("block.aerial_hell_portal.ambient");
	
	public static final RegistryObject<SoundEvent> MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER = register("music_disc.sweden_andreas_zoeller");
	
	public static final RegistryObject<SoundEvent> AERIALHELL_DIMENSION_MUSIC = register("aerialhell.dimension_music");
	
	private static RegistryObject<SoundEvent> register(String name)
	{
		return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(AerialHell.MODID, name)));
	}
}