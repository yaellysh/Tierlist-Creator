package entity;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class TierAdapter extends TypeAdapter<Tier> {
    @Override
    public void write(JsonWriter jsonWriter, Tier tier) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("name");
        jsonWriter.value(tier.getName());
        jsonWriter.endObject();
    }

    @Override
    public Tier read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
