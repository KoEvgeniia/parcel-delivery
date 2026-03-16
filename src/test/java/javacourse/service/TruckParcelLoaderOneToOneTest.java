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
                Parcel.builder().form(new char[][] {{'9','9','9'},{'9','9','9'},{'9','9','9'}}).width(3).height(3).build(),
                Parcel.builder().form(new char[][] {{'7','7','7','7'},{'7','7','7'}}).width(4).height(2).build(),
                Parcel.builder().form(new char[][] {{'1'}}).width(1).height(1).build()
        );
        assertThat(service.loadTruck(parcels))
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(
                        Truck.builder().truckSpace(new char[][] {{'7','7','7','7'},{'7','7','7'}}).height(6).width(6).build(),
                        Truck.builder().truckSpace(new char[][] {{'9','9','9'},{'9','9','9'},{'9','9','9'}}).height(6).width(6).build(),
                        Truck.builder().truckSpace(new char[][] {{'1'}}).height(6).width(6).build()
                );
    }
}