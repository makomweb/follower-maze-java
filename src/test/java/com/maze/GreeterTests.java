import org.junit.Test;
import static org.junit.Assert.*;

public class GreeterTests {
	@Test
	public void when_calling_say_hello_it_should_return_hello_world() {
		Greeter g = new Greeter();
		assertEquals("Hello world!", g.sayHello());
	}
}