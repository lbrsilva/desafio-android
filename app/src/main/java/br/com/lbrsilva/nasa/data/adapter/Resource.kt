package br.com.lbrsilva.nasa.data.adapter

data class Resource<out T>(val code: Int, val status: Status, val data: T?, val message: String?) {
    enum class Status {
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(200, Status.SUCCESS, data, null)
        }

        fun <T> error(code: Int = 500, message: String? = null): Resource<T> {
            return Resource(code, Status.ERROR, null, message)
        }
    }
}