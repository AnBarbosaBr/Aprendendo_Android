package ex10.com.example.coopermind.ex10

data class Carro (val modelo : String,
                  val ano:Int,
                  val fabricante:Int, // 0=VW; 1=GM; 2=Fiat; 3=FORD
                  val gasolina:Boolean,
                    val etanol:Boolean) {
    enum class Fabricante(val id:Int) { VW(0), GM(1), FIAT(2), FORD(3) }
}


