package fr.factionbedrock.aerialhell.Registry.Entities;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

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
import fr.factionbedrock.aerialhell.Entity.Neutral.*;
import fr.factionbedrock.aerialhell.Entity.Passive.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AerialHellEntities
{
	public static final EntityType<StellarStoneAutomatonEntity> STELLAR_STONE_AUTOMATON = register("stellar_stone_automaton", StellarStoneAutomatonEntity::new, 0.9F,2.1F, SpawnGroup.MONSTER);
	public static final EntityType<MudGolemEntity> MUD_GOLEM = register("mud_golem", MudGolemEntity::new, 1.4F,2.3F, SpawnGroup.MONSTER);
	public static final EntityType<MudSpectralGolemEntity> MUD_SPECTRAL_GOLEM = register("mud_spectral_golem", MudSpectralGolemEntity::new, 1.4F,2.3F, SpawnGroup.MONSTER);
	public static final EntityType<CrystalGolemEntity> CRYSTAL_GOLEM = register("crystal_golem", CrystalGolemEntity::new, 0.9F,1.95F, SpawnGroup.MONSTER);
	public static final EntityType<LunaticPriestEntity> LUNATIC_PRIEST = register("lunatic_priest", LunaticPriestEntity::new, 0.8F,2.5F, SpawnGroup.MONSTER);
	public static final EntityType<EvilCowEntity> EVIL_COW = register("evil_cow", EvilCowEntity::new, 0.99F, 1.4F, SpawnGroup.MONSTER);
	public static final EntityType<EvilCowEntity> CORTINARIUS_COW = register("cortinarius_cow", EvilCowEntity::new, 0.99F, 1.4F, SpawnGroup.MONSTER);
	public static final EntityType<EntEntity> STELLAR_ENT = register("stellar_ent", EntEntity::new, 0.7F,2.0F, SpawnGroup.MONSTER);
	public static final EntityType<VenomousSnakeEntity> VENOMOUS_SNAKE = register("venomous_snake", VenomousSnakeEntity::new, 0.8F,0.8F, SpawnGroup.MONSTER);
	public static final EntityType<WormEntity> WORM = register("worm", WormEntity::new, 0.8F,0.8F, SpawnGroup.MONSTER);
	public static final EntityType<StellarChickenEntity> STELLAR_CHICKEN = register("stellar_chicken", StellarChickenEntity::new, 0.4F, 0.7F, SpawnGroup.CREATURE);
	public static final EntityType<BoarEntity> STELLAR_BOAR = register("stellar_boar", BoarEntity::new, 0.9F, 0.9F, SpawnGroup.CREATURE);
	public static final EntityType<ShroomBoomEntity> SHROOMBOOM = register("shroomboom", ShroomBoomEntity::new, 0.6F, 1.7F, SpawnGroup.MONSTER);
	public static final EntityType<VerdigrisZombieEntity> VERDIGRIS_ZOMBIE = register("verdigris_zombie", VerdigrisZombieEntity::new, 0.7F,2.0F, SpawnGroup.MONSTER);
	public static final EntityType<MummyEntity> MUMMY = register("mummy", MummyEntity::new, 0.7F,1.9F, SpawnGroup.MONSTER);
	public static final EntityType<SlimePirateEntity> SLIME_PIRATE = register("slime_pirate", SlimePirateEntity::new, 0.7F,1.9F, SpawnGroup.MONSTER);
	public static final EntityType<SlimeNinjaPirateEntity> SLIME_NINJA_PIRATE = register("slime_ninja_pirate", SlimeNinjaPirateEntity::new, 0.7F,1.9F, SpawnGroup.MONSTER);
	public static final EntityType<GhostSlimePirateEntity> GHOST_SLIME_PIRATE = register("ghost_slime_pirate", GhostSlimePirateEntity::new, 0.7F,1.9F, SpawnGroup.MONSTER);
	public static final EntityType<GhostSlimeNinjaPirateEntity> GHOST_SLIME_NINJA_PIRATE = register("ghost_slime_ninja_pirate", GhostSlimeNinjaPirateEntity::new, 0.7F,1.9F, SpawnGroup.MONSTER);
	public static final EntityType<SandySheepEntity> SANDY_SHEEP = register("sandy_sheep", SandySheepEntity::new, 0.9F, 1.4F, SpawnGroup.CREATURE);
	public static final EntityType<GlidingTurtleEntity> GLIDING_TURTLE = register("gliding_turtle", GlidingTurtleEntity::new, 1.4F, 1.9F, SpawnGroup.CREATURE);
	public static final EntityType<FatPhantomEntity> FAT_PHANTOM = register("fat_phantom", FatPhantomEntity::new, 2.6F, 1.5F, SpawnGroup.CREATURE);
	public static final EntityType<KodamaEntity> KODAMA = register("kodama", KodamaEntity::new, 0.7F, 0.9F, SpawnGroup.CREATURE);
	public static final EntityType<CrystalSlimeEntity> CRYSTAL_SLIME = register("crystal_slime", CrystalSlimeEntity::new, 1.0F, 1.0F, SpawnGroup.MONSTER);
	public static final EntityType<MudSoldierEntity> MUD_SOLDIER = register("mud_soldier", MudSoldierEntity::new, 0.6F, 1.99F, SpawnGroup.MONSTER);
	public static final EntityType<MudSpectralSoldierEntity> MUD_SPECTRAL_SOLDIER = register("mud_spectral_soldier", MudSpectralSoldierEntity::new, 0.6F, 1.99F, SpawnGroup.MONSTER);
	public static final EntityType<MudCycleMageEntity> MUD_CYCLE_MAGE = register("mud_cycle_mage", MudCycleMageEntity::new, 0.6F, 1.99F, SpawnGroup.MONSTER);
	public static final EntityType<MudSpectralCycleMageEntity> MUD_SPECTRAL_CYCLE_MAGE = register("mud_spectral_cycle_mage", MudSpectralCycleMageEntity::new, 0.6F, 1.99F, SpawnGroup.MONSTER);
	public static final EntityType<TornSpiritEntity> TORN_SPIRIT = register("torn_spirit", TornSpiritEntity::new, 0.8F,1.95F, SpawnGroup.MONSTER);
	public static final EntityType<IceSpiritEntity> ICE_SPIRIT = register("ice_spirit", IceSpiritEntity::new, 0.7F, 1.0F, SpawnGroup.MONSTER);
	public static final EntityType<FireSpiritEntity> FIRE_SPIRIT = register("fire_spirit", FireSpiritEntity::new, 0.7F, 1.0F, SpawnGroup.MONSTER);
	public static final EntityType<ElectroSpiritEntity> ELECTRO_SPIRIT = register("electro_spirit", ElectroSpiritEntity::new, 0.7F, 1.0F, SpawnGroup.MONSTER);
	public static final EntityType<ChainedGodEntity> CHAINED_GOD = register("chained_god", ChainedGodEntity::new, 2.8F,5F, SpawnGroup.MONSTER);
	public static final EntityType<PoisonballEntity> POISONBALL = register("poisonball", PoisonballEntity::new, 1.0F, 1.0F, SpawnGroup.MISC);
	public static final EntityType<DimensionShattererProjectileEntity> DIMENSION_SHATTERER_PROJECTILE = register("dimension_shatterer_projectile", DimensionShattererProjectileEntity::new, 1.0F, 1.0F, SpawnGroup.MISC);
	public static final EntityType<FlyingJellyfishEntity> FLYING_JELLYFISH = register("flying_jellyfish", FlyingJellyfishEntity::new, 3.0F, 3.0F, SpawnGroup.MONSTER);
	public static final EntityType<ThrownStellarEgg> THROWN_STELLAR_EGG = register("thrown_stellar_egg", ThrownStellarEgg::new, 0.25F, 0.25F, SpawnGroup.MISC);
	public static final EntityType<IronShurikenEntity> IRON_SHURIKEN = register("iron_shuriken", IronShurikenEntity::new, 0.25F, 0.25F, SpawnGroup.MISC);
	public static final EntityType<GoldShurikenEntity> GOLD_SHURIKEN = register("gold_shuriken", GoldShurikenEntity::new, 0.25F, 0.25F, SpawnGroup.MISC);
	public static final EntityType<DiamondShurikenEntity> DIAMOND_SHURIKEN = register("diamond_shuriken", DiamondShurikenEntity::new, 0.25F, 0.25F, SpawnGroup.MISC);
	public static final EntityType<NetheriteShurikenEntity> NETHERITE_SHURIKEN = register("netherite_shuriken", NetheriteShurikenEntity::new, 0.25F, 0.25F, SpawnGroup.MISC);
	public static final EntityType<RubyShurikenEntity> RUBY_SHURIKEN = register("ruby_shuriken", RubyShurikenEntity::new, 0.25F, 0.25F, SpawnGroup.MISC);
	public static final EntityType<AzuriteShurikenEntity> AZURITE_SHURIKEN = register("azurite_shuriken", AzuriteShurikenEntity::new, 0.25F, 0.25F, SpawnGroup.MISC);
	public static final EntityType<MagmaticGelShurikenEntity> MAGMATIC_GEL_SHURIKEN = register("magmatic_gel_shuriken", MagmaticGelShurikenEntity::new, 0.25F, 0.25F, SpawnGroup.MISC);
	public static final EntityType<VoluciteShurikenEntity> VOLUCITE_SHURIKEN = register("volucite_shuriken", VoluciteShurikenEntity::new, 0.25F, 0.25F, SpawnGroup.MISC);
	public static final EntityType<ObsidianShurikenEntity> OBSIDIAN_SHURIKEN = register("obsidian_shuriken", ObsidianShurikenEntity::new, 0.25F, 0.25F, SpawnGroup.MISC);
	public static final EntityType<LunaticCrystalShurikenEntity> LUNATIC_CRYSTAL_SHURIKEN = register("lunatic_crystal_shuriken", LunaticCrystalShurikenEntity::new, 0.25F, 0.25F, SpawnGroup.MISC);
	public static final EntityType<ArsonistShurikenEntity> ARSONIST_SHURIKEN = register("arsonist_shuriken", ArsonistShurikenEntity::new, 0.25F, 0.25F, SpawnGroup.MISC);
	public static final EntityType<LightningShurikenEntity> LIGHTNING_SHURIKEN = register("lightning_shuriken", LightningShurikenEntity::new, 0.25F, 0.25F, SpawnGroup.MISC);
	public static final EntityType<VoluciteArrowEntity> VOLUCITE_BLOWPIPE_ARROW = register("volucite_blowpipe_arrow", VoluciteArrowEntity::new, 0.5F, 0.5F, SpawnGroup.MISC);
	public static final EntityType<RubyArrowEntity> RUBY_BLOWPIPE_ARROW = register("ruby_blowpipe_arrow", RubyArrowEntity::new, 0.5F, 0.5F, SpawnGroup.MISC);
	public static final EntityType<LunaticProjectileEntity> LUNATIC_PROJECTILE = register("lunatic_projectile", LunaticProjectileEntity::new, 1.1F, 1.1F, SpawnGroup.MISC);
	public static final EntityType<ShadowProjectileEntity> SHADOW_PROJECTILE = register("shadow_projectile", ShadowProjectileEntity::new, 1.1F, 1.1F, SpawnGroup.MISC);
	public static final EntityType<ShadowFlyingSkullEntity> SHADOW_FLYING_SKULL = register("shadow_flying_skull", ShadowFlyingSkullEntity::new, 0.8F, 0.8F, SpawnGroup.MONSTER);
	public static final EntityType<ShadowTrollEntity> SHADOW_TROLL = register("shadow_troll", ShadowTrollEntity::new, 1.2F, 2.5F, SpawnGroup.MONSTER);
	public static final EntityType<ShadowAutomatonEntity> SHADOW_AUTOMATON = register("shadow_automaton", ShadowAutomatonEntity::new, 0.8F,1.95F, SpawnGroup.MONSTER);
	public static final EntityType<LilithEntity> LILITH = register("lilith", LilithEntity::new, 0.8F,1.8F, SpawnGroup.MONSTER);
	public static final EntityType<AerialTreeChestMimicEntity> AERIAL_TREE_MIMIC = register("aerial_tree_mimic", AerialTreeChestMimicEntity::new, 0.99F, 2.0F, SpawnGroup.MONSTER);
	public static final EntityType<GoldenBeechChestMimicEntity> GOLDEN_BEECH_MIMIC = register("golden_beech_mimic", GoldenBeechChestMimicEntity::new, 0.99F, 2.0F, SpawnGroup.MONSTER);
	public static final EntityType<SkyCactusFiberChestMimicEntity> SKY_CACTUS_FIBER_MIMIC = register("sky_cactus_fiber_mimic", SkyCactusFiberChestMimicEntity::new, 0.99F, 2.0F, SpawnGroup.MONSTER);
	public static final EntityType<CopperPineChestMimicEntity> COPPER_PINE_MIMIC = register("copper_pine_mimic", CopperPineChestMimicEntity::new, 0.99F, 2.0F, SpawnGroup.MONSTER);
	public static final EntityType<ShadowPineBarrelMimicEntity> SHADOW_PINE_MIMIC = register("shadow_pine_mimic", ShadowPineBarrelMimicEntity::new, 0.99F, 0.9F, SpawnGroup.MONSTER);
	public static final EntityType<HellSpiderEntity> HELL_SPIDER = register("hell_spider", HellSpiderEntity::new, 0.85F, 0.8F, SpawnGroup.MONSTER);
	public static final EntityType<CrystalSpiderEntity> CRYSTAL_SPIDER = register("crystal_spider", CrystalSpiderEntity::new, 1.1F, 0.9F, SpawnGroup.MONSTER);
	public static final EntityType<ShadowSpiderEntity> SHADOW_SPIDER = register("shadow_spider", ShadowSpiderEntity::new, 0.85F, 0.8F, SpawnGroup.MONSTER);
	public static final EntityType<ForestCaterpillarEntity> FOREST_CATERPILLAR = register("forest_caterpillar", ForestCaterpillarEntity::new, 0.4F, 0.4F, SpawnGroup.MONSTER);
	public static final EntityType<CrystalCaterpillarEntity> CRYSTAL_CATERPILLAR = register("crystal_caterpillar", CrystalCaterpillarEntity::new, 0.4F, 0.4F, SpawnGroup.MONSTER);
	public static final EntityType<AerialHellPaintingEntity> AERIAL_HELL_PAINTING = registerPainting("aerial_hell_painting", AerialHellPaintingEntity::new, 0.5F, 0.5F, SpawnGroup.MISC);

	public static <E extends Entity> EntityType<E> registerPainting(String id, EntityType.EntityFactory<E> factory, float width, float height, SpawnGroup group)
	{
		return register(id, EntityType.Builder.create(factory, group).maxTrackingRange(10).trackingTickInterval(Integer.MAX_VALUE).dimensions(width, height).build(id));
	}

	public static <E extends Entity> EntityType<E> register(String id, EntityType.EntityFactory<E> factory, float width, float height, SpawnGroup group)
	{
		return register(id, EntityType.Builder.create(factory, group).dimensions(width, height).build(id));
	}

	public static <T extends EntityType<? extends Entity>> T register(String id, T entityType) {return Registry.register(Registries.ENTITY_TYPE, AerialHell.id(id), entityType);}

	public static void load()
	{
		FabricDefaultAttributeRegistry.register(STELLAR_STONE_AUTOMATON, StellarStoneAutomatonEntity.registerAttributes());
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

	/*public static void entitySpawnPlacements(RegisterSpawnPlacementsEvent event) TODO spawn placements
	{
		event.register(SANDY_SHEEP, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellAnimalEntity::canAerialHellAnimalSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(GLIDING_TURTLE, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellAnimalEntity::canAerialHellAnimalSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(STELLAR_CHICKEN, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, StellarChickenEntity::canSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(STELLAR_BOAR, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BoarEntity::canSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);

		event.register(EVIL_COW, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(CORTINARIUS_COW, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(STELLAR_ENT, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(SHROOMBOOM, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(VERDIGRIS_ZOMBIE, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		//event.register(MUD_SOLDIER, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE); //pb : makes hostile entities that can spawn in daylight spawn without any requirement (1 entity / tick / chunk, game crash)
		event.register(HELL_SPIDER, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(CRYSTAL_SPIDER, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(SHADOW_SPIDER, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(VENOMOUS_SNAKE, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(WORM, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(MUMMY, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(SLIME_PIRATE, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(SLIME_NINJA_PIRATE, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(GHOST_SLIME_PIRATE, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GhostSlimePirateEntity::canGhostSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(GHOST_SLIME_NINJA_PIRATE, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GhostSlimePirateEntity::canGhostSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(CRYSTAL_SLIME, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CrystalSlimeEntity::canSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(FOREST_CATERPILLAR, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractCaterpillarEntity::canCaterpillarSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(CRYSTAL_CATERPILLAR, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractCaterpillarEntity::canCaterpillarSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(SHADOW_TROLL, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(SHADOW_AUTOMATON, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(MUD_SOLDIER, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(FAT_PHANTOM, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FatPhantomEntity::canSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(KODAMA, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, KodamaEntity::canSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(FLYING_JELLYFISH, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FlyingJellyfishEntity::canJellyfishSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(ICE_SPIRIT, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(FIRE_SPIRIT, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(ELECTRO_SPIRIT, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(TORN_SPIRIT, SpawnPlacementTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}*/
}
