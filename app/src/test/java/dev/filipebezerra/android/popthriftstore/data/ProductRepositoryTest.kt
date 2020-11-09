package dev.filipebezerra.android.popthriftstore.data

import dev.filipebezerra.android.popthriftstore.ServiceLocator
import org.hamcrest.Matchers.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ProductRepositoryTest {

    private val productRepository: ProductRepository = ServiceLocator.provideProductRepository()

    private lateinit var productThatImLookingFor: Product

    @Before
    fun setUp() {
        productThatImLookingFor = Product(
            id = 21026611,
            name = "Fedora de lã",
            sellerName = "Paula",
            imageUrl = "https://photos.enjoei.com.br/mocas-acessorios-chapeu-urban-outfitters-fedora-de-la/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy8xNjUzMTMvMDI1MDU1MTQwMmQyYWFlYTAzMjVhOWI1MTcxMDdmMjEuanBn",
            price = 150.00,
            discountedPrice = null,
            rating = 3.5F
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun when_list_products_then_return_all_products() {
        productRepository.apply {
            listProducts().also { productList ->
                assertNotNull(productList)
                productList.forEach { product ->
                    assertNotNull(product.id)
                    assertNotNull(product.name)
                    assertNotNull(product.sellerName)
                    assertNotNull(product.imageUrl)
                    assertNotNull(product.price)
                    assertNotEquals(product.price, product.discountedPrice)
                    assertNotNull(product.rating)
                    assertThat(product.rating, `is`(greaterThanOrEqualTo(0F)))
                    assertThat(product.rating, `is`(lessThanOrEqualTo(5F)))
                }
            }
        }
    }

    @Test
    fun given_the_product_im_looking_for_when_find_by_id_given_find_that_product() {
        productRepository.apply {
            findProductById(21026611).also { productFound ->
                assertNotNull(productFound)
                assertThat(productFound!!.id, `is`(equalTo(21026611)))
                assertThat(productFound.name, `is`(equalTo("Fedora de lã")))
                assertThat(productFound.sellerName, `is`(equalTo("Paula")))
                assertThat(
                    productFound.imageUrl,
                    `is`(equalTo("https://photos.enjoei.com.br/mocas-acessorios-chapeu-urban-outfitters-fedora-de-la/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy8xNjUzMTMvMDI1MDU1MTQwMmQyYWFlYTAzMjVhOWI1MTcxMDdmMjEuanBn"))
                )
                assertThat(productFound.price, `is`(equalTo(150.00)))
                assertThat(productFound.discountedPrice, `is`(nullValue()))
                assertThat(productFound.rating, `is`(equalTo(3.5F)))
            }
        }
    }
}