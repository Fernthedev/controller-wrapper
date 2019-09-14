package com.github.fernthedev.joystick.mappings.xbox;

import com.github.fernthedev.joystick.mappings.AxesMapping;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class XboxOneAxeMapping implements AxesMapping {

    @Getter(AccessLevel.NONE)
    private static XboxOneAxeMapping instance;

    public static AxesMapping INSTANCE() {
        if(instance == null) {
            instance = new XboxOneAxeMapping();
        }
        return instance;
    }

    private final int HORIZONTAL_LEFT_STICKER = 0;
    private final int VERTICAL_LEFT_STICKER = 1;
    private final int VERTICAL_RIGHT_STICKER = 2;
    private final int HORIZONTAL_RIGHT_STICKER = 3;
    private final int LEFT_TRIGGER = 4;
    private final int RIGHT_TRIGGER = 5;


}
