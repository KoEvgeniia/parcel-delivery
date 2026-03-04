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
        Truck truck = Truck.builder().build();
        List<Truck> trucks = new ArrayList<>();
        int height = TRUCK_HEIGHT_START_POSITION;
        int width = TRUCK_WIDTH;
        List<Parcel> sortedParcels = parcels.stream().sorted(Comparator.comparingInt(Parcel::getHeight).reversed()).sorted(Comparator.comparingInt(Parcel::getWidth).reversed()).toList();
        for (Parcel parcel : sortedParcels) {
            if (height + parcel.getHeight() > TRUCK_HEIGHT || parcel.getWidth() / 2 > width) {
                trucks.add(Truck.builder().level1(truck.getLevel1()).level2(truck.getLevel2()).level3(truck.getLevel3()).level4(truck.getLevel4()).level5(truck.getLevel5()).level6(truck.getLevel6()).build());
                height = TRUCK_HEIGHT_START_POSITION;
            }
            switch (height) {
                case 0:
                    truck.setLevel1(String.format("%-6s", parcel.getLevel1()));
                    truck.setLevel2(String.format("%-6s", parcel.getLevel2()));
                    truck.setLevel3(String.format("%-6s", parcel.getLevel3()));
                    truck.setLevel4(String.format("%-6s", " "));
                    truck.setLevel5(String.format("%-6s", " "));
                    truck.setLevel6(String.format("%-6s", " "));
                    break;
                case 1:
                    truck.setLevel2(String.format("%-6s", parcel.getLevel1()));
                    truck.setLevel3(String.format("%-6s", parcel.getLevel2()));
                    truck.setLevel4(String.format("%-6s", parcel.getLevel3()));
                    break;
                case 2:
                    truck.setLevel3(String.format("%-6s", parcel.getLevel1()));
                    truck.setLevel4(String.format("%-6s", parcel.getLevel2()));
                    truck.setLevel5(String.format("%-6s", parcel.getLevel3()));
                    break;
                case 3:
                    truck.setLevel4(String.format("%-6s", parcel.getLevel1()));
                    truck.setLevel5(String.format("%-6s", parcel.getLevel2()));
                    truck.setLevel6(String.format("%-6s", parcel.getLevel3()));
                    break;
                case 4:
                    truck.setLevel5(String.format("%-6s", parcel.getLevel1()));
                    truck.setLevel6(String.format("%-6s", parcel.getLevel2()));
                    break;
                case 5:
                    truck.setLevel6(String.format("%-6s", parcel.getLevel1()));
                    break;
            }
            height += parcel.getHeight();
            width = parcel.getWidth();
        }
        trucks.add(Truck.builder().level1(truck.getLevel1()).level2(truck.getLevel2()).level3(truck.getLevel3()).level4(truck.getLevel4()).level5(truck.getLevel5()).level6(truck.getLevel6()).build());
        return trucks;
    }

    public void showTrucks(List<Truck> trucks) {
        trucks.forEach(truck -> System.out.println(truck.toString()));
    }
}
