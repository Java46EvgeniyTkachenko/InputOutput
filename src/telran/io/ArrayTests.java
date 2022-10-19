package telran.io;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ArrayTests<T> {
		
	/**
	 * The method doesn't update a given array
	 * @param <T>
	 * @param array
	 * @return true if there is exactly one swap for getting sorted array
	 * examples: {1, 2, 3, 10, -1, 5, 6} -> false
	 * {1, 2, 3, 5, 6, 10} -> false
	 * {1,3,2,4, 3, 10} -> false
	 * {10, 2, 3, 4, 1} -> true
	 * {1, 2, 4, 3, 5, 10} -> true
	 * {1, 5, 3, 4, 2, 10} -> true
	 * {"lmn", "ab", "bc", "cd", "a"} -> true
	 * An Array should contain Comparable elements
	 */
	<T> boolean isOneSwapForSorted(T [] array) {
		T temp;
		T[] arrayEx = array.clone();

		int first = 0, second = 0;
		int count = 0;
		int var = 0;
		boolean isTypeNumber = isNumber(arrayEx[0].toString()) ;
		for (int i = 1; i < arrayEx.length; i++){
	
			if (isTypeNumber) {
				var = compareToInteger((Integer) arrayEx[i],(Integer)arrayEx[i - 1]);
			}
			else {
				var = compareToString(arrayEx[i].toString(), arrayEx[i - 1].toString());
			}
			if (var < 0){
				count++;
				if (first == 0)
					first = i;
				else
					second = i;
			}
		}
		
		if (count > 2)
			return false;

		if (count == 0) {
			return false;
		}
		if (count == 2) {
				temp = arrayEx[first - 1];
				arrayEx[first - 1] = arrayEx[second];
				arrayEx[second] = temp;
		}
	
		if (count == 1) {
	
				temp = arrayEx[first - 1];
				arrayEx[first - 1] = arrayEx[first];
				arrayEx[first] = temp;
		}
		
		for (int i = 1; i < arrayEx.length; i++) {
			if (isTypeNumber) {
				var = compareToInteger((Integer) arrayEx[i], (Integer) arrayEx[i - 1]);
			} else {
				var = compareToString(arrayEx[i].toString(), arrayEx[i - 1].toString());
			}
			if (var < 0) {
				return false;
			}
		}
		return true;
			
	}
	
	private int compareToString(String a, String b) {
		return a.compareTo(b);
	}

	private int compareToInteger(Integer a, Integer b) {
		return a.compareTo(b);
	}

	private static boolean isNumber(String string) {
		if (string == null || string.equals("")) {
			return false;
		}
		try {
			Integer.parseInt(string);
			return true;
		} catch (NumberFormatException e) {
		}
		return false;
	}


	@Test
	void isOneSwapTestFalse() {
		Object[]  arrayFalse = new Object[] {1, 2, 3, 10, -1, 5, 6};
		assertFalse(isOneSwapForSorted(arrayFalse));
		arrayFalse = new Object[] {1, 2, 3, 5, 6, 10};
		assertFalse(isOneSwapForSorted(arrayFalse));
		arrayFalse = new Object[] {1, 3, 2, 4, 3, 10};
		assertFalse(isOneSwapForSorted(arrayFalse));
		arrayFalse = new Object[] {5, 1, 2, 3, 4};
		assertFalse(isOneSwapForSorted(arrayFalse));

		
	}
	@Test
	void isOneSwapTestTrue(){
		Object[]  arrayTrue = new Object[] {10, 2, 3, 4, 1};
		assertTrue(isOneSwapForSorted(arrayTrue));
		 arrayTrue = new Object[] {1, 2, 4, 3, 5, 10};
		assertTrue(isOneSwapForSorted(arrayTrue));
		arrayTrue = new Object[] {1, 5, 3, 4, 2, 10};
		assertTrue(isOneSwapForSorted(arrayTrue));
		arrayTrue = new Object[] {"lmn", "ab", "bc", "cd", "a"};
		assertTrue(isOneSwapForSorted(arrayTrue));
	}

}
