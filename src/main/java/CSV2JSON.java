import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CSV2JSON {
    public static void main(String[] args) throws Exception {

        //TODO: Clean POM as some resources are not being used
        File input = new File("C:/Users/gomez/IdeaProjects/utepcs/csvjson.csv");

        List<String[]> content = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.add(line.split(","));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] parameters = new String[23];
        String[] values = new String[21];
        JSONObject obj = new JSONObject();
        JSONArray paramInfo = new JSONArray();
        JSONArray paramInfo2 = new JSONArray();
        String param;
        String value;
        int count = 0;

        for(int i = 0; i < content.get(0).length; i++){
            parameters[i] = content.get(0)[i];
        }

        for(int i = 0 ; i < content.get(1).length; i++){
            if(i == 9 || i == 15){
                values[count] = content.get(1)[i + 1];
                i++;
            } else {
                values[count] = content.get(1)[i];
            }
            count++;
        }

        for(int i = 0 ; i <= 8; i++){
            param = parameters[i];
            value = values[i];
            obj.put(param, value);
            System.out.println(param + " ... " + value);
        }

        //First paramInfo array
        count = 9;
        for(int i = 10; i <= 14; i++){
            param = parameters[i];
            value = values[count];
            count++;
            paramInfo.add(param + ": " + value);
            System.out.println(param + " ... " + value);
        }

        obj.put("paramInfo", paramInfo);

        //Second paramInfo array
        for(int i = 16; i <= 20; i++){
            param = parameters[i];
            value = values[count];
            count++;
            paramInfo2.add(param + ": " + value);
            System.out.println(param + " ... " + value);
        }

        obj.put("infoDeParametro", paramInfo2);

        //TODO: Make these last two parameters arrays (as it will have more than one value)
        for(int i = 21 ; i <= 22; i++){
            param = parameters[i];
            value = values[count];
            count++;
            obj.put(param, value);
            System.out.println(param + " ... " + value);
        }

        try (FileWriter file = new FileWriter("/Users/gomez/IdeaProjects/utepcs/output.json")) {
            file.write(obj.toJSONString());
            System.out.println("\nSuccessfully Copied JSON Object to File...");
            System.out.println("JSON FILE: " + obj.toJSONString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}