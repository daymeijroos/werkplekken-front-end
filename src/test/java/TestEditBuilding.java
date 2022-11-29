import com.example.werkplekkenfrontend.controllers.AdminEditBuildingViewController;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestEditBuilding {
    AdminEditBuildingViewController classInTesting = new AdminEditBuildingViewController();

    @Test
    public void should_returnFalse_when_listContainsEmptyString(){
        //asses
        String item0 = "This is a string";
        String item1 = "";
        String item2 = "This is another string";
        List<String> itemList = List.of(item0, item1, item2);
        boolean expectedResult = false;

        //act
        boolean actualResult = classInTesting.validityCheck(itemList);

        //assert
        assertThat(expectedResult, is(actualResult));
    }

    @Test
    public void should_returnTrue_when_listContainsNoEmptyString(){
        //asses
        String item0 = "This is a string";
        String item1 = "This is also a string";
        String item2 = "This is another string";
        List<String> itemList = List.of(item0, item1, item2);
        boolean expectedResult = true;

        //act
        boolean actualResult = classInTesting.validityCheck(itemList);

        //assert
        assertThat(expectedResult, is(actualResult));
    }
}
