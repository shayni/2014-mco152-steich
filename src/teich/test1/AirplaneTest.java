package teich.test1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class AirplaneTest {

	@Test
	public void testToStringWithEmptyPlane() {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		Assert.assertEquals("    AB_CD_EF\n" + "001 .._.._..\n" + "002 .._.._..\n" + "003 .._.._..\n", plane.toString());
	}

	@Test
	public void testToStringWithFullPlane() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy("1A", "1B", "1C", "1D", "1E", "1F", "2A", "2B", "2C", "2D", "2E", "2F", "3A", "3B", "3C", "3D",
				"3E", "3F");
		Assert.assertEquals("    AB_CD_EF\n" + "001 OO_OO_OO\n" + "002 OO_OO_OO\n" + "003 OO_OO_OO\n", plane.toString());
	}

	@Test
	public void testGetNumSeats() {
		Airplane airplane = new Airplane("AB_CD_EF", 3);
		int numSeats = airplane.getNumSeats();
		Assert.assertEquals(18, numSeats);
	}

	@Test
	public void testGetNumEmptySeats() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		int numEmpty = plane.getNumEmptySeats();
		Assert.assertEquals(18, numEmpty);

		plane.occupy("1A", "2B", "3C");
		numEmpty = plane.getNumEmptySeats();
		Assert.assertEquals(15, numEmpty);
	}

	@Test
	public void testIsFull() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		Assert.assertFalse(plane.isFull());
		plane.occupy("1A", "1B", "1C", "1D", "1E", "1F", "2A", "2B", "2C", "2D", "2E", "2F", "3A", "3B", "3C", "3D",
				"3E", "3F");
		Assert.assertTrue(plane.isFull());
	}

	@Test
	public void testGetSeatThrowsUnknownSeatException() {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		try {
			plane.getSeat("2G");
			Assert.fail("UnkownSeatException should be thrown here");
		} catch (UnknownSeatException e) {

		}
	}

	@Test
	public void testOccupySeats() throws UnknownSeatException, FullPlaneException, NotEnoughSeatsTogeatherException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		List<Seat> seats = plane.occupySeats(2);
		List<Seat> expected = new ArrayList<Seat>();
		expected.add(new Seat(1, "A"));
		expected.add(new Seat(1, "B"));
		Assert.assertEquals(expected, seats);
	}

	@Test
	public void testOccupySeatsThrowsNotEnoughSeatsTogeatherException() throws FullPlaneException, UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy("1A", "1B", "1C", "1D", "1E", "1F", "2A", "2B", "2C", "2D", "2E", "2F", "3A", "3B", "3C", "3D",
				"3E");
		try {
			plane.occupySeats(2);
			Assert.fail("NotEnoughSeatsTogeatherException should be thrown here.");
		} catch (NotEnoughSeatsTogeatherException e) {

		}
	}

	@Test
	public void testOccupySeatsThrowsFullPlaneException() throws NotEnoughSeatsTogeatherException, UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy("1A", "1B", "1C", "1D", "1E", "1F", "2A", "2B", "2C", "2D", "2E", "2F", "3A", "3B", "3C", "3D",
				"3E", "3F");
		try {
			plane.occupySeats(3);
			Assert.fail("FullPlaneException should be thrown here.");
		} catch (FullPlaneException e) {

		}
	}

}
