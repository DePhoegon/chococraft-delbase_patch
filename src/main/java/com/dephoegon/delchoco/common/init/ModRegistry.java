package com.dephoegon.delchoco.common.init;

import com.dephoegon.delchoco.DelChoco;
import com.dephoegon.delchoco.common.blockentities.ChocoboEggBlockEntity;
import com.dephoegon.delchoco.common.blockentities.ChocoboNestBlockEntity;
import com.dephoegon.delchoco.common.blocks.ChocoboEggBlock;
import com.dephoegon.delchoco.common.blocks.GysahlGreenBlock;
import com.dephoegon.delchoco.common.blocks.StrawNestBlock;
import com.dephoegon.delchoco.common.entities.properties.ChocoboColor;
import com.dephoegon.delchoco.common.items.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.dephoegon.delchoco.DelChoco.creativeTab;
import static com.dephoegon.delchoco.common.entities.Chocobo.tier_one_chocobo_inv_slot_count;
import static com.dephoegon.delchoco.common.entities.Chocobo.tier_two_chocobo_inv_slot_count;

@SuppressWarnings("unused")
public class ModRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DelChoco.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DelChoco.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, DelChoco.MOD_ID);

    public static final RegistryObject<Block> GYSAHL_GREEN = BLOCKS.register("gysahl_green", () ->
            new GysahlGreenBlock(Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));

    public static final RegistryObject<Block> STRAW_NEST = BLOCKS.register("straw_nest", () ->
            new StrawNestBlock(Properties.of(Material.STONE).sound(SoundType.GRASS)));

    public static final RegistryObject<Block> CHOCOBO_EGG = BLOCKS.register("chocobo_egg", () ->
            new ChocoboEggBlock(Properties.of(Material.EGG).strength(0.5F).noOcclusion().sound(SoundType.GRASS)));


    public static final RegistryObject<BlockEntityType<ChocoboNestBlockEntity>> STRAW_NEST_TILE = BLOCK_ENTITIES.register("chocobo_nest", () -> BlockEntityType.Builder.of(
            ChocoboNestBlockEntity::new, STRAW_NEST.get()).build(null));

    public static final RegistryObject<BlockEntityType<ChocoboEggBlockEntity>> CHOCOBO_EGG_TILE = BLOCK_ENTITIES.register("chocobo_egg", () -> BlockEntityType.Builder.of(
            ChocoboEggBlockEntity::new, CHOCOBO_EGG.get()).build(null));


    public static final RegistryObject<Item> CHOCOBO_SADDLE = ITEMS.register("chocobo_saddle", () -> new ChocoboSaddleItem(itemBuilder(), 0));
    public static final RegistryObject<Item> CHOCOBO_SADDLE_BAGS = ITEMS.register("chocobo_saddle_bags", () -> new ChocoboSaddleItem(itemBuilder(), tier_one_chocobo_inv_slot_count));
    public static final RegistryObject<Item> CHOCOBO_SADDLE_PACK = ITEMS.register("chocobo_saddle_pack", () -> new ChocoboSaddleItem(itemBuilder(), tier_two_chocobo_inv_slot_count));

    public static final RegistryObject<Item> YELLOW_CHOCOBO_SPAWN_EGG = ITEMS.register("yellow_chocobo_spawn_egg", () -> new ChocoboSpawnEggItem(itemBuilder(), ChocoboColor.YELLOW));
    public static final RegistryObject<Item> GREEN_CHOCOBO_SPAWN_EGG = ITEMS.register("green_chocobo_spawn_egg", () -> new ChocoboSpawnEggItem(itemBuilder(), ChocoboColor.GREEN));
    public static final RegistryObject<Item> BLUE_CHOCOBO_SPAWN_EGG = ITEMS.register("blue_chocobo_spawn_egg", () -> new ChocoboSpawnEggItem(itemBuilder(), ChocoboColor.BLUE));
    public static final RegistryObject<Item> WHITE_CHOCOBO_SPAWN_EGG = ITEMS.register("white_chocobo_spawn_egg", () -> new ChocoboSpawnEggItem(itemBuilder(), ChocoboColor.WHITE));
    public static final RegistryObject<Item> BLACK_CHOCOBO_SPAWN_EGG = ITEMS.register("black_chocobo_spawn_egg", () -> new ChocoboSpawnEggItem(itemBuilder(), ChocoboColor.BLACK));
    public static final RegistryObject<Item> GOLD_CHOCOBO_SPAWN_EGG = ITEMS.register("gold_chocobo_spawn_egg", () -> new ChocoboSpawnEggItem(itemBuilder(), ChocoboColor.GOLD));
    public static final RegistryObject<Item> PINK_CHOCOBO_SPAWN_EGG = ITEMS.register("pink_chocobo_spawn_egg", () -> new ChocoboSpawnEggItem(itemBuilder(), ChocoboColor.PINK));
    public static final RegistryObject<Item> RED_CHOCOBO_SPAWN_EGG = ITEMS.register("red_chocobo_spawn_egg", () -> new ChocoboSpawnEggItem(itemBuilder(), ChocoboColor.RED));
    public static final RegistryObject<Item> PURPLE_CHOCOBO_SPAWN_EGG = ITEMS.register("purple_chocobo_spawn_egg", () -> new ChocoboSpawnEggItem(itemBuilder(), ChocoboColor.PURPLE));
    public static final RegistryObject<Item> FLAME_CHOCOBO_SPAWN_EGG = ITEMS.register("flame_chocobo_spawn_egg", () -> new ChocoboSpawnEggItem(itemBuilder(), ChocoboColor.FLAME));

    public static final RegistryObject<Item> GYSAHL_GREEN_SEEDS = ITEMS.register("gysahl_green_seeds", () -> new CustomBlockNamedItem(GYSAHL_GREEN, itemBuilder()));

    public static final RegistryObject<Item> GYSAHL_GREEN_ITEM = ITEMS.register("gysahl_green", () -> new Item(itemBuilder().food(ModFoods.GYSAHL_GREEN)));
    public static final RegistryObject<Item> CHOCOBO_WHISTLE = ITEMS.register("chocobo_whistle", () -> new Item(itemBuilder()));
    public static final RegistryObject<Item> CHOCOBO_FEATHER = ITEMS.register("chocobo_feather", () -> new ChocoboSpawnerItemHelper(itemBuilder()));
    public static final RegistryObject<Item> LOVELY_GYSAHL_GREEN = ITEMS.register("lovely_gysahl_green", () -> new Item(itemBuilder()));

    public static final RegistryObject<Item> CHOCOBO_DRUMSTICK_RAW = ITEMS.register("chocobo_drumstick_raw", () -> new Item(itemBuilder().food(ModFoods.CHOCOBO_DRUMSTICK_RAW)));
    public static final RegistryObject<Item> CHOCOBO_DRUMSTICK_COOKED = ITEMS.register("chocobo_drumstick_cooked", () -> new Item(itemBuilder().food(ModFoods.CHOCOBO_DRUMSTICK_COOKED)));
    public static final RegistryObject<Item> PICKLED_GYSAHL_RAW = ITEMS.register("pickled_gysahl_raw", () -> new Item(itemBuilder().food(ModFoods.PICKLED_GYSAHL_RAW)));
    public static final RegistryObject<Item> PICKLED_GYSAHL_COOKED = ITEMS.register("pickled_gysahl_cooked", () -> new Item(itemBuilder().food(ModFoods.PICKLED_GYSAHL_COOKED)));

    public static final RegistryObject<Item> CHOCOPEDIA = ITEMS.register("chocopedia", () -> new ChocopediaItem(itemBuilder()));
    public static final RegistryObject<Item> GYSAHL_CAKE = ITEMS.register("gysahl_cake", () -> new Item(itemBuilder().stacksTo(8)));

    public static final RegistryObject<Item> CHOCO_DISGUISE_HELMET = ITEMS.register("choco_disguise_helmet", () -> new ChocoDisguiseItem(ModArmorMaterial.CHOCO_DISGUISE, EquipmentSlot.HEAD, itemBuilder()));
    public static final RegistryObject<Item> CHOCO_DISGUISE_CHESTPLATE = ITEMS.register("choco_disguise_chestplate", () -> new ChocoDisguiseItem(ModArmorMaterial.CHOCO_DISGUISE, EquipmentSlot.CHEST, itemBuilder()));
    public static final RegistryObject<Item> CHOCO_DISGUISE_LEGGINGS = ITEMS.register("choco_disguise_leggings", () -> new ChocoDisguiseItem(ModArmorMaterial.CHOCO_DISGUISE, EquipmentSlot.LEGS, itemBuilder()));
    public static final RegistryObject<Item> CHOCO_DISGUISE_BOOTS = ITEMS.register("choco_disguise_boots", () -> new ChocoDisguiseItem(ModArmorMaterial.CHOCO_DISGUISE, EquipmentSlot.FEET, itemBuilder()));

    //Regular block items
    public static final RegistryObject<Item> STRAW_NEST_ITEM = ITEMS.register("straw_nest", () -> new BlockItem(STRAW_NEST.get(), itemBuilder()));
    public static final RegistryObject<Item> CHOCOBO_EGG_ITEM = ITEMS.register("chocobo_egg", () -> new ChocoboEggBlockItem(CHOCOBO_EGG.get(), itemBuilder()));


    private static Item.Properties itemBuilder() {
        return new Item.Properties().tab(creativeTab);
    }
}
