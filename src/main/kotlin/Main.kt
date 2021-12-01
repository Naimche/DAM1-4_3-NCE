package un4.eje4_3

data class Tienda(val nombre: String, val clientes: List<Clientes>){
    fun obtenerConjuntoDeClientes(): Set<Clientes> = clientes.toSet()
    fun obtenerCiudadesDeClientes(): Set<Ciudad> = clientes.map { it.ciudad }.toSet()
    fun obtenerClientesPor(ciudad:Ciudad): List<Clientes> = clientes.filter { it.ciudad == ciudad  }
    fun checkTodosClientesSonDe(ciudad : Ciudad): Boolean = clientes.all { it.ciudad == ciudad }
    fun hayClientesDe(ciudad: Ciudad): Boolean = clientes.any{it.ciudad == ciudad}
    fun cuentaClientesDe(ciudad: Ciudad): Int = clientes.count(){it.ciudad == ciudad}
    fun encuentraClienteDe(ciudad: Ciudad): Clientes? = clientes.find { it.ciudad == ciudad }
    fun obtenerClientesOrdenadosPorPedidos(): List<Clientes> = clientes.sortedByDescending { it.pedidos.size }
    fun obtenerClientesConPedidosSinEngregar(): Set<Clientes> = clientes.partition { clientes -> clientes.pedidos.all { it.estaEntregado > !it.estaEntregado }}.second.toSet()

}

data class Clientes(val nombre: String, val ciudad: Ciudad, val pedidos: List<Pedido>) {
    override fun toString() = "$nombre from ${ciudad.nombre}"
    fun obtenerProductosPedidos(): List<Producto> =pedidos.flatMap { it.productos }
}

data class Pedido(val productos: List<Producto>, val estaEntregado: Boolean)

data class Producto(val nombre: String, val precio: Double) {
    override fun toString() = "'$nombre' for $precio"
}

data class Ciudad(val nombre: String) {
    override fun toString() = nombre
}
fun main(){
    val tarifa = Ciudad("Tarifa")
    val cadiz = Ciudad("Cádiz")
    val villamartin = Ciudad("Villamartin")

    val patatas = Producto("patatas", 2.0)
    val cebolla = Producto("Cebolla", 2.0)
    val lechuga = Producto("Lechuga", 3.0)

    val pedido1 = listOf(patatas)
    val pedido2 = listOf(cebolla)
    val pedido3 = listOf(lechuga)

    val listpedido1 = listOf(Pedido(pedido1, false))
    val listpedido2 = listOf(Pedido(pedido2, false))
    val listpedido3 = listOf(Pedido(pedido3, true))

    val cliente1 = Clientes("Naim", tarifa, listpedido1)
    val cliente2 = Clientes("Maria", villamartin, listpedido2)
    val cliente3 = Clientes("Benji", cadiz, listpedido3)
    val clientes = listOf(cliente1, cliente2, cliente3)

    val mercadona = Tienda("Mercadona",clientes)
    println("Clientes del Mercadona")
    println(mercadona.obtenerConjuntoDeClientes())
    println()
    println("Las ciudades a las que pertenecen los clientes")
    println(mercadona.obtenerCiudadesDeClientes())
    println()
    println("Clientes perteneciente a cadiz")
    println(mercadona.obtenerClientesPor(cadiz))
    println()
    println("Son todos los clientes de Tarifa")
    if (mercadona.checkTodosClientesSonDe(tarifa)) println("Si todos pertenecen a esa ciudad") else println("Todos no pertenecen a esa ciudad")
    println()
    if (mercadona.hayClientesDe(villamartin)) println("Si hay clientes de villamartin") else println("No hay clientes de villamartin")
    println()
    println("Cuantos clientes son de cadiz")
    println(mercadona.cuentaClientesDe(cadiz))
    println()
    println("Clientes de Tarifa")
    println(mercadona.encuentraClienteDe(tarifa))
    println()
    println("Clientes ordenados por pedidos")
    println(mercadona.obtenerClientesOrdenadosPorPedidos())
    println()
    println("")
    println(mercadona.obtenerClientesConPedidosSinEngregar())
    println(cliente1.obtenerProductosPedidos())

}