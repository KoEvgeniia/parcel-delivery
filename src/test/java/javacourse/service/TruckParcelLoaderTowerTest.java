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
                Parcel.builder().form(new char[][] {{'9','9','9'},{'9','9','9'},{'9','9','9'}}).height(3).width(3).build(),
                Parcel.builder().form(new char[][] {{'7','7','7','7'},{'7','7','7'}}).height(2).width(4).build(),
                Parcel.builder().form(new char[][] {{'1'}}).height(1).width(1).build(),
                Parcel.builder().form(new char[][] {{'1'}}).height(1).width(1).build()
        );
        assertThat(service.loadTruck(parcels))
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(
                        Truck.builder().truckSpace(new char[][] {{'7','7','7','7',0,0},{'7','7','7',0,0,0},{'9','9','9',0,0,0},{'9','9','9',0,0,0},{'9','9','9',0,0,0},{'1',0,0,0,0,0}}).width(6).height(6).build(),
                        Truck.builder().truckSpace(new char[][] {{'1',0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0}}).width(6).height(6).build()
                );
    }
}