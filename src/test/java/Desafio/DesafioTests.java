package Desafio;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class DesafioTests {

    private WebDriver driver;

    @Before
    public void inicializarNavegador() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver\\version-93\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testpages.herokuapp.com/styled/basic-html-form-test.html");

    }

    @Test //interagindo com os campos solicitados e verificando se foram enviados corretamente.
    public void validarValoresNosCampos() {
        //Selecionar o campo do Username e digitar o solicitado.
        WebElement userName = driver.findElement(By.xpath("//*[@id=\"HTMLFormElements\"]/table/tbody/tr[1]/td/input"));
        userName.sendKeys("Vithor");

        //Selecionar o campo do Password e digitar o solicitado.
        WebElement passWord = driver.findElement(By.xpath("//*[@id=\"HTMLFormElements\"]/table/tbody/tr[2]/td/input"));
        passWord.sendKeys("senha123");

        //Selecionar o campo do Comments, limpar o mesmo e digitar o solicitado.
        WebElement textComments = driver.findElement(By.xpath("//*[@id=\"HTMLFormElements\"]/table/tbody/tr[3]/td/textarea"));
        textComments.clear();
        textComments.sendKeys("Comentario");

        //Buscar pelo CheckBox e marcar o Item 1.
        WebElement checkBox1 = driver.findElement(By.xpath("//*[@id=\"HTMLFormElements\"]/table/tbody/tr[5]/td/input[1]"));
        checkBox1.click();

        //Buscar pelo CheckBox e marcar o Item 2.
        WebElement checkBox2 = driver.findElement(By.xpath("//*[@id=\"HTMLFormElements\"]/table/tbody/tr[5]/td/input[2]"));
        checkBox2.click();

        //Buscar pelo Radio Itens e marcar o Radio 1.
        WebElement radioItems1 = driver.findElement(By.xpath("//*[@id=\"HTMLFormElements\"]/table/tbody/tr[6]/td/input[1]"));
        radioItems1.click();

        //Comando para  desmarcar  o item 4  e marcar o item pedido, o item 1 no Multiple Select Value.
        WebElement selectionItem = driver.findElement(By.xpath("//*[@id=\"HTMLFormElements\"]/table/tbody/tr[7]/td/select"));
        Select item4 = new Select(selectionItem);
        item4.deselectByValue("ms4");
        Select item1 = new Select(selectionItem);
        item1.selectByValue("ms1");

        //Buscar o DropDown e selecionar o DropDown Item 1.
        WebElement dropDown = driver.findElement(By.xpath("//*[@id=\"HTMLFormElements\"]/table/tbody/tr[8]/td/select/option[1]"));
        dropDown.click();

        //Clicar no Botão Submit e submeter o teste para verificar os valores.
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"HTMLFormElements\"]/table/tbody/tr[9]/td/input[2]"));
        submitButton.click();

        //Validações do Cenário 1 do Desafio

        //Validar o nome do usuario com o esperado.
        Assert.assertEquals("Vithor", driver.findElement(By.xpath("//*[@id=\"_valueusername\"]")).getText());

        //Validar a senha do usuario com o esperado.
        Assert.assertEquals("senha123", driver.findElement(By.xpath("//*[@id=\"_valuepassword\"]")).getText());

        //Validar se o campo de comentario foi limpo e digitado o esperado.
        Assert.assertEquals("Comentario", driver.findElement(By.xpath("//*[@id=\"_valuecomments\"]")).getText());

        //Validar se foi selecionado corretamente os itens no CheckBoxes com o esperado.
        Assert.assertEquals("cb1", driver.findElement(By.xpath("//*[@id=\"_valuecheckboxes0\"]")).getText());
        Assert.assertEquals("cb2", driver.findElement(By.xpath("//*[@id=\"_valuecheckboxes1\"]")).getText());
        Assert.assertEquals("cb3", driver.findElement(By.xpath("//*[@id=\"_valuecheckboxes2\"]")).getText());

        //Validar se foi selecionado o item corretamente no Radio Items com o esperado.
        Assert.assertEquals("rd1", driver.findElement(By.xpath("//*[@id=\"_valueradioval\"]")).getText());

        //Validar se foi selecionado o item corretamente no Multiple Select Values com o esperado.
        Assert.assertEquals("ms1", driver.findElement(By.xpath("//*[@id=\"_valuemultipleselect0\"]")).getText());

        //Validar se foi selecionado o item corretamente no DropDown com o esperado.
        Assert.assertEquals("dd1", driver.findElement(By.xpath("//*[@id=\"_valuedropdown\"]")).getText());

    }

    @Test //Validando que os dados em Username, Password e Comments estão sem valores.
    public void validarCamposSemValores() {

        //Limpar o campo de comentarios.
        WebElement textComments = driver.findElement(By.xpath("//*[@id=\"HTMLFormElements\"]/table/tbody/tr[3]/td/textarea"));
        textComments.clear();

        //Clicar no Botão Submit para verificar se os campos solicitados estão limpos.
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"HTMLFormElements\"]/table/tbody/tr[9]/td/input[2]"));
        submitButton.click();

        //Validações do Cenário 2 do Desafio

        //Validar o nome do usuario esta sem valor.
        Assert.assertEquals("No Value for username", driver.findElement(By.xpath("/html/body/div[1]/div[3]/p[1]/strong")).getText());

        //Validar a senha do usuario esta sem valor.
        Assert.assertEquals("No Value for password", driver.findElement(By.xpath("/html/body/div[1]/div[3]/p[2]/strong")).getText());

        //Validar se o campo de comentario foi limpo e esta sem valor.
        Assert.assertEquals("No Value for comments", driver.findElement(By.xpath("/html/body/div[1]/div[3]/p[3]/strong")).getText());

    }

    @After
    public void finalizarNavegador() {
        driver.quit();

    }

}
