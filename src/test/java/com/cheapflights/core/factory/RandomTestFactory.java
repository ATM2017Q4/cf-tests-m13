package com.cheapflights.core.factory;

import com.cheapflights.ui.entities.TravelInfo;
import com.cheapflights.ui.tests.CheapFlightsTest;
import com.cheapflights.ui.utils.FileSearchUtil;
import com.cheapflights.ui.utils.JsonUtil;
import com.cheapflights.ui.utils.RandomUtil;
import org.testng.annotations.Factory;

import java.util.List;

public class RandomTestFactory {
    private static String folderPath = "./src/main/resources/travel-info-files";
    private static String extension = ".json";

    @Factory
    public Object[] createInstance() {
        List<String> files = FileSearchUtil.getDirectoryFiles(folderPath, extension);
        Object[] object = new Object[]{
                new CheapFlightsTest(JsonUtil.readJson(files.get(RandomUtil.generateRandom(0, files.size())), TravelInfo.class))
        };
        return object;
    }
}
