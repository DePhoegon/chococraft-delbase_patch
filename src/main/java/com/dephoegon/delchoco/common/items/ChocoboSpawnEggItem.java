package com.dephoegon.delchoco.common.items;

import com.dephoegon.delchoco.common.entities.Chocobo;
import com.dephoegon.delchoco.common.entities.properties.ChocoboColor;
import com.dephoegon.delchoco.common.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ChocoboSpawnEggItem extends Item {
    private final ChocoboColor color;

    public ChocoboSpawnEggItem(Properties pProperties, ChocoboColor color) {
        super(pProperties);
        this.color = color;
    }


    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level worldIn = context.getLevel();
        if (worldIn.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        final Chocobo chocobo = ModEntities.CHOCOBO.get().create(worldIn);
        if (chocobo != null) {
            final BlockPos pos = context.getClickedPos();
            final Player player = context.getPlayer();
            if (player != null) {
                if (player.isCrouching()) {
                    chocobo.setAge(-7500);
                }
            }

            chocobo.moveTo(pos.getX() + .5, pos.getY() + 1.5F, pos.getZ() + .5, Mth.wrapDegrees(worldIn.random.nextFloat() * 360.0F), 0.0F);
            chocobo.yHeadRot = chocobo.getYRot();
            chocobo.yBodyRot = chocobo.getYRot();
            chocobo.setChocoboColor(color);
            chocobo.setFlame(color == ChocoboColor.FLAME);
            chocobo.finalizeSpawn((ServerLevel)worldIn, worldIn.getCurrentDifficultyAt(chocobo.blockPosition()), MobSpawnType.SPAWN_EGG, (SpawnGroupData)null, (CompoundTag)null);
            worldIn.addFreshEntity(chocobo);
            chocobo.playAmbientSound();
            context.getItemInHand().shrink(1);
        }

        return InteractionResult.CONSUME;
    }
}
