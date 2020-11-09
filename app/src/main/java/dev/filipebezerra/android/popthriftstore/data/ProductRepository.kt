package dev.filipebezerra.android.popthriftstore.data

class ProductRepository {

    companion object {
        @Volatile
        private var INSTANCE: List<Product>? = null

        fun getProductList(): List<Product> = INSTANCE ?: synchronized(this) {
            val instance = createProductList()
            INSTANCE = instance
            instance
        }

        private fun createProductList() =
            listOf(
                Product(
                    id = 45355684,
                    name = "Salto grosso preto Paula Villalonge",
                    sellerName = "Gabriela",
                    imageUrl = "https://photos.enjoei.com.br/mocas-calcados-sapatos-paula-villalonge-salto-grosso-preto-paula-villalonge/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy8xNDUzNDA5OS83NDAwOGM3ZjU0NWYxMzQ5YjNlZmJjNjM3YTg4ODE1Yi5qcGc",
                    price = 135.00,
                    discountedPrice = 72.00,
                    rating = 4.5F
                ),
                Product(
                    id = 46687226,
                    name = "Sapato peeptoe Christian Louboutin",
                    sellerName = "Anderson",
                    imageUrl = "https://photos.enjoei.com.br/mocas-calcados-sapatos-christian-louboutin-sapato-peeptoe-christian-louboutin/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy8xNzkwNC8wMDg5ODZlMDM0MzUzZDAwMzVjNGFlODdmOGUwOGUzNC5qcGc",
                    price = 895.00,
                    discountedPrice = 800.00,
                    rating = 5F
                ),
                Product(
                    id = 46765944,
                    name = "Tênis preto detalhe em verniz Satinato",
                    sellerName = "Bruna",
                    imageUrl = "https://photos.enjoei.com.br/mocas-calcados-tenis-satinato-tenis-preto-detalhe-em-verniz-satinato/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy8yODMzOTUvNzNlZTZiMDBhNjhiYWY5MTMzM2RjMThmOGJmNmQ3MzEuanBn",
                    price = 100.00,
                    discountedPrice = 81.00,
                    rating = 4.5F
                ),
                Product(
                    id = 46503603,
                    name = "Sandália vermelha 35 último par disponível",
                    sellerName = "Clotildes",
                    imageUrl = "https://photos.enjoei.com.br/mocas-calcados-sandalias-moleca-sandalia-preta-salto-pequeno-37/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy8xMzU1OTc2MS9iZjg2ODNjMmU4OWVkYmVlZTliZTgzMjViYThlNGJjMi5qcGc",
                    price = 110.00,
                    discountedPrice = null,
                    rating = 4.0F
                ),
                Product(
                    id = 46398025,
                    name = "Blusa off white listrado",
                    sellerName = "Tia Li",
                    imageUrl = "https://photos.enjoei.com.br/mocas-roupas-vestidos-sempre-magnifica-blusa-off-white-listrado/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy81OTAzMjM2LzU1NmMxMjEzMmY3MzQxMzNkNzBkM2FkMGFmZjRiMjkyLmpwZw",
                    price = 70.00,
                    discountedPrice = 59.00,
                    rating = 5F
                ),
                Product(
                    id = 46749525,
                    name = "Tamanco amber preto 38",
                    sellerName = "Bonequinha de luxo",
                    imageUrl = "https://photos.enjoei.com.br/mocas-calcados-sandalias-amber-tamanco-amber-preto-38/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy85MzA5MTAzLzVkYjA0OGI4NDI2ZWFkNzBjNWU3MGM5YmExZDc3ZWQ4LmpwZw",
                    price = 99.00,
                    discountedPrice = 84.00,
                    rating = 4.5F
                ),
                Product(
                    id = 46713451,
                    name = "Caneca prata 90 domus",
                    sellerName = "Juliannie",
                    imageUrl = "https://photos.enjoei.com.br/etc-tal-cacarecos-canequinhas-domus-caneca-prata-90-domus/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy8zMDc5MDcvNGI4ZTczM2FkMDdiNmE5NWQwNmRhMmE0MTc4MjlkYjUuanBn",
                    price = 100.00,
                    discountedPrice = null,
                    rating = 4.5F
                ),
                Product(
                    id = 35733825,
                    name = "Vestido verde escuro vazado Suely Cencini",
                    sellerName = "Pâmela",
                    imageUrl = "https://photos.enjoei.com.br/mocas-roupas-vestidos-suely-cencini-vestido-verde-escuro-vazado-suely-cencini/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy84MjU4MDU2L2M5YTE1MGEwNDA2ZTU1ZDFhYTc5NzAzZTRiMjY1YTZlLmpwZw",
                    price = 276.00,
                    discountedPrice = 138.00,
                    rating = 5F
                ),
                Product(
                    id = 29329569,
                    name = "Vestidinho anina",
                    sellerName = "Flávio",
                    imageUrl = "https://photos.enjoei.com.br/mocas-roupas-vestidos-anina-vestidinho-anina/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy83Mzk0MTQxL2Y5M2UwZGI2NWNjN2Q3YzBhMzI5YTVlN2Y4OTQ0OGJhLmpwZw",
                    price = 88.00,
                    discountedPrice = 35.00,
                    rating = 5F
                ),
                Product(
                    id = 45297601,
                    name = "Mochila vermelha Fjallraven Kanken",
                    sellerName = "Carol",
                    imageUrl = "https://photos.enjoei.com.br/mocas-bolsas-mochila-fjallraven-kanken-mochila-vermelha-fjallraven-kanken/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy84ODA0NTI5LzAwYTc3YTM4MDQ3YTY4ZGVmZjI2OGJiNTljMDdjN2FmLmpwZw",
                    price = 580.00,
                    discountedPrice = null,
                    rating = 5F
                ),
                Product(
                    id = 46958716,
                    name = "Biquíni top cortininha com calcinha asa delta",
                    sellerName = "Gabriela",
                    imageUrl = "https://photos.enjoei.com.br/mocas-praia-alurez-moda-praia-biquini-top-cortininha-com-calcinha-asa-delta/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy8xMTI0OTY3NS84YTRjOGIzNzI4MzJhNzQ3M2NmOTM5NmRkZGVlOTViZC5qcGc",
                    price = 125.00,
                    discountedPrice = null,
                    rating = 5F
                ),
                Product(
                    id = 47236159,
                    name = "Camisa listrada zara",
                    sellerName = "Gabriela",
                    imageUrl = "https://photos.enjoei.com.br/mocas-roupas-camisas-zara-camisa-listrada-zara/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy81MjA3OTk1LzI4Y2E3ZmY3NjVjMzlkYjhkN2VjYzgxMzM2NGUxNTA5LmpwZw",
                    price = 79.00,
                    discountedPrice = null,
                    rating = 4.5F
                ),
                Product(
                    id = 43323953,
                    name = "Blazer curto bege",
                    sellerName = "Gabriela",
                    imageUrl = "https://photos.enjoei.com.br/mocas-calcados-sapatos-paula-villalonge-salto-grosso-preto-paula-villalonge/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy8xNDUzNDA5OS83NDAwOGM3ZjU0NWYxMzQ5YjNlZmJjNjM3YTg4ODE1Yi5qcGc",
                    price = 135.00,
                    discountedPrice = 72.00,
                    rating = 4.5F
                ),
                Product(
                    id = 45355684,
                    name = "Salto grosso preto Paula Villalonge",
                    sellerName = "Paulo",
                    imageUrl = "https://photos.enjoei.com.br/mocas-roupas-casaquinhos-gritt-blazer-curto-bege/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy81MjA3OTk1L2ZiZmVmZmZkMWZkMThiYWI5MTNkYWQ5MjhkZGVjMzYxLmpwZw",
                    price = 75.00,
                    discountedPrice = 43.00,
                    rating = 4.5F
                ),
                Product(
                    id = 42498169,
                    name = "Panela de foundue - le creuset",
                    sellerName = "Maria",
                    imageUrl = "https://photos.enjoei.com.br/casa-cozinha-le-creuset-panela-de-foundue-le-creuset/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy8zNTcxNDcvMDQ1ZDNjODlkNTRkNDgxMWNmMjRmZThiN2Y4ZDBmMmIuanBn",
                    price = 900.00,
                    discountedPrice = 800.00,
                    rating = 4F
                ),
                Product(
                    id = 46291540,
                    name = "Panelas mãe ágata original",
                    sellerName = "Eliel",
                    imageUrl = "https://photos.enjoei.com.br/casa-cozinha-mae-agata-panelas-mae-agata-original/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy82Nzg5OTc2LzIxOWM1ZDkwYjVhMjJjZTdkNzI0ZmNjOGE1ZjA0NzA4LmpwZw",
                    price = 1499.00,
                    discountedPrice = 400.00,
                    rating = 4F
                ),
                Product(
                    id = 42256870,
                    name = "Panela de arroz elétrica",
                    sellerName = "Alana",
                    imageUrl = "https://photos.enjoei.com.br/casa-eletrodomesticos-lenoxx-casa-panela-de-arroz-eletrica/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy8xMTE5MDU3MC84OTg3MzBmMmVkM2JmMzFmMWY5NzcxNTVjNjVlOTBhYi5qcGc",
                    price = 120.00,
                    discountedPrice = null,
                    rating = 4F
                ),
                Product(
                    id = 47715807,
                    name = "Cinto p - fivela meia lua - bege",
                    sellerName = "Jenifer",
                    imageUrl = "https://photos.enjoei.com.br/mocas-acessorios-cintos-cinto-p-fivela-meia-lua-bege/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy82MDI0NTQxL2RiYjNiODM0YTY3YjZiNjk3NzMyZTYyNTVjOWVhYjM4LmpwZw",
                    price = 86.00,
                    discountedPrice = null,
                    rating = 5F
                ),
                Product(
                    id = 37747598,
                    name = "Cinto jeans",
                    sellerName = "Julia",
                    imageUrl = "https://photos.enjoei.com.br/mocas-acessorios-cintos-cinto-jeans/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy8xMDY1NTY1My9jZGM3Y2U4NTRlY2Y0ZmM5N2I2Y2Q0NmUyMjM3YzVjNS5qcGc",
                    price = 20.00,
                    discountedPrice = 12.00,
                    rating = 5F
                ),
                Product(
                    id = 45449157,
                    name = "Conjunto de mesa e cadeiras Tok Stok",
                    sellerName = "Otto e Liz",
                    imageUrl = "https://photos.enjoei.com.br/casa-moveis-mesas-tok-e-stok-conjunto-de-mesa-e-cadeiras-tok-stok/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy8xMjM1OTcwNy80NmQyMDIyYjE4MDdhY2ZkNTZhYmIwOGRlNWI5MWRkZC5qcGc",
                    price = 800.00,
                    discountedPrice = null,
                    rating = 4F
                ),
                Product(
                    id = 21026611,
                    name = "Fedora de lã",
                    sellerName = "Paula",
                    imageUrl = "https://photos.enjoei.com.br/mocas-acessorios-chapeu-urban-outfitters-fedora-de-la/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy8xNjUzMTMvMDI1MDU1MTQwMmQyYWFlYTAzMjVhOWI1MTcxMDdmMjEuanBn",
                    price = 150.00,
                    discountedPrice = null,
                    rating = 3.5F
                ),
            )
    }

    fun listProducts(): List<Product> = getProductList()

    fun findProductById(productId: Long): Product? = getProductList().find { it.id == productId }
}