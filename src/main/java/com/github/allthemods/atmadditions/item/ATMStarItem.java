package com.github.allthemods.atmadditions.item;

import com.github.allthemods.atmadditions.init.ModItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ATMStarItem extends Item
{
    public ATMStarItem()
    {
        super(new Properties().group(ModItemGroups.ATM_ADDITIONS));
    }

    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }
}
