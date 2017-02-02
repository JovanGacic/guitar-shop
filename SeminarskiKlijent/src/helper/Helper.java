/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jovan
 */
public class Helper {
    private Map<String, Object> map;
    private static Helper instance;

    private Helper() {
        map = new HashMap<>();
    }
    
    public void put(String key, Object value) {
        map.put(key, value);
    }
    
    public Object get(String key) {
        return map.get(key);
    }
    
    public void remove(String key) {
        map.remove(key);
    }
    
    public static Helper getInstance() {
        if (instance == null) {
            instance = new Helper();
        }
        return instance;
    }
}
