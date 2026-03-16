package javacourse.service;

import javacourse.domain.Parcel;
import javacourse.domain.Truck;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TruckParcelLoaderTower implements TruckParcelLoader {
    final int TRUCK_HEIGHT = 6;
    final int TRUCK_WIDTH = 6;
    final int TRUCK_HEIGHT_START_POSITION = 0;

    public List<Truck> loadTruck(List<Parcel> parcels) {
        List<Truck> trucks = new ArrayList<>();
        int height = TRUCK_HEIGHT_START_POSITION;
        int width = TRUCK_WIDTH;
        char[][] truckSpace = new char[TRUCK_HEIGHT][TRUCK_WIDTH];
        List<Parcel> sortedParcels = parcels.stream().sorted(Comparator.comparingInt(Parcel::getHeight).reversed()).sorted(Comparator.comparingInt(Parcel::getWidth).reversed()).toList();
        for (Parcel parcel : sortedParcels) {
            if (height + parcel.getHeight() > TRUCK_HEIGHT || parcel.getWidth() / 2 > width) {
                trucks.add(Truck.builder().truckSpace(truckSpace).width(TRUCK_WIDTH).height(TRUCK_HEIGHT).build());
                truckSpace = new char[TRUCK_HEIGHT][TRUCK_WIDTH];
                height = TRUCK_HEIGHT_START_POSITION;
            }
            for (int i = 0; i < parcel.getForm().length; i++) {
                char[][] from = parcel.getForm();
                for (int j = 0; j < from[i].length; j++) {
                    truckSpace[height + i][j] = from[i][j];
                }
            }
            height += parcel.getForm().length;
            width = parcel.getWidth();
        }
        trucks.add(Truck.builder().truckSpace(truckSpace).width(TRUCK_WIDTH).height(TRUCK_HEIGHT).build());
        return trucks;
    }

    public void showTrucks(List<Truck> trucks) {
        trucks.forEach(truck -> System.out.println(truck.toString()));
    }
}
