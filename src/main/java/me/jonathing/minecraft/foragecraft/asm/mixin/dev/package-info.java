/**
 * This package contains all of the mixin classes used by ForageCraft to hook into Minecraft, Minecraft Forge, or any
 * other given mod it is injecting into, and changes it based on its needs. These mixins, in particular, are only ever
 * fired when the {@link me.jonathing.minecraft.foragecraft.info.ForageInfo#IDE} property is enabled. Full documentation
 * of ForageCraft's use of coremodding can be found in {@link me.jonathing.minecraft.foragecraft.asm}.
 *
 * @see me.jonathing.minecraft.foragecraft.asm
 * @see me.jonathing.minecraft.foragecraft.info.ForageInfo#IDE
 */
package me.jonathing.minecraft.foragecraft.asm.mixin.dev;