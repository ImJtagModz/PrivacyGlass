package com.imjtagmodz.privacyglass.blocks;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PrivacyGlassBlock extends PrivacyBlockBase{
	private final Set<BlockPos> blocksNeedingUpdate = Sets.<BlockPos>newHashSet();
	private boolean ProvidePower = false;
	
	public PrivacyGlassBlock(String name) {
		super(name);		
	}
	
	@Override
	public boolean canProvidePower(IBlockState state) {
		return this.ProvidePower;
	}
	
	private IBlockState updateSurroundingRedstone(World worldIn, BlockPos pos, IBlockState state)
    {
        state = this.calculateCurrentChanges(worldIn, pos, pos, state);
        List<BlockPos> list = Lists.newArrayList(this.blocksNeedingUpdate);
        this.blocksNeedingUpdate.clear();

        for (BlockPos blockpos : list)
        {
            worldIn.notifyNeighborsOfStateChange(blockpos, this, false);
        }

        return state;
    }
	
	private IBlockState calculateCurrentChanges(World worldIn, BlockPos pos1, BlockPos pos2, IBlockState state)
	    {
	        IBlockState iblockstate = state;
	        int i = ((Integer)state.getValue(ACTIVE)).intValue();
	        int j = 0;
	        j = this.getMaxCurrentStrength(worldIn, pos2, j);
	        this.ProvidePower = false;
	        int k = worldIn.isBlockIndirectlyGettingPowered(pos1);
	        this.ProvidePower = true;

	        if (k > 0 && k > j - 1)
	        {
	            j = k;
	        }

	        int l = 0;

	        for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
	        {
	            BlockPos blockpos = pos1.offset(enumfacing);
	            boolean flag = blockpos.getX() != pos2.getX() || blockpos.getZ() != pos2.getZ();

	            if (flag)
	            {
	                l = this.getMaxCurrentStrength(worldIn, blockpos, l);
	            }

	            if (worldIn.getBlockState(blockpos).isNormalCube() && !worldIn.getBlockState(pos1.up()).isNormalCube())
	            {
	                if (flag && pos1.getY() >= pos2.getY())
	                {
	                    l = this.getMaxCurrentStrength(worldIn, blockpos.up(), l);
	                }
	            }
	            else if (!worldIn.getBlockState(blockpos).isNormalCube() && flag && pos1.getY() <= pos2.getY())
	            {
	            	l = this.getMaxCurrentStrength(worldIn, blockpos.down(), l);
	            }
	        }

	        if (l > j)
	        {
	            j = l - 1;
	        }
	        else if (j > 0)
	        {
	            --j;
	        }
	        else
	        {
	            j = 0;
	        }

	        if (k > j - 1)
	        {
	            j = k;
	        }

	        if (i != j)
	        {
	        	state = state.withProperty(ACTIVE, Integer.valueOf(j));

	            if (worldIn.getBlockState(pos1) == iblockstate)
	            {
	                worldIn.setBlockState(pos1, state, 2);
	            }

	            this.blocksNeedingUpdate.add(pos1);

	            for (EnumFacing enumfacing1 : EnumFacing.values())
	            {
	                this.blocksNeedingUpdate.add(pos1.offset(enumfacing1));
	            }
	        }

	        return state;
	    }

	private void notifyWireNeighborsOfStateChange(World worldIn, BlockPos pos)
    {
        if (worldIn.getBlockState(pos).getBlock() == this)
        {
            worldIn.notifyNeighborsOfStateChange(pos, this, false);

            for (EnumFacing enumfacing : EnumFacing.values())
            {
                worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this, false);
            }
        }
    }
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            this.updateSurroundingRedstone(worldIn, pos, state);

            for (EnumFacing enumfacing : EnumFacing.Plane.VERTICAL)
            {
                worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this, false);
            }

            for (EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL)
            {
                this.notifyWireNeighborsOfStateChange(worldIn, pos.offset(enumfacing1));
            }

            for (EnumFacing enumfacing2 : EnumFacing.Plane.HORIZONTAL)
            {
                BlockPos blockpos = pos.offset(enumfacing2);

                if (worldIn.getBlockState(blockpos).isNormalCube())
                {
                    this.notifyWireNeighborsOfStateChange(worldIn, blockpos.up());
                }
                else
                {
                    this.notifyWireNeighborsOfStateChange(worldIn, blockpos.down());
                }
            }
        }
    }
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        super.breakBlock(worldIn, pos, state);

        if (!worldIn.isRemote)
        {
            for (EnumFacing enumfacing : EnumFacing.values())
            {
                worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this, false);
            }

            this.updateSurroundingRedstone(worldIn, pos, state);

            for (EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL)
            {
                this.notifyWireNeighborsOfStateChange(worldIn, pos.offset(enumfacing1));
            }

            for (EnumFacing enumfacing2 : EnumFacing.Plane.HORIZONTAL)
            {
                BlockPos blockpos = pos.offset(enumfacing2);

                if (worldIn.getBlockState(blockpos).isNormalCube())
                {
                    this.notifyWireNeighborsOfStateChange(worldIn, blockpos.up());
                }
                else
                {
                    this.notifyWireNeighborsOfStateChange(worldIn, blockpos.down());
                }
            }
        }
    }
	
	private int getMaxCurrentStrength(World worldIn, BlockPos pos, int strength)
    {
        if (worldIn.getBlockState(pos).getBlock() != this)
        {
        	return strength;
        }
        else
        {
            int i = ((Integer)worldIn.getBlockState(pos).getValue(ACTIVE)).intValue();
            return i > strength ? i : strength;
        }
    }
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!worldIn.isRemote)
        {
        	this.updateSurroundingRedstone(worldIn, pos, state);
        }
    }
}
