package org.steps;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.bg.И;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Тогда;
import org.driver.InitDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Steps {
    @Допустим("^Пользователь инициализирует драйвер и  открывает страницу \"([^\"]*)\"$")
    public void пользователь_инициализирует_драйвер_и_открывает_страницу(String arg1) {
        InitDriver.getDriver().get(arg1);
    }

    @Тогда("^Пользователь проверяет, что название таблицы \"([^\"]*)\"$")
    public void checkPageTitle(String string) {
        WebElement el = InitDriver.getDriver().findElement(By.xpath("//*[@class='container-fluid']/h5"));
        Assert.assertEquals("Название таблицы отличается от ожидаемого, ожидали 'Список товаров', получили " + el.getText(),
                string,
                el.getText());
    }

    @Тогда("^Пользователь проверяет, что наименования столбцов таблицы:$")
    public void вМенюДоступныПункты(DataTable arg) {
        List<String> list = arg.asList(String.class);
        List<WebElement> columnsNames = InitDriver.getDriver().findElements(By.xpath("//*[@scope='col']"));
        List<String> columnsNamesString = (List) columnsNames.stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertEquals(list, columnsNamesString);
    }

    @Тогда("^Пользователь добавлет товар:$")
    public void товар(DataTable arg) {
        List<String> list = arg.asList(String.class);
        WebElement addButton = InitDriver.getDriver().findElement(By.xpath("//*[text()='Добавить']"));
        addButton.click();
        WebElement name = InitDriver.getDriver().findElement(By.id("name"));
        name.click();
        name.sendKeys(list.get(0));
        WebElement type = InitDriver.getDriver().findElement(By.id("type"));
        type.click();
        List<WebElement> wes = InitDriver.getDriver().findElements(By.xpath("//*[@id='editForm']//option"));
        clickFromList(wes, list.get(1));
        if (list.get(2).equals("exotic")) {
            WebElement exotic = InitDriver.getDriver().findElement(By.id("exotic"));
            exotic.click();
        }
        WebElement saved = InitDriver.getDriver().findElement(By.id("save"));
        saved.click();
    }

    @Тогда("^Пользователь проверяет, что товар \"([^\"]*)\" присутствует в таблице$")
    public void checkP(String string) {
        Assert.assertTrue(InitDriver.getDriver().findElement(By.xpath("//*[text()='" + string + "']")).isDisplayed());
    }

    @И("Пользователь закрывает драйвер")
    public void cheeeeeckP() {
        InitDriver.getDriver().quit();
    }

    public void clickFromList(List<WebElement> list, String value) {
        Iterator var3 = list.iterator();

        while (var3.hasNext()) {
            WebElement el = (WebElement) var3.next();
            if (el.getText().equals(value)) {
                el.click();
            }
        }

    }
}
