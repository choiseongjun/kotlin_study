package Exception

fun<K,V> Map<K,V>.getResult(key:K) = when{
    this.containsKey(key) -> Result(this[key])
    else -> Result.failure("Key $key not found in map")
}

data class Toon private constructor(val firstName:String,
                                    val lastName:String,
                                    val email:Result<String>){
    companion object{
        operator fun invoke(firstName: String,
                            lastName: String) = Toon(firstName, lastName,Result.failure("$firstName $lastName has no mail"))
        operator fun invoke(firstName: String,
                            lastName:String,
                            email:String) = Toon(firstName,lastName,Result(email))

    }
}