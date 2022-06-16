package me.alphamode.topcompact;

import mcjty.theoneprobe.api.IProbeHitData;
import mcp.mobius.waila.api.IBlockAccessor;
import mcp.mobius.waila.api.ICommonAccessor;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IEntityAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class ProbeBlockAccessor implements ICommonAccessor, IBlockAccessor, IDataAccessor, IEntityAccessor {

    private final IProbeHitData data;
    private final Level world;
    private final Player player;
    private final BlockState blockState;
    private final HitResult hitResult;
    private final CompoundTag tag;

    public ProbeBlockAccessor(IProbeHitData data, Level world, Player player, BlockState blockState, HitResult hitResult, CompoundTag tag) {
        this.data = data;
        this.world = world;
        this.player = player;
        this.blockState = blockState;
        this.hitResult = hitResult;
        this.tag = tag;
    }

    @Override
    public Level getWorld() {
        return world;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Block getBlock() {
        return blockState.getBlock();
    }

    @Override
    public ResourceLocation getBlockId() {
        return null;
    }

    @Override
    public BlockState getBlockState() {
        return blockState;
    }

    @Override
    public <T extends BlockEntity> @Nullable T getBlockEntity() {
        return (T) world.getBlockEntity(data.getPos());
    }

    @Override
    public <T extends Entity> @Nullable T getEntity() {
        return null;
    }

    @Override
    public HitResult getHitResult() {
        return hitResult;
    }

    @Override
    public BlockPos getPosition() {
        return data.getPos();
    }

    @Override
    public @Nullable Vec3 getRenderingPosition() {
        return data.getHitVec();
    }

    @Override
    public CompoundTag getServerData() {
        return tag;
    }

    @Override
    public double getPartialFrame() {
        return 0;
    }

    @Override
    public Direction getSide() {
        return data.getSideHit();
    }

    @Override
    public ItemStack getStack() {
        return data.getPickBlock();
    }

    @Override
    public String getModNameFormat() {
        return null;
    }

    @Override
    public String getBlockNameFormat() {
        return null;
    }

    @Override
    public String getFluidNameFormat() {
        return null;
    }

    @Override
    public String getEntityNameFormat() {
        return null;
    }

    @Override
    public String getRegistryNameFormat() {
        return null;
    }
}
