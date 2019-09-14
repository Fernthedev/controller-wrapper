package com.github.fernthedev.joystick;

import com.github.fernthedev.joystick.mappings.Mapping;
import lombok.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Objects;

import static org.lwjgl.glfw.GLFW.*;

@Data
@RequiredArgsConstructor
public class JoystickController {

    @Getter
    public final int controllerIndex;

    @Getter
    @Setter
    @NonNull
    private Mapping mapping;

    private ControllerButtons controllerButtons;
    private ControllerAxis controllerAxis;

    /**
     * Checks if controller is connected
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        return glfwJoystickPresent(controllerIndex);
    }

    /**
     * Gets name of controller
     * @return JoystickController name such as “Microsoft PC-com.github.fernthedev.joystick driver", null if controller not found
     */
    public String getName() {
        validateConnected();
        return glfwGetJoystickName(controllerIndex);
    }

    /**
     * Useful for debugging and checking button ids
     */
    public void printControllerButtonStates() {
        validateConnected();

        ByteBuffer buttons = glfwGetJoystickButtons(controllerIndex);

        int buttonID = 1;
        assert buttons != null;
        while (buttons.hasRemaining()) {
            int state = buttons.get();
            if (state == GLFW_PRESS) {
                System.out.println("Button " + buttonID + " is pressed!");
            }else{
                System.out.println("Button " + buttonID + " is released!");
            }
            buttonID++;
        }
    }



    public ControllerButtons getButtons() {
        validateConnected();

        if(controllerButtons == null) {
            return controllerButtons = ControllerButtons.buildControllerButtons(controllerIndex, mapping);
        }

        return controllerButtons.getControllerButtons(controllerIndex,mapping);
    }

    public ControllerAxis getAxes() {
        validateConnected();

        if(controllerAxis == null) {
            return controllerAxis = ControllerAxis.buildAxis(controllerIndex, mapping);
        }

        return controllerAxis.getAxis(controllerIndex, mapping);
    }

    /**
     * Useful for debugging and checking axis ids
     */
    public void printControllerAxisStates() {
        validateConnected();

        FloatBuffer axes = glfwGetJoystickAxes(controllerIndex);

        int axisID = 1;
        while (Objects.requireNonNull(axes).hasRemaining()) {
            float state = axes.get();
            if (state < -0.95f || state > 0.95f) {
                System.out.println("Axis " + axisID + " is at full-range!");
            } else if (state < -0.5f || state > 0.5f) {
                System.out.println("Axis " + axisID + " is at mid-range!");
            }else{
                System.out.println("Axis " + axisID + " is at " + state);
            }
            axisID++;
        }

    }

    private void validateConnected() {
        if(!isConnected()) throw new IllegalStateException("Controller is not connected, check using isConnected() method.");


    }
}
