import java.io.File
import java.io.FileWriter
import java.lang.Exception

var name: String = "test"
var did_they_sign_in:Boolean = false
fun main(args: Array<String>) {
    menu()
}
fun menu () {
    if (did_they_sign_in == false) {
        println("Hello sir, can you please give me your name?")
        var get_name: String = readln()
        name = get_name
        println("Hello " + name + " what do you want to do?")
        did_they_sign_in = true
    } else {
        println("Hello again "+ name + ", how can i help you?")
    }
    val file_name = File("my_database/"+ name + ".txt")
    val file_path = "my_database/" + name + ".txt"
    println("1) Add a new note\n2) Edit one\n3) Delete one\n4) End")


    var what_did_they_choose:Int = readln().toInt()

    if (what_did_they_choose == 1) {
        add_a_new_note(file_path)
    } else if (what_did_they_choose == 2) {
        edit_one(file_path)
    } else if (what_did_they_choose == 3) {
        delete_one(file_path)
    } else{
        end()
    }

}

fun add_a_new_note(the_path:String) {
    println("Please give me the new note: ")
    var the_note:String = readln()
    try {
        var add = FileWriter(the_path, true)
        add.write(the_note + "\n")
        add.close()
    } catch (ex: Exception){
        print(ex.message)
    }
    menu()
}

fun edit_one(the_path:String) {
    var i : Int = 0
    File(the_path).readLines().forEach{
        print((i++))
        print(": "+it+"\n")
    }
    println("Any one of these do you want to edit?")
    var choose:Int = readln().toInt()
    println("Please give me the new content:")

    var new_content:String = readln()
    val lines = File(the_path).readLines().toMutableList()

    if (choose >= 0 && choose < lines.size) {
        lines[choose] = new_content
        val writer = FileWriter(the_path)
        for (line in lines) {
            writer.write("$line\n")
        }
        writer.close()

        println("Line $choose has been edited.")
    } else {
        println("Invalid line number.")
    }
    menu()

}

fun delete_one(the_path:String) {
    var i : Int = 0
    File(the_path).readLines().forEach{
        print((i++))
        print(": "+it+"\n")
    }
    println("Any one of these do you want to delete?")
    var choose:Int = readln().toInt()
    val lines = File(the_path).readLines().toMutableList()

    if (choose >= 0 && choose < lines.size) {
        lines.remove(lines[choose])
        val writer = FileWriter(the_path)
        for (line in lines) {
            writer.write("$line\n")
        }
        writer.close()

        println("Line $choose has been deleted.")
    } else {
        println("Invalid line number.")
    }
    menu()
}

fun end(): Int {
    return 0
}
