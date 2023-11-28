package data_access;

import entity.Item;
import entity.TierList;
import use_case.generate.random_tierlist.RandomTierListDataAccessInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class ChatGPTDataAccessObject implements RandomTierListDataAccessInterface {

    public static String chatGPT(String prompt) throws IOException {

        // https://rollbar.com/blog/how-to-use-chatgpt-api-with-java/ this website was used to help integrate the ChatGPT
        // API with Java

        String apiKey = System.getenv("OPENAI_API_KEY");
        String url = "https://api.openai.com/v1/chat/completions";
        String model = "gpt-3.5-turbo";

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        connection.setRequestProperty("Content-Type", "application/json");

        // The request body
        String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
        connection.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(body);
        writer.flush();
        writer.close();

        // Response from ChatGPT
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;

        StringBuilder response = new StringBuilder();

        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();
        return extractMessageFromJSONResponse(response.toString());
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content") + 11;

        int end = response.indexOf("\"", start);

        return response.substring(start, end);

    }


    @Override
    public List<Item> generateTierList(String prompt) {
        try {
            String result = chatGPT(prompt); // i currently have no key :((
            List<String> list = List.of(result.split("\n"));
            list.forEach(System.out::println);

            if (list.size() != TierList.LENGTH) {
                return null;
            }
            return list.stream().map(Item::new).collect(Collectors.toList());
        }
        catch (IOException e) {
            return null;
        }
    }
}

