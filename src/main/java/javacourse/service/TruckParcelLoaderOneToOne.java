package javacourse.service;

import javacourse.domain.Parcel;
import javacourse.domain.Truck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TruckParcelLoaderOneToOne implements TruckParcelLoader {
    final int TRUCK_HEIGHT = 6;
    final int TRUCK_WIDTH = 6;
    public List<Truck> loadTruck(List<Parcel> parcels) {
        return parcels.stream().map(parcel -> Truck.builder()
                .truckSpace(parcel.getForm())
                .width(TRUCK_WIDTH)
                .height(TRUCK_HEIGHT)
                .build()).toList();
    }

    public void showTrucks(List<Truck> trucks) {
        trucks.forEach(truck -> System.out.println(truck.toString()));
    }
}
