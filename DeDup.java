import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 
 * DeDup.java
 * 
 * Purpose: Remove duplicate elements from a specified array and retain the
 * original order.
 *
 * @author Sunil Sahu
 * @version 1.0 05/09/2016
 */

public class DeDup {

	// There are 78 items in list and final count should be 28.
	public static int[] randomIntegers = { 1, 2, 34, 34, 25, 1, 45, 3, 26, 85,
			4, 34, 86, 25, 43, 2, 1, 10000, 11, 16, 19, 1, 18, 4, 9, 3, 20, 17,
			8, 15, 6, 2, 5, 10, 14, 12, 13, 7, 8, 9, 1, 2, 15, 12, 18, 10, 14,
			20, 17, 16, 3, 6, 19, 13, 5, 11, 4, 7, 19, 16, 5, 9, 12, 3, 20, 7,
			15, 17, 10, 6, 1, 8, 18, 4, 14, 13, 2, 11 };

	/**
	 * The main method invokes 3 private methods implemented using different
	 * collection {@link #useLinkedHashSet(int[]) useLinkedHashSet} ,
	 * {@link #useLinkedList(int[]) useLinkedList} and
	 * {@link #useArr(int[]) useArr}
	 *
	 * @param input
	 *            parameter args is not used
	 */
	public static void main(String[] args) {
		useLinkedHashSet(randomIntegers);
		useLinkedList(randomIntegers);
		useArr(randomIntegers);
	}

	/**
	 * 1st preference
	 * 
	 * PROS : LinkedHashSet are good to avoid duplicate values and good for fast insertion & deletion.
	 * 
	 * CONS: Random access is not supported.
	 * 
	 * Use case : Large data volume with look-ups and modifications.
	 * 
	 * @param randomIntegers
	 *            integer array that should be used for removing duplicates.
	 */
	private static void useLinkedHashSet(final int[] randomIntegers) {
		long startTime = System.currentTimeMillis();

		Set<Integer> linkedHashSet = Collections
				.synchronizedSet(new LinkedHashSet<Integer>());
		synchronized (linkedHashSet) {
			for (int index = 0; index < randomIntegers.length; index++) {
				linkedHashSet.add(randomIntegers[index]);
			}
			System.out.println("useLinkedHashSet, Final list : "
					+ linkedHashSet);
		}
		long endTime = System.currentTimeMillis();
		long delta = endTime - startTime;
		System.out
				.println("Matrics: Time taken to execute useLinkedHashSet method = "
						+ delta);
	}

	/**
	 * 2nd preference
	 * 
	 * PROS : LinkedLists are good for fast insertion & deletion.
	 * 
	 * CONS : Performance for lookups might be slower than that of
	 * {@link #useLinkedHashSet(int[]) useLinkedHashSet} method.
	 * 
	 * Use case : medium data volume with look-ups and modifications, compared
	 * to {@link #useLinkedHashSet(int[]) useLinkedHashSet} method.
	 * 
	 * @param randomIntegers
	 *            integer array that should be used for removing duplicates.
	 */
	private static void useLinkedList(final int[] randomIntegers) {
		long startTime = System.currentTimeMillis();
		List<Integer> linkedList = Collections
				.synchronizedList(new LinkedList<Integer>());
		int nextIndex = 0;
		synchronized (linkedList) {
			for (int index = 0; index < randomIntegers.length; index++) {
				if (!linkedList.contains(randomIntegers[index])) {
					linkedList.add(nextIndex++, randomIntegers[index]);
				}
			}
		}
		System.out.println("useLinkedList, Final list : " + linkedList);
		long endTime = System.currentTimeMillis();
		long delta = endTime - startTime;
		System.out
				.println("Matrics: Time taken to execute useLinkedList method = "
						+ delta);
	}

	/**
	 * last preference.
	 * 
	 * PROS : Arrays are good for small set of data.
	 * 
	 * CONS : Arrays are not suitable for insertion/deletion of elements.
	 * 
	 * Use case : Small data volumes requiring more look-ups
	 * 
	 * @param randomIntegerArr
	 *            integer array that should be used for removing duplicates.
	 */

	private static void useArr(int[] randomIntegerArr) {
		long startTime = System.currentTimeMillis();
		synchronized (randomIntegerArr) {

			int count = 0;
			Integer[] finalArray = new Integer[randomIntegerArr.length];
			for (int index = 0; index < randomIntegerArr.length; index++) {
				boolean exists = false;
				for (int j = 0; j < finalArray.length; j++) {
					if (finalArray[j] != null
							&& finalArray[j] == randomIntegerArr[index]) {
						exists = true;
					}
				}
				if (!exists) {
					finalArray[count] = randomIntegerArr[index];
					count++;
				}
			}
			System.out.println("useArr, Final list : "
					+ Arrays.toString(Arrays.copyOf(finalArray, count)));

		}
		long endTime = System.currentTimeMillis();
		long delta = endTime - startTime;
		System.out.println("Matrics: Time taken to execute useArr method = "
				+ delta);
	}

}
