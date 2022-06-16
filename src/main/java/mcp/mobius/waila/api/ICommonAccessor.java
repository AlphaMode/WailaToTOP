package mcp.mobius.waila.api;

import mcp.mobius.waila.api.__internal__.ApiSide;
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
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

/**
 * Used to get some basic data out of the game without having to request direct access to the game engine.
 */
@ApiSide.ClientOnly
@ApiStatus.NonExtendable
public interface ICommonAccessor {

    Level getWorld();

    Player getPlayer();

    Block getBlock();

    ResourceLocation getBlockId();

    @Nullable
    <T extends BlockEntity> T getBlockEntity();

    @Nullable
    <T extends Entity> T getEntity();

    BlockPos getPosition();

    @Nullable
    Vec3 getRenderingPosition();

    CompoundTag getServerData();

    double getPartialFrame();

    @Nullable
    Direction getSide();

    ItemStack getStack();

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.19")
    String getModNameFormat();

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.19")
    String getBlockNameFormat();

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.19")
    String getFluidNameFormat();

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.19")
    String getEntityNameFormat();

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.19")
    String getRegistryNameFormat();

}
