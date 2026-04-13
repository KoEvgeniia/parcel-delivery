package javacourse.service;

import javacourse.domain.Parcel;
import javacourse.domain.Truck;

import java.util.List;

/**
 * Provides methods for loading trucks
 */
public interface TruckParcelLoader {
    /**
     * Loads trucks
     *
     * @param parcels    list of parcels for loading
     * @param truckCount number of trucks
     * @return list of trucks
     */
    List<Truck> loadTruck(List<Parcel> parcels, Long truckCount);

    /**
     * Shows trucks
     *
     * @param trucks list of trucks
     * @return truck composition in text format
     */
    String showTrucks(List<Truck> trucks);
}