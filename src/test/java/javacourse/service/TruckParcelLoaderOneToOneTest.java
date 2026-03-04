package javacourse.service;

import javacourse.domain.Parcel;
import javacourse.domain.Truck;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TruckParcelLoaderOneToOneTest {
    @Test
    public void correctLoadTruck() {
        TruckParcelLoaderOneToOne service = new TruckParcelLoaderOneToOne();
        List<Parcel> parcels = List.of(
                Parcel.builder().level1("999").level2("999").level3("999").build(),
                Parcel.builder().level1("7777").level2("777").level3("").build(),
                Parcel.builder().level1("1").level2("").level3("").build()
        );
        assertThat(service.loadTruck(parcels))
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(
                        Truck.builder().level1("7777  ").level2("777   ").level3("      ").level4("      ").level5("      ").level6("      ").build(),
                        Truck.builder().level1("999   ").level2("999   ").level3("999   ").level4("      ").level5("      ").level6("      ").build(),
                        Truck.builder().level1("1     ").level2("      ").level3("      ").level4("      ").level5("      ").level6("      ").build()
                );
    }
}