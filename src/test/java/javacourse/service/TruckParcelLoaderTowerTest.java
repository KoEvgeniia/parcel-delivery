package javacourse.service;

import javacourse.domain.Parcel;
import javacourse.domain.Truck;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TruckParcelLoaderTowerTest {
    @Test
    public void correctLoadTruck() {
        TruckParcelLoaderTower service = new TruckParcelLoaderTower();
        List<Parcel> parcels = List.of(
                Parcel.builder().level1("999").level2("999").level3("999").height(3).width(3).build(),
                Parcel.builder().level1("7777").level2("777").level3("").height(2).width(4).build(),
                Parcel.builder().level1("1").level2("").level3("").height(1).width(1).build(),
                Parcel.builder().level1("1").level2("").level3("").height(1).width(1).build()
        );
        assertThat(service.loadTruck(parcels))
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(
                        Truck.builder().level1("7777  ").level2("777   ").level3("999   ").level4("999   ").level5("999   ").level6("1     ").build(),
                        Truck.builder().level1("1     ").level2("      ").level3("      ").level4("      ").level5("      ").level6("      ").build()
                );
    }
}