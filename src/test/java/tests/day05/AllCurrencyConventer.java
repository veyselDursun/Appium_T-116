package tests.day05;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AllCurrencyConverterPage;
import utils.Driver;

public class AllCurrencyConventer {

    AndroidDriver<AndroidElement> driver = Driver.getAndroidDriver();

    AllCurrencyConverterPage all = new AllCurrencyConverterPage();

    @Test
    public void allCurrencyTest() {

        // all currency uygulamasinin yuklendigi dogulanir
        Assert.assertTrue(driver.isAppInstalled("com.smartwho.SmartAllCurrencyConverter"));

        // uygulamanin acildigi dogrulanir

         String updateButonText = all.updateButton.getText();
         String expected = "CURRENCY\n" +
                            "UPDATE";

         Assert.assertEquals(updateButonText,expected);

        // cevirmek istedigimiz para birimi zloty olarak secilir
        // cevirelecek olan para birimi Tl olarak secilir
        // cevrilen tutar screenShot olarak kaydedilir
        // Ardindan zloty nin tl karsiligi olan tl degeri kaydedilir
        // bu islem dolar tl, sweden kron-tl, Japon yeni- tl olarak tekrarlanir ve kullaniciya sms olarak bildirilir

    }


}
