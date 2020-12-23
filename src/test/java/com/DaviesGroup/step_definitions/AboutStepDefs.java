package com.DaviesGroup.step_definitions;

import com.DaviesGroup.pages.AboutPage;
import com.DaviesGroup.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;

public class AboutStepDefs {

    AboutPage aboutPage = new AboutPage();

    @When("I click the {string} submodule under {string} Us module")
    public void i_click_the_submodule_under_us_module(String subModule, String module) {
      BrowserUtils.hover(aboutPage.navigateToModule(module));
      aboutPage.clickSubModule(subModule);
    }


    @Then("I capture the {string} office address")
    public void I_capture_the_office_address(String officeLocation) throws IOException {
        String actualOfficeLocation = aboutPage.getOfficeLocation(officeLocation);

        Assert.assertEquals(officeLocation,actualOfficeLocation);

    }
}
