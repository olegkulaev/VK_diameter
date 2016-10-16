package exporters;

import models.ChainElement;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVChainExporter {
    private static final String NEW_LINE_SEPARATOR = "\r\n";

    public void export(String fileName, List<List<ChainElement>> chains) throws IOException {
        FileWriter fileWriter = null;
        CSVPrinter csvPrinter = null;
        CSVFormat csvFileFormat = CSVFormat.RFC4180.withRecordSeparator(NEW_LINE_SEPARATOR);
        try {
            fileWriter = new FileWriter(fileName);
            csvPrinter = new CSVPrinter(fileWriter, csvFileFormat);
            for (List<ChainElement> chain:
                chains){
                List<String> csvRecord = new ArrayList<>();
                csvRecord.add(String.valueOf(chain.size()));
                csvRecord.addAll(chain
                        .stream()
                        .map(chainElement ->
                                String.valueOf(chainElement.getFriendsCount()))
                        .collect(Collectors.toList()));
                csvPrinter.printRecord(csvRecord);
            }
        } finally {
            if (fileWriter != null) {
                fileWriter.flush();
                fileWriter.close();
            }
            if (csvPrinter != null) {
                csvPrinter.close();
            }
        }
    }
}
