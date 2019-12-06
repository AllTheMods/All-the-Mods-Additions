package com.atm_additions;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabATMAdditions extends CreativeTabs {
    public CreativeTabATMAdditions(String label){
        super(label);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
        return new ItemStack(Registry.coldIronPlate);
    }
}
