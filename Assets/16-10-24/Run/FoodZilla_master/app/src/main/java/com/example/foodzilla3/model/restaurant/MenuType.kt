package com.example.foodzilla3.model.restaurant

class MenuType (
var menuTypeName:String="",
var itemsList:ArrayList<Item>
        ){
        fun addItem(item:Item){
                itemsList.add(item)

        }
        fun displayMenu(){
                print("Menu is "+menuTypeName)
                for (item in itemsList){
                        item.itemDesplay()
                }
        }
}