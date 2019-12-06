package com.atm_additions;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;

public class FluidBlockMisc extends BlockFluidClassic {
    public FluidBlockMisc(FluidMisc fluid,  MapColor mapColor) {
        super(fluid, getMaterialType(fluid), mapColor);
    }

    public static Material getMaterialType(FluidMisc fluid) {
        if (fluid.isMolten()) {
            return Material.LAVA;
        }
        return Material.WATER;
    }
}
