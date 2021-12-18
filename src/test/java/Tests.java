import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Tests  extends  mainTest {

    SelenideElement signInButton =  $x(" //div[@class=\"HeaderMenu__button HeaderMenu__button_auth IconAndTextWithCount_mainHeader js--HeaderMenu__button_auth IconAndTextWithCount js--IconAndTextWithCount\"]");
    SelenideElement changecityButton = $x("//button[@class=\"js--CitiesSearch-trigger MainHeader__open-text TextWithIcon\"]");
    SelenideElement ourCity = $x("//span[@class=\"CitiesSearch__highlight\"]");
    SelenideElement inputCity = $x("//input[@class=\" InputBox__input js--InputBox__input js--CitiesSearch__input  InputSearch__container-input\"]");
    SelenideElement inputPhone = $x("//input[@class=\" InputBox__input js--InputBox__input  js--SignIn__login__container-input\"]");
    SelenideElement inputPassword = $x("//input[@class=\" InputBox__input js--InputBox__input  js--SignIn__password js--InputPassword InputPassword__container-input\"]");
    SelenideElement CatalogButton = $x("//button[@class=\"js--PopupCatalogMenu__button-open PopupCatalogMenu__button-open  Button  jsButton Button_theme_primary-transparent Button_size_m Button_with-icon\"]");

    SelenideElement HealthAndBeauty =  $x(".//a[@data-title=\"Красота и здоровье\"]");
    SelenideElement Brush = $x("//a[@data-title=\"Зубные щетки\"]");


    SelenideElement filterTag = $x("//p[@class=\"FilterTags__name js--FilterTags__name\"]");
    SelenideElement OneBrush = $x("//a[@class=\" ProductCardVertical__name  Link js--Link Link_type_default\"]");
    SelenideElement ToShoppingBasket = $x("//button[@data-label=\"Перейти в корзину\"]");
    ElementsCollection addToShoppingBasket = $$x("//button[@data-label=\"В корзину\"]");
    SelenideElement ProductPrice =  $x("//span[@class=\"ProductCardForBasket__price-current_current-price js--ProductCardForBasket__price-current_current-price \"]");
    ElementsCollection max = $$x("//input[@name=\"input-max\"]");
    ElementsCollection min = $$x("//input[@name=\"input-min\"]");

    @Test
    public void SignIn() {
        Selenide.open("https://www.citilink.ru/");
        signInButton.click();
        inputPhone.setValue("77777777777777202202");
        inputPassword.setValue("таолыфтвлдтсц-2=-12щ03");
        Assert.assertFalse(signInButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).isEnabled());

    }

    @Test
    public void checkOurCity() {
        Selenide.open("https://www.citilink.ru/");
        changecityButton.click();
        inputCity.setValue("Санкт-Петербург");
        ourCity.click();
        System.out.println(changecityButton.getText());
        Assert.assertTrue(changecityButton.getText().contains("Санкт-Петербург"));

    }

    @Test
    public void Brushes() {
        Selenide.open("https://www.citilink.ru/");
        CatalogButton.click();
        actions().moveToElement(HealthAndBeauty).perform();
        Brush.click();
        min.get(1).setValue("1999");
        max.get(1).setValue("3999");
        max.get(1).sendKeys(Keys.ENTER);
        filterTag.shouldHave(text("от 1999 до 3999 рублей"));
        OneBrush.click();
        addToShoppingBasket.get(1).click();
        ToShoppingBasket.click();
        ProductPrice.shouldBe(Condition.visible,Duration.ofSeconds(5));
        ProductPrice.shouldBe(Condition.enabled);

    }





}
