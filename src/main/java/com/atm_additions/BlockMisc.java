package com.atm_additions;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMisc extends Block {
    public BlockMisc(String name) {
        super(Material.ROCK);
        this.setRegistryName(name.toLowerCase());
        this.setUnlocalizedName(ATMAdditions.MODID + "." + name);
        this.setCreativeTab(ATMAdditions.creativeTab);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(
                Item.getItemFromBlock(this), 0,
                new ModelResourceLocation(getRegistryName(), "inventory")
        );
    }
}
