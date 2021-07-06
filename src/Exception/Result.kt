package Exception

import java.io.IOException
import java.io.Serializable
import java.lang.Exception
import java.lang.IllegalStateException
import java.lang.NullPointerException
import java.lang.RuntimeException

sealed class Result<out A>:Serializable {
    internal
    class Failure<out A>(
        internal val exception: RuntimeException):Result<A>(){
        override fun toString(): String ="Failure(${exception.message}"
        }

    internal
    class Success<out A>(internal val value: A):Result<A>(){
        override fun toString(): String ="Success(${value}"
    }

    companion object{
        operator fun <A> invoke(a : A?=null):Result<A> = when(a){
            null->Failure(NullPointerException())
            else -> Success(a)
        }
        fun <A> failure(message:String):Result<A> = Failure(IllegalStateException(message))
        fun <A> failure(exception: RuntimeException): Result<A> = Failure(exception)
        fun <A> failure(exception: Exception): Result<A> = Failure(IllegalStateException(exception))
    }
}

fun main(args:Array<String>) {
    val toons : Map<String,Toon> = mapOf(
        "Mickey" to Toon("Mickey","Mouse","mocikey@disney.com"),
        "Minnie" to Toon("Minnie","Mouse"),
        "Donald" to Toon("Donald","Duck","donald@disney.com")
    )
    val toon = getName()

    println(toon)


}
fun getName():Result<String> =
    try{
        validate(readLine())
    }catch (e:IOException){
        Result.failure(e)
    }
fun validate(name:String?):Result<String> = when{
    name?.isNotEmpty()?:false -> Result(name)
    else->Result.failure("Invalid name $name")
}
