# TODOs

1. DONE Login screen: move start drawables to outside of TextInputLayout
2. DONE Login screen: Add logo at the top the layout
3. DONE Change the app logo
4. NO   Save state: logged in or logged out state
5. DONE Welcome screen: Put the layout inside ScrollView
6. DONE MainActivity: setup the nav controller with the toolbar and an AppBarConfiguration
7. DONE Create a detail screen that shows two columns of labels and edit views to enter in a new item.
    > Use BottomSheet to show the EditText when user presses to add to Cart or to add to Wish list
8. Show cart feature
    ```kotlin
    // at ProductListViewModel

    private val _showCart = MutableLiveData<Event<ShoppingCart>>()
    val showCart: LiveData<Event<ShoppingCart>>
        get() = _showCart
            
    _cart.value?.let { cart ->
        _showCart.postEvent(cart)
    }


    // 


    productListViewModel.showCart.observe(viewLifecycleOwner, EventObserver {

    })
    ```