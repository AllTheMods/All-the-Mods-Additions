package com.atm_additions;

import net.minecraftforge.fluids.Fluid;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.BowStringMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.IMaterialStats;
import slimeknights.tconstruct.library.materials.Material;

public class TiCCompat {

    public static void initMaterials() {
        registerMaterial("vitrified_sand", 0xf7e4a1, true, true, Registry.vitrifiedSand, new HeadMaterialStats(400, 5.5F, 5.5F, 2), new HandleMaterialStats(0.5F, 400), new BowStringMaterialStats(0.5F));
        registerMaterial("soldering_alloy", 0xbbbec0, true, true, Registry.solderingAlloy, new HeadMaterialStats(1000, 7.5F, 7.5F, 3), new HandleMaterialStats(0.7F, 500), new BowStringMaterialStats(0.5F));
    }

    protected static void registerMaterial(String name, int color, boolean castable, boolean craftable, Fluid fluid, IMaterialStats stats, IMaterialStats... stats2) {
        Material mat = new Material(name, color)
                .setCastable(castable).setCraftable(craftable);
        mat.setFluid(fluid);

        TinkerRegistry.addMaterialStats(mat, stats, stats2);
        TinkerRegistry.addMaterial(mat);
        TinkerRegistry.integrate(mat, fluid);
    }
}
