package com.github.allthemods.atmadditions.init;

import com.github.allthemods.atmadditions.ATMAdditions;
import com.github.allthemods.atmadditions.item.ATMStarItem;
import com.github.allthemods.atmadditions.item.WithersCompassItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.system.CallbackI;

public class ModRegistry
{
    //public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ATMAdditions.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ATMAdditions.MODID);

    //Blocks


    //Items
    public static final RegistryObject<Item> ATM_STAR = ITEMS.register("atm_star", () -> new ATMStarItem());
    public static final RegistryObject<Item> ATM_STAR_SHARD = ITEMS.register("atm_star_shard", () -> new ATMStarItem());
    public static final RegistryObject<Item> PHILOSOPHERS_FUEL = ITEMS.register("philosophers_fuel", () -> new Item(new Item.Properties().group(ModItemGroups.ATM_ADDITIONS)));
    public static final RegistryObject<Item> NEXIUM_EMITTER = ITEMS.register("nexium_emitter", () -> new Item(new Item.Properties().group(ModItemGroups.ATM_ADDITIONS)));
    public static final RegistryObject<Item> DRAGON_SOUL = ITEMS.register("dragon_soul", () -> new Item(new Item.Properties().group(ModItemGroups.ATM_ADDITIONS)));
    public static final RegistryObject<Item> WITHERS_COMPASS = ITEMS.register("withers_compass", () -> new WithersCompassItem());
    public static final RegistryObject<Item> PULSATING_BLACK_HOLE = ITEMS.register("pulsating_black_hole", () -> new Item(new Item.Properties().group(ModItemGroups.ATM_ADDITIONS)));
    public static final RegistryObject<Item> OBLIVION_SHARD = ITEMS.register("oblivion_shard", () -> new Item(new Item.Properties().group(ModItemGroups.ATM_ADDITIONS)));
    public static final RegistryObject<Item> IMPROBABLE_PROBABILITY_DEVICE = ITEMS.register("improbable_probability_device", () -> new Item(new Item.Properties().group(ModItemGroups.ATM_ADDITIONS)));
    public static final RegistryObject<Item> DIMENSIONAL_SEED = ITEMS.register("dimensional_seed", () -> new Item(new Item.Properties().group(ModItemGroups.ATM_ADDITIONS)));

    public static void register()
    {
       //BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
