package com.github.allthemods.atmadditions;

import com.github.allthemods.atmadditions.init.ModRegistry;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ATMAdditions.MODID)
public class ATMAdditions
{
    public static final String MODID = "atmadditions";
    public static final Logger LOGGER = LogManager.getLogger();

    public ATMAdditions()
    {
        LOGGER.debug("All the hellos from All The Mods: Additions!");

        ModRegistry.register();
    }
}
