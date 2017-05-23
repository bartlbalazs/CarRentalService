package hu.bartl.CarRentalService.model.projection;

import hu.bartl.CarRentalService.model.Type;

public interface CarShortInfo {

    String getCarId();

    Type getType();
}
