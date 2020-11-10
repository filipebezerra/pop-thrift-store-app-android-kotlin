package dev.filipebezerra.android.popthriftstore.data

import dev.filipebezerra.android.popthriftstore.ServiceLocator
import org.hamcrest.Matchers.*
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class ShoppingCartRepositoryTest {

    private val userRepository: UserRepository = ServiceLocator.provideUserRepository()

    private lateinit var user: User

    private val shoppingCartRepository: ShoppingCartRepository =
        ServiceLocator.provideShoppingCartRepository()

    private lateinit var productThatImBuying: Product

    private lateinit var productThatImBuyingToo: Product

    private lateinit var productThatImBuyingFinally: Product

    @Before
    fun setUp() {
        user = User(
            email = "filipebzerra@gmail.com",
            fullName = "Filipe Bezerra"
        )

        productThatImBuying = Product(
            id = 21026611,
            name = "Fedora de lã",
            sellerName = "Paula",
            imageUrl = "https://photos.enjoei.com.br/mocas-acessorios-chapeu-urban-outfitters-fedora-de-la/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy8xNjUzMTMvMDI1MDU1MTQwMmQyYWFlYTAzMjVhOWI1MTcxMDdmMjEuanBn",
            price = 150.00,
            discountedPrice = null,
            rating = 3.5F
        )
        productThatImBuyingToo = Product(
            id = 45355684,
            name = "Salto grosso preto Paula Villalonge",
            sellerName = "Gabriela",
            imageUrl = "https://photos.enjoei.com.br/mocas-calcados-sapatos-paula-villalonge-salto-grosso-preto-paula-villalonge/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy8xNDUzNDA5OS83NDAwOGM3ZjU0NWYxMzQ5YjNlZmJjNjM3YTg4ODE1Yi5qcGc",
            price = 135.00,
            discountedPrice = 72.00,
            rating = 4.5F
        )
        productThatImBuyingFinally = Product(
            id = 46398025,
            name = "Blusa off white listrado",
            sellerName = "Tia Li",
            imageUrl = "https://photos.enjoei.com.br/mocas-roupas-vestidos-sempre-magnifica-blusa-off-white-listrado/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy81OTAzMjM2LzU1NmMxMjEzMmY3MzQxMzNkNzBkM2FkMGFmZjRiMjkyLmpwZw",
            price = 70.00,
            discountedPrice = 59.00,
            rating = 5F
        )
    }

    @After
    fun tearDown() {}

    @Test(expected = IllegalStateException::class)
    fun given_no_logged_in_user_when_add_to_cart_then_throw_exception() {
        shoppingCartRepository.addToShoppingCart(productThatImBuying)
    }

    @Test
    fun given_products_that_im_buying_when_add_to_cart_then_verify_cart_with_all_products() {
        userRepository.signInUser("Filipe Bezerra", "filipebzerra@gmail.com")
        shoppingCartRepository.apply {
            addToShoppingCart(productThatImBuying).also {
                getCart().also { cart ->
                    assertNotNull(cart)
                    assertNotNull(cart.user)
                    assertNotNull(cart.products)
                    assertThat(cart.user!!.email, `is`(equalTo("filipebzerra@gmail.com")))
                    assertThat(cart.products, `is`(hasSize(1)))
                    assertThat(cart.products.first().name, `is`(equalTo("Fedora de lã")))
                }
            }

            addToShoppingCart(productThatImBuyingToo).also {
                getCart().also { cart ->
                    assertNotNull(cart)
                    assertNotNull(cart.user)
                    assertNotNull(cart.products)
                    assertThat(cart.user!!.email, `is`(equalTo("filipebzerra@gmail.com")))
                    assertThat(cart.products, `is`(hasSize(2)))
                    assertThat(
                        cart.products.last().name,
                        `is`(equalTo("Salto grosso preto Paula Villalonge"))
                    )
                }
            }

            addToShoppingCart(productThatImBuyingFinally).also {
                getCart().also { cart ->
                    assertNotNull(cart)
                    assertNotNull(cart.user)
                    assertNotNull(cart.products)
                    assertThat(cart.user!!.email, `is`(equalTo("filipebzerra@gmail.com")))
                    assertThat(cart.products, `is`(hasSize(3)))
                    assertThat(cart.products.last().name, `is`(equalTo("Blusa off white listrado")))
                }
            }
        }
    }

    @Test(expected = IllegalStateException::class)
    fun given_products_that_im_buying_when_add_twice_to_cart_then_throw_exception() {
        userRepository.signInUser("Filipe Bezerra", "filipebzerra@gmail.com")
        shoppingCartRepository.apply {
            addToShoppingCart(productThatImBuying)
            addToShoppingCart(productThatImBuying)
        }
    }
}