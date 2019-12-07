package com.atm_additions;

import net.minecraft.block.material.MapColor;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.Fluid;

import java.awt.*;

public class FluidMisc extends Fluid {
    protected static SoundEvent emptySound = SoundEvents.ITEM_BUCKET_EMPTY;
    protected static SoundEvent fillSound = SoundEvents.ITEM_BUCKET_FILL;
    boolean molten;
    MapColor mapColor;
    public FluidMisc(String fluidName, boolean molten, MapColor mapColor) {
        super(fluidName, new ResourceLocation(ATMAdditions.MODID, "fluids/" + fluidName + "_still"), new ResourceLocation(ATMAdditions.MODID, "fluids/" + fluidName + "_flowing"), Color.WHITE);
        this.molten = molten;
        this.temperature = calculateTemperature();
        this.mapColor = mapColor;
        if (molten){
            this.setViscosity(6000);
            this.setDensity(3000);
        }
    }

    public MapColor getMapColor() {
        return mapColor;
    }

    public int calculateTemperature() {
        if (this.molten) {
            return 2000;
        }
        return 300;
    }

    public boolean isMolten() {
        return molten;
    }

    @Override
    public FluidMisc setEmptySound(SoundEvent parSound) {
        emptySound = parSound;
        return this;
    }

    @Override
    public SoundEvent getEmptySound() {
        return emptySound;
    }

    @Override
    public FluidMisc setFillSound(SoundEvent parSound) {
        fillSound = parSound;
        return this;
    }

    @Override
    public SoundEvent getFillSound() {
        return fillSound;
    }

}
