package javacourse.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import javacourse.controller.ParcelProcess;
import javacourse.controller.TruckProcess;
import javacourse.domain.InputDataType;
import javacourse.repository.ParcelRepository;
import javacourse.service.ParcelHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProcessFactoryTest {
    private ObjectMapper mapper;
    private ParcelHandler parcelHandler;
    private ProcessFactory processFactory;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        parcelHandler = new ParcelHandler(new ParcelRepository());
        processFactory = new ProcessFactory();
    }

    @Test
    public void createProcessControllerTruck() {
        assertThat(processFactory.createProcessController(InputDataType.UNLOAD, mapper, parcelHandler)).isInstanceOf(TruckProcess.class);
    }

    @Test
    public void createProcessControllerParcel() {
        assertThat(processFactory.createProcessController(InputDataType.LOAD, mapper, parcelHandler)).isInstanceOf(ParcelProcess.class);
    }
}