package com.atm_additions;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FluidBlockMisc extends BlockFluidClassic {
    String name;
    public FluidBlockMisc(FluidMisc fluid,  MapColor mapColor, String name) {
        super(fluid, getMaterialType(fluid), mapColor);
        this.name = name;
    }

    public static Material getMaterialType(FluidMisc fluid) {
        if (fluid.isMolten()) {
            return Material.LAVA;
        }
        return Material.WATER;
    }

    @SideOnly(Side.CLIENT)
    public void registerModels() {

        Item item = Item.getItemFromBlock(this);
        StateMapper mapper = new StateMapper(ATMAdditions.MODID, "fluid", name);

        // Item Model
        ModelBakery.registerItemVariants(item);
        ModelLoader.setCustomMeshDefinition(item, mapper);
        // Block Model
        ModelLoader.setCustomStateMapper(this, mapper);
    }
}
