package javacourse.factory;

import javacourse.domain.InputDataType;
import javacourse.util.FileDownloader;
import javacourse.util.ParcelDownloader;
import javacourse.util.TruckDownloader;

public class DownloaderFactory {
    public FileDownloader createFileDownloader(InputDataType inputDataType) {
        return switch (inputDataType) {
            case TRUCK -> new ParcelDownloader();
            case PARCEL -> new TruckDownloader();
        };
    }
}
