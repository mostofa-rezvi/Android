package com.example.foodzilla3.model.restaurant

class Item(
    var name: String = "",
    var price: String = "",
    var rating: String = "",
var noOrder:Int=0
){
    fun itemDesplay(){
        print(name+" "+price+" "+rating)
    }
}