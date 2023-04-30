package fr.factionbedrock.aerialhell.Registry;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import fr.factionbedrock.aerialhell.Entity.*;
import fr.factionbedrock.aerialhell.Entity.Bosses.*;
import fr.factionbedrock.aerialhell.Entity.Monster.*;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.*;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.*;
import fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit.*;
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
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
	
	public static final EntityType<StellarStoneAutomatonEntity> STELLAR_STONE_AUTOMATON_TYPE = EntityType.Builder.of(StellarStoneAutomatonEntity::new, MobCategory.MONSTER)
	            .sized(0.9F,2.1F).build("stellar_stone_automaton");
	  
	public static final EntityType<MudGolemEntity> MUD_GOLEM_TYPE = EntityType.Builder.of(MudGolemEntity::new, MobCategory.MONSTER)
	            .sized(1.4F,2.3F).build("mud_golem");

	public static final EntityType<MudSpectralGolemEntity> MUD_SPECTRAL_GOLEM_TYPE = EntityType.Builder.of(MudSpectralGolemEntity::new, MobCategory.MONSTER)
            	.sized(1.4F,2.3F).build("mud_spectral_golem");
	
	public static final EntityType<CrystalGolemEntity> CRYSTAL_GOLEM_TYPE = EntityType.Builder.of(CrystalGolemEntity::new, MobCategory.MONSTER)
            	.sized(0.9F,1.95F).build("crystal_golem");
	
	public static final EntityType<LunaticPriestEntity> LUNATIC_PRIEST_TYPE = EntityType.Builder.of(LunaticPriestEntity::new, MobCategory.MONSTER)
        		.sized(0.8F,2.5F).build("lunatic_priest");
	
	public static final EntityType<EvilCowEntity> EVIL_COW_TYPE = EntityType.Builder.<EvilCowEntity>of(EvilCowEntity::new, MobCategory.MONSTER)
			    .sized(0.99F, 1.4F).build("evil_cow");
	
	public static final EntityType<EvilCowEntity> CORTINARIUS_COW_TYPE = EntityType.Builder.<EvilCowEntity>of(EvilCowEntity::new, MobCategory.MONSTER)
		    	.sized(0.99F, 1.4F).build("cortinarius_cow");
	
	public static final EntityType<ShroomBoomEntity> SHROOMBOOM_TYPE = EntityType.Builder.<ShroomBoomEntity>of(ShroomBoomEntity::new, MobCategory.MONSTER)
				.sized(0.6F, 1.7F).build("shroomboom");
	
	public static final EntityType<VerdigrisZombieEntity> VERDIGRIS_ZOMBIE_TYPE = EntityType.Builder.of(VerdigrisZombieEntity::new, MobCategory.MONSTER)
            	.sized(0.7F,2.0F).build("verdigris_zombie");
	
	public static final EntityType<SandySheepEntity> SANDY_SHEEP_TYPE = EntityType.Builder.<SandySheepEntity>of(SandySheepEntity::new, MobCategory.CREATURE)
			    .sized(0.9F, 1.4F).build("sandy_sheep");

	public static final EntityType<GlidingTurtleEntity> GLIDING_TURTLE_TYPE = EntityType.Builder.<GlidingTurtleEntity>of(GlidingTurtleEntity::new, MobCategory.CREATURE)
				.sized(1.4F, 1.9F).build("gliding_turtle");
	
	public static final EntityType<FatPhantomEntity> FAT_PHANTOM_TYPE = EntityType.Builder.<FatPhantomEntity>of(FatPhantomEntity::new, MobCategory.CREATURE)
			    .sized(1.3F, 1.0F).fireImmune().build("fat_phantom");
	
	public static final EntityType<CrystalSlimeEntity> CRYSTAL_SLIME_TYPE = EntityType.Builder.<CrystalSlimeEntity>of(CrystalSlimeEntity::new, MobCategory.MONSTER)
			    .sized(2.0F, 2.0F).build("crystal_slime");
	
	public static final EntityType<MudSoldierEntity> MUD_SOLDIER_TYPE = EntityType.Builder.<MudSoldierEntity>of(MudSoldierEntity::new, MobCategory.MONSTER)
		    	.sized(0.6F, 1.99F).build("mud_soldier");
	
	public static final EntityType<MudSpectralSoldierEntity> MUD_SPECTRAL_SOLDIER_TYPE = EntityType.Builder.<MudSpectralSoldierEntity>of(MudSpectralSoldierEntity::new, MobCategory.MONSTER)
	    		.sized(0.6F, 1.99F).build("mud_spectral_soldier");
	
	public static final EntityType<MudCycleMageEntity> MUD_CYCLE_MAGE_TYPE = EntityType.Builder.<MudCycleMageEntity>of(MudCycleMageEntity::new, MobCategory.MONSTER)
	    		.sized(0.6F, 1.99F).build("mud_cycle_mage");
	
	public static final EntityType<TornSpiritEntity> TORN_SPIRIT_TYPE = EntityType.Builder.<TornSpiritEntity>of(TornSpiritEntity::new, MobCategory.MONSTER)
			    .sized(0.8F,1.95F).build("torn_spirit");
	
	public static final EntityType<IceSpiritEntity> ICE_SPIRIT_TYPE = EntityType.Builder.<IceSpiritEntity>of(IceSpiritEntity::new, MobCategory.MONSTER)
		    	.sized(0.7F, 1.0F).build("ice_spirit");
	
	public static final EntityType<FireSpiritEntity> FIRE_SPIRIT_TYPE = EntityType.Builder.<FireSpiritEntity>of(FireSpiritEntity::new, MobCategory.MONSTER)
	    		.sized(0.7F, 1.0F).build("fire_spirit");
	
	public static final EntityType<ElectroSpiritEntity> ELECTRO_SPIRIT_TYPE = EntityType.Builder.<ElectroSpiritEntity>of(ElectroSpiritEntity::new, MobCategory.MONSTER)
    			.sized(0.7F, 1.0F).build("electro_spirit");
	
	public static final EntityType<ChainedGodEntity> CHAINED_GOD_TYPE = EntityType.Builder.<ChainedGodEntity>of(ChainedGodEntity::new, MobCategory.MONSTER)
		        .sized(2.8F,5F).build("chained_god");
	
	public static final EntityType<PoisonballEntity> POISONBALL_TYPE = EntityType.Builder.<PoisonballEntity>of(PoisonballEntity::new, MobCategory.MISC)
			    .sized(1.0F, 1.0F).build("poisonball");
	
	public static final EntityType<FlyingJellyfishEntity> FLYING_JELLYFISH_TYPE = EntityType.Builder.<FlyingJellyfishEntity>of(FlyingJellyfishEntity::new, MobCategory.MONSTER)
			    .sized(3.0F, 3.0F).build("flying_jellyfish");
	
	public static final EntityType<IronShurikenEntity> IRON_SHURIKEN_TYPE = EntityType.Builder.<IronShurikenEntity>of(IronShurikenEntity::new, MobCategory.MISC)
    			.sized(0.25F, 0.25F).build("iron_shuriken");
	
	public static final EntityType<GoldShurikenEntity> GOLD_SHURIKEN_TYPE = EntityType.Builder.<GoldShurikenEntity>of(GoldShurikenEntity::new, MobCategory.MISC)
	    		.sized(0.25F, 0.25F).build("gold_shuriken");
	
	public static final EntityType<DiamondShurikenEntity> DIAMOND_SHURIKEN_TYPE = EntityType.Builder.<DiamondShurikenEntity>of(DiamondShurikenEntity::new, MobCategory.MISC)
	    		.sized(0.25F, 0.25F).build("diamond_shuriken");
	
	public static final EntityType<NetheriteShurikenEntity> NETHERITE_SHURIKEN_TYPE = EntityType.Builder.<NetheriteShurikenEntity>of(NetheriteShurikenEntity::new, MobCategory.MISC)
	    		.sized(0.25F, 0.25F).build("netherite_shuriken");
	
	public static final EntityType<RubyShurikenEntity> RUBY_SHURIKEN_TYPE = EntityType.Builder.<RubyShurikenEntity>of(RubyShurikenEntity::new, MobCategory.MISC)
    			.sized(0.25F, 0.25F).build("ruby_shuriken");
	
	public static final EntityType<AzuriteShurikenEntity> AZURITE_SHURIKEN_TYPE = EntityType.Builder.<AzuriteShurikenEntity>of(AzuriteShurikenEntity::new, MobCategory.MISC)
    			.sized(0.25F, 0.25F).build("azurite_shuriken");
	
	public static final EntityType<MagmaticGelShurikenEntity> MAGMATIC_GEL_SHURIKEN_TYPE = EntityType.Builder.<MagmaticGelShurikenEntity>of(MagmaticGelShurikenEntity::new, MobCategory.MISC)
    			.sized(0.25F, 0.25F).build("magmatic_gel_shuriken");
	
	public static final EntityType<VoluciteShurikenEntity> VOLUCITE_SHURIKEN_TYPE = EntityType.Builder.<VoluciteShurikenEntity>of(VoluciteShurikenEntity::new, MobCategory.MISC)
		    	.sized(0.25F, 0.25F).build("volucite_shuriken");
	
	public static final EntityType<ObsidianShurikenEntity> OBSIDIAN_SHURIKEN_TYPE = EntityType.Builder.<ObsidianShurikenEntity>of(ObsidianShurikenEntity::new, MobCategory.MISC)
    			.sized(0.25F, 0.25F).build("obsidian_shuriken");
	
	public static final EntityType<LunaticCrystalShurikenEntity> LUNATIC_CRYSTAL_SHURIKEN_TYPE = EntityType.Builder.<LunaticCrystalShurikenEntity>of(LunaticCrystalShurikenEntity::new, MobCategory.MISC)
	    		.sized(0.25F, 0.25F).build("lunatic_crystal_shuriken");
	
	public static final EntityType<ArsonistShurikenEntity> ARSONIST_SHURIKEN_TYPE = EntityType.Builder.<ArsonistShurikenEntity>of(ArsonistShurikenEntity::new, MobCategory.MISC)
    			.sized(0.25F, 0.25F).build("arsonist_shuriken");
	
	public static final EntityType<LightningShurikenEntity> LIGHTNING_SHURIKEN_TYPE = EntityType.Builder.<LightningShurikenEntity>of(LightningShurikenEntity::new, MobCategory.MISC)
			    .sized(0.25F, 0.25F).build("lightning_shuriken");
	
	public static final EntityType<VoluciteArrowEntity> VOLUCITE_BLOWPIPE_ARROW_TYPE = EntityType.Builder.<VoluciteArrowEntity>of(VoluciteArrowEntity::new, MobCategory.MISC)
			    .sized(0.5F, 0.5F).build("volucite_blowpipe_arrow");
	
	public static final EntityType<RubyArrowEntity> RUBY_BLOWPIPE_ARROW_TYPE = EntityType.Builder.<RubyArrowEntity>of(RubyArrowEntity::new, MobCategory.MISC)
		    	.sized(0.5F, 0.5F).build("ruby_blowpipe_arrow");
	
	public static final EntityType<LunaticProjectileEntity> LUNATIC_PROJECTILE_TYPE = EntityType.Builder.<LunaticProjectileEntity>of(LunaticProjectileEntity::new, MobCategory.MISC)
		    	.sized(1.1F, 1.1F).build("lunatic_projectile");

	public static final EntityType<ShadowProjectileEntity> SHADOW_PROJECTILE_TYPE = EntityType.Builder.<ShadowProjectileEntity>of(ShadowProjectileEntity::new, MobCategory.MISC)
				.sized(1.1F, 1.1F).build("shadow_projectile");

	public static final EntityType<ShadowFlyingSkullEntity> SHADOW_FLYING_SKULL_TYPE = EntityType.Builder.of(ShadowFlyingSkullEntity::new, MobCategory.MONSTER)
				.sized(0.8F, 0.8F).build("shadow_flying_skull");
	
	public static final EntityType<ShadowTrollEntity> SHADOW_TROLL_TYPE = EntityType.Builder.of(ShadowTrollEntity::new, MobCategory.MONSTER)
			    .sized(1.2F, 2.5F).build("shadow_troll");

	public static final EntityType<ShadowAutomatonEntity> SHADOW_AUTOMATON_TYPE = EntityType.Builder.of(ShadowAutomatonEntity::new, MobCategory.MONSTER)
				.sized(0.8F,1.95F).build("shadow_automaton");
	
	public static final EntityType<LilithEntity> LILITH_TYPE = EntityType.Builder.<LilithEntity>of(LilithEntity::new, MobCategory.MONSTER)
	        	.sized(0.8F,1.8F).build("lilith");
	
	public static final EntityType<AerialTreeChestMimicEntity> AERIAL_TREE_MIMIC_TYPE = EntityType.Builder.<AerialTreeChestMimicEntity>of(AerialTreeChestMimicEntity::new, MobCategory.MONSTER)
			    .sized(0.99F, 2.0F).build("aerial_tree_mimic");
	
	public static final EntityType<GoldenBeechChestMimicEntity> GOLDEN_BEECH_MIMIC_TYPE = EntityType.Builder.<GoldenBeechChestMimicEntity>of(GoldenBeechChestMimicEntity::new, MobCategory.MONSTER)
		    	.sized(0.99F, 2.0F).build("golden_beech_mimic");
	
	public static final EntityType<SkyCactusFiberChestMimicEntity> SKY_CACTUS_FIBER_MIMIC_TYPE = EntityType.Builder.<SkyCactusFiberChestMimicEntity>of(SkyCactusFiberChestMimicEntity::new, MobCategory.MONSTER)
		    	.sized(0.99F, 2.0F).build("sky_cactus_fiber_mimic");
	
	public static final EntityType<CopperPineChestMimicEntity> COPPER_PINE_MIMIC_TYPE = EntityType.Builder.<CopperPineChestMimicEntity>of(CopperPineChestMimicEntity::new, MobCategory.MONSTER)
		    	.sized(0.99F, 2.0F).build("copper_pine_mimic");

	public static final EntityType<ShadowPineBarrelMimicEntity> SHADOW_PINE_MIMIC_TYPE = EntityType.Builder.<ShadowPineBarrelMimicEntity>of(ShadowPineBarrelMimicEntity::new, MobCategory.MONSTER)
				.sized(0.99F, 0.9F).build("shadow_pine_mimic");

	public static final EntityType<HellSpiderEntity> HELL_SPIDER_TYPE = EntityType.Builder.of(HellSpiderEntity::new, MobCategory.MONSTER)
			    .sized(0.85F, 0.8F).build("hell_spider");
	
	public static final EntityType<CrystalSpiderEntity> CRYSTAL_SPIDER_TYPE = EntityType.Builder.of(CrystalSpiderEntity::new, MobCategory.MONSTER)
		    	.sized(1.1F, 0.9F).build("crystal_spider");
	
	public static final EntityType<ShadowSpiderEntity> SHADOW_SPIDER_TYPE = EntityType.Builder.of(ShadowSpiderEntity::new, MobCategory.MONSTER)
	    		.sized(0.85F, 0.8F).build("shadow_spider");
	
	public static final EntityType<ForestCaterpillarEntity> FOREST_CATERPILLAR_TYPE = EntityType.Builder.<ForestCaterpillarEntity>of(ForestCaterpillarEntity::new, MobCategory.CREATURE)
		    	.sized(0.4F, 0.4F).build("forest_caterpillar");
	
	public static final EntityType<CrystalCaterpillarEntity> CRYSTAL_CATERPILLAR_TYPE = EntityType.Builder.<CrystalCaterpillarEntity>of(CrystalCaterpillarEntity::new, MobCategory.CREATURE)
	    		.sized(0.4F, 0.4F).build("crystal_caterpillar");

	public static final EntityType<AerialHellPaintingEntity> AERIAL_HELL_PAINTING_TYPE = EntityType.Builder.<AerialHellPaintingEntity>of(AerialHellPaintingEntity::new, MobCategory.MISC)
				.sized(0.5F, 0.5F).setTrackingRange(10).updateInterval(Integer.MAX_VALUE).build("aerial_hell_painting");
	
	public static final RegistryObject<EntityType<StellarStoneAutomatonEntity>> STELLAR_STONE_AUTOMATON = ENTITIES.register("stellar_stone_automaton", () -> STELLAR_STONE_AUTOMATON_TYPE);
	public static final RegistryObject<EntityType<MudGolemEntity>> MUD_GOLEM = ENTITIES.register("mud_golem", () -> MUD_GOLEM_TYPE);
	public static final RegistryObject<EntityType<MudSpectralGolemEntity>> MUD_SPECTRAL_GOLEM = ENTITIES.register("mud_spectral_golem", () -> MUD_SPECTRAL_GOLEM_TYPE);
	public static final RegistryObject<EntityType<CrystalGolemEntity>> CRYSTAL_GOLEM = ENTITIES.register("crystal_golem", () -> CRYSTAL_GOLEM_TYPE);
	public static final RegistryObject<EntityType<LunaticPriestEntity>> LUNATIC_PRIEST = ENTITIES.register("lunatic_priest", () -> LUNATIC_PRIEST_TYPE);
	public static final RegistryObject<EntityType<EvilCowEntity>> EVIL_COW = ENTITIES.register("evil_cow", () -> EVIL_COW_TYPE);
	public static final RegistryObject<EntityType<EvilCowEntity>> CORTINARIUS_COW = ENTITIES.register("cortinarius_cow", () -> CORTINARIUS_COW_TYPE);
	public static final RegistryObject<EntityType<ShroomBoomEntity>> SHROOMBOOM = ENTITIES.register("shroomboom", () -> SHROOMBOOM_TYPE);
	public static final RegistryObject<EntityType<VerdigrisZombieEntity>> VERDIGRIS_ZOMBIE = ENTITIES.register("verdigris_zombie", () -> VERDIGRIS_ZOMBIE_TYPE);
	public static final RegistryObject<EntityType<SandySheepEntity>> SANDY_SHEEP = ENTITIES.register("sandy_sheep", () -> SANDY_SHEEP_TYPE);
	public static final RegistryObject<EntityType<GlidingTurtleEntity>> GLIDING_TURTLE = ENTITIES.register("gliding_turtle", () -> GLIDING_TURTLE_TYPE);
	public static final RegistryObject<EntityType<FatPhantomEntity>> FAT_PHANTOM = ENTITIES.register("fat_phantom", () -> FAT_PHANTOM_TYPE);
	public static final RegistryObject<EntityType<CrystalSlimeEntity>> CRYSTAL_SLIME = ENTITIES.register("crystal_slime", () -> CRYSTAL_SLIME_TYPE);
	public static final RegistryObject<EntityType<MudSoldierEntity>> MUD_SOLDIER = ENTITIES.register("mud_soldier", () -> MUD_SOLDIER_TYPE);
	public static final RegistryObject<EntityType<MudSpectralSoldierEntity>> MUD_SPECTRAL_SOLDIER = ENTITIES.register("mud_spectral_soldier", () -> MUD_SPECTRAL_SOLDIER_TYPE);
	public static final RegistryObject<EntityType<MudCycleMageEntity>> MUD_CYCLE_MAGE = ENTITIES.register("mud_cycle_mage", () -> MUD_CYCLE_MAGE_TYPE);
	public static final RegistryObject<EntityType<TornSpiritEntity>> TORN_SPIRIT = ENTITIES.register("torn_spirit", () -> TORN_SPIRIT_TYPE);
	public static final RegistryObject<EntityType<IceSpiritEntity>> ICE_SPIRIT = ENTITIES.register("ice_spirit", () -> ICE_SPIRIT_TYPE);
	public static final RegistryObject<EntityType<FireSpiritEntity>> FIRE_SPIRIT = ENTITIES.register("fire_spirit", () -> FIRE_SPIRIT_TYPE);
	public static final RegistryObject<EntityType<ElectroSpiritEntity>> ELECTRO_SPIRIT = ENTITIES.register("electro_spirit", () -> ELECTRO_SPIRIT_TYPE);
	public static final RegistryObject<EntityType<ChainedGodEntity>> CHAINED_GOD = ENTITIES.register("chained_god", () -> CHAINED_GOD_TYPE);
	public static final RegistryObject<EntityType<PoisonballEntity>> POISONBALL = ENTITIES.register("poisonball", () -> POISONBALL_TYPE);
	public static final RegistryObject<EntityType<FlyingJellyfishEntity>> FLYING_JELLYFISH = ENTITIES.register("flying_jellyfish", () -> FLYING_JELLYFISH_TYPE);
	public static final RegistryObject<EntityType<IronShurikenEntity>> IRON_SHURIKEN = ENTITIES.register("iron_shuriken", () -> IRON_SHURIKEN_TYPE);
	public static final RegistryObject<EntityType<GoldShurikenEntity>> GOLD_SHURIKEN = ENTITIES.register("gold_shuriken", () -> GOLD_SHURIKEN_TYPE);
	public static final RegistryObject<EntityType<DiamondShurikenEntity>> DIAMOND_SHURIKEN = ENTITIES.register("diamond_shuriken", () -> DIAMOND_SHURIKEN_TYPE);
	public static final RegistryObject<EntityType<NetheriteShurikenEntity>> NETHERITE_SHURIKEN = ENTITIES.register("netherite_shuriken", () -> NETHERITE_SHURIKEN_TYPE);
	public static final RegistryObject<EntityType<RubyShurikenEntity>> RUBY_SHURIKEN = ENTITIES.register("ruby_shuriken", () -> RUBY_SHURIKEN_TYPE);
	public static final RegistryObject<EntityType<AzuriteShurikenEntity>> AZURITE_SHURIKEN = ENTITIES.register("azurite_shuriken", () -> AZURITE_SHURIKEN_TYPE);
	public static final RegistryObject<EntityType<MagmaticGelShurikenEntity>> MAGMATIC_GEL_SHURIKEN = ENTITIES.register("magmatic_gel_shuriken", () -> MAGMATIC_GEL_SHURIKEN_TYPE);
	public static final RegistryObject<EntityType<VoluciteShurikenEntity>> VOLUCITE_SHURIKEN = ENTITIES.register("volucite_shuriken", () -> VOLUCITE_SHURIKEN_TYPE);
	public static final RegistryObject<EntityType<ObsidianShurikenEntity>> OBSIDIAN_SHURIKEN = ENTITIES.register("obsidian_shuriken", () -> OBSIDIAN_SHURIKEN_TYPE);
	public static final RegistryObject<EntityType<LunaticCrystalShurikenEntity>> LUNATIC_CRYSTAL_SHURIKEN = ENTITIES.register("lunatic_crystal_shuriken", () -> LUNATIC_CRYSTAL_SHURIKEN_TYPE);
	public static final RegistryObject<EntityType<ArsonistShurikenEntity>> ARSONIST_SHURIKEN = ENTITIES.register("arsonist_shuriken", () -> ARSONIST_SHURIKEN_TYPE);
	public static final RegistryObject<EntityType<LightningShurikenEntity>> LIGHTNING_SHURIKEN = ENTITIES.register("lightning_shuriken", () -> LIGHTNING_SHURIKEN_TYPE);
	public static final RegistryObject<EntityType<VoluciteArrowEntity>> VOLUCITE_BLOWPIPE_ARROW = ENTITIES.register("volucite_blowpipe_arrow", () -> VOLUCITE_BLOWPIPE_ARROW_TYPE);
	public static final RegistryObject<EntityType<RubyArrowEntity>> RUBY_BLOWPIPE_ARROW = ENTITIES.register("ruby_blowpipe_arrow", () -> RUBY_BLOWPIPE_ARROW_TYPE);
	public static final RegistryObject<EntityType<LunaticProjectileEntity>> LUNATIC_PROJECTILE = ENTITIES.register("lunatic_projectile", () -> LUNATIC_PROJECTILE_TYPE);
	public static final RegistryObject<EntityType<ShadowProjectileEntity>> SHADOW_PROJECTILE = ENTITIES.register("shadow_projectile", () -> SHADOW_PROJECTILE_TYPE);
	public static final RegistryObject<EntityType<ShadowFlyingSkullEntity>> SHADOW_FLYING_SKULL = ENTITIES.register("shadow_flying_skull", () -> SHADOW_FLYING_SKULL_TYPE);
	public static final RegistryObject<EntityType<ShadowTrollEntity>> SHADOW_TROLL = ENTITIES.register("shadow_troll", () -> SHADOW_TROLL_TYPE);
	public static final RegistryObject<EntityType<ShadowAutomatonEntity>> SHADOW_AUTOMATON = ENTITIES.register("shadow_automaton", () -> SHADOW_AUTOMATON_TYPE);
	public static final RegistryObject<EntityType<LilithEntity>> LILITH = ENTITIES.register("lilith", () -> LILITH_TYPE);
	public static final RegistryObject<EntityType<AerialTreeChestMimicEntity>> AERIAL_TREE_MIMIC = ENTITIES.register("aerial_tree_mimic", () -> AERIAL_TREE_MIMIC_TYPE);
	public static final RegistryObject<EntityType<GoldenBeechChestMimicEntity>> GOLDEN_BEECH_MIMIC = ENTITIES.register("golden_beech_mimic", () -> GOLDEN_BEECH_MIMIC_TYPE);
	public static final RegistryObject<EntityType<SkyCactusFiberChestMimicEntity>> SKY_CACTUS_FIBER_MIMIC = ENTITIES.register("sky_cactus_fiber_mimic", () -> SKY_CACTUS_FIBER_MIMIC_TYPE);
	public static final RegistryObject<EntityType<CopperPineChestMimicEntity>> COPPER_PINE_MIMIC = ENTITIES.register("copper_pine_mimic", () -> COPPER_PINE_MIMIC_TYPE);
	public static final RegistryObject<EntityType<ShadowPineBarrelMimicEntity>> SHADOW_PINE_MIMIC = ENTITIES.register("shadow_pine_mimic", () -> SHADOW_PINE_MIMIC_TYPE);
	public static final RegistryObject<EntityType<HellSpiderEntity>> HELL_SPIDER = ENTITIES.register("hell_spider", () -> HELL_SPIDER_TYPE);
	public static final RegistryObject<EntityType<CrystalSpiderEntity>> CRYSTAL_SPIDER = ENTITIES.register("crystal_spider", () -> CRYSTAL_SPIDER_TYPE);
	public static final RegistryObject<EntityType<ShadowSpiderEntity>> SHADOW_SPIDER = ENTITIES.register("shadow_spider", () -> SHADOW_SPIDER_TYPE);
	public static final RegistryObject<EntityType<ForestCaterpillarEntity>> FOREST_CATERPILLAR = ENTITIES.register("forest_caterpillar", () -> FOREST_CATERPILLAR_TYPE);
	public static final RegistryObject<EntityType<CrystalCaterpillarEntity>> CRYSTAL_CATERPILLAR = ENTITIES.register("crystal_caterpillar", () -> CRYSTAL_CATERPILLAR_TYPE);
	public static final RegistryObject<EntityType<AerialHellPaintingEntity>> AERIAL_HELL_PAINTING = ENTITIES.register("aerial_hell_painting", () -> AERIAL_HELL_PAINTING_TYPE);

	public static void entitySpawnPlacements()
	{
		SpawnPlacements.register(SANDY_SHEEP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellAnimalEntity::canAerialHellAnimalSpawn);
		SpawnPlacements.register(GLIDING_TURTLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AerialHellAnimalEntity::canAerialHellAnimalSpawn);
		
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
