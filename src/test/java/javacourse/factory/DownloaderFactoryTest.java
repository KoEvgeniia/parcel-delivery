package javacourse.factory;

import javacourse.domain.InputDataType;
import javacourse.util.ParcelDownloader;
import javacourse.util.TruckDownloader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DownloaderFactoryTest {
    @Test
    public void createFileDownloaderTruck() {
        DownloaderFactory factory = new DownloaderFactory();
        assertThat(factory.createFileDownloader(InputDataType.TRUCK)).isInstanceOf(ParcelDownloader.class);
    }

    @Test
    public void createFileDownloaderParcel() {
        DownloaderFactory factory = new DownloaderFactory();
        assertThat(factory.createFileDownloader(InputDataType.PARCEL)).isInstanceOf(TruckDownloader.class);
    }
}