package com.example.foodzilla3.model.restaurant

import java.io.Serializable


class Resturant(
    val menuList: ArrayList<MenuType>,
    var resturantName: String? ="",
    var resRating:String="0"
        ):Serializable{

    fun addMenu(menu:MenuType){
                menuList.add(menu)
            }
    fun displayRes(){
        println("Resturant is "+resturantName)
        for (menu in menuList){
            menu.displayMenu()
        }
    }

}