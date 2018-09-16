package ex10.com.example.coopermind.ex10

import java.io.Serializable

data class Carro(val modelo : String,
                  val ano:Int,
                  val fabricante:Int, // 0=VW; 1=GM; 2=Fiat; 3=FORD
                  val gasolina:Boolean,
                    val etanol:Boolean)  : Serializable

{
    enum class Fabricante(val id:Int, val montadora:String) {
        VW(0, "VW"),
        GM(1,"GM"),
        FIAT(2,"FIAT"),
        FORD(3,"FORD");
        companion object {
            @JvmStatic
            fun getMarcas():Array<String> =arrayOf("VW", "GM", "FIAT", "FORD")
        }
    }
}


