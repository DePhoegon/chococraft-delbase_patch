package com.dephoegon.delchoco.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.dephoegon.delchoco.DelChoco;
import com.dephoegon.delchoco.common.entities.Chocobo;
import com.dephoegon.delchoco.common.init.ModAttributes;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import org.apache.logging.log4j.util.BiConsumer;

import java.util.HashMap;
import java.util.Map;

public class ChocoboCommand {
    public static void initializeCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        final LiteralArgumentBuilder<CommandSourceStack> root = Commands.literal("chocobo");
        root.requires((commandSource) -> commandSource.hasPermission(2))
                .then(Commands.literal("list").executes(ChocoboCommand::sendList))
                .then(Commands.literal("set")
                    .then(Commands.literal("health").then(Commands.argument("value", FloatArgumentType.floatArg(0))).executes((ctx) ->
                        setAttribute(ctx, "health", String.valueOf(FloatArgumentType.getFloat(ctx, "value")))))
                    .then(Commands.literal("resistance").then(Commands.argument("value", FloatArgumentType.floatArg(0))).executes((ctx) ->
                        setAttribute(ctx, "resistance", String.valueOf(FloatArgumentType.getFloat(ctx, "value")))))
                    .then(Commands.literal("speed").then(Commands.argument("value", FloatArgumentType.floatArg(0))).executes((ctx) ->
                        setAttribute(ctx, "speed", String.valueOf(FloatArgumentType.getFloat(ctx, "value")))))
                    .then(Commands.literal("stamina").then(Commands.argument("value", FloatArgumentType.floatArg(0))).executes((ctx) ->
                        setAttribute(ctx, "stamina", String.valueOf(FloatArgumentType.getFloat(ctx, "value"))))));
        dispatcher.register(root);
    }

    private static final String MODID = DelChoco.MOD_ID;

    private static final Map<String, BiConsumer<Chocobo, String>> setMap;

    static {
        setMap = new HashMap<>();
        setMap.put("health", (entity, arg) -> entity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(Float.parseFloat(arg)));
        setMap.put("resistance", (entity, arg) -> entity.getAttribute(Attributes.ARMOR).setBaseValue(Float.parseFloat(arg)));
        setMap.put("speed", (entity, arg) -> entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(Float.parseFloat(arg)));
        setMap.put("stamina", (entity, arg) -> entity.getAttribute(ModAttributes.MAX_STAMINA.get()).setBaseValue(Float.parseFloat(arg)));
    }


    private static int sendList(CommandContext<CommandSourceStack> commandContext) {
        CommandSourceStack source = commandContext.getSource();
        Entity commandEntity = source.getEntity();
        if(commandEntity instanceof Player player) {
            Entity mount = player.getVehicle();
            if (!(mount instanceof Chocobo chocobo)) {
                source.sendSuccess(new TranslatableComponent("command." + MODID + ".chocobo.not_riding_chocobo"), false);
                return 0;
            } else {
                source.sendSuccess(getText("get_health", chocobo, Attributes.MAX_HEALTH), false);
                source.sendSuccess(getText("get_resistance", chocobo, Attributes.ARMOR), false);
                source.sendSuccess(getText("get_speed", chocobo, Attributes.MOVEMENT_SPEED), false);
                source.sendSuccess(getText("get_stamina", chocobo, ModAttributes.MAX_STAMINA.get()), false);
            }
        }

        return 0;
    }

    private static int setAttribute(CommandContext<CommandSourceStack> commandContext, String trait, String value) {
        CommandSourceStack source = commandContext.getSource();
        Entity commandEntity = source.getEntity();
        if(commandEntity instanceof Player player) {
            Entity mount = player.getVehicle();
            if (!(mount instanceof Chocobo chocobo)) {
                source.sendSuccess(new TranslatableComponent("command." + MODID + ".chocobo.not_riding_chocobo"), false);
                return 0;
            } else {
                if (setMap.containsKey(trait)) {
                    setMap.get(trait).accept(chocobo, value);
                    source.sendSuccess(new TranslatableComponent("command." + MODID + ".chocobo.successfuly_set_parameters", trait, value), false);
                }
            }
        }

        return 0;
    }

    private static TranslatableComponent getText(String key, Chocobo chocobo, Attribute attribute) {
        return new TranslatableComponent("command." + MODID + ".chocobo." + key, chocobo.getAttribute(attribute).getBaseValue());
    }

    private static TranslatableComponent getText(String key, boolean state) {
        return new TranslatableComponent("command." + MODID + ".chocobo." + key, I18n.get(state ? "command.delchoco.chocobo.true" : "command.delchoco.chocobo.false"));
    }
}
