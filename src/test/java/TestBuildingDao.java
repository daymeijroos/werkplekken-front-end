import com.example.werkplekkenfrontend.daos.BuildingDao;
import com.example.werkplekkenfrontend.models.Building;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class TestBuildingDao {

    private BuildingDao buildingDaoInTesting = new BuildingDao();
    private Building testBuilding;
    private JSONObject jsonObject;

    @Mock
    BuildingDao buildingDaoMock;

    @BeforeEach
    public void initBuildingAndJSON(){
        testBuilding = new Building(UUID.randomUUID(), "name", "zip", "city", "adress");
        jsonObject = new JSONObject();
        jsonObject.put("id", testBuilding.getId().toString());
        jsonObject.put("name", testBuilding.getName());
        jsonObject.put("zipcode", testBuilding.getZipcode());
        jsonObject.put("city", testBuilding.getCity());
        jsonObject.put("adress", testBuilding.getAdress());
    }

    @Test
    public void should_returnBuilding_when_buildingFromJSONIsCalled(){
        Building expectedBuilding = testBuilding;

        //act
        Building actualBuilding = buildingDaoInTesting.BuildingFromJSON(jsonObject);

        //assert
        assertThat(expectedBuilding.getName(), is(actualBuilding.getName()));
    }

    @Test
    public void should_returnBuildingWithSameID_when_getIsCalled(){
        //asses
        Building expectedBuilding = testBuilding;
        BuildingDao buildingDaoMock = Mockito.spy(new BuildingDao());
        //MockitoAnnotations.initMocks(this);
        when(buildingDaoMock.fetchResponseBodyFromURL(anyString())).thenReturn("{\"zipcode\":\"zip\",\"city\":\"city\",\"name\":\"name\",\"adress\":\"adress\",\"id\":\"" + testBuilding.getId() + "\"}");

        //act
        Building actualBuilding = buildingDaoMock.get(expectedBuilding.getId());

        //assert
        assertThat(expectedBuilding, is(actualBuilding));
    }
}
