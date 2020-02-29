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

package de.linzn.simplyConfiguration.iohub;

import java.util.List;
import java.util.Map;

public abstract class ConfigurationOptions {


    public boolean contains(String path) {
        return get(path) != null;
    }

    public Object get(String path, Object defaultValue) {
        if (!contains(path)) {
            set(path, defaultValue);
        }
        return get(path);
    }

    public boolean getBoolean(String path) {
        return (boolean) get(path);
    }

    public boolean getBoolean(String path, boolean def) {
        return (boolean) get(path, def);
    }

    public List<Boolean> getBooleanList(String path) {
        return (List<Boolean>) get(path);
    }

    public List<Byte> getByteList(String path) {
        return (List<Byte>) get(path);
    }

    public List<Character> getCharacterList(String path) {
        return (List<Character>) get(path);
    }

    public double getDouble(String path) {
        return (double) get(path);
    }

    double getDouble(String path, double def) {
        return (double) get(path, def);
    }

    public List<Double> getDoubleList(String path) {
        return (List<Double>) get(path);
    }

    public List<Float> getFloatList(String path) {
        return (List<Float>) get(path);
    }

    public int getInt(String path) {
        return (int) get(path);
    }

    public int getInt(String path, int def) {
        return (int) get(path, def);
    }

    public List<Integer> getIntegerList(String path) {
        return (List<Integer>) get(path);
    }

    public List<?> getList(String path) {
        return (List<?>) get(path);
    }

    public List<?> getList(String path, List<?> def) {
        return (List<?>) get(path, def);
    }

    public long getLong(String path) {
        return (long) get(path);
    }

    public long getLong(String path, long def) {
        return (long) get(path, def);
    }

    public List<Long> getLongList(String path) {
        return (List<Long>) get(path);
    }

    public List<Map<?, ?>> getMapList(String path) {
        return (List<Map<?, ?>>) get(path);
    }

    public List<Short> getShortList(String path) {
        return (List<Short>) get(path);
    }

    public String getString(String path) {
        return (String) get(path);
    }

    public String getString(String path, String def) {
        return (String) get(path, def);
    }

    public List<String> getStringList(String path) {
        return (List<String>) get(path);
    }


    public abstract void set(String path, Object value);

    public abstract Object get(String path);

}
