package hu.bartl.CarRentalService.initializer;

import hu.bartl.CarRentalService.model.Car;
import hu.bartl.CarRentalService.model.Type;
import hu.bartl.CarRentalService.repository.CarRepository;
import hu.bartl.CarRentalService.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SampleDataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    public static final int CARS_PER_TYPE = 25;
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        initializeTypes();
        typeRepository.findAll().forEach(type -> {
            for (int i = 0; i < CARS_PER_TYPE; i++) {
                Car c = new Car();
                c.setType(type);
                Car savedCar = carRepository.save(c);
                savedCar.setDescription("Sample description for Car#" + savedCar.getId());
                carRepository.save(c);
            }
        });
    }

    private void initializeTypes() {
        Type t1 = new Type();
        t1.setManufacturer("Ford");
        t1.setModel("Fiesta");
        typeRepository.save(t1);

        Type t2 = new Type();
        t2.setManufacturer("Suzuki");
        t2.setModel("Swift");
        typeRepository.save(t2);

        Type t3 = new Type();
        t3.setManufacturer("Opel");
        t3.setModel("Corsa");
        typeRepository.save(t3);

        Type t4 = new Type();
        t4.setManufacturer("Toyota");
        t4.setModel("Yaris");
        typeRepository.save(t4);
    }
}
