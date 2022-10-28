package com.qaprosoft.carina.demo;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.report.ReportContext;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.DriverHelper;

public class TitleTest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    @BeforeSuite()
    public void BeforeAutoDownload() {
        R.CONFIG.put("Title_download", "true");
        R.CONFIG.put("Title_screenshot", "false");
    }

    @Test()
    public void getTitleTest() {
        String url = "\"https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_SX300.jpg";

        LOGGER.info("Title's folder: {}", ReportContext.getTitlesFolder().getAbsolutePath());

        DriverHelper driverHelper = new DriverHelper(getDriver());
        driverHelper.openURL(url);
        pause(1);

        File file = ReportContext.getArtifact(getDriver(), "Avengers-Endgame.zip");
        Assert.assertTrue(file.exists(), "avengers-endgame.zip is not available among movie titles");
    }
    
    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Unable to find title:.*")
    public void getInvalidTitleTest() {
        String url = "https://www.free-css.com/assets/files/free-css-templates/download/page280/klassy-cafe.zip";

        LOGGER.info("Artifact's folder: {}", ReportContext.getArtifactsFolder().getAbsolutePath());

        DriverHelper driverHelper = new DriverHelper(getDriver());
        driverHelper.openURL(url);

        ReportContext.getArtifact(getDriver(), UUID.randomUUID().toString());
    }
   
    
    @Test()
    public void TitleTest() {
        String url1 = "https://www.free-css.com/assets/files/free-css-templates/download/page279/tropiko.zip";
        String url2 = "https://www.free-css.com/assets/files/free-css-templates/download/page280/solar.zip";

        R.CONFIG.put("movie_title", "true");

        LOGGER.info("Title's folder: {}", ReportContext.getArtifactsFolder().getAbsolutePath());

        DriverHelper driverHelper = new DriverHelper(getDriver());
        driverHelper.openURL(url1);
        driverHelper.openURL(url2);
        pause(1);

        
        List<String> fileNames = ReportContext.listArtifacts(getDriver());
        Assert.assertTrue(fileNames.contains("getTitles.zip"), "getTitles.zip not found");
        Assert.assertTrue(fileNames.contains("solar.zip"), "solar.zip not found");
        
        
        List<File> files = ReportContext.getTitles(getDriver(), ".+");
        Assert.assertEquals(files.size(), 2);
        
        files = ReportContext.getArtifacts(getDriver(), "solar.z.+");
        Assert.assertEquals(files.size(), 1);

        files = ReportContext.getArtifacts(getDriver(), "UUID.randomUUID().toString()");
        Assert.assertEquals(files.size(), 0);

        
    }    
    
}
