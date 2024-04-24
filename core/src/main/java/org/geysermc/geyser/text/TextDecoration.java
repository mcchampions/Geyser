/*
 * Copyright (c) 2019-2022 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

package org.geysermc.geyser.text;

import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.opennbt.tag.builtin.ListTag;
import com.github.steveice10.opennbt.tag.builtin.StringTag;
import com.github.steveice10.opennbt.tag.builtin.Tag;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import org.geysermc.mcprotocollib.protocol.data.game.RegistryEntry;

import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;

public final class TextDecoration {
    private final String translationKey;
    private final Style style;
    private final Set<Parameter> parameters;

    public TextDecoration(CompoundTag tag) {
        translationKey = (String) tag.get("translation_key").getValue();

        CompoundTag styleTag = tag.get("style");
        Style.Builder builder = Style.style();
        if (styleTag != null) {
            StringTag color = styleTag.get("color");
            if (color != null) {
                builder.color(NamedTextColor.NAMES.value(color.getValue()));
            }
            //TODO implement the rest
            Tag italic = styleTag.get("italic");
            if (italic != null && ((Number) italic.getValue()).byteValue() == (byte) 1) {
                builder.decorate(net.kyori.adventure.text.format.TextDecoration.ITALIC);
            }
        }
        style = builder.build();

        this.parameters = EnumSet.noneOf(Parameter.class);
        ListTag parameters = tag.get("parameters");
        for (Tag parameter : parameters) {
            this.parameters.add(Parameter.valueOf(((String) parameter.getValue()).toUpperCase(Locale.ROOT)));
        }
    }

    public String translationKey() {
        return translationKey;
    }

    public Style style() {
        return style;
    }

    public Set<Parameter> parameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "TextDecoration{" +
                "translationKey='" + translationKey + '\'' +
                ", style=" + style +
                ", parameters=" + parameters +
                '}';
    }

    public static TextDecoration readChatType(RegistryEntry entry) {
        // Note: The ID is NOT ALWAYS THE SAME! ViaVersion as of 1.19 adds two registry entries that do NOT match vanilla.
        CompoundTag tag = entry.getData();
        CompoundTag chat = tag.get("chat");
        TextDecoration textDecoration = null;
        if (chat != null) {
            textDecoration = new TextDecoration(chat);
        }
        return textDecoration;
    }

    public enum Parameter {
        CONTENT,
        SENDER,
        TARGET
    }
}
