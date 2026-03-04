package javacourse.service;

import javacourse.domain.Parcel;
import javacourse.domain.Truck;

import java.util.List;

public interface TruckParcelLoader {
    List<Truck> loadTruck(List<Parcel> parcels);
    void showTrucks(List<Truck> trucks);
}
