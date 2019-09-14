package com.github.fernthedev.joystick;

import com.github.fernthedev.joystick.mappings.Mapping;
import lombok.Data;
import lombok.NonNull;

import java.nio.FloatBuffer;
import java.util.Objects;

import static org.lwjgl.glfw.GLFW.glfwGetJoystickAxes;

@Data
//@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ControllerAxis {

    /**
     * Mapping to use
     */
    @NonNull
    private Mapping mapping;

    /**
     * 1 IS RIGHT
     * -1 IS LEFT
     * 0 IS STATELESS
     */
    private ControllerAxisState HORIZONTAL_LEFT_STICKER;

    /**
     * -1 IS DOWN
     * 1 IS UP
     * 0 IS STATELESS
     */
    private ControllerAxisState VERTICAL_LEFT_STICKER;

    /**
     * 1 IS RIGHT
     * -1 IS LEFT
     * 0 IS STATELESS
     */
    private ControllerAxisState VERTICAL_RIGHT_STICKER;

    /**
     * -1 IS DOWN
     * 1 IS UP
     * 0 IS STATELESS
     */
    private ControllerAxisState HORIZONTAL_RIGHT_STICKER;

    /**
     * -1 is default
     * 1 is trigger
     */
    private ControllerAxisState LEFT_TRIGGER;

    /**
     * 0 is default
     * 1 is trigger
     */
    private ControllerAxisState RIGHT_TRIGGER;


    /**
     * Called on constructor
     */
    private void build() {
        HORIZONTAL_LEFT_STICKER = new ControllerAxisState(mapping.getAxesMapping().getHORIZONTAL_LEFT_STICKER());
        VERTICAL_LEFT_STICKER = new ControllerAxisState(mapping.getAxesMapping().getVERTICAL_LEFT_STICKER());
        VERTICAL_RIGHT_STICKER = new ControllerAxisState(mapping.getAxesMapping().getVERTICAL_RIGHT_STICKER());
        HORIZONTAL_RIGHT_STICKER = new ControllerAxisState(mapping.getAxesMapping().getHORIZONTAL_RIGHT_STICKER());
        LEFT_TRIGGER = new ControllerAxisState(mapping.getAxesMapping().getLEFT_TRIGGER());
        RIGHT_TRIGGER = new ControllerAxisState(mapping.getAxesMapping().getRIGHT_TRIGGER());
    }

    public static ControllerAxis buildAxis(int controllerIndex, @NonNull Mapping mapping) {
        ControllerAxis controllerAxis = new ControllerAxis(mapping);


        controllerAxis.build();
        
        return controllerAxis.getAxis(controllerIndex, mapping);
    }
    
    /**
     * Gets an instance of {@link ControllerAxis} for the controller specified
     * @param controllerIndex The controller
     * @param mapping The mapping
     * @return The instance of {@link ControllerAxis}
     */
    public ControllerAxis getAxis(int controllerIndex, @NonNull Mapping mapping) {
        if(this.mapping != mapping) {
            this.mapping = mapping;
            
            remap();
        }
        
        FloatBuffer axes = glfwGetJoystickAxes(controllerIndex);

        int axisID = 1;
        while (Objects.requireNonNull(axes).hasRemaining()) {
            float state = axes.get();

            int id = axisID - 1;

            if(id == HORIZONTAL_LEFT_STICKER.getButtonIndex()) HORIZONTAL_LEFT_STICKER.setValue(state);
            if(id == VERTICAL_LEFT_STICKER.getButtonIndex()) VERTICAL_LEFT_STICKER.setValue(state);
            if(id == VERTICAL_RIGHT_STICKER.getButtonIndex()) VERTICAL_RIGHT_STICKER.setValue(state);
            if(id == HORIZONTAL_RIGHT_STICKER.getButtonIndex()) HORIZONTAL_RIGHT_STICKER.setValue(state);
            if(id == LEFT_TRIGGER.getButtonIndex()) LEFT_TRIGGER.setValue(state);
            if(id == RIGHT_TRIGGER.getButtonIndex()) RIGHT_TRIGGER.setValue(state);

            axisID++;
        }
        return this;
    }

    /**
     * Remaps the axes
     */
    private void remap() {
        HORIZONTAL_LEFT_STICKER.setButtonIndex(mapping.getAxesMapping().getHORIZONTAL_LEFT_STICKER());
        VERTICAL_LEFT_STICKER.setButtonIndex(mapping.getAxesMapping().getVERTICAL_LEFT_STICKER());
        VERTICAL_RIGHT_STICKER.setButtonIndex(mapping.getAxesMapping().getVERTICAL_RIGHT_STICKER());
        HORIZONTAL_RIGHT_STICKER.setButtonIndex(mapping.getAxesMapping().getHORIZONTAL_RIGHT_STICKER());
        LEFT_TRIGGER.setButtonIndex(mapping.getAxesMapping().getLEFT_TRIGGER());
        RIGHT_TRIGGER.setButtonIndex(mapping.getAxesMapping().getRIGHT_TRIGGER());
    }

    /**
     * Checks if the values are different in any state
     * @param controllerAxis The instance of {@link ControllerAxis} to check
     * @return True if all same, false otherwise
     */
    public boolean checkDifferent(ControllerAxis controllerAxis) {
        return controllerAxis.LEFT_TRIGGER.equals(LEFT_TRIGGER) &&
                controllerAxis.RIGHT_TRIGGER.equals(RIGHT_TRIGGER) &&

                controllerAxis.VERTICAL_LEFT_STICKER.equals(VERTICAL_LEFT_STICKER) &&
                controllerAxis.VERTICAL_RIGHT_STICKER.equals(VERTICAL_RIGHT_STICKER) &&

                controllerAxis.HORIZONTAL_LEFT_STICKER.equals(HORIZONTAL_LEFT_STICKER) &&
                controllerAxis.HORIZONTAL_RIGHT_STICKER.equals(HORIZONTAL_RIGHT_STICKER);
    }

    public void printDifferent(ControllerAxis controllerAxis) {
        if(!controllerAxis.LEFT_TRIGGER.equals(LEFT_TRIGGER)) {
            System.out.println("Current: " + LEFT_TRIGGER + " Check: " + controllerAxis.LEFT_TRIGGER);
        }

        if(!controllerAxis.RIGHT_TRIGGER.equals(RIGHT_TRIGGER)) {
            System.out.println("Current: " + RIGHT_TRIGGER + " Check: " + controllerAxis.RIGHT_TRIGGER);
        }

        if(!controllerAxis.VERTICAL_LEFT_STICKER.equals(VERTICAL_LEFT_STICKER)) {
            System.out.println("Current: " + VERTICAL_LEFT_STICKER + " Check: " + controllerAxis.VERTICAL_LEFT_STICKER);
        }

        if(!controllerAxis.VERTICAL_RIGHT_STICKER.equals(VERTICAL_RIGHT_STICKER)) {
            System.out.println("Current: " + VERTICAL_RIGHT_STICKER + " Check: " + controllerAxis.VERTICAL_RIGHT_STICKER);
        }


        if(!controllerAxis.HORIZONTAL_LEFT_STICKER.equals(HORIZONTAL_LEFT_STICKER)) {
            System.out.println("Current: " + HORIZONTAL_LEFT_STICKER + " Check: " + controllerAxis.HORIZONTAL_LEFT_STICKER);
        }

        if(!controllerAxis.HORIZONTAL_RIGHT_STICKER.equals(HORIZONTAL_RIGHT_STICKER)) {
            System.out.println("Current: " + HORIZONTAL_RIGHT_STICKER + " Check: " + controllerAxis.HORIZONTAL_RIGHT_STICKER);
        }

    }
}
