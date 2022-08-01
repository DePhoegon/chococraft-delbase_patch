package com.dephoegon.chococraft.common.init;

import com.dephoegon.chococraft.Chococraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Chococraft.MOD_ID);

    public static final RegistryObject<SoundEvent> AMBIENT_SOUND = SOUND_EVENTS.register("entity.chocobo.kweh", () ->
            new SoundEvent(new ResourceLocation(Chococraft.MOD_ID, "entity.chocobo.kweh")));
    public static final RegistryObject<SoundEvent> WHISTLE_SOUND_FOLLOW = SOUND_EVENTS.register("entity.chocobo.kwehwhistlefollow", () ->
            new SoundEvent(new ResourceLocation(Chococraft.MOD_ID, "entity.chocobo.kwehwhistlefollow")));
    public static final RegistryObject<SoundEvent> WHISTLE_SOUND_STAY = SOUND_EVENTS.register("entity.chocobo.kwehwhistlestay", () ->
            new SoundEvent(new ResourceLocation(Chococraft.MOD_ID, "entity.chocobo.kwehwhistlestay")));
    public static final net.minecraftforge.registries.RegistryObject<SoundEvent> WHISTLE_SOUND_WANDER = SOUND_EVENTS.register("entity.chocobo.kwehwhistlewander", () ->
            new SoundEvent(new ResourceLocation(Chococraft.MOD_ID, "entity.chocobo.kwehwhistlewander")));
}
