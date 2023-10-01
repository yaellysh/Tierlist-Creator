import okhttp3.*;

import java.io.IOException;

public class ChallongeAPIExample {
    public static Response Challonge() throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.challonge.com/v1/tournaments.json?api_key=ROgq5HscsbpCEVvoY6bJGzIviqQjm3EX5CZZPEEO&tournament[name]=new_tour&tournament[tournament_type]=single elimination")
                .method("POST", body)
                .addHeader("Cookie", "refresh_token=dHlGVys0cXoyUkZMYzZxRkRVUjIvamg0WkJnMURpTVcvL1JhOUxPTWhIMHBHdEZ5T01iT2RuOEQxNmNqU2czSnhjNnpRdHRscjJlUVo5ck8zWm15MUpta2VLd09ONmliQnJSNzZOeXRSTHIxRWlGRTd1ZVNYVUwvWUx4Z0crY2hHSHl2eVRQc3FnSVRLK0kvNmRkNUtFem9tZUFSZ0w2S1ZLUzlTYmlKU1ZyTmE1S0U2NklSU3VxK0ttbWRTRkhpbnM2SVJVbk45VEdNSWxoQ04wQ0FxUjhES25GSUNFMG44Wmh6NE13UXNreVg5SjFBZ2MzQ1VkODRURWhubENZM1pMRzdITnBlbHVCV0QwUm1iaEhlcHNNYmtucC9lTkJoYW1XQTRNQ3dJdWcyYW16cUh4R2E5c3RzUFdVYzA4WjItLTgvUmoxbm16Y2Q0VzViUlI1Szl6Tmc9PQ%3D%3D--d8b7799ac8ebc798e30e193aa6e2139e584eeefc")
                .build();

        return client.newCall(request).execute();
    }
    public ChallongeAPIExample() throws IOException {
        ChallongeAPIExample.Challonge();
        }
    }

