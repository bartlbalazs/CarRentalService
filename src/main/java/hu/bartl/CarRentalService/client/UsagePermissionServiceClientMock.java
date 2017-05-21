package hu.bartl.CarRentalService.client;

import hu.bartl.CarRentalService.model.Type;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsagePermissionServiceClientMock implements UsagePermissionServiceClient {

    private List<Type> permittedTypes = new ArrayList<>();

    public boolean isForeignUsagePermitted(Type type) {
        return true;
    }

    public void permitType(Type type) {
        permittedTypes.add(type);
    }
}
