package com.goeuro.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GoEuroCSVWriter {

    public static void writeToCSV(String fileName, List<HashMap> dataMaps) {
        try (FileWriter writer = new FileWriter(fileName)) {
            System.out.println("Writing to file " + fileName);
            writer.append("_type,_id,name,type,latitude,longitude\n");

            String typeS;
            String id;
            String name;
            String type;
            String longitude;
            String latitude;

            for (HashMap map : dataMaps) {
                typeS = map.get("_type") != null ? (String) map.get("_type") : null;
                id = map.get("_id") != null ? map.get("_id").toString() : null;
                name = map.get("name") != null ? (String) map.get("name") : null;
                type = map.get("type") != null ? (String) map.get("type") : null;
                longitude = null;
                latitude = null;
                if (map.get("geo_position") != null) {
                    Map geo = (HashMap<String, Object>) map.get("geo_position");
                    latitude = geo.get("latitude").toString();
                    longitude = geo.get("longitude").toString();
                }

                writer.append(typeS).append(",");
                writer.append(id).append(",");
                writer.append(name).append(",");
                writer.append(type).append(",");
                writer.append(latitude).append(",");
                writer.append(longitude).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Exception parsing data");
            e.printStackTrace();
        }
    }

}

