package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.*;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.RecipeDisplay.CraftingRecipeDisplay;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.RecipeDisplay.CraftingTableRecipeDisplay;
import fr.factionbedrock.aerialhell.Client.Util.TextureInfo;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ARGB;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class GuideBookScreen extends Screen
{
    //book dimensions
    private static final int BOOK_TEXTURE_WIDTH = 384;
    private static final int BOOK_TEXTURE_HEIGHT = 192;
    //navigation button dimension
    private static final int NAVIGATION_BUTTON_SIZE = 20;

    //tabs dimensions
    private static final int TAB_MARGIN = 4; //margin (gap) from top to first tab, or from last tab to bottom
    private static final int TAB_WIDTH = 18;
    private static final int HOVERED_TAB_EXTRA_WIDTH = 4;
    private static final int TAB_HEIGHT = 24;
    private static final int TAB_GAP = 8;

    //page
    private static final int LINE_HEIGHT = 10;
    private static final int MARGIN_WIDTH = 12;
    private static final int LINE_WIDTH = 190;
    private static final int LINE_WIDTH_NO_MARGIN = LINE_WIDTH - 2 * MARGIN_WIDTH;
    private static final int MAX_LINES_PER_VISUAL_PAGE = 17;
    private static final int MAX_LINES_PER_TECHNICAL_PAGE = MAX_LINES_PER_VISUAL_PAGE * 2;

    private static final TextureInfo BOOK_TEXTURE = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/guide_book_page.png"), BOOK_TEXTURE_WIDTH, BOOK_TEXTURE_HEIGHT);
    private static final TextureInfo NAVIGATION_ARROW_PREVIOUS_PAGE = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_arrow_previous_page.png"), NAVIGATION_BUTTON_SIZE, NAVIGATION_BUTTON_SIZE);
    private static final TextureInfo NAVIGATION_ARROW_PREVIOUS_PAGE_HOVERED = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_arrow_previous_page_hovered.png"), NAVIGATION_BUTTON_SIZE, NAVIGATION_BUTTON_SIZE);
    private static final TextureInfo NAVIGATION_ARROW_NEXT_PAGE = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_arrow_next_page.png"), NAVIGATION_BUTTON_SIZE, NAVIGATION_BUTTON_SIZE);
    private static final TextureInfo NAVIGATION_ARROW_NEXT_PAGE_HOVERED = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_arrow_next_page_hovered.png"), NAVIGATION_BUTTON_SIZE, NAVIGATION_BUTTON_SIZE);
    private static final TextureInfo NAVIGATION_BUTTON_HOME_PAGE = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_button_home_page.png"), NAVIGATION_BUTTON_SIZE, NAVIGATION_BUTTON_SIZE);
    private static final TextureInfo NAVIGATION_BUTTON_HOME_PAGE_HOVERED = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_button_home_page_hovered.png"), NAVIGATION_BUTTON_SIZE, NAVIGATION_BUTTON_SIZE);

    private float textScale;

    private static final List<Page> ALL_PAGES = List.of(
            new Page("summary", BOOK_TEXTURE, 0)
                    .addTextureDisplay(1, Alignment.CENTER, 0.5F, "gui/guide_book/content/aerial_hell_logo", 300, 49)
                    .addParagraph(5, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(7, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "welcome_text")
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "table_of_content_title")
                    .addParagraph(20, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "table_of_content")
                    .addParagraph(20, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.RIGHT, "table_of_content_pages"),
            new Page("journey_1", BOOK_TEXTURE, 1)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "journey_section_title")
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "journey_section_quote")
                    .addParagraph(7, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "journey_content_desc")
                    .addItemTexture(14, Alignment.CENTER, 1.2F, AerialHellItems.STELLAR_GRASS_BLOCK, true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_1_enter_dimension")
                    .addTextureDisplay(19, Alignment.CENTER, 0.8F, "gui/guide_book/content/stellar_portal", 64, 80, "block.aerialhell.aerial_hell_portal")
                    .addParagraph(26, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "light_the_portal")
                    .addItemTexture(31, Alignment.RIGHT, 1.0F, AerialHellItems.STELLAR_LIGHTER, true),
            new Page("journey_2", BOOK_TEXTURE, 2)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_2_collect_resources")
                    .addItemTexture(5, Alignment.LEFT, 1.0F, AerialHellItems.SKY_WOOD_PICKAXE, true)
                    .addItemTexture(5, Alignment.CENTER, 1.0F, AerialHellItems.STELLAR_STONE_PICKAXE, true)
                    .addItemTexture(5, Alignment.RIGHT, 1.0F, AerialHellItems.STELLAR_STONE_AXE, true)
                    .addParagraph(8, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_3_collect_food")
                    .addItemTexture(9, Alignment.LEFT, 1.0F, AerialHellItems.STELLAR_WHEAT, true)
                    .addItemTexture(9, Alignment.CENTER, 1.0F, AerialHellItems.AERIAL_BERRY, true)
                    .addItemTexture(9, Alignment.RIGHT, 1.0F, AerialHellItems.WHITE_SOLID_ETHER_FRAGMENT, true)
                    .addParagraph(12, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_4_collect_fluorite")
                    .addItemTexture(15, Alignment.LEFT, 1.0F, AerialHellItems.FLUORITE_ORE, true)
                    .addItemTexture(15, Alignment.CENTER, 1.0F, AerialHellItems.FLUORITE, true)
                    .addItemTexture(15, Alignment.RIGHT, 1.0F, AerialHellItems.FLUORITE_TORCH, true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_5_craft_stellar_furnace")
                    .addCraftingTableRecipeDisplay(19, Alignment.CENTER, 1.0F, new CraftingTableRecipeDisplay.Ingredients(
                            AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE,
                            AerialHellItems.STELLAR_COBBLESTONE, () -> null, AerialHellItems.STELLAR_COBBLESTONE,
                            AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE
                    ), () -> AerialHellItems.STELLAR_FURNACE.get().getDefaultInstance(), true)
                    .addParagraph(25, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "cook_food")
                    .addSmeltingRecipeDisplay(26, Alignment.CENTER, 0.9F, AerialHellItems.AERIAL_BERRY, AerialHellItems.ROASTED_AERIAL_BERRY, true)
                    .addParagraph(31, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_6_mine_more")
                    .addItemTexture(32, Alignment.LEFT, 1.0F, AerialHellItems.RUBY_ORE, true)
                    .addItemTexture(32, Alignment.CENTER, 1.0F, AerialHellItems.AZURITE_ORE, true)
                    .addItemTexture(32, Alignment.RIGHT, 1.0F, AerialHellItems.MAGMATIC_GEL_ORE, true),
            new Page("journey_3", BOOK_TEXTURE, 3)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_7_craft_oscillator")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "craft_oscillator_prerequisites")
                    .addSmeltingRecipeDisplay(3, Alignment.CENTER, 1.0F, AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_STONE, true)
                    .addParagraph(9, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "craft_oscillator")
                    .addCraftingTableRecipeDisplay(10, Alignment.CENTER, 1.0F, new CraftingTableRecipeDisplay.Ingredients(
                            AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE,
                            AerialHellItems.STELLAR_STONE, AerialHellItems.FLUORITE, AerialHellItems.STELLAR_STONE,
                            AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE
                    ), () -> AerialHellItems.OSCILLATOR.get().getDefaultInstance(), true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_8_craft_ruby_tier_equipment")
                    .addParagraph(19, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "make_materials_oscillate")
                    .addOscillatingRecipeDisplay(20, Alignment.LEFT, 1.0F, AerialHellItems.RAW_RUBY, AerialHellItems.RUBY, true)
                    .addOscillatingRecipeDisplay(20, Alignment.RIGHT, 1.0F, AerialHellItems.RAW_AZURITE, AerialHellItems.AZURITE_CRYSTAL, true)
                    .addParagraph(26, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "craft_ruby_equipment")
                    .addItemTexture(29, Alignment.LEFT, 0.8F, AerialHellItems.RUBY_HELMET, true)
                    .addItemTexture(30, Alignment.LEFT, 0.8F, AerialHellItems.MAGMATIC_GEL_CHESTPLATE, true)
                    .addItemTexture(31, Alignment.LEFT, 0.8F, AerialHellItems.RUBY_LEGGINGS, true)
                    .addItemTexture(32, Alignment.LEFT, 0.8F, AerialHellItems.RUBY_BOOTS, true)
                    .addItemTexture(29, Alignment.CENTER, 0.85F, AerialHellItems.RUBY_PICKAXE, true)
                    .addItemTexture(30, Alignment.CENTER, 0.85F, AerialHellItems.AZURITE_AXE, true)
                    .addItemTexture(31, Alignment.CENTER, 0.85F, AerialHellItems.RUBY_SWORD, true)
                    .addItemTexture(32, Alignment.CENTER, 0.85F, AerialHellItems.MAGMATIC_GEL_SWORD, true)
                    .addItemTexture(29, Alignment.RIGHT, 0.8F, AerialHellItems.AZURITE_HELMET, true)
                    .addItemTexture(30, Alignment.RIGHT, 0.8F, AerialHellItems.AZURITE_CHESTPLATE, true)
                    .addItemTexture(31, Alignment.RIGHT, 0.8F, AerialHellItems.MAGMATIC_GEL_LEGGINGS, true)
                    .addItemTexture(32, Alignment.RIGHT, 0.8F, AerialHellItems.AZURITE_BOOTS, true),
            new Page("journey_4", BOOK_TEXTURE, 4)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_9_make_a_farm")
                    .addSingleIngredientCraftingRecipeDisplay(4, Alignment.LEFT, 0.9F, AerialHellItems.AERIAL_BERRY, () -> new ItemStack(AerialHellItems.AERIAL_BERRY_SEEDS.get(), 1), true)
                    .addOscillatingRecipeDisplay(4, Alignment.RIGHT, 0.9F, AerialHellItems.AERIAL_BERRY, AerialHellItems.VIBRANT_AERIAL_BERRY, true)
                    .addParagraph(9, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "breed_animals")
                    .addTextureDisplay(12, Alignment.LEFT, 0.8F, "gui/guide_book/content/gliding_turtle", 70, 64, "entity.aerialhell.gliding_turtle")
                    .addItemTexture(13, Alignment.CENTER, 1.0F, AerialHellItems.AERIAL_BERRY, true)
                    .addItemTexture(14, Alignment.RIGHT, 1.0F, AerialHellItems.TURTLE_MEAT, true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_10_rush_mud_dungeon")
                    .addTextureDisplay(19, Alignment.LEFT, 0.14F, "gui/guide_book/content/mud_dungeon", 1161, 599)
                    .addItemTexture(27, Alignment.RIGHT, 0.85F, AerialHellItems.OBSIDIAN_SHARD, true)
                    .addParagraph(28, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "get_obsidian_material")
                    .addItemTexture(31, Alignment.LEFT, 0.8F, AerialHellItems.OBSIDIAN_HELMET, true)
                    .addItemTexture(31, Alignment.CENTER, 0.75F, AerialHellItems.NIGHT_VISION_TOTEM, true)
                    .addItemTexture(30, Alignment.RIGHT, 0.8F, AerialHellItems.OBSIDIAN_SWORD, true)
                    .addParagraph(32, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "go_to_dungeon_section"),
            new Page("journey_5", BOOK_TEXTURE, 5)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_11_get_enchanting_table")
                    .addCraftingRecipeDisplay(4, Alignment.CENTER, 1.0F, new CraftingRecipeDisplay.Ingredients(
                            AerialHellItems.OBSIDIAN_SHARD, AerialHellItems.OBSIDIAN_SHARD,
                            AerialHellItems.OBSIDIAN_SHARD, AerialHellItems.OBSIDIAN_SHARD
                    ), Items.OBSIDIAN::getDefaultInstance, true)
                    .addParagraph(9, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "get_leather")
                    .addTextureDisplay(11, Alignment.LEFT, 0.8F, "gui/guide_book/content/evil_cow", 64, 73, "entity.aerialhell.evil_cow")
                    .addItemTexture(12, Alignment.CENTER, 1.0F, AerialHellItems.ROTTEN_LEATHER, true)
                    .addOscillatingRecipeDisplay(12, Alignment.RIGHT, 0.9F, AerialHellItems.ROTTEN_LEATHER, () -> Items.LEATHER, true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "get_paper")
                    .addSingleIngredientCraftingRecipeDisplay(19, Alignment.LEFT, 0.9F, AerialHellItems.SKY_CACTUS, () -> new ItemStack(AerialHellItems.SKY_CACTUS_FIBER.get(), 4), true)
                    .addCraftingTableRecipeDisplay(19, Alignment.RIGHT, 1.0F, new CraftingTableRecipeDisplay.Ingredients(
                            () -> null, () -> null, () -> null,
                            AerialHellItems.SKY_CACTUS_FIBER, AerialHellItems.SKY_CACTUS_FIBER, AerialHellItems.SKY_CACTUS_FIBER,
                            () -> null, () -> null, () -> null
                    ), () -> new ItemStack(Items.PAPER, 3), true)
                    .addCraftingTableRecipeDisplay(24, Alignment.LEFT, 1.0F, new CraftingTableRecipeDisplay.Ingredients(
                            () -> null, () -> null, () -> null,
                            AerialHellItems.STELLAR_SUGAR_CANE, AerialHellItems.STELLAR_SUGAR_CANE, AerialHellItems.STELLAR_SUGAR_CANE,
                            () -> null, () -> null, () -> null
                    ), () -> new ItemStack(Items.PAPER, 3), true)
                    .addTextureDisplay(26, Alignment.RIGHT, 0.8F, "block/stellar_sugar_cane", 16, 16, "block.aerialhell.stellar_sugar_cane")
                    .addParagraph(30, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "improve_enchanting_table")
                    .addTextureDisplay(32, Alignment.LEFT, 0.8F, "block/copper_pine_bookshelf", 16, 16, "block.aerialhell.copper_pine_bookshelf")
                    .addTextureDisplay(32, Alignment.CENTER, 0.8F, "block/aerial_tree_bookshelf", 16, 16, "block.aerialhell.aerial_tree_bookshelf")
                    .addTextureDisplay(32, Alignment.RIGHT, 0.8F, "block/mud_bookshelf", 16, 16, "block.aerialhell.mud_bookshelf"),
            new Page("journey_6", BOOK_TEXTURE, 6)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_12_enchant")
                    .addItemTexture(7, Alignment.LEFT, 1.0F, AerialHellItems.LAPIS_ROBINIA_LOG, true)
                    .addItemTexture(7, Alignment.CENTER, 1.0F, AerialHellItems.LAPIS_ROBINIA_LEAVES, true)
                    .addItemTexture(7, Alignment.RIGHT, 1.0F, () -> Items.LAPIS_LAZULI, true)
                    .addParagraph(10, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_13_ranged_weapon")
                    .addCraftingTableRecipeDisplay(12, Alignment.LEFT, 0.8F, new CraftingTableRecipeDisplay.Ingredients(
                            () -> null, AerialHellItems.SKY_STICK, () -> null,
                            () -> null, AerialHellItems.SKY_STICK, () -> null,
                            () -> null, AerialHellItems.RUBY, () -> null
                    ), () -> AerialHellItems.RUBY_BLOWPIPE.get().getDefaultInstance(), true)
                    .addCraftingTableRecipeDisplay(12, Alignment.RIGHT, 0.8F, new CraftingTableRecipeDisplay.Ingredients(
                            () -> null, AerialHellItems.WHITE_SOLID_ETHER_FRAGMENT, () -> null,
                            () -> null, AerialHellItems.SKY_STICK, () -> null,
                            () -> null, AerialHellItems.RUBY, () -> null
                    ), () -> new ItemStack(AerialHellItems.RUBY_BLOWPIPE_ARROW.get(), 4), true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_14_rush_lunar_temple")
                    .addTextureDisplay(19, Alignment.LEFT, 0.115F, "gui/guide_book/content/lunar_temple", 1199, 896)
                    .addItemTexture(20, Alignment.RIGHT, 0.9F, AerialHellItems.LUNATIC_CRYSTAL, true)
                    .addItemTexture(22, Alignment.RIGHT, 0.85F, AerialHellItems.LUNATIC_AXE, true)
                    .addItemTexture(24, Alignment.RIGHT, 0.85F, AerialHellItems.SWORD_OF_LIGHT, true)
                    .addItemTexture(26, Alignment.RIGHT, 0.85F, AerialHellItems.LUNATIC_CHESTPLATE, true)
                    .addParagraph(30, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "lunar_temple_material"),
            new Page("journey_7", BOOK_TEXTURE, 7)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_15_prepare_for_shadows")
                    .addItemTexture(5, Alignment.LEFT, 0.85F, AerialHellItems.AXE_OF_LIGHT, true)
                    .addItemTexture(5, Alignment.CENTER, 0.85F, AerialHellItems.SWORD_OF_LIGHT, true)
                    .addItemTexture(5, Alignment.RIGHT, 0.85F, AerialHellItems.NIGHT_VISION_TOTEM, true)
                    .addCraftingRecipeDisplay(7, Alignment.CENTER, 0.8F, new CraftingRecipeDisplay.Ingredients(
                            AerialHellItems.DARK_SHADOW_FRUIT, AerialHellItems.PURPLE_SHADOW_FRUIT,
                            AerialHellItems.SHADOW_SPIDER_EYE, AerialHellItems.SKY_BOWL
                    ), () -> AerialHellItems.SHADOW_FRUIT_STEW.get().getDefaultInstance(), true)
                    .addParagraph(11, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "recommended_shadow_items_tips")
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_16_rush_shadow_catacombs")
                    .addTextureDisplay(20, Alignment.LEFT, 0.13F, "gui/guide_book/content/shadow_catacombs", 1124, 583)
                    .addItemTexture(21, Alignment.RIGHT, 0.9F, AerialHellItems.CURSED_CRYSTAL, true)
                    .addItemTexture(23, Alignment.RIGHT, 0.85F, AerialHellItems.CURSED_AXE, true)
                    .addItemTexture(25, Alignment.RIGHT, 0.85F, AerialHellItems.REAPER_SCYTHE, true)
                    .addItemTexture(27, Alignment.RIGHT, 0.85F, AerialHellItems.SHADOW_CHESTPLATE, true)
                    .addParagraph(29, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "shadow_catacombs_material"),
            new Page("journey_8", BOOK_TEXTURE, 8)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_17_get_volucite_equipment")
                    .addItemTexture(4, Alignment.LEFT, 1.0F, AerialHellItems.LUNATIC_PICKAXE, true)
                    .addItemTexture(4, Alignment.CENTER, 1.0F, AerialHellItems.VOLUCITE_ORE, true)
                    .addItemTexture(4, Alignment.RIGHT, 1.0F, AerialHellItems.RAW_VOLUCITE, true)
                    .addOscillatingRecipeDisplay(6, Alignment.CENTER, 1.0F, AerialHellItems.RAW_VOLUCITE, AerialHellItems.VOLUCITE_VIBRANT, true)
                    .addParagraph(12, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "volucite_equipment_info")
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_18_rush_golden_nether_prison")
                    .addTextureDisplay(19, Alignment.CENTER, 0.15F, "gui/guide_book/content/golden_nether_prison", 807, 693)
                    .addItemTexture(21, Alignment.LEFT, 0.9F, AerialHellItems.ARSONIST_INGOT, true)
                    .addItemTexture(23, Alignment.LEFT, 0.85F, AerialHellItems.ARSONIST_UPGRADE_SMITHING_TEMPLATE, true)
                    .addItemTexture(25, Alignment.LEFT, 0.85F, AerialHellItems.ARSONIST_AXE, true)
                    .addItemTexture(27, Alignment.LEFT, 0.85F, AerialHellItems.ARSONIST_CHESTPLATE, true)
                    .addItemTexture(21, Alignment.RIGHT, 0.9F, () -> Items.NETHERITE_INGOT, true)
                    .addItemTexture(23, Alignment.RIGHT, 0.85F, AerialHellItems.BERSERK_AXE, true)
                    .addItemTexture(25, Alignment.RIGHT, 0.85F, AerialHellItems.NETHERIAN_KING_SWORD, true)
                    .addItemTexture(27, Alignment.RIGHT, 0.85F, AerialHellItems.GODS_VOLUCITE_AERIAL_BERRY, true)
                    .addParagraph(29, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "golden_nether_prison_material"),
            new Page("crafting_1", BOOK_TEXTURE, 9)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "crafting_section_title")
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "crafting_section_quote")
                    .addParagraph(9, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "crafting_content_desc")
                    .addItemTexture(14, Alignment.LEFT, 1.0F, AerialHellItems.STELLAR_FURNACE, true)
                    .addItemTexture(14, Alignment.CENTER, 1.0F, AerialHellItems.OSCILLATOR, true)
                    .addItemTexture(14, Alignment.RIGHT, 1.0F, AerialHellItems.FREEZER, true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "type_1_smelting_recipe")
                    .addCraftingTableRecipeDisplay(21, Alignment.CENTER, 1.0F, new CraftingTableRecipeDisplay.Ingredients(
                            AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE,
                            AerialHellItems.STELLAR_COBBLESTONE, () -> null, AerialHellItems.STELLAR_COBBLESTONE,
                            AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE
                    ), () -> AerialHellItems.STELLAR_FURNACE.get().getDefaultInstance(), true)
                    .addParagraph(27, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.5F), (int)(LINE_WIDTH_NO_MARGIN * 0.5F), Alignment.LEFT, "get_smelting_fuel")
                    .addSmeltingRecipeDisplay(28, Alignment.LEFT, 0.9F, AerialHellItems.AERIAL_TREE_LOG, () -> Items.CHARCOAL, AerialHellItems.AERIAL_TREE_PLANKS, true),
            new Page("crafting_2", BOOK_TEXTURE, 10)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "smelting_food")
                    .addSmeltingRecipeDisplay(3, Alignment.LEFT, 0.9F, AerialHellItems.AERIAL_BERRY, AerialHellItems.ROASTED_AERIAL_BERRY, true)
                    .addSmeltingRecipeDisplay(3, Alignment.RIGHT, 0.9F, AerialHellItems.PHANTOM_MEAT, AerialHellItems.COOKED_PHANTOM_MEAT, true)
                    .addSmeltingRecipeDisplay(9, Alignment.LEFT, 0.9F, AerialHellItems.TURTLE_MEAT, AerialHellItems.COOKED_TURTLE_MEAT, true)
                    .addSmeltingRecipeDisplay(9, Alignment.RIGHT, 0.9F, () -> Items.CHICKEN, () -> Items.COOKED_CHICKEN, true)
                    .addParagraph(14, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "better_than_smelting")
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "smelting_ores")
                    .addSmeltingRecipeDisplay(20, Alignment.LEFT, 0.95F, AerialHellItems.RAW_RUBY, AerialHellItems.OVERHEATED_RUBY, true)
                    .addSmeltingRecipeDisplay(20, Alignment.RIGHT, 0.95F, AerialHellItems.RAW_VOLUCITE, AerialHellItems.OVERHEATED_VOLUCITE, true)
                    .addParagraph(26, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "transition_to_oscillating"),
            new Page("crafting_3", BOOK_TEXTURE, 11)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "type_2_oscillating_recipe")
                    .addCraftingTableRecipeDisplay(5, Alignment.CENTER, 1.0F, new CraftingTableRecipeDisplay.Ingredients(
                            AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE,
                            AerialHellItems.STELLAR_STONE, AerialHellItems.FLUORITE, AerialHellItems.STELLAR_STONE,
                            AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE
                    ), () -> AerialHellItems.OSCILLATOR.get().getDefaultInstance(), true)
                    .addParagraph(11, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "oscillating_fuel")
                    .addItemTexture(14, Alignment.LEFT, 1.0F, AerialHellItems.FLUORITE_BLOCK, true)
                    .addItemTexture(14, Alignment.CENTER, 1.0F, AerialHellItems.CRYSTAL, true)
                    .addItemTexture(14, Alignment.RIGHT, 1.0F, AerialHellItems.CRYSTAL_BLOCK, true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "oscillating_ores")
                    .addOscillatingRecipeDisplay(20, Alignment.LEFT, 0.9F, AerialHellItems.STELLAR_STONE_CRYSTAL, AerialHellItems.CRYSTAL, true)
                    .addOscillatingRecipeDisplay(20, Alignment.RIGHT, 0.9F, AerialHellItems.RAW_RUBY, AerialHellItems.RUBY, true)
                    .addOscillatingRecipeDisplay(25, Alignment.LEFT, 0.9F, AerialHellItems.RAW_AZURITE, AerialHellItems.AZURITE_CRYSTAL, true)
                    .addOscillatingRecipeDisplay(25, Alignment.RIGHT, 0.9F, AerialHellItems.RAW_VOLUCITE, AerialHellItems.VOLUCITE_VIBRANT, true)
                    .addParagraph(31, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "oscillating_overheated_ores")
                    .addOscillatingRecipeDisplay(30, Alignment.RIGHT, 0.9F, AerialHellItems.OVERHEATED_RUBY, AerialHellItems.RUBY, true),
            new Page("crafting_4", BOOK_TEXTURE, 12)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "oscillating_food")
                    .addOscillatingRecipeDisplay(3, Alignment.LEFT, 0.9F, AerialHellItems.AERIAL_BERRY, AerialHellItems.VIBRANT_AERIAL_BERRY, true)
                    .addOscillatingRecipeDisplay(3, Alignment.RIGHT, 0.9F, AerialHellItems.GLOWING_STICK_FRUIT, AerialHellItems.VIBRANT_GLOWING_STICK_FRUIT, true)
                    .addOscillatingRecipeDisplay(9, Alignment.LEFT, 0.9F, AerialHellItems.SOLID_ETHER_SOUP, AerialHellItems.VIBRANT_SOLID_ETHER_SOUP, true)
                    .addOscillatingRecipeDisplay(9, Alignment.RIGHT, 0.9F, () -> Items.CHICKEN, AerialHellItems.VIBRANT_CHICKEN, true)
                    .addParagraph(14, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "other_than_oscillating")
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "type_3_freezing_recipe")
                    .addCraftingTableRecipeDisplay(20, Alignment.CENTER, 1.0F, new CraftingTableRecipeDisplay.Ingredients(
                            AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE,
                            AerialHellItems.STELLAR_STONE, AerialHellItems.MAGMATIC_GEL, AerialHellItems.STELLAR_STONE,
                            AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE
                    ), () -> AerialHellItems.FREEZER.get().getDefaultInstance(), true)
                    .addItemTexture(26, Alignment.LEFT, 1.0F, AerialHellItems.MAGMATIC_GEL_BOOTS, true)
                    .addItemTexture(26, Alignment.CENTER, 1.0F, AerialHellItems.MAGMATIC_GEL, true)
                    .addItemTexture(26, Alignment.RIGHT, 1.0F, AerialHellItems.MAGMATIC_GEL_BLOCK, true)
                    .addParagraph(28, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "freezing_description")
                    .addFreezingRecipeDisplay(30, Alignment.LEFT, 0.87F, AerialHellItems.AZURITE_CRYSTAL, () -> Items.QUARTZ, true)
                    .addFreezingRecipeDisplay(30, Alignment.RIGHT, 0.87F, () -> Items.ICE, () -> Items.PACKED_ICE, true),
            new Page("crafting_5", BOOK_TEXTURE, 13)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "freezing_food")
                    .addFreezingRecipeDisplay(3, Alignment.LEFT, 0.9F, AerialHellItems.AERIAL_BERRY, AerialHellItems.FROZEN_AERIAL_BERRY, true)
                    .addFreezingRecipeDisplay(3, Alignment.RIGHT, 0.9F, AerialHellItems.TURTLE_MEAT, AerialHellItems.FROZEN_TURTLE_MEAT, true)
                    .addFreezingRecipeDisplay(9, Alignment.LEFT, 0.9F, AerialHellItems.WHITE_SOLID_ETHER_FRAGMENT, AerialHellItems.BLUE_SOLID_ETHER_FRAGMENT, true)
                    .addFreezingRecipeDisplay(9, Alignment.RIGHT, 0.9F, () -> Items.MUTTON, AerialHellItems.FROZEN_MUTTON, true)
                    .addParagraph(14, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "frozen_food_particularity")
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "brewing_recipes")
                    .addBrewingRecipeDisplay(21, Alignment.CENTER, 1.0F, () -> ItemHelper.createPotionItemStack(Potions.AWKWARD), AerialHellItems.SHADOW_SPIDER_EYE, () -> ItemHelper.createPotionItemStack(Potions.POISON), true)
                    .addBrewingRecipeDisplay(28, Alignment.CENTER, 1.0F, () -> ItemHelper.createPotionItemStack(Potions.AWKWARD), AerialHellItems.VENOMOUS_SNAKE_SKIN, () -> ItemHelper.createPotionItemStack(Potions.HEALING), true),
            new Page("armors_4", BOOK_TEXTURE, 14)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFFFF0000, "content_1"),
            new Page("tools_1", BOOK_TEXTURE, 15)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("tools_2", BOOK_TEXTURE, 16)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("tools_3", BOOK_TEXTURE, 17)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("tools_4", BOOK_TEXTURE, 18)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("tools_5", BOOK_TEXTURE, 19)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("utilities_1", BOOK_TEXTURE, 20)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("utilities_2", BOOK_TEXTURE, 21)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(5, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("utilities_3", BOOK_TEXTURE, 22)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("utilities_4", BOOK_TEXTURE, 23)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("utilities_5", BOOK_TEXTURE, 24)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1")
    );

    //The guide book is designed to contain 6 tabs on each side.
    //Each tab is 24 pixels high.
    //Tabs are separated by an 8-pixel gap.
    //The first and last tabs are separated from the top/bottom edges by a 4-pixel margin.
    //Layout:
    //   book top
    //      4 px margin
    //      24 px tab
    //      8 px gap
    //      ...
    //      4 px margin
    //   book bottom
    private static class TabList
    {
        private final List<Tab> tabs;
        private final boolean isLeft;
        private final Supplier<Integer> bookLeft;
        private final Supplier<Integer> bookTop;
        private int nextTabYOffsetFromBookTop;

        private TabList(boolean isLeft, Supplier<Integer> bookLeft, Supplier<Integer> bookTop)
        {
            this.tabs = new ArrayList<>();
            this.bookLeft = bookLeft;
            this.bookTop = bookTop;
            this.isLeft = isLeft;
            this.nextTabYOffsetFromBookTop = TAB_MARGIN;
        }

        public List<Tab> getTabs() {return this.tabs;}

        private TabList add(String name, int pageIndex)
        {
            int currentTabYOffset = this.nextTabYOffsetFromBookTop;
            this.tabs.add(new Tab(
                    name,
                    (isHovered) -> isHovered ? TAB_WIDTH + HOVERED_TAB_EXTRA_WIDTH : TAB_WIDTH,
                    (isHovered) -> TAB_HEIGHT,
                    bookLeft,
                    bookTop,
                    this.isLeft ? (isHovered) -> - TAB_WIDTH - (isHovered ? HOVERED_TAB_EXTRA_WIDTH : 0) : (isHovered) -> BOOK_TEXTURE_WIDTH,
                    (isHovered) -> currentTabYOffset,
                    this.isLeft ? (isHovered) -> 0.0F : (isHovered) -> isHovered ? 0.0F : 4.0F, //blitU : left tab is offset by default (due to relativeXPos moving). right tab : offset when not hovered, to give the impression that we are "pulling the tab" when hovered, like left one
                    (isHovered) -> 0.0F, //blitY : always 0.0F, no matter if isHovered
                    pageIndex
            ));
            this.nextTabYOffsetFromBookTop += TAB_HEIGHT + TAB_GAP;
            return this;
        }
    }

    private TabList leftTabs, rightTabs;

    //timer for navigation button display
    private boolean homeButtonVisible;
    private float homeButtonAlpha;
    private boolean navigationArrowsVisible;
    private float navigationArrowsAlpha;
    private int prevMouseX, prevMouseY;

    //book position
    private int bookLeft, bookRight, bookTop, bookBottom, rightPageLeft;
    //navigation arrows position
    private int navigationButtonTop;
    private int navigationButtonBottom;
    private int leftNavigationArrowLeft;
    private int leftNavigationArrowRight;
    private int rightNavigationArrowLeft;
    private int rightNavigationArrowRight;
    private int homePageNavigationButtonLeft;
    private int homePageNavigationButtonRight;

    //page
    private int firstLineY;
    private int leftPageLineX, rightPageLineX;
    private int leftPageCenterX, rightPageCenterX;
    private final List<Line> lines = new ArrayList<>();

    //state
    private static final int PAGE_SUMMARY_INDEX = 0;
    private int currentPage = PAGE_SUMMARY_INDEX;

    public GuideBookScreen() {super(Component.empty());}

    @Override protected void init()
    {
        super.init();
        this.homeButtonVisible = false; this.navigationArrowsVisible = false;
        this.homeButtonAlpha = 0.0F; this.navigationArrowsAlpha = 0.0F;
        this.prevMouseX = 0; this.prevMouseY = 0;
        this.createTabs();

        this.textScale = Minecraft.getInstance().options.forceUnicodeFont().get() ? 1.0F : 0.8F;

        this.bookLeft = (this.width - BOOK_TEXTURE_WIDTH) / 2;
        this.bookTop  = (this.height - BOOK_TEXTURE_HEIGHT) / 2;
        this.bookRight = this.bookLeft + BOOK_TEXTURE_WIDTH;
        this.bookBottom = this.bookTop + BOOK_TEXTURE_HEIGHT;
        this.navigationButtonBottom = this.bookBottom + 18;
        this.navigationButtonTop = this.navigationButtonBottom - NAVIGATION_BUTTON_SIZE;
        this.leftNavigationArrowLeft = this.bookLeft + 5;
        this.leftNavigationArrowRight = this.leftNavigationArrowLeft + NAVIGATION_BUTTON_SIZE;
        this.rightNavigationArrowRight = this.bookRight - 5;
        this.rightNavigationArrowLeft = this.rightNavigationArrowRight - NAVIGATION_BUTTON_SIZE;
        this.homePageNavigationButtonLeft = this.bookLeft + (BOOK_TEXTURE_WIDTH / 2) - (NAVIGATION_BUTTON_SIZE / 2);
        this.homePageNavigationButtonRight = this.homePageNavigationButtonLeft + NAVIGATION_BUTTON_SIZE;

        this.rightPageLeft = this.bookLeft + 194;
        this.firstLineY = this.bookTop + 9;
        this.leftPageLineX = this.bookLeft + MARGIN_WIDTH;
        this.rightPageLineX = this.rightPageLeft + MARGIN_WIDTH;
        this.leftPageCenterX = this.leftPageLineX + LINE_WIDTH_NO_MARGIN / 2;
        this.rightPageCenterX = this.rightPageLineX + LINE_WIDTH_NO_MARGIN / 2;

        for (int lineIndex = 0; lineIndex < MAX_LINES_PER_TECHNICAL_PAGE; lineIndex++)
        {
            boolean isLeftPageLine = lineIndex < MAX_LINES_PER_VISUAL_PAGE;
            this.lines.add(new Line(lineIndex, isLeftPageLine ? this.leftPageLineX : this.rightPageLineX, isLeftPageLine ? this.leftPageCenterX : this.rightPageCenterX, isLeftPageLine ? this.leftPageLineX + LINE_WIDTH_NO_MARGIN : this.rightPageLineX + LINE_WIDTH_NO_MARGIN, this.firstLineY + (lineIndex % MAX_LINES_PER_VISUAL_PAGE) * LINE_HEIGHT));
        }

        this.playOpenSound();
    }

    @Override public void onClose()
    {
        this.playCloseSound();
        super.onClose();
    }

    protected void createTabs()
    {
        this.leftTabs = new TabList(true, () -> this.bookLeft, () -> this.bookTop)
                .add("journey", 1)
                .add("crafting", 9)
                .add("materials", 6)
                .add("effects", 6)
                .add("enchanting", 6)
                .add("environment", 6);

        this.rightTabs = new TabList(false, () -> this.bookLeft, () -> this.bookTop)
                .add("bestiary", 11)
                .add("bosses", 15)
                .add("structures", 20)
                .add("dungeons", 20)
                .add("shadow_and_light", 20)
                .add("items", 20);
    }

    @Override public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick)
    {
        //navigation arrows
        if (this.isHoveringPrevArrow(event.x(), event.y()))
        {
            this.navigateToPreviousPage();
            return true;
        }

        if (this.isHoveringNextArrow(event.x(), event.y()))
        {
            this.navigateToNextPage();
            return true;
        }

        if (this.isHoveringHomeButton(event.x(), event.y()))
        {
            this.navigateToPage(ALL_PAGES.getFirst());
            return true;
        }

        //tabs
        for (Tab tab : leftTabs.getTabs())
        {
            if (tab.isHovered(event.x(), event.y()))
            {
                this.navigateToTab(tab);
                return true;
            }
        }
        for (Tab tab : rightTabs.getTabs())
        {
            if (tab.isHovered(event.x(), event.y()))
            {
                this.navigateToTab(tab);
                return true;
            }
        }
        return super.mouseClicked(event, doubleClick);
    }

    private boolean isHoveringPrevArrow(double mouseX, double mouseY)
    {
        return mouseX >= this.leftNavigationArrowLeft && mouseX <= this.leftNavigationArrowRight  && mouseY >= this.navigationButtonTop && mouseY <= this.navigationButtonBottom;
    }

    private boolean isHoveringNextArrow(double mouseX, double mouseY)
    {
        return mouseX >= this.rightNavigationArrowLeft && mouseX <= this.rightNavigationArrowRight && mouseY >= this.navigationButtonTop && mouseY <= this.navigationButtonBottom;
    }

    private boolean isHoveringHomeButton(double mouseX, double mouseY)
    {
        return mouseX >= this.homePageNavigationButtonLeft && mouseX <= this.homePageNavigationButtonRight && mouseY >= this.navigationButtonTop && mouseY <= this.navigationButtonBottom;
    }

    @Override public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick)
    {
        for (Tab tab : leftTabs.getTabs()) {tab.render(graphics, this.font, mouseX, mouseY);}
        for (Tab tab : rightTabs.getTabs()) {tab.render(graphics, this.font, mouseX, mouseY);}

        this.renderPageContent(graphics, mouseX, mouseY);
        this.renderNavigationButtons(graphics, mouseX, mouseY);

        super.extractBackground(graphics, mouseX, mouseY, partialTick);
    }

    private void renderPageContent(GuiGraphicsExtractor graphics, int mouseX, int mouseY)
    {
        Page currentPage = null;
        for (Page page : ALL_PAGES) {if (page.pageIndex() == this.currentPage) currentPage = page;}
        if (currentPage == null) {return;}

        currentPage.render(this.font, graphics, this.textScale, lines, this.bookLeft, this.bookTop, mouseX, mouseY);
    }

    private void renderNavigationButtons(GuiGraphicsExtractor graphics, int mouseX, int mouseY)
    {
        this.tickHomeButton(mouseX, mouseY);
        this.tickNavigationArrows(mouseX, mouseY);

        //previous page arrow
        if (this.currentPage != 0)
        {
            TextureInfo previousArrowTexture = this.isHoveringPrevArrow(mouseX, mouseY) ? NAVIGATION_ARROW_PREVIOUS_PAGE_HOVERED : NAVIGATION_ARROW_PREVIOUS_PAGE;
            graphics.blit(RenderPipelines.GUI_TEXTURED, previousArrowTexture.texture(), this.leftNavigationArrowLeft, this.navigationButtonTop, previousArrowTexture.u(), previousArrowTexture.v(), previousArrowTexture.width(), previousArrowTexture.height(), previousArrowTexture.width(), previousArrowTexture.height(), previousArrowTexture.textureWidth(), previousArrowTexture.textureHeight(), ARGB.white(this.getNavigationArrowsAlpha()));

            TextureInfo homePageButtonTexture = this.isHoveringHomeButton(mouseX, mouseY) ? NAVIGATION_BUTTON_HOME_PAGE_HOVERED : NAVIGATION_BUTTON_HOME_PAGE;
            graphics.blit(RenderPipelines.GUI_TEXTURED, homePageButtonTexture.texture(), this.homePageNavigationButtonLeft, this.navigationButtonTop, homePageButtonTexture.u(), homePageButtonTexture.v(), homePageButtonTexture.width(), homePageButtonTexture.height(), homePageButtonTexture.width(), homePageButtonTexture.height(), homePageButtonTexture.textureWidth(), homePageButtonTexture.textureHeight(), ARGB.white(this.getHomeButtonAlpha()));
        }

        //next page arrow
        if (this.currentPage != ALL_PAGES.size() - 1)
        {
            TextureInfo nextArrowTexture = this.isHoveringNextArrow(mouseX, mouseY) ? NAVIGATION_ARROW_NEXT_PAGE_HOVERED : NAVIGATION_ARROW_NEXT_PAGE;
            graphics.blit(RenderPipelines.GUI_TEXTURED, nextArrowTexture.texture(), this.rightNavigationArrowLeft, this.navigationButtonTop, nextArrowTexture.u(), nextArrowTexture.v(), nextArrowTexture.width(), nextArrowTexture.height(), nextArrowTexture.width(), nextArrowTexture.height(), nextArrowTexture.textureWidth(), nextArrowTexture.textureHeight(), ARGB.white(this.getNavigationArrowsAlpha()));
        }
    }

    private void navigateToTab(Tab tab)
    {
        this.currentPage = tab.pageIndex();
        this.playPageTurnSound();
    }

    private void navigateToPage(Page page)
    {
        this.currentPage = page.pageIndex();
        this.playPageTurnSound();
    }

    private void navigateToPreviousPage()
    {
        int nextIndex = getCurrentIndex() - 1;
        if (nextIndex >= 0 && nextIndex < ALL_PAGES.size()) {this.navigateToPage(ALL_PAGES.get(nextIndex));}
    }

    private void navigateToNextPage()
    {
        int nextIndex = getCurrentIndex() + 1;
        if (nextIndex >= 0 && nextIndex < ALL_PAGES.size()) {this.navigateToPage(ALL_PAGES.get(nextIndex));}
    }

    private int getCurrentIndex()
    {
        for (int i = 0; i < ALL_PAGES.size(); i++)
        {
            if (ALL_PAGES.get(i).pageIndex() == this.currentPage) {return i;}
        }
        return -1;
    }

    private float getHomeButtonAlpha()
    {
        return Math.min(this.homeButtonAlpha, 1.0F);
    }

    private float getNavigationArrowsAlpha()
    {
        return Math.min(this.navigationArrowsAlpha, 1.0F);
    }

    private void tickHomeButton(int mouseX, int mouseY)
    {
        this.homeButtonVisible = this.isHoveringPrevArrow(mouseX, mouseY) || this.isHoveringHomeButton(mouseX, mouseY) || this.isHoveringNextArrow(mouseX, mouseY); //isHoveringAnyButton
        float speed = this.homeButtonVisible && this.homeButtonAlpha > 1.0F ? 1.0F : 0.1F;
        float target = this.homeButtonVisible ? 10.0F : 0.0F;

        if (this.homeButtonAlpha < target)
        {
            this.homeButtonAlpha = Math.min(target, this.homeButtonAlpha + speed);
        }
        else if (this.homeButtonAlpha > target)
        {
            this.homeButtonAlpha = Math.max(target, this.homeButtonAlpha - speed);
        }
    }

    private void tickNavigationArrows(int mouseX, int mouseY)
    {
        this.navigationArrowsVisible = mouseX != this.prevMouseX || mouseY != this.prevMouseY || this.isHoveringPrevArrow(mouseX, mouseY) || this.isHoveringNextArrow(mouseX, mouseY);
        this.prevMouseX = mouseX; this.prevMouseY = mouseY;

        float speed = this.navigationArrowsVisible ? (this.navigationArrowsAlpha > 1.0F ? 1.0F : 0.1F) : 0.05F;
        float target = this.navigationArrowsVisible ? 10.0F : 0.0F;

        if (this.navigationArrowsAlpha < target)
        {
            this.navigationArrowsAlpha = Math.min(target, this.navigationArrowsAlpha + speed);
        }
        else if (this.navigationArrowsAlpha > target)
        {
            this.navigationArrowsAlpha = Math.max(target, this.navigationArrowsAlpha - speed);
        }
    }

    @Override public boolean isPauseScreen() {return false;}

    private void playOpenSound()
    {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.CHISELED_BOOKSHELF_INSERT, 1.0F));
    }

    private void playCloseSound()
    {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.CHISELED_BOOKSHELF_PICKUP, 1.0F));
    }

    private void playPageTurnSound()
    {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));
    }
}