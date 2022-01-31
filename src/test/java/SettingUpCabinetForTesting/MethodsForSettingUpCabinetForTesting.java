package SettingUpCabinetForTesting;

import BaseActions.BaseActions;
import org.openqa.selenium.By;

public class MethodsForSettingUpCabinetForTesting extends BaseActions {
    public String[] arrayWithBuyerTypes={
            "Беларусь юр. лицо", "BelarusLegalPerson", "//option[text()='Юридическое лицо']",
            "Беларусь ИП", "BelarusIP", "//option[text()='Физическое лицо']",
            "Украина юр.лицо", "UkraineLegalPerson", "//option[text()='Юридическое лицо']",
            "Казахстан юр. лицо", "KazakhstanLegalPerson", "//option[text()='Юридическое лицо']",
            "Казахстан ИП", "KazakhstanIP", "//option[text()='Физическое лицо']",
    };
    public String[] arrayWithPropertyNames={
            "Название компании", "COMPANY",
            "Юридический адрес", "COMPANY_ADR",
            "Контактное лицо", "CONTACT_PERSON",
            "E-Mail", "EMAIL",
            "Город", "CITY",
            "Местоположение", "LOCATION",
            "ИНН", "INN",
    };

    public void navigationToBuyerTypes(){
        driver.navigate().to(b2bUrl.replaceAll("b2bcabinet/" , "") + "bitrix/admin/sale_person_type.php?lang=ru");
    }
    public void navigationToListOfProperties(){
        driver.navigate().to(b2bUrl.replaceAll("b2bcabinet/" , "") + "bitrix/admin/sale_order_props.php?lang=ru");
    }
    public void creatingBuyerTypes() {
        System.out.println(arrayWithBuyerTypes.length / 3);
        for (int i = 0; i < arrayWithBuyerTypes.length; i=i+3) {

        String nameOfBuyerType = arrayWithBuyerTypes[i];
        String nameOfBuyerTypeByEnglish = arrayWithBuyerTypes[i+1];
        By accordanceWithLocator = By.xpath(arrayWithBuyerTypes[i+2]);

        driver.findElement(By.xpath("//*[@class='ui-btn ui-btn-primary']")).click();
        driver.findElement(By.xpath("(//*[contains(@class, 'adm-designed-checkbox-label')])[1]")).click();
        driver.findElement(By.xpath("(//*[contains(@class, 'adm-designed-checkbox-label')])[2]")).click();
        driver.findElement(By.xpath("//*[@name='NAME']")).sendKeys(nameOfBuyerType);
        driver.findElement(By.xpath("//*[@name='CODE']")).sendKeys(nameOfBuyerTypeByEnglish);
        driver.findElement(By.xpath("//select")).click();
        driver.findElement(accordanceWithLocator).click();
        driver.findElement(By.xpath("//*[@class='adm-btn-save']")).click();
        }
    }
    int sortValue = 101;
    public void creatingPropertiesForTheseBuyerTypes(){
        for (int i = 0; i < arrayWithBuyerTypes.length; i=i+3) {
            driver.findElement(By.xpath("//*[@name='filter_person_type_id']")).click();
            System.out.println(arrayWithBuyerTypes[i]);
            driver.findElement(By.xpath("//*[@name='filter_person_type_id']//*[contains(text(), '"+ arrayWithBuyerTypes[i] + "')]")).click();
            driver.findElement(By.xpath("//*[@name='set_filter']")).click();
            for (int j = 0; j < arrayWithPropertyNames.length ; j=j+2) {
                driver.findElement(By.cssSelector(".adm-btn-add")).click();
                driver.findElement(By.xpath("//*[contains(@class, 'popup-menu-item-text')][contains(text(), '"+ arrayWithBuyerTypes[i] + "')]")).click();
                driver.findElement(By.xpath("//*[@name='NAME']")).sendKeys(arrayWithPropertyNames[j]);
                driver.findElement(By.xpath("//*[@name='CODE']")).sendKeys(arrayWithPropertyNames[j+1]);
                driver.findElement(By.xpath("//*[@name='SORT']")).clear();
                driver.findElement(By.xpath("//*[@name='SORT']")).sendKeys(String.valueOf(sortValue));
                sortValue++;
                driver.findElement(By.xpath("//*[contains(text(), 'Входит в профиль')] /following::label[1]")).click();
                scrollToTheElement("//*[contains(text(), 'Обязательное')] /following::label[1]");
                driver.findElement(By.xpath("//*[contains(text(), 'Обязательное')] /following::label[1]")).click();
                if (arrayWithPropertyNames[j].equals("Название компании")){
                    scrollUp();
                    driver.findElement(By.xpath("//*[contains(text(), 'Доступно в фильтре по заказам')] /following::label[1]")).click();
                    scrollToTheElement("//*[contains(text(), 'Использовать как название профиля')] /following::label[1]");
                    driver.findElement(By.xpath("//*[contains(text(), 'Использовать как название профиля')] /following::label[1]")).click();
                }
                if (arrayWithPropertyNames[j].equals("Юридический адрес")){
                    scrollToTheElement("//*[contains(text(), 'Является адресом')] /following::label[1]");
                    driver.findElement(By.xpath("//*[contains(text(), 'Является адресом')] /following::label[1]")).click();
                    scrollToTheElement("//*[contains(text(), 'Обязательное')] /following::label[1]");
                    driver.findElement(By.xpath("//*[contains(text(), 'Обязательное')] /following::label[1]")).click();
                    driver.findElement(By.xpath("//*[contains(text(), 'Много строк')] /following::label[1]")).click();
                }
                if (arrayWithPropertyNames[j].equals("Контактное лицо")){
                    scrollToTheElement("//*[contains(text(), 'Использовать как имя плательщика')] /following::label[1]");
                    driver.findElement(By.xpath("//*[contains(text(), 'Использовать как имя плательщика')] /following::label[1]")).click();
                }
                if (arrayWithPropertyNames[j].equals("E-Mail")){
                    scrollToTheElement("//*[contains(text(), 'Использовать как E-Mail')] /following::label[1]");
                    driver.findElement(By.xpath("//*[contains(text(), 'Использовать как E-Mail')] /following::label[1]")).click();
                }
                if (arrayWithPropertyNames[j].equals("Город")){
                    scrollUp();
                    driver.findElement(By.xpath("//*[contains(text(), 'Доступно в фильтре по заказам')] /following::label[1]")).click();
                    scrollToTheElement("//*[contains(text(), 'Обязательное')] /following::label[1]");
                    driver.findElement(By.xpath("//*[contains(text(), 'Обязательное')] /following::label[1]")).click();
                }
                if (arrayWithPropertyNames[j].equals("Местоположение")){
                    scrollToTheElement("//*[@name='TYPE']");
                    driver.findElement(By.xpath("//*[@name='TYPE']")).click();
                    driver.findElement(By.xpath("//option[@value='LOCATION']")).click();
                    scrollToTheElement("//*[contains(text(), 'Использовать как местоположение')] /following::label[1]");
                    driver.findElement(By.xpath("//*[contains(text(), 'Использовать как местоположение')] /following::label[1]")).click();
                    scrollToTheElement("//*[@name='INPUT_FIELD_LOCATION']");
                    driver.findElement(By.xpath("//*[@name='INPUT_FIELD_LOCATION']")).click();
                    driver.findElement(By.xpath("//*[text()='Город']")).click();
                    driver.findElement(By.xpath("//*[contains(text(), 'Использовать как местоположение для налогов')] /following::label[1]")).click();
                }
                if (arrayWithPropertyNames[j].equals("ИНН") && arrayWithBuyerTypes[i].contains("Беларусь")){
                    scrollUp();
                    driver.findElement(By.xpath("//*[@name='NAME']")).clear();
                    driver.findElement(By.xpath("//*[@name='NAME']")).sendKeys("УНП");
                    driver.findElement(By.xpath("//*[@name='CODE']")).clear();
                    driver.findElement(By.xpath("//*[@name='CODE']")).sendKeys("UNP");
                }
                if (arrayWithPropertyNames[j].equals("ИНН") && arrayWithBuyerTypes[i].contains("Украина")){
                    scrollUp();
                    driver.findElement(By.xpath("//*[@name='NAME']")).clear();
                    driver.findElement(By.xpath("//*[@name='NAME']")).sendKeys("ЕГРПОУ");
                    driver.findElement(By.xpath("//*[@name='CODE']")).clear();
                    driver.findElement(By.xpath("//*[@name='CODE']")).sendKeys("EGRPOU");
                }
                if (arrayWithPropertyNames[j].equals("ИНН") && arrayWithBuyerTypes[i].contains("Казахстан")){
                    scrollUp();
                    driver.findElement(By.xpath("//*[@name='NAME']")).clear();
                    driver.findElement(By.xpath("//*[@name='NAME']")).sendKeys("БСН");
                    driver.findElement(By.xpath("//*[@name='CODE']")).clear();
                    driver.findElement(By.xpath("//*[@name='CODE']")).sendKeys("BSN");
                }
                driver.findElement(By.cssSelector(".adm-btn-save")).click();
            }
        }
    }
}
