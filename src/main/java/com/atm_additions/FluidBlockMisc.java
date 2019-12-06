package com.atm_additions;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public class FluidBlockMisc extends BlockFluidClassic {
    String name;
    FluidMisc fluid;
    public FluidBlockMisc(FluidMisc fluid, String name) {
        super(fluid, getMaterialType(fluid), fluid.getMapColor());
        this.setRegistryName(name.toLowerCase() + "_fluidblock");
        this.setUnlocalizedName(ATMAdditions.MODID + "." + name);
        this.setCreativeTab(ATMAdditions.creativeTab);
        this.name = name;
        this.fluid = fluid;
    }

    public static Material getMaterialType(FluidMisc fluid) {
        if (fluid.isMolten()) {
            return Material.LAVA;
        }
        return Material.WATER;
    }

    @Override
    public int tickRate(World worldIn) {
        if (fluid.isMolten()){
            return worldIn.provider.isNether() ? 10 : 30;
        }
        return super.tickRate(worldIn);
    }

    @Override
    public void updateTick(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state,
                           @Nonnull Random rand) {
        if (fluid.isMolten()){
            int quantaRemaining = quantaPerBlock - state.getValue(LEVEL);
            int expQuanta = -101;
            // check adjacent block levels if non-source
            if (quantaRemaining < quantaPerBlock) {
                int adjacentSourceBlocks = 0;
                if (ForgeEventFactory.canCreateFluidSource(world, pos, state, canCreateSources)) {
                    for (EnumFacing side : EnumFacing.Plane.HORIZONTAL) {
                        if (isSourceBlock(world, pos.offset(side)))
                            adjacentSourceBlocks++;
                    }
                }
                // new source block
                if (adjacentSourceBlocks >= 2 && (world.getBlockState(pos.up(densityDir)).getMaterial().isSolid()
                        || isSourceBlock(world, pos.up(densityDir)))) {
                    expQuanta = quantaPerBlock;
                }
                // unobstructed flow from 'above'
                else if (world.getBlockState(pos.down(densityDir)).getBlock() == this
                        || hasDownhillFlow(world, pos, EnumFacing.EAST) || hasDownhillFlow(world, pos, EnumFacing.WEST)
                        || hasDownhillFlow(world, pos, EnumFacing.NORTH) || hasDownhillFlow(world, pos, EnumFacing.SOUTH)) {
                    expQuanta = quantaPerBlock - 1;
                } else {
                    int maxQuanta = -100;
                    for (EnumFacing side : EnumFacing.Plane.HORIZONTAL) {
                        maxQuanta = getLargerQuanta(world, pos.offset(side), maxQuanta);
                    }
                    expQuanta = maxQuanta - 1;
                }
                // decay calculation
                if (expQuanta != quantaRemaining) {
                    quantaRemaining = expQuanta;
                    if (expQuanta <= 0) {
                        world.setBlockToAir(pos);
                    } else {
                        world.setBlockState(pos, state.withProperty(LEVEL, quantaPerBlock - expQuanta), 2);
                        world.scheduleUpdate(pos, this, tickRate);
                        world.notifyNeighborsOfStateChange(pos, this, false);
                    }
                }
            }
            // Flow vertically if possible
            if (canDisplace(world, pos.up(densityDir))) {
                flowIntoBlock(world, pos.up(densityDir), 1);
                return;
            }
            // Flow outward if possible
            if (world.getBlockState(pos.down()).getMaterial().equals(Material.LAVA)) {
                return;
            }
            int flowMeta = quantaPerBlock - quantaRemaining + 1;
            if (flowMeta >= quantaPerBlock) {
                return;
            }
            if (isSourceBlock(world, pos) || !isFlowingVertically(world, pos)) {
                if (world.getBlockState(pos.down(densityDir)).getBlock() == this) {
                    flowMeta = 1;
                }
                boolean flowTo[] = getOptimalFlowDirections(world, pos);
                for (int i = 0; i < 4; i++) {
                    if (flowTo[i])
                        flowIntoBlock(world, pos.offset(SIDES.get(i)), flowMeta);
                }
            }
        } else {
            super.updateTick(world, pos, state, rand);
        }
    }

    @Override
    public boolean shouldSideBeRendered(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos,
                                        @Nonnull EnumFacing side) {
        IBlockState neighbor = world.getBlockState(pos.offset(side));
        if (neighbor.getBlock() == state.getBlock()) {
            return false;
        }
        if (side == (densityDir < 0 ? EnumFacing.UP : EnumFacing.DOWN)) {
            return true;
        }
        return super.shouldSideBeRendered(state, world, pos, side);
    }

    @SideOnly(Side.CLIENT)
    public void initModels() {

        Item item = Item.getItemFromBlock(this);
        StateMapper mapper = new StateMapper(ATMAdditions.MODID, "fluid", name);

        // Item Model
        ModelBakery.registerItemVariants(item);
        ModelLoader.setCustomMeshDefinition(item, mapper);
        // Block Model
        ModelLoader.setCustomStateMapper(this, mapper);
    }
}
