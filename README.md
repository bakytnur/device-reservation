## Device Booking System

### APIs can be tested using Swagger:
http://localhost:8080/swagger-ui/index.html

### For booking and returning device
User should be registered and logged in.
Once the user logs in, the system will return Bearer Token

`/api/booking/device` API requires user authentication with `Bearer eyJWKRd...` style

I have tried to get the phone device information from https://github.com/shakee93/fonoapi,
But the project has been archived by the owner on May 19, 2023. It is now read-only.

Therefore, the device information is pre-loaded from the following file when the application is started.
```
/src/main/resources/data.sql
```


# API
## User Account Controller

1. POST `/api/user/authenticate` authenticateUser - Authenticates the user, and returns Bearer Token
2. POST `/api/user/register` registerAccount - Registers the user with email and password

## Device Booking Controller
1. GET `/api/booking/devices` getAllDevices - Returns the devices basic information
2. GET `/api/booking/devices/booking` getDeviceBookingStates - Returns the all devices current booking states
3. GET `/api/booking/devices/booking/{device_id}` getDeviceBookingStates - Return the booking state of a single device
4. POST `/api/booking/device` bookDevice - Books the device with `device_id`. 
Performs validation on device availability, device existence.
5. PATCH `/api/booking/device` returnDevice - Returns the previously booked `device_id`. 
Performs validation on device booking state, device existence, device booking ownership.

