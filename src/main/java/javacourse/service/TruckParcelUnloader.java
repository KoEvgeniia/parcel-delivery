package javacourse.service;

import javacourse.domain.Parcel;
import javacourse.domain.Truck;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Unloads trucks
 */
public class TruckParcelUnloader {
    /**
     * Unloads trucks
     * @param trucks list of trucks
     * @return list of parcels
     */
    public List<Parcel> unloadTruck(List<Truck> trucks) {
        return trucks.stream().map(Truck::getParcels).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
