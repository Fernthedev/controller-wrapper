package com.github.fernthedev.joystick.mappings.xbox;

import com.github.fernthedev.joystick.mappings.Mapping;
import lombok.Getter;

@Getter
public class XboxOneMapping extends Mapping {

    public XboxOneMapping() {
        super(XboxOneButtonMapping.INSTANCE(), XboxOneAxeMapping.INSTANCE(),"XboxOne");
    }


}
