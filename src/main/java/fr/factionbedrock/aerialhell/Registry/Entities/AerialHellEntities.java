package fr.factionbedrock.aerialhell.Registry.Entities;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import fr.factionbedrock.aerialhell.Entity.*;
import fr.factionbedrock.aerialhell.Entity.Bosses.*;
import fr.factionbedrock.aerialhell.Entity.Monster.*;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.*;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.*;
import fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit.*;
import fr.factionbedrock.aerialhell.Entity.Neutral.BoarEntity;
import fr.factionbedrock.aerialhell.Entity.Neutral.ForestCaterpillarEntity;
import fr.factionbedrock.aerialhell.Entity.Passive.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellEntities
{
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

	public static final RegistryObject<EntityType<StellarStoneAutomatonEntity>> STELLAR_STONE_AUTOMATON = ENTITIES.register("stellar_stone_automaton", () -> EntityType.Builder.of(StellarStoneAutomatonEntity::new, MobCategory.MONSTER)
			.sized(0.9F,2.1F).build("stellar_stone_automaton"));

	public static final RegistryObject<EntityType<MudGolemEntity>> MUD_GOLEM = ENTITIES.register("mud_golem", () -> EntityType.Builder.of(MudGolemEntity::new, MobCategory.MONSTER)
			.sized(1.4F,2.3F).build("mud_golem"));

	public static final RegistryObject<EntityType<MudSpectralGolemEntity>> MUD_SPECTRAL_GOLEM = ENTITIES.register("mud_spectral_golem", () -> EntityType.Builder.of(MudSpectralGolemEntity::new, MobCategory.MONSTER)
			.sized(1.4F,2.3F).build("mud_spectral_golem"));

	public static final RegistryObject<EntityType<CrystalGolemEntity>> CRYSTAL_GOLEM = ENTITIES.register("crystal_golem", () -> EntityType.Builder.of(CrystalGolemEntity::new, MobCategory.MONSTER)
			.sized(0.9F,1.95F).build("crystal_golem"));

	public static final RegistryObject<EntityType<LunaticPriestEntity>> LUNATIC_PRIEST = ENTITIES.register("lunatic_priest", () -> EntityType.Builder.of(LunaticPriestEntity::new, MobCategory.MONSTER)
			.sized(0.8F,2.5F).build("lunatic_priest"));

	public static final RegistryObject<EntityType<EvilCowEntity>> EVIL_COW = ENTITIES.register("evil_cow", () -> EntityType.Builder.<EvilCowEntity>of(EvilCowEntity::new, MobCategory.MONSTER)
			.sized(0.99F, 1.4F).build("evil_cow"));

	public static final RegistryObject<EntityType<EvilCowEntity>> CORTINARIUS_COW = ENTITIES.register("cortinarius_cow", () -> EntityType.Builder.<EvilCowEntity>of(EvilCowEntity::new, MobCategory.MONSTER)
			.sized(0.99F, 1.4F).build("cortinarius_cow"));

	public static final RegistryObject<EntityType<StellarChickenEntity>> STELLAR_CHICKEN = ENTITIES.register("stellar_chicken", () -> EntityType.Builder.<StellarChickenEntity>of(StellarChickenEntity::new, MobCategory.CREATURE)
			.sized(0.4F, 0.7F).build("stellar_chicken"));

	public static final RegistryObject<EntityType<BoarEntity>> STELLAR_BOAR = ENTITIES.register("stellar_boar", () -> EntityType.Builder.<BoarEntity>of(BoarEntity::new, MobCategory.CREATURE)
			.sized(0.9F, 0.9F).build("stellar_boar"));

	public static final RegistryObject<EntityType<ShroomBoomEntity>> SHROOMBOOM = ENTITIES.register("shroomboom", () -> EntityType.Builder.<ShroomBoomEntity>of(ShroomBoomEntity::new, MobCategory.MONSTER)
			.sized(0.6F, 1.7F).build("shroomboom"));

	public static final RegistryObject<EntityType<VerdigrisZombieEntity>> VERDIGRIS_ZOMBIE = ENTITIES.register("verdigris_zombie", () -> EntityType.Builder.of(VerdigrisZombieEntity::new, MobCategory.MONSTER)
			.sized(0.7F,2.0F).build("verdigris_zombie"));

	public static final RegistryObject<EntityType<SandySheepEntity>> SANDY_SHEEP = ENTITIES.register("sandy_sheep", () -> EntityType.Builder.<SandySheepEntity>of(SandySheepEntity::new, MobCategory.CREATURE)
			.sized(0.9F, 1.4F).build("sandy_sheep"));

	public static final RegistryObject<EntityType<GlidingTurtleEntity>> GLIDING_TURTLE = ENTITIES.register("gliding_turtle", () -> EntityType.Builder.<GlidingTurtleEntity>of(GlidingTurtleEntity::new, MobCategory.CREATURE)
			.sized(1.4F, 1.9F).build("gliding_turtle"));

	public static final RegistryObject<EntityType<FatPhantomEntity>> FAT_PHANTOM = ENTITIES.register("fat_phantom", () -> EntityType.Builder.<FatPhantomEntity>of(FatPhantomEntity::new, MobCategory.CREATURE)
			.sized(1.3F, 1.0F).fireImmune().build("fat_phantom"));

	public static final RegistryObject<EntityType<KodamaEntity>> KODAMA = ENTITIES.register("kodama", () -> EntityType.Builder.<KodamaEntity>of(KodamaEntity::new, MobCategory.CREATURE)
			.sized(0.7F, 0.9F).build("kodama"));

	public static final RegistryObject<EntityType<CrystalSlimeEntity>> CRYSTAL_SLIME = ENTITIES.register("crystal_slime", () -> EntityType.Builder.<CrystalSlimeEntity>of(CrystalSlimeEntity::new, MobCategory.MONSTER)
			.sized(2.0F, 2.0F).build("crystal_slime"));

	public static final RegistryObject<EntityType<MudSoldierEntity>> MUD_SOLDIER = ENTITIES.register("mud_soldier", () -> EntityType.Builder.<MudSoldierEntity>of(MudSoldierEntity::new, MobCategory.MONSTER)
			.sized(0.6F, 1.99F).build("mud_soldier"));

	public static final RegistryObject<EntityType<MudSpectralSoldierEntity>> MUD_SPECTRAL_SOLDIER = ENTITIES.register("mud_spectral_soldier", () -> EntityType.Builder.<MudSpectralSoldierEntity>of(MudSpectralSoldierEntity::new, MobCategory.MONSTER)
			.sized(0.6F, 1.99F).build("mud_spectral_soldier"));

	public static final RegistryObject<EntityType<MudCycleMageEntity>> MUD_CYCLE_MAGE = ENTITIES.register("mud_cycle_mage", () -> EntityType.Builder.<MudCycleMageEntity>of(MudCycleMageEntity::new, MobCategory.MONSTER)
			.sized(0.6F, 1.99F).build("mud_cycle_mage"));

	public static final RegistryObject<EntityType<MudSpectralCycleMageEntity>> MUD_SPECTRAL_CYCLE_MAGE = ENTITIES.register("mud_spectral_cycle_mage", () -> EntityType.Builder.<MudSpectralCycleMageEntity>of(MudSpectralCycleMageEntity::new, MobCategory.MONSTER)
			.sized(0.6F, 1.99F).build("mud_spectral_cycle_mage"));

	public static final RegistryObject<EntityType<TornSpiritEntity>> TORN_SPIRIT = ENTITIES.register("torn_spirit", () -> EntityType.Builder.<TornSpiritEntity>of(TornSpiritEntity::new, MobCategory.MONSTER)
			.sized(0.8F,1.95F).build("torn_spirit"));

	public static final RegistryObject<EntityType<IceSpiritEntity>> ICE_SPIRIT = ENTITIES.register("ice_spirit", () -> EntityType.Builder.<IceSpiritEntity>of(IceSpiritEntity::new, MobCategory.MONSTER)
			.sized(0.7F, 1.0F).build("ice_spirit"));

	public static final RegistryObject<EntityType<FireSpiritEntity>> FIRE_SPIRIT = ENTITIES.register("fire_spirit", () -> EntityType.Builder.<FireSpiritEntity>of(FireSpiritEntity::new, MobCategory.MONSTER)
			.sized(0.7F, 1.0F).build("fire_spirit"));

	public static final RegistryObject<EntityType<ElectroSpiritEntity>> ELECTRO_SPIRIT = ENTITIES.register("electro_spirit", () -> EntityType.Builder.<ElectroSpiritEntity>of(ElectroSpiritEntity::new, MobCategory.MONSTER)
			.sized(0.7F, 1.0F).build("electro_spirit"));

	public static final RegistryObject<EntityType<ChainedGodEntity>> CHAINED_GOD = ENTITIES.register("chained_god", () -> EntityType.Builder.<ChainedGodEntity>of(ChainedGodEntity::new, MobCategory.MONSTER)
			.sized(2.8F,5F).build("chained_god"));

	public static final RegistryObject<EntityType<PoisonballEntity>> POISONBALL = ENTITIES.register("poisonball", () -> EntityType.Builder.<PoisonballEntity>of(PoisonballEntity::new, MobCategory.MISC)
			.sized(1.0F, 1.0F).build("poisonball"));

	public static final RegistryObject<EntityType<FlyingJellyfishEntity>> FLYING_JELLYFISH = ENTITIES.register("flying_jellyfish", () -> EntityType.Builder.<FlyingJellyfishEntity>of(FlyingJellyfishEntity::new, MobCategory.MONSTER)
			.sized(3.0F, 3.0F).build("flying_jellyfish"));

	public static final RegistryObject<EntityType<ThrownStellarEgg>> THROWN_STELLAR_EGG = ENTITIES.register("thrown_stellar_egg", () -> EntityType.Builder.<ThrownStellarEgg>of(ThrownStellarEgg::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("thrown_stellar_egg"));

	public static final RegistryObject<EntityType<IronShurikenEntity>> IRON_SHURIKEN = ENTITIES.register("iron_shuriken", () -> EntityType.Builder.<IronShurikenEntity>of(IronShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("iron_shuriken"));

	public static final RegistryObject<EntityType<GoldShurikenEntity>> GOLD_SHURIKEN = ENTITIES.register("gold_shuriken", () -> EntityType.Builder.<GoldShurikenEntity>of(GoldShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("gold_shuriken"));

	public static final RegistryObject<EntityType<DiamondShurikenEntity>> DIAMOND_SHURIKEN = ENTITIES.register("diamond_shuriken", () -> EntityType.Builder.<DiamondShurikenEntity>of(DiamondShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("diamond_shuriken"));

	public static final RegistryObject<EntityType<NetheriteShurikenEntity>> NETHERITE_SHURIKEN = ENTITIES.register("netherite_shuriken", () -> EntityType.Builder.<NetheriteShurikenEntity>of(NetheriteShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("netherite_shuriken"));

	public static final RegistryObject<EntityType<RubyShurikenEntity>> RUBY_SHURIKEN = ENTITIES.register("ruby_shuriken", () -> EntityType.Builder.<RubyShurikenEntity>of(RubyShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("ruby_shuriken"));

	public static final RegistryObject<EntityType<AzuriteShurikenEntity>> AZURITE_SHURIKEN = ENTITIES.register("azurite_shuriken", () -> EntityType.Builder.<AzuriteShurikenEntity>of(AzuriteShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("azurite_shuriken"));

	public static final RegistryObject<EntityType<MagmaticGelShurikenEntity>> MAGMATIC_GEL_SHURIKEN = ENTITIES.register("magmatic_gel_shuriken", () -> EntityType.Builder.<MagmaticGelShurikenEntity>of(MagmaticGelShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("magmatic_gel_shuriken"));

	public static final RegistryObject<EntityType<VoluciteShurikenEntity>> VOLUCITE_SHURIKEN = ENTITIES.register("volucite_shuriken", () -> EntityType.Builder.<VoluciteShurikenEntity>of(VoluciteShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("volucite_shuriken"));

	public static final RegistryObject<EntityType<ObsidianShurikenEntity>> OBSIDIAN_SHURIKEN = ENTITIES.register("obsidian_shuriken", () -> EntityType.Builder.<ObsidianShurikenEntity>of(ObsidianShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("obsidian_shuriken"));

	public static final RegistryObject<EntityType<LunaticCrystalShurikenEntity>> LUNATIC_CRYSTAL_SHURIKEN = ENTITIES.register("lunatic_crystal_shuriken", () -> EntityType.Builder.<LunaticCrystalShurikenEntity>of(LunaticCrystalShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("lunatic_crystal_shuriken"));

	public static final RegistryObject<EntityType<ArsonistShurikenEntity>> ARSONIST_SHURIKEN = ENTITIES.register("arsonist_shuriken", () -> EntityType.Builder.<ArsonistShurikenEntity>of(ArsonistShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("arsonist_shuriken"));

	public static final RegistryObject<EntityType<LightningShurikenEntity>> LIGHTNING_SHURIKEN = ENTITIES.register("lightning_shuriken", () -> EntityType.Builder.<LightningShurikenEntity>of(LightningShurikenEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).build("lightning_shuriken"));

	public static final RegistryObject<EntityType<VoluciteArrowEntity>> VOLUCITE_BLOWPIPE_ARROW = ENTITIES.register("volucite_blowpipe_arrow", () -> EntityType.Builder.<VoluciteArrowEntity>of(VoluciteArrowEntity::new, MobCategory.MISC)
			.sized(0.5F, 0.5F).build("volucite_blowpipe_arrow"));

	public static final RegistryObject<EntityType<RubyArrowEntity>> RUBY_BLOWPIPE_ARROW = ENTITIES.register("ruby_blowpipe_arrow", () -> EntityType.Builder.<RubyArrowEntity>of(RubyArrowEntity::new, MobCategory.MISC)
			.sized(0.5F, 0.5F).build("ruby_blowpipe_arrow"));

	public static final RegistryObject<EntityType<LunaticProjectileEntity>> LUNATIC_PROJECTILE = ENTITIES.register("lunatic_projectile", () -> EntityType.Builder.<LunaticProjectileEntity>of(LunaticProjectileEntity::new, MobCategory.MISC)
			.sized(1.1F, 1.1F).build("lunatic_projectile"));

	public static final RegistryObject<EntityType<ShadowProjectileEntity>> SHADOW_PROJECTILE = ENTITIES.register("shadow_projectile", () -> EntityType.Builder.<ShadowProjectileEntity>of(ShadowProjectileEntity::new, MobCategory.MISC)
			.sized(1.1F, 1.1F).build("shadow_projectile"));

	public static final RegistryObject<EntityType<ShadowFlyingSkullEntity>> SHADOW_FLYING_SKULL = ENTITIES.register("shadow_flying_skull", () -> EntityType.Builder.of(ShadowFlyingSkullEntity::new, MobCategory.MONSTER)
			.sized(0.8F, 0.8F).build("shadow_flying_skull"));

	public static final RegistryObject<EntityType<ShadowTrollEntity>> SHADOW_TROLL = ENTITIES.register("shadow_troll", () -> EntityType.Builder.of(ShadowTrollEntity::new, MobCategory.MONSTER)
			.sized(1.2F, 2.5F).build("shadow_troll"));

	public static final RegistryObject<EntityType<ShadowAutomatonEntity>> SHADOW_AUTOMATON = ENTITIES.register("shadow_automaton", () -> EntityType.Builder.of(ShadowAutomatonEntity::new, MobCategory.MONSTER)
			.sized(0.8F,1.95F).build("shadow_automaton"));

	public static final RegistryObject<EntityType<LilithEntity>> LILITH = ENTITIES.register("lilith", () -> EntityType.Builder.<LilithEntity>of(LilithEntity::new, MobCategory.MONSTER)
			.sized(0.8F,1.8F).build("lilith"));

	public static final RegistryObject<EntityType<AerialTreeChestMimicEntity>> AERIAL_TREE_MIMIC = ENTITIES.register("aerial_tree_mimic", () -> EntityType.Builder.<AerialTreeChestMimicEntity>of(AerialTreeChestMimicEntity::new, MobCategory.MONSTER)
			.sized(0.99F, 2.0F).build("aerial_tree_mimic"));

	public static final RegistryObject<EntityType<GoldenBeechChestMimicEntity>> GOLDEN_BEECH_MIMIC = ENTITIES.register("golden_beech_mimic", () -> EntityType.Builder.<GoldenBeechChestMimicEntity>of(GoldenBeechChestMimicEntity::new, MobCategory.MONSTER)
			.sized(0.99F, 2.0F).build("golden_beech_mimic"));

	public static final RegistryObject<EntityType<SkyCactusFiberChestMimicEntity>> SKY_CACTUS_FIBER_MIMIC = ENTITIES.register("sky_cactus_fiber_mimic", () -> EntityType.Builder.<SkyCactusFiberChestMimicEntity>of(SkyCactusFiberChestMimicEntity::new, MobCategory.MONSTER)
			.sized(0.99F, 2.0F).build("sky_cactus_fiber_mimic"));

	public static final RegistryObject<EntityType<CopperPineChestMimicEntity>> COPPER_PINE_MIMIC = ENTITIES.register("copper_pine_mimic", () -> EntityType.Builder.<CopperPineChestMimicEntity>of(CopperPineChestMimicEntity::new, MobCategory.MONSTER)
			.sized(0.99F, 2.0F).build("copper_pine_mimic"));

	public static final RegistryObject<EntityType<ShadowPineBarrelMimicEntity>> SHADOW_PINE_MIMIC = ENTITIES.register("shadow_pine_mimic", () -> EntityType.Builder.<ShadowPineBarrelMimicEntity>of(ShadowPineBarrelMimicEntity::new, MobCategory.MONSTER)
			.sized(0.99F, 0.9F).build("shadow_pine_mimic"));

	public static final RegistryObject<EntityType<HellSpiderEntity>> HELL_SPIDER = ENTITIES.register("hell_spider", () -> EntityType.Builder.of(HellSpiderEntity::new, MobCategory.MONSTER)
			.sized(0.85F, 0.8F).build("hell_spider"));

	public static final RegistryObject<EntityType<CrystalSpiderEntity>> CRYSTAL_SPIDER = ENTITIES.register("crystal_spider", () -> EntityType.Builder.of(CrystalSpiderEntity::new, MobCategory.MONSTER)
			.sized(1.1F, 0.9F).build("crystal_spider"));

	public static final RegistryObject<EntityType<ShadowSpiderEntity>> SHADOW_SPIDER = ENTITIES.register("shadow_spider", () -> EntityType.Builder.of(ShadowSpiderEntity::new, MobCategory.MONSTER)
			.sized(0.85F, 0.8F).build("shadow_spider"));

	public static final RegistryObject<EntityType<ForestCaterpillarEntity>> FOREST_CATERPILLAR = ENTITIES.register("forest_caterpillar", () -> EntityType.Builder.<ForestCaterpillarEntity>of(ForestCaterpillarEntity::new, MobCategory.CREATURE)
			.sized(0.4F, 0.4F).build("forest_caterpillar"));

	public static final RegistryObject<EntityType<CrystalCaterpillarEntity>> CRYSTAL_CATERPILLAR = ENTITIES.register("crystal_caterpillar", () -> EntityType.Builder.<CrystalCaterpillarEntity>of(CrystalCaterpillarEntity::new, MobCategory.CREATURE)
			.sized(0.4F, 0.4F).build("crystal_caterpillar"));

	public static final RegistryObject<EntityType<AerialHellPaintingEntity>> AERIAL_HELL_PAINTING = ENTITIES.register("aerial_hell_painting", () -> EntityType.Builder.<AerialHellPaintingEntity>of(AerialHellPaintingEntity::new, MobCategory.MISC)
			.sized(0.5F, 0.5F).setTrackingRange(10).updateInterval(Integer.MAX_VALUE).build("aerial_hell_painting"));

	public static void entitySpawnPlacements()
	{
		SpawnPlacements.register(SANDY_SHEEP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellAnimalEntity::canAerialHellAnimalSpawn);
		SpawnPlacements.register(GLIDING_TURTLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellAnimalEntity::canAerialHellAnimalSpawn);
		SpawnPlacements.register(STELLAR_CHICKEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, StellarChickenEntity::canSpawn);
		SpawnPlacements.register(STELLAR_BOAR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BoarEntity::canSpawn);

		SpawnPlacements.register(EVIL_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(CORTINARIUS_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(SHROOMBOOM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(VERDIGRIS_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		//SpawnPlacements.register(MUD_SOLDIER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn); //pb : makes hostile entities that can spawn in daylight spawn without any requirement (1 entity / tick / chunk, game crash)
		SpawnPlacements.register(HELL_SPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(CRYSTAL_SPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(SHADOW_SPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(CRYSTAL_SLIME.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrystalSlimeEntity::canSpawn);
		SpawnPlacements.register(FOREST_CATERPILLAR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractCaterpillarEntity::canCaterpillarSpawn);
		SpawnPlacements.register(CRYSTAL_CATERPILLAR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractCaterpillarEntity::canCaterpillarSpawn);
		SpawnPlacements.register(SHADOW_TROLL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(SHADOW_AUTOMATON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(MUD_SOLDIER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(FAT_PHANTOM.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FatPhantomEntity::canSpawn);
		SpawnPlacements.register(FLYING_JELLYFISH.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FlyingJellyfishEntity::canJellyfishSpawn);
		SpawnPlacements.register(ICE_SPIRIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(FIRE_SPIRIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(ELECTRO_SPIRIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		SpawnPlacements.register(TORN_SPIRIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
	}
}
