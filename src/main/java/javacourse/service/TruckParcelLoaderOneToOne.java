package javacourse.service;

import javacourse.domain.Parcel;
import javacourse.domain.Truck;
import javacourse.exception.TruckNotEnoughException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Loads trucks
 * One parcel in one truck
 */
@Slf4j
@RequiredArgsConstructor
public class TruckParcelLoaderOneToOne implements TruckParcelLoader {
    final int TRUCK_HEIGHT = 6;
    final int TRUCK_WIDTH = 6;

    /**
     * Loads trucks
     *
     * @param parcels    list of parcels for loading
     * @param truckCount number of trucks
     * @return list of trucks
     */
    public List<Truck> loadTruck(List<Parcel> parcels, Long truckCount) {
        if (parcels.size() > truckCount) {
            throw new TruckNotEnoughException(truckCount);
        }
        return parcels.stream().map(parcel -> Truck.builder()
                .truckSpace(parcel.getForm())
                .width(TRUCK_WIDTH)
                .height(TRUCK_HEIGHT)
                .parcels(List.of(parcel))
                .build()).toList();
    }

    /**
     * Shows trucks
     *
     * @param trucks list of trucks
     * @return truck composition in text format
     */
    public String showTrucks(List<Truck> trucks) {
        StringBuilder sb = new StringBuilder();
        trucks.forEach(truck -> sb.append(truck.toStringFormat()));
        return sb.toString();
    }
}
