package hu.bartl.CarRentalService.client;

import hu.bartl.CarRentalService.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class UsagePermissionServiceClientMock implements UsagePermissionServiceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsagePermissionServiceClientMock.class);

    private List<Type> permittedTypes = new ArrayList<>();

    public boolean isForeignUsagePermitted(Type type) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            LOGGER.error("Exception during checking usage permission.", e);
        }
        return permittedTypes.contains(type);
    }

    public void permitType(Type type) {
        permittedTypes.add(type);
    }
}
