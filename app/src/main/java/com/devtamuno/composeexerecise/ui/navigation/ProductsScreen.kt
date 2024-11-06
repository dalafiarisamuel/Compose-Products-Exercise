package com.devtamuno.composeexerecise.ui.navigation

const val ARG_PRODUCT_ID = "product_id"

enum class ProductsScreen(val route: String) {
    PRODUCT_LIST("product_list"),
    PRODUCT_DETAIL("products/{$ARG_PRODUCT_ID}/detail");

    fun createPath(vararg args: Any): String {
        var route = route
        require(args.size == route.argumentCount) {
            "Provided ${args.count()} parameters, was expected ${route.argumentCount} parameters!"
        }
        route.arguments().forEachIndexed { index, matchResult ->
            route = route.replace(matchResult.value, args[index].toString())
        }
        return route
    }
}
