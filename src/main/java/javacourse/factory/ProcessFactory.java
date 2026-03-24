package javacourse.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import javacourse.controller.ParcelProcess;
import javacourse.controller.ProcessController;
import javacourse.controller.TruckProcess;
import javacourse.domain.InputDataType;
import javacourse.util.ParcelParser;
import javacourse.util.TruckParser;

public class ProcessFactory {
    public ProcessController createProcessController(InputDataType inputDataType, ObjectMapper mapper) {
        return switch (inputDataType) {
            case TRUCK ->
                    new TruckProcess(new TruckParser(mapper), new DownloaderFactory().createFileDownloader(inputDataType),mapper);
            case PARCEL ->
                    new ParcelProcess(new ParcelParser(), new LoaderFactory(), new DownloaderFactory().createFileDownloader(inputDataType),mapper);
        };
    }
}
