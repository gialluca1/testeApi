package runner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class FileCreate {

    public static void createJson(String dirPath){

        JSONParser parser = new JSONParser();
        JSONArray report = new JSONArray();

        try {

            for (File file : new File(dirPath).listFiles()) {
                if (file.isFile() && file.getName().startsWith("features") && file.getName().endsWith(".json")) {
                    JSONArray jsonArray = new JSONArray();
                    try {
                        jsonArray = (JSONArray) parser.parse(new FileReader(file));
                    } catch (Exception e) {
                    }

                    if (jsonArray.size() > 0) {

                        // Try to add scenario to feature
                        String scenarioURI = (String) ((JSONObject) jsonArray.get(0)).get("uri");
                        JSONArray scenarioElements = (JSONArray) ((JSONObject) jsonArray.get(0)).get("elements");
                        Boolean featureFound = false;

                        for (int i = 0; i < report.size(); i++) {
                            String featureURI = (String) ((JSONObject) report.get(i)).get("uri");

                            if (featureURI.equals(scenarioURI)) {

                                JSONArray featureElements = (JSONArray) ((JSONObject) report.get(i)).get("elements");
                                featureElements.addAll(scenarioElements);

                                featureFound = true;
                            }
                        }

                        // Add new feature
                        if (featureFound == false) {
                            report.add(jsonArray.get(0));
                        }
                    }
                }
            }

            File jsonFile = new File(dirPath + "/Run.json");
            Writer jsonWriter = new BufferedWriter(new FileWriter(jsonFile.getAbsoluteFile()));
            PrintWriter jsonOut = new PrintWriter(jsonWriter);

            try {
                jsonOut.print(report.toString());
                jsonOut.flush();
                jsonWriter.flush();
            } finally {
                jsonWriter.close();
                jsonOut.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
