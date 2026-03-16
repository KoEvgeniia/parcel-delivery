package javacourse.factory;

import javacourse.service.LoaderType;
import javacourse.service.TruckParcelLoader;
import javacourse.service.TruckParcelLoaderOneToOne;
import javacourse.service.TruckParcelLoaderTower;

public class LoaderFactory {
      public TruckParcelLoader createTruckParcelLoader(LoaderType loaderType) {
          return switch (loaderType) {
              case ONE_BY_ONE -> new TruckParcelLoaderOneToOne();
              case TOWER -> new TruckParcelLoaderTower();
          };
      }
}
