package edu;

import java.util.*;

public class SortedMapTest {
	SortedMap<Integer, String> map;
	
	SortedMapTest() {
		map = new TreeMap<Integer, String>();
		
		map.put(4,  "Four");
		map.put(2, "Two");
		map.put(3,  "Three");
		map.put(-4,  "Minus Four");
		map.put(5, "Five");
		map.put(1, "One");
		
		for (Map.Entry<Integer, String> mapElement : map.entrySet()) {
            int key = (int)mapElement.getKey();
            
            // Finding the value
            String value = (String)mapElement.getValue();
            
            System.out.println(key + " : " + value);
        }
	}
	
	public static void main(String args[]) {
		new SortedMapTest();
	}
}
