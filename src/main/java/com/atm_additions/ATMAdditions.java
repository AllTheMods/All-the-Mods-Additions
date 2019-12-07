package com.atm_additions;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

@Mod(modid = ATMAdditions.MODID, name = ATMAdditions.MODNAME, version = ATMAdditions.MODVERSION, dependencies = ATMAdditions.DEPENDS)
public class ATMAdditions {
    public static final String MODID = "atm_additions";
    public static final String MODNAME = "All the Mods Additions";
    public static final String MODVERSION = "@VERSION@";
    public static final String DEPENDS = "after:tconstruct";
    public static final CreativeTabs creativeTab = new CreativeTabATMAdditions(MODID);

    public static Logger logger;

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @Mod.EventHandler
    public synchronized void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        Registry.register();
        if (event.getSide() == Side.CLIENT){
            Registry.registerModels();
        }
    }
}
