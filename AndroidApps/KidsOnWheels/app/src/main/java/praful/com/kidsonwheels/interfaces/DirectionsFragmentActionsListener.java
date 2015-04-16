package praful.com.kidsonwheels.interfaces;

import praful.com.kidsonwheels.model.Student;

/**
 * Created by prafulmantale on 4/15/15.
 */
public interface DirectionsFragmentActionsListener {
    public void pickupStateUpdated(Student student, Student.PickupState newState);
    public void ShowTravelSteps();
}
