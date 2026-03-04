package javacourse.service;

import javacourse.domain.Parcel;
import javacourse.domain.Truck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TruckParcelLoaderOneToOne implements TruckParcelLoader {
    public List<Truck> loadTruck(List<Parcel> parcels) {
        return parcels.stream().map(parcel -> Truck.builder()
                .level1(String.format("%-6s", parcel.getLevel1()))
                .level2(String.format("%-6s", parcel.getLevel2()))
                .level3(String.format("%-6s", parcel.getLevel3()))
                .level4(String.format("%-6s", ""))
                .level5(String.format("%-6s", ""))
                .level6(String.format("%-6s", ""))
                .build()).toList();
    }

    public void showTrucks(List<Truck> trucks) {
        trucks.forEach(truck -> System.out.println(truck.toString()));
    }
}
