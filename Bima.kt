
class StudentCard(
    val nim: String,
    var nama: String,
    val prodi: String
) {
    var totalSks: Int = 0
        private set

    val status: String
        get() = if (totalSks > 0) "Aktif" else "Baru"

    init {
        require(nim.length >= 7) { "NIM tidak valid" }
    }

    fun ambilSks(jumlah: Int) {
        if (jumlah > 0 && totalSks + jumlah <= 24) {
            totalSks += jumlah
        }
    }

    fun ringkasan() =
        "$nim · $nama · $prodi · $totalSks SKS"
}

fun main() {
    println("=== OBJEK VALID ===")

    val mahasiswa1 = StudentCard(
        nim = "2409020233",
        nama = "Muhammad Bima Maulana",
        prodi = "Teknologi Informasi"
    )

    mahasiswa1.ambilSks(20)

    println(mahasiswa1.ringkasan())
    println("Status: ${mahasiswa1.status}")

    println("\n=== OBJEK TIDAK VALID ===")

    try {
        val mahasiswa2 = StudentCard(
            nim = "88888",
            nama = "Teddy",
            prodi = "Teknologi Informasi"
        )

        println(mahasiswa2.ringkasan())
    } catch (e: IllegalArgumentException) {
        println("Gagal membuat objek: ${e.message}")
    }
}
