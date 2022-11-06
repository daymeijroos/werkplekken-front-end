import com.example.werkplekkenfrontend.daos.BuildingDao;
import com.example.werkplekkenfrontend.models.Building;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TestBuildingDao {

    private BuildingDao buildingDaoInTesting = new BuildingDao(new HttpService(), new ObjectMapper());
    private Building testBuilding;
    private JSONObject jsonObject;

    @Mock
    BuildingDao buildingDaoMock;

    @BeforeEach
    public void initBuildingAndJSON(){
        testBuilding = new Building(UUID.randomUUID(), "name", "zip", "city", "address");
        jsonObject = new JSONObject();
        jsonObject.put("id", testBuilding.getId().toString());
        jsonObject.put("name", testBuilding.getName());
        jsonObject.put("zipcode", testBuilding.getZipcode());
        jsonObject.put("city", testBuilding.getCity());
        jsonObject.put("address", testBuilding.getAddress());
    }

    @Test
    public void should_returnBuilding_when_buildingFromJSONIsCalled(){
        Building expectedBuilding = testBuilding;

        //act
        //Building actualBuilding = buildingDaoInTesting.BuildingFromJSON(jsonObject);

        //assert
        //assertThat(expectedBuilding.getName(), is(actualBuilding.getName()));
    }

    @Test
    public void should_returnBuildingWithSameID_when_getIsCalled(){
        //asses
        Building expectedBuilding = testBuilding;
        BuildingDao buildingDaoMock = Mockito.spy(new BuildingDao(new HttpService(), new ObjectMapper()));
        //MockitoAnnotations.initMocks(this);
        //when(buildingDaoMock.fetchResponseBodyFromURL(anyString())).thenReturn("{\"zipcode\":\"zip\",\"city\":\"city\",\"name\":\"name\",\"address\":\"address\",\"id\":\"" + testBuilding.getId() + "\"}");

        //act
        //Building actualBuilding = buildingDaoMock.get(expectedBuilding.getId());

        //assert
        //assertThat(expectedBuilding, is(actualBuilding));
    }
}
