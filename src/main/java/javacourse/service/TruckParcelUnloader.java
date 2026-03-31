package javacourse.service;

import javacourse.domain.Parcel;
import javacourse.domain.Truck;
import javacourse.util.ParcelParser;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TruckParcelUnloader {
    public List<Parcel> unloadTruck(List<Truck> trucks) {
        return trucks.stream().map(Truck::toString).map(new ParcelParser()::parse
        ).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
