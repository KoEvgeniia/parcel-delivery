package javacourse.service;

import javacourse.domain.Parcel;
import javacourse.domain.Truck;
import javacourse.exception.TruckNotEnoughException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TruckParcelLoaderTowerTest {
    @Test
    public void loadTruckMayParcels() {
        TruckParcelLoaderTower service = new TruckParcelLoaderTower();
        List<Parcel> parcels = List.of(
                Parcel.builder().form(new String[][]{{"9", "9", "9"}, {"9", "9", "9"}, {"9", "9", "9"}}).height(3).width(3).build(),
                Parcel.builder().form(new String[][]{{"7", "7", "7", "7"}, {"7", "7", "7"}}).height(2).width(4).build(),
                Parcel.builder().form(new String[][]{{"1"}}).height(1).width(1).build(),
                Parcel.builder().form(new String[][]{{"1"}}).height(1).width(1).build()
        );
        assertThat(service.loadTruck(parcels, 6L))
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(
                        Truck.builder().truckSpace(new String[][]{{"7", "7", "7", "7", null, null}, {"7", "7", "7", null, null, null}, {"9", "9", "9", null, null, null}, {"9", "9", "9", null, null, null}, {"9", "9", "9", null, null, null}, {"1", null, null, null, null, null}}).width(6).height(6).build(),
                        Truck.builder().truckSpace(new String[][]{{"1", null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}).width(6).height(6).build()
                );
    }

    @Test
    public void loadTruckOneParcel() {
        TruckParcelLoaderTower service = new TruckParcelLoaderTower();
        List<Parcel> parcels = List.of(
                Parcel.builder().form(new String[][]{{"9", "9", "9"}, {"9", "9", "9"}, {"9", "9", "9"}}).height(3).width(3).build()
        );
        assertThat(service.loadTruck(parcels, 6L))
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(
                        Truck.builder().truckSpace(new String[][]{
                                {"9", "9", "9", null, null, null},
                                {"9", "9", "9", null, null, null},
                                {"9", "9", "9", null, null, null},
                                {null, null, null, null, null, null},
                                {null, null, null, null, null, null},
                                {null, null, null, null, null, null}}).width(6).height(6).build()
                );
    }

    @Test
    public void loadTruckNotEnoughException() {
        TruckParcelLoaderTower service = new TruckParcelLoaderTower();
        List<Parcel> parcels = List.of(
                Parcel.builder().form(new String[][]{{"9", "9", "9"}, {"9", "9", "9"}, {"9", "9", "9"}}).height(3).width(3).build(),
                Parcel.builder().form(new String[][]{{"7", "7", "7", "7"}, {"7", "7", "7"}}).height(2).width(4).build(),
                Parcel.builder().form(new String[][]{{"1"}}).height(1).width(1).build(),
                Parcel.builder().form(new String[][]{{"1"}}).height(1).width(1).build()
        );
        assertThrows(TruckNotEnoughException.class, () -> service.loadTruck(parcels, 1L));
    }
}