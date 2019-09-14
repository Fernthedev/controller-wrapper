package com.github.fernthedev.joystick;

import lombok.*;

@Data
@Setter
@RequiredArgsConstructor
public class ControllerAxisState {
    @Getter
    private float value;

    @Getter
    @NonNull
    private int buttonIndex;
}
