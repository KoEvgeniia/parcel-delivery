package javacourse.service;

import javacourse.domain.Parcel;
import javacourse.domain.Truck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TruckParcelUnloaderTest {
    private TruckParcelUnloader unloader;

    @BeforeEach
    void setUp() {
        unloader = new TruckParcelUnloader();
    }

    @Test
    public void unloadOneTruckOneParcel() {
        List<Truck> trucks = List.of(
                Truck.builder().truckSpace(new Character[][]{{'3', '3', '3', null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}})
                        .width(6)
                        .height(6)
                        .parcels(List.of(Parcel.builder().form(new Character[][]{{'3', '3', '3'}}).width(3).height(1).build()))
                        .build());
        assertThat(unloader.unloadTruck(trucks)).containsExactlyInAnyOrder(
                Parcel.builder().form(new Character[][]{{'3', '3', '3'}}).width(3).height(1).build()
        );
    }

    @Test
    public void unloadOneTruckManyParcels() {
        List<Truck> trucks = List.of(
                Truck.builder().truckSpace(new Character[][]{{'7', '7', '7', '7', null, null}, {'7', '7', '7', null, null, null}, {'9', '9', '9', null, null, null}, {'9', '9', '9', null, null, null}, {'9', '9', '9', null, null, null}, {'1', null, null, null, null, null}})
                        .width(6)
                        .height(6)
                        .parcels(List.of(Parcel.builder().form(new Character[][]{{'7', '7', '7', '7'}, {'7', '7', '7'}}).height(2).width(4).build(),
                                Parcel.builder().form(new Character[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}).height(3).width(3).build(),
                                Parcel.builder().form(new Character[][]{{'1'}}).height(1).width(1).build()))
                        .build());
        assertThat(unloader.unloadTruck(trucks)).containsExactlyInAnyOrder(
                Parcel.builder().form(new Character[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}).width(3).height(3).build(),
                Parcel.builder().form(new Character[][]{{'7', '7', '7', '7'}, {'7', '7', '7'}}).width(4).height(2).build(),
                Parcel.builder().form(new Character[][]{{'1'}}).width(1).height(1).build()
        );
    }

    @Test
    public void unloadManyTrucks() {
        List<Truck> trucks = List.of(
                Truck.builder().truckSpace(new Character[][]{{'7', '7', '7', '7', null, null}, {'7', '7', '7', null, null, null}, {'9', '9', '9', null, null, null}, {'9', '9', '9', null, null, null}, {'9', '9', '9', null, null, null}, {'1', null, null, null, null, null}})
                        .width(6)
                        .height(6)
                        .parcels(List.of(Parcel.builder().form(new Character[][]{{'7', '7', '7', '7'}, {'7', '7', '7'}}).width(4).height(2).build(),
                                Parcel.builder().form(new Character[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}).width(3).height(3).build(),
                                Parcel.builder().form(new Character[][]{{'1'}}).width(1).height(1).build()))
                        .build(),
                Truck.builder().truckSpace(new Character[][]{{'5', '5', '5', '5', '5', null}, {'7', '7', '7', '7', null, null}, {'7', '7', '7', null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}})
                        .width(6)
                        .height(6)
                        .parcels(List.of(Parcel.builder().form(new Character[][]{{'5', '5', '5', '5', '5'}}).width(5).height(1).build(),
                                Parcel.builder().form(new Character[][]{{'7', '7', '7', '7'}, {'7', '7', '7'}}).width(4).height(2).build()))
                        .build());
        assertThat(unloader.unloadTruck(trucks)).containsExactlyInAnyOrder(
                Parcel.builder().form(new Character[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}).width(3).height(3).build(),
                Parcel.builder().form(new Character[][]{{'7', '7', '7', '7'}, {'7', '7', '7'}}).width(4).height(2).build(),
                Parcel.builder().form(new Character[][]{{'1'}}).width(1).height(1).build(),
                Parcel.builder().form(new Character[][]{{'7', '7', '7', '7'}, {'7', '7', '7'}}).width(4).height(2).build(),
                Parcel.builder().form(new Character[][]{{'5', '5', '5', '5', '5'}}).width(5).height(1).build()
        );
    }

    @Test
    public void unloadEmptyTruck() {
        assertThat(unloader.unloadTruck(Collections.emptyList())).isEqualTo(Collections.emptyList());
    }
}