# controller-wrapper

## Some info
[![](https://jitpack.io/v/Fernthedev/controller-wrapper.svg)](https://jitpack.io/#Fernthedev/controller-wrapper)

This is a wrapper for GLFW controller with Xbox/DS4 or custom mappings. It is based off my controller mod for Minecraft and it has been decoupled into it's own project implemented in the mod now.

## Usage
You can use the JoystickController class to access the functions.

```java

GLFW.glfwPollEvents(); //Necessary to be called, updates the state of the controller buttons. DOES NOT DIRECTLY UPDATE THE controller.getButtons(); or controller.getAxes(); BUT IS REQUIRED TO UPDATE IT.

JoystickController controller = new JoystickController(GLFW.GLFW_JOYSTICK_1, new XboxOneMapping());

controller.setMapping(new XboxOne); // Set mapping

controller.getButtons(); // Retrieves the button states. It is updated every time you call getButtons. isPressed(); only executes if it is held and only once until it is unHeld. Use isHeld(); for continous checking

controller.getAxes(); // Retrieves the axes states. It is updated every time you call getButtons.
```

## Download

### Maven
```xml
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```

```xml
	<dependency>
	    <groupId>com.github.Fernthedev</groupId>
	    <artifactId>controller-wrapper</artifactId>
	    <version>1.0.3</version> <!-- Check releases tab for version -->
	</dependency>
```

### Gradle
```gradle
repositories {
  maven { url 'https://jitpack.io' }
}
```

```gradle
dependencies {
        implementation 'com.github.Fernthedev:controller-wrapper:1.0.3' // Check releases tab for version
}
```
