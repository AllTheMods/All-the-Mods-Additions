package com.atm_additions;

import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class Registry {

    static List<Item> toRegisterItem = new ArrayList<>();
    static List<Block> toRegisterBlock = new ArrayList<>();
    static List<FluidMisc> toRegisterFluids = new ArrayList<>();
    static List<FluidBlockMisc> toRegisterFluidBlocks = new ArrayList<>();

    public static final BlockMisc infusionBlock = createBlock(new BlockMisc("infusion_block"));
    public static final BlockMisc magmaSoakedCobblestone = createBlock(new BlockMisc("magma_soaked_cobblestone"));

    public static final ItemMisc coldIronIngot = createItem(new ItemMisc("coldIronIngot"));
    public static final ItemMisc coldIronPlate = createItem(new ItemMisc("coldIronPlate"));
    public static final ItemMisc corruptedStarmetal = createItem(new ItemMisc("corruptedStarmetal"));
    public static final ItemMisc crystallisedBlood = createItem(new ItemMisc("crystallisedBlood"));
    public static final ItemMisc elementiumPrint = createItem(new ItemMisc("elementiumPrint"));
    public static final ItemMisc elementiumProcessor = createItem(new ItemMisc("elementiumProcessor"));
    public static final ItemMisc lonsdale = createItem(new ItemMisc("lonsdale"));
    public static final ItemMisc manaDiamondPlate = createItem(new ItemMisc("manaDiamondPlate"));
    public static final ItemMisc manaSteelPlate = createItem(new ItemMisc("manaSteelPlate"));
    public static final ItemMisc planetShard = createItem(new ItemMisc("planetShard"));
    public static final ItemMisc quartzIngot = createItem(new ItemMisc("quartzIngot"));
    public static final ItemMisc quartzPlate = createItem(new ItemMisc("quartzPlate"));
    public static final ItemMisc tier1 = createItem(new ItemMisc("tier1"));
    public static final ItemMisc tier2 = createItem(new ItemMisc("tier2"));
    public static final ItemMisc tier3 = createItem(new ItemMisc("tier3"));
    public static final ItemMisc tier4 = createItem(new ItemMisc("tier4"));
    public static final ItemMisc tier5 = createItem(new ItemMisc("tier5"));
    public static final ItemMisc tier6 = createItem(new ItemMisc("tier6"));
    public static final ItemMisc unrefinedHotSteelIngot = createItem(new ItemMisc("unrefinedHotSteelIngot"));

    public static final FluidMisc corruptedStarlight = createFluid(new FluidMisc("corrupted_starlight", false, MapColor.RED));
    public static final FluidMisc grout = createFluid(new FluidMisc("grout", true, MapColor.GRAY));
    public static final FluidMisc solderingAlloy = createFluid(new FluidMisc("soldering_alloy", true, MapColor.SILVER));
    public static final FluidMisc vitrifiedSand = createFluid(new FluidMisc("vitrified_sand", true, MapColor.SAND));

    public static <T extends Block> T createBlock(T block) {
        toRegisterBlock.add(block);
        return block;
    }

    public static <T extends Item> T createItem(T item) {
        toRegisterItem.add(item);
        return item;
    }

    public static <T extends FluidMisc> T createFluid(T fluid){
        toRegisterFluids.add(fluid);
        return fluid;
    }

    public static void register() {
        for (Item item : toRegisterItem) {
            ForgeRegistries.ITEMS.register(item);
        }
        for (Block block : toRegisterBlock){
            ForgeRegistries.BLOCKS.register(block);
            ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(),
                    "Block %s has null registry name", block);
            ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(registryName).setCreativeTab(ATMAdditions.creativeTab));
        }
        for (FluidMisc fluid : toRegisterFluids){
            FluidRegistry.registerFluid(fluid);
            FluidRegistry.addBucketForFluid(fluid);
            FluidBlockMisc block = new FluidBlockMisc(fluid, fluid.getName());
            toRegisterFluidBlocks.add(block);
            ForgeRegistries.BLOCKS.register(block);
            ItemBlock itemBlock = new ItemBlock(block);
            ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(),
                    "Block %s has null registry name", block);
            itemBlock.setRegistryName(registryName);
            ForgeRegistries.ITEMS.register(itemBlock);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        for (Item item : toRegisterItem) {
            ((ItemMisc)item).initModel();
        }
        for (Block block : toRegisterBlock){
            ((BlockMisc)block).initModel();
        }
        for (FluidBlockMisc block : toRegisterFluidBlocks){
            block.initModels();
        }
    }
}
