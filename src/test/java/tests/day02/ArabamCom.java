package tests.day02;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.aspectj.weaver.ast.And;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ArabamCom {

    AndroidDriver<AndroidElement> driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        // capabilities.setCapability(MobileCapabilityType.APP,"C:\\Users\\ahmet\\IdeaProjects\\APPIUM_T-116\\Apps\\arabam.com_4.8.0_Apkpure.apk");
        capabilities.setCapability("appPackage", "com.dogan.arabam");//Hangi uygulama uzerinde calismak istiyorsak,
        // Apk infodan uyguluma bilgisine ulasabiliriz
        capabilities.setCapability("appActivity", "com.dogan.arabam.presentation.feature.home.HomeActivity");
        // Kullanacak oldugumuz uygulamayi belirledikten sonra, o uygulamada hangi sayfadan baslamak istiyorsak
        // onun degerini activities kisminda bularak appActivity degiskenin karsisina parametre olarak giriyoruz


        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }


    @Test
    public void arabamTest() throws InterruptedException {
        //  driver.activateApp("com.dogan.arabam"); Bir uygulamayi aktif hale getirmek
        //  istedigimizde apk info dan onun bundleId degerini bularak buradan aktif hale getirebiliriz

        // uygulamanin basarili bir sekilde yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled("com.dogan.arabam"));

        // uygulaminin basarili bir sekilde acildigi dogrulanir
        AndroidElement headerKontrol = driver.findElementById("com.dogan.arabam:id/tvShowroomInfo");
        Assert.assertTrue(headerKontrol.isDisplayed());

        // Arabam kac para bolumune tiklayalim
        driver.findElementByXPath("//*[@text='Arabam kaç para?']").click();

        // Aracimin fiyatini merak ediyorum bolumunetiklayalim
        driver.findElementByXPath("//*[@text='Aracımın fiyatını merak ediyorum']").click();

        // Wolkswagen markasini secelim
        Thread.sleep(1000);
        TouchAction action = new TouchAction<>(driver);

        // 535,1726   535,240
        action.press(PointOption.point(535, 1726)) // Telefondaki ilk tiklama islemini yaptigimiz yer
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300))) // press ve moveTo adimlarindaki mesafenin zaman araligi
                .moveTo(PointOption.point(535, 240)) // Telefondaki kaydirma islemini gerceklestirecegimiz yer
                .release()                                       // ekrandan parmaginizi kaldirma
                .perform();                                      // action in gorevleri yerine getir emrini verdigimiz kisim

        // Eger ki wait actiondaki sure milisaniye olarak arttirilirsa ekranin assagiya kayma hizimiz yavaslar ve daha az mesafe kat ederiz
        // Eger ki wait actiondaki sure milisaniye olarak azaltilirsa ekranin assgiya kayma hizi artar ve daha fazla mesafe kat ederiz.
        Thread.sleep(1000);
        driver.findElementByXPath("//*[@text='Volkswagen']").click();

        // yil secimi yapalim
        driver.findElementByXPath("//*[@text='2018']").click();

        // model secimi yapalim
        driver.findElementByXPath("//*[@text='Passat']").click();

        // govde tipini secelim
        driver.findElementByXPath("//*[@text='Sedan']").click();

        // yakit tipini secelim
        driver.findElementByXPath("//*[@text='Benzin']").click();

        // vites tipini secelim
        driver.findElementByXPath("//*[@text='Yarı Otomatik']").click();

        // Versiyon secimi yapalim
        //456 618 BURADA KOORDINAT UZERINDEN PRESS YAPTIK
        Thread.sleep(1000);
        action.press(PointOption.point(456,618))
                .release()
                .perform();

        // aracin km bilgilerini girelim
        AndroidElement kmBox = driver.findElementById("com.dogan.arabam:id/et_km");
        if (driver.isKeyboardShown()){
            driver.getKeyboard().pressKey("150000");
        } else {
            kmBox.sendKeys("150000");
        }
        Thread.sleep(1000);
        driver.findElementByXPath("//*[@text='Devam']").click();

        // aracin rengini secelim
        driver.findElementByXPath("(//*[@class='android.widget.TextView'])[16]").click();

        // opsiyel donanim (varsa) seecelim
        driver.findElementByXPath("//*[@text='Devam']").click();

        // degisen bilgisi ekleyerek tramer kaydi belirtelim
        // 538,795
        Thread.sleep(1000);
        action.press(PointOption.point(538,795)).release().perform();
        action.press(PointOption.point(233,1609)).release().perform();
        Thread.sleep(1000);
        driver.findElementByXPath("//*[@text='Devam']").click();
        Thread.sleep(1000);
        driver.findElementByXPath("//*[@text='Tramer kaydı yok']").click();
        Thread.sleep(1000);
        driver.findElementByXPath("//*[@text='Devam']").click();

        // aracimizin fiyatinin 500.000 tl den fazla oldugunu test edelim
        AndroidElement ortalamaFiyatSonucuLocate = driver.findElementById("com.dogan.arabam:id/tvAveragePrice");
        String ortalamaSonSonuc = ortalamaFiyatSonucuLocate.getText();

        System.out.println(ortalamaSonSonuc); // 1.169.500 TL
        ortalamaSonSonuc = ortalamaSonSonuc.replaceAll("\\D",""); // Digit olmayan herseyi hiclige cevirdi
        System.out.println(ortalamaSonSonuc); // 1169500

        Assert.assertTrue(Integer.parseInt(ortalamaSonSonuc)>500000); // Stringi Intigere cevirdi

        // uygulamayi kapatalim
        driver.closeApp();
    }

}