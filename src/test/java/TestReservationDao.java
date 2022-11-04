import com.example.werkplekkenfrontend.daos.ReservationDao;
import com.example.werkplekkenfrontend.models.Reservation;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestReservationDao {
    @Test
    void should_returnBuilding_when_buildingFromJSONIsCalled(){
        //asses
        ReservationDao reservationDaoInTesting = new ReservationDao();
        Reservation expectedReservation = new Reservation("1", "1", new Long("18051333"), new Long("18051340"), 2, "Tram");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "1");
        jsonObject.put("userId", "1");
        jsonObject.put("dateIn", new Long("18051333"));
        jsonObject.put("dateOut", new Long("18051340"));
        jsonObject.put("amountOfPeople", 2);
        jsonObject.put("spaceId", "Tram");
        //act
        Reservation actualReservation = reservationDaoInTesting.ReservationFromJSON(jsonObject);
        //assert
        assertThat(expectedReservation.dateIn, is(actualReservation.dateIn));
    }
}