package co.uk.jaycarr.kitgui.util;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public final class StringUtil {

    private StringUtil() {
        throw new AssertionError("You cannot instantiate this class.");
    }

    public static String toColor(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static List<String> toColor(List<String> input) {
        return input.stream()
                .map(StringUtil::toColor)
                .collect(Collectors.toList());
    }
}