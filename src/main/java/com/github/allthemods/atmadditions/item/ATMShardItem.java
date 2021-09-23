package com.github.allthemods.atmadditions.item;

import com.github.allthemods.atmadditions.init.ModItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ATMShardItem extends Item
{
    public ATMShardItem()
    {
        super(new Properties().group(ModItemGroups.ATM_ADDITIONS).maxStackSize(5));
    }

    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }
}
