package test.system.com.pyxis.petstore;

import com.pyxis.petstore.domain.Item;
import com.pyxis.petstore.domain.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.support.com.pyxis.petstore.web.DatabaseDriver;
import test.support.com.pyxis.petstore.web.PetStoreDriver;
import test.system.com.pyxis.petstore.page.HomePage;
import test.system.com.pyxis.petstore.page.ItemsPage;
import test.system.com.pyxis.petstore.page.ProductsPage;

import static test.support.com.pyxis.petstore.builders.ItemBuilder.anItem;
import static test.support.com.pyxis.petstore.builders.ProductBuilder.aProduct;

public class BrowseCatalogFeature {
	
	PetStoreDriver petstore = new PetStoreDriver();
	DatabaseDriver database = new DatabaseDriver();
	HomePage homePage;
	
	@Before public void
    startApplication() throws Exception {
        database.start();
        homePage = petstore.start();
	}

	@Test
	public void listsItemsOfProductsAvailableInCatalog() throws Exception {
		Product iguana = aProduct().withName("Iguana").build();
        Item greenAdult = anItem().of(iguana)
                .withNumber("12345678")
                .describedAs("Green Adult")
                .priced(18.5f).build();
        database.given(iguana, greenAdult);
		ProductsPage productsPage = homePage.searchFor("Iguana");
		ItemsPage itemsPage = productsPage.browseItemsOf("Iguana");
		itemsPage.displaysItem("12345678", "Green Adult", 18.5f);
	}
	
	@After public void
    stopApplication() {
		petstore.close();
		database.stop();
	}
}
