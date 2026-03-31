package javacourse.factory;

import javacourse.domain.LoaderType;
import javacourse.service.TruckParcelLoaderOneToOne;
import javacourse.service.TruckParcelLoaderTower;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoaderFactoryTest {
    @Test
    public void createLoaderOneByOne() {
        LoaderFactory loaderFactory = new LoaderFactory();
        assertThat(loaderFactory.createTruckParcelLoader(LoaderType.ONE_BY_ONE)).isInstanceOf(TruckParcelLoaderOneToOne.class);
    }

    @Test
    public void createLoaderTower() {
        LoaderFactory loaderFactory = new LoaderFactory();
        assertThat(loaderFactory.createTruckParcelLoader(LoaderType.TOWER)).isInstanceOf(TruckParcelLoaderTower.class);
    }
}