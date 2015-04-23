package se.thinkcode;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HelloWorldTest {
    @Test
    public void hello_world() {
        String expected = "Hello Bristol!";

        HelloWorld helloWorld = new HelloWorld();

        String actual = helloWorld.sayHiTo("Bristol");

        assertThat(actual, is(expected));
    }
}
