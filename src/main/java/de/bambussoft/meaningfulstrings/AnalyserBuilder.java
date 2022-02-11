package de.bambussoft.meaningfulstrings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AnalyserBuilder {

    public static final Charset CHARSET = StandardCharsets.UTF_8;

    public StringAnalyser build() {
        InputStream inputList = getClass().getClassLoader().getResourceAsStream("german");
        List<String> lines = toLineList(inputList);

        // Build Model
        StringAnalysis stringAnalysis = StringAnalysis.of(lines.stream());
        StringProbability stringProbability = StringProbability.of(stringAnalysis);

        // Evaluate Model
        return WordProbability.build(stringProbability, lines.stream(), 0.05f);
    }

    private List<String> toLineList(InputStream is) {
        List<String> lines = new ArrayList<>();
        try (InputStreamReader streamReader = new InputStreamReader(is, CHARSET);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
