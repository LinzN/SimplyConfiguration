/*
 * Copyright (C) 2020. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.simplyConfiguration;

import de.linzn.simplyConfiguration.iohub.ConfigurationOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class FileConfiguration extends ConfigurationOptions {

    protected Map<String, Object> objectMap;

    protected FileConfiguration(Map<String, Object> objectMap) {
        this.objectMap = objectMap;
    }

    private static String[] splitPath(String path) {
        return path.split(Pattern.quote("."));
    }

    @Override
    public void set(String path, Object value) {
        String[] splitPath = splitPath(path);
        Map<String, Object> tempMap = this.objectMap;
        for (int i = 0; i < splitPath.length; i++) {
            if (i != splitPath.length - 1) {
                if (tempMap.containsKey(splitPath[i])) {
                    if (tempMap.get(splitPath[i]) instanceof HashMap) {
                        tempMap = (Map<String, Object>) tempMap.get(splitPath[i]);
                    } else {
                        tempMap.put(splitPath[i], new HashMap<>());
                        tempMap = (Map<String, Object>) tempMap.get(splitPath[i]);
                    }
                } else {
                    tempMap.put(splitPath[i], new HashMap<>());
                    tempMap = (Map<String, Object>) tempMap.get(splitPath[i]);
                }
            } else {
                tempMap.put(splitPath[i], value);
            }
        }
    }


    @Override
    public Object get(String path) {
        String[] splitPath = splitPath(path);
        Map<String, Object> tempMap = this.objectMap;
        for (int i = 0; i < splitPath.length; i++) {
            if (i != splitPath.length - 1) {
                if (tempMap.containsKey(splitPath[i])) {
                    if (tempMap.get(splitPath[i]) instanceof HashMap) {
                        tempMap = (Map<String, Object>) tempMap.get(splitPath[i]);
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return tempMap.get(splitPath[i]);
            }

        }
        return null;
    }

    public abstract void load();

    public abstract void save();
}
