package fr.factionbedrock.aerialhell.Registry.Entities;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.*;
import fr.factionbedrock.aerialhell.Entity.Bosses.*;
import fr.factionbedrock.aerialhell.Entity.Monster.*;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.*;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.*;
import fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit.*;
import fr.factionbedrock.aerialhell.Entity.Monster.Flying.*;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.*;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.*;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.*;
import fr.factionbedrock.aerialhell.Entity.Monster.Snake.*;
import fr.factionbedrock.aerialhell.Entity.Monster.Spider.*;
import fr.factionbedrock.aerialhell.Entity.Monster.VoluciteGolem.*;
import fr.factionbedrock.aerialhell.Entity.Neutral.*;
import fr.factionbedrock.aerialhell.Entity.Passive.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class AerialHellEntities
{
	public static final EntityType<StellarStoneAutomatonEntity> STELLAR_STONE_AUTOMATON = register(Keys.STELLAR_STONE_AUTOMATON, StellarStoneAutomatonEntity::new, 0.9F,2.1F, MobCategory.MONSTER, true);
	public static final EntityType<VoluciteGolemEntity> VOLUCITE_GOLEM = register(Keys.VOLUCITE_GOLEM, VoluciteGolemEntity::new, 1.3F,2.1F, MobCategory.MONSTER, true);
	public static final EntityType<VoluciteGolemHeadEntity> VOLUCITE_GOLEM_HEAD = register(Keys.VOLUCITE_GOLEM_HEAD, VoluciteGolemHeadEntity::new, 0.6F,0.6F, 0.3F, MobCategory.MISC, true);
	public static final EntityType<MudGolemEntity> MUD_GOLEM = register(Keys.MUD_GOLEM, MudGolemEntity::new, 1.4F,2.3F, MobCategory.MONSTER, true);
	public static final EntityType<MudSpectralGolemEntity> MUD_SPECTRAL_GOLEM = register(Keys.MUD_SPECTRAL_GOLEM, MudSpectralGolemEntity::new, 1.4F,2.3F, MobCategory.MONSTER);
	public static final EntityType<CrystalGolemEntity> CRYSTAL_GOLEM = register(Keys.CRYSTAL_GOLEM, CrystalGolemEntity::new, 0.9F,1.95F, MobCategory.MONSTER, true);
	public static final EntityType<LunaticPriestEntity> LUNATIC_PRIEST = register(Keys.LUNATIC_PRIEST, LunaticPriestEntity::new, 0.8F,2.5F, MobCategory.MONSTER);
	public static final EntityType<EvilCowEntity> EVIL_COW = register(Keys.EVIL_COW, EvilCowEntity::new, 0.99F, 1.4F, MobCategory.MONSTER, true);
	public static final EntityType<EvilCowEntity> CORTINARIUS_COW = register(Keys.CORTINARIUS_COW, EvilCowEntity::new, 0.99F, 1.4F, MobCategory.MONSTER, true);
	public static final EntityType<EntEntity> STELLAR_ENT = register(Keys.STELLAR_ENT, EntEntity::new, 0.7F,2.0F, MobCategory.MONSTER, true);
	public static final EntityType<VenomousSnakeEntity> VENOMOUS_SNAKE = register(Keys.VENOMOUS_SNAKE, VenomousSnakeEntity::new, 0.8F,0.8F, MobCategory.MONSTER, true);
	public static final EntityType<WormEntity> WORM = register(Keys.WORM, WormEntity::new, 0.8F,0.8F, MobCategory.MONSTER, true);
	public static final EntityType<StellarChickenEntity> STELLAR_CHICKEN = register(Keys.STELLAR_CHICKEN, StellarChickenEntity::new, 0.4F, 0.7F, MobCategory.CREATURE);
	public static final EntityType<BoarEntity> STELLAR_BOAR = register(Keys.STELLAR_BOAR, BoarEntity::new, 0.9F, 0.9F, MobCategory.CREATURE);
	public static final EntityType<ShroomBoomEntity> SHROOMBOOM = register(Keys.SHROOMBOOM, ShroomBoomEntity::new, 0.6F, 1.7F, MobCategory.MONSTER, true);
	public static final EntityType<VerdigrisZombieEntity> VERDIGRIS_ZOMBIE = register(Keys.VERDIGRIS_ZOMBIE, VerdigrisZombieEntity::new, 0.7F,2.0F, MobCategory.MONSTER, true);
	public static final EntityType<MummyEntity> MUMMY = register(Keys.MUMMY, MummyEntity::new, 0.7F,1.9F, MobCategory.MONSTER, true);
	public static final EntityType<SlimePirateEntity> SLIME_PIRATE = register(Keys.SLIME_PIRATE, SlimePirateEntity::new, 0.7F,1.9F, MobCategory.MONSTER, true);
	public static final EntityType<SlimeNinjaPirateEntity> SLIME_NINJA_PIRATE = register(Keys.SLIME_NINJA_PIRATE, SlimeNinjaPirateEntity::new, 0.7F,1.9F, MobCategory.MONSTER, true);
	public static final EntityType<GhostSlimePirateEntity> GHOST_SLIME_PIRATE = register(Keys.GHOST_SLIME_PIRATE, GhostSlimePirateEntity::new, 0.7F,1.9F, MobCategory.MONSTER, true);
	public static final EntityType<GhostSlimeNinjaPirateEntity> GHOST_SLIME_NINJA_PIRATE = register(Keys.GHOST_SLIME_NINJA_PIRATE, GhostSlimeNinjaPirateEntity::new, 0.7F,1.9F, MobCategory.MONSTER, true);
	public static final EntityType<SandySheepEntity> SANDY_SHEEP = register(Keys.SANDY_SHEEP, SandySheepEntity::new, 0.9F, 1.4F, MobCategory.CREATURE);
	public static final EntityType<GlidingTurtleEntity> GLIDING_TURTLE = register(Keys.GLIDING_TURTLE, GlidingTurtleEntity::new, 1.4F, 1.9F, MobCategory.CREATURE);
	public static final EntityType<FatPhantomEntity> FAT_PHANTOM = register(Keys.FAT_PHANTOM, FatPhantomEntity::new, 2.6F, 1.5F, MobCategory.CREATURE);
	public static final EntityType<KodamaEntity> KODAMA = register(Keys.KODAMA, KodamaEntity::new, 0.7F, 0.9F, MobCategory.CREATURE);
	public static final EntityType<CrystalSlimeEntity> CRYSTAL_SLIME = register(Keys.CRYSTAL_SLIME, CrystalSlimeEntity::new, 1.0F, 1.0F, MobCategory.MONSTER, true);
	public static final EntityType<MudSoldierEntity> MUD_SOLDIER = register(Keys.MUD_SOLDIER, MudSoldierEntity::new, 0.6F, 1.99F, MobCategory.MONSTER, true);
	public static final EntityType<MudSpectralSoldierEntity> MUD_SPECTRAL_SOLDIER = register(Keys.MUD_SPECTRAL_SOLDIER, MudSpectralSoldierEntity::new, 0.6F, 1.99F, MobCategory.MONSTER);
	public static final EntityType<MudCycleMageEntity> MUD_CYCLE_MAGE = register(Keys.MUD_CYCLE_MAGE, MudCycleMageEntity::new, 0.6F, 1.99F, MobCategory.MONSTER);
	public static final EntityType<MudSpectralCycleMageEntity> MUD_SPECTRAL_CYCLE_MAGE = register(Keys.MUD_SPECTRAL_CYCLE_MAGE, MudSpectralCycleMageEntity::new, 0.6F, 1.99F, MobCategory.MONSTER);
	public static final EntityType<TornSpiritEntity> TORN_SPIRIT = register(Keys.TORN_SPIRIT, TornSpiritEntity::new, 0.8F,1.95F, MobCategory.MONSTER, true);
	public static final EntityType<IceSpiritEntity> ICE_SPIRIT = register(Keys.ICE_SPIRIT, IceSpiritEntity::new, 0.7F, 1.0F, MobCategory.MONSTER, true);
	public static final EntityType<FireSpiritEntity> FIRE_SPIRIT = register(Keys.FIRE_SPIRIT, FireSpiritEntity::new, 0.7F, 1.0F, MobCategory.MONSTER, true);
	public static final EntityType<ElectroSpiritEntity> ELECTRO_SPIRIT = register(Keys.ELECTRO_SPIRIT, ElectroSpiritEntity::new, 0.7F, 1.0F, MobCategory.MONSTER, true);
	public static final EntityType<ChainedGodEntity> CHAINED_GOD = register(Keys.CHAINED_GOD, ChainedGodEntity::new, 2.8F,5F, MobCategory.MONSTER);
	public static final EntityType<PoisonballEntity> POISONBALL = register(Keys.POISONBALL, PoisonballEntity::new, 1.0F, 1.0F, MobCategory.MISC);
	public static final EntityType<DimensionShattererProjectileEntity> DIMENSION_SHATTERER_PROJECTILE = register(Keys.DIMENSION_SHATTERER_PROJECTILE, DimensionShattererProjectileEntity::new, 1.0F, 1.0F, MobCategory.MISC);
	public static final EntityType<FlyingJellyfishEntity> FLYING_JELLYFISH = register(Keys.FLYING_JELLYFISH, FlyingJellyfishEntity::new, 3.0F, 3.0F, MobCategory.MONSTER, true);
	public static final EntityType<ThrownStellarEgg> THROWN_STELLAR_EGG = register(Keys.THROWN_STELLAR_EGG, ThrownStellarEgg::new, 0.25F, 0.25F, MobCategory.MISC);
	public static final EntityType<ShurikenEntity> IRON_SHURIKEN = register(Keys.IRON_SHURIKEN, (entityType, level) -> new ShurikenEntity(entityType, level, 8.0F), 0.25F, 0.25F, MobCategory.MISC);
	public static final EntityType<ShurikenEntity> GOLD_SHURIKEN = register(Keys.GOLD_SHURIKEN, (entityType, level) -> new ShurikenEntity(entityType, level, 9.0F), 0.25F, 0.25F, MobCategory.MISC);
	public static final EntityType<ShurikenEntity> DIAMOND_SHURIKEN = register(Keys.DIAMOND_SHURIKEN, (entityType, level) -> new ShurikenEntity(entityType, level, 11.0F), 0.25F, 0.25F, MobCategory.MISC);
	public static final EntityType<ShurikenEntity> NETHERITE_SHURIKEN = register(Keys.NETHERITE_SHURIKEN, (entityType, level) -> new ShurikenEntity(entityType, level, 12.0F), 0.25F, 0.25F, MobCategory.MISC);
	public static final EntityType<ShurikenEntity> RUBY_SHURIKEN = register(Keys.RUBY_SHURIKEN, (entityType, level) -> new ShurikenEntity(entityType, level, 9.0F), 0.25F, 0.25F, MobCategory.MISC);
	public static final EntityType<ShurikenEntity> AZURITE_SHURIKEN = register(Keys.AZURITE_SHURIKEN, (entityType, level) -> new ShurikenEntity(entityType, level, 9.0F), 0.25F, 0.25F, MobCategory.MISC);
	public static final EntityType<SlowingShurikenEntity> MAGMATIC_GEL_SHURIKEN = register(Keys.MAGMATIC_GEL_SHURIKEN, (entityType, level) -> new SlowingShurikenEntity(entityType, level, 9.0F), 0.25F, 0.25F, MobCategory.MISC);
	public static final EntityType<NoGravityShurikenEntity> VOLUCITE_SHURIKEN = register(Keys.VOLUCITE_SHURIKEN, (entityType, level) -> new NoGravityShurikenEntity(entityType, level, 13.0F), 0.25F, 0.25F, MobCategory.MISC);
	public static final EntityType<ShurikenEntity> OBSIDIAN_SHURIKEN = register(Keys.OBSIDIAN_SHURIKEN, (entityType, level) -> new ShurikenEntity(entityType, level, 11.0F), 0.25F, 0.25F, MobCategory.MISC);
	public static final EntityType<ShurikenEntity> LUNATIC_CRYSTAL_SHURIKEN = register(Keys.LUNATIC_CRYSTAL_SHURIKEN, (entityType, level) -> new ShurikenEntity(entityType, level, 12.0F), 0.25F, 0.25F, MobCategory.MISC);
	public static final EntityType<FlamingShurikenEntity> ARSONIST_SHURIKEN = register(Keys.ARSONIST_SHURIKEN, (entityType, level) -> new FlamingShurikenEntity(entityType, level, 14.0F), 0.25F, 0.25F, MobCategory.MISC);
	public static final EntityType<LightningShurikenEntity> LIGHTNING_SHURIKEN = register(Keys.LIGHTNING_SHURIKEN, (entityType, level) -> new LightningShurikenEntity(entityType, level, 2.0F), 0.25F, 0.25F, MobCategory.MISC);
	public static final EntityType<VoluciteArrowEntity> VOLUCITE_BLOWPIPE_ARROW = register(Keys.VOLUCITE_BLOWPIPE_ARROW, VoluciteArrowEntity::new, 0.5F, 0.5F, MobCategory.MISC);
	public static final EntityType<RubyArrowEntity> RUBY_BLOWPIPE_ARROW = register(Keys.RUBY_BLOWPIPE_ARROW, RubyArrowEntity::new, 0.5F, 0.5F, MobCategory.MISC);
	public static final EntityType<LunaticProjectileEntity> LUNATIC_PROJECTILE = register(Keys.LUNATIC_PROJECTILE, LunaticProjectileEntity::new, 1.1F, 1.1F, MobCategory.MISC);
	public static final EntityType<ShadowProjectileEntity> SHADOW_PROJECTILE = register(Keys.SHADOW_PROJECTILE, ShadowProjectileEntity::new, 1.1F, 1.1F, MobCategory.MISC);
	public static final EntityType<ShadowFlyingSkullEntity> SHADOW_FLYING_SKULL = register(Keys.SHADOW_FLYING_SKULL, ShadowFlyingSkullEntity::new, 0.8F, 0.8F, MobCategory.MONSTER);
	public static final EntityType<ShadowTrollEntity> SHADOW_TROLL = register(Keys.SHADOW_TROLL, ShadowTrollEntity::new, 1.2F, 2.5F, MobCategory.MONSTER);
	public static final EntityType<ShadowAutomatonEntity> SHADOW_AUTOMATON = register(Keys.SHADOW_AUTOMATON, ShadowAutomatonEntity::new, 0.8F,1.95F, MobCategory.MONSTER, true);
	public static final EntityType<LilithEntity> LILITH = register(Keys.LILITH, LilithEntity::new, 0.8F,1.8F, MobCategory.MONSTER);
	public static final EntityType<AerialTreeChestMimicEntity> AERIAL_TREE_MIMIC = register(Keys.AERIAL_TREE_MIMIC, AerialTreeChestMimicEntity::new, 0.99F, 2.0F, MobCategory.MONSTER, true);
	public static final EntityType<GoldenBeechChestMimicEntity> GOLDEN_BEECH_MIMIC = register(Keys.GOLDEN_BEECH_MIMIC, GoldenBeechChestMimicEntity::new, 0.99F, 2.0F, MobCategory.MONSTER, true);
	public static final EntityType<SkyCactusFiberChestMimicEntity> SKY_CACTUS_FIBER_MIMIC = register(Keys.SKY_CACTUS_FIBER_MIMIC, SkyCactusFiberChestMimicEntity::new, 0.99F, 2.0F, MobCategory.MONSTER, true);
	public static final EntityType<CopperPineChestMimicEntity> COPPER_PINE_MIMIC = register(Keys.COPPER_PINE_MIMIC, CopperPineChestMimicEntity::new, 0.99F, 2.0F, MobCategory.MONSTER, true);
	public static final EntityType<ShadowPineBarrelMimicEntity> SHADOW_PINE_MIMIC = register(Keys.SHADOW_PINE_MIMIC, ShadowPineBarrelMimicEntity::new, 0.99F, 0.9F, MobCategory.MONSTER, true);
	public static final EntityType<HellSpiderEntity> HELL_SPIDER = register(Keys.HELL_SPIDER, HellSpiderEntity::new, 0.85F, 0.8F, MobCategory.MONSTER, true);
	public static final EntityType<CrystalSpiderEntity> CRYSTAL_SPIDER = register(Keys.CRYSTAL_SPIDER, CrystalSpiderEntity::new, 1.1F, 0.9F, MobCategory.MONSTER, true);
	public static final EntityType<ShadowSpiderEntity> SHADOW_SPIDER = register(Keys.SHADOW_SPIDER, ShadowSpiderEntity::new, 0.85F, 0.8F, MobCategory.MONSTER, true);
	public static final EntityType<ForestCaterpillarEntity> FOREST_CATERPILLAR = register(Keys.FOREST_CATERPILLAR, ForestCaterpillarEntity::new, 0.4F, 0.4F, MobCategory.MONSTER, true);
	public static final EntityType<CrystalCaterpillarEntity> CRYSTAL_CATERPILLAR = register(Keys.CRYSTAL_CATERPILLAR, CrystalCaterpillarEntity::new, 0.4F, 0.4F, MobCategory.MONSTER, true);
	public static final EntityType<AerialHellPaintingEntity> AERIAL_HELL_PAINTING = registerPainting(Keys.AERIAL_HELL_PAINTING, AerialHellPaintingEntity::new, 0.5F, 0.5F, MobCategory.MISC);

	public static <E extends Entity> EntityType<E> registerPainting(ResourceKey<EntityType<?>> key, EntityType.EntityFactory<E> factory, float width, float height, MobCategory group)
	{
		return register(key.identifier().getPath(), EntityType.Builder.of(factory, group).clientTrackingRange(10).updateInterval(Integer.MAX_VALUE).sized(width, height).build(key));
	}

	public static <E extends Entity> EntityType<E> register(ResourceKey<EntityType<?>> key, EntityType.EntityFactory<E> factory, float width, float height, MobCategory group)
	{
		return register(key, factory, width, height, getDefaultEyeHeight(height), group);
	}

	public static <E extends Entity> EntityType<E> register(ResourceKey<EntityType<?>> key, EntityType.EntityFactory<E> factory, float width, float height, MobCategory group, boolean notInPeaceful)
	{
		return register(key, factory, width, height, getDefaultEyeHeight(height), group, notInPeaceful);
	}

	public static <E extends Entity> EntityType<E> register(ResourceKey<EntityType<?>> key, EntityType.EntityFactory<E> factory, float width, float height, float eyeHeight, MobCategory group, boolean notInPeaceful)
	{
		if (notInPeaceful) {return register(key.identifier().getPath(), EntityType.Builder.of(factory, group).notInPeaceful().sized(width, height).eyeHeight(eyeHeight).build(key));}
		else {return register(key, factory, width, height, eyeHeight, group);}
	}

	public static <E extends Entity> EntityType<E> register(ResourceKey<EntityType<?>> key, EntityType.EntityFactory<E> factory, float width, float height, float eyeHeight, MobCategory group)
	{
		return register(key.identifier().getPath(), EntityType.Builder.of(factory, group).sized(width, height).eyeHeight(eyeHeight).build(key));
	}

	public static <T extends EntityType<? extends Entity>> T register(String id, T entityType) {return Registry.register(BuiltInRegistries.ENTITY_TYPE, AerialHell.id(id), entityType);}

	//copy of EntityDimensions method of same name
	private static float getDefaultEyeHeight(float height) {return height * 0.85F;}

	public static class Keys
	{
		public static ResourceKey<EntityType<?>> STELLAR_STONE_AUTOMATON = key("stellar_stone_automaton");
		public static ResourceKey<EntityType<?>> VOLUCITE_GOLEM = key("volucite_golem");
		public static ResourceKey<EntityType<?>> VOLUCITE_GOLEM_HEAD = key("volucite_golem_head");
		public static ResourceKey<EntityType<?>> MUD_GOLEM = key("mud_golem");
		public static ResourceKey<EntityType<?>> MUD_SPECTRAL_GOLEM = key("mud_spectral_golem");
		public static ResourceKey<EntityType<?>> CRYSTAL_GOLEM = key("crystal_golem");
		public static ResourceKey<EntityType<?>> LUNATIC_PRIEST = key("lunatic_priest");
		public static ResourceKey<EntityType<?>> EVIL_COW = key("evil_cow");
		public static ResourceKey<EntityType<?>> CORTINARIUS_COW = key("cortinarius_cow");
		public static ResourceKey<EntityType<?>> STELLAR_ENT = key("stellar_ent");
		public static ResourceKey<EntityType<?>> VENOMOUS_SNAKE = key("venomous_snake");
		public static ResourceKey<EntityType<?>> WORM = key("worm");
		public static ResourceKey<EntityType<?>> STELLAR_CHICKEN = key("stellar_chicken");
		public static ResourceKey<EntityType<?>> STELLAR_BOAR = key("stellar_boar");
		public static ResourceKey<EntityType<?>> SHROOMBOOM = key("shroomboom");
		public static ResourceKey<EntityType<?>> VERDIGRIS_ZOMBIE = key("verdigris_zombie");
		public static ResourceKey<EntityType<?>> MUMMY = key("mummy");
		public static ResourceKey<EntityType<?>> SLIME_PIRATE = key("slime_pirate");
		public static ResourceKey<EntityType<?>> SLIME_NINJA_PIRATE = key("slime_ninja_pirate");
		public static ResourceKey<EntityType<?>> GHOST_SLIME_PIRATE = key("ghost_slime_pirate");
		public static ResourceKey<EntityType<?>> GHOST_SLIME_NINJA_PIRATE = key("ghost_slime_ninja_pirate");
		public static ResourceKey<EntityType<?>> SANDY_SHEEP = key("sandy_sheep");
		public static ResourceKey<EntityType<?>> GLIDING_TURTLE = key("gliding_turtle");
		public static ResourceKey<EntityType<?>> FAT_PHANTOM = key("fat_phantom");
		public static ResourceKey<EntityType<?>> KODAMA = key("kodama");
		public static ResourceKey<EntityType<?>> CRYSTAL_SLIME = key("crystal_slime");
		public static ResourceKey<EntityType<?>> MUD_SOLDIER = key("mud_soldier");
		public static ResourceKey<EntityType<?>> MUD_SPECTRAL_SOLDIER = key("mud_spectral_soldier");
		public static ResourceKey<EntityType<?>> MUD_CYCLE_MAGE = key("mud_cycle_mage");
		public static ResourceKey<EntityType<?>> MUD_SPECTRAL_CYCLE_MAGE = key("mud_spectral_cycle_mage");
		public static ResourceKey<EntityType<?>> TORN_SPIRIT = key("torn_spirit");
		public static ResourceKey<EntityType<?>> ICE_SPIRIT = key("ice_spirit");
		public static ResourceKey<EntityType<?>> FIRE_SPIRIT = key("fire_spirit");
		public static ResourceKey<EntityType<?>> ELECTRO_SPIRIT = key("electro_spirit");
		public static ResourceKey<EntityType<?>> CHAINED_GOD = key("chained_god");
		public static ResourceKey<EntityType<?>> POISONBALL = key("poisonball");
		public static ResourceKey<EntityType<?>> DIMENSION_SHATTERER_PROJECTILE = key("dimension_shatterer_projectile");
		public static ResourceKey<EntityType<?>> FLYING_JELLYFISH = key("flying_jellyfish");
		public static ResourceKey<EntityType<?>> THROWN_STELLAR_EGG = key("thrown_stellar_egg");
		public static ResourceKey<EntityType<?>> IRON_SHURIKEN = key("iron_shuriken");
		public static ResourceKey<EntityType<?>> GOLD_SHURIKEN = key("gold_shuriken");
		public static ResourceKey<EntityType<?>> DIAMOND_SHURIKEN = key("diamond_shuriken");
		public static ResourceKey<EntityType<?>> NETHERITE_SHURIKEN = key("netherite_shuriken");
		public static ResourceKey<EntityType<?>> RUBY_SHURIKEN = key("ruby_shuriken");
		public static ResourceKey<EntityType<?>> AZURITE_SHURIKEN = key("azurite_shuriken");
		public static ResourceKey<EntityType<?>> MAGMATIC_GEL_SHURIKEN = key("magmatic_gel_shuriken");
		public static ResourceKey<EntityType<?>> VOLUCITE_SHURIKEN = key("volucite_shuriken");
		public static ResourceKey<EntityType<?>> OBSIDIAN_SHURIKEN = key("obsidian_shuriken");
		public static ResourceKey<EntityType<?>> LUNATIC_CRYSTAL_SHURIKEN = key("lunatic_crystal_shuriken");
		public static ResourceKey<EntityType<?>> ARSONIST_SHURIKEN = key("arsonist_shuriken");
		public static ResourceKey<EntityType<?>> LIGHTNING_SHURIKEN = key("lightning_shuriken");
		public static ResourceKey<EntityType<?>> VOLUCITE_BLOWPIPE_ARROW = key("volucite_blowpipe_arrow");
		public static ResourceKey<EntityType<?>> RUBY_BLOWPIPE_ARROW = key("ruby_blowpipe_arrow");
		public static ResourceKey<EntityType<?>> LUNATIC_PROJECTILE = key("lunatic_projectile");
		public static ResourceKey<EntityType<?>> SHADOW_PROJECTILE = key("shadow_projectile");
		public static ResourceKey<EntityType<?>> SHADOW_FLYING_SKULL = key("shadow_flying_skull");
		public static ResourceKey<EntityType<?>> SHADOW_TROLL = key("shadow_troll");
		public static ResourceKey<EntityType<?>> SHADOW_AUTOMATON = key("shadow_automaton");
		public static ResourceKey<EntityType<?>> LILITH = key("lilith");
		public static ResourceKey<EntityType<?>> AERIAL_TREE_MIMIC = key("aerial_tree_mimic");
		public static ResourceKey<EntityType<?>> GOLDEN_BEECH_MIMIC = key("golden_beech_mimic");
		public static ResourceKey<EntityType<?>> SKY_CACTUS_FIBER_MIMIC = key("sky_cactus_fiber_mimic");
		public static ResourceKey<EntityType<?>> COPPER_PINE_MIMIC = key("copper_pine_mimic");
		public static ResourceKey<EntityType<?>> SHADOW_PINE_MIMIC = key("shadow_pine_mimic");
		public static ResourceKey<EntityType<?>> HELL_SPIDER = key("hell_spider");
		public static ResourceKey<EntityType<?>> CRYSTAL_SPIDER = key("crystal_spider");
		public static ResourceKey<EntityType<?>> SHADOW_SPIDER = key("shadow_spider");
		public static ResourceKey<EntityType<?>> FOREST_CATERPILLAR = key("forest_caterpillar");
		public static ResourceKey<EntityType<?>> CRYSTAL_CATERPILLAR = key("crystal_caterpillar");
		public static ResourceKey<EntityType<?>> AERIAL_HELL_PAINTING = key("aerial_hell_painting");

		private static ResourceKey<EntityType<?>> key(String name)
		{
			return ResourceKey.create(Registries.ENTITY_TYPE, AerialHell.id(name));
		}
	}
	
	public static void load()
	{
		FabricDefaultAttributeRegistry.register(STELLAR_STONE_AUTOMATON, StellarStoneAutomatonEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(VOLUCITE_GOLEM, VoluciteGolemEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(VOLUCITE_GOLEM_HEAD, VoluciteGolemHeadEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(MUD_GOLEM, MudGolemEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(MUD_SPECTRAL_GOLEM, MudSpectralGolemEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(CRYSTAL_GOLEM, CrystalGolemEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(LUNATIC_PRIEST, LunaticPriestEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(EVIL_COW, EvilCowEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(CORTINARIUS_COW, EvilCowEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(STELLAR_ENT, EntEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(VENOMOUS_SNAKE, VenomousSnakeEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(WORM, WormEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(STELLAR_CHICKEN, StellarChickenEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(STELLAR_BOAR, BoarEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(SHROOMBOOM, ShroomBoomEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(VERDIGRIS_ZOMBIE, VerdigrisZombieEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(MUMMY, MummyEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(SLIME_PIRATE, SlimePirateEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(SLIME_NINJA_PIRATE, SlimeNinjaPirateEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(GHOST_SLIME_PIRATE, GhostSlimePirateEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(GHOST_SLIME_NINJA_PIRATE, GhostSlimeNinjaPirateEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(SANDY_SHEEP, SandySheepEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(GLIDING_TURTLE, GlidingTurtleEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(FAT_PHANTOM, FatPhantomEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(KODAMA, KodamaEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(CRYSTAL_SLIME, CrystalSlimeEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(MUD_SOLDIER, MudSoldierEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(MUD_SPECTRAL_SOLDIER, MudSpectralSoldierEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(MUD_CYCLE_MAGE, MudCycleMageEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(MUD_SPECTRAL_CYCLE_MAGE, MudSpectralCycleMageEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(TORN_SPIRIT, TornSpiritEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(ICE_SPIRIT, AbstractElementSpiritEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(FIRE_SPIRIT, AbstractElementSpiritEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(ELECTRO_SPIRIT, AbstractElementSpiritEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(CHAINED_GOD, ChainedGodEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(FLYING_JELLYFISH, FlyingJellyfishEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(SHADOW_FLYING_SKULL, ShadowFlyingSkullEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(SHADOW_TROLL, ShadowTrollEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(SHADOW_AUTOMATON, ShadowAutomatonEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(AERIAL_TREE_MIMIC, AerialTreeChestMimicEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(GOLDEN_BEECH_MIMIC, GoldenBeechChestMimicEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(SKY_CACTUS_FIBER_MIMIC, SkyCactusFiberChestMimicEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(COPPER_PINE_MIMIC, CopperPineChestMimicEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(SHADOW_PINE_MIMIC, ShadowPineBarrelMimicEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(HELL_SPIDER, HellSpiderEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(CRYSTAL_SPIDER, CrystalSpiderEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(LILITH, LilithEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(SHADOW_SPIDER, ShadowSpiderEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(FOREST_CATERPILLAR, ForestCaterpillarEntity.registerAttributes());
		FabricDefaultAttributeRegistry.register(CRYSTAL_CATERPILLAR, CrystalCaterpillarEntity.registerAttributes());
	}

	public static void registerEntitySpawnPlacements()
	{
		SpawnPlacements.register(SANDY_SHEEP, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellAnimalEntity::canAerialHellAnimalSpawn);
		SpawnPlacements.register(GLIDING_TURTLE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellAnimalEntity::canAerialHellAnimalSpawn);
		SpawnPlacements.register(STELLAR_CHICKEN, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, StellarChickenEntity::canSpawn);
		SpawnPlacements.register(STELLAR_BOAR, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BoarEntity::canSpawn);

		SpawnPlacements.register(EVIL_COW, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(CORTINARIUS_COW, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(STELLAR_ENT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(SHROOMBOOM, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(VERDIGRIS_ZOMBIE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		//SpawnRestriction.register(MUD_SOLDIER, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn); //pb : makes hostile entities that can spawn in daylight spawn without any requirement (1 entity / tick / chunk, game crash)
		SpawnPlacements.register(HELL_SPIDER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(CRYSTAL_SPIDER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(SHADOW_SPIDER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(VENOMOUS_SNAKE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(WORM, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(MUMMY, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(SLIME_PIRATE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(SLIME_NINJA_PIRATE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(GHOST_SLIME_PIRATE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GhostSlimePirateEntity::canGhostSpawn);
		SpawnPlacements.register(GHOST_SLIME_NINJA_PIRATE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GhostSlimePirateEntity::canGhostSpawn);
		SpawnPlacements.register(CRYSTAL_SLIME, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrystalSlimeEntity::canSpawn);
		SpawnPlacements.register(FOREST_CATERPILLAR, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractCaterpillarEntity::canCaterpillarSpawn);
		SpawnPlacements.register(CRYSTAL_CATERPILLAR, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractCaterpillarEntity::canCaterpillarSpawn);
		SpawnPlacements.register(SHADOW_TROLL, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(SHADOW_AUTOMATON, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(MUD_SOLDIER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(FAT_PHANTOM, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FatPhantomEntity::canSpawn);
		SpawnPlacements.register(KODAMA, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, KodamaEntity::canSpawn);
		SpawnPlacements.register(FLYING_JELLYFISH, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FlyingJellyfishEntity::canJellyfishSpawn);
		SpawnPlacements.register(ICE_SPIRIT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(FIRE_SPIRIT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(ELECTRO_SPIRIT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(TORN_SPIRIT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
	}
}
