package hu.bartl.CarRentalService.client;

import hu.bartl.CarRentalService.model.Type;

public interface UsagePermissionServiceClient {

    boolean isForeignUsagePermitted(Type type);
}
