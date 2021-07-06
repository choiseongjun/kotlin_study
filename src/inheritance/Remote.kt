package inheritance

interface Remote {
    fun up()
    fun down()
    fun doubleUp(){
        up()
        up()
    }
}

class TV{
    var volumn = 0
}
class TVRemote(val tv:TV):Remote{
    override fun up() {
        tv.volumn++
    }

    override fun down() {
        tv.volumn--
    }
}

fun main() {
    val tv = TV()
    val remote:Remote = TVRemote(tv)
    println("Volumn :${tv.volumn}")
    remote.up()
    println("After increasing:${tv.volumn}")
    remote.doubleUp()
    println("After doubleUp:${tv.volumn}")
}