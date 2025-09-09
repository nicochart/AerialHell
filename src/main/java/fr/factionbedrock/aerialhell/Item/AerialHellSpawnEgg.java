package fr.factionbedrock.aerialhell.Item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Spawner;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

//temporary solution to fix forge "invisible" (no texture displaying) spawn eggs
public class AerialHellSpawnEgg extends Item
{
    private final Supplier<? extends EntityType<? extends Mob>> typeSupplier;
    private final boolean isBoss;

    public AerialHellSpawnEgg(Supplier<? extends EntityType<? extends Mob>> type, Item.Properties props, boolean isBoss)
    {
        super(props);
        this.typeSupplier = type;
        this.isBoss = isBoss;
    }

    @Override public InteractionResult useOn(UseOnContext context)
    {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel)) {return InteractionResult.SUCCESS;}
        else
        {
            ItemStack itemstack = context.getItemInHand();
            BlockPos blockpos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            BlockState blockstate = level.getBlockState(blockpos);
            if (level.getBlockEntity(blockpos) instanceof Spawner spawner)
            {
                EntityType<?> entitytype1 = this.getType(itemstack);
                spawner.setEntityId(entitytype1, level.getRandom());
                level.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
                level.gameEvent(context.getPlayer(), GameEvent.BLOCK_CHANGE, blockpos);
                itemstack.shrink(1);
                return InteractionResult.CONSUME;
            }
            else
            {
                BlockPos blockpos1;
                if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {blockpos1 = blockpos;}
                else {blockpos1 = blockpos.relative(direction);}
                EntityType<?> entitytype = this.getType(itemstack);
                if (entitytype.spawn((ServerLevel)level,itemstack,context.getPlayer(),blockpos1,EntitySpawnReason.SPAWN_ITEM_USE,true,!Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null)
                {
                    itemstack.shrink(1);
                    level.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
                }
                return InteractionResult.CONSUME;
            }
        }
    }

    @Override public InteractionResult use(Level level, Player player, InteractionHand hand)
    {
        ItemStack itemstack = player.getItemInHand(hand);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        if (blockhitresult.getType() != HitResult.Type.BLOCK) {return InteractionResult.PASS;}
        else if (!(level instanceof ServerLevel)) {return InteractionResult.SUCCESS;}
        else
        {
            BlockPos blockpos = blockhitresult.getBlockPos();
            if (!(level.getBlockState(blockpos).getBlock() instanceof LiquidBlock)) {return InteractionResult.PASS;}
            else if (level.mayInteract(player, blockpos) && player.mayUseItemAt(blockpos, blockhitresult.getDirection(), itemstack))
            {
                EntityType<?> entitytype = this.getType(itemstack);
                Entity entity = entitytype.spawn((ServerLevel)level, itemstack, player, blockpos, EntitySpawnReason.SPAWN_ITEM_USE, false, false);
                if (entity == null) {return InteractionResult.PASS;}
                else
                {
                    itemstack.consume(1, player);
                    player.awardStat(Stats.ITEM_USED.get(this));
                    level.gameEvent(player, GameEvent.ENTITY_PLACE, entity.position());
                    return InteractionResult.CONSUME;
                }
            }
            else {return InteractionResult.FAIL;}
        }
    }

    public EntityType<?> getType(ItemStack itemStack) {return this.typeSupplier.get();}

    @Override public boolean isFoil(ItemStack stack) {return this.isBoss;}

    @Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag)
    {
        if (this.isBoss) {tooltipAdder.accept(this.getDescription().withStyle(ChatFormatting.DARK_RED));}
    }

    public MutableComponent getDescription()
    {
        return Component.translatable("item.aerialhell.boss_spawn_egg.desc");
    }
}
