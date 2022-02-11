package de.bambussoft.meaningfulstrings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Analyse {

    public static void main(String[] args) throws IOException {
        AnalyserBuilder analyserBuilder = new AnalyserBuilder();
        StringAnalyser build = analyserBuilder.build();
        Path stringsPath = Path.of(args[0]);
        Stream<String> lines = Files.lines(stringsPath);

        // Build Model
        StringAnalysis stringAnalysis = StringAnalysis.of(lines);
        StringProbability stringProbability = StringProbability.of(stringAnalysis);

        // Evaluate Model
        lines = Files.lines(stringsPath);
        WordProbability wordProbability = WordProbability.build(stringProbability, lines, 0.05f);
        System.out.println("Finished");
    }
}
