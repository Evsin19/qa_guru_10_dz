import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class LambdaTest {
private static final String REPOSITORY = "Evsin19/qa_guru_10_dz";
    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Ищем репозиторий " + REPOSITORY, () -> {
                    $(".search-input").click();
                    $("#query-builder-test").sendKeys(REPOSITORY);
                    $("#query-builder-test").submit();
                });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
                    $(linkText(REPOSITORY)).click();
                });

        step("Проверяем наличие Issue", () -> {
        $("#issues-tab").click();
        $(".container-md").shouldHave(text("Welcome to issues!"));
        });
    }
}
