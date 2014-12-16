package teich.test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Airplane {

	/**
	 * Construct a new Airplane with the specified configuration and number of
	 * rows. The configuration is a String with letters specifying a seat's
	 * position in the row and a "_" for the aisle.
	 * 
	 * For instance, an Airplane with configuration, ABC_DEFGH_JKL would be
	 * three seats, then an aisle, then 5 seats, then an aisle, then 3 seats.
	 * 
	 * @param configuration
	 * @param numRows
	 */
	private String config;
	private int numRows;
	private Map<String, Boolean> plane;
	private int numLetters;

	public Airplane(String configuration, int numRows) {
		config = configuration;
		this.numRows = numRows;
		plane = new HashMap<String, Boolean>();
		numLetters = 0;
		char[] letters = new char[configuration.length()];
		for (int j = 0; j < configuration.length(); j++) {
			char c = configuration.charAt(j);
			if (c != '_') {
				letters[j] = c;
				numLetters++;
			} else {
				letters[j] = c;
			}
		}
		for (int i = 0; i < numRows; i++) {
			int row = i + 1;
			for (int c = 0; c < letters.length; c++) {
				String l;
				if (letters[c] == '_') {
					c++;
					l = String.valueOf(letters[c]);
					String code = row + l;
					plane.put(code, false);
				} else {
					l = String.valueOf(letters[c]);
					String code = row + l;
					plane.put(code, false);
				}

			}
		}
	}

	/**
	 * @return the total number of EMPTy seats on the plane.
	 */
	public int getNumEmptySeats() {
		int counter = 0;
		for (Map.Entry<String, Boolean> entry : plane.entrySet()) {
			if (entry.getValue() == false) {
				counter++;
			}
		}
		return counter;
	}

	/**
	 * @return true if the plane is full, otherwise false.
	 */
	public boolean isFull() {
		for (Map.Entry<String, Boolean> entry : plane.entrySet()) {
			if (entry.getValue() == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param code
	 * @return true if the seat is occupied, otherwise false.
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public boolean isOccupied(String code) throws UnknownSeatException {
		if (plane.containsKey(code)) {
			return plane.get(code);
		} else {
			throw new UnknownSeatException();
		}
	}

	/**
	 * Sets the seat as occupied/unoccupied
	 * 
	 * @param code
	 * @param occupied
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public void setOccupied(String code, boolean occupied) throws UnknownSeatException {
		if (plane.containsKey(code)) {
			plane.put(code, occupied);
		} else {
			throw new UnknownSeatException();
		}
	}

	/**
	 * Set all seats by their codes as occupied
	 * 
	 * @param codes
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public void occupy(String... codes) throws UnknownSeatException {
		for (String s : codes) {
			if (plane.containsKey(s)) {
				plane.put(s, true);
			} else {
				throw new UnknownSeatException();
			}
		}
	}

	/**
	 * Sets all seats as occupied
	 * 
	 * @param seats
	 */
	public void occupy(List<Seat> seats) {
		for (Seat seat : seats) {
			String code = seat.getCode();
			plane.put(code, true);
		}
	}

	/**
	 * Returns the seat specified by it's code
	 * 
	 * @param code
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public Seat getSeat(String code) throws UnknownSeatException {
		if (plane.containsKey(code)) {
			Seat seat = new Seat(Integer.parseInt(code.substring(0, 1)), code.charAt(1));
			return seat;
		} else {
			throw new UnknownSeatException();
		}
	}

	/**
	 * @return total number of seats on the plane
	 */
	public int getNumSeats() {
		return numRows * numLetters;
	}

	/**
	 * Returns the Airplane specified in text format.
	 * 
	 * The first line should be the configuration, prepended by 4 spaces Each
	 * row in the plane gets a line which starts with The row number, padded
	 * with leading zeros so that is is always 3 digits. A space Then for each
	 * seat, either a "." for an empty seat, "O" for an occupied seat and "_"
	 * for an aisle.
	 * 
	 * Example. AB_CD_EF\n 001 .._.._..\n 002 .._.._..\n 003 .._.._..\n
	 * 
	 * 
	 */
	public String toString() {
		StringBuilder build = new StringBuilder();
		build.append("    " + config + "\n");
		for (int k = 0; k < numRows; k++) {
			if (k < 10) {
				build.append("00");
				build.append(k + 1);
			} else if (k >= 10 || k < 100) {
				build.append("0");
				build.append(k + 1);
			} else {
				build.append(k + 1);
			}
			build.append(" ");
			for (int s = 0; s < config.length(); s++) {
				String sub = config.substring(s, s + 1);
				if (sub.matches("[a-zA-Z]")) {
					String code = (k + 1) + sub;
					if (plane.get(code) == true) {
						build.append("O");
					} else {
						build.append(".");
					}
				} else {
					build.append("_");
				}
			}
			build.append("\n");
		}
		return build.toString();
	}

	/**
	 * 
	 * @param numSeatsTogeather
	 *            the number of seats to occupy.
	 * @return A list of occupied seats.
	 * @throws FullPlaneException
	 *             if the plane is full
	 * @throws NotEnoughSeatsTogeatherException
	 *             if there are not enough seats next to each other.
	 */
	public List<Seat> occupySeats(int numSeatsTogeather) throws FullPlaneException, NotEnoughSeatsTogeatherException {
		if (isFull()) {
			throw new FullPlaneException();
		}
		int numEmpty = 0;
		List<String> sorted = new ArrayList<String>(plane.keySet());
		Collections.sort(sorted, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
			}
		});
		List<Seat> occupy = new ArrayList<Seat>();
		for (String s : sorted) {
			if (plane.get(s) == false) {
				numEmpty++;
				int row = Integer.parseInt(s.substring(0, 1));
				occupy.add(new Seat(row, s.charAt(1)));
				if (numEmpty == numSeatsTogeather) {
					break;
				}
			} else {
				numEmpty = 0;
				occupy.removeAll(occupy);
			}
		}
		if (numEmpty < numSeatsTogeather) {
			throw new NotEnoughSeatsTogeatherException();
		} else {
			return occupy;
		}
	}

}
