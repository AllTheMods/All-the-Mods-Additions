package com.github.allthemods.atmadditions.init;

import com.github.allthemods.atmadditions.ATMAdditions;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.util.function.Supplier;

public class ModItemGroups
{
    public static final ItemGroup ATM_ADDITIONS = new ModItemGroup(ATMAdditions.MODID, () -> new ItemStack(ModRegistry.ATM_STAR.get()));

    public static final class ModItemGroup extends ItemGroup
    {
        private final Supplier<ItemStack> iconSupplier;

        public ModItemGroup(final String name, final Supplier<ItemStack> iconSupplier)
        {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        public ItemStack createIcon()
        {
            return iconSupplier.get();
        }
    }
}
