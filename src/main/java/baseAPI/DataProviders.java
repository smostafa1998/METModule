package baseAPI;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider
    public Object[][] getEssayFilterData(){
        Object[][] essayData = new Object[][]
                {{"1800–1900 A.D.", "Africa", "Artists","(153)"},
                        {"1–500 A.D.", "East Asia", "Asian Art","(130)"},
                        {"8000–2000 B.C.", "Europe", "Renaissance","(136)"}};
        return essayData;
    }


}
