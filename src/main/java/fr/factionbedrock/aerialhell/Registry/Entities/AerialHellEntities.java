package fr.factionbedrock.aerialhell.Registry.Entities;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

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
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AerialHellEntities
{
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MODID);

	public static final DeferredHolder<EntityType<?>, EntityType<StellarStoneAutomatonEntity>> STELLAR_STONE_AUTOMATON = ENTITIES.register("stellar_stone_automaton", () -> EntityType.Builder.of(StellarStoneAutomatonEntity::new, MobCategory.MONSTER)
			.sized(0.9F,2.1F).build("stellar_stone_automaton"));

	public static final DeferredHolder<EntityType<?>, EntityType<MudGolemEntity>> MUD_GOLEM = ENTITIES.register("mud_golem", () -> EntityType.Builder.of(MudGolemEntity::new, MobCategory.MONSTER)
			.sized(1.4F,2.3F).build("mud_golem"));

	public static final DeferredHolder<EntityType<?>, EntityType<MudSpectralGolemEntity>> MUD_SPECTRAL_GOLEM = ENTITIES.register("mud_spectral_golem", () -> EntityType.Builder.of(MudSpectralGolemEntity::new, MobCategory.MONSTER)
			.sized(1.4F,2.3F).build("mud_spectral_golem"));

	public static final DeferredHolder<EntityType<?>, EntityType<CrystalGolemEntity>> CRYSTAL_GOLEM = ENTITIES.register("crystal_golem", () -> EntityType.Builder.of(CrystalGolemEntity::new, MobCategory.MONSTER)
			.sized(0.9F,1.95F).build("crystal_golem"));

	public static final DeferredHolder<EntityType<?>, EntityType<LunaticPriestEntity>> LUNATIC_PRIEST = ENTITIES.register("lunatic_priest", () -> EntityType.Builder.of(LunaticPriestEntity::new, MobCategory.MONSTER)
			.sized(0.8F,2.5F).build("lunatic_priest"));

	public static final DeferredHolder<EntityType<?>, EntityType<EvilCowEntity>> EVIL_COW = ENTITIES.register("evil_cow", () -> EntityType.Builder.<EvilCowEntity>of(EvilCowEntity::new, MobCategory.MONSTER)
			.sized(0.99F, 1.4F).build("evil_cow"));

	public static final DeferredHolder<EntityType<?>, EntityType<EvilCowEntity>> CORTINARIUS_COW = ENTITIES.register("cortinarius_cow", () -> EntityType.Builder.<EvilCowEntity>of(EvilCowEntity::new, MobCategory.MONSTER)
			.sized(0.99F, 1.4F).build("cortinarius_cow"));

	public static final DeferredHolder<EntityType<?>, EntityType<EntEntity>> STELLAR_ENT = ENTITIES.register("stellar_ent", () -> EntityType.Builder.of(EntEntity::new, MobCategory.MONSTER)
			.sized(0.7F,2.0F).build("stellar_ent"));

	public static final DeferredHolder<EntityType<?>, EntityType<VenomousSnakeEntity>> VENOMOUS_SNAKE = ENTITIES.register("venomous_snake", () -> EntityType.Builder.of(VenomousSnakeEntity::new, MobCategory.MONSTER)
			.sized(0.8F,0.8F).build("venomous_snake"));

	public static final DeferredHolder<EntityType<?>, EntityType<WormEntity>> WORM = ENTITIES.register("worm", () -> EntityType.Builder.of(WormEntity::new, MobCategory.MONSTER)
			.sized(0.8F,0.8F).build("worm"));

	public static final DeferredHolder<EntityType<?>, EntityType<StellarChickenEntity>> STELLAR_CHICKEN = ENTITIES.register("stellar_chicken", () -> EntityType.Builder.<StellarChickenEntity>of(StellarChickenEntity::new, MobCategory.CREATURE)
			.sized(0.4F, 0.7F).build("stellar_chicken"));

	public static final DeferredHolder<EntityType<?>, EntityType<BoarEntity>> STELLAR_BOAR = ENTITIES.register("stellar_boar", () -> EntityType.Builder.<BoarEntity>of(BoarEntity::new, MobCategory.CREATURE)
			.sized(0.9F, 0.9F).build("stellar_boar"));

	public static final DeferredHolder<EntityType<?>, EntityType<ShroomBoomEntity>> SHROOMBOOM = ENTITIES.register("shroomboom", () -> EntityType.Builder.<ShroomBoomEntity>of(ShroomBoomEntity::new, MobCategory.MONSTER)
			.sized(0.6F, 1.7F).build("shroomboom"));

	public static final DeferredHolder<EntityType<?>, EntityType<VerdigrisZombieEntity>> VERDIGRIS_ZOMBIE = ENTITIES.register("verdigris_zombie", () -> EntityType.Builder.of(VerdigrisZombieEntity::new, MobCategory.MONSTER)
			.sized(0.7F,2.0F).build("verdigris_zombie"));

	public static final DeferredHolder<EntityType<?>, EntityType<MummyEntity>> MUMMY = ENTITIES.register("mummy", () -> EntityType.Builder.of(MummyEntity::new, MobCategory.MONSTER)
			.sized(0.7F,1.9F).build("mummy"));

	public static final DeferredHolder<EntityType<?>, EntityType<SlimePirateEntity>> SLIME_PIRATE = ENTITIES.register("slime_pirate", () -> EntityType.Builder.of(SlimePirateEntity::new, MobCategory.MONSTER)
			.sized(0.7F,1.9F).build("slime_pirate"));

	public static final DeferredHolder<EntityType<?>, EntityType<SlimeNinjaPirateEntity>> SLIME_NINJA_PIRATE = ENTITIES.register("slime_ninja_pirate", () -> EntityType.Builder.of(SlimeNinjaPirateEntity::new, MobCategory.MONSTER)
			.sized(0.7F,1.9F).build("slime_ninja_pirate"));

	public static final DeferredHolder<EntityType<?>, EntityType<GhostSlimePirateEntity>> GHOST_SLIME_PIRATE = ENTITIES.register("ghost_slime_pirate", () -> EntityType.Builder.of(GhostSlimePirateEntity::new, MobCategory.MONSTER)
			.sized(0.7F,1.9F).build("ghost_slime_pirate"));

	public static final DeferredHolder<EntityType<?>, EntityType<GhostSlimeNinjaPirateEntity>> GHOST_SLIME_NINJA_PIRATE = ENTITIES.register("ghost_slime_ninja_pirate", () -> EntityType.Builder.of(GhostSlimeNinjaPirateEntity::new, MobCategory.MONSTER)
			.sized(0.7F,1.9F).build("ghost_slime_ninja_pirate"));

	public static final DeferredHolder<EntityType<?>, EntityType<SandySheepEntity>> SANDY_SHEEP = ENTITIES.register("sandy_sheep", () -> EntityType.Builder.<SandySheepEntity>of(SandySheepEntity::new, MobCategory.CREATURE)
			.sized(0.9F, 1.4F).build("sandy_sheep"));

	public static final DeferredHolder<EntityType<?>, EntityType<GlidingTurtleEntity>> GLIDING_TURTLE = ENTITIES.register("gliding_turtle", () -> EntityType.Builder.<GlidingTurtleEntity>of(GlidingTurtleEntity::new, MobCategory.CREATURE)
			.sized(1.4F, 1.9F).build("gliding_turtle"));

	public static final DeferredHolder<EntityType<?>, EntityType<FatPhantomEntity>> FAT_PHANTOM = ENTITIES.register("fat_phantom", () -> EntityType.Builder.<FatPhantomEntity>of(FatPhantomEntity::new, MobCategory.CREATURE)
			.sized(2.6F, 1.5F).fireImmune().build("fat_phantom"));

	public static final DeferredHolder<EntityType<?>, EntityType<KodamaEntity>> KODAMA = ENTITIES.register("kodama", () -> EntityType.Builder.<KodamaEntity>of(KodamaEntity::new, MobCategory.CREATURE)
			.sized(0.7F, 0.9F).build("kodama"));

	public static final DeferredHolder<EntityType<?>, EntityType<CrystalSlimeEntity>> CRYSTAL_SLIME = ENTITIES.register("crystal_slime", () -> EntityType.Builder.<CrystalSlimeEntity>of(CrystalSlimeEntity::new, MobCategory.MONSTER)
			.sized(1.0F, 1.0F).build("crystal_slime"));

	public static final DeferredHolder<EntityType<?>, EntityType<MudSoldierEntity>> MUD_SOLDIER = ENTITIES.register("mud_soldier", () -> EntityType.Builder.<MudSoldierEntity>of(MudSoldierEntity::new, MobCategory.MONSTER)
			.sized(0.6F, 1.99F).build("mud_soldier"));

	public static final DeferredHolder<EntityType<?>, EntityType<MudSpectralSoldierEntity>> MUD_SPECTRAL_SOLDIER = ENTITIES.register("mud_spectral_soldier", () -> EntityType.Builder.<MudSpectralSoldierEntity>of(MudSpectralSoldierEntity::new, MobCategory.MONSTER)
			.sized(0.6F, 1.99F).build("mud_spectral_soldier"));

	public static final DeferredHolder<EntityType<?>, EntityType<MudCycleMageEntity>> MUD_CYCLE_MAGE = ENTITIES.register("mud_cycle_mage", () -> EntityType.Builder.<MudCycleMageEntity>of(MudCycleMageEntity::new, MobCategory.MONSTER)
			.sized(0.6F, 1.99F).build("mud_cycle_mage"));

	public static final DeferredHolder<EntityType<?>, EntityType<MudSpectralCycleMageEntity>> MUD_SPECTRAL_CYCLE_MAGE = ENTITIES.register("mud_spectral_cycle_mage", () -> EntityType.Builder.<MudSpectralCycleMageEntity>of(MudSpectralCycleMageEntity::new, MobCategory.MONSTER)
			.sized(0.6F, 1.99F).build("mud_spectral_cycle_mage"));

	public static final DeferredHolder<EntityType<?>, EntityType<TornSpiritEntity>> TORN_SPIRIT = ENTITIES.register("torn_spirit", () -> EntityType.Builder.<TornSpiritEntity>of(TornSpiritEntity::new, MobCategory.MONSTER)
			.sized(0.8F,1.95F).build("torn_spirit"));

	public static final DeferredHolder<EntityType<?>, EntityType<IceSpiritEntity>> ICE_SPIRIT = ENTITIES.register("ice_spirit", () -> EntityType.Builder.<IceSpiritEntity>of(IceSpiritEntity::new, MobCategory.MONSTER)
			.sized(0.7F, 1.0F).build("ice_spirit"));

	public static final DeferredHolder<EntityType<?>, EntityType<FireSpiritEntity>> FIRE_SPIRIT = ENTITIES.register("fire_spirit", () -> EntityType.Builder.<FireSpiritEntity>of(FireSpiritEntity::new, MobCategory.MONSTER)
			.sized(0.7F, 1.0F).build("fire_spirit"));

	public static final DeferredHolder<EntityType<?>, EntityType<ElectroSpiritEntity>> ELECTRO_SPIRIT = ENTITIES.register("electro_spirit", () -> EntityType.Builder.<ElectroSpiritEntity>of(ElectroSpiritEntity::new, MobCategory.MONSTER)
			.sized(0.7F, 1.0F).build("electro_spirit"));

	public static final DeferredHolder<EntityType<?>, EntityType<ChainedGodEntity>> CHAINED_GOD = ENTITIES.register("chained_god", () -> EntityType.Builder.<ChainedGodEntity>of(ChainedGodEntity::new, MobCategory.MONSTER)
			.sized(2.8F,5F).build("chained_god"));

	public static final DeferredHolder<EntityType<?>, EntityType<PoisonballEntity>> POISONBALL = ENTITIES.register("poisonball", () -> EntityType.Builder.<PoisonballEntity>of(PoisonballEntity::new, MobCategory.MISC)
			.sized(1.0F, 1.0F).build("poisonball"));

	public static final DeferredHolder<EntityType<?>, EntityType<DimensionShattererProjectileEntity>> DIMENSION_SHATTERER_PROJECTILE = ENTITIES.register("dimension_shatterer_projectile", () -> EntityType.Builder.<DimensionShattererProjectileEntity>of(DimensionShattererProjectileEntity::new, MobCategory.MISC)
			.sized(1.0F, 1.0F).build("dimension_shatterer_projectile"));

	public static final DeferredHolder<EntityType<?>, EntityType<FlyingJellyfishEntity>> FLYING_JELLYFISH = ENTITIES.register("flying_jellyfish", () -> EntityType.Builder.<FlyingJellyfishEntity>of(FlyingJellyfishEntity::new, MobCategory.MONSTER)
			.sized(3.0F, 3.0F).build("flying_jellyfish"));

	public static final DeferredHolder<EntityType<?>, EntityType<ThrownStellarEgg>> THROWN_STELLAR_EGG = ENTITIES.register("thrown_stellar_egg", () -> EntityType.Builder.<ThrownStellarEgg>of(ThrownStellarEgg::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("thrown_stellar_egg"));

	public static final DeferredHolder<EntityType<?>, EntityType<IronShurikenEntity>> IRON_SHURIKEN = ENTITIES.register("iron_shuriken", () -> EntityType.Builder.<IronShurikenEntity>of(IronShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("iron_shuriken"));

	public static final DeferredHolder<EntityType<?>, EntityType<GoldShurikenEntity>> GOLD_SHURIKEN = ENTITIES.register("gold_shuriken", () -> EntityType.Builder.<GoldShurikenEntity>of(GoldShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("gold_shuriken"));

	public static final DeferredHolder<EntityType<?>, EntityType<DiamondShurikenEntity>> DIAMOND_SHURIKEN = ENTITIES.register("diamond_shuriken", () -> EntityType.Builder.<DiamondShurikenEntity>of(DiamondShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("diamond_shuriken"));

	public static final DeferredHolder<EntityType<?>, EntityType<NetheriteShurikenEntity>> NETHERITE_SHURIKEN = ENTITIES.register("netherite_shuriken", () -> EntityType.Builder.<NetheriteShurikenEntity>of(NetheriteShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("netherite_shuriken"));

	public static final DeferredHolder<EntityType<?>, EntityType<RubyShurikenEntity>> RUBY_SHURIKEN = ENTITIES.register("ruby_shuriken", () -> EntityType.Builder.<RubyShurikenEntity>of(RubyShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("ruby_shuriken"));

	public static final DeferredHolder<EntityType<?>, EntityType<AzuriteShurikenEntity>> AZURITE_SHURIKEN = ENTITIES.register("azurite_shuriken", () -> EntityType.Builder.<AzuriteShurikenEntity>of(AzuriteShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("azurite_shuriken"));

	public static final DeferredHolder<EntityType<?>, EntityType<MagmaticGelShurikenEntity>> MAGMATIC_GEL_SHURIKEN = ENTITIES.register("magmatic_gel_shuriken", () -> EntityType.Builder.<MagmaticGelShurikenEntity>of(MagmaticGelShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("magmatic_gel_shuriken"));

	public static final DeferredHolder<EntityType<?>, EntityType<VoluciteShurikenEntity>> VOLUCITE_SHURIKEN = ENTITIES.register("volucite_shuriken", () -> EntityType.Builder.<VoluciteShurikenEntity>of(VoluciteShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("volucite_shuriken"));

	public static final DeferredHolder<EntityType<?>, EntityType<ObsidianShurikenEntity>> OBSIDIAN_SHURIKEN = ENTITIES.register("obsidian_shuriken", () -> EntityType.Builder.<ObsidianShurikenEntity>of(ObsidianShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("obsidian_shuriken"));

	public static final DeferredHolder<EntityType<?>, EntityType<LunaticCrystalShurikenEntity>> LUNATIC_CRYSTAL_SHURIKEN = ENTITIES.register("lunatic_crystal_shuriken", () -> EntityType.Builder.<LunaticCrystalShurikenEntity>of(LunaticCrystalShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("lunatic_crystal_shuriken"));

	public static final DeferredHolder<EntityType<?>, EntityType<ArsonistShurikenEntity>> ARSONIST_SHURIKEN = ENTITIES.register("arsonist_shuriken", () -> EntityType.Builder.<ArsonistShurikenEntity>of(ArsonistShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("arsonist_shuriken"));

	public static final DeferredHolder<EntityType<?>, EntityType<LightningShurikenEntity>> LIGHTNING_SHURIKEN = ENTITIES.register("lightning_shuriken", () -> EntityType.Builder.<LightningShurikenEntity>of(LightningShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("lightning_shuriken"));

	public static final DeferredHolder<EntityType<?>, EntityType<VoluciteArrowEntity>> VOLUCITE_BLOWPIPE_ARROW = ENTITIES.register("volucite_blowpipe_arrow", () -> EntityType.Builder.<VoluciteArrowEntity>of(VoluciteArrowEntity::new, MobCategory.MISC)
			.sized(0.5F, 0.5F).build("volucite_blowpipe_arrow"));

	public static final DeferredHolder<EntityType<?>, EntityType<RubyArrowEntity>> RUBY_BLOWPIPE_ARROW = ENTITIES.register("ruby_blowpipe_arrow", () -> EntityType.Builder.<RubyArrowEntity>of(RubyArrowEntity::new, MobCategory.MISC)
			.sized(0.5F, 0.5F).build("ruby_blowpipe_arrow"));

	public static final DeferredHolder<EntityType<?>, EntityType<LunaticProjectileEntity>> LUNATIC_PROJECTILE = ENTITIES.register("lunatic_projectile", () -> EntityType.Builder.<LunaticProjectileEntity>of(LunaticProjectileEntity::new, MobCategory.MISC)
			.sized(1.1F, 1.1F).build("lunatic_projectile"));

	public static final DeferredHolder<EntityType<?>, EntityType<ShadowProjectileEntity>> SHADOW_PROJECTILE = ENTITIES.register("shadow_projectile", () -> EntityType.Builder.<ShadowProjectileEntity>of(ShadowProjectileEntity::new, MobCategory.MISC)
			.sized(1.1F, 1.1F).build("shadow_projectile"));

	public static final DeferredHolder<EntityType<?>, EntityType<ShadowFlyingSkullEntity>> SHADOW_FLYING_SKULL = ENTITIES.register("shadow_flying_skull", () -> EntityType.Builder.of(ShadowFlyingSkullEntity::new, MobCategory.MONSTER)
			.sized(0.8F, 0.8F).build("shadow_flying_skull"));

	public static final DeferredHolder<EntityType<?>, EntityType<ShadowTrollEntity>> SHADOW_TROLL = ENTITIES.register("shadow_troll", () -> EntityType.Builder.of(ShadowTrollEntity::new, MobCategory.MONSTER)
			.sized(1.2F, 2.5F).build("shadow_troll"));

	public static final DeferredHolder<EntityType<?>, EntityType<ShadowAutomatonEntity>> SHADOW_AUTOMATON = ENTITIES.register("shadow_automaton", () -> EntityType.Builder.of(ShadowAutomatonEntity::new, MobCategory.MONSTER)
			.sized(0.8F,1.95F).build("shadow_automaton"));

	public static final DeferredHolder<EntityType<?>, EntityType<LilithEntity>> LILITH = ENTITIES.register("lilith", () -> EntityType.Builder.<LilithEntity>of(LilithEntity::new, MobCategory.MONSTER)
			.sized(0.8F,1.8F).build("lilith"));

	public static final DeferredHolder<EntityType<?>, EntityType<AerialTreeChestMimicEntity>> AERIAL_TREE_MIMIC = ENTITIES.register("aerial_tree_mimic", () -> EntityType.Builder.<AerialTreeChestMimicEntity>of(AerialTreeChestMimicEntity::new, MobCategory.MONSTER)
			.sized(0.99F, 2.0F).build("aerial_tree_mimic"));

	public static final DeferredHolder<EntityType<?>, EntityType<GoldenBeechChestMimicEntity>> GOLDEN_BEECH_MIMIC = ENTITIES.register("golden_beech_mimic", () -> EntityType.Builder.<GoldenBeechChestMimicEntity>of(GoldenBeechChestMimicEntity::new, MobCategory.MONSTER)
			.sized(0.99F, 2.0F).build("golden_beech_mimic"));

	public static final DeferredHolder<EntityType<?>, EntityType<SkyCactusFiberChestMimicEntity>> SKY_CACTUS_FIBER_MIMIC = ENTITIES.register("sky_cactus_fiber_mimic", () -> EntityType.Builder.<SkyCactusFiberChestMimicEntity>of(SkyCactusFiberChestMimicEntity::new, MobCategory.MONSTER)
			.sized(0.99F, 2.0F).build("sky_cactus_fiber_mimic"));

	public static final DeferredHolder<EntityType<?>, EntityType<CopperPineChestMimicEntity>> COPPER_PINE_MIMIC = ENTITIES.register("copper_pine_mimic", () -> EntityType.Builder.<CopperPineChestMimicEntity>of(CopperPineChestMimicEntity::new, MobCategory.MONSTER)
			.sized(0.99F, 2.0F).build("copper_pine_mimic"));

	public static final DeferredHolder<EntityType<?>, EntityType<ShadowPineBarrelMimicEntity>> SHADOW_PINE_MIMIC = ENTITIES.register("shadow_pine_mimic", () -> EntityType.Builder.<ShadowPineBarrelMimicEntity>of(ShadowPineBarrelMimicEntity::new, MobCategory.MONSTER)
			.sized(0.99F, 0.9F).build("shadow_pine_mimic"));

	public static final DeferredHolder<EntityType<?>, EntityType<HellSpiderEntity>> HELL_SPIDER = ENTITIES.register("hell_spider", () -> EntityType.Builder.of(HellSpiderEntity::new, MobCategory.MONSTER)
			.sized(0.85F, 0.8F).build("hell_spider"));

	public static final DeferredHolder<EntityType<?>, EntityType<CrystalSpiderEntity>> CRYSTAL_SPIDER = ENTITIES.register("crystal_spider", () -> EntityType.Builder.of(CrystalSpiderEntity::new, MobCategory.MONSTER)
			.sized(1.1F, 0.9F).build("crystal_spider"));

	public static final DeferredHolder<EntityType<?>, EntityType<ShadowSpiderEntity>> SHADOW_SPIDER = ENTITIES.register("shadow_spider", () -> EntityType.Builder.of(ShadowSpiderEntity::new, MobCategory.MONSTER)
			.sized(0.85F, 0.8F).build("shadow_spider"));

	public static final DeferredHolder<EntityType<?>, EntityType<ForestCaterpillarEntity>> FOREST_CATERPILLAR = ENTITIES.register("forest_caterpillar", () -> EntityType.Builder.<ForestCaterpillarEntity>of(ForestCaterpillarEntity::new, MobCategory.CREATURE)
			.sized(0.4F, 0.4F).build("forest_caterpillar"));

	public static final DeferredHolder<EntityType<?>, EntityType<CrystalCaterpillarEntity>> CRYSTAL_CATERPILLAR = ENTITIES.register("crystal_caterpillar", () -> EntityType.Builder.<CrystalCaterpillarEntity>of(CrystalCaterpillarEntity::new, MobCategory.CREATURE)
			.sized(0.4F, 0.4F).build("crystal_caterpillar"));

	public static final DeferredHolder<EntityType<?>, EntityType<AerialHellPaintingEntity>> AERIAL_HELL_PAINTING = ENTITIES.register("aerial_hell_painting", () -> EntityType.Builder.<AerialHellPaintingEntity>of(AerialHellPaintingEntity::new, MobCategory.MISC)
			.sized(0.5F, 0.5F).setTrackingRange(10).updateInterval(Integer.MAX_VALUE).build("aerial_hell_painting"));

	public static void entitySpawnPlacements(RegisterSpawnPlacementsEvent event)
	{
		event.register(SANDY_SHEEP.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellAnimalEntity::canAerialHellAnimalSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(GLIDING_TURTLE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellAnimalEntity::canAerialHellAnimalSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(STELLAR_CHICKEN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, StellarChickenEntity::canSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(STELLAR_BOAR.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BoarEntity::canSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);

		event.register(EVIL_COW.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(CORTINARIUS_COW.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(STELLAR_ENT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(SHROOMBOOM.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(VERDIGRIS_ZOMBIE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		//event.register(MUD_SOLDIER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE); //pb : makes hostile entities that can spawn in daylight spawn without any requirement (1 entity / tick / chunk, game crash)
		event.register(HELL_SPIDER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(CRYSTAL_SPIDER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(SHADOW_SPIDER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(VENOMOUS_SNAKE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(WORM.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(MUMMY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(SLIME_PIRATE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(SLIME_NINJA_PIRATE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(GHOST_SLIME_PIRATE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GhostSlimePirateEntity::canGhostSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(GHOST_SLIME_NINJA_PIRATE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GhostSlimePirateEntity::canGhostSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(CRYSTAL_SLIME.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrystalSlimeEntity::canSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(FOREST_CATERPILLAR.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractCaterpillarEntity::canCaterpillarSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(CRYSTAL_CATERPILLAR.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractCaterpillarEntity::canCaterpillarSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(SHADOW_TROLL.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(SHADOW_AUTOMATON.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(MUD_SOLDIER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(FAT_PHANTOM.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FatPhantomEntity::canSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(KODAMA.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, KodamaEntity::canSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(FLYING_JELLYFISH.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FlyingJellyfishEntity::canJellyfishSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(ICE_SPIRIT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(FIRE_SPIRIT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(ELECTRO_SPIRIT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(TORN_SPIRIT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}
}
