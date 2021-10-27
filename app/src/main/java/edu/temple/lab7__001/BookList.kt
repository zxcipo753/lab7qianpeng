package edu.temple.lab7__001

package edu.temple.assignment7audiobb

class BookList(){

    val booklist= mutableListOf<Book>()

    fun add(b:Book){
        booklist.add(b)
    }
    fun remove(b:Book){
        booklist.remove(b)
    }
    fun get(index:Int): Book {
        return  booklist[(index)]
    }
    fun size():Int{
        return booklist.size
    }

    //Override toString()
    override fun toString():String{
        var output = ""
        for(index in booklist.indices){
            output += index.toString()
        }
        return output
    }

}