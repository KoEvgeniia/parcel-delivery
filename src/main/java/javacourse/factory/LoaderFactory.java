package javacourse.factory;

import javacourse.domain.LoaderType;
import javacourse.service.TruckParcelLoader;
import javacourse.service.TruckParcelLoaderOneToOne;
import javacourse.service.TruckParcelLoaderTower;

/**
 * Factory for creating a truck loader class depending on the type of loading
 */
public class LoaderFactory {
    /**
     * Creates a truck loader class depending on the type of loading
     * @param loaderType type of loading
     * @return truck loader class
     */
      public TruckParcelLoader createTruckParcelLoader(LoaderType loaderType) {
          return switch (loaderType) {
              case ONE_BY_ONE -> new TruckParcelLoaderOneToOne();
              case TOWER -> new TruckParcelLoaderTower();
          };
      }
}
