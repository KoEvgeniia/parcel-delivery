package javacourse.factory;

import javacourse.domain.LoaderType;
import javacourse.service.TruckParcelLoaderOneToOne;
import javacourse.service.TruckParcelLoaderTower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoaderFactoryTest {
    private LoaderFactory loaderFactory;

    @BeforeEach
    void setUp() {
        loaderFactory = new LoaderFactory();
    }

    @Test
    public void createLoaderOneByOne() {
        assertThat(loaderFactory.createTruckParcelLoader(LoaderType.ONE_BY_ONE)).isInstanceOf(TruckParcelLoaderOneToOne.class);
    }

    @Test
    public void createLoaderTower() {
        assertThat(loaderFactory.createTruckParcelLoader(LoaderType.TOWER)).isInstanceOf(TruckParcelLoaderTower.class);
    }
}