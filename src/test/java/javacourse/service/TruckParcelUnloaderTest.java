package javacourse.service;

import javacourse.domain.Parcel;
import javacourse.domain.Truck;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TruckParcelUnloaderTest {
    @Test
    public void unloadOneTruckOneParcel() {
        TruckParcelUnloader unloader = new TruckParcelUnloader();
        List<Truck> trucks = List.of(
                Truck.builder().truckSpace(new Character[][]{{'3', '3', '3', null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}).width(6).height(6).build());
        assertThat(unloader.unloadTruck(trucks)).containsExactlyInAnyOrder(
                Parcel.builder().form(new Character[][]{{'3', '3', '3'}}).width(3).height(1).build()
        );
    }

    @Test
    public void unloadOneTruckManyParcels() {
        TruckParcelUnloader unloader = new TruckParcelUnloader();
        List<Truck> trucks = List.of(
                Truck.builder().truckSpace(new Character[][]{{'7', '7', '7', '7', null, null}, {'7', '7', '7', null, null, null}, {'9', '9', '9', null, null, null}, {'9', '9', '9', null, null, null}, {'9', '9', '9', null, null, null}, {'1', null, null, null, null, null}}).width(6).height(6).build());
        assertThat(unloader.unloadTruck(trucks)).containsExactlyInAnyOrder(
                Parcel.builder().form(new Character[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}).width(3).height(3).build(),
                Parcel.builder().form(new Character[][]{{'7', '7', '7', '7'}, {'7', '7', '7', null}}).width(4).height(2).build(),
                Parcel.builder().form(new Character[][]{{'1'}}).width(1).height(1).build()
        );
    }

    @Test
    public void unloadManyTrucks() {
        TruckParcelUnloader unloader = new TruckParcelUnloader();
        List<Truck> trucks = List.of(
                Truck.builder().truckSpace(new Character[][]{{'7', '7', '7', '7', null, null}, {'7', '7', '7', null, null, null}, {'9', '9', '9', null, null, null}, {'9', '9', '9', null, null, null}, {'9', '9', '9', null, null, null}, {'1', null, null, null, null, null}}).width(6).height(6).build(),
                Truck.builder().truckSpace(new Character[][]{{'5', '5', '5', '5', '5', null}, {'7', '7', '7', '7', null, null}, {'7', '7', '7', null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}).width(6).height(6).build());
        assertThat(unloader.unloadTruck(trucks)).containsExactlyInAnyOrder(
                Parcel.builder().form(new Character[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}).width(3).height(3).build(),
                Parcel.builder().form(new Character[][]{{'7', '7', '7', '7'}, {'7', '7', '7', null}}).width(4).height(2).build(),
                Parcel.builder().form(new Character[][]{{'1'}}).width(1).height(1).build(),
                Parcel.builder().form(new Character[][]{{'7', '7', '7', '7'}, {'7', '7', '7', null}}).width(4).height(2).build(),
                Parcel.builder().form(new Character[][]{{'5', '5', '5', '5', '5'}}).width(5).height(1).build()
        );
    }

    @Test
    public void unloadEmptyTruck() {
        TruckParcelUnloader unloader = new TruckParcelUnloader();
        assertThat(unloader.unloadTruck(Collections.emptyList())).isEqualTo(Collections.emptyList());
    }
}