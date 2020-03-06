package com.github.allthemods.atmadditions.item;

import com.github.allthemods.atmadditions.init.ModItemGroups;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class WithersCompassItem extends Item
{
    public WithersCompassItem()
    {
        super(new Properties().group(ModItemGroups.ATM_ADDITIONS));
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if(isSelected)
        {
            if (entityIn instanceof LivingEntity)
            {
                ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.WITHER, 200));
            }
        }
    }
}
