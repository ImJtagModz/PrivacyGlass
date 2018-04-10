package com.imjtagmodz.privacyglass.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class PrivacyBlockBase extends Block {
	public static final PropertyInteger ACTIVE = PropertyInteger.create("active", 0, 15);
	
	protected PrivacyBlockBase(String name) {
		this(Material.GLASS);
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	protected PrivacyBlockBase(Material materialIn) {
		super(materialIn, materialIn.getMaterialMapColor());
		setHardness(0.3F);
		setResistance(1.5F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, 0));
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{ACTIVE});
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(ACTIVE).intValue();
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState iBlockState) {
		return false;
	}
	
	@Override
	public int quantityDropped(Random random)
    {
        return 0;
    }
	
	@Override
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	protected boolean canSilkHarvest() {
		return true;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        IBlockState iBlockState = blockAccess.getBlockState(pos.offset(side));
        Block block = iBlockState.getBlock();
        
        if (blockState != iBlockState) return true;
        if (block == this) return false;
        
        return blockState.shouldSideBeRendered(blockAccess, pos, side);
    }
	
}
