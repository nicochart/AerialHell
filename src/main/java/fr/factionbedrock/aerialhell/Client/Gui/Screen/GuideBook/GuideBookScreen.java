package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.*;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.RecipeDisplay.CraftingRecipeDisplay;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.RecipeDisplay.CraftingTableRecipeDisplay;
import fr.factionbedrock.aerialhell.Client.Util.TextureInfo;
import fr.factionbedrock.aerialhell.Registry.AerialHellEnchantments;
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

    private static final TextureInfo PAGE_TEXTURE = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/page.png"), BOOK_TEXTURE_WIDTH, BOOK_TEXTURE_HEIGHT);
    private static final TextureInfo VERTICAL_SEPARATOR_8 = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/vertical_separator_8.png"), 10, 82);
    private static final TextureInfo VERTICAL_SEPARATOR_9 = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/vertical_separator_9.png"), 10, 92);
    private static final TextureInfo VERTICAL_SEPARATOR_16 = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/vertical_separator_16.png"), 8, 168);
    private static final TextureInfo HORIZONTAL_SEPARATOR = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/horizontal_separator.png"), 166, 6);
    private static final TextureInfo CROSSING_POINT = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/crossing_point.png"), 22, 22);

    private static final TextureInfo NAVIGATION_ARROW_PREVIOUS_PAGE = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_arrow_previous_page.png"), NAVIGATION_BUTTON_SIZE, NAVIGATION_BUTTON_SIZE);
    private static final TextureInfo NAVIGATION_ARROW_PREVIOUS_PAGE_HOVERED = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_arrow_previous_page_hovered.png"), NAVIGATION_BUTTON_SIZE, NAVIGATION_BUTTON_SIZE);
    private static final TextureInfo NAVIGATION_ARROW_NEXT_PAGE = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_arrow_next_page.png"), NAVIGATION_BUTTON_SIZE, NAVIGATION_BUTTON_SIZE);
    private static final TextureInfo NAVIGATION_ARROW_NEXT_PAGE_HOVERED = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_arrow_next_page_hovered.png"), NAVIGATION_BUTTON_SIZE, NAVIGATION_BUTTON_SIZE);
    private static final TextureInfo NAVIGATION_BUTTON_HOME_PAGE = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_button_home_page.png"), NAVIGATION_BUTTON_SIZE, NAVIGATION_BUTTON_SIZE);
    private static final TextureInfo NAVIGATION_BUTTON_HOME_PAGE_HOVERED = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_button_home_page_hovered.png"), NAVIGATION_BUTTON_SIZE, NAVIGATION_BUTTON_SIZE);

    private float textScale;

    private static final List<Page> ALL_PAGES = List.of(
            new Page("summary", PAGE_TEXTURE, 0)
                    .addTextureDisplay(centered(1).build(), 0.5F, "gui/guide_book/content/aerial_hell_logo", 300, 49)
                    .addParagraph(5, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(7, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "welcome_text")
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "table_of_content_title")
                    .addParagraph(20, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "table_of_content")
                    .addParagraph(20, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.RIGHT, "table_of_content_pages"),
            new Page("journey_1", PAGE_TEXTURE, 1)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "journey_section_title")
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "journey_section_quote")
                    .addParagraph(7, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "journey_content_desc")
                    .addItemTexture(centered(14).build(), 1.2F, AerialHellItems.STELLAR_GRASS_BLOCK, true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_1_enter_dimension")
                    .addTextureDisplay(centered(19).build(), 0.8F, "gui/guide_book/content/stellar_portal", 64, 80, "block.aerialhell.aerial_hell_portal")
                    .addParagraph(26, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "light_the_portal")
                    .addItemTexture(alignedToRight(31).build(), 1.0F, AerialHellItems.STELLAR_LIGHTER, true),
            new Page("journey_2", PAGE_TEXTURE, 2)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_2_collect_resources")
                    .addItemTexture(alignedToLeft(5).build(), 1.0F, AerialHellItems.SKY_WOOD_PICKAXE, true)
                    .addItemTexture(centered(5).build(), 1.0F, AerialHellItems.STELLAR_STONE_PICKAXE, true)
                    .addItemTexture(alignedToRight(5).build(), 1.0F, AerialHellItems.STELLAR_STONE_AXE, true)
                    .addParagraph(8, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_3_collect_food")
                    .addItemTexture(alignedToLeft(9).build(), 1.0F, AerialHellItems.STELLAR_WHEAT, true)
                    .addItemTexture(alignedToLeft(9).horizontalOffset(LINE_WIDTH_NO_MARGIN / 4 - 8).build(), 1.0F, AerialHellItems.GLOWING_STICK_FRUIT, true)
                    .addItemTexture(centered(9).build(), 1.0F, AerialHellItems.AERIAL_BERRY, true)
                    .addItemTexture(alignedToRight(9).horizontalOffset(-LINE_WIDTH_NO_MARGIN / 4 + 8).build(), 1.0F, AerialHellItems.SKY_CACTUS_FIBER, true)
                    .addItemTexture(alignedToRight(9).build(), 1.0F, AerialHellItems.WHITE_SOLID_ETHER_FRAGMENT, true)
                    .addParagraph(12, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_4_collect_fluorite")
                    .addItemTexture(alignedToLeft(15).build(), 1.0F, AerialHellItems.FLUORITE_ORE, true)
                    .addItemTexture(centered(15).build(), 1.0F, AerialHellItems.FLUORITE, true)
                    .addItemTexture(alignedToRight(15).build(), 1.0F, AerialHellItems.FLUORITE_TORCH, true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_5_craft_stellar_furnace")
                    .addCraftingTableRecipeDisplay(19, Alignment.CENTER, 1.0F, new CraftingTableRecipeDisplay.Ingredients(
                            AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE,
                            AerialHellItems.STELLAR_COBBLESTONE, () -> null, AerialHellItems.STELLAR_COBBLESTONE,
                            AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE
                    ), () -> AerialHellItems.STELLAR_FURNACE.get().getDefaultInstance(), true)
                    .addParagraph(25, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "cook_food")
                    .addSmeltingRecipeDisplay(26, Alignment.CENTER, 0.9F, AerialHellItems.AERIAL_BERRY, AerialHellItems.ROASTED_AERIAL_BERRY, true)
                    .addParagraph(31, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_6_mine_more")
                    .addItemTexture(alignedToLeft(32).build(), 1.0F, AerialHellItems.RUBY_ORE, true)
                    .addItemTexture(centered(32).build(), 1.0F, AerialHellItems.AZURITE_ORE, true)
                    .addItemTexture(alignedToRight(32).build(), 1.0F, AerialHellItems.MAGMATIC_GEL_ORE, true),
            new Page("journey_3", PAGE_TEXTURE, 3)
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
                    .addItemTexture(alignedToLeft(29).build(), 0.8F, AerialHellItems.RUBY_HELMET, true)
                    .addItemTexture(alignedToLeft(30).horizontalOffset(8).build(), 0.8F, AerialHellItems.MAGMATIC_GEL_CHESTPLATE, true)
                    .addItemTexture(alignedToLeft(31).horizontalOffset(16).build(), 0.8F, AerialHellItems.RUBY_LEGGINGS, true)
                    .addItemTexture(alignedToLeft(32).horizontalOffset(24).build(), 0.8F, AerialHellItems.RUBY_BOOTS, true)
                    .addItemTexture(centered(29).horizontalOffset(-10).build(), 0.85F, AerialHellItems.RUBY_PICKAXE, true)
                    .addItemTexture(centered(30).horizontalOffset(10).build(), 0.85F, AerialHellItems.AZURITE_AXE, true)
                    .addItemTexture(centered(31).horizontalOffset(-12).build(), 0.85F, AerialHellItems.RUBY_SWORD, true)
                    .addItemTexture(centered(32).horizontalOffset(12).build(), 0.85F, AerialHellItems.MAGMATIC_GEL_SWORD, true)
                    .addItemTexture(alignedToRight(29).build(), 0.8F, AerialHellItems.AZURITE_HELMET, true)
                    .addItemTexture(alignedToRight(30).horizontalOffset(-8).build(), 0.8F, AerialHellItems.AZURITE_CHESTPLATE, true)
                    .addItemTexture(alignedToRight(31).horizontalOffset(-16).build(), 0.8F, AerialHellItems.MAGMATIC_GEL_LEGGINGS, true)
                    .addItemTexture(alignedToRight(32).horizontalOffset(-24).build(), 0.8F, AerialHellItems.AZURITE_BOOTS, true),
            new Page("journey_4", PAGE_TEXTURE, 4)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_9_make_a_farm")
                    .addSingleIngredientCraftingRecipeDisplay(4, Alignment.LEFT, 0.9F, AerialHellItems.AERIAL_BERRY, () -> new ItemStack(AerialHellItems.AERIAL_BERRY_SEEDS.get(), 1), true)
                    .addOscillatingRecipeDisplay(4, Alignment.RIGHT, 0.9F, AerialHellItems.AERIAL_BERRY, AerialHellItems.VIBRANT_AERIAL_BERRY, true)
                    .addParagraph(9, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "breed_animals")
                    .addTextureDisplay(alignedToLeft(12).build(), 1.6F, "gui/guide_book/content/entities/gliding_turtle", 32, 28, "entity.aerialhell.gliding_turtle")
                    .addItemTexture(centered(13).horizontalOffset(10).build(), 1.0F, AerialHellItems.AERIAL_BERRY, true)
                    .addItemTexture(alignedToRight(14).build(), 1.0F, AerialHellItems.TURTLE_MEAT, true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_10_rush_mud_dungeon")
                    .addTextureDisplay(alignedToLeft(19).build(), 0.14F, "gui/guide_book/content/mud_dungeon", 1161, 599)
                    .addItemTexture(alignedToRight(27).build(), 0.85F, AerialHellItems.OBSIDIAN_SHARD, true)
                    .addParagraph(28, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "get_obsidian_material")
                    .addItemTexture(alignedToLeft(31).centerVerticallyOnLine().build(),0.8F, AerialHellItems.OBSIDIAN_HELMET, true)
                    .addItemTexture(centered(31).centerVerticallyOnLine().build(), 0.75F, AerialHellItems.NIGHT_VISION_TOTEM, true)
                    .addItemTexture(alignedToRight(30).build(), 0.8F, AerialHellItems.OBSIDIAN_SWORD, true)
                    .addParagraph(32, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "go_to_dungeon_section"),
            new Page("journey_5", PAGE_TEXTURE, 5)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_11_get_enchanting_table")
                    .addCraftingRecipeDisplay(4, Alignment.CENTER, 1.0F, new CraftingRecipeDisplay.Ingredients(
                            AerialHellItems.OBSIDIAN_SHARD, AerialHellItems.OBSIDIAN_SHARD,
                            AerialHellItems.OBSIDIAN_SHARD, AerialHellItems.OBSIDIAN_SHARD
                    ), Items.OBSIDIAN::getDefaultInstance, true)
                    .addParagraph(9, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "get_leather")
                    .addTextureDisplay(alignedToLeft(11).build(), 1.6F, "gui/guide_book/content/entities/evil_cow", 30, 30, "entity.aerialhell.evil_cow")
                    .addItemTexture(centered(12).build(), 1.0F, AerialHellItems.ROTTEN_LEATHER, true)
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
                    .addTextureDisplay(alignedToRight(26).build(), 0.8F, "block/stellar_sugar_cane", 16, 16, "block.aerialhell.stellar_sugar_cane")
                    .addParagraph(30, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "improve_enchanting_table")
                    .addTextureDisplay(alignedToLeft(32).build(), 0.8F, "block/copper_pine_bookshelf", 16, 16, "block.aerialhell.copper_pine_bookshelf")
                    .addTextureDisplay(centered(32).build(), 0.8F, "block/aerial_tree_bookshelf", 16, 16, "block.aerialhell.aerial_tree_bookshelf")
                    .addTextureDisplay(alignedToRight(32).build(), 0.8F, "block/mud_bookshelf", 16, 16, "block.aerialhell.mud_bookshelf"),
            new Page("journey_6", PAGE_TEXTURE, 6)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_12_enchant")
                    .addItemTexture(alignedToLeft(7).build(), 1.0F, AerialHellItems.LAPIS_ROBINIA_LOG, true)
                    .addItemTexture(centered(7).build(), 1.0F, AerialHellItems.LAPIS_ROBINIA_LEAVES, true)
                    .addItemTexture(alignedToRight(7).build(), 1.0F, () -> Items.LAPIS_LAZULI, true)
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
                    .addTextureDisplay(alignedToLeft(19).build(), 0.115F, "gui/guide_book/content/lunar_temple", 1199, 896)
                    .addItemTexture(alignedToRight(20).build(), 0.9F, AerialHellItems.LUNATIC_CRYSTAL, true)
                    .addItemTexture(alignedToRight(22).build(), 0.85F, AerialHellItems.LUNATIC_AXE, true)
                    .addItemTexture(alignedToRight(24).build(), 0.85F, AerialHellItems.SWORD_OF_LIGHT, true)
                    .addItemTexture(alignedToRight(26).build(), 0.85F, AerialHellItems.LUNATIC_CHESTPLATE, true)
                    .addParagraph(30, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "lunar_temple_material"),
            new Page("journey_7", PAGE_TEXTURE, 7)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_15_prepare_for_shadows")
                    .addItemTexture(alignedToLeft(5).build(), 0.85F, AerialHellItems.AXE_OF_LIGHT, true)
                    .addItemTexture(centered(5).build(), 0.85F, AerialHellItems.SWORD_OF_LIGHT, true)
                    .addItemTexture(alignedToRight(5).build(), 0.85F, AerialHellItems.NIGHT_VISION_TOTEM, true)
                    .addCraftingRecipeDisplay(7, Alignment.CENTER, 0.8F, new CraftingRecipeDisplay.Ingredients(
                            AerialHellItems.DARK_SHADOW_FRUIT, AerialHellItems.PURPLE_SHADOW_FRUIT,
                            AerialHellItems.SHADOW_SPIDER_EYE, AerialHellItems.SKY_BOWL
                    ), () -> AerialHellItems.SHADOW_FRUIT_STEW.get().getDefaultInstance(), true)
                    .addParagraph(11, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "recommended_shadow_items_tips")
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_16_rush_shadow_catacombs")
                    .addTextureDisplay(alignedToLeft(20).build(), 0.13F, "gui/guide_book/content/shadow_catacombs", 1124, 583)
                    .addItemTexture(alignedToRight(21).build(), 0.9F, AerialHellItems.CURSED_CRYSTAL, true)
                    .addItemTexture(alignedToRight(23).build(), 0.85F, AerialHellItems.CURSED_AXE, true)
                    .addItemTexture(alignedToRight(25).build(), 0.85F, AerialHellItems.REAPER_SCYTHE, true)
                    .addItemTexture(alignedToRight(27).build(), 0.85F, AerialHellItems.SHADOW_CHESTPLATE, true)
                    .addParagraph(29, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "shadow_catacombs_material"),
            new Page("journey_8", PAGE_TEXTURE, 8)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_17_get_volucite_equipment")
                    .addItemTexture(alignedToLeft(4).build(), 1.0F, AerialHellItems.LUNATIC_PICKAXE, true)
                    .addItemTexture(centered(4).build(), 1.0F, AerialHellItems.VOLUCITE_ORE, true)
                    .addItemTexture(alignedToRight(4).build(), 1.0F, AerialHellItems.RAW_VOLUCITE, true)
                    .addOscillatingRecipeDisplay(6, Alignment.CENTER, 1.0F, AerialHellItems.RAW_VOLUCITE, AerialHellItems.VOLUCITE_VIBRANT, true)
                    .addParagraph(12, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "volucite_equipment_info")
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "step_18_rush_golden_nether_prison")
                    .addTextureDisplay(centered(19).build(), 0.15F, "gui/guide_book/content/golden_nether_prison", 807, 693)
                    .addItemTexture(alignedToLeft(21).build(), 0.9F, AerialHellItems.ARSONIST_INGOT, true)
                    .addItemTexture(alignedToLeft(23).build(), 0.85F, AerialHellItems.ARSONIST_UPGRADE_SMITHING_TEMPLATE, true)
                    .addItemTexture(alignedToLeft(25).build(), 0.85F, AerialHellItems.ARSONIST_AXE, true)
                    .addItemTexture(alignedToLeft(27).build(), 0.85F, AerialHellItems.ARSONIST_CHESTPLATE, true)
                    .addItemTexture(alignedToRight(21).build(), 0.9F, () -> Items.NETHERITE_INGOT, true)
                    .addItemTexture(alignedToRight(23).build(), 0.85F, AerialHellItems.BERSERK_AXE, true)
                    .addItemTexture(alignedToRight(25).build(), 0.85F, AerialHellItems.NETHERIAN_KING_SWORD, true)
                    .addItemTexture(alignedToRight(27).build(), 0.85F, AerialHellItems.GODS_VOLUCITE_AERIAL_BERRY, true)
                    .addParagraph(29, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "golden_nether_prison_material"),
            new Page("crafting_1", PAGE_TEXTURE, 9)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "crafting_section_title")
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "crafting_section_quote")
                    .addParagraph(9, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "crafting_content_desc")
                    .addItemTexture(alignedToLeft(14).build(), 1.0F, AerialHellItems.STELLAR_FURNACE, true)
                    .addItemTexture(centered(14).build(), 1.0F, AerialHellItems.OSCILLATOR, true)
                    .addItemTexture(alignedToRight(14).build(), 1.0F, AerialHellItems.FREEZER, true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "type_1_smelting_recipe")
                    .addCraftingTableRecipeDisplay(21, Alignment.CENTER, 1.0F, new CraftingTableRecipeDisplay.Ingredients(
                            AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE,
                            AerialHellItems.STELLAR_COBBLESTONE, () -> null, AerialHellItems.STELLAR_COBBLESTONE,
                            AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE
                    ), () -> AerialHellItems.STELLAR_FURNACE.get().getDefaultInstance(), true)
                    .addParagraph(27, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.4F), (int)(LINE_WIDTH_NO_MARGIN * 0.6F), Alignment.LEFT, "get_smelting_fuel")
                    .addSmeltingRecipeDisplay(28, Alignment.LEFT, 0.9F, AerialHellItems.AERIAL_TREE_LOG, () -> Items.CHARCOAL, AerialHellItems.AERIAL_TREE_PLANKS, true),
            new Page("crafting_2", PAGE_TEXTURE, 10)
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
            new Page("crafting_3", PAGE_TEXTURE, 11)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "type_2_oscillating_recipe")
                    .addCraftingTableRecipeDisplay(5, Alignment.CENTER, 1.0F, new CraftingTableRecipeDisplay.Ingredients(
                            AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE,
                            AerialHellItems.STELLAR_STONE, AerialHellItems.FLUORITE, AerialHellItems.STELLAR_STONE,
                            AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE
                    ), () -> AerialHellItems.OSCILLATOR.get().getDefaultInstance(), true)
                    .addParagraph(11, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "oscillating_fuel")
                    .addItemTexture(alignedToLeft(14).build(), 1.0F, AerialHellItems.FLUORITE, true)
                    .addItemTexture(alignedToLeft(14).horizontalOffset(LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.FLUORITE_BLOCK, true)
                    .addItemTexture(alignedToLeft(14).horizontalOffset(2 * LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.CRYSTAL, true)
                    .addItemTexture(alignedToRight(14).build(), 1.0F, AerialHellItems.CRYSTAL_BLOCK, true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "oscillating_ores")
                    .addOscillatingRecipeDisplay(20, Alignment.LEFT, 0.9F, AerialHellItems.STELLAR_STONE_CRYSTAL, AerialHellItems.CRYSTAL, true)
                    .addOscillatingRecipeDisplay(20, Alignment.RIGHT, 0.9F, AerialHellItems.RAW_RUBY, AerialHellItems.RUBY, true)
                    .addOscillatingRecipeDisplay(25, Alignment.LEFT, 0.9F, AerialHellItems.RAW_AZURITE, AerialHellItems.AZURITE_CRYSTAL, true)
                    .addOscillatingRecipeDisplay(25, Alignment.RIGHT, 0.9F, AerialHellItems.RAW_VOLUCITE, AerialHellItems.VOLUCITE_VIBRANT, true)
                    .addParagraph(31, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "oscillating_overheated_ores")
                    .addOscillatingRecipeDisplay(30, Alignment.RIGHT, 0.9F, AerialHellItems.OVERHEATED_RUBY, AerialHellItems.RUBY, true),
            new Page("crafting_4", PAGE_TEXTURE, 12)
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
                    .addItemTexture(centered(26).horizontalOffset(-25).build(), 1.0F, AerialHellItems.MAGMATIC_GEL, true)
                    .addItemTexture(centered(26).horizontalOffset(25).build(), 1.0F, AerialHellItems.MAGMATIC_GEL_BLOCK, true)
                    .addParagraph(28, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "freezing_description")
                    .addFreezingRecipeDisplay(30, Alignment.LEFT, 0.87F, AerialHellItems.AZURITE_CRYSTAL, () -> Items.QUARTZ, true)
                    .addFreezingRecipeDisplay(30, Alignment.RIGHT, 0.87F, () -> Items.ICE, () -> Items.PACKED_ICE, true),
            new Page("crafting_5", PAGE_TEXTURE, 13)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "freezing_food")
                    .addFreezingRecipeDisplay(3, Alignment.LEFT, 0.9F, AerialHellItems.AERIAL_BERRY, AerialHellItems.FROZEN_AERIAL_BERRY, true)
                    .addFreezingRecipeDisplay(3, Alignment.RIGHT, 0.9F, AerialHellItems.TURTLE_MEAT, AerialHellItems.FROZEN_TURTLE_MEAT, true)
                    .addFreezingRecipeDisplay(9, Alignment.LEFT, 0.9F, AerialHellItems.WHITE_SOLID_ETHER_FRAGMENT, AerialHellItems.BLUE_SOLID_ETHER_FRAGMENT, true)
                    .addFreezingRecipeDisplay(9, Alignment.RIGHT, 0.9F, () -> Items.MUTTON, AerialHellItems.FROZEN_MUTTON, true)
                    .addParagraph(14, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "frozen_food_particularity")
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "brewing_recipes")
                    .addBrewingRecipeDisplay(21, Alignment.CENTER, 1.0F, () -> ItemHelper.createPotionItemStack(Potions.AWKWARD), AerialHellItems.SHADOW_SPIDER_EYE, () -> ItemHelper.createPotionItemStack(Potions.POISON), true)
                    .addBrewingRecipeDisplay(28, Alignment.CENTER, 1.0F, () -> ItemHelper.createPotionItemStack(Potions.AWKWARD), AerialHellItems.VENOMOUS_SNAKE_SKIN, () -> ItemHelper.createPotionItemStack(Potions.HEALING), true),
            new Page("materials_1", PAGE_TEXTURE, 14)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "materials_section_title")
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "materials_section_quote")
                    .addParagraph(7, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "materials_content_desc")
                    .addItemTexture(alignedToLeft(11).build(), 1.0F, AerialHellItems.FLUORITE, true)
                    .addItemTexture(alignedToLeft(11).horizontalOffset(LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.MAGMATIC_GEL, true)
                    .addItemTexture(alignedToLeft(11).horizontalOffset(2 * LINE_WIDTH_NO_MARGIN / 3 - 16).build(), 1.0F, AerialHellItems.AZURITE_CRYSTAL, true)
                    .addItemTexture(alignedToRight(11).build(), 1.0F, AerialHellItems.OBSIDIAN_SHARD, true)
                    .addItemTexture(alignedToLeft(13).horizontalOffset(LINE_WIDTH_NO_MARGIN / 4 - 8).build(), 1.0F, AerialHellItems.RUBY, true)
                    .addItemTexture(centered(13).horizontalOffset(LINE_WIDTH_NO_MARGIN / 4 - 8).build(), 1.0F, AerialHellItems.VOLUCITE_VIBRANT, true)
                    .addItemTexture(alignedToLeft(15).build(), 1.0F, AerialHellItems.SHADOW_SHARD, true)
                    .addItemTexture(alignedToLeft(15).horizontalOffset(LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.LUNATIC_CRYSTAL, true)
                    .addItemTexture(alignedToLeft(15).horizontalOffset(2 * LINE_WIDTH_NO_MARGIN / 3 - 16).build(), 1.0F, AerialHellItems.CURSED_CRYSTAL, true)
                    .addItemTexture(alignedToRight(15).build(), 1.0F, AerialHellItems.ARSONIST_INGOT, true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.1F), (int)(LINE_WIDTH_NO_MARGIN * 0.9F), Alignment.LEFT, "materials_tier_list")
                    .addItemTexture(alignedToLeft(20).build(), 0.6F, AerialHellItems.AERIAL_TREE_PLANKS, true)
                    .addItemTexture(alignedToLeft(22).centerVerticallyOnLine().build(), 0.6F, AerialHellItems.FLUORITE, true)
                    .addItemTexture(alignedToLeft(23).centerVerticallyOnLine().build(), 0.6F, AerialHellItems.STELLAR_COBBLESTONE, true)
                    .addItemTexture(alignedToLeft(24).centerVerticallyOnLine().build(), 0.6F, AerialHellItems.RUBY, true)
                    .addItemTexture(alignedToLeft(25).centerVerticallyOnLine().build(), 0.6F, AerialHellItems.MAGMATIC_GEL, true)
                    .addItemTexture(alignedToLeft(26).centerVerticallyOnLine().build(), 0.6F, AerialHellItems.AZURITE_CRYSTAL, true)
                    .addItemTexture(alignedToLeft(27).centerVerticallyOnLine().build(), 0.6F, AerialHellItems.OBSIDIAN_SHARD, true)
                    .addItemTexture(alignedToLeft(28).build(), 0.6F, AerialHellItems.LUNATIC_CRYSTAL, true)
                    .addItemTexture(alignedToLeft(30).centerVerticallyOnLine().build(), 0.6F, AerialHellItems.CURSED_CRYSTAL, true)
                    .addItemTexture(alignedToLeft(31).centerVerticallyOnLine().build(), 0.6F, AerialHellItems.VOLUCITE_VIBRANT, true)
                    .addItemTexture(alignedToLeft(32).centerVerticallyOnLine().build(), 0.6F, AerialHellItems.ARSONIST_INGOT, true),
            new Page("materials_2", PAGE_TEXTURE, 15)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "fluorite_material")
                    .addItemTexture(alignedToRight(1).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.FLUORITE, true)
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.14F), (int)(LINE_WIDTH_NO_MARGIN * 0.86F), Alignment.LEFT, "fluorite_description")
                    .addItemTexture(alignedToLeft(3).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.FLUORITE_ORE, false)
                    .addItemTexture(alignedToLeft(5).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.OSCILLATOR, false)
                    .addItemTexture(alignedToLeft(7).build(), 1.0F, AerialHellItems.WEAK_LIGHT_REACTOR, false)
                    .addItemTexture(alignedToLeft(10).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.AERIAL_TREE_CRAFTING_TABLE, false)
                    .addItemTexture(alignedToLeft(12).build(), 1.0F, AerialHellItems.FLUORITE_TORCH, true)
                    .addItemTexture(alignedToLeft(12).horizontalOffset(LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.STELLAR_LIGHTER, true)
                    .addItemTexture(alignedToLeft(12).horizontalOffset(2 * LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.RUBY_FLUORITE_LANTERN, true)
                    .addItemTexture(alignedToRight(12).build(), 1.0F, AerialHellItems.FLUORITE_BLOCK, true)
                    .addItemTexture(alignedToLeft(14).horizontalOffset(LINE_WIDTH_NO_MARGIN / 4 - 8).build(), 1.0F, AerialHellItems.OSCILLATOR, true)
                    .addItemTexture(alignedToRight(14).horizontalOffset(-LINE_WIDTH_NO_MARGIN / 4 + 8).build(), 1.0F, AerialHellItems.WEAK_LIGHT_REACTOR, true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "ruby_material")
                    .addItemTexture(alignedToRight(18).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.RUBY, true)
                    .addItemTexture(alignedToLeft(18).horizontalOffset(LINE_WIDTH_NO_MARGIN - 38).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.RAW_RUBY, true)
                    .addParagraph(20, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.14F), (int)(LINE_WIDTH_NO_MARGIN * 0.86F), Alignment.LEFT, "ruby_description")
                    .addItemTexture(alignedToLeft(20).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.RUBY_ORE, false)
                    .addItemTexture(alignedToLeft(22).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.AERIAL_TREE_CHEST, false)
                    .addItemTexture(alignedToLeft(24).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.OSCILLATOR, false)
                    .addItemTexture(alignedToLeft(26).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.COPPER_PINE_CRAFTING_TABLE, false)
                    .addItemTexture(alignedToLeft(28).build(), 1.0F, AerialHellItems.RUBY_CHESTPLATE, true)
                    .addItemTexture(alignedToLeft(28).horizontalOffset(LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.RUBY_SWORD, true)
                    .addItemTexture(alignedToLeft(28).horizontalOffset(2 * LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.RUBY_FLUORITE_LANTERN, true)
                    .addItemTexture(alignedToRight(28).build(), 1.0F, AerialHellItems.RUBY_BLOCK, true)
                    .addItemTexture(alignedToLeft(30).horizontalOffset(LINE_WIDTH_NO_MARGIN / 4 - 8).build(), 1.0F, AerialHellItems.RUBY_BLOWPIPE, true)
                    .addItemTexture(alignedToRight(30).horizontalOffset(-LINE_WIDTH_NO_MARGIN / 4 + 8).build(), 1.0F, AerialHellItems.RUBY_BLOWPIPE_ARROW, true)
                    .addItemTexture(alignedToLeft(32).build(), 1.0F, AerialHellItems.RUBY_BUCKET, true)
                    .addItemTexture(alignedToLeft(32).horizontalOffset(LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.RUBY_BARS, true)
                    .addItemTexture(alignedToLeft(32).horizontalOffset(2 * LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.STELLAR_LIGHTER, true)
                    .addItemTexture(alignedToRight(32).build(), 1.0F, AerialHellItems.RUBY_FLUORITE_LANTERN, true),
            new Page("materials_3", PAGE_TEXTURE, 16)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "magmatic_gel_material")
                    .addItemTexture(alignedToRight(1).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.MAGMATIC_GEL, true)
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.14F), (int)(LINE_WIDTH_NO_MARGIN * 0.86F), Alignment.LEFT, "magmatic_gel_description")
                    .addItemTexture(alignedToLeft(3).build(), 1.0F, AerialHellItems.MAGMATIC_GEL_ORE, false)
                    .addItemTexture(alignedToLeft(6).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.FREEZER, false)
                    .addItemTexture(alignedToLeft(8).build(), 1.0F, AerialHellItems.MAGMATIC_GEL_BOOTS, true)
                    .addItemTexture(alignedToLeft(11).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.MAGMATIC_GEL_SWORD, true)
                    .addItemTexture(alignedToLeft(13).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.SKY_CACTUS_FIBER_CRAFTING_TABLE, false)
                    .addItemTexture(alignedToLeft(15).build(), 1.0F, AerialHellItems.MAGMATIC_GEL_CHESTPLATE, true)
                    .addItemTexture(alignedToLeft(15).horizontalOffset(LINE_WIDTH_NO_MARGIN / 5 - 8).build(), 1.0F, AerialHellItems.MAGMATIC_GEL_PICKAXE, true)
                    .addItemTexture(alignedToLeft(15).horizontalOffset(2 *LINE_WIDTH_NO_MARGIN / 5 - 8).build(), 1.0F, AerialHellItems.FREEZER, true)
                    .addItemTexture(alignedToLeft(15).horizontalOffset(3 * LINE_WIDTH_NO_MARGIN / 5 - 8).build(), 1.0F, AerialHellItems.MAGMATIC_GEL_BLOCK, true)
                    .addItemTexture(alignedToLeft(15).horizontalOffset(4 * LINE_WIDTH_NO_MARGIN / 5 - 8).build(), 1.0F, AerialHellItems.MAGMATIC_GEL_STAIRS, true)
                    .addItemTexture(alignedToRight(15).build(), 1.0F, AerialHellItems.MAGMATIC_GEL_SLAB, true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "azurite_crystal_material")
                    .addItemTexture(alignedToRight(18).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.AZURITE_CRYSTAL, true)
                    .addItemTexture(alignedToLeft(18).horizontalOffset(LINE_WIDTH_NO_MARGIN - 38).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.RAW_AZURITE, true)
                    .addParagraph(20, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.14F), (int)(LINE_WIDTH_NO_MARGIN * 0.86F), Alignment.LEFT, "azurite_crystal_description")
                    .addItemTexture(alignedToLeft(20).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.AZURITE_ORE, false)
                    .addItemTexture(alignedToLeft(22).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.AZURITE_PICKAXE, false)
                    .addItemTexture(alignedToLeft(24).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.OSCILLATOR, false)
                    .addItemTexture(alignedToLeft(26).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.LAPIS_ROBINIA_CRAFTING_TABLE, false)
                    .addItemTexture(alignedToLeft(28).build(), 1.0F, AerialHellItems.AZURITE_CHESTPLATE, true)
                    .addItemTexture(alignedToLeft(28).horizontalOffset(LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.AZURITE_SWORD, true)
                    .addItemTexture(alignedToLeft(28).horizontalOffset(2 * LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.AZURITE_PICKAXE, true)
                    .addItemTexture(alignedToRight(28).build(), 1.0F, AerialHellItems.AZURITE_BLOCK, true)
                    .addItemTexture(alignedToLeft(30).horizontalOffset(LINE_WIDTH_NO_MARGIN / 4 - 8).build(), 1.0F, AerialHellItems.AZURITE_COPPER_PINE_CONE, true)
                    .addItemTexture(alignedToRight(30).horizontalOffset(-LINE_WIDTH_NO_MARGIN / 4 + 8).build(), 1.0F, AerialHellItems.AZURITE_SHURIKEN, true),
            new Page("materials_4", PAGE_TEXTURE, 17)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "vanilla_materials")
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.1F), (int)(LINE_WIDTH_NO_MARGIN * 0.9F), Alignment.LEFT, "vanilla_materials_found_as_ore_list")
                    .addItemTexture(alignedToLeft(3).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.IRON_STELLAR_ORE, true)
                    .addItemTexture(alignedToLeft(4).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.GOLD_STELLAR_ORE, true)
                    .addItemTexture(alignedToLeft(5).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.DIAMOND_STELLAR_ORE, true)
                    .addParagraph(7, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "vanilla_materials_found_in_structures")
                    .addParagraph(9, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.1F), (int)(LINE_WIDTH_NO_MARGIN * 0.9F), Alignment.LEFT, "vanilla_materials_found_in_structures_list")
                    .addItemTexture(alignedToLeft(9).centerVerticallyOnLine().build(), 0.7F, () -> Items.FLINT, true)
                    .addItemTexture(alignedToLeft(10).centerVerticallyOnLine().build(), 0.7F, () -> Items.REDSTONE, true)
                    .addParagraph(12, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "vanilla_materials_craftable")
                    .addFreezingRecipeDisplay(13, Alignment.LEFT, 0.8F, AerialHellItems.AZURITE_CRYSTAL, () -> Items.QUARTZ, true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "obsidian_material")
                    .addItemTexture(alignedToRight(18).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.OBSIDIAN_SHARD, true)
                    .addParagraph(20, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.14F), (int)(LINE_WIDTH_NO_MARGIN * 0.86F), Alignment.LEFT, "obsidian_description")
                    .addItemTexture(alignedToLeft(20).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.MUD_BRICKS, false)
                    .addItemTexture(alignedToLeft(22).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.OBSIDIAN_ORE, false)
                    .addItemTexture(alignedToLeft(24).build(), 1.0F, () -> Items.ANVIL, false)
                    .addItemTexture(alignedToLeft(27).build(), 1.0F, () -> Items.ENCHANTING_TABLE, false)
                    .addItemTexture(alignedToLeft(30).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.SHADOW_PINE_CRAFTING_TABLE, false)
                    .addItemTexture(alignedToLeft(32).build(), 1.0F, AerialHellItems.OBSIDIAN_HELMET, true)
                    .addItemTexture(alignedToLeft(32).horizontalOffset(LINE_WIDTH_NO_MARGIN / 5 - 8).build(), 1.0F, AerialHellItems.OBSIDIAN_CHESTPLATE, true)
                    .addItemTexture(alignedToLeft(32).horizontalOffset(2 * LINE_WIDTH_NO_MARGIN / 5 - 8).build(), 1.0F, AerialHellItems.OBSIDIAN_SWORD, true)
                    .addItemTexture(alignedToLeft(32).horizontalOffset(3 * LINE_WIDTH_NO_MARGIN / 5 - 8).build(), 1.0F, AerialHellItems.OBSIDIAN_PICKAXE, true)
                    .addItemTexture(alignedToLeft(32).horizontalOffset(4 * LINE_WIDTH_NO_MARGIN / 5 - 8).build(), 1.0F, () -> Items.OBSIDIAN, true)
                    .addItemTexture(alignedToRight(32).build(), 1.0F, () -> Items.ENCHANTING_TABLE, true),
            new Page("materials_5", PAGE_TEXTURE, 18)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "lunar_crystal_material")
                    .addItemTexture(alignedToRight(1).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.LUNATIC_CRYSTAL, true)
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.14F), (int)(LINE_WIDTH_NO_MARGIN * 0.86F), Alignment.LEFT, "lunar_crystal_description")
                    .addItemTexture(alignedToLeft(3).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.LUNATIC_STONE, false)
                    .addItemTexture(alignedToLeft(5).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.LUNATIC_CHESTPLATE, false)
                    .addItemTexture(alignedToLeft(7).build(), 1.0F, () -> Items.ANVIL, false)
                    .addItemTexture(alignedToLeft(10).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.GOLDEN_BEECH_CRAFTING_TABLE, false)
                    .addItemTexture(alignedToLeft(11).build(), 1.0F, AerialHellItems.LUNATIC_HELMET, true)
                    .addItemTexture(alignedToLeft(11).horizontalOffset(LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.LUNATIC_CHESTPLATE, true)
                    .addItemTexture(alignedToLeft(11).horizontalOffset(2 * LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.LUNATIC_LEGGINGS, true)
                    .addItemTexture(alignedToRight(11).build(), 1.0F, AerialHellItems.LUNATIC_BOOTS, true)
                    .addItemTexture(alignedToLeft(13).horizontalOffset(LINE_WIDTH_NO_MARGIN / 4 - 8).build(), 1.0F, AerialHellItems.LUNATIC_SWORD, true)
                    .addItemTexture(alignedToRight(13).horizontalOffset(-LINE_WIDTH_NO_MARGIN / 4 + 8).build(), 1.0F, AerialHellItems.LUNATIC_PICKAXE, true)
                    .addItemTexture(alignedToLeft(15).build(), 1.0F, AerialHellItems.LUNATIC_SHOVEL, true)
                    .addItemTexture(alignedToLeft(15).horizontalOffset(LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.LUNATIC_HOE, true)
                    .addItemTexture(alignedToLeft(15).horizontalOffset(2 * LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.LUNATIC_AXE, true)
                    .addItemTexture(alignedToRight(15).build(), 1.0F, AerialHellItems.LUNATIC_CRYSTAL_BLOCK, true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "cursed_crystal_material")
                    .addItemTexture(alignedToRight(18).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.CURSED_CRYSTAL, true)
                    .addParagraph(20, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.14F), (int)(LINE_WIDTH_NO_MARGIN * 0.86F), Alignment.LEFT, "cursed_crystal_description")
                    .addItemTexture(alignedToLeft(20).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.SHADOW_CATACOMBS_BRICKS, false)
                    .addItemTexture(alignedToLeft(22).build(), 1.0F, AerialHellItems.SHADOW_CHESTPLATE, false)
                    .addItemTexture(alignedToLeft(25).build(), 1.0F, () -> Items.ANVIL, false)
                    .addItemTexture(alignedToLeft(28).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.SHADOW_PINE_CRAFTING_TABLE, false)
                    .addItemTexture(alignedToLeft(30).build(), 1.0F, AerialHellItems.SHADOW_HELMET, true)
                    .addItemTexture(alignedToLeft(30).horizontalOffset(LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.SHADOW_CHESTPLATE, true)
                    .addItemTexture(alignedToLeft(30).horizontalOffset(2 * LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.SHADOW_LEGGINGS, true)
                    .addItemTexture(alignedToRight(30).build(), 1.0F, AerialHellItems.SHADOW_BOOTS, true)
                    .addItemTexture(alignedToLeft(32).horizontalOffset(LINE_WIDTH_NO_MARGIN / 4 - 8).build(), 1.0F, AerialHellItems.CURSED_CRYSAL_BLOCK, true)
                    .addItemTexture(alignedToRight(32).horizontalOffset(-LINE_WIDTH_NO_MARGIN / 4 + 8).build(), 1.0F, AerialHellItems.WEAK_SHADOW_REACTOR, true),
            new Page("materials_6", PAGE_TEXTURE, 19)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "volucite_vibrant_material")
                    .addItemTexture(alignedToLeft(1).horizontalOffset(LINE_WIDTH_NO_MARGIN - 38).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.RAW_VOLUCITE, true)
                    .addItemTexture(alignedToRight(1).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.VOLUCITE_VIBRANT, true)
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.14F), (int)(LINE_WIDTH_NO_MARGIN * 0.86F), Alignment.LEFT, "volucite_vibrant_description")
                    .addItemTexture(alignedToLeft(3).build(), 1.0F, AerialHellItems.VOLUCITE_ORE, false)
                    .addItemTexture(alignedToLeft(6).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.LUNATIC_STONE, false)
                    .addItemTexture(alignedToLeft(8).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.VOLUCITE_CHESTPLATE, true)
                    .addItemTexture(alignedToLeft(10).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.VOLUCITE_SWORD, true)
                    .addItemTexture(alignedToLeft(12).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.LAPIS_ROBINIA_CRAFTING_TABLE, false)
                    .addItemTexture(alignedToLeft(14).build(), 1.0F, AerialHellItems.VOLUCITE_HELMET, true)
                    .addItemTexture(alignedToLeft(14).horizontalOffset(LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.VOLUCITE_PICKAXE, true)
                    .addItemTexture(alignedToLeft(14).horizontalOffset(2 * LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.VOLUCITE_AERIAL_BERRY, true)
                    .addItemTexture(alignedToRight(14).build(), 1.0F, AerialHellItems.VOLUCITE_FLUORITE_LANTERN, true)
                    .addItemTexture(alignedToLeft(16).horizontalOffset(LINE_WIDTH_NO_MARGIN / 4 - 8).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.VOLUCITE_BLOWPIPE, true)
                    .addItemTexture(alignedToRight(16).horizontalOffset(-LINE_WIDTH_NO_MARGIN / 4 + 8).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.VOLUCITE_BLOWPIPE_ARROW, true)
                    .addParagraph(18, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "arsonist_material")
                    .addItemTexture(alignedToRight(18).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.ARSONIST_INGOT, true)
                    .addParagraph(20, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.14F), (int)(LINE_WIDTH_NO_MARGIN * 0.86F), Alignment.LEFT, "arsonist_description")
                    .addItemTexture(alignedToLeft(20).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.GOLDEN_NETHER_BRICKS, false)
                    .addItemTexture(alignedToLeft(22).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.ARSONIST_CHESTPLATE, false)
                    .addItemTexture(alignedToLeft(24).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.ARSONIST_SWORD, false)
                    .addItemTexture(alignedToLeft(26).build(), 1.0F, () -> Items.ANVIL, false)
                    .addItemTexture(alignedToLeft(29).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.COPPER_PINE_CRAFTING_TABLE, false)
                    .addItemTexture(alignedToLeft(31).build(), 1.0F, AerialHellItems.ARSONIST_HELMET, true)
                    .addItemTexture(alignedToLeft(31).horizontalOffset(LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.ARSONIST_CHESTPLATE, true)
                    .addItemTexture(alignedToLeft(31).horizontalOffset(2 * LINE_WIDTH_NO_MARGIN / 3 - 8).build(), 1.0F, AerialHellItems.ARSONIST_SWORD, true)
                    .addItemTexture(alignedToRight(31).build(), 1.0F, AerialHellItems.ARSONIST_LEGGINGS, true)
                    .addItemTexture(alignedToLeft(33).horizontalOffset(LINE_WIDTH_NO_MARGIN / 4 - 8).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.ARSONIST_PICKAXE, true)
                    .addItemTexture(alignedToRight(33).horizontalOffset(-LINE_WIDTH_NO_MARGIN / 4 + 8).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.ARSONIST_BLOCK, true),
            new Page("materials_7", PAGE_TEXTURE, 20)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "other_materials")
                    .addItemTexture(alignedToLeft(3).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.SMOKY_QUARTZ_ORE, true)
                    .addItemTexture(alignedToLeft(3).horizontalOffset(20).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.SMOKY_QUARTZ, true)
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.28F), (int)(LINE_WIDTH_NO_MARGIN * 0.72F), Alignment.LEFT, "smoky_quartz")
                    .addParagraph(5, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "smoky_quartz_desc")
                    .addCraftingTableRecipeDisplay(9, Alignment.LEFT, 0.95F, new CraftingTableRecipeDisplay.Ingredients(
                            () -> null, () -> Items.REDSTONE_TORCH, () -> null,
                            () -> Items.REDSTONE_TORCH, AerialHellItems.SMOKY_QUARTZ, () -> Items.REDSTONE_TORCH,
                            AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE, AerialHellItems.STELLAR_STONE
                    ), Items.COMPARATOR::getDefaultInstance, true)
                    .addItemTexture(alignedToLeft(11).horizontalOffset(4 * LINE_WIDTH_NO_MARGIN / 5 - 8).build(), 1.0F, AerialHellItems.SMOKY_QUARTZ_BLOCK, true)
                    .addItemTexture(alignedToRight(11).build(), 1.0F, AerialHellItems.SMOKY_QUARTZ_SLAB, true)
                    .addItemTexture(alignedToLeft(13).horizontalOffset(4 * LINE_WIDTH_NO_MARGIN / 5 - 8).build(), 1.0F, AerialHellItems.CHISELED_SMOKY_QUARTZ_BLOCK, true)
                    .addItemTexture(alignedToRight(13).build(), 1.0F, AerialHellItems.SMOKY_QUARTZ_PILLAR, true)
                    .addItemTexture(alignedToLeft(15).horizontalOffset(4 * LINE_WIDTH_NO_MARGIN / 5 - 8).build(), 1.0F, AerialHellItems.SMOKY_QUARTZ_BRICKS, true)
                    .addItemTexture(alignedToRight(15).build(), 1.0F, AerialHellItems.SMOKY_QUARTZ_STAIRS, true)
                    .addCraftingTableRecipeDisplay(18, Alignment.LEFT, 0.8F, new CraftingTableRecipeDisplay.Ingredients(
                            AerialHellItems.SLIPPERY_SAND_GLASS, AerialHellItems.SLIPPERY_SAND_GLASS, AerialHellItems.SLIPPERY_SAND_GLASS,
                            AerialHellItems.SMOKY_QUARTZ, AerialHellItems.SMOKY_QUARTZ, AerialHellItems.SMOKY_QUARTZ,
                            AerialHellItems.AERIAL_TREE_SLAB, AerialHellItems.AERIAL_TREE_SLAB, AerialHellItems.AERIAL_TREE_SLAB
                    ), Items.DAYLIGHT_DETECTOR::getDefaultInstance, true)
                    .addCraftingTableRecipeDisplay(18, Alignment.RIGHT, 0.8F, new CraftingTableRecipeDisplay.Ingredients(
                            AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE,
                            () -> Items.REDSTONE, () -> Items.REDSTONE, AerialHellItems.SMOKY_QUARTZ,
                            AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_COBBLESTONE
                    ), Items.OBSERVER::getDefaultInstance, true)
                    .addItemTexture(alignedToLeft(24).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.CRYSTAL_BLOCK, true)
                    .addItemTexture(alignedToLeft(24).horizontalOffset(20).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.CRYSTAL, true)
                    .addParagraph(24, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.28F), (int)(LINE_WIDTH_NO_MARGIN * 0.72F), Alignment.LEFT, "crystal")
                    .addParagraph(25, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "crystal_desc")
                    .addItemTexture(alignedToLeft(29).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.SHADOW_SHARD, true)
                    .addParagraph(29, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.2F), (int)(LINE_WIDTH_NO_MARGIN * 0.8F), Alignment.LEFT, "shadow_shard")
                    .addParagraph(30, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "shadow_shard_desc"),
            new Page("effects_1", PAGE_TEXTURE, 21)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "effects_section_title")
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "effects_section_quote")
                    .addParagraph(8, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "effects_content_desc")
                    .addParagraph(18,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "beneficial")
                    .addTextureDisplay(alignedToLeft(19).build(), 1.0F, "mob_effect/head_in_the_clouds", 25, 25, "effect.aerialhell.head_in_the_clouds")
                    .addTextureDisplay(alignedToRight(19).build(), 1.0F, "mob_effect/shadow_immunity", 25, 25, "effect.aerialhell.shadow_immunity")
                    .addTextureDisplay(alignedToLeft(22).build(), 1.0F, "mob_effect/shadow_bind", 25, 25, "effect.aerialhell.shadow_bind")
                    .addTextureDisplay(alignedToRight(22).build(), 1.0F, "mob_effect/god", 25, 25, "effect.aerialhell.god")
                    .addParagraph(26,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "negative")
                    .addTextureDisplay(alignedToLeft(27).build(), 1.0F, "mob_effect/vulnerability", 25, 25, "effect.aerialhell.vulnerability")
                    .addTextureDisplay(alignedToRight(27).build(), 1.0F, "mob_effect/traitor", 25, 25, "effect.aerialhell.traitor"),
            new Page("effects_2", PAGE_TEXTURE, 22)
                    .addParagraph(1,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "head_in_the_clouds")
                    .addTextureDisplay(alignedToRight(0).build(), 1.0F, "mob_effect/head_in_the_clouds", 25, 25, "effect.aerialhell.head_in_the_clouds")
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.14F), (int)(LINE_WIDTH_NO_MARGIN * 0.86F), Alignment.LEFT, "head_in_the_clouds_desc")
                    .addItemTexture(alignedToLeft(3).build(), 1.0F, () -> Items.FEATHER, false)
                    .addItemTexture(alignedToLeft(7).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.WHITE_SOLID_ETHER, true)
                    .addItemTexture(alignedToLeft(10).build(), 1.0F, AerialHellItems.GHOST_BOAT_PLANKS, true)
                    .addParagraph(13, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.16F), 70, Alignment.LEFT, "head_in_the_clouds_soup")
                    .addItemTexture(alignedToLeft(13).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.SOLID_ETHER_SOUP, true)
                    .addItemTexture(alignedToLeft(14).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.FROZEN_SOLID_ETHER_SOUP, true)
                    .addItemTexture(alignedToLeft(15).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.VIBRANT_SOLID_ETHER_SOUP, true)
                    .addCraftingTableRecipeDisplay(13, Alignment.RIGHT, 0.65F, new CraftingTableRecipeDisplay.Ingredients(
                            AerialHellItems.WHITE_SOLID_ETHER_FRAGMENT, AerialHellItems.BLUE_SOLID_ETHER_FRAGMENT, AerialHellItems.GOLDEN_SOLID_ETHER_FRAGMENT,
                            AerialHellItems.GREEN_SOLID_ETHER_FRAGMENT, AerialHellItems.SKY_BOWL, () -> null,
                            () -> null, () -> null, () -> null
                    ), () -> AerialHellItems.SOLID_ETHER_SOUP.get().getDefaultInstance(), true)
                    .addParagraph(18,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "shadow_immunity")
                    .addTextureDisplay(alignedToRight(17).build(), 1.0F, "mob_effect/shadow_immunity", 25, 25, "effect.aerialhell.shadow_immunity")
                    .addParagraph(20, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.14F), (int)(LINE_WIDTH_NO_MARGIN * 0.86F), Alignment.LEFT, "shadow_immunity_desc")
                    .addItemTexture(alignedToLeft(20).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.SHADOW_FRUIT_STEW, false)
                    .addCraftingRecipeDisplay(22, Alignment.CENTER, 0.8F, new CraftingRecipeDisplay.Ingredients(
                            AerialHellItems.DARK_SHADOW_FRUIT, AerialHellItems.PURPLE_SHADOW_FRUIT,
                            AerialHellItems.SHADOW_SPIDER_EYE, AerialHellItems.SKY_BOWL
                    ), () -> AerialHellItems.SHADOW_FRUIT_STEW.get().getDefaultInstance(), true)
                    .addItemTexture(alignedToLeft(26).build(), 1.0F, AerialHellItems.SHADOW_BRAMBLES, true)
                    .addItemStackTexture(alignedToLeft(31).centerVerticallyOnLine().build(), 1.0F, () -> ItemHelper.createPotionItemStack(Potions.TURTLE_MASTER), false),
            new Page("effects_3", PAGE_TEXTURE,23)
                    .addParagraph(1,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "vulnerability")
                    .addTextureDisplay(alignedToRight(0).build(), 1.0F, "mob_effect/vulnerability", 25, 25, "effect.aerialhell.vulnerability")
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.14F), (int)(LINE_WIDTH_NO_MARGIN * 0.86F), Alignment.LEFT, "vulnerability_desc")
                    .addItemStackTexture(alignedToLeft(4).centerVerticallyOnLine().build(), 1.0F, () -> ItemHelper.createPotionItemStack(Potions.HARMING), false)
                    .addItemStackTexture(alignedToLeft(7).build(), 1.0F, () -> ItemHelper.createPotionItemStack(Potions.STRONG_HARMING), false)
                    .addItemStackTexture(alignedToLeft(10).build(), 1.0F, () -> ItemHelper.createPotionItemStack(Potions.LONG_WEAKNESS), false)
                    .addItemTexture(alignedToLeft(13).build(), 1.0F, AerialHellItems.SHADOW_SHARD, false)
                    .addItemTexture(alignedToLeft(16).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.SHADOW_FRUIT_STEW, false)
                    .addParagraph(18,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "shadow_bind")
                    .addTextureDisplay(alignedToRight(17).build(), 1.0F, "mob_effect/shadow_bind", 25, 25, "effect.aerialhell.shadow_bind")
                    .addParagraph(20, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.14F), (int)(LINE_WIDTH_NO_MARGIN * 0.86F), Alignment.LEFT, "shadow_bind_desc")
                    .addItemTexture(alignedToLeft(20).build(), 1.0F, AerialHellItems.SHADOW_CHESTPLATE, false)
                    .addItemTexture(alignedToLeft(23).build(),1.0F, AerialHellItems.SHADOW_GRASS_BLOCK, false)
                    .addItemTexture(alignedToLeft(26).build(), 1.0F, AerialHellItems.SHADOW_BRAMBLES, true)
                    .addItemTexture(alignedToLeft(31).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.SHADOW_AUTOMATON_SPAWN_EGG, false),
            new Page("effects_4", PAGE_TEXTURE,24)
                    .addParagraph(1,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "god")
                    .addTextureDisplay(alignedToRight(0).build(), 1.0F, "mob_effect/god", 25, 25, "effect.aerialhell.god")
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.14F), (int)(LINE_WIDTH_NO_MARGIN * 0.86F), Alignment.LEFT, "god_desc")
                    .addItemStackTexture(alignedToLeft(4).centerVerticallyOnLine().build(), 1.0F, () -> ItemHelper.createPotionItemStack(Potions.REGENERATION), false)
                    .addItemTexture(alignedToLeft(7).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.BLACK_ROSE, false)
                    .addParagraph(18,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "traitor")
                    .addTextureDisplay(alignedToRight(17).build(), 1.0F, "mob_effect/traitor", 25, 25, "effect.aerialhell.traitor")
                    .addParagraph(20, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.14F), (int)(LINE_WIDTH_NO_MARGIN * 0.86F), Alignment.LEFT, "traitor_desc")
                    .addItemTexture(alignedToLeft(21).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.DISLOYAL_SWORD, false)
                    .addItemStackTexture(alignedToLeft(24).build(), 1.0F, () -> ItemHelper.createPotionItemStack(Potions.INFESTED), false),
            new Page("enchantments_1", PAGE_TEXTURE, 25)
                    .addParagraph(1, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "enchantments_section_title")
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "enchantments_section_quote")
                    .addParagraph(7, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "enchantments_content_desc")
                    .addItemStackTexture(alignedToLeft(13).horizontalOffset(LINE_WIDTH_NO_MARGIN / 4 - 8).centerVerticallyOnLine().build(), 1.0F, () -> ItemHelper.createEnchantedBookItemStack(AerialHellEnchantments.SOLID_ETHER_WALKER, 1, Minecraft.getInstance().level.registryAccess()), true)
                    .addItemStackTexture(alignedToLeft(13).horizontalOffset(3 * LINE_WIDTH_NO_MARGIN / 4 - 8).centerVerticallyOnLine().build(), 1.0F, () -> ItemHelper.createEnchantedBookItemStack(AerialHellEnchantments.VULNERABILITY_ASPECT, 1, Minecraft.getInstance().level.registryAccess()), true)
                    .addParagraph(18,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "solid_ether_walker")
                    .addItemStackTexture(alignedToRight(18).centerVerticallyOnLine().build(), 1.0F, () -> ItemHelper.createEnchantedBookItemStack(AerialHellEnchantments.SOLID_ETHER_WALKER, 1, Minecraft.getInstance().level.registryAccess()), true)
                    .addParagraph(20, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.14F), (int)(LINE_WIDTH_NO_MARGIN * 0.86F), Alignment.LEFT, "solid_ether_walker_desc")
                    .addItemTexture(alignedToLeft(20).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.AERIAL_TREE_CHEST, false)
                    .addItemTexture(alignedToLeft(22).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.LUNATIC_STONE, false)
                    .addItemTexture(alignedToLeft(25).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.WHITE_SOLID_ETHER, true)
                    .addItemTexture(alignedToLeft(28).build(), 1.0F, AerialHellItems.GHOST_BOAT_PLANKS, true),
            new Page("enchantments_2", PAGE_TEXTURE, 26)
                    .addParagraph(1,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "vulnerability_aspect")
                    .addItemStackTexture(alignedToRight(1).centerVerticallyOnLine().build(), 1.0F, () -> ItemHelper.createEnchantedBookItemStack(AerialHellEnchantments.VULNERABILITY_ASPECT, 1, Minecraft.getInstance().level.registryAccess()), true)
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.14F), (int)(LINE_WIDTH_NO_MARGIN * 0.86F), Alignment.LEFT, "vulnerability_aspect_desc")
                    .addItemTexture(alignedToLeft(3).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.SHADOW_PINE_CHEST, false)
                    .addItemTexture(alignedToLeft(5).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.SHADOW_CATACOMBS_BRICKS, false)
                    .addItemTexture(alignedToLeft(8).centerVerticallyOnLine().build(), 1.0F, AerialHellItems.CURSED_SWORD, false)
                    .addParagraph(11,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "vulnerability_aspect_additional_info")
                    .addParagraph(18,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "light_entities_additional_info"),
            new Page("environment_1", PAGE_TEXTURE,27)
                    .addParagraph(1,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "environment_section_title")
                    .addParagraph(3, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "environment_section_quote")
                    .addParagraph(7, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "environment_content_desc")
                    .addParagraph(18,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "classic_biomes")
                    .addParagraph(20,MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.1F), (int)(LINE_WIDTH_NO_MARGIN * 0.9F), Alignment.LEFT, "aerial_hell_plains")
                    .addItemTexture(alignedToLeft(20).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.STELLAR_GRASS_BLOCK, false)
                    .addParagraph(22,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "aerial_hell_plains_desc")
                    .addParagraph(27,MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.1F), (int)(LINE_WIDTH_NO_MARGIN * 0.9F), Alignment.LEFT, "aerial_tree_forest")
                    .addItemTexture(alignedToLeft(27).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.AERIAL_TREE_LEAVES, false)
                    .addParagraph(29,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "aerial_tree_forest_desc")
                    .addTextureDisplay(centered(31).build(), 0.8F, "gui/guide_book/content/entities/flying_jellyfish", 28, 30, "entity.aerialhell.flying_jellyfish"),
            new Page("environment_2", PAGE_TEXTURE,28)
                    .addParagraph(1,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "lapis_robinia_biomes")
                    .addParagraph(3,MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.1F), (int)(LINE_WIDTH_NO_MARGIN * 0.9F), Alignment.LEFT, "lapis_robinia_savana")
                    .addItemTexture(alignedToLeft(3).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.LAPIS_ROBINIA_LOG, false)
                    .addParagraph(5,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "lapis_robinia_savana_desc")
                    .addItemTexture(alignedToLeft(10).build(), 1.0F, AerialHellItems.LAPIS_ROBINIA_LOG, true)
                    .addItemTexture(centered(10).build(), 1.0F, AerialHellItems.LAPIS_ROBINIA_LEAVES, true)
                    .addItemTexture(alignedToRight(10).build(), 1.0F, () -> Items.LAPIS_LAZULI, true)
                    .addParagraph(18,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "dense_forest_biomes")
                    .addParagraph(20,MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.1F), (int)(LINE_WIDTH_NO_MARGIN * 0.9F), Alignment.LEFT, "copper_pine_biomes")
                    .addItemTexture(alignedToLeft(20).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.COPPER_PINE_LEAVES, false)
                    .addItemTexture(alignedToLeft(21).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.COPPER_PINE_LOG, false)
                    .addParagraph(23,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "copper_pine_biomes_desc")
                    .addTextureDisplay(alignedToRight(25).build(), 1.0F, "gui/guide_book/content/entities/kodama", 14, 22, "entity.aerialhell.kodama")
                    .addItemTexture(centered(26).build(), 1.0F, AerialHellItems.COPPER_PINE_CONE, false)
                    .addParagraph(28,MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.1F), (int)(LINE_WIDTH_NO_MARGIN * 0.9F), Alignment.LEFT, "stellar_jungle")
                    .addItemTexture(alignedToLeft(28).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.STELLAR_JUNGLE_TREE_LOG, false)
                    .addParagraph(30, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "stellar_jungle_desc"),
            new Page("environment_3", PAGE_TEXTURE,29)
                    .addParagraph(1,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "shroomy_biomes")
                    .addParagraph(3,MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.1F), (int)(LINE_WIDTH_NO_MARGIN * 0.9F), Alignment.LEFT, "shroomy_biomes_list")
                    .addItemTexture(alignedToLeft(3).centerVerticallyOnLine().build(), 0.7F, () -> Items.RED_MUSHROOM, false)
                    .addItemTexture(alignedToLeft(4).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.VERDIGRIS_AGARIC, false)
                    .addItemTexture(alignedToLeft(5).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.CORTINARIUS_VIOLACEUS, false)
                    .addParagraph(7,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "shroomy_biomes_desc")
                    .addTextureDisplay(alignedToLeft(12).build(), 1.0F, "gui/guide_book/content/entities/cortinarius_cow", 26, 26, "entity.aerialhell.cortinarius_cow")
                    .addTextureDisplay(centered(12).build(), 0.9F, "gui/guide_book/content/entities/verdigris_zombie", 20, 31, "entity.aerialhell.verdigris_zombie")
                    .addTextureDisplay(alignedToRight(12).build(), 1.0F, "gui/guide_book/content/entities/shroomboom", 18, 27, "entity.aerialhell.shroomboom")
                    .addParagraph(18,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "crystal_biomes")
                    .addParagraph(20,MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.1F), (int)(LINE_WIDTH_NO_MARGIN * 0.9F), Alignment.LEFT, "crystal_biomes_list")
                    .addItemTexture(alignedToLeft(20).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.CRYSTAL_BLOCK, false)
                    .addItemTexture(alignedToLeft(21).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.CRYSTALLIZED_LEAVES, false)
                    .addParagraph(23,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "crystal_biomes_desc")
                    .addItemTexture(alignedToLeft(28).build(), 0.9F, AerialHellItems.CRYSTAL, true)
                    .addItemTexture(centered(28).build(), 0.9F, AerialHellItems.CRYSTAL_BLOCK, true)
                    .addItemTexture(alignedToRight(28).build(),0.9F, AerialHellItems.STELLAR_CRYSTAL_PODZOL, true)
                    .addTextureDisplay(alignedToLeft(30).build(), 1.0F, "gui/guide_book/content/entities/crystal_caterpillar", 26, 22, "entity.aerialhell.crystal_caterpillar")
                    .addTextureDisplay(centered(30).build(), 1.0F, "gui/guide_book/content/entities/crystal_slime", 30, 30, "entity.aerialhell.crystal_slime")
                    .addTextureDisplay(alignedToRight(30).build(), 1.0F, "gui/guide_book/content/entities/crystal_spider", 30, 28, "entity.aerialhell.crystal_spider"),
            new Page("environment_4", PAGE_TEXTURE,30)
                    .addParagraph(1,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "desert_biomes")
                    .addParagraph(3,MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.1F), (int)(LINE_WIDTH_NO_MARGIN * 0.9F), Alignment.LEFT, "slippery_sand_ocean")
                    .addItemTexture(alignedToLeft(3).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.SLIPPERY_SAND, false)
                    .addParagraph(5,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "desert_biomes_desc")
                    .addItemTexture(centered(13).build(), 0.9F, AerialHellItems.SKY_CACTUS, true)
                    .addItemTexture(centered(15).build(),0.9F, AerialHellItems.PETRIFIED_AERIAL_TREE_LOG, true)
                    .addTextureDisplay(alignedToLeft(14).build(), 1.0F, "gui/guide_book/content/entities/venomous_snake", 22, 24, "entity.aerialhell.venomous_snake")
                    .addTextureDisplay(alignedToRight(14).build(), 1.0F, "gui/guide_book/content/entities/hell_spider", 32, 24, "entity.aerialhell.hell_spider")
                    .addParagraph(18,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "shadow_biomes")
                    .addParagraph(20,MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.1F), (int)(LINE_WIDTH_NO_MARGIN * 0.9F), Alignment.LEFT, "shadow_biomes_list")
                    .addItemTexture(alignedToLeft(20).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.SHADOW_GRASS_BLOCK, false)
                    .addItemTexture(alignedToLeft(21).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.SHADOW_PINE_LEAVES, false)
                    .addParagraph(23,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "shadow_biomes_desc")
                    .addItemTexture(alignedToLeft(28).build(), 0.9F, AerialHellItems.SHADOW_CRYSTAL, true)
                    .addItemTexture(centered(28).horizontalOffset(-20).build(), 0.9F, AerialHellItems.SHADOW_CRYSTAL_BLOCK, true)
                    .addItemTexture(alignedToRight(28).horizontalOffset(-42).build(),0.9F, AerialHellItems.SHADOW_BRAMBLES, true)
                    .addItemTexture(centered(30).build(),0.9F, AerialHellItems.SHADOW_SHARD, true)
                    .addTextureDisplay(alignedToLeft(30).build(), 1.0F, "gui/guide_book/content/entities/shadow_automaton", 16, 28, "entity.aerialhell.shadow_automaton")
                    .addTextureDisplay(alignedToRight(28).build(), 0.9F, "gui/guide_book/content/entities/shadow_troll", 39, 64, "entity.aerialhell.shadow_troll"),
            new Page("bestiary_1", PAGE_TEXTURE,31)
                    .addParagraph(1,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, "bestiary_section_title")
                    .addParagraph(2,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, "bestiary_section_quote")
                    .addParagraph(5,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, "bestiary_friendly")
                    .addParagraph(6,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "bestiary_friendly_list")
                    .addParagraph(11,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, "bestiary_neutral")
                    .addParagraph(12,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "bestiary_neutral_list")
                    .addParagraph(18,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, "bestiary_aggressive")
                    .addParagraph(19,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "bestiary_aggressive_list")
                    .addParagraph(19, MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.5F), (int)(LINE_WIDTH_NO_MARGIN * 0.5F), Alignment.LEFT, "bestiary_aggressive_list2"),
            new Page("bestiary_2", PAGE_TEXTURE,32)
                    .addParagraph(1,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "friendly")
                    .addParagraph(3,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "friendly_desc")
                    .addTextureDisplay(centered(8).horizontalOffset(1).build(), 1.0F, VERTICAL_SEPARATOR_9)
                    //left
                    .addTextureDisplay(alignedToLeft(8).horizontalOffset(25).build(), 1.0F, "gui/guide_book/content/entities/gliding_turtle", 32, 28, "entity.aerialhell.gliding_turtle")
                    .addParagraph(11,MAX_LINES_PER_TECHNICAL_PAGE - 1, 0, 80, Alignment.LEFT, "gliding_turtle")
                    .addParagraph(12,MAX_LINES_PER_TECHNICAL_PAGE - 1, 20, 60, Alignment.LEFT, "gliding_turtle_desc")
                    .addItemTexture(alignedToLeft(12).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.STELLAR_GRASS_BLOCK, false)
                    .addItemTexture(alignedToLeft(13).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.TURTLE_MEAT, false)
                    .addItemTexture(alignedToLeft(14).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.AERIAL_BERRY, true)
                    .addItemTexture(alignedToLeft(15).centerVerticallyOnLine().build(), 0.7F, () -> Items.FEATHER, false)

                    //right
                    .addTextureDisplay(alignedToRight(8).horizontalOffset(-40).build(), 1.1F, "gui/guide_book/content/entities/stellar_boar", 22, 15, "entity.aerialhell.stellar_boar")
                    .addParagraph(11,MAX_LINES_PER_TECHNICAL_PAGE - 1, 88, 80, Alignment.LEFT, "stellar_boar")
                    .addParagraph(12,MAX_LINES_PER_TECHNICAL_PAGE - 1, 108, 60, Alignment.LEFT, "stellar_boar_desc")
                    .addItemTexture(alignedToLeft(12).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.STELLAR_GRASS_BLOCK, false)
                    .addItemTexture(alignedToLeft(13).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, () -> Items.PORKCHOP, false)
                    .addItemTexture(alignedToLeft(14).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.AERIAL_BERRY, true)

                    //right page
                    .addTextureDisplay(centered(17).horizontalOffset(1).build(), 1.0F, VERTICAL_SEPARATOR_8)
                    //left
                    .addTextureDisplay(alignedToLeft(17).horizontalOffset(25).build(), 1.3F, "gui/guide_book/content/entities/stellar_chicken", 10, 12, "entity.aerialhell.stellar_chicken")
                    .addParagraph(20,MAX_LINES_PER_TECHNICAL_PAGE - 1, 0, 80, Alignment.LEFT, "stellar_chicken")
                    .addParagraph(21,MAX_LINES_PER_TECHNICAL_PAGE - 1, 20, 60, Alignment.LEFT, "stellar_chicken_desc")
                    .addItemTexture(alignedToLeft(21).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.STELLAR_GRASS_BLOCK, false)
                    .addItemTexture(alignedToLeft(22).centerVerticallyOnLine().build(), 0.7F, () -> Items.CHICKEN, false)
                    .addItemTexture(alignedToLeft(23).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.STELLAR_EGG, false)
                    .addItemTexture(alignedToLeft(24).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.STELLAR_WHEAT_SEEDS, true)

                    //right
                    .addTextureDisplay(alignedToRight(17).horizontalOffset(-40).build(), 1.2F, "gui/guide_book/content/entities/sandy_sheep", 20, 18, "entity.aerialhell.sandy_sheep")
                    .addParagraph(20,MAX_LINES_PER_TECHNICAL_PAGE - 1, 88, 80, Alignment.LEFT, "sandy_sheep")
                    .addParagraph(21,MAX_LINES_PER_TECHNICAL_PAGE - 1, 108, 60, Alignment.LEFT, "sandy_sheep_desc")
                    .addItemTexture(alignedToLeft(21).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.STELLAR_GRASS_BLOCK, false)
                    .addItemTexture(alignedToLeft(22).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, () -> Items.MUTTON, false)
                    .addItemTexture(alignedToLeft(23).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.AERIAL_BERRY, true)
                    .addItemTexture(alignedToLeft(24).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, () -> Items.YELLOW_WOOL, false)

                    .addParagraph(26,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "friendly_desc2")
                    .addParagraph(29,MAX_LINES_PER_TECHNICAL_PAGE - 1, (int)(LINE_WIDTH_NO_MARGIN * 0.2F), (int)(LINE_WIDTH_NO_MARGIN * 0.8F), Alignment.LEFT, "kodama")
                    .addTextureDisplay(alignedToLeft(29).build(), 1.3F, "gui/guide_book/content/entities/kodama", 14, 22, "entity.aerialhell.kodama")
                    .addItemTexture(alignedToLeft(30).horizontalOffset(20).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.STELLAR_JUNGLE_TREE_LEAVES, false)
                    .addItemStackTexture(alignedToLeft(31).horizontalOffset(20).centerVerticallyOnLine().build(), 0.7F, () -> ItemHelper.createPotionItemStack(Potions.INVISIBILITY), false)
                    .addItemTexture(alignedToLeft(33).horizontalOffset(20).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.AERIAL_BERRY, true),
            new Page("bestiary_3", PAGE_TEXTURE,33)
                    .addParagraph(1,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "neutral")
                    .addParagraph(3,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "neutral_desc")

                    .addTextureDisplay(centered(7).horizontalOffset(1).build(), 1.0F, VERTICAL_SEPARATOR_9)
                    //left
                    .addTextureDisplay(alignedToLeft(7).horizontalOffset(25).build(), 0.8F, "gui/guide_book/content/entities/forest_caterpillar", 26, 22, "entity.aerialhell.forest_caterpillar")
                    .addParagraph(9,MAX_LINES_PER_TECHNICAL_PAGE - 1, 0, 80, Alignment.LEFT, "forest_caterpillar")
                    .addParagraph(11,MAX_LINES_PER_TECHNICAL_PAGE - 1, 20, 60, Alignment.LEFT, "forest_caterpillar_desc")
                    .addItemTexture(alignedToLeft(11).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.STELLAR_GRASS_BLOCK, false)
                    .addItemStackTexture(alignedToLeft(12).centerVerticallyOnLine().build(), 0.7F, () -> ItemHelper.createPotionItemStack(Potions.POISON), false)

                    //right
                    .addTextureDisplay(alignedToRight(6).horizontalOffset(-30).build(), 0.95F, "gui/guide_book/content/entities/fat_phantom", 32, 28, "entity.aerialhell.fat_phantom")
                    .addParagraph(9,MAX_LINES_PER_TECHNICAL_PAGE - 1, 88, 80, Alignment.LEFT, "fat_phantom")
                    .addParagraph(10,MAX_LINES_PER_TECHNICAL_PAGE - 1, 108, 60, Alignment.LEFT, "fat_phantom_desc")
                    .addItemTexture(alignedToLeft(10).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.STELLAR_GRASS_BLOCK, false)
                    .addItemTexture(alignedToLeft(12).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.PHANTOM_MEAT, false)
                    .addItemStackTexture(alignedToLeft(13).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, () -> ItemHelper.createPotionItemStack(Potions.STRENGTH), true)
                    .addItemTexture(alignedToLeft(15).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, () -> Items.SUNFLOWER, true)

                    //- right page
                    //upper left
                    .addTextureDisplay(alignedToLeft(17).horizontalOffset(25).build(), 1.0F, "gui/guide_book/content/entities/cortinarius_cow", 26, 26, "entity.aerialhell.cortinarius_cow")
                    .addParagraph(20,MAX_LINES_PER_TECHNICAL_PAGE - 1, 0, 80, Alignment.LEFT, "cortinarius_cow")
                    .addParagraph(21,MAX_LINES_PER_TECHNICAL_PAGE - 1, 20, 60, Alignment.LEFT, "cortinarius_cow_desc")
                    .addItemTexture(alignedToLeft(21).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.MOSSY_STELLAR_COBBLESTONE, false)
                    .addItemTexture(alignedToLeft(22).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.ROTTEN_LEATHER, false)

                    .addParagraph(18,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "hostile")
                    .addParagraph(20,MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "hostile_desc"),
            new Page("bestiary_4", PAGE_TEXTURE,34)
                    .addTextureDisplay(centered(0).horizontalOffset(1).build(), 1.0F, VERTICAL_SEPARATOR_16)
                    .addTextureDisplay(alignedToLeft(8).verticalOffset(3).build(), 1.0F, HORIZONTAL_SEPARATOR)
                    .addTextureDisplay(centered(8).horizontalOffset(1).verticalOffset(-6).build(), 1.0F, CROSSING_POINT)
                    .addTextureDisplay(centered(17).horizontalOffset(1).build(), 1.0F, VERTICAL_SEPARATOR_16)
                    .addTextureDisplay(alignedToLeft(25).verticalOffset(3).build(), 1.0F, HORIZONTAL_SEPARATOR)
                    .addTextureDisplay(centered(25).horizontalOffset(1).verticalOffset(-6).build(), 1.0F, CROSSING_POINT)

                    //- left page
                    //upper left
                    .addTextureDisplay(alignedToLeft(0).horizontalOffset(25).build(), 1.0F, "gui/guide_book/content/entities/gliding_turtle", 32, 28, "entity.aerialhell.gliding_turtle")
                    .addParagraph(3,MAX_LINES_PER_TECHNICAL_PAGE - 1, 0, 80, Alignment.LEFT, "gliding_turtle")
                    .addParagraph(4,MAX_LINES_PER_TECHNICAL_PAGE - 1, 20, 60, Alignment.LEFT, "gliding_turtle_desc")
                    .addItemTexture(alignedToLeft(4).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.STELLAR_GRASS_BLOCK, false)
                    .addItemTexture(alignedToLeft(5).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.TURTLE_MEAT, false)

                    //upper right
                    .addTextureDisplay(alignedToRight(0).horizontalOffset(-40).build(), 1.2F, "gui/guide_book/content/entities/sandy_sheep", 20, 18, "entity.aerialhell.sandy_sheep")
                    .addParagraph(3,MAX_LINES_PER_TECHNICAL_PAGE - 1, 88, 80, Alignment.LEFT, "sandy_sheep")
                    .addParagraph(4,MAX_LINES_PER_TECHNICAL_PAGE - 1, 108, 60, Alignment.LEFT, "sandy_sheep_desc")
                    .addItemTexture(alignedToLeft(4).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.STELLAR_GRASS_BLOCK, false)
                    .addItemTexture(alignedToLeft(5).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, () -> Items.MUTTON, false)

                    //bottom left
                    .addTextureDisplay(alignedToLeft(9).horizontalOffset(25).build(), 1.1F, "gui/guide_book/content/entities/stellar_boar", 22, 15, "entity.aerialhell.stellar_boar")
                    .addParagraph(12,MAX_LINES_PER_TECHNICAL_PAGE - 1, 0, 80, Alignment.LEFT, "stellar_boar")
                    .addParagraph(13,MAX_LINES_PER_TECHNICAL_PAGE - 1, 20, 60, Alignment.LEFT, "stellar_boar_desc")
                    .addItemTexture(alignedToLeft(13).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.STELLAR_GRASS_BLOCK, false)
                    .addItemTexture(alignedToLeft(14).centerVerticallyOnLine().build(), 0.7F, () -> Items.PORKCHOP, false)

                    //bottom right
                    .addTextureDisplay(alignedToRight(9).horizontalOffset(-40).build(), 1.3F, "gui/guide_book/content/entities/stellar_chicken", 10, 12, "entity.aerialhell.stellar_chicken")
                    .addParagraph(12,MAX_LINES_PER_TECHNICAL_PAGE - 1, 88, 80, Alignment.LEFT, "stellar_chicken")
                    .addParagraph(13,MAX_LINES_PER_TECHNICAL_PAGE - 1, 108, 60, Alignment.LEFT, "stellar_chicken_desc")
                    .addItemTexture(alignedToLeft(13).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.STELLAR_GRASS_BLOCK, false)
                    .addItemTexture(alignedToLeft(14).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, () -> Items.CHICKEN, false)
                    .addItemTexture(alignedToLeft(15).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.STELLAR_EGG, false)

                    //- right page
                    //upper left
                    .addTextureDisplay(alignedToLeft(17).horizontalOffset(25).build(), 1.0F, "gui/guide_book/content/entities/cortinarius_cow", 26, 26, "entity.aerialhell.cortinarius_cow")
                    .addParagraph(20,MAX_LINES_PER_TECHNICAL_PAGE - 1, 0, 80, Alignment.LEFT, "cortinarius_cow")
                    .addParagraph(21,MAX_LINES_PER_TECHNICAL_PAGE - 1, 20, 60, Alignment.LEFT, "cortinarius_cow_desc")
                    .addItemTexture(alignedToLeft(21).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.MOSSY_STELLAR_COBBLESTONE, false)
                    .addItemTexture(alignedToLeft(22).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.ROTTEN_LEATHER, false)

                    //upper right
                    .addTextureDisplay(alignedToRight(17).horizontalOffset(-40).build(), 1.0F, "gui/guide_book/content/entities/evil_cow", 30, 30, "entity.aerialhell.evil_cow")
                    .addParagraph(20,MAX_LINES_PER_TECHNICAL_PAGE - 1, 88, 80, Alignment.LEFT, "evil_cow")
                    .addParagraph(21,MAX_LINES_PER_TECHNICAL_PAGE - 1, 108, 60, Alignment.LEFT, "evil_cow_desc")
                    .addItemTexture(alignedToLeft(21).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.MOSSY_STELLAR_COBBLESTONE, false)
                    .addItemTexture(alignedToLeft(22).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.ROTTEN_LEATHER, false)

                    //bottom left
                    .addTextureDisplay(alignedToLeft(26).horizontalOffset(25).build(), 1.1F, "gui/guide_book/content/entities/stellar_ent", 19, 21, "entity.aerialhell.stellar_ent")
                    .addParagraph(29,MAX_LINES_PER_TECHNICAL_PAGE - 1, 0, 80, Alignment.LEFT, "stellar_ent")
                    .addParagraph(30,MAX_LINES_PER_TECHNICAL_PAGE - 1, 20, 60, Alignment.LEFT, "stellar_ent_desc")
                    .addItemTexture(alignedToLeft(30).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.MOSSY_STELLAR_COBBLESTONE, false)
                    .addItemTexture(alignedToLeft(31).horizontalOffset(88).build(), 0.7F, AerialHellItems.AERIAL_TREE_LEAVES, false)
                    .addItemTexture(alignedToLeft(33).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.SKY_BOWL, false)

                    //bottom right
                    .addTextureDisplay(alignedToRight(26).horizontalOffset(-40).build(), 0.9F, "gui/guide_book/content/entities/verdigris_zombie", 20, 31, "entity.aerialhell.verdigris_zombie")
                    .addParagraph(29,MAX_LINES_PER_TECHNICAL_PAGE - 1, 88, 80, Alignment.LEFT, "verdigris_zombie")
                    .addParagraph(30,MAX_LINES_PER_TECHNICAL_PAGE - 1, 108, 60, Alignment.LEFT, "verdigris_zombie_desc")
                    .addItemTexture(alignedToLeft(30).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.MOSSY_STELLAR_COBBLESTONE, false)
                    .addItemTexture(alignedToLeft(32).horizontalOffset(88).centerVerticallyOnLine().build(), 0.7F, AerialHellItems.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK, false),
            new Page("bestiary_4", PAGE_TEXTURE,35)
                    .addTextureDisplay(alignedToLeft(1).build(), 2.0F, "entity/automaton/shadow_automaton", 8, 8, 8, 8, 64, 32, "entity.aerialhell.shadow_automaton")
                    .addTextureDisplay(alignedToRight(1).build(), 2.0F, "entity/automaton/stellar_stone_automaton", 8, 8, 8, 8, 64, 32, "entity.aerialhell.stellar_stone_automaton")
                    .addTextureDisplay(alignedToLeft(3).build(), 1.0F, "item/aerial_tree_chest_mimic", 16, 16, "entity.aerialhell.aerial_tree_mimic")
                    .addTextureDisplay(centered(3).build(), 1.0F, "item/copper_pine_chest_mimic", 16, 16, "entity.aerialhell.copper_pine_mimic")
                    .addTextureDisplay(alignedToRight(3).build(), 1.0F, "item/golden_beech_chest_mimic", 16, 16, "entity.aerialhell.golden_beech_mimic")
                    .addTextureDisplay(alignedToLeft(5).build(), 1.0F, "item/sky_cactus_fiber_chest_mimic",  16, 16, "entity.aerialhell.sky_cactus_fiber_mimic")
                    .addTextureDisplay(alignedToRight(5).build(), 1.0F, "item/shadow_pine_barrel_mimic", 16, 16, "entity.aerialhell.shadow_pine_mimic")
                    .addTextureDisplay(alignedToLeft(9).build(), 0.8F, "gui/guide_book/content/entities/crystal_golem", 34, 54, "entity.aerialhell.crystal_golem")
                    .addTextureDisplay(centered(9).build(), 1.0F, "gui/guide_book/content/entities/crystal_slime", 30, 30, "entity.aerialhell.crystal_slime")
                    .addTextureDisplay(alignedToRight(9).build(), 1.0F, "gui/guide_book/content/entities/fat_phantom", 32, 28, "entity.aerialhell.fat_phantom")
                    .addTextureDisplay(alignedToLeft(14).build(), 0.95F, "gui/guide_book/content/entities/worm", 28, 28, "entity.aerialhell.worm")
                    .addTextureDisplay(centered(14).build(), 1.0F, "gui/guide_book/content/entities/venomous_snake", 22, 24, "entity.aerialhell.venomous_snake")
                    .addTextureDisplay(alignedToRight(13).build(), 0.7F, "gui/guide_book/content/entities/aerial_tree_mimic", 32, 50, "entity.aerialhell.aerial_tree_mimic")
                    .addTextureDisplay(alignedToLeft(18).build(), 1.0F, "gui/guide_book/content/entities/cortinarius_cow", 26, 26, "entity.aerialhell.cortinarius_cow")
                    .addTextureDisplay(centered(18).build(), 1.0F, "gui/guide_book/content/entities/evil_cow", 30, 30, "entity.aerialhell.evil_cow")
                    .addTextureDisplay(alignedToRight(18).build(), 1.0F, "gui/guide_book/content/entities/crystal_spider", 30, 28, "entity.aerialhell.crystal_spider")
                    .addTextureDisplay(alignedToLeft(21).build(), 1.0F, "gui/guide_book/content/entities/forest_caterpillar", 26, 22, "entity.aerialhell.forest_caterpillar")
                    .addTextureDisplay(centered(21).build(), 1.0F, "gui/guide_book/content/entities/crystal_caterpillar", 26, 22, "entity.aerialhell.crystal_caterpillar")
                    .addTextureDisplay(alignedToRight(21).build(), 1.0F, "gui/guide_book/content/entities/hell_spider", 32, 24, "entity.aerialhell.hell_spider")
                    .addTextureDisplay(alignedToLeft(24).build(), 1.0F, "gui/guide_book/content/entities/shadow_pine_mimic", 32, 20, "entity.aerialhell.shadow_pine_mimic")
                    .addTextureDisplay(centered(24).build(), 1.2F, "gui/guide_book/content/entities/sandy_sheep", 20, 18, "entity.aerialhell.sandy_sheep")
                    .addTextureDisplay(alignedToRight(24).build(), 1.0F, "gui/guide_book/content/entities/torn_spirit", 24, 32, "entity.aerialhell.torn_spirit")
                    .addTextureDisplay(alignedToLeft(27).build(), 1.0F, "gui/guide_book/content/entities/flying_jellyfish", 28, 30, "entity.aerialhell.flying_jellyfish")
                    .addTextureDisplay(centered(27).build(), 1.1F, "gui/guide_book/content/entities/slime_pirate", 20, 24, "entity.aerialhell.slime_pirate")
                    .addTextureDisplay(alignedToRight(27).build(), 1.0F, "gui/guide_book/content/entities/ghost_slime_pirate", 23, 28, "entity.aerialhell.ghost_slime_pirate")
                    .addTextureDisplay(alignedToLeft(31).build(), 1.0F, "gui/guide_book/content/entities/fire_spirit", 18, 24, "entity.aerialhell.fire_spirit")
                    .addTextureDisplay(centered(30).build(), 1.0F, "gui/guide_book/content/entities/ice_spirit", 20, 30, "entity.aerialhell.ice_spirit")
                    .addTextureDisplay(alignedToRight(30).build(), 1.0F, "gui/guide_book/content/entities/electro_spirit", 18, 22, "entity.aerialhell.electro_spirit"),
            new Page("bestiary_5", PAGE_TEXTURE,36)
                    .addTextureDisplay(alignedToLeft(1).build(), 1.3F, "gui/guide_book/content/entities/kodama", 14, 22, "entity.aerialhell.kodama")
                    .addTextureDisplay(centered(1).build(), 1.1F, "gui/guide_book/content/entities/mud_skeletron", 18, 27, "entity.aerialhell.mud_skeletron")
                    .addTextureDisplay(alignedToRight(1).build(), 1.1F, "gui/guide_book/content/entities/mummy", 18, 23, "entity.aerialhell.mummy")
                    .addTextureDisplay(alignedToLeft(4).build(), 1.0F, "gui/guide_book/content/entities/verdigris_zombie", 20, 31, "entity.aerialhell.verdigris_zombie")
                    .addTextureDisplay(centered(4).build(), 1.0F, "gui/guide_book/content/entities/mud_golem", 27, 33, "entity.aerialhell.mud_golem")
                    .addTextureDisplay(alignedToRight(4).build(), 1.0F, "gui/guide_book/content/entities/shroomboom", 18, 27, "entity.aerialhell.shroomboom")
                    .addTextureDisplay(alignedToLeft(8).build(), 1.1F, "gui/guide_book/content/entities/stellar_ent", 19, 21, "entity.aerialhell.stellar_ent")
                    .addTextureDisplay(centered(8).build(), 1.1F, "gui/guide_book/content/entities/stellar_boar", 22, 15, "entity.aerialhell.stellar_boar")
                    .addTextureDisplay(alignedToRight(7).build(), 1.3F, "gui/guide_book/content/entities/stellar_chicken", 10, 12, "entity.aerialhell.stellar_chicken")
                    .addTextureDisplay(alignedToLeft(11).build(), 1.0F, "gui/guide_book/content/entities/volucite_golem", 26, 36, "entity.aerialhell.volucite_golem")
                    .addTextureDisplay(centered(10).build(), 1.0F, "gui/guide_book/content/entities/shadow_flying_skull", 30, 12, "entity.aerialhell.shadow_flying_skull")
                    .addTextureDisplay(alignedToRight(10).build(), 1.0F, "gui/guide_book/content/entities/shadow_troll", 39, 64, "entity.aerialhell.shadow_troll")
                    .addTextureDisplay(centered(13).build(), 1.0F, "gui/guide_book/content/entities/shadow_automaton", 16, 28, "entity.aerialhell.shadow_automaton"),
            new Page("bosses_1", PAGE_TEXTURE,37)
                    .addTextureDisplay(alignedToLeft(18).build(), 1.0F, "gui/guide_book/content/entities/chained_god", 42, 42, "entity.aerialhell.chained_god")
                    .addTextureDisplay(alignedToRight(18).build(), 1.0F, "gui/guide_book/content/entities/mud_cycle_mage", 30, 30, "entity.aerialhell.mud_cycle_mage")
                    .addTextureDisplay(centered(23).build(), 1.0F, "gui/guide_book/content/entities/lunar_priest", 32, 32, "entity.aerialhell.lunatic_priest")
                    .addTextureDisplay(alignedToLeft(28).build(), 1.0F, "gui/guide_book/content/entities/lilith", 32, 32, "entity.aerialhell.lilith")
                    .addTextureDisplay(alignedToRight(28).build(), 1.0F, "gui/guide_book/content/entities/lilith_evil", 32, 32, "entity.aerialhell.lilith")
    );

    private static ElementPositionInfo.Builder posBuilder() {return new ElementPositionInfo.Builder();}
    private static ElementPositionInfo.Builder alignedToLeft(int lineIndex) {return posBuilder().lineIndex(lineIndex).alignment(Alignment.LEFT);}
    private static ElementPositionInfo.Builder alignedToRight(int lineIndex) {return posBuilder().lineIndex(lineIndex).alignment(Alignment.RIGHT);}
    private static ElementPositionInfo.Builder centered(int lineIndex) {return posBuilder().lineIndex(lineIndex).alignment(Alignment.CENTER);}

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
            this.lines.add(new Line(lineIndex, LINE_HEIGHT, isLeftPageLine ? this.leftPageLineX : this.rightPageLineX, isLeftPageLine ? this.leftPageCenterX : this.rightPageCenterX, isLeftPageLine ? this.leftPageLineX + LINE_WIDTH_NO_MARGIN : this.rightPageLineX + LINE_WIDTH_NO_MARGIN, this.firstLineY + (lineIndex % MAX_LINES_PER_VISUAL_PAGE) * LINE_HEIGHT));
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
                .add("materials", 14)
                .add("effects", 21)
                .add("enchanting", 25)
                .add("environment", 27);

        this.rightTabs = new TabList(false, () -> this.bookLeft, () -> this.bookTop)
                .add("bestiary", 31)
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