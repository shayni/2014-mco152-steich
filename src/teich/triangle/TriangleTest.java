package teich.triangle;

import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {

	@Test
	public void testTriangle() {
		Triangle t = new Triangle(3);
		String actual = t.toString();
		String expected = "  *\n *  *\n*****";
		Assert.assertEquals(expected, actual);
	}

}
