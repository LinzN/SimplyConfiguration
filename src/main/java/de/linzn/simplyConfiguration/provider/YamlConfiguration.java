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

package de.linzn.simplyConfiguration.provider;

import de.linzn.simplyConfiguration.FileConfiguration;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.LinkedHashMap;

public class YamlConfiguration extends FileConfiguration {

    private File file;
    private Yaml yaml;

    private YamlConfiguration(File file) {
        super(new LinkedHashMap<>());
        this.file = file;
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        this.yaml = new Yaml(options);
    }

    public static FileConfiguration loadConfiguration(File file) {
        FileConfiguration yamlConfiguration = new YamlConfiguration(file);
        yamlConfiguration.load();
        return yamlConfiguration;
    }

    @Override
    public void load() {
        if (this.file.exists() && this.file.isFile()) {
            try {
                InputStream inputStream = new FileInputStream(this.file);
                objectMap = yaml.load(inputStream);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (this.objectMap == null) {
            this.objectMap = new LinkedHashMap<>();
        }
    }

    @Override
    public void save() {
        StringWriter writer = new StringWriter();
        yaml.dump(this.objectMap, writer);

        if (this.file.getParentFile() != null && !this.file.getParentFile().exists()) {
            this.file.getParentFile().mkdir();
        }

        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(writer.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
