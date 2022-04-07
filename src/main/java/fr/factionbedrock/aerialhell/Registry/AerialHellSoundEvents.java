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
	
	public static final RegistryObject<SoundEvent> ENTITY_THROWING_KNIFE_SHOOT = register("entity.throwing_knife.throw");
	public static final RegistryObject<SoundEvent> ENTITY_VOLUCITE_BLOWPIPE_SHOOT = register("entity.volucite_blowpipe.shoot");
	
	public static final RegistryObject<SoundEvent> TRAPPED_BLOCK_STEP = register("block.trapped_block.step");
	public static final RegistryObject<SoundEvent> BLOCK_AERIAL_HELL_PORTAL_AMBIENT = register("block.aerial_hell_portal.ambient");
	
	public static final RegistryObject<SoundEvent> MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER = register("music_disc.sweden_andreas_zoeller");
	
	private static RegistryObject<SoundEvent> register(String name)
	{
		return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(AerialHell.MODID, name)));
	}
}