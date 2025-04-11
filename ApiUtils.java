import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiUtils {

    public static String fetchDataAsString(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                return response.toString(); // Return the response as a String

            } else {
                System.err.println("API request failed with response code: " + responseCode);
                return null; // Or throw an exception
            }

        } finally {
            connection.disconnect();
        }
    }

    // Example Usage:
    public static void main(String[] args) throws IOException {
        String apiUrl = "https://jsonplaceholder.typicode.com/todos/1";

        String jsonResponse = fetchDataAsString(apiUrl);

        if (jsonResponse != null) {
            System.out.println(jsonResponse);
        }
    }
}
