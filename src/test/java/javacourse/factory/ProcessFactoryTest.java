package javacourse.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import javacourse.controller.ParcelProcess;
import javacourse.controller.TruckProcess;
import javacourse.domain.InputDataType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProcessFactoryTest {
    @Test
    public void createProcessControllerTruck() {
        ProcessFactory processFactory = new ProcessFactory();
        assertThat(processFactory.createProcessController(InputDataType.TRUCK, new ObjectMapper())).isInstanceOf(TruckProcess.class);
    }

    @Test
    public void createProcessControllerParcel() {
        ProcessFactory processFactory = new ProcessFactory();
        assertThat(processFactory.createProcessController(InputDataType.PARCEL, new ObjectMapper())).isInstanceOf(ParcelProcess.class);
    }
}