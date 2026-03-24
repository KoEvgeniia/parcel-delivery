package javacourse.service;

import javacourse.domain.Parcel;
import javacourse.domain.Truck;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TruckParcelLoaderOneToOneTest {
    @Test
    public void loadTruckManyParcels() {
        TruckParcelLoaderOneToOne service = new TruckParcelLoaderOneToOne();
        List<Parcel> parcels = List.of(
                Parcel.builder().form(new String[][]{{"9", "9", "9"}, {"9", "9", "9"}, {"9", "9", "9"}}).width(3).height(3).build(),
                Parcel.builder().form(new String[][]{{"7", "7", "7", "7"}, {"7", "7", "7"}}).width(4).height(2).build(),
                Parcel.builder().form(new String[][]{{"1"}}).width(1).height(1).build()
        );
        assertThat(service.loadTruck(parcels, 3L))
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(
                        Truck.builder().truckSpace(new String[][]{{"7", "7", "7", "7"}, {"7", "7", "7"}}).height(6).width(6).build(),
                        Truck.builder().truckSpace(new String[][]{{"9", "9", "9"}, {"9", "9", "9"}, {"9", "9", "9"}}).height(6).width(6).build(),
                        Truck.builder().truckSpace(new String[][]{{"1"}}).height(6).width(6).build()
                );
    }

    @Test
    public void loadTruckOneParcel() {
        TruckParcelLoaderOneToOne service = new TruckParcelLoaderOneToOne();
        List<Parcel> parcels = List.of(
                Parcel.builder().form(new String[][]{{"9", "9", "9"}, {"9", "9", "9"}, {"9", "9", "9"}}).width(3).height(3).build()
        );
        assertThat(service.loadTruck(parcels, 1L))
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(
                        Truck.builder().truckSpace(new String[][]{{"9", "9", "9"}, {"9", "9", "9"}, {"9", "9", "9"}}).height(6).width(6).build()
                );
    }
}