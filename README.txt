Assumptions:
This is to be a web service.
It is acceptable to use a 3rd party library to handle the ws implementation.
I have chosen to use standard inbuilt eclipse (Axis libraries) for simplicity, Spring-WS is probably better in the real word.
I have decided to separate concerns by having ws packages for the actual exposed service, 'service' packages for the business logic, and 'dao' for storing things.
In the real world the DAO would store the information into persistence. This is considered out of the scope of the test. I feel structurally it is best to keep this, and mock the behaviour by having it interact with a separate class containing static variables. We don't /really/ need both, but it makes it more extensible
A map of lists keyed by type seems the simplest way to mock this up.
As no database it's ok to use static strings directly to represent error messages. In the real world this would map to an error messages table via a numeric id or similar.
I decided to create a base message interface with common (item, quantity) values and have superclasses for each message type. This seems like overkill but allows for more extensibility in the future.
Validation/Handling of 'amounts' is outwith the scope of this demonstration. The application isn't expected to prevent, for example, adjustments to the point of sales becoming negative.
Using long data types just in case, as if it were the real world.
We should reject messages trying to adjust items for which no sales exist.
Although we really need to store message type 3 to meet requirements, we may as well keep a record of all of them and filter for message 3 in case we need to keep them later.
We are not concerned with extra metadata in this test. There isn't a requirement to timestamp messages at this stage.

Testing:
JUnit tests have been included. These test the business logic at the service level. These are in the com.SRMessagingTest.JUnit area.
Simple tests have been provided for each message, GroupedTests uses these methods to generate multiple requests of each type to hit the report thresholds and checks those.

SOAPUI tests have also been included in the /SOAPUI Tests directory to test the web service interface.

Running:
The application can be ran as a jax/Axis web service from Eclipse using tomcat.
A prebuilt .war has been included under /build
