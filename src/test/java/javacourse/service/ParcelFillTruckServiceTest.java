package javacourse.service;

import javacourse.domain.Parcel;
import javacourse.domain.Truck;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParcelFillTruckServiceTest {
    @Test
    public void correctFillTruck() {
        ParcelFillTruckService service = new ParcelFillTruckService();
        List<Parcel> parcels = List.of(
                Parcel.builder().Level1("999").Level2("999").Level3("999").build(),
                Parcel.builder().Level1("7777").Level2("777").Level3("").build(),
                Parcel.builder().Level1("1").Level2("").Level3("").build()
        );
        assertThat(service.fillTruck(parcels))
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(
                        Truck.builder().Level1("7777  ").Level2("777   ").Level3("      ").Level4("      ").Level5("      ").Level6("      ").build(),
                        Truck.builder().Level1("999   ").Level2("999   ").Level3("999   ").Level4("      ").Level5("      ").Level6("      ").build(),
                        Truck.builder().Level1("1     ").Level2("      ").Level3("      ").Level4("      ").Level5("      ").Level6("      ").build()
                );
    }
}