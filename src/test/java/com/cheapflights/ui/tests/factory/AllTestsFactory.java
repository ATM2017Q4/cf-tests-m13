package com.cheapflights.ui.tests.factory;

import com.cheapflights.ui.entities.TravelInfo;
import com.cheapflights.ui.tests.CheapFlightsTest;
import com.cheapflights.ui.utils.FileSearchUtil;
import com.cheapflights.ui.utils.JsonUtil;

import org.testng.annotations.Factory;

import java.util.List;

public class AllTestsFactory {
    private static String folderPath = "./src/test/resources/";
    private static String extension = ".json";

    @Factory
    public Object[] createInstance() {
        List<String> files = FileSearchUtil.getDirectoryFiles(folderPath, extension);
        Object[] objects = new Object[]{
                new CheapFlightsTest(JsonUtil.getInstance().readJson(files.get(0), TravelInfo.class)),
                new CheapFlightsTest(JsonUtil.getInstance().readJson(files.get(1), TravelInfo.class))

        };
        return objects;
    }
}
