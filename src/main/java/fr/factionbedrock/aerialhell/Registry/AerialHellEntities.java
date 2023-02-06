package fr.factionbedrock.aerialhell.Registry;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import fr.factionbedrock.aerialhell.Entity.*;
import fr.factionbedrock.aerialhell.Entity.Bosses.*;
import fr.factionbedrock.aerialhell.Entity.Monster.*;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.*;
import fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit.*;
import fr.factionbedrock.aerialhell.Entity.Neutral.ForestCaterpillarEntity;
import fr.factionbedrock.aerialhell.Entity.Passive.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellEntities
{
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
	
	public static final EntityType<StellarStoneAutomatonEntity> STELLAR_STONE_AUTOMATON_TYPE = EntityType.Builder.create(StellarStoneAutomatonEntity::new, EntityClassification.MONSTER)
	            .size(0.8F,2.5F).build("stellar_stone_automaton");
	  
	public static final EntityType<MudGolemEntity> MUD_GOLEM_TYPE = EntityType.Builder.create(MudGolemEntity::new, EntityClassification.MONSTER)
	            .size(1.4F,2.3F).build("mud_golem");
	
	public static final EntityType<MudSpectralGolemEntity> MUD_SPECTRAL_GOLEM_TYPE = EntityType.Builder.create(MudSpectralGolemEntity::new, EntityClassification.MONSTER)
            	.size(1.4F,2.3F).build("mud_spectral_golem");
	
	public static final EntityType<CrystalGolemEntity> CRYSTAL_GOLEM_TYPE = EntityType.Builder.create(CrystalGolemEntity::new, EntityClassification.MONSTER)
            	.size(0.9F,1.95F).build("crystal_golem");
	
	public static final EntityType<LunaticPriestEntity> LUNATIC_PRIEST_TYPE = EntityType.Builder.create(LunaticPriestEntity::new, EntityClassification.MONSTER)
        		.size(0.8F,2.5F).build("lunatic_priest");
	
	public static final EntityType<EvilCowEntity> EVIL_COW_TYPE = EntityType.Builder.<EvilCowEntity>create(EvilCowEntity::new, EntityClassification.MONSTER)
			    .size(0.99F, 1.4F).build("evil_cow");
	
	public static final EntityType<EvilCowEntity> CORTINARIUS_COW_TYPE = EntityType.Builder.<EvilCowEntity>create(EvilCowEntity::new, EntityClassification.MONSTER)
		    	.size(0.99F, 1.4F).build("cortinarius_cow");
	
	public static final EntityType<ShroomBoomEntity> SHROOMBOOM_TYPE = EntityType.Builder.<ShroomBoomEntity>create(ShroomBoomEntity::new, EntityClassification.MONSTER)
				.size(0.6F, 1.7F).build("shroomboom");
	
	public static final EntityType<VerdigrisZombieEntity> VERDIGRIS_ZOMBIE_TYPE = EntityType.Builder.create(VerdigrisZombieEntity::new, EntityClassification.MONSTER)
            	.size(0.7F,2.0F).build("verdigris_zombie");
	
	public static final EntityType<SandySheepEntity> SANDY_SHEEP_TYPE = EntityType.Builder.<SandySheepEntity>create(SandySheepEntity::new, EntityClassification.CREATURE)
			    .size(0.9F, 1.4F).build("sandy_sheep");
	
	public static final EntityType<FatPhantomEntity> FAT_PHANTOM_TYPE = EntityType.Builder.<FatPhantomEntity>create(FatPhantomEntity::new, EntityClassification.CREATURE)
			    .size(1.3F, 1.0F).immuneToFire().build("fat_phantom");
	
	public static final EntityType<CrystalSlimeEntity> CRYSTAL_SLIME_TYPE = EntityType.Builder.<CrystalSlimeEntity>create(CrystalSlimeEntity::new, EntityClassification.MONSTER)
			    .size(2.0F, 2.0F).build("crystal_slime");
	
	public static final EntityType<MudSoldierEntity> MUD_SOLDIER_TYPE = EntityType.Builder.<MudSoldierEntity>create(MudSoldierEntity::new, EntityClassification.MONSTER)
		    	.size(0.6F, 1.99F).build("mud_soldier");
	
	public static final EntityType<MudSpectralSoldierEntity> MUD_SPECTRAL_SOLDIER_TYPE = EntityType.Builder.<MudSpectralSoldierEntity>create(MudSpectralSoldierEntity::new, EntityClassification.MONSTER)
	    		.size(0.6F, 1.99F).build("mud_spectral_soldier");
	
	public static final EntityType<MudCycleMageEntity> MUD_CYCLE_MAGE_TYPE = EntityType.Builder.<MudCycleMageEntity>create(MudCycleMageEntity::new, EntityClassification.MONSTER)
	    		.size(0.6F, 1.99F).build("mud_cycle_mage");
	
	public static final EntityType<TornSpiritEntity> TORN_SPIRIT_TYPE = EntityType.Builder.<TornSpiritEntity>create(TornSpiritEntity::new, EntityClassification.MONSTER)
			    .size(0.8F,1.95F).build("torn_spirit");
	
	public static final EntityType<IceSpiritEntity> ICE_SPIRIT_TYPE = EntityType.Builder.<IceSpiritEntity>create(IceSpiritEntity::new, EntityClassification.MONSTER)
		    	.size(0.7F, 1.0F).build("ice_spirit");
	
	public static final EntityType<FireSpiritEntity> FIRE_SPIRIT_TYPE = EntityType.Builder.<FireSpiritEntity>create(FireSpiritEntity::new, EntityClassification.MONSTER)
	    		.size(0.7F, 1.0F).build("fire_spirit");
	
	public static final EntityType<ElectroSpiritEntity> ELECTRO_SPIRIT_TYPE = EntityType.Builder.<ElectroSpiritEntity>create(ElectroSpiritEntity::new, EntityClassification.MONSTER)
    			.size(0.7F, 1.0F).build("electro_spirit");
	
	public static final EntityType<ChainedGodEntity> CHAINED_GOD_TYPE = EntityType.Builder.<ChainedGodEntity>create(ChainedGodEntity::new, EntityClassification.MONSTER)
		        .size(2.8F,5F).build("chained_god");
	
	public static final EntityType<PoisonballEntity> POISONBALL_TYPE = EntityType.Builder.<PoisonballEntity>create(PoisonballEntity::new, EntityClassification.MISC)
			    .size(1.0F, 1.0F).build("poisonball");
	
	public static final EntityType<FlyingJellyfishEntity> FLYING_JELLYFISH_TYPE = EntityType.Builder.<FlyingJellyfishEntity>create(FlyingJellyfishEntity::new, EntityClassification.MONSTER)
			    .size(3.0F, 3.0F).build("flying_jellyfish");
	
	public static final EntityType<IronThrowingKnifeEntity> IRON_THROWING_KNIFE_TYPE = EntityType.Builder.<IronThrowingKnifeEntity>create(IronThrowingKnifeEntity::new, EntityClassification.MISC)
    			.size(0.25F, 0.25F).build("iron_throwing_knife");
	
	public static final EntityType<GoldThrowingKnifeEntity> GOLD_THROWING_KNIFE_TYPE = EntityType.Builder.<GoldThrowingKnifeEntity>create(GoldThrowingKnifeEntity::new, EntityClassification.MISC)
	    		.size(0.25F, 0.25F).build("gold_throwing_knife");
	
	public static final EntityType<DiamondThrowingKnifeEntity> DIAMOND_THROWING_KNIFE_TYPE = EntityType.Builder.<DiamondThrowingKnifeEntity>create(DiamondThrowingKnifeEntity::new, EntityClassification.MISC)
	    		.size(0.25F, 0.25F).build("diamond_throwing_knife");
	
	public static final EntityType<NetheriteThrowingKnifeEntity> NETHERITE_THROWING_KNIFE_TYPE = EntityType.Builder.<NetheriteThrowingKnifeEntity>create(NetheriteThrowingKnifeEntity::new, EntityClassification.MISC)
	    		.size(0.25F, 0.25F).build("netherite_throwing_knife");
	
	public static final EntityType<RubyThrowingKnifeEntity> RUBY_THROWING_KNIFE_TYPE = EntityType.Builder.<RubyThrowingKnifeEntity>create(RubyThrowingKnifeEntity::new, EntityClassification.MISC)
    			.size(0.25F, 0.25F).build("ruby_throwing_knife");
	
	public static final EntityType<AzuriteThrowingKnifeEntity> AZURITE_THROWING_KNIFE_TYPE = EntityType.Builder.<AzuriteThrowingKnifeEntity>create(AzuriteThrowingKnifeEntity::new, EntityClassification.MISC)
    			.size(0.25F, 0.25F).build("azurite_throwing_knife");
	
	public static final EntityType<MagmaticGelThrowingKnifeEntity> MAGMATIC_GEL_THROWING_KNIFE_TYPE = EntityType.Builder.<MagmaticGelThrowingKnifeEntity>create(MagmaticGelThrowingKnifeEntity::new, EntityClassification.MISC)
    			.size(0.25F, 0.25F).build("magmatic_gel_throwing_knife");
	
	public static final EntityType<VoluciteThrowingKnifeEntity> VOLUCITE_THROWING_KNIFE_TYPE = EntityType.Builder.<VoluciteThrowingKnifeEntity>create(VoluciteThrowingKnifeEntity::new, EntityClassification.MISC)
		    	.size(0.25F, 0.25F).build("volucite_throwing_knife");
	
	public static final EntityType<ObsidianThrowingKnifeEntity> OBSIDIAN_THROWING_KNIFE_TYPE = EntityType.Builder.<ObsidianThrowingKnifeEntity>create(ObsidianThrowingKnifeEntity::new, EntityClassification.MISC)
    			.size(0.25F, 0.25F).build("obsidian_throwing_knife");
	
	public static final EntityType<LunaticCrystalThrowingKnifeEntity> LUNATIC_CRYSTAL_THROWING_KNIFE_TYPE = EntityType.Builder.<LunaticCrystalThrowingKnifeEntity>create(LunaticCrystalThrowingKnifeEntity::new, EntityClassification.MISC)
	    		.size(0.25F, 0.25F).build("lunatic_crystal_throwing_knife");
	
	public static final EntityType<ArsonistThrowingKnifeEntity> ARSONIST_THROWING_KNIFE_TYPE = EntityType.Builder.<ArsonistThrowingKnifeEntity>create(ArsonistThrowingKnifeEntity::new, EntityClassification.MISC)
    			.size(0.25F, 0.25F).build("arsonist_throwing_knife");
	
	public static final EntityType<LightningThrowingKnifeEntity> LIGHTNING_THROWING_KNIFE_TYPE = EntityType.Builder.<LightningThrowingKnifeEntity>create(LightningThrowingKnifeEntity::new, EntityClassification.MISC)
			    .size(0.25F, 0.25F).build("lightning_throwing_knife");
	
	public static final EntityType<VoluciteArrowEntity> VOLUCITE_BLOWPIPE_ARROW_TYPE = EntityType.Builder.<VoluciteArrowEntity>create(VoluciteArrowEntity::new, EntityClassification.MISC)
			    .size(0.5F, 0.5F).build("volucite_blowpipe_arrow");
	
	public static final EntityType<RubyArrowEntity> RUBY_BLOWPIPE_ARROW_TYPE = EntityType.Builder.<RubyArrowEntity>create(RubyArrowEntity::new, EntityClassification.MISC)
		    	.size(0.5F, 0.5F).build("ruby_blowpipe_arrow");
	
	public static final EntityType<LunaticProjectileEntity> LUNATIC_PROJECTILE_TYPE = EntityType.Builder.<LunaticProjectileEntity>create(LunaticProjectileEntity::new, EntityClassification.MISC)
		    	.size(1.1F, 1.1F).build("lunatic_projectile");
	
	public static final EntityType<ShadowTrollEntity> SHADOW_TROLL_TYPE = EntityType.Builder.create(ShadowTrollEntity::new, EntityClassification.MONSTER)
			    .size(1.2F, 2.5F).build("shadow_troll");
	
	public static final EntityType<LilithEntity> LILITH_TYPE = EntityType.Builder.<LilithEntity>create(LilithEntity::new, EntityClassification.MONSTER)
	        	.size(0.8F,1.8F).build("lilith");
	
	public static final EntityType<AerialTreeChestMimicEntity> AERIAL_TREE_MIMIC_TYPE = EntityType.Builder.<AerialTreeChestMimicEntity>create(AerialTreeChestMimicEntity::new, EntityClassification.MONSTER)
			    .size(0.95F, 2.0F).build("aerial_tree_mimic");
	
	public static final EntityType<GoldenBeechChestMimicEntity> GOLDEN_BEECH_MIMIC_TYPE = EntityType.Builder.<GoldenBeechChestMimicEntity>create(GoldenBeechChestMimicEntity::new, EntityClassification.MONSTER)
		    	.size(0.95F, 2.0F).build("golden_beech_mimic");
	
	public static final EntityType<SkyCactusFiberChestMimicEntity> SKY_CACTUS_FIBER_MIMIC_TYPE = EntityType.Builder.<SkyCactusFiberChestMimicEntity>create(SkyCactusFiberChestMimicEntity::new, EntityClassification.MONSTER)
		    	.size(0.95F, 2.0F).build("sky_cactus_fiber_mimic");
	
	public static final EntityType<CopperPineChestMimicEntity> COPPER_PINE_MIMIC_TYPE = EntityType.Builder.<CopperPineChestMimicEntity>create(CopperPineChestMimicEntity::new, EntityClassification.MONSTER)
		    	.size(0.95F, 2.0F).build("copper_pine_mimic");
	
	public static final EntityType<HellSpiderEntity> HELL_SPIDER_TYPE = EntityType.Builder.create(HellSpiderEntity::new, EntityClassification.MONSTER)
			    .size(0.85F, 0.8F).build("hell_spider");
	
	public static final EntityType<CrystalSpiderEntity> CRYSTAL_SPIDER_TYPE = EntityType.Builder.create(CrystalSpiderEntity::new, EntityClassification.MONSTER)
		    	.size(1.1F, 0.9F).build("crystal_spider");
	
	public static final EntityType<ShadowSpiderEntity> SHADOW_SPIDER_TYPE = EntityType.Builder.create(ShadowSpiderEntity::new, EntityClassification.MONSTER)
	    		.size(0.85F, 0.8F).build("shadow_spider");
	
	public static final EntityType<ForestCaterpillarEntity> FOREST_CATERPILLAR_TYPE = EntityType.Builder.<ForestCaterpillarEntity>create(ForestCaterpillarEntity::new, EntityClassification.CREATURE)
		    	.size(0.4F, 0.4F).build("forest_caterpillar");
	
	public static final EntityType<CrystalCaterpillarEntity> CRYSTAL_CATERPILLAR_TYPE = EntityType.Builder.<CrystalCaterpillarEntity>create(CrystalCaterpillarEntity::new, EntityClassification.CREATURE)
	    		.size(0.4F, 0.4F).build("crystal_caterpillar");
	
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
	public static final RegistryObject<EntityType<IronThrowingKnifeEntity>> IRON_THROWING_KNIFE = ENTITIES.register("iron_throwing_knife", () -> IRON_THROWING_KNIFE_TYPE);
	public static final RegistryObject<EntityType<GoldThrowingKnifeEntity>> GOLD_THROWING_KNIFE = ENTITIES.register("gold_throwing_knife", () -> GOLD_THROWING_KNIFE_TYPE);
	public static final RegistryObject<EntityType<DiamondThrowingKnifeEntity>> DIAMOND_THROWING_KNIFE = ENTITIES.register("diamond_throwing_knife", () -> DIAMOND_THROWING_KNIFE_TYPE);
	public static final RegistryObject<EntityType<NetheriteThrowingKnifeEntity>> NETHERITE_THROWING_KNIFE = ENTITIES.register("netherite_throwing_knife", () -> NETHERITE_THROWING_KNIFE_TYPE);
	public static final RegistryObject<EntityType<RubyThrowingKnifeEntity>> RUBY_THROWING_KNIFE = ENTITIES.register("ruby_throwing_knife", () -> RUBY_THROWING_KNIFE_TYPE);
	public static final RegistryObject<EntityType<AzuriteThrowingKnifeEntity>> AZURITE_THROWING_KNIFE = ENTITIES.register("azurite_throwing_knife", () -> AZURITE_THROWING_KNIFE_TYPE);
	public static final RegistryObject<EntityType<MagmaticGelThrowingKnifeEntity>> MAGMATIC_GEL_THROWING_KNIFE = ENTITIES.register("magmatic_gel_throwing_knife", () -> MAGMATIC_GEL_THROWING_KNIFE_TYPE);
	public static final RegistryObject<EntityType<VoluciteThrowingKnifeEntity>> VOLUCITE_THROWING_KNIFE = ENTITIES.register("volucite_throwing_knife", () -> VOLUCITE_THROWING_KNIFE_TYPE);
	public static final RegistryObject<EntityType<ObsidianThrowingKnifeEntity>> OBSIDIAN_THROWING_KNIFE = ENTITIES.register("obsidian_throwing_knife", () -> OBSIDIAN_THROWING_KNIFE_TYPE);
	public static final RegistryObject<EntityType<LunaticCrystalThrowingKnifeEntity>> LUNATIC_CRYSTAL_THROWING_KNIFE = ENTITIES.register("lunatic_crystal_throwing_knife", () -> LUNATIC_CRYSTAL_THROWING_KNIFE_TYPE);
	public static final RegistryObject<EntityType<ArsonistThrowingKnifeEntity>> ARSONIST_THROWING_KNIFE = ENTITIES.register("arsonist_throwing_knife", () -> ARSONIST_THROWING_KNIFE_TYPE);
	public static final RegistryObject<EntityType<LightningThrowingKnifeEntity>> LIGHTNING_THROWING_KNIFE = ENTITIES.register("lightning_throwing_knife", () -> LIGHTNING_THROWING_KNIFE_TYPE);
	public static final RegistryObject<EntityType<VoluciteArrowEntity>> VOLUCITE_BLOWPIPE_ARROW = ENTITIES.register("volucite_blowpipe_arrow", () -> VOLUCITE_BLOWPIPE_ARROW_TYPE);
	public static final RegistryObject<EntityType<RubyArrowEntity>> RUBY_BLOWPIPE_ARROW = ENTITIES.register("ruby_blowpipe_arrow", () -> RUBY_BLOWPIPE_ARROW_TYPE);
	public static final RegistryObject<EntityType<LunaticProjectileEntity>> LUNATIC_PROJECTILE = ENTITIES.register("lunatic_projectile", () -> LUNATIC_PROJECTILE_TYPE);
	public static final RegistryObject<EntityType<ShadowTrollEntity>> SHADOW_TROLL = ENTITIES.register("shadow_troll", () -> SHADOW_TROLL_TYPE);
	public static final RegistryObject<EntityType<LilithEntity>> LILITH = ENTITIES.register("lilith", () -> LILITH_TYPE);
	public static final RegistryObject<EntityType<AerialTreeChestMimicEntity>> AERIAL_TREE_MIMIC = ENTITIES.register("aerial_tree_mimic", () -> AERIAL_TREE_MIMIC_TYPE);
	public static final RegistryObject<EntityType<GoldenBeechChestMimicEntity>> GOLDEN_BEECH_MIMIC = ENTITIES.register("golden_beech_mimic", () -> GOLDEN_BEECH_MIMIC_TYPE);
	public static final RegistryObject<EntityType<SkyCactusFiberChestMimicEntity>> SKY_CACTUS_FIBER_MIMIC = ENTITIES.register("sky_cactus_fiber_mimic", () -> SKY_CACTUS_FIBER_MIMIC_TYPE);
	public static final RegistryObject<EntityType<CopperPineChestMimicEntity>> COPPER_PINE_MIMIC = ENTITIES.register("copper_pine_mimic", () -> COPPER_PINE_MIMIC_TYPE);
	public static final RegistryObject<EntityType<HellSpiderEntity>> HELL_SPIDER = ENTITIES.register("hell_spider", () -> HELL_SPIDER_TYPE);
	public static final RegistryObject<EntityType<CrystalSpiderEntity>> CRYSTAL_SPIDER = ENTITIES.register("crystal_spider", () -> CRYSTAL_SPIDER_TYPE);
	public static final RegistryObject<EntityType<ShadowSpiderEntity>> SHADOW_SPIDER = ENTITIES.register("shadow_spider", () -> SHADOW_SPIDER_TYPE);
	public static final RegistryObject<EntityType<ForestCaterpillarEntity>> FOREST_CATERPILLAR = ENTITIES.register("forest_caterpillar", () -> FOREST_CATERPILLAR_TYPE);
	public static final RegistryObject<EntityType<CrystalCaterpillarEntity>> CRYSTAL_CATERPILLAR = ENTITIES.register("crystal_caterpillar", () -> CRYSTAL_CATERPILLAR_TYPE);
	
	public static void entitySpawnPlacements()
	{
		EntitySpawnPlacementRegistry.register(SANDY_SHEEP.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellAnimalEntity::canAerialHellAnimalSpawn);
		
		EntitySpawnPlacementRegistry.register(EVIL_COW.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		EntitySpawnPlacementRegistry.register(CORTINARIUS_COW.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		EntitySpawnPlacementRegistry.register(SHROOMBOOM.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		EntitySpawnPlacementRegistry.register(VERDIGRIS_ZOMBIE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		//EntitySpawnPlacementRegistry.register(MUD_SOLDIER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn); //pb : makes hostile entities that can spawn in daylight spawn without any requirement (1 entity / tick / chunk, game crash)
		EntitySpawnPlacementRegistry.register(HELL_SPIDER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		EntitySpawnPlacementRegistry.register(CRYSTAL_SPIDER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		EntitySpawnPlacementRegistry.register(SHADOW_SPIDER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		EntitySpawnPlacementRegistry.register(CRYSTAL_SLIME.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CrystalSlimeEntity::canSpawn);
		EntitySpawnPlacementRegistry.register(FOREST_CATERPILLAR.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractCaterpillarEntity::canCaterpillarSpawn);
		EntitySpawnPlacementRegistry.register(CRYSTAL_CATERPILLAR.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractCaterpillarEntity::canCaterpillarSpawn);
		EntitySpawnPlacementRegistry.register(SHADOW_TROLL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		EntitySpawnPlacementRegistry.register(MUD_SOLDIER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		EntitySpawnPlacementRegistry.register(FAT_PHANTOM.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FatPhantomEntity::canSpawn);
		EntitySpawnPlacementRegistry.register(FLYING_JELLYFISH.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FlyingJellyfishEntity::canJellyfishSpawn);
		EntitySpawnPlacementRegistry.register(ICE_SPIRIT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		EntitySpawnPlacementRegistry.register(FIRE_SPIRIT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		EntitySpawnPlacementRegistry.register(ELECTRO_SPIRIT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
		EntitySpawnPlacementRegistry.register(TORN_SPIRIT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AerialHellHostileEntity::canHostileEntitySpawn);
	}
}
